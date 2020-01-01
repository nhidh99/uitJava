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
-- Temporary view structure for view `view_thongkethang`
--

DROP TABLE IF EXISTS `view_thongkethang`;
/*!50001 DROP VIEW IF EXISTS `view_thongkethang`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_thongkethang` AS SELECT 
 1 AS `MaNguoiDung`,
 1 AS `TenLoaiGiaoDich`,
 1 AS `TongGiaTri`,
 1 AS `Thang`,
 1 AS `Nam`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_giaodichthang`
--

DROP TABLE IF EXISTS `view_giaodichthang`;
/*!50001 DROP VIEW IF EXISTS `view_giaodichthang`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_giaodichthang` AS SELECT 
 1 AS `MaNguoiDung`,
 1 AS `NgayGiaoDich`,
 1 AS `TongGiaTri`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `view_thongkethang`
--

/*!50001 DROP VIEW IF EXISTS `view_thongkethang`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_thongkethang` AS select `gd`.`MaNguoiDung` AS `MaNguoiDung`,`lgd`.`TenLoaiGiaoDich` AS `TenLoaiGiaoDich`,sum(`gd`.`GiaTri`) AS `TongGiaTri`,month(`gd`.`NgayGiaoDich`) AS `Thang`,year(`gd`.`NgayGiaoDich`) AS `Nam` from (`giaodich` `gd` join `loaigiaodich` `lgd` on((`gd`.`MaLoaiGiaoDich` = `lgd`.`MaLoaiGiaoDich`))) group by `gd`.`MaNguoiDung`,`lgd`.`TenLoaiGiaoDich`,`Thang`,`Nam` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_giaodichthang`
--

/*!50001 DROP VIEW IF EXISTS `view_giaodichthang`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_giaodichthang` AS select `giaodich`.`MaNguoiDung` AS `MaNguoiDung`,`giaodich`.`NgayGiaoDich` AS `NgayGiaoDich`,sum(`giaodich`.`GiaTri`) AS `TongGiaTri` from `giaodich` group by `giaodich`.`MaNguoiDung`,dayofmonth(`giaodich`.`NgayGiaoDich`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping events for database 'quanlitaichinh'
--

--
-- Dumping routines for database 'quanlitaichinh'
--
/*!50003 DROP PROCEDURE IF EXISTS `login_NguoiDung` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login_NguoiDung`(
	IN login_TenTaiKhoan VARCHAR(45),
    IN login_MatKhau VARCHAR(45)
)
BEGIN
	SELECT EXISTS (SELECT 1
	FROM NguoiDung
	WHERE TenTaiKhoan = login_TenTaiKhoan
	AND BINARY MatKhau = login_MatKhau);
END ;;
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
