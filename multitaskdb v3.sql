-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-10-2024 a las 17:11:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `multitaskdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `salario` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id`, `nombre`, `cargo`, `salario`) VALUES
(1, 'Juan Pérez', 'Desarrollador', 3000.00),
(2, 'María López', 'Diseñadora', 2500.00),
(3, 'Carlos Gómez', 'Gerente de Proyecto', 4000.00),
(4, 'Laura Martínez', 'Analista de Sistemas', 3500.00),
(5, 'Ana Rodríguez', 'Tester', 2200.00),
(6, 'Luis Fernández', 'Administrador de Base de Datos', 3700.00),
(7, 'Sofía Torres', 'Desarrolladora Frontend', 3200.00),
(8, 'Pedro Sánchez', 'Desarrollador Backend', 3400.00),
(9, 'Julia Castro', 'Product Owner', 4500.00),
(10, 'Roberto Díaz', 'DevOps', 3900.00),
(11, 'Claudia Morales', 'Gerente de Ventas', 4800.00),
(12, 'Fernando Ruiz', 'Especialista en Marketing', 2800.00),
(13, 'Isabel Jiménez', 'Analista de Datos', 3000.00),
(14, 'Gabriel Silva', 'Soporte Técnico', 2100.00),
(15, 'Teresa León', 'Administrativa', 2300.00),
(16, 'Ricardo Castillo', 'Arquitecto de Software', 4200.00),
(17, 'Valentina Ríos', 'Ingeniera de Calidad', 2600.00),
(18, 'Javier Ortiz', 'Consultor', 3600.00),
(19, 'Martín Alvarado', 'Desarrollador Móvil', 3800.00),
(20, 'Diana Paredes', 'Scrum Master', 4100.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `contraseña` varchar(100) NOT NULL,
  `tipo_usuario` enum('Administrador','Empleado') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `contraseña`, `tipo_usuario`) VALUES
(1, 'juan', '12345678', 'Administrador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`,`tipo_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
