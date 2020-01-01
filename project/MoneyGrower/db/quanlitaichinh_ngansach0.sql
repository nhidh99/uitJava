-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlitaichinh
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
-- Table structure for table `ngansach`
--

DROP TABLE IF EXISTS `ngansach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ngansach` (
  `MaNganSach` int(11) NOT NULL AUTO_INCREMENT,
  `MaLoaiGiaoDich` int(11) NOT NULL,
  `MaNguoiDung` int(11) NOT NULL,
  `NgayBatDau` date NOT NULL,
  `NgayKetThuc` date NOT NULL,
  `GiaTri` decimal(15,0) NOT NULL,
  `SuDung` decimal(15,0) DEFAULT NULL,
  PRIMARY KEY (`MaNganSach`),
  KEY `fk_ns_lgd_idx` (`MaLoaiGiaoDich`),
  KEY `fk_ns_nd_idx` (`MaNguoiDung`),
  CONSTRAINT `fk_ns_lgd` FOREIGN KEY (`MaLoaiGiaoDich`) REFERENCES `loaigiaodich` (`MaLoaiGiaoDich`),
  CONSTRAINT `fk_ns_nd` FOREIGN KEY (`MaNguoiDung`) REFERENCES `nguoidung` (`MaNguoiDung`)
) ENGINE=InnoDB AUTO_INCREMENT=13028 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ngansach`
--

LOCK TABLES `ngansach` WRITE;
/*!40000 ALTER TABLE `ngansach` DISABLE KEYS */;
INSERT INTO `ngansach` VALUES (13021,11039,10001,'2019-11-11','2019-11-17',150000,15000),(13022,11001,10004,'2019-11-11','2019-11-17',45000,30000),(13025,11001,10001,'2019-11-11','2019-11-17',30000,30000);
/*!40000 ALTER TABLE `ngansach` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ngansach_BEFORE_INSERT` BEFORE INSERT ON `ngansach` FOR EACH ROW BEGIN
 SET NEW.SuDung = 
		(SELECT COALESCE(-SUM(GiaTri), 0) result
		FROM GiaoDich
        WHERE MaLoaiGiaoDich = NEW.MaLoaiGiaoDich
        AND MaNguoiDung = NEW.MaNguoiDung
        AND NgayGiaoDich >= NEW.NgayBatDau
        AND NgayGiaoDich <= NEW.NgayKetThuc);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ngansach_BEFORE_UPDATE` BEFORE UPDATE ON `ngansach` FOR EACH ROW BEGIN
 SET NEW.SuDung = 
		(SELECT COALESCE(-SUM(GiaTri), 0) result
		FROM GiaoDich
        WHERE MaLoaiGiaoDich = NEW.MaLoaiGiaoDich
        AND MaNguoiDung = NEW.MaNguoiDung
        AND NgayGiaoDich >= NEW.NgayBatDau
        AND NgayGiaoDich <= NEW.NgayKetThuc);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-01 23:22:14
