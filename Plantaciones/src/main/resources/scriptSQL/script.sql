-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.18-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura para tabla estudiocs.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrip` varchar(255) DEFAULT NULL,
  `nivel` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla estudiocs.roles: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `descrip`, `nivel`) VALUES
	(1, 'Administrador', 1),
	(2, 'Usuarios', 3),
	(4, 'Invitado', 2);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Volcando estructura para tabla estudiocs.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pass` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `fk_rol` int(11) NOT NULL,
  `inactivo` bit(1) DEFAULT NULL,
  `pass_orig` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa8tuc4kcqw7cjk2w4kb3q4l7j` (`fk_rol`),
  CONSTRAINT `FKa8tuc4kcqw7cjk2w4kb3q4l7j` FOREIGN KEY (`fk_rol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla estudiocs.usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `pass`, `usuario`, `fk_rol`, `inactivo`, `pass_orig`) VALUES
	(1, 'Jakarta89', 'admin', 1, b'0', 'Jakarta89'),
	(2, 'terminal1', 'terminal1', 2, b'1', 'terminal1');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

-- Volcando estructura para tabla estudiocs.cultivos
DROP TABLE IF EXISTS `cultivos`;
CREATE TABLE IF NOT EXISTS `cultivos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Volcando estructura para tabla estudiocs.plantaciones
DROP TABLE IF EXISTS `plantaciones`;
CREATE TABLE IF NOT EXISTS `plantaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `periodo` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_cultivo` int(11) NOT NULL,
  `porcentaje_lote` double DEFAULT NULL,
  `fecha_cierre` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKplantaciones_cultivo` (`id_cultivo`),
  CONSTRAINT `FKplantaciones_cultivo` FOREIGN KEY (`id_cultivo`) REFERENCES `cultivos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `compradores`;
CREATE TABLE IF NOT EXISTS `compradores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `iva` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lotes`;
CREATE TABLE IF NOT EXISTS `lotes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrip` varchar(255) DEFAULT NULL,
  `sup` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cosechas`;
CREATE TABLE IF NOT EXISTS `cosechas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `rendimientos` double NOT NULL,
  `plantaciones_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcosechas_plantaciones` (`plantaciones_id`),
  CONSTRAINT `FKcosechas_plantaciones` FOREIGN KEY (`plantaciones_id`) REFERENCES `plantaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
