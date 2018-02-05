-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: capstone
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.19-MariaDB

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
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `point` int(11) DEFAULT NULL,
  `isTemplate` tinyint(1) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `fromDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `templateName` varchar(50) DEFAULT NULL,
  `feedbackName` varchar(50) DEFAULT NULL,
  `templateDes` varchar(50) DEFAULT NULL,
  `feedbackDes` varchar(50) DEFAULT NULL,
  `isPublished` tinyint(1) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `departmentID` int(11) DEFAULT NULL,
  `courseID` int(11) DEFAULT NULL,
  `majorID` int(11) DEFAULT NULL,
  `classID` int(11) DEFAULT NULL,
  `typeID` int(11) DEFAULT NULL,
  `referenceID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Feedback_Type1_idx` (`typeID`),
  KEY `fk_Feedback_Info1_idx` (`classID`),
  KEY `fk_Feedback_Major1_idx` (`majorID`),
  KEY `fk_Feedback_Course1_idx` (`courseID`),
  KEY `fk_Feedback_Department1_idx` (`departmentID`),
  KEY `fk_Feedback_UserInformation1_idx` (`userID`),
  KEY `fk_Feedback_Feedback1_idx` (`referenceID`),
  CONSTRAINT `fk_Feedback_Course1` FOREIGN KEY (`courseID`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Department1` FOREIGN KEY (`departmentID`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Feedback1` FOREIGN KEY (`referenceID`) REFERENCES `feedback` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Info1` FOREIGN KEY (`classID`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Major1` FOREIGN KEY (`majorID`) REFERENCES `major` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Type1` FOREIGN KEY (`typeID`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_UserInformation1` FOREIGN KEY (`userID`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-05 17:11:13
