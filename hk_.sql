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


-- Dumping database structure for hk_
CREATE DATABASE IF NOT EXISTS `hk_` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hk_`;

-- Dumping structure for table hk_.exception_event
CREATE TABLE IF NOT EXISTS `exception_event` (
  `Exception_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` varchar(8) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `DATE_NON_ACTIVE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(8) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  `DATE_TIME` datetime DEFAULT NULL,
  `EXCEPTION_TYPE` varchar(255) DEFAULT NULL,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `MESSAGE` text,
  `STACKTRACE` text,
  PRIMARY KEY (`Exception_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk_.exception_event: ~-1 rows (approximately)
DELETE FROM `exception_event`;
/*!40000 ALTER TABLE `exception_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `exception_event` ENABLE KEYS */;

-- Dumping structure for table hk_.m_access_user
CREATE TABLE IF NOT EXISTS `m_access_user` (
  `USER_ID` varchar(255) NOT NULL,
  `MODULE_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`MODULE_ID`,`USER_ID`),
  KEY `FKDCE384D440D1CC86` (`MODULE_ID`),
  KEY `FKDCE384D4CF2EFF26` (`USER_ID`),
  CONSTRAINT `FKDCE384D440D1CC86` FOREIGN KEY (`MODULE_ID`) REFERENCES `m_module` (`MODULE_ID`),
  CONSTRAINT `FKDCE384D4CF2EFF26` FOREIGN KEY (`USER_ID`) REFERENCES `m_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk_.m_access_user: ~-1 rows (approximately)
DELETE FROM `m_access_user`;
/*!40000 ALTER TABLE `m_access_user` DISABLE KEYS */;
INSERT INTO `m_access_user` (`USER_ID`, `MODULE_ID`) VALUES
	('it', '1'),
	('it', '11'),
	('it', '1121'),
	('it', '37'),
	('it', '5'),
	('it', '6'),
	('it', 'MATAUANG'),
	('it', 'UNIT');
/*!40000 ALTER TABLE `m_access_user` ENABLE KEYS */;

-- Dumping structure for table hk_.m_mata_uang
CREATE TABLE IF NOT EXISTS `m_mata_uang` (
  `MATA_UANG_ID` varchar(255) NOT NULL,
  `CREATE_BY` varchar(8) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `DATE_NON_ACTIVE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(8) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`MATA_UANG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk_.m_mata_uang: ~-1 rows (approximately)
DELETE FROM `m_mata_uang`;
/*!40000 ALTER TABLE `m_mata_uang` DISABLE KEYS */;
INSERT INTO `m_mata_uang` (`MATA_UANG_ID`, `CREATE_BY`, `CREATE_DATE`, `DATE_NON_ACTIVE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `VERSION`, `IS_ACTIVE`) VALUES
	('RP', 'it', '2017-02-23 15:40:40', NULL, NULL, NULL, 0, b'1');
/*!40000 ALTER TABLE `m_mata_uang` ENABLE KEYS */;

-- Dumping structure for table hk_.m_module
CREATE TABLE IF NOT EXISTS `m_module` (
  `MODULE_ID` varchar(255) NOT NULL,
  `CREATE_BY` varchar(8) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `DATE_NON_ACTIVE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(8) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  `MODULE_FK` varchar(255) DEFAULT NULL,
  `NAMA` varchar(50) DEFAULT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `STATUS` varchar(5) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `URUTAN` int(11) DEFAULT NULL,
  PRIMARY KEY (`MODULE_ID`),
  KEY `FK6809169E40D1CC30` (`MODULE_FK`),
  CONSTRAINT `FK6809169E40D1CC30` FOREIGN KEY (`MODULE_FK`) REFERENCES `m_module` (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk_.m_module: ~-1 rows (approximately)
DELETE FROM `m_module`;
/*!40000 ALTER TABLE `m_module` DISABLE KEYS */;
INSERT INTO `m_module` (`MODULE_ID`, `CREATE_BY`, `CREATE_DATE`, `DATE_NON_ACTIVE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `VERSION`, `IS_ACTIVE`, `MODULE_FK`, `NAMA`, `PATH`, `STATUS`, `URL`, `URUTAN`) VALUES
	('1', NULL, '2014-04-10 10:18:31', NULL, 'admin', '2014-09-25 10:52:58', 2, b'1', '37', 'Kode User', '/master/user', '1', '/user_search.html', 1),
	('11', NULL, NULL, NULL, 'admin', '2014-04-30 16:09:21', 8, b'1', NULL, 'Administrator', '', '0', '', 0),
	('1121', 'admin', '2014-05-06 13:49:58', NULL, 'jim', '2016-09-10 13:01:24', 4, b'1', '11', 'Menu', '/', '0', 'index.html', 4),
	('123', 'admin', '2014-05-06 13:46:54', NULL, 'admin', '2014-09-25 10:04:40', 1, b'1', '11', 'Sistem', '', '0', '', 1),
	('37', NULL, '2014-04-10 10:18:47', NULL, 'admin', '2014-09-25 17:17:41', 4, b'1', '11', 'User', '', '0', '', 2),
	('5', NULL, '2014-04-10 10:18:35', NULL, 'jim', '2016-09-10 13:01:42', 8, b'1', '1121', 'Daftar Menu/Module', '/master/module', '1', '/module_search.html', 1),
	('6', NULL, '2014-04-10 10:18:36', NULL, 'jim', '2016-09-10 13:04:19', 4, b'1', '1121', 'Hak Akses Menu', '/master/accessuser', '1', '/accessuser_search.html', 2),
	('DASHBOARD', 'jim', '2016-09-10 12:22:06', NULL, NULL, NULL, 0, b'1', '11', 'Dashboard', '/', '0', 'index.html', 3),
	('MATAUANG', 'it', '2017-02-23 15:37:40', NULL, NULL, NULL, 0, b'1', '1121', 'Mata Uang', '/master/mataUang', '1', '/mata_uang_search.html', NULL),
	('UNIT', 'it', '2017-02-23 15:38:04', NULL, 'it', '2017-02-23 15:38:15', 1, b'1', '1121', 'Unit', '/master/unit', '1', '/unit_search.html', NULL);
/*!40000 ALTER TABLE `m_module` ENABLE KEYS */;

-- Dumping structure for table hk_.m_unit
CREATE TABLE IF NOT EXISTS `m_unit` (
  `UNIT_ID` varchar(255) NOT NULL,
  `CREATE_BY` varchar(8) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `DATE_NON_ACTIVE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(8) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  `NAMA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UNIT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk_.m_unit: ~-1 rows (approximately)
DELETE FROM `m_unit`;
/*!40000 ALTER TABLE `m_unit` DISABLE KEYS */;
INSERT INTO `m_unit` (`UNIT_ID`, `CREATE_BY`, `CREATE_DATE`, `DATE_NON_ACTIVE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `VERSION`, `IS_ACTIVE`, `NAMA`) VALUES
	('ROL', 'it', '2017-02-23 15:40:23', NULL, 'it', '2017-02-23 15:40:31', 1, b'1', 'Rols');
/*!40000 ALTER TABLE `m_unit` ENABLE KEYS */;

-- Dumping structure for table hk_.m_user
CREATE TABLE IF NOT EXISTS `m_user` (
  `USER_ID` varchar(255) NOT NULL,
  `BARCODE` varchar(255) DEFAULT NULL,
  `CREATE_BY` varchar(8) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `DATE_NON_ACTIVE` datetime DEFAULT NULL,
  `IS_ACCOUNT_NON_EXPIRED` char(1) DEFAULT NULL,
  `IS_ACCOUNT_NON_LOCKED` char(1) DEFAULT NULL,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  `IS_CANCEL` bit(1) DEFAULT NULL,
  `IS_CONFIRM` bit(1) DEFAULT NULL,
  `IS_CREATE` bit(1) DEFAULT NULL,
  `IS_CREDENTIALS_NON_EXPIRED` char(1) DEFAULT NULL,
  `IS_DELETE` bit(1) DEFAULT NULL,
  `IS_ENABLED` char(1) DEFAULT NULL,
  `IS_PRINT` bit(1) DEFAULT NULL,
  `IS_REPORT` bit(1) DEFAULT NULL,
  `IS_REPRINT` bit(1) DEFAULT NULL,
  `IS_SUPERUSER` bit(1) DEFAULT NULL,
  `IS_SUPERVISOR` bit(1) DEFAULT NULL,
  `IS_UNCONFIRM` bit(1) DEFAULT NULL,
  `IS_UPDATE` bit(1) DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(8) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `MRU_LIMIT` int(11) DEFAULT NULL,
  `NAMA` varchar(50) DEFAULT NULL,
  `NIP` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PASSWORD_OTORISASI` varchar(255) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk_.m_user: ~-1 rows (approximately)
DELETE FROM `m_user`;
/*!40000 ALTER TABLE `m_user` DISABLE KEYS */;
INSERT INTO `m_user` (`USER_ID`, `BARCODE`, `CREATE_BY`, `CREATE_DATE`, `DATE_NON_ACTIVE`, `IS_ACCOUNT_NON_EXPIRED`, `IS_ACCOUNT_NON_LOCKED`, `IS_ACTIVE`, `IS_CANCEL`, `IS_CONFIRM`, `IS_CREATE`, `IS_CREDENTIALS_NON_EXPIRED`, `IS_DELETE`, `IS_ENABLED`, `IS_PRINT`, `IS_REPORT`, `IS_REPRINT`, `IS_SUPERUSER`, `IS_SUPERVISOR`, `IS_UNCONFIRM`, `IS_UPDATE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `MRU_LIMIT`, `NAMA`, `NIP`, `PASSWORD`, `PASSWORD_OTORISASI`, `VERSION`) VALUES
	('123', NULL, 'it', '2017-02-23 15:42:25', NULL, NULL, NULL, b'1', b'0', b'0', b'1', NULL, b'0', NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 'it', '2017-02-23 15:42:36', 123, '123x', NULL, '202cb962ac59075b964b07152d234b70', NULL, 2),
	('it', NULL, 'jim', '2015-01-19 09:37:19', NULL, NULL, NULL, b'1', b'1', b'1', b'1', NULL, b'1', NULL, b'1', b'1', b'1', b'1', b'1', b'1', b'1', 'it', '2016-11-16 12:17:05', 10, 'IT', NULL, '1c95a99099cc145355004b42d349cc42', NULL, 100);
/*!40000 ALTER TABLE `m_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
