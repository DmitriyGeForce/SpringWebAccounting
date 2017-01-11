-- MySQL dump 10.16  Distrib 10.1.20-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.20-MariaDB-1~xenial

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounting_table`
--

DROP TABLE IF EXISTS `accounting_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounting_table` (
  `accounting_table_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `dateOfAction` varchar(45) NOT NULL,
  `typeOfAction` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  `totalAmount` int(11) NOT NULL,
  PRIMARY KEY (`accounting_table_id`),
  UNIQUE KEY `id_UNIQUE` (`accounting_table_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounting_table`
--

LOCK TABLES `accounting_table` WRITE;
/*!40000 ALTER TABLE `accounting_table` DISABLE KEYS */;
INSERT INTO `accounting_table` VALUES (1,2,'17:44 21.10.2016','Income',1,1),(2,2,'17:44 21.10.2016','Income',1,2),(5,3,'22:48 21.10.2016','Income',1,1),(7,2,'22:25 24.10.2016','Income',1,3),(8,2,'22:25 24.10.2016','Income',45,48),(9,2,'22:25 24.10.2016','Expense',34,14),(10,2,'22:41 24.10.2016','Income',344,358),(11,2,'12:33 31.10.2016','Income',344,702),(12,2,'15:05 31.10.2016','Income',344,1046),(13,2,'15:10 31.10.2016','Income',123,1169),(15,3,'14:00 01.11.2016','Income',450,451),(16,3,'14:01 01.11.2016','Income',48,499),(17,3,'14:01 01.11.2016','Expense',399,100),(18,3,'14:01 01.11.2016','Income',150,250),(20,2,'15:42 11.11.2016','Income',12345,13514),(22,2,'16:32 11.11.2016','Income',12345,25859),(25,2,'17:16 11.11.2016','Expense',118,25741),(29,2,'23:16 11.11.2016','Income',124,25865);
/*!40000 ALTER TABLE `accounting_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `salt` char(16) NOT NULL,
  `password` char(32) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','FJB6L','0cc42419cfbfee6a2dd6b59bf7cb0a02'),(2,'user','RTGFC','308d48a84d337a255efb0cc003cbbb74'),(3,'usa','3htQh','1480346a8406dd2c4aa68f40ce30406c');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-11 15:26:56
