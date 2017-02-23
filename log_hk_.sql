-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.19-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for log_hk_
CREATE DATABASE IF NOT EXISTS `log_hk_` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `log_hk_`;

-- Dumping structure for table log_hk_.auditlog
CREATE TABLE IF NOT EXISTS `auditlog` (
  `AUDIT_LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTION` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `DETAIL` longtext,
  `ENTITY_ID` varchar(255) DEFAULT NULL,
  `ENTITY_NAME` varchar(255) DEFAULT NULL,
  `MODULE` varchar(100) DEFAULT NULL,
  `USERNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`AUDIT_LOG_ID`),
  UNIQUE KEY `AUDIT_LOG_ID` (`AUDIT_LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table log_hk_.auditlog: ~-1 rows (approximately)
DELETE FROM `auditlog`;
/*!40000 ALTER TABLE `auditlog` DISABLE KEYS */;
INSERT INTO `auditlog` (`AUDIT_LOG_ID`, `ACTION`, `CREATED_DATE`, `DETAIL`, `ENTITY_ID`, `ENTITY_NAME`, `MODULE`, `USERNAME`) VALUES
	(1, 'INSERT', '2017-02-23 15:37:41', 'MODULE_ID : MATAUANG', 'MATAUANG', 'class com.hk.base.entity.Module', 'Module', 'it'),
	(2, 'INSERT', '2017-02-23 15:38:04', 'MODULE_ID : UNIT', 'UNIT', 'class com.hk.base.entity.Module', 'Module', 'it'),
	(3, 'UPDATE', '2017-02-23 15:38:15', 'MODULE_ID : UNIT', 'UNIT', 'class com.hk.base.entity.Module', 'Module', 'it'),
	(4, 'UPDATE', '2017-02-23 15:39:51', 'UBAH HAK AKSES USER_ID : it', 'it', 'class com.hk.base.entity.User', 'User', 'it'),
	(5, 'UPDATE', '2017-02-23 15:40:01', 'UBAH HAK AKSES USER_ID : it', 'it', 'class com.hk.base.entity.User', 'User', 'it'),
	(6, 'INSERT', '2017-02-23 15:40:23', 'Unit ID : ROL', 'ROL', 'class com.hk.base.entity.Unit', 'Unit', 'it'),
	(7, 'UPDATE', '2017-02-23 15:40:31', 'Unit ID : ROL', 'ROL', 'class com.hk.base.entity.Unit', 'Unit', 'it'),
	(8, 'INSERT', '2017-02-23 15:40:40', 'Mata Uang ID : RP', 'RP', 'class com.hk.base.entity.MataUang', 'MataUang', 'it'),
	(9, 'INSERT', '2017-02-23 15:42:25', 'USER_ID : 123', '123', 'class com.hk.base.entity.User', 'User', 'it'),
	(10, 'UPDATE', '2017-02-23 15:42:33', 'USER_ID : 123', '123', 'class com.hk.base.entity.User', 'User', 'it'),
	(11, 'UPDATE', '2017-02-23 15:42:36', 'USER_ID : 123', '123', 'class com.hk.base.entity.User', 'User', 'it');
/*!40000 ALTER TABLE `auditlog` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
