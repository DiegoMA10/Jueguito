CREATE DATABASE  IF NOT EXISTS `juego` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `juego`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: juego
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `character_party`
--

DROP TABLE IF EXISTS `character_party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `character_party` (
  `PartyId` int NOT NULL,
  `characterID` int NOT NULL,
  `party_index` int NOT NULL,
  `level` int NOT NULL,
  `exp` int NOT NULL,
  `hp` int NOT NULL,
  `mp` int NOT NULL,
  `isAlive` tinyint(1) NOT NULL,
  PRIMARY KEY (`PartyId`,`characterID`),
  KEY `characterID` (`characterID`),
  CONSTRAINT `character_party_ibfk_1` FOREIGN KEY (`PartyId`) REFERENCES `party` (`partyID`),
  CONSTRAINT `character_party_ibfk_2` FOREIGN KEY (`characterID`) REFERENCES `characters` (`characterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `character_party`
--

LOCK TABLES `character_party` WRITE;
/*!40000 ALTER TABLE `character_party` DISABLE KEYS */;
INSERT INTO `character_party` VALUES (1,1,0,8,2100,0,57,0),(1,2,1,8,2100,0,46,0),(1,3,2,8,2100,90,51,1);
/*!40000 ALTER TABLE `character_party` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters` (
  `characterID` int NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`characterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
INSERT INTO `characters` VALUES (1,'Aerith'),(2,'Tifa'),(3,'Cloud');
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `games` (
  `gameID` int NOT NULL,
  `playTime` double unsigned NOT NULL,
  `partyID` int NOT NULL,
  PRIMARY KEY (`gameID`),
  KEY `partyID` (`partyID`),
  CONSTRAINT `games_ibfk_1` FOREIGN KEY (`partyID`) REFERENCES `party` (`partyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES (1,378.41666666665117,1);
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `partyID` int NOT NULL,
  `itemID` int NOT NULL,
  `amount` int NOT NULL,
  PRIMARY KEY (`partyID`,`itemID`),
  KEY `itemID` (`itemID`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`partyID`) REFERENCES `party` (`partyID`),
  CONSTRAINT `inventory_ibfk_2` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,1,2),(1,2,4);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `itemID` int NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Potion'),(2,'Ether');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `levelstats`
--

DROP TABLE IF EXISTS `levelstats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `levelstats` (
  `level` int NOT NULL,
  `HP` int NOT NULL,
  `MP` int NOT NULL,
  `EXP` int NOT NULL,
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `levelstats`
--

LOCK TABLES `levelstats` WRITE;
/*!40000 ALTER TABLE `levelstats` DISABLE KEYS */;
INSERT INTO `levelstats` VALUES (2,11,4,32),(3,23,8,96),(4,37,13,208),(5,54,18,400),(6,74,24,672),(7,96,30,1056),(8,120,37,1552),(9,146,45,2184),(10,173,53,2976),(11,201,62,3936),(12,231,71,5080),(13,266,81,6432),(14,305,91,7992),(15,349,101,9784),(16,399,111,11840),(17,453,121,14152),(18,510,132,16736),(19,571,143,19616),(20,636,154,22832),(21,703,165,26360),(22,772,176,30232),(23,844,188,34456),(24,920,200,39056),(25,999,212,44072),(26,1081,224,49464),(27,1167,236,55288),(28,1257,249,61568),(29,1352,262,68304),(30,1451,275,75496),(31,1551,288,83184),(32,1652,301,91384),(33,1754,315,100088),(34,1856,329,109344),(35,1959,343,119136),(36,2063,357,129504),(37,2169,371,140464),(38,2276,386,152008),(39,2384,401,164184),(40,2494,416,176976),(41,2605,431,190416),(42,2718,446,204520),(43,2832,462,219320),(44,2948,478,234808),(45,3065,494,251000),(46,3184,510,267936),(47,3304,526,285600),(48,3426,543,304040),(49,3551,560,323248),(50,3679,577,343248),(51,3809,593,364064),(52,3940,608,385696),(53,4073,622,408160),(54,4207,635,431488),(55,4343,647,455680),(56,4480,658,480776),(57,4619,668,506760),(58,4761,677,533680),(59,4905,685,561528),(60,5050,692,590320),(61,5197,698,620096),(62,5345,703,650840),(63,5495,708,682600),(64,5647,714,715368),(65,5800,720,749160),(66,5955,727,784016),(67,6111,734,819920),(68,6269,741,856920),(69,6429,749,895016),(70,6591,757,934208),(71,6751,765,974536),(72,6906,773,1016000),(73,7057,781,1058640),(74,7202,788,1102456),(75,7342,795,1147456),(76,7478,802,1193648),(77,7610,808,1241080),(78,7736,814,1289744),(79,7856,820,1339672),(80,7973,826,1390872),(81,8086,831,1443368),(82,8196,836,1497160),(83,8304,841,1552264),(84,8409,846,1608712),(85,8511,851,1666512),(86,8611,856,1725688),(87,8709,861,1786240),(88,8804,867,1848184),(89,8896,873,1911552),(90,8986,879,1976352),(91,9074,885,2042608),(92,9161,891,2110320),(93,9246,898,2179504),(94,9329,906,2250192),(95,9411,915,2322392),(96,9491,925,2396128),(97,9574,936,2471400),(98,9660,948,2548224),(99,9748,961,2637112);
/*!40000 ALTER TABLE `levelstats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `party`
--

DROP TABLE IF EXISTS `party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `party` (
  `partyID` int NOT NULL,
  `gil` int NOT NULL,
  PRIMARY KEY (`partyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `party`
--

LOCK TABLES `party` WRITE;
/*!40000 ALTER TABLE `party` DISABLE KEYS */;
INSERT INTO `party` VALUES (1,3300);
/*!40000 ALTER TABLE `party` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-29  4:45:41
