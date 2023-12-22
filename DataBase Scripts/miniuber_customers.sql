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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `UserPassword` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Mohamed','Ali','mohamed.ali@example.com','01011112222','password123','2023-12-16 21:09:47'),(2,'Fatima','Ahmed','fatima.ahmed@example.com','01022223333','password456','2023-12-16 21:09:47'),(3,'Ali','Khalil','ali.khalil@example.com','01033334444','password789','2023-12-16 21:09:47'),(4,'Nour','Mahmoud','nour.mahmoud@example.com','01044445555','passwordabc','2023-12-16 21:09:47'),(5,'Lamia','Hassan','lamia.hassan@example.com','01055556666','passworddef','2023-12-16 21:09:47'),(6,'Ahmed','Saleh','ahmed.saleh@example.com','01066667777','passwordghi','2023-12-16 21:09:47'),(7,'Reem','Abdullah','reem.abdullah@example.com','01077778888','passwordjkl','2023-12-16 21:09:47'),(8,'Sara','Issa','sara.issa@example.com','01088889999','passwordmno','2023-12-16 21:09:47'),(9,'Hassan','Nour','hassan.nour@example.com','01099990000','passwordpqr','2023-12-16 21:09:47'),(10,'Zeinab','Abbas','zeinab.abbas@example.com','01012341111','passwordstu','2023-12-16 21:09:47'),(11,'Youssef','Maher','youssef.maher@example.com','01011112222','password123','2023-12-16 21:09:47'),(12,'Hala','Tarek','hala.tarek@example.com','01022223333','password456','2023-12-16 21:09:47'),(13,'Karim','Nadia','karim.nadia@example.com','01033334444','password789','2023-12-16 21:09:47'),(14,'Nada','Wael','nada.wael@example.com','01044445555','passwordabc','2023-12-16 21:09:47'),(15,'Ahmed','Lina','ahmed.lina@example.com','01055556666','passworddef','2023-12-16 21:09:47'),(16,'Sara','Amr','sara.amr@example.com','01066667777','passwordghi','2023-12-16 21:09:47'),(17,'Omar','Heba','omarr.heba@example.com','01077778888','passwordjkl','2023-12-16 21:09:47'),(18,'Rana','Maged','rana.maged@example.com','01088889999','passwordmno','2023-12-16 21:09:47'),(19,'Hassan','Samar','hassaan.samar@example.com','01099990000','passwordpqr','2023-12-16 21:09:47'),(20,'Dina','Tamer','dina.tamer@example.com','01012341111','passwordstu','2023-12-16 21:09:47'),(21,'Mohammed','Fatima','mohammed.fatima@example.com','01011112222','password123','2023-12-16 21:10:13'),(22,'Aisha','Tarek','aisha.tarek@example.com','01022223333','password456','2023-12-16 21:10:13'),(23,'Khaled','Nadia','khaled.nadia@example.com','01033334444','password789','2023-12-16 21:10:13'),(24,'Mona','Wael','mona.wael@example.com','01044445555','passwordabc','2023-12-16 21:10:13'),(25,'Ali','Lina','ali.lina@example.com','01055556666','passworddef','2023-12-16 21:10:13'),(26,'Safia','Amr','safia.amr@example.com','01066667777','passwordghi','2023-12-16 21:10:13'),(27,'Omar','Heba','omar.heba@example.com','01077778888','passwordjkl','2023-12-16 21:10:13'),(28,'Randa','Maged','randa.maged@example.com','01088889999','passwordmno','2023-12-16 21:10:13'),(29,'Hassan','Samar','hassan.samar@example.com','01099990000','passwordpqr','2023-12-16 21:10:13'),(30,'Dalia','Tamer','dalia.tamer@example.com','01012341111','passwordstu','2023-12-16 21:10:13'),(31,'mohamed','ali','a@gmail.com','01096688107','123','2023-12-17 14:27:22'),(32,'a','b','ab@gmail.com','01099688107','koko123','2023-12-20 12:08:21');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
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
