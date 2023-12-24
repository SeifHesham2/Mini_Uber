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
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `carID` int NOT NULL AUTO_INCREMENT,
  `plate_number` varchar(100) NOT NULL,
  `car_type` varchar(100) NOT NULL,
  `car_color` varchar(50) NOT NULL,
  `car_model` varchar(100) NOT NULL,
  `number_of_seats` int NOT NULL,
  `driverID` int DEFAULT NULL,
  PRIMARY KEY (`carID`),
  UNIQUE KEY `plate_number` (`plate_number`),
  KEY `driverID` (`driverID`),
  CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`driverID`) REFERENCES `drivers` (`driverID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'ABC123','Sedan','Blue','Toyota Camry',5,1),(2,'XYZ789','SUV','Black','Ford Explorer',7,2),(3,'DEF456','Hatchback','Red','Honda Civic',4,3),(4,'GHI789','Truck','White','Chevrolet Silverado',3,4),(5,'JKL012','Convertible','Yellow','Mazda MX-5',2,5),(6,'MNO345','Van','Green','Ford Transit',6,6),(7,'PQR678','Coupe','Silver','Audi A5',2,7),(8,'STU901','Minivan','Gray','Chrysler Pacifica',7,8),(9,'VWX234','Crossover','Orange','Nissan Rogue',5,9),(10,'YZA567','Electric','Purple','Tesla Model 3',5,10),(11,'KLM890','SUV','Brown','Jeep Grand Cherokee',5,11),(12,'NOP123','Hatchback','Blue','Volkswagen Golf',4,12),(13,'QRS456','Truck','Gray','Ram 1500',6,13),(14,'TUV789','Convertible','Red','BMW 4 Series',2,14),(15,'WXY012','Van','Black','Mercedes-Benz Sprinter',7,15),(16,'ZAB345','Coupe','Silver','Chevrolet Corvette',2,16),(17,'BCD678','Minivan','White','Dodge Grand Caravan',7,17),(18,'EFG901','Crossover','Green','Subaru Outback',5,18),(19,'HIJ234','Electric','Purple','Nissan Leaf',5,19),(20,'LMN567','Sedan','Orange','Hyundai Sonata',5,20),(21,'OPQ890','Crossover','Silver','Toyota RAV4',5,NULL),(22,'RST123','Sedan','Black','Honda Accord',5,NULL),(23,'UVW456','Minivan','Red','Kia Sedona',7,NULL),(24,'XYZ389','Truck','Blue','Ford F-150',6,NULL),(25,'ABC012','Convertible','Yellow','Mazda MX-5',2,NULL),(26,'DEF345','SUV','Green','Subaru Crosstrek',5,NULL),(27,'GHI678','Van','White','Chevrolet Express',7,NULL),(28,'JKL901','Coupe','Orange','Ford Mustang',2,NULL),(29,'MNO234','Electric','Purple','Tesla Model Y',5,NULL),(30,'PQR567','Hatchback','Gray','Volkswagen Polo',4,NULL),(31,'seif','SUV','red','bmw',4,NULL),(32,'dsad','sad','asd','sas',4,NULL);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-22 12:53:17
