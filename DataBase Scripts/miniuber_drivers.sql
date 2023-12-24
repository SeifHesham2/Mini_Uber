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
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drivers` (
  `driverID` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `driver_password` varchar(100) NOT NULL,
  `number_of_trips` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `have_car` tinyint(1) NOT NULL DEFAULT '0',
  `rating` double DEFAULT '0',
  PRIMARY KEY (`driverID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drivers`
--

LOCK TABLES `drivers` WRITE;
/*!40000 ALTER TABLE `drivers` DISABLE KEYS */;
INSERT INTO `drivers` VALUES (1,'John','Doe','john.doe1@example.com','01012345678','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f',4,'2023-12-16 21:09:16',1,0),(2,'Jane','Smith','jane.smith2@example.com','01023456789','c6ba91b90d922e159893f46c387e5dc1b3dc5c101a5a4522f03b987177a24a91',0,'2023-12-16 21:09:16',1,0),(3,'Mike','Johnson','mike.johnson3@example.com','01034567890','5efc2b017da4f7736d192a74dde5891369e0685d4d38f2a455b6fcdab282df9c',0,'2023-12-16 21:09:16',1,0),(4,'Emily','Williams','emily.williams4@example.com','01045678901','6733b7ffeace4887c3b31258079c780d8db3018db9cbc05c500df3521f968df8',0,'2023-12-16 21:09:16',1,0),(5,'Daniel','Brown','daniel.brown5@example.com','01056789012','478a7da128a2875a1484798da2010d8f518ab4f341000da93c59fc5c201ded2c',0,'2023-12-16 21:09:16',1,0),(6,'Sophia','Jones','sophia.jones6@example.com','01067890123','b5e7000ad118b0dca7999213936347a400719a40560c32928947d1a6fcde7376',0,'2023-12-16 21:09:16',1,0),(7,'Ryan','Davis','ryan.davis7@example.com','01078901234','327a8226459a05182281f7a278463bc61ae72b31c2e0fc4f3f73cab2c29f5ed8',0,'2023-12-16 21:09:16',1,0),(8,'Emma','Taylor','emma.taylor8@example.com','01089012345','a8adcd2d4dba9baa859db460f956b1f00a35000e448f662b97336d0d2664ca99',0,'2023-12-16 21:09:16',1,0),(9,'Jacob','White','jacob.white9@example.com','01090123456','b8c882ed82994189513c8ac2d3a5c3e055546610e356244db45546fef32d2d62',0,'2023-12-16 21:09:16',1,0),(10,'Olivia','Clark','olivia.clark10@example.com','01012345670','451f969d4c174da1a948e7a7921bdf6db0da45e0681056eef169c4e330323735',0,'2023-12-16 21:09:16',1,0),(11,'Alice','Johnson','alice.johnson@example.com','01011112222','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f',16,'2023-12-16 21:09:16',1,0),(12,'Bob','Smith','bob.smith@example.com','01022223333','c6ba91b90d922e159893f46c387e5dc1b3dc5c101a5a4522f03b987177a24a91',0,'2023-12-16 21:09:16',1,0),(13,'Charlie','Davis','charlie.davis@example.com','01033334444','5efc2b017da4f7736d192a74dde5891369e0685d4d38f2a455b6fcdab282df9c',0,'2023-12-16 21:09:16',1,0),(14,'David','Miller','david.miller@example.com','01044445555','6733b7ffeace4887c3b31258079c780d8db3018db9cbc05c500df3521f968df8',0,'2023-12-16 21:09:16',1,0),(15,'Eva','Williams','eva.williams@example.com','01055556666','478a7da128a2875a1484798da2010d8f518ab4f341000da93c59fc5c201ded2c',0,'2023-12-16 21:09:16',1,0),(16,'Frank','Brown','frank.brown@example.com','01066667777','59a1ea0e7b558df84d247db20315c9e4b9bff7719ffaafd3150a3c529aa38d98',0,'2023-12-16 21:09:16',1,0),(17,'Grace','Taylor','grace.taylor@example.com','01077778888','b77e3c94b3fbc99f22771482363dc0ea731113fb184e655d2ec9461e1c68519b',0,'2023-12-16 21:09:16',1,0),(18,'Harry','Moore','harry.moore@example.com','01088889999','ebbf75fd13baaab8ce25b1d576efd9d071f8e95b8e8024035bb027a45604651e',0,'2023-12-16 21:09:16',1,0),(19,'Ivy','Jones','ivy.jones@example.com','01099990000','5d6e996d4ef01c66b299460b84d470c585ac813064ce1b5616dbfb738e232d38',0,'2023-12-16 21:09:16',1,0),(20,'Jack','Garcia','jack.garcia@example.com','01012341111','8d3c8ba9a36b65c7f050bec4d15dc1b05df668eddc2f80f8966f472c9483bd4c',0,'2023-12-16 21:09:16',1,0);
/*!40000 ALTER TABLE `drivers` ENABLE KEYS */;
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
