CREATE DATABASE  IF NOT EXISTS `miniuber` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `miniuber`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: miniuber
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaints` (
  `complaintID` int NOT NULL AUTO_INCREMENT,
  `tripID` int NOT NULL,
  `description` varchar(255) NOT NULL,
  `opened` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`complaintID`),
  KEY `tripID` (`tripID`),
  CONSTRAINT `complaints_ibfk_1` FOREIGN KEY (`tripID`) REFERENCES `trips` (`tripID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES (1,1,'Driver was rude',1,'2023-12-16 21:10:49'),(2,2,'Late pickup',1,'2023-12-16 21:10:49'),(3,3,'Uncomfortable ride',1,'2023-12-16 21:10:49'),(4,4,'Lost item',1,'2023-12-16 21:10:49'),(5,5,'Overcharged',1,'2023-12-16 21:10:49'),(6,6,'Unsafe driving',1,'2023-12-16 21:10:49'),(7,7,'Dirty vehicle',1,'2023-12-16 21:10:49'),(8,8,'Driver took wrong route',1,'2023-12-16 21:10:49'),(9,9,'Trip canceled without notice',1,'2023-12-16 21:10:49'),(10,10,'Driver behavior issue',1,'2023-12-16 21:10:49'),(11,11,'Billing discrepancy',1,'2023-12-16 21:10:49'),(12,12,'Vehicle condition issue',1,'2023-12-16 21:10:49'),(13,13,'Driver didn\'t follow instructions',1,'2023-12-16 21:10:49'),(14,14,'Unprofessional conduct',1,'2023-12-16 21:10:49'),(15,15,'Late drop-off',1,'2023-12-16 21:10:49');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-22 12:53:16
