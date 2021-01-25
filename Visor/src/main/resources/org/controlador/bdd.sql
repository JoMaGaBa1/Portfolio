CREATE DATABASE IF NOT EXISTS `1T_DDI_PRACJASPERREPORTS_JMGB`;
USE `1T_DDI_PRACJASPERREPORTS_JMGB`;

-- Volcando estructura para tabla 1T_DDI_PRACJASPERREPORTS_JMGB.cuentas
CREATE TABLE IF NOT EXISTS `cuentas` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `titular` varchar(40) NOT NULL,
  `nacionalidad` varchar(20) NOT NULL,
  `fecha_apertura` date NOT NULL,
  `saldo` float(10,2) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla 1T_DDI_PRACJASPERREPORTS_JMGB.cuentas: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
REPLACE INTO `cuentas` (`numero`, `titular`, `nacionalidad`, `fecha_apertura`, `saldo`) VALUES
	(1, 'Alex', 'Española', '1980-05-04', 12587.23),
	(2, 'Jose Manuel', 'Española', '2019-04-15', 1436.28),
	(3, 'Eva', 'Francesa', '2005-11-10', 1528.00),
	(4, 'Sara', 'Francesa', '1990-06-17', 850.00),
	(5, 'Pedro', 'Argentina', '2018-02-14', 254.86);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
