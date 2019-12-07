-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlyquanhat
-- ------------------------------------------------------
-- Server version	5.7.28

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
-- Table structure for table `lichdangky`
--

DROP TABLE IF EXISTS `lichdangky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lichdangky` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hbatdau` int(11) NOT NULL,
  `hketthuc` int(11) NOT NULL,
  `pbatdau` int(11) NOT NULL,
  `pketthuc` int(11) NOT NULL,
  `idnhanvien` int(11) NOT NULL,
  `tenca` varchar(45) NOT NULL,
  `trangthai` varchar(45) NOT NULL,
  `ngay` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lichdangky`
--

LOCK TABLES `lichdangky` WRITE;
/*!40000 ALTER TABLE `lichdangky` DISABLE KEYS */;
INSERT INTO `lichdangky` VALUES (1,12,18,0,0,2,'ca chieu','Da duyet','2019-11-28'),(2,7,12,0,0,2,'ca sang','Da duyet','2019-11-28'),(3,7,12,0,0,3,'ca sang','Da duyet','2019-11-29'),(4,12,18,0,0,3,'ca chieu','Da duyet','2019-11-29'),(5,12,18,0,0,3,'ca chieu','Da duyet','2019-11-28'),(6,7,12,0,0,3,'ca sang','Cho duyet','2019-11-30'),(7,18,23,0,0,3,'ca toi','Cho duyet','2019-11-30'),(8,18,23,0,0,3,'ca toi','Cho duyet','2019-12-01'),(9,12,18,0,0,3,'ca chieu','Cho duyet','2019-12-01'),(10,12,18,0,0,5,'ca chieu','Cho duyet','2019-11-28'),(11,12,18,0,0,9,'ca chieu','Da duyet','2019-11-28');
/*!40000 ALTER TABLE `lichdangky` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-01 20:18:56
