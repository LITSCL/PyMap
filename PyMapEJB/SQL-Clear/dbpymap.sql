-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-12-2023 a las 20:43:59
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbpymap`
--
CREATE DATABASE IF NOT EXISTS `dbpymap` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dbpymap`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id` int(11) NOT NULL,
  `coste` double NOT NULL,
  `estado` varchar(255) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `usuario_correo` varchar(255) NOT NULL,
  `negocio_rut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca`
--

CREATE TABLE `marca` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_negocio`
--

CREATE TABLE `marca_negocio` (
  `id` int(11) NOT NULL,
  `marca_id` int(11) NOT NULL,
  `negocio_rut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_tipo-negocio`
--

CREATE TABLE `marca_tipo-negocio` (
  `id` int(11) NOT NULL,
  `marca_id` int(11) NOT NULL,
  `tipo-negocio_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_tipo-producto`
--

CREATE TABLE `marca_tipo-producto` (
  `id` int(11) NOT NULL,
  `marca_id` int(11) NOT NULL,
  `tipo-producto_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `negocio`
--

CREATE TABLE `negocio` (
  `rut` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `comuna` varchar(255) NOT NULL,
  `calle` varchar(255) NOT NULL,
  `dias_atencion` varchar(255) NOT NULL,
  `metodos_pago` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `tipo-negocio_id` int(11) NOT NULL,
  `usuario_correo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio` double DEFAULT NULL,
  `imagen` varchar(255) NOT NULL,
  `marca_id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `tipo-producto_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_compra`
--

CREATE TABLE `producto_compra` (
  `id` int(11) NOT NULL,
  `compra_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `unidades` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_negocio`
--

CREATE TABLE `producto_negocio` (
  `id` int(11) NOT NULL,
  `precio` double DEFAULT NULL,
  `producto_id` int(11) NOT NULL,
  `negocio_rut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo-negocio`
--

CREATE TABLE `tipo-negocio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo-producto`
--

CREATE TABLE `tipo-producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `categoria_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo-producto_negocio`
--

CREATE TABLE `tipo-producto_negocio` (
  `id` int(11) NOT NULL,
  `tipo-producto_id` int(11) NOT NULL,
  `negocio_rut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `correo` varchar(255) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `primer_nombre` varchar(255) NOT NULL,
  `segundo_nombre` varchar(255) NOT NULL,
  `apellido_paterno` varchar(255) NOT NULL,
  `apellido_materno` varchar(255) NOT NULL,
  `imagen` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id`,`usuario_correo`,`negocio_rut`),
  ADD KEY `fk_pedido_usuario1_idx` (`usuario_correo`),
  ADD KEY `fk_compra_negocio1_idx` (`negocio_rut`);

--
-- Indices de la tabla `marca`
--
ALTER TABLE `marca`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `marca_negocio`
--
ALTER TABLE `marca_negocio`
  ADD PRIMARY KEY (`id`,`marca_id`,`negocio_rut`),
  ADD KEY `fk_marca_has_negocio_negocio1_idx` (`negocio_rut`),
  ADD KEY `fk_marca_has_negocio_marca1_idx` (`marca_id`);

--
-- Indices de la tabla `marca_tipo-negocio`
--
ALTER TABLE `marca_tipo-negocio`
  ADD PRIMARY KEY (`id`,`marca_id`,`tipo-negocio_id`),
  ADD KEY `fk_marca_has_tipo-negocio_tipo-negocio1_idx` (`tipo-negocio_id`),
  ADD KEY `fk_marca_has_tipo-negocio_marca1_idx` (`marca_id`);

--
-- Indices de la tabla `marca_tipo-producto`
--
ALTER TABLE `marca_tipo-producto`
  ADD PRIMARY KEY (`id`,`marca_id`,`tipo-producto_id`),
  ADD KEY `fk_marca_has_tipo-producto_tipo-producto1_idx` (`tipo-producto_id`),
  ADD KEY `fk_marca_has_tipo-producto_marca1_idx` (`marca_id`);

--
-- Indices de la tabla `negocio`
--
ALTER TABLE `negocio`
  ADD PRIMARY KEY (`rut`,`tipo-negocio_id`),
  ADD KEY `fk_negocio_usuario1_idx` (`usuario_correo`),
  ADD KEY `fk_negocio_tipo-negocio1_idx` (`tipo-negocio_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`,`marca_id`,`categoria_id`,`tipo-producto_id`),
  ADD KEY `fk_producto_categoria_idx` (`categoria_id`),
  ADD KEY `fk_producto_marca1_idx` (`marca_id`),
  ADD KEY `fk_producto_tipo_producto1_idx` (`tipo-producto_id`);

--
-- Indices de la tabla `producto_compra`
--
ALTER TABLE `producto_compra`
  ADD PRIMARY KEY (`id`,`compra_id`,`producto_id`),
  ADD KEY `fk_producto_has_pedido_pedido1_idx` (`compra_id`),
  ADD KEY `fk_producto_has_pedido_producto1_idx` (`producto_id`);

--
-- Indices de la tabla `producto_negocio`
--
ALTER TABLE `producto_negocio`
  ADD PRIMARY KEY (`id`,`producto_id`,`negocio_rut`),
  ADD KEY `fk_producto_has_negocio_negocio1_idx` (`negocio_rut`),
  ADD KEY `fk_producto_has_negocio_producto1_idx` (`producto_id`);

--
-- Indices de la tabla `tipo-negocio`
--
ALTER TABLE `tipo-negocio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo-producto`
--
ALTER TABLE `tipo-producto`
  ADD PRIMARY KEY (`id`,`categoria_id`),
  ADD KEY `fk_tipo_categoria1_idx` (`categoria_id`);

--
-- Indices de la tabla `tipo-producto_negocio`
--
ALTER TABLE `tipo-producto_negocio`
  ADD PRIMARY KEY (`id`,`tipo-producto_id`,`negocio_rut`),
  ADD KEY `fk_negocio_has_tipo-producto_tipo-producto1_idx` (`tipo-producto_id`),
  ADD KEY `fk_negocio_has_tipo-producto_negocio1_idx` (`negocio_rut`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marca`
--
ALTER TABLE `marca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marca_negocio`
--
ALTER TABLE `marca_negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marca_tipo-negocio`
--
ALTER TABLE `marca_tipo-negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marca_tipo-producto`
--
ALTER TABLE `marca_tipo-producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto_compra`
--
ALTER TABLE `producto_compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto_negocio`
--
ALTER TABLE `producto_negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo-negocio`
--
ALTER TABLE `tipo-negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo-producto`
--
ALTER TABLE `tipo-producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo-producto_negocio`
--
ALTER TABLE `tipo-producto_negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_compra_negocio1` FOREIGN KEY (`negocio_rut`) REFERENCES `negocio` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pedido_usuario1` FOREIGN KEY (`usuario_correo`) REFERENCES `usuario` (`correo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `marca_negocio`
--
ALTER TABLE `marca_negocio`
  ADD CONSTRAINT `fk_marca_has_negocio_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_marca_has_negocio_negocio1` FOREIGN KEY (`negocio_rut`) REFERENCES `negocio` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `marca_tipo-negocio`
--
ALTER TABLE `marca_tipo-negocio`
  ADD CONSTRAINT `fk_marca_has_tipo-negocio_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_marca_has_tipo-negocio_tipo-negocio1` FOREIGN KEY (`tipo-negocio_id`) REFERENCES `tipo-negocio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `marca_tipo-producto`
--
ALTER TABLE `marca_tipo-producto`
  ADD CONSTRAINT `fk_marca_has_tipo-producto_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_marca_has_tipo-producto_tipo-producto1` FOREIGN KEY (`tipo-producto_id`) REFERENCES `tipo-producto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `negocio`
--
ALTER TABLE `negocio`
  ADD CONSTRAINT `fk_negocio_tipo-negocio1` FOREIGN KEY (`tipo-negocio_id`) REFERENCES `tipo-negocio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_negocio_usuario1` FOREIGN KEY (`usuario_correo`) REFERENCES `usuario` (`correo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_producto_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_producto_tipo_producto1` FOREIGN KEY (`tipo-producto_id`) REFERENCES `tipo-producto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto_compra`
--
ALTER TABLE `producto_compra`
  ADD CONSTRAINT `fk_producto_has_pedido_pedido1` FOREIGN KEY (`compra_id`) REFERENCES `compra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_producto_has_pedido_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto_negocio`
--
ALTER TABLE `producto_negocio`
  ADD CONSTRAINT `fk_producto_has_negocio_negocio1` FOREIGN KEY (`negocio_rut`) REFERENCES `negocio` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_producto_has_negocio_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tipo-producto`
--
ALTER TABLE `tipo-producto`
  ADD CONSTRAINT `fk_tipo_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tipo-producto_negocio`
--
ALTER TABLE `tipo-producto_negocio`
  ADD CONSTRAINT `fk_negocio_has_tipo-producto_negocio1` FOREIGN KEY (`negocio_rut`) REFERENCES `negocio` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_negocio_has_tipo-producto_tipo-producto1` FOREIGN KEY (`tipo-producto_id`) REFERENCES `tipo-producto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
