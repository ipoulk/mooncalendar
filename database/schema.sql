-- MySQL dump 10.13  Distrib 8.0.44, for macos15 (arm64)
--
-- Host: 127.0.0.1    Database: mooncalendar_db
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `date_rule_type`
--

DROP TABLE IF EXISTS `date_rule_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `date_rule_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) NOT NULL,
  `name` varchar(500) NOT NULL,
  `name_translation_id` int DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_rule_type_translation1_idx` (`name_translation_id`),
  KEY `fk_rule_type_translation2_idx` (`description_translation_id`),
  KEY `fk_date_rule_type_translation1_idx` (`comment_translation_id`),
  CONSTRAINT `fk_date_rule_type_translation1` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_rule_type_translation1` FOREIGN KEY (`name_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_rule_type_translation2` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `document_category_id` int NOT NULL,
  `name` varchar(500) NOT NULL COMMENT 'Greek',
  `name_translation_id` int NOT NULL,
  `content` text COMMENT 'Greek',
  `content_translation_id` int NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_document_translation1_idx` (`name_translation_id`),
  KEY `fk_document_translation2_idx` (`description_translation_id`),
  KEY `fk_document_document_category1_idx` (`document_category_id`),
  KEY `fk_document_translation3_idx` (`comment_translation_id`),
  KEY `fk_document_translation4_idx` (`content_translation_id`),
  CONSTRAINT `fk_document_document_category1` FOREIGN KEY (`document_category_id`) REFERENCES `document_category` (`id`),
  CONSTRAINT `fk_document_translation1` FOREIGN KEY (`name_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_document_translation2` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_document_translation3` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_document_translation4` FOREIGN KEY (`content_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document_category`
--

DROP TABLE IF EXISTS `document_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `name_translation_id` int NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_document_category_translation1_idx` (`name_translation_id`),
  KEY `fk_document_category_translation2_idx` (`comment_translation_id`),
  KEY `fk_document_category_translation3_idx` (`description_translation_id`),
  CONSTRAINT `fk_document_category_translation1` FOREIGN KEY (`name_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_document_category_translation2` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_document_category_translation3` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document_has_image`
--

DROP TABLE IF EXISTS `document_has_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document_has_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `page_number` int DEFAULT NULL,
  `document_id` int NOT NULL,
  `image_id` int NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_document_image_document1_idx` (`document_id`),
  KEY `fk_document_image_image1_idx` (`image_id`),
  KEY `fk_document_image_translation1_idx` (`comment_translation_id`),
  KEY `fk_document_image_translation2_idx` (`description_translation_id`),
  CONSTRAINT `fk_document_image_document1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `fk_document_image_image1` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `fk_document_image_translation1` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_document_image_translation2` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) NOT NULL,
  `is_season` tinyint DEFAULT NULL,
  `event_category_id` int NOT NULL,
  `name` varchar(500) NOT NULL,
  `name_translation_id` int NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_event_event_category1_idx` (`event_category_id`),
  KEY `fk_event_translation1_idx` (`name_translation_id`),
  KEY `fk_event_translation2_idx` (`description_translation_id`),
  KEY `fk_event_translation3_idx` (`comment_translation_id`),
  CONSTRAINT `fk_event_event_category1` FOREIGN KEY (`event_category_id`) REFERENCES `event_category` (`id`),
  CONSTRAINT `fk_event_translation1` FOREIGN KEY (`name_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_event_translation2` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_event_translation3` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_category`
--

DROP TABLE IF EXISTS `event_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_category_id` int DEFAULT NULL,
  `code` varchar(500) NOT NULL,
  `name` varchar(500) NOT NULL,
  `name_translation_id` int NOT NULL DEFAULT '1',
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL DEFAULT '1',
  `comment` varchar(2000) DEFAULT NULL COMMENT 'This is for internal use',
  `comment_translation_id` int NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_event_category_event_category1_idx` (`parent_category_id`),
  KEY `fk_event_category_translation2_idx` (`name_translation_id`),
  KEY `fk_event_category_translation1_idx` (`description_translation_id`),
  KEY `fk_event_category_translation3_idx` (`comment_translation_id`),
  CONSTRAINT `fk_event_category_event_category1` FOREIGN KEY (`parent_category_id`) REFERENCES `event_category` (`id`),
  CONSTRAINT `fk_event_category_translation1` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_event_category_translation2` FOREIGN KEY (`name_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_event_category_translation3` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_date_rule`
--

DROP TABLE IF EXISTS `event_date_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_date_rule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `date_rule_type_id` int NOT NULL,
  `fasting_type_id` int DEFAULT NULL,
  `event_id` int NOT NULL,
  `start_month` int DEFAULT NULL,
  `start_day` int DEFAULT NULL,
  `end_month` int DEFAULT NULL,
  `end_day` int DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_event_date_rule_event1_idx` (`event_id`),
  KEY `fk_event_date_rule_fasting_type1_idx` (`fasting_type_id`),
  KEY `fk_event_date_rule_date_rule_type1_idx` (`date_rule_type_id`),
  KEY `fk_event_date_rule_translation1_idx` (`comment_translation_id`),
  KEY `fk_event_date_rule_translation2_idx` (`description_translation_id`),
  CONSTRAINT `fk_event_date_rule_date_rule_type1` FOREIGN KEY (`date_rule_type_id`) REFERENCES `date_rule_type` (`id`),
  CONSTRAINT `fk_event_date_rule_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `fk_event_date_rule_fasting_type1` FOREIGN KEY (`fasting_type_id`) REFERENCES `fasting_type` (`id`),
  CONSTRAINT `fk_event_date_rule_translation1` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_event_date_rule_translation2` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_has_document`
--

DROP TABLE IF EXISTS `event_has_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_has_document` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `event_id` int NOT NULL,
  `document_id` int NOT NULL,
  `role` varchar(200) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_event_has_document_event_document` (`event_id`,`document_id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_event_has_document_event1_idx` (`event_id`),
  KEY `fk_event_has_document_document1_idx` (`document_id`),
  KEY `fk_event_has_document_translation1_idx` (`description_translation_id`),
  KEY `fk_event_has_document_translation2_idx` (`comment_translation_id`),
  CONSTRAINT `fk_event_has_document_document1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `fk_event_has_document_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `fk_event_has_document_translation1` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_event_has_document_translation2` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_has_image`
--

DROP TABLE IF EXISTS `event_has_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_has_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `event_id` int NOT NULL,
  `image_id` int NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_event_has_image_event1_idx` (`event_id`),
  KEY `fk_event_has_image_image1_idx` (`image_id`),
  KEY `fk_event_has_image_translation1_idx` (`description_translation_id`),
  KEY `fk_event_has_image_translation2_idx` (`comment_translation_id`),
  CONSTRAINT `fk_event_has_image_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `fk_event_has_image_image1` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `fk_event_has_image_translation1` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_event_has_image_translation2` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fasting_type`
--

DROP TABLE IF EXISTS `fasting_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fasting_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `name_translation_id` int NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `meat` tinyint NOT NULL,
  `dairy` tinyint NOT NULL,
  `fish` tinyint NOT NULL,
  `wineoil` tinyint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_fasting_type_translation1_idx` (`name_translation_id`),
  KEY `fk_fasting_type_translation2_idx` (`description_translation_id`),
  KEY `fk_fasting_type_translation3_idx` (`comment_translation_id`),
  CONSTRAINT `fk_fasting_type_translation1` FOREIGN KEY (`name_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_fasting_type_translation2` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_fasting_type_translation3` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `language_id` int NOT NULL,
  `title` varchar(500) NOT NULL,
  `title_translation_id` int NOT NULL,
  `uri` varchar(4096) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int NOT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `mime_type` varchar(200) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_image_language1_idx` (`language_id`),
  KEY `fk_image_translation1_idx` (`title_translation_id`),
  KEY `fk_image_translation2_idx` (`description_translation_id`),
  KEY `fk_image_translation3_idx` (`comment_translation_id`),
  CONSTRAINT `fk_image_language1` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`),
  CONSTRAINT `fk_image_translation1` FOREIGN KEY (`title_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_image_translation2` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_image_translation3` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(500) DEFAULT NULL,
  `name` varchar(500) NOT NULL,
  `name_translation_id` int NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `description_translation_id` int DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `comment_translation_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_language_translation1_idx` (`name_translation_id`),
  KEY `fk_language_translation2_idx` (`comment_translation_id`),
  KEY `fk_language_translation3_idx` (`description_translation_id`),
  CONSTRAINT `fk_language_translation1` FOREIGN KEY (`name_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_language_translation2` FOREIGN KEY (`comment_translation_id`) REFERENCES `translation` (`id`),
  CONSTRAINT `fk_language_translation3` FOREIGN KEY (`description_translation_id`) REFERENCES `translation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `translation`
--

DROP TABLE IF EXISTS `translation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `translation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `en` varchar(2000) NOT NULL,
  `de` varchar(2000) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=365 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-27 16:50:35
