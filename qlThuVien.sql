-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: thuvien
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `docgia`
--

DROP TABLE IF EXISTS `docgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docgia` (
  `madocgia` int(11) NOT NULL AUTO_INCREMENT,
  `tendocgia` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gioitinh` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `namsinh` datetime DEFAULT NULL,
  `diachi` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sothe` int(11) DEFAULT NULL,
  PRIMARY KEY (`madocgia`),
  KEY `fk_the_docgia_idx` (`sothe`),
  CONSTRAINT `fk_the_docgia` FOREIGN KEY (`sothe`) REFERENCES `thedocgia` (`sothe`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docgia`
--

LOCK TABLES `docgia` WRITE;
/*!40000 ALTER TABLE `docgia` DISABLE KEYS */;
INSERT INTO `docgia` VALUES (1,'Trần Viết Tin','Nam','1999-05-22 00:00:00','BinhDinh',1751010160),(2,'Trần Nguyễn Ánh','Nữ ','1999-10-13 00:00:00','SaiGon',1751010161),(3,'Nguyến Mạnh Hậu','Nam','1999-07-03 00:00:00','BD',1751010162),(5,'Phạm Trí Quang','Nam','1999-07-10 00:00:00','Cà Mau',1751010164),(6,'Đỗ Đại Hải','Nam','1999-07-09 00:00:00','Bình Thuận',1751010165),(7,'Trịnh Minh Linh','Nữ','1999-05-20 00:00:00','Bình Định',1751010166),(8,'Nguyễn Khang Duy','Nam','1999-01-15 00:00:00','Sài Gòn',1751010168),(11,'Hồ Hoàng Hưng Thịnh','Nam','1999-01-08 00:00:00','Sài Gòn',1751010169),(12,'Nguyễn Hoàng Long','Nam','1999-04-16 00:00:00','BD',1751010170),(13,'Trần Huyền Trang','Nữ','1999-01-15 00:00:00','Sài Gòn',1751010171);
/*!40000 ALTER TABLE `docgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matsach`
--

DROP TABLE IF EXISTS `matsach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matsach` (
  `mamatsach` int(11) NOT NULL AUTO_INCREMENT,
  `maphieumuon` int(11) DEFAULT NULL,
  `masach` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `tensach` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `sothe` int(11) DEFAULT NULL,
  `ngayghinhan` datetime DEFAULT NULL,
  PRIMARY KEY (`mamatsach`),
  KEY `fk_muontra_matsach_idx` (`maphieumuon`),
  KEY `fk_thedocgia_matsach_idx` (`sothe`),
  CONSTRAINT `fk_matsach_mamuon` FOREIGN KEY (`maphieumuon`) REFERENCES `muontra` (`mamuon`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matsach`
--

LOCK TABLES `matsach` WRITE;
/*!40000 ALTER TABLE `matsach` DISABLE KEYS */;
INSERT INTO `matsach` VALUES (3,52,'TL01','Tuổi Trẻ Đáng Giá Bao Nhiêu',1751010160,'2020-01-02 00:00:00'),(4,64,'TN01','Dế Mèn Phiêu Lưu Ký',1751010164,'2020-01-02 00:00:00'),(5,54,'TN01','Dế Mèn Phiêu Lưu Ký',1751010160,'2020-01-02 00:00:00'),(6,120,'VH03','Nhà Giả Kim',1751010171,'2020-01-04 00:00:00');
/*!40000 ALTER TABLE `matsach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muontra`
--

DROP TABLE IF EXISTS `muontra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muontra` (
  `mamuon` int(11) NOT NULL AUTO_INCREMENT,
  `sothemuon` int(11) NOT NULL,
  `masachmuon` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tensachmuon` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngaymuon` datetime DEFAULT NULL,
  `ngaytra` datetime DEFAULT NULL,
  `ngayhethan` datetime DEFAULT NULL,
  `tienphat` int(11) DEFAULT NULL,
  `ghichu` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `tennhanvien` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`mamuon`,`sothemuon`,`masachmuon`),
  KEY `fk_muon_the_idx` (`sothemuon`),
  KEY `fk_muon_sach` (`masachmuon`),
  KEY `fk_muon_nhanvien` (`tennhanvien`),
  CONSTRAINT `fk_muon_nhanvien` FOREIGN KEY (`tennhanvien`) REFERENCES `nhanvien` (`hoten`),
  CONSTRAINT `fk_muon_sach` FOREIGN KEY (`masachmuon`) REFERENCES `sach` (`masach`),
  CONSTRAINT `fk_muon_the` FOREIGN KEY (`sothemuon`) REFERENCES `thedocgia` (`sothe`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muontra`
--

LOCK TABLES `muontra` WRITE;
/*!40000 ALTER TABLE `muontra` DISABLE KEYS */;
INSERT INTO `muontra` VALUES (51,1751010160,'GT01','Lập Trình Java','2020-01-02 00:00:00','2020-01-04 00:00:00','2020-01-31 00:00:00',0,'Đã trả','Trần Viết Tin'),(52,1751010160,'TL01','Tuổi Trẻ Đáng Giá Bao Nhiêu','2020-01-02 00:00:00',NULL,'2020-01-31 00:00:00',0,'Đã mất','Trần Viết Tin'),(53,1751010160,'TL02','Đắc Nhân Tâm','2020-01-02 00:00:00','2020-01-03 00:00:00','2020-01-31 00:00:00',0,'Đã trả','Trần Viết Tin'),(54,1751010160,'TN01','Dế Mèn Phiêu Lưu Ký','2020-02-05 00:00:00',NULL,'2020-02-29 00:00:00',0,'Đã mất','Trần Viết Tin'),(55,1751010160,'TT001','Cà Phê Cùng Tony','2020-02-05 00:00:00','2020-01-03 00:00:00','2020-02-29 00:00:00',0,'Đã trả','Trần Viết Tin'),(56,1751010161,'TT01','Thám Tử Lừng Danh Conan','2020-02-05 00:00:00','2020-01-02 00:00:00','2020-02-29 00:00:00',0,'Đã trả','Trần Viết Tin'),(57,1751010161,'TT02','Doraemon','2020-02-05 00:00:00','2020-01-04 00:00:00','2020-02-29 00:00:00',0,'Đã trả','Trần Viết Tin'),(58,1751010161,'VH01','Làm Bạn Với Bầu Trời','2020-02-05 00:00:00','2020-01-02 00:00:00','2020-02-29 00:00:00',0,'Đã trả','Trần Viết Tin'),(59,1751010161,'VH02','Mắt Biếc','2020-03-01 00:00:00','2020-01-04 00:00:00','2020-03-31 00:00:00',0,'Đã trả','Trần Viết Tin'),(60,1751010161,'VH03','Nhà Giả Kim','2020-03-01 00:00:00','2020-01-02 00:00:00','2020-03-31 00:00:00',0,'Đã trả','Trần Viết Tin'),(61,1751010162,'GT01','Lập Trình Java','2020-03-01 00:00:00',NULL,'2020-03-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(62,1751010162,'TN01','Dế Mèn Phiêu Lưu Ký','2020-03-01 00:00:00','2020-01-02 00:00:00','2020-03-31 00:00:00',0,'Đã trả','Trần Viết Tin'),(63,1751010162,'VH01','Làm Bạn Với Bầu Trời','2020-03-01 00:00:00',NULL,'2020-03-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(64,1751010164,'TN01','Dế Mèn Phiêu Lưu Ký','2020-04-01 00:00:00',NULL,'2020-04-30 00:00:00',0,'Đã mất','Trần Viết Tin'),(65,1751010164,'TT02','Doraemon','2020-04-01 00:00:00',NULL,'2020-04-30 00:00:00',0,'Chưa trả','Trần Viết Tin'),(66,1751010164,'VH03','Nhà Giả Kim','2020-04-01 00:00:00','2020-01-02 00:00:00','2020-04-30 00:00:00',0,'Đã trả','Trần Viết Tin'),(67,1751010164,'TL01','Tuổi Trẻ Đáng Giá Bao Nhiêu','2020-04-01 00:00:00',NULL,'2020-04-30 00:00:00',0,'Chưa trả','Trần Viết Tin'),(68,1751010161,'TT01','Thám Tử Lừng Danh Conan','2020-01-01 00:00:00',NULL,'2020-01-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(69,1751010161,'TT01','Thám Tử Lừng Danh Conan','2020-01-01 00:00:00',NULL,'2020-01-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(70,1751010161,'TT01','Thám Tử Lừng Danh Conan','2020-01-01 00:00:00',NULL,'2020-01-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(71,1751010166,'VH01','Làm Bạn Với Bầu Trời','2020-01-04 00:00:00',NULL,'2020-01-30 00:00:00',0,'Chưa trả','Trần Viết Tin'),(72,1751010166,'VH01','Làm Bạn Với Bầu Trời','2020-01-29 00:00:00',NULL,'2020-02-20 00:00:00',0,'Chưa trả','Trần Viết Tin'),(73,1751010166,'TN01','Dế Mèn Phiêu Lưu Ký','2020-01-29 00:00:00',NULL,'2020-01-24 00:00:00',0,'Chưa trả','Trần Viết Tin'),(74,1751010166,'VH03','Nhà Giả Kim','2020-01-31 00:00:00',NULL,'2020-01-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(75,1751010164,'TT02','Doraemon','2020-01-29 00:00:00',NULL,'2020-02-27 00:00:00',0,'Chưa trả','Trần Viết Tin'),(76,1751010165,'TT02','Doraemon','2020-01-18 00:00:00',NULL,'2020-01-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(77,1751010165,'TT02','Doraemon','2020-01-18 00:00:00','2020-01-04 00:00:00','2020-01-31 00:00:00',0,'Đã trả','Trần Viết Tin'),(78,1751010167,'TN01','Dế Mèn Phiêu Lưu Ký','2020-01-01 00:00:00','2020-01-04 00:00:00','2020-01-31 00:00:00',0,'Đã trả','Trần Viết Tin'),(79,1751010162,'TT01','Thám Tử Lừng Danh Conan','2019-01-01 00:00:00',NULL,'2019-01-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(80,1751010162,'TL02','Đắc Nhân Tâm','2019-01-23 00:00:00','2020-01-04 00:00:00','2019-02-27 00:00:00',60000,'Đã trả','Trần Viết Tin'),(81,1751010162,'TT02','Doraemon','2019-01-23 00:00:00',NULL,'2019-02-21 00:00:00',0,'Chưa trả','Trần Viết Tin'),(82,1751010164,'VH01','Làm Bạn Với Bầu Trời','2019-02-02 00:00:00','2020-01-04 00:00:00','2019-02-21 00:00:00',90000,'Đã trả','Trần Viết Tin'),(83,1751010165,'TT001','Cà Phê Cùng Tony','2019-02-02 00:00:00',NULL,'2019-02-21 00:00:00',0,'Chưa trả','Trần Viết Tin'),(84,1751010165,'VH01','Làm Bạn Với Bầu Trời','2019-02-02 00:00:00','2020-01-04 00:00:00','2019-02-21 00:00:00',90000,'Đã trả','Trần Viết Tin'),(85,1751010165,'VH03','Nhà Giả Kim','2019-02-02 00:00:00',NULL,'2019-02-21 00:00:00',0,'Chưa trả','Trần Viết Tin'),(86,1751010166,'VH03','Nhà Giả Kim','2019-03-14 00:00:00','2020-01-04 00:00:00','2019-03-31 00:00:00',-95000,'Đã trả','Trần Viết Tin'),(87,1751010168,'TN01','Dế Mèn Phiêu Lưu Ký','2019-03-14 00:00:00','2020-01-04 00:00:00','2019-03-31 00:00:00',-95000,'Đã trả','Trần Viết Tin'),(88,1751010168,'TL02','Đắc Nhân Tâm','2019-04-12 00:00:00','2020-01-04 00:00:00','2019-07-25 00:00:00',65000,'Đã trả','Trần Viết Tin'),(89,1751010168,'TT01','Thám Tử Lừng Danh Conan','2019-04-12 00:00:00','2020-01-04 00:00:00','2019-07-25 00:00:00',65000,'Đã trả','Trần Viết Tin'),(90,1751010168,'VH01','Làm Bạn Với Bầu Trời','2019-04-12 00:00:00','2020-01-04 00:00:00','2019-07-25 00:00:00',815000,'Đã trả','Trần Viết Tin'),(91,1751010166,'VH03','Nhà Giả Kim','2019-10-14 00:00:00',NULL,'2019-12-14 00:00:00',0,'Chưa trả','Trần Viết Tin'),(92,1751010165,'VH03','Nhà Giả Kim','2019-10-03 00:00:00','2020-01-04 00:00:00','2019-11-03 00:00:00',60000,'Đã trả','Trần Viết Tin'),(93,1751010162,'TL02','Đắc Nhân Tâm','2019-10-04 00:00:00','2020-01-04 00:00:00','2019-11-04 00:00:00',55000,'Đã trả','Trần Viết Tin'),(94,1751010165,'TT001','Cà Phê Cùng Tony','2019-10-04 00:00:00','2020-01-04 00:00:00','2019-11-04 00:00:00',55000,'Đã trả','Trần Viết Tin'),(95,1751010165,'TL02','Đắc Nhân Tâm','2019-10-04 00:00:00','2020-01-04 00:00:00','2019-11-04 00:00:00',305000,'Đã trả','Trần Viết Tin'),(96,1751010162,'VH01','Làm Bạn Với Bầu Trời','2019-05-16 00:00:00','2020-01-04 00:00:00','2019-06-19 00:00:00',995000,'Đã trả','Trần Viết Tin'),(97,1751010161,'TL02','Đắc Nhân Tâm','2019-05-16 00:00:00',NULL,'2019-06-19 00:00:00',0,'Chưa trả','Trần Viết Tin'),(98,1751010164,'VH03','Nhà Giả Kim','2019-06-06 00:00:00','2020-01-04 00:00:00','2019-07-24 00:00:00',820000,'Đã trả','Trần Viết Tin'),(99,1751010165,'TL01','Tuổi Trẻ Đáng Giá Bao Nhiêu','2019-06-06 00:00:00',NULL,'2019-07-24 00:00:00',0,'Chưa trả','Trần Viết Tin'),(100,1751010168,'VH02','Mắt Biếc','2019-06-06 00:00:00',NULL,'2019-07-24 00:00:00',0,'Chưa trả','Trần Viết Tin'),(101,1751010168,'VH02','Mắt Biếc','2019-07-04 00:00:00','2020-01-04 00:00:00','2019-08-22 00:00:00',675000,'Đã trả','Trần Viết Tin'),(102,1751010168,'TL02','Đắc Nhân Tâm','2019-07-04 00:00:00',NULL,'2019-08-22 00:00:00',0,'Chưa trả','Trần Viết Tin'),(103,1751010168,'TT01','Thám Tử Lừng Danh Conan','2019-07-04 00:00:00','2020-01-04 00:00:00','2019-08-22 00:00:00',675000,'Đã trả','Trần Viết Tin'),(104,1751010168,'TT01','Thám Tử Lừng Danh Conan','2019-08-16 00:00:00','2020-01-04 00:00:00','2019-09-20 00:00:00',530000,'Đã trả','Trần Viết Tin'),(105,1751010168,'VH01','Làm Bạn Với Bầu Trời','2019-08-16 00:00:00',NULL,'2019-09-20 00:00:00',0,'Chưa trả','Trần Viết Tin'),(106,1751010168,'TL01','Tuổi Trẻ Đáng Giá Bao Nhiêu','2019-08-16 00:00:00',NULL,'2019-09-20 00:00:00',0,'Chưa trả','Trần Viết Tin'),(107,1751010168,'TT01','Thám Tử Lừng Danh Conan','2019-08-16 00:00:00',NULL,'2019-09-20 00:00:00',0,'Chưa trả','Trần Viết Tin'),(108,1751010162,'VH03','Nhà Giả Kim','2019-09-12 00:00:00',NULL,'2019-10-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(109,1751010164,'TL02','Đắc Nhân Tâm','2019-09-12 00:00:00',NULL,'2019-10-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(110,1751010170,'TT01','Thám Tử Lừng Danh Conan','2019-10-11 00:00:00',NULL,'2020-10-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(111,1751010170,'VH01','Làm Bạn Với Bầu Trời','2019-10-11 00:00:00',NULL,'2020-10-31 00:00:00',0,'Chưa trả','Trần Viết Tin'),(112,1751010170,'TT01','Thám Tử Lừng Danh Conan','2019-10-11 00:00:00','2020-01-04 00:00:00','2020-10-31 00:00:00',0,'Đã trả','Trần Viết Tin'),(113,1751010170,'VH02','Mắt Biếc','2019-10-11 00:00:00',NULL,'2020-11-25 00:00:00',0,'Chưa trả','Trần Viết Tin'),(114,1751010170,'GT01','Lập Trình Java','2019-10-11 00:00:00',NULL,'2020-11-25 00:00:00',0,'Chưa trả','Trần Viết Tin'),(115,1751010171,'GT01','Lập Trình Java','2019-11-14 00:00:00',NULL,'2020-12-18 00:00:00',0,'Chưa trả','Trần Viết Tin'),(116,1751010171,'VH02','Mắt Biếc','2019-11-14 00:00:00','2020-01-04 00:00:00','2020-12-18 00:00:00',0,'Đã trả','Trần Viết Tin'),(117,1751010171,'TT01','Thám Tử Lừng Danh Conan','2019-11-14 00:00:00',NULL,'2020-12-18 00:00:00',0,'Chưa trả','Trần Viết Tin'),(118,1751010171,'TT001','Cà Phê Cùng Tony','2019-11-14 00:00:00','2020-01-04 00:00:00','2020-12-18 00:00:00',0,'Đã trả','Trần Viết Tin'),(119,1751010171,'TT001','Cà Phê Cùng Tony','2019-12-19 00:00:00',NULL,'2020-01-17 00:00:00',0,'Chưa trả','Trần Viết Tin'),(120,1751010171,'VH03','Nhà Giả Kim','2019-12-19 00:00:00',NULL,'2020-01-17 00:00:00',0,'Đã mất','Trần Viết Tin'),(121,1751010171,'VH02','Mắt Biếc','2019-12-19 00:00:00','2020-01-04 00:00:00','2020-01-17 00:00:00',0,'Đã trả','Trần Viết Tin'),(122,1751010165,'TT01','Thám Tử Lừng Danh Conan','2020-01-04 00:00:00','2020-01-04 00:00:00','2020-02-04 00:00:00',0,'Đã trả','Trần Viết Tin'),(123,1751010165,'VH01','Làm Bạn Với Bầu Trời','2020-01-04 00:00:00',NULL,'2020-02-04 00:00:00',0,'Chưa trả','Trần Viết Tin'),(124,1751010171,'TT01','Thám Tử Lừng Danh Conan','2020-01-04 00:00:00',NULL,'2020-02-03 00:00:00',0,'Chưa trả','Trần Viết Tin'),(125,1751010170,'TT02','Doraemon','2020-01-04 00:00:00',NULL,'2020-02-03 00:00:00',0,'Chưa trả','Trần Viết Tin'),(126,1751010160,'TT01','Thám Tử Lừng Danh Conan','2020-01-04 00:00:00',NULL,'2020-02-03 00:00:00',0,'Chưa trả','Trần Viết Tin'),(127,1751010160,'TT01','Thám Tử Lừng Danh Conan','2020-01-04 00:00:00',NULL,'2020-02-03 00:00:00',0,'Chưa trả','Trần Viết Tin'),(128,1751010160,'TT01','Thám Tử Lừng Danh Conan','2020-01-04 00:00:00',NULL,'2020-02-03 00:00:00',0,'Chưa trả','Trần Viết Tin'),(129,1751010161,'TT01','Thám Tử Lừng Danh Conan','2020-01-04 00:00:00',NULL,'2020-02-03 00:00:00',0,'Chưa trả','Trần Viết Tin'),(130,1751010169,'TL01','Tuổi Trẻ Đáng Giá Bao Nhiêu','2020-01-04 00:00:00',NULL,'2020-02-03 00:00:00',0,'Chưa trả','Nguyễn Mạnh Hậu');
/*!40000 ALTER TABLE `muontra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `manhanvien` int(11) NOT NULL AUTO_INCREMENT,
  `hoten` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gioitinh` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `namsinh` datetime NOT NULL,
  `diachi` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tentk` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`manhanvien`),
  UNIQUE KEY `hoten_UNIQUE` (`hoten`),
  KEY `fk_nv_tk_idx` (`tentk`),
  CONSTRAINT `fk_nv_tk` FOREIGN KEY (`tentk`) REFERENCES `taikhoan` (`tentk`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Trần Viết Tin','Nam','1999-05-21 00:00:00','BD','admin'),(2,'Nguyễn Mạnh Hậu','Nam','1999-05-20 00:00:00','SG','nhanvien');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sach` (
  `masach` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tensach` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tacgia` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nhaxuatban` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `theloai` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `soluong` int(11) DEFAULT NULL,
  `img` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`masach`),
  UNIQUE KEY `tensach_UNIQUE` (`tensach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES ('GT01','Lập Trình Java','Dương Hữu Thành','Thông Tin và Truyền Thông','Giáo Trình',200,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\GT001.jpg'),('TL01','Tuổi Trẻ Đáng Giá Bao Nhiêu','Rosie Nguyễn','Kim Đồng','Tâm Lý',200,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\tuoitredanggiabaonhieu.jpg'),('TL02','Đắc Nhân Tâm','Dale Carnegie','Dân Trí','Tâm Lý',200,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\dacnhantam.jpg'),('TN01','Dế Mèn Phiêu Lưu Ký','Tô Hoài','Dân Trí','Truyện Ngắn',200,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\LT01.jpg'),('TT001','Cà Phê Cùng Tony','Tony ','Xuất Bản Trẻ','Tiểu Thuyết',200,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\cafecungtony.jpg'),('TT01','Thám Tử Lừng Danh Conan','GoSho Aoyama','Kim Đồng','Truyện Tranh',500,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\conan.jpg'),('TT02','Doraemon','Fujiko','Kim Đồng','Truyện Tranh',2000,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\doraemon.jpg'),('VH01','Làm Bạn Với Bầu Trời','Nguyễn Nhật Ánh','Xuất Bản Trẻ','Văn Học ',200,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\lambanvoibautroi.jpg'),('VH02','Mắt Biếc','Nguyễn Nhật Ánh','Xuất Bản Trẻ','Văn Học ',500,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\matbiec.jpg'),('VH03','Nhà Giả Kim','Paulo Coelho','Văn Học','Văn Học ',200,'C:\\Users\\anhtr\\Desktop\\1x\\ThuVien\\src\\AnhSach\\nhagiakim.jpg');
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `tentk` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `matkhau` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `quyen` bit(1) NOT NULL,
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tentk`),
  UNIQUE KEY `tentk_UNIQUE` (`tentk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('admin','admin',_binary '','21232f297a57a5a743894a0e4a801fc3'),('nhanvien','nhanvien',_binary '\0','2a2fa4fe2fa737f129ef2d127b861b7e'),('nhanvien2','123456',_binary '\0','e10adc3949ba59abbe56e057f20f883e'),('nhanvien3','2434343',_binary '\0','e2faf4b9ecc7459aa6b36110069e8d0c');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thedocgia`
--

DROP TABLE IF EXISTS `thedocgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thedocgia` (
  `sothe` int(11) NOT NULL,
  `ngayketthuc` datetime NOT NULL,
  PRIMARY KEY (`sothe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thedocgia`
--

LOCK TABLES `thedocgia` WRITE;
/*!40000 ALTER TABLE `thedocgia` DISABLE KEYS */;
INSERT INTO `thedocgia` VALUES (1751010160,'2020-02-02 00:00:00'),(1751010161,'2020-02-03 00:00:00'),(1751010162,'2020-02-01 00:00:00'),(1751010163,'2019-12-31 00:00:00'),(1751010164,'2020-01-22 00:00:00'),(1751010165,'2020-02-22 00:00:00'),(1751010166,'2020-03-25 00:00:00'),(1751010167,'2020-01-01 00:00:00'),(1751010168,'2020-03-31 00:00:00'),(1751010169,'2020-03-26 00:00:00'),(1751010170,'2020-03-26 00:00:00'),(1751010171,'2020-03-19 00:00:00');
/*!40000 ALTER TABLE `thedocgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theloaisach`
--

DROP TABLE IF EXISTS `theloaisach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theloaisach` (
  `matheloai` int(11) NOT NULL AUTO_INCREMENT,
  `tentheloai` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`matheloai`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theloaisach`
--

LOCK TABLES `theloaisach` WRITE;
/*!40000 ALTER TABLE `theloaisach` DISABLE KEYS */;
INSERT INTO `theloaisach` VALUES (1,'Giáo Trình'),(2,'Truyện Ngắn'),(3,'Truyện Tranh'),(4,'Tiểu Thuyết'),(5,'Sách Giáo Khoa'),(6,'Thơ'),(7,'Cuộc Sống'),(8,'Kinh Tế'),(9,'Tâm Lý'),(10,'Thường Thức'),(11,'Văn Học '),(12,'Toán Học'),(13,'Khoa Học - Viễn Tưởng'),(14,'Ẩm thực'),(15,'Giao Thông'),(16,'Tình Cảm'),(17,'Truyện Hài');
/*!40000 ALTER TABLE `theloaisach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongke`
--

DROP TABLE IF EXISTS `thongke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongke` (
  `mathongke` int(11) NOT NULL AUTO_INCREMENT,
  `thang` int(11) DEFAULT NULL,
  `nam` int(11) DEFAULT NULL,
  `tongthang` int(11) DEFAULT NULL,
  `tongquy` int(11) DEFAULT NULL,
  `tongnam` int(11) DEFAULT NULL,
  PRIMARY KEY (`mathongke`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongke`
--

LOCK TABLES `thongke` WRITE;
/*!40000 ALTER TABLE `thongke` DISABLE KEYS */;
INSERT INTO `thongke` VALUES (45,1,2019,3,9,43),(46,2,2019,4,9,43),(47,3,2019,2,9,43),(48,4,2019,3,8,43),(49,5,2019,2,8,43),(50,6,2019,3,8,43),(51,7,2019,3,9,43),(52,8,2019,4,9,43),(53,9,2019,2,9,43),(54,10,2019,10,17,43),(55,11,2019,4,17,43),(56,1,2020,23,33,37);
/*!40000 ALTER TABLE `thongke` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-04  4:19:34
