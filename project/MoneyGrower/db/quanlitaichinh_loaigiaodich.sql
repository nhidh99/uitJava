-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlitaichinh
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
-- Table structure for table `loaigiaodich`
--

DROP TABLE IF EXISTS `loaigiaodich`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaigiaodich` (
  `MaLoaiGiaoDich` int(11) NOT NULL AUTO_INCREMENT,
  `TenLoaiGiaoDich` varchar(45) NOT NULL,
  `GiaoDichThu` tinyint(1) NOT NULL,
  `BieuTuong` varchar(5) NOT NULL,
  PRIMARY KEY (`MaLoaiGiaoDich`)
) ENGINE=InnoDB AUTO_INCREMENT=11061 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaigiaodich`
--

LOCK TABLES `loaigiaodich` WRITE;
/*!40000 ALTER TABLE `loaigiaodich` DISABLE KEYS */;
INSERT INTO `loaigiaodich` VALUES (11001,'Bạn bè',0,'?'),(11039,'Ăn uống',0,'?'),(11040,'Chi phí',0,'?'),(11041,'Di chuyển',0,'?'),(11042,'Du lịch',0,'✈'),(11043,'Gia đình',0,'?'),(11044,'Giáo dục',0,'?'),(11045,'Hoá đơn',0,'?'),(11046,'Mua sắm',0,'?'),(11047,'Kinh doanh',0,'?'),(11048,'Quà tặng',0,'?'),(11049,'Sức khoẻ',0,'?'),(11050,'Bảo hiểm',0,'?'),(11052,'Cho vay',0,'?'),(11053,'Bán đồ',1,'?'),(11054,'Lương',1,'?'),(11055,'Thưởng',1,'?'),(11056,'Tiền lãi',1,'?'),(11057,'Được tặng',1,'?'),(11058,'Thu nợ',1,'?'),(11059,'Đi vay',1,'?'),(11060,'Trả nợ',0,'?');
/*!40000 ALTER TABLE `loaigiaodich` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-11 13:46:36
