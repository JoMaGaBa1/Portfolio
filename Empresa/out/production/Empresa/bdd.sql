CREATE DATABASE IF NOT EXISTS `empresa`;
USE `empresa`;

CREATE TABLE IF NOT EXISTS `departamento` (
  `id_dpto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_dpto` varchar(20) NOT NULL,
  `num_emp_dpto` int(11) NOT NULL,
  `jefe_dpto` varchar(20) NOT NULL,
  `servicios` varchar(50) NOT NULL,
  PRIMARY KEY (`id_dpto`),
  UNIQUE KEY `nombre_dpto` (`nombre_dpto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

REPLACE INTO `departamento` (`id_dpto`, `nombre_dpto`, `num_emp_dpto`, `jefe_dpto`, `servicios`) VALUES
	(1, 'Recursos Humanos', 15, 'Pepe', 'Contrataciones, altas y bajas'),
	(2, 'Marketing', 5, 'Inma', 'Posicionamiento'),
	(3, 'Financiero', 8, 'Mamen', 'Gestión financiera'),
	(4, 'NNTT', 49, 'Jose Manuel', 'Nuevas tecnologías'),
	(5, 'Internacionalización', 15, 'Pablo', 'Internacionalización global'),
	(6, 'Comercial', 7, 'Fran', 'Venta del producto'),
	(7, 'Comunicación', 4, 'Roberto', 'Comunicación y medios'),
	(8, 'Formación', 18, 'José Luis', 'Formación de los trabajadores'),
	(9, 'Producción', 39, 'Mateo', 'Producción de los bienes a vender'),
	(10, 'Gerencia', 2, 'Helio', 'Dirección y Gerencia');
