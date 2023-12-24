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
-- Table structure for table `trips`
--

DROP TABLE IF EXISTS `trips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trips` (
  `tripID` int NOT NULL AUTO_INCREMENT,
  `pickup_point` varchar(100) DEFAULT NULL,
  `destination` varchar(100) NOT NULL,
  `trip_time` datetime NOT NULL,
  `trip_price` decimal(10,0) NOT NULL,
  `is_finished` tinyint(1) DEFAULT '0',
  `payment_method` enum('PayPal','Visa','Cash') NOT NULL,
  `customerID` int DEFAULT NULL,
  `driverID` int DEFAULT NULL,
  PRIMARY KEY (`tripID`),
  KEY `customerID` (`customerID`),
  KEY `driverID` (`driverID`),
  CONSTRAINT `trips_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customerID`),
  CONSTRAINT `trips_ibfk_2` FOREIGN KEY (`driverID`) REFERENCES `drivers` (`driverID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trips`
--

LOCK TABLES `trips` WRITE;
/*!40000 ALTER TABLE `trips` DISABLE KEYS */;
INSERT INTO `trips` VALUES (1,'City Center','Suburb A','2023-12-20 08:00:00',50,1,'PayPal',NULL,NULL),(2,'Airport','Downtown','2023-12-21 12:30:00',30,1,'Cash',NULL,NULL),(3,'Mall','Park','2023-12-22 15:45:00',25,0,'Visa',NULL,11),(4,'Office','Restaurant','2023-12-23 18:20:00',40,0,'PayPal',NULL,11),(5,'Train Station','Beach','2023-12-24 09:10:00',60,0,'Cash',NULL,NULL),(6,'Suburb B','City Center','2023-12-25 14:00:00',35,0,'Visa',NULL,1),(7,'Hospital','Gym','2023-12-26 17:30:00',20,0,'PayPal',NULL,1),(8,'School','Library','2023-12-27 20:15:00',15,0,'Cash',NULL,1),(9,'Cinema','Stadium','2023-12-28 10:45:00',55,0,'Visa',NULL,11),(10,'Park','Museum','2023-12-29 13:25:00',30,0,'PayPal',NULL,NULL),(11,'Suburb C','Zoo','2023-12-30 16:40:00',45,0,'Cash',NULL,NULL),(12,'Beach','City Center','2023-12-31 19:55:00',50,0,'Visa',NULL,11),(13,'Downtown','Restaurant','2024-01-01 09:30:00',25,0,'PayPal',NULL,11),(14,'Gym','Office','2024-01-02 12:00:00',35,0,'Cash',NULL,11),(15,'Library','Suburb A','2024-01-03 15:15:00',40,0,'Visa',NULL,11),(16,'Stadium','Airport','2024-01-04 18:50:00',20,0,'PayPal',NULL,NULL),(17,'Museum','Train Station','2024-01-05 11:05:00',30,0,'Cash',NULL,11),(18,'Zoo','School','2024-01-06 13:50:00',15,0,'Visa',NULL,11),(19,'Park','Cinema','2024-01-07 16:30:00',55,0,'PayPal',NULL,11),(20,'City Center','Suburb B','2024-01-08 19:20:00',40,0,'Cash',NULL,11),(21,'Suburb A','City Center','2024-01-09 08:00:00',25,0,'Visa',NULL,11),(22,'Downtown','Mall','2024-01-10 12:30:00',30,0,'PayPal',NULL,NULL),(23,'Gym','Office','2024-01-11 15:45:00',40,0,'Cash',NULL,11),(24,'Library','Stadium','2024-01-12 18:20:00',50,0,'Visa',NULL,11),(25,'Restaurant','Airport','2024-01-13 09:10:00',35,0,'PayPal',NULL,NULL),(26,'Beach','Park','2024-01-14 14:00:00',45,0,'Cash',NULL,NULL),(27,'City Center','Hospital','2024-01-15 17:30:00',20,0,'Visa',NULL,NULL),(28,'Suburb B','Train Station','2024-01-16 20:15:00',55,0,'PayPal',NULL,11),(29,'Zoo','Cinema','2024-01-17 10:45:00',30,0,'Cash',NULL,1),(30,'Museum','School','2024-01-18 13:25:00',15,0,'Visa',NULL,11),(31,'home','sahel','2024-12-16 23:09:47',105,0,'PayPal',1,NULL),(32,'home','sahel','2043-12-16 23:09:16',103,0,'Visa',31,NULL);
/*!40000 ALTER TABLE `trips` ENABLE KEYS */;
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
