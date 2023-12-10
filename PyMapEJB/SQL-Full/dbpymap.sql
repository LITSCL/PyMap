-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-12-2023 a las 20:47:12
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

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(1, 'Lácteos'),
(2, 'Despensa'),
(3, 'Congelados'),
(4, 'Herramientas'),
(5, 'Cervezas'),
(6, 'Aceite'),
(7, 'Limpieza');

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

--
-- Volcado de datos para la tabla `marca`
--

INSERT INTO `marca` (`id`, `nombre`, `logo`) VALUES
(1, 'Colun', 'Colun.png'),
(2, 'Wasil', 'Wasil.png'),
(3, 'Banquete', 'Banquete.png'),
(4, 'Trendy', 'Trendy.png'),
(5, 'Minuto Verde', 'Minuto Verde.png'),
(6, 'Dewalt', 'Dewalt.png'),
(7, 'Bauker', 'Bauker.png'),
(8, 'Starrett', 'Starrett.png'),
(9, 'Corona', 'Corona.png'),
(10, 'Pilsen', 'Pilsen.png'),
(11, 'Kunstmann', 'Kunstmann.png'),
(12, 'Kraft', 'Kraft.png'),
(13, 'Hellmans', 'Hellmans.png'),
(14, 'Chef', 'Chef.png'),
(15, 'Quix', 'Quix.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_negocio`
--

CREATE TABLE `marca_negocio` (
  `id` int(11) NOT NULL,
  `marca_id` int(11) NOT NULL,
  `negocio_rut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `marca_negocio`
--

INSERT INTO `marca_negocio` (`id`, `marca_id`, `negocio_rut`) VALUES
(1, 6, '43.251.591-8'),
(2, 7, '43.251.591-8'),
(3, 8, '43.251.591-8');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_tipo-negocio`
--

CREATE TABLE `marca_tipo-negocio` (
  `id` int(11) NOT NULL,
  `marca_id` int(11) NOT NULL,
  `tipo-negocio_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `marca_tipo-negocio`
--

INSERT INTO `marca_tipo-negocio` (`id`, `marca_id`, `tipo-negocio_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 2),
(7, 7, 2),
(8, 8, 2),
(9, 9, 3),
(10, 10, 3),
(11, 11, 3),
(12, 14, 1),
(13, 15, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_tipo-producto`
--

CREATE TABLE `marca_tipo-producto` (
  `id` int(11) NOT NULL,
  `marca_id` int(11) NOT NULL,
  `tipo-producto_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `marca_tipo-producto`
--

INSERT INTO `marca_tipo-producto` (`id`, `marca_id`, `tipo-producto_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 3, 4),
(5, 4, 5),
(6, 5, 6),
(7, 6, 7),
(8, 6, 8),
(9, 7, 7),
(10, 7, 8),
(11, 8, 7),
(12, 8, 8),
(13, 9, 9),
(14, 9, 10),
(15, 10, 9),
(16, 10, 10),
(17, 11, 9),
(18, 11, 10),
(19, 1, 11),
(20, 1, 12),
(21, 1, 13),
(22, 14, 16),
(23, 14, 17),
(24, 15, 18),
(25, 13, 15),
(26, 12, 14);

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

--
-- Volcado de datos para la tabla `negocio`
--

INSERT INTO `negocio` (`rut`, `nombre`, `ciudad`, `comuna`, `calle`, `dias_atencion`, `metodos_pago`, `estado`, `logo`, `tipo-negocio_id`, `usuario_correo`) VALUES
('43.251.591-8', 'Ferretería Lalo', 'Valparaíso', 'Quillota', 'Esmeralda 21', 'Lunes;Martes;Miércoles;Jueves;Viernes;SábadoLunes;Martes;Miércoles;Jueves;Viernes;Sábado', 'Débito;Transferencia;EfectivoDébito;Transferencia', 'Aprobado', 'ko7x8lwzp0oh8s8-Ferreteria Lalo.png', 2, 'manuel@falso.local');

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

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `precio`, `imagen`, `marca_id`, `categoria_id`, `tipo-producto_id`) VALUES
(1, '[Colun] Leche Entera 1L', '...', NULL, 'Colun Leche Entera 1L.png', 1, 1, 1),
(2, '[Colun] Leche Descremada 1L', '...', NULL, 'Colun Leche Descremada 1L.png', 1, 1, 1),
(3, '[Colun] Queso Gauda 500G', '...', NULL, 'Colun Queso Gauda 500G.png', 1, 1, 2),
(4, '[Colun] Queso Parmesano Rallado 80G', '...', NULL, 'Colun Queso Parmesano Rallado 80G.png', 1, 1, 2),
(5, '[Wasil] Arvegas 380G', '...', NULL, 'Wasil Arvegas 380G.png', 2, 2, 3),
(6, '[Wasil] Porotos Negros 380G', '...', NULL, 'Wasil Porotos Negros 380G.png', 2, 2, 3),
(7, '[Wasil] Primavera de Verduras 340G', '...', NULL, 'Wasil Primavera de Verduras 340G.png', 2, 2, 3),
(8, '[Banquete] Lentejas 1KG', '...', NULL, 'Banquete Lentejas 1KG.png', 3, 2, 4),
(9, '[Banquete] Porotos Blancos 1KG', '...', NULL, 'Banquete Porotos Blancos 1KG.png', 3, 2, 4),
(10, '[Banquete] Porotos Negros 1KG', '...', NULL, 'Banquete Porotos Negros 1KG.png', 3, 2, 4),
(11, '[Trendy] Helado Brownie Vainilla 2.5L', '...', NULL, 'Trendy Helado Brownie Vainilla 2.5L.png', 4, 3, 5),
(12, '[Trendy] Helado Chocolate Suizo 2.5L', '...', NULL, 'Trendy Helado Chocolate Suizo 2.5L.png', 4, 3, 5),
(13, '[Trendy] Helado Suspiros Bombón Escocés 720G', '...', NULL, 'Trendy Helado Suspiros Bombón Escocés 720G.png', 4, 3, 5),
(14, '[Minuto Verde] Arvejas Congeladas 500G', '...', NULL, 'Minuto Verde Arvejas Congeladas 500G.png', 5, 3, 6),
(15, '[Minuto Verde] Choclo Congelado 500G', '...', NULL, 'Minuto Verde Choclo Congelado 500G.png', 5, 3, 6),
(16, '[Minuto Verde] Zapallo en Cubos Congelado 500G', '...', NULL, 'Minuto Verde Zapallo en Cubos Congelado 500G.png', 5, 3, 6),
(17, '[Dewalt] Esmeril Angular 700W', '...', NULL, 'Dewalt Esmeril Angular 700W.png', 6, 4, 7),
(18, '[Dewalt] Sierra Caladora 650W', '...', NULL, 'Dewalt Sierra Caladora 650W.png', 6, 4, 7),
(19, '[Dewalt] Taladro Percutor 700W', '...', NULL, 'Dewalt Taladro Percutor 700W.png', 6, 4, 7),
(20, '[Bauker] Lijadora Orbital 220W', '...', NULL, 'Bauker Lijadora Orbital 220W.png', 7, 4, 7),
(21, '[Bauker] Pistola de Pintar 400W', '...', NULL, 'Bauker Pistola de Pintar 400W.png', 7, 4, 7),
(22, '[Bauker] Soldadora Arco Manual 160A', '...', NULL, 'Bauker Soldadora Arco Manual 160A.png', 7, 4, 7),
(23, '[Starret] Disco de Corte', '...', NULL, 'Starret Disco de Corte.png', 8, 4, 7),
(24, '[Starret] Huincha de Medir 3MTS', '...', NULL, 'Starret Huincha de Medir 3MTS.png', 8, 4, 8),
(25, '[Starret] Vernier Digital IP65', '...', NULL, 'Starret Vernier Digital IP65.png', 8, 4, 8),
(26, '[Corona] Cerveza Lager Botella 330CC', '...', NULL, 'Corona Cerveza Lager Botella 330CC.png', 9, 5, 9),
(27, '[Corona] Pack 18 Unidades Botella 330CC', '...', NULL, 'Corona Pack 18 Unidades Botella 330CC.png', 9, 5, 9),
(28, '[Corona] Pack 10 Unidades Lata 355CC', '...', NULL, 'Corona Pack 10 Unidades Lata 355CC.png', 9, 5, 10),
(29, '[Pilsen] Cerveza Lager Botella 300CC', '...', NULL, 'Pilsen Cerveza Lager Botella 300CC.png', 10, 5, 9),
(30, '[Pilsen] Cerveza Lager Lata 354CC', '...', NULL, 'Pilsen Cerveza Lager Lata 354CC.png', 10, 5, 10),
(31, '[Pilsen] Cerveza Lager Lata 473CC', '...', NULL, 'Pilsen Cerveza Lager Lata 473CC.png', 10, 5, 10),
(32, '[Kunstmann] Pack 12 Unidades Torobayo Botella 330CC', '...', NULL, 'Kunstmann Pack 12 Unidades Torobayo Botella 330CC.png', 11, 5, 9),
(33, '[Kunstmann] Pack 6 Unidades Lager Botella 330CC', '...', NULL, 'Kunstmann Pack 6 Unidades Lager Botella 330CC.png', 11, 5, 9),
(34, '[Kunstmann] Cerveza Torobayo Lata 470CC', '...', NULL, 'Kunstmann Cerveza Torobayo Lata 470CC.png', 11, 5, 10),
(35, '[Colun] Mantequilla 250G', '...', NULL, 'Colun Mantequilla 250G.png', 1, 2, 11),
(36, '[Colun] Manjar 1KG', '...', NULL, 'Colun Manjar 1KG.png', 1, 2, 13),
(37, '[Colun] Yoghurt Batido Light 125G', '...', NULL, 'Colun Yoghurt Batido Light 125G.png', 1, 1, 12),
(38, '[Hellmans] Ketchup Light 850G', '...', NULL, 'Hellmans Ketchup Light 850G.png', 13, 2, 15),
(39, '[Hellmmans] Mayonesa 930G', '...', NULL, 'Hellmmans Mayonesa 930G.png', 13, 2, 14),
(40, '[Kraft] Mayonesa 1,26KG', '...', NULL, 'Kraft Mayonesa 1,26KG.png', 12, 2, 14),
(41, '[Kraft] Mayonesa con Aceite de Palta 340G', '...', NULL, 'Kraft Mayonesa con Aceite de Palta 340G.png', 12, 2, 14),
(42, '[Chef] Aceite de Maravilla 1L', '...', NULL, 'Chef Aceite de Maravilla 1L.png', 14, 6, 16),
(43, '[Chef] Aceite Pepita de Uva 500ML', '...', NULL, 'Chef Aceite Pepita de Uva 500ML.png', 14, 6, 17),
(44, '[Quix] Lavaloza Concentrado Limón 1.5L', '...', NULL, 'Quix Lavaloza Concentrado Limón 1.5L.png', 15, 7, 18),
(45, 'Quix Lavaloza Concentrado Limón 750ML', '...', NULL, 'Quix Lavaloza Concentrado Limón 750ML.png', 15, 7, 18);

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

--
-- Volcado de datos para la tabla `producto_negocio`
--

INSERT INTO `producto_negocio` (`id`, `precio`, `producto_id`, `negocio_rut`) VALUES
(1, 71200, 17, '43.251.591-8'),
(2, 34000, 18, '43.251.591-8'),
(3, 0, 19, '43.251.591-8'),
(4, 92500, 20, '43.251.591-8'),
(5, 31700, 21, '43.251.591-8'),
(6, 145000, 22, '43.251.591-8'),
(7, 0, 23, '43.251.591-8'),
(8, 0, 24, '43.251.591-8'),
(9, 29900, 25, '43.251.591-8');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo-negocio`
--

CREATE TABLE `tipo-negocio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo-negocio`
--

INSERT INTO `tipo-negocio` (`id`, `nombre`) VALUES
(1, 'Minimarket'),
(2, 'Ferretería'),
(3, 'Botillería');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo-producto`
--

CREATE TABLE `tipo-producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `categoria_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo-producto`
--

INSERT INTO `tipo-producto` (`id`, `nombre`, `categoria_id`) VALUES
(1, 'Leche', 1),
(2, 'Queso', 1),
(3, 'Conserva', 2),
(4, 'Legumbre', 2),
(5, 'Helado', 3),
(6, 'Verdura Congelada', 3),
(7, 'Herramienta Eléctrica', 4),
(8, 'Herramienta Manual', 4),
(9, 'Cerveza en Botella', 5),
(10, 'Cerveza en Lata', 5),
(11, 'Mantequilla', 2),
(12, 'Yoghurt', 1),
(13, 'Manjar', 2),
(14, 'Mayonesa', 2),
(15, 'Ketchup', 2),
(16, 'Aceite de maravilla', 6),
(17, 'Aceite de uva', 6),
(18, 'Lavaloza', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo-producto_negocio`
--

CREATE TABLE `tipo-producto_negocio` (
  `id` int(11) NOT NULL,
  `tipo-producto_id` int(11) NOT NULL,
  `negocio_rut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo-producto_negocio`
--

INSERT INTO `tipo-producto_negocio` (`id`, `tipo-producto_id`, `negocio_rut`) VALUES
(1, 7, '43.251.591-8'),
(2, 8, '43.251.591-8');

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
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`correo`, `clave`, `tipo`, `primer_nombre`, `segundo_nombre`, `apellido_paterno`, `apellido_materno`, `imagen`) VALUES
('daniel@falso.local', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Administrador', 'Daniel', 'Andres', 'Alvarez', 'Zamorano', 'Daniel.png'),
('manuel@falso.local', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Cliente', 'Manuel', 'Gerardo', 'Rojas', 'Pinilla', 'Manuel.png');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marca`
--
ALTER TABLE `marca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `marca_negocio`
--
ALTER TABLE `marca_negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `marca_tipo-negocio`
--
ALTER TABLE `marca_tipo-negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `marca_tipo-producto`
--
ALTER TABLE `marca_tipo-producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT de la tabla `producto_compra`
--
ALTER TABLE `producto_compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto_negocio`
--
ALTER TABLE `producto_negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `tipo-negocio`
--
ALTER TABLE `tipo-negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipo-producto`
--
ALTER TABLE `tipo-producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `tipo-producto_negocio`
--
ALTER TABLE `tipo-producto_negocio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
