SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `dbpymap` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `dbpymap` ;

-- -----------------------------------------------------
-- Table `dbpymap`.`categoria`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`marca`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`marca` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NOT NULL ,
  `logo` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`tipo-producto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`tipo-producto` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NOT NULL ,
  `categoria_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `categoria_id`) ,
  INDEX `fk_tipo_categoria1_idx` (`categoria_id` ASC) ,
  CONSTRAINT `fk_tipo_categoria1`
    FOREIGN KEY (`categoria_id` )
    REFERENCES `dbpymap`.`categoria` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`producto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`producto` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NOT NULL ,
  `descripcion` VARCHAR(255) NOT NULL ,
  `precio` DOUBLE NULL ,
  `imagen` VARCHAR(255) NOT NULL ,
  `marca_id` INT NOT NULL ,
  `categoria_id` INT NOT NULL ,
  `tipo-producto_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `marca_id`, `categoria_id`, `tipo-producto_id`) ,
  INDEX `fk_producto_categoria_idx` (`categoria_id` ASC) ,
  INDEX `fk_producto_marca1_idx` (`marca_id` ASC) ,
  INDEX `fk_producto_tipo_producto1_idx` (`tipo-producto_id` ASC) ,
  CONSTRAINT `fk_producto_categoria`
    FOREIGN KEY (`categoria_id` )
    REFERENCES `dbpymap`.`categoria` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_marca1`
    FOREIGN KEY (`marca_id` )
    REFERENCES `dbpymap`.`marca` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_tipo_producto1`
    FOREIGN KEY (`tipo-producto_id` )
    REFERENCES `dbpymap`.`tipo-producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`usuario` (
  `correo` VARCHAR(255) NOT NULL ,
  `clave` VARCHAR(255) NOT NULL ,
  `tipo` VARCHAR(255) NOT NULL ,
  `primer_nombre` VARCHAR(255) NOT NULL ,
  `segundo_nombre` VARCHAR(255) NOT NULL ,
  `apellido_paterno` VARCHAR(255) NOT NULL ,
  `apellido_materno` VARCHAR(255) NOT NULL ,
  `imagen` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`correo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`tipo-negocio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`tipo-negocio` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`negocio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`negocio` (
  `rut` VARCHAR(255) NOT NULL ,
  `nombre` VARCHAR(255) NOT NULL ,
  `ciudad` VARCHAR(255) NOT NULL ,
  `comuna` VARCHAR(255) NOT NULL ,
  `calle` VARCHAR(255) NOT NULL ,
  `dias_atencion` VARCHAR(255) NOT NULL ,
  `metodos_pago` VARCHAR(255) NOT NULL ,
  `estado` VARCHAR(255) NOT NULL ,
  `logo` VARCHAR(255) NOT NULL ,
  `tipo-negocio_id` INT NOT NULL ,
  `usuario_correo` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`rut`, `tipo-negocio_id`) ,
  INDEX `fk_negocio_usuario1_idx` (`usuario_correo` ASC) ,
  INDEX `fk_negocio_tipo-negocio1_idx` (`tipo-negocio_id` ASC) ,
  CONSTRAINT `fk_negocio_usuario1`
    FOREIGN KEY (`usuario_correo` )
    REFERENCES `dbpymap`.`usuario` (`correo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_negocio_tipo-negocio1`
    FOREIGN KEY (`tipo-negocio_id` )
    REFERENCES `dbpymap`.`tipo-negocio` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`compra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`compra` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `coste` DOUBLE NOT NULL ,
  `estado` VARCHAR(255) NOT NULL ,
  `fecha` DATE NOT NULL ,
  `hora` TIME NOT NULL ,
  `usuario_correo` VARCHAR(255) NOT NULL ,
  `negocio_rut` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`, `usuario_correo`, `negocio_rut`) ,
  INDEX `fk_pedido_usuario1_idx` (`usuario_correo` ASC) ,
  INDEX `fk_compra_negocio1_idx` (`negocio_rut` ASC) ,
  CONSTRAINT `fk_pedido_usuario1`
    FOREIGN KEY (`usuario_correo` )
    REFERENCES `dbpymap`.`usuario` (`correo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_negocio1`
    FOREIGN KEY (`negocio_rut` )
    REFERENCES `dbpymap`.`negocio` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`producto_compra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`producto_compra` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `compra_id` INT NOT NULL ,
  `producto_id` INT NOT NULL ,
  `unidades` INT NOT NULL ,
  PRIMARY KEY (`id`, `compra_id`, `producto_id`) ,
  INDEX `fk_producto_has_pedido_pedido1_idx` (`compra_id` ASC) ,
  INDEX `fk_producto_has_pedido_producto1_idx` (`producto_id` ASC) ,
  CONSTRAINT `fk_producto_has_pedido_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `dbpymap`.`producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_pedido_pedido1`
    FOREIGN KEY (`compra_id` )
    REFERENCES `dbpymap`.`compra` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`producto_negocio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`producto_negocio` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `precio` DOUBLE NULL ,
  `producto_id` INT NOT NULL ,
  `negocio_rut` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`, `producto_id`, `negocio_rut`) ,
  INDEX `fk_producto_has_negocio_negocio1_idx` (`negocio_rut` ASC) ,
  INDEX `fk_producto_has_negocio_producto1_idx` (`producto_id` ASC) ,
  CONSTRAINT `fk_producto_has_negocio_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `dbpymap`.`producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_negocio_negocio1`
    FOREIGN KEY (`negocio_rut` )
    REFERENCES `dbpymap`.`negocio` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`marca_tipo-negocio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`marca_tipo-negocio` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `marca_id` INT NOT NULL ,
  `tipo-negocio_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `marca_id`, `tipo-negocio_id`) ,
  INDEX `fk_marca_has_tipo-negocio_tipo-negocio1_idx` (`tipo-negocio_id` ASC) ,
  INDEX `fk_marca_has_tipo-negocio_marca1_idx` (`marca_id` ASC) ,
  CONSTRAINT `fk_marca_has_tipo-negocio_marca1`
    FOREIGN KEY (`marca_id` )
    REFERENCES `dbpymap`.`marca` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_marca_has_tipo-negocio_tipo-negocio1`
    FOREIGN KEY (`tipo-negocio_id` )
    REFERENCES `dbpymap`.`tipo-negocio` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`marca_negocio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`marca_negocio` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `marca_id` INT NOT NULL ,
  `negocio_rut` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`, `marca_id`, `negocio_rut`) ,
  INDEX `fk_marca_has_negocio_negocio1_idx` (`negocio_rut` ASC) ,
  INDEX `fk_marca_has_negocio_marca1_idx` (`marca_id` ASC) ,
  CONSTRAINT `fk_marca_has_negocio_marca1`
    FOREIGN KEY (`marca_id` )
    REFERENCES `dbpymap`.`marca` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_marca_has_negocio_negocio1`
    FOREIGN KEY (`negocio_rut` )
    REFERENCES `dbpymap`.`negocio` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`marca_tipo-producto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`marca_tipo-producto` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `marca_id` INT NOT NULL ,
  `tipo-producto_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `marca_id`, `tipo-producto_id`) ,
  INDEX `fk_marca_has_tipo-producto_tipo-producto1_idx` (`tipo-producto_id` ASC) ,
  INDEX `fk_marca_has_tipo-producto_marca1_idx` (`marca_id` ASC) ,
  CONSTRAINT `fk_marca_has_tipo-producto_marca1`
    FOREIGN KEY (`marca_id` )
    REFERENCES `dbpymap`.`marca` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_marca_has_tipo-producto_tipo-producto1`
    FOREIGN KEY (`tipo-producto_id` )
    REFERENCES `dbpymap`.`tipo-producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbpymap`.`tipo-producto_negocio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbpymap`.`tipo-producto_negocio` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `tipo-producto_id` INT NOT NULL ,
  `negocio_rut` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`, `tipo-producto_id`, `negocio_rut`) ,
  INDEX `fk_negocio_has_tipo-producto_tipo-producto1_idx` (`tipo-producto_id` ASC) ,
  INDEX `fk_negocio_has_tipo-producto_negocio1_idx` (`negocio_rut` ASC) ,
  CONSTRAINT `fk_negocio_has_tipo-producto_negocio1`
    FOREIGN KEY (`negocio_rut` )
    REFERENCES `dbpymap`.`negocio` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_negocio_has_tipo-producto_tipo-producto1`
    FOREIGN KEY (`tipo-producto_id` )
    REFERENCES `dbpymap`.`tipo-producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `dbpymap` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
