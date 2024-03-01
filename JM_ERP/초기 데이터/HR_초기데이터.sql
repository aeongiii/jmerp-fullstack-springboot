-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: jm_erp
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
-- Table structure for table `hr_cont`
--

DROP TABLE IF EXISTS `hr_cont`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_cont` (
  `id` int NOT NULL,
  `contract_date` date DEFAULT NULL,
  `contract_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `signa` bit(1) NOT NULL,
  `signb` bit(1) NOT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkfg0luohga28isq7bl60jknwc` (`employee_id`),
  CONSTRAINT `FKkfg0luohga28isq7bl60jknwc` FOREIGN KEY (`employee_id`) REFERENCES `hr_mem` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- HR_cont에서 id값 자동 증가
ALTER TABLE HR_cont MODIFY id INT AUTO_INCREMENT;

--
-- Dumping data for table `hr_cont`
--

LOCK TABLES `hr_cont` WRITE;
/*!40000 ALTER TABLE `hr_cont` DISABLE KEYS */;
INSERT INTO `hr_cont` VALUES (1,'2023-12-31','2023 연봉협상 근로계약서','김예은',_binary '',_binary '\0','1902005'),(2,'2023-12-31','2023 연봉협상 근로계약서','이동규',_binary '\0',_binary '\0','1704015'),(3,'2023-12-31','2023 연봉협상 근로계약서','김선진',_binary '',_binary '','2001002');
/*!40000 ALTER TABLE `hr_cont` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_day`
--

DROP TABLE IF EXISTS `hr_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_day` (
  `id` int NOT NULL,
  `day_work_date` date DEFAULT NULL,
  `day_work_hour` int NOT NULL,
  `day_work_name` varchar(255) DEFAULT NULL,
  `day_work_pay` int NOT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- HR_day에서 id값 자동 증가
ALTER TABLE HR_day MODIFY id INT AUTO_INCREMENT;

--
-- Dumping data for table `hr_day`
--

LOCK TABLES `hr_day` WRITE;
/*!40000 ALTER TABLE `hr_day` DISABLE KEYS */;
INSERT INTO `hr_day` VALUES (1,'2023-12-01',8,'아이유',80000,'영업팀'),(2,'2023-12-01',8,'배수지',80000,'생산팀'),(3,'2023-12-02',8,'장원영',80000,'인사팀'),(4,'2023-12-03',8,'안유진',80000,'구매팀');
/*!40000 ALTER TABLE `hr_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_dept`
--

DROP TABLE IF EXISTS `hr_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_dept` (
  `dept_name` varchar(255) NOT NULL,
  `dept_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dept_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_dept`
--

LOCK TABLES `hr_dept` WRITE;
/*!40000 ALTER TABLE `hr_dept` DISABLE KEYS */;
INSERT INTO `hr_dept` VALUES ('구매팀','02'),('생산팀','04'),('영업팀','03'),('인사팀','01'),('회계팀','05');
/*!40000 ALTER TABLE `hr_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_doc`
--

DROP TABLE IF EXISTS `hr_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_doc` (
  `doc_num` varchar(255) NOT NULL,
  `doc_date` date DEFAULT NULL,
  `doc_type` varchar(255) DEFAULT NULL,
  `doc_use` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`doc_num`),
  KEY `FK6aygbsyqtv82v849weinltmeh` (`employee_id`),
  CONSTRAINT `FK6aygbsyqtv82v849weinltmeh` FOREIGN KEY (`employee_id`) REFERENCES `hr_mem` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_doc`
--

LOCK TABLES `hr_doc` WRITE;
/*!40000 ALTER TABLE `hr_doc` DISABLE KEYS */;
INSERT INTO `hr_doc` VALUES ('2023-1','2023-05-08','경력증명서','금융기관제출','임호진','1505018'),('2023-2','2023-09-11','경력증명서','관공서제출','조현주','1805019'),('2023-3','2023-12-04','퇴직증명서','관공서제출','천준호','2205020'),('2024-1','2024-01-11','재직증명서','회사제출','황정은','1805021');
/*!40000 ALTER TABLE `hr_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_mem`
--

DROP TABLE IF EXISTS `hr_mem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_mem` (
  `employee_id` varchar(255) NOT NULL,
  `bank_num` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `regular_pay` int NOT NULL,
  `start_date` date DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FK6l9cy5k6vru3dle593ahvpo20` (`dept_name`),
  CONSTRAINT `FK6l9cy5k6vru3dle593ahvpo20` FOREIGN KEY (`dept_name`) REFERENCES `hr_dept` (`dept_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_mem`
--

LOCK TABLES `hr_mem` WRITE;
/*!40000 ALTER TABLE `hr_mem` DISABLE KEYS */;
INSERT INTO `hr_mem` VALUES ('1501004','123456789','email004@naver.com','김영훈','사원',60000000,'2015-03-02','인사팀'),('1503009','123456789','email009@naver.com','박주성','팀장',60000000,'2015-03-02','영업팀'),('1504013','123456789','email013@naver.com','서준하','팀장',60000000,'2015-09-01','생산팀'),('1505018','123456789','email018@naver.com','임호진','팀장',60000000,'2015-09-01','회계팀'),('1603010','123456789','email010@naver.com','박준석','사원',60000000,'2016-03-02','영업팀'),('1701001','123456789','email001@naver.com','김가을','팀장',60000000,'2017-03-02','인사팀'),('1701003','123456789','email003@naver.com','김세중','사원',60000000,'2017-03-02','인사팀'),('1703012','123456789','email012@naver.com','서주완','사원',60000000,'2017-09-01','영업팀'),('1704015','123456789','email015@naver.com','이동규','사원',60000000,'2017-03-02','생산팀'),('1804016','123456789','email016@naver.com','이상효','사원',60000000,'2018-03-02','생산팀'),('1805019','123456789','email019@naver.com','조현주','사원',60000000,'2018-09-01','회계팀'),('1805021','123456789','email021@naver.com','황정은','사원',60000000,'2018-03-02','회계팀'),('1902005','123456789','email005@naver.com','김예은','팀장',60000000,'2019-03-02','구매팀'),('2001002','123456789','email002@naver.com','김선진','사원',60000000,'2020-03-02','인사팀'),('2002007','123456789','email007@naver.com','문성빈','사원',60000000,'2020-03-02','구매팀'),('2002008','123456789','email008@naver.com','박종현','사원',60000000,'2020-03-02','구매팀'),('2003011','123456789','email011@naver.com','서예성','사원',60000000,'2020-03-02','영업팀'),('2204014','123456789','email014@naver.com','성하빈','사원',60000000,'2022-09-01','생산팀'),('2204017','123456789','email017@naver.com','임지환','사원',60000000,'2022-09-01','생산팀'),('2205020','123456789','email020@naver.com','천준호','사원',60000000,'2022-09-01','회계팀'),('2302006','123456789','email006@naver.com','김태일','사원',60000000,'2023-09-01','구매팀');
/*!40000 ALTER TABLE `hr_mem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_work`
--

DROP TABLE IF EXISTS `hr_work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_work` (
  `id` int NOT NULL,
  `attendance` varchar(255) DEFAULT NULL,
  `end_time` time(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `overtime_hour` int NOT NULL,
  `overtime_pay` int NOT NULL,
  `overtime_type` varchar(255) DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  `work_date` date DEFAULT NULL,
  `work_hour` int NOT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2rocam6vrd4q38un9v327efn2` (`employee_id`),
  CONSTRAINT `FK2rocam6vrd4q38un9v327efn2` FOREIGN KEY (`employee_id`) REFERENCES `hr_mem` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- HR_work에서 id값 자동 증가
ALTER TABLE HR_work MODIFY id INT AUTO_INCREMENT;
--
-- Dumping data for table `hr_work`
--

LOCK TABLES `hr_work` WRITE;
/*!40000 ALTER TABLE `hr_work` DISABLE KEYS */;
INSERT INTO `hr_work` VALUES (1,'연차',NULL,'김가을',0,0,'',NULL,'2023-12-01',0,'1701001'),(2,'','18:10:00.000000','김선진',3,80000,'야근수당','08:45:00.000000','2023-12-01',8,'2001002'),(3,'','18:10:00.000000','김세중',0,0,'','08:45:00.000000','2023-12-01',8,'1701003'),(4,'','18:10:00.000000','김영훈',0,0,'','08:45:00.000000','2023-12-01',8,'1501004'),(5,'','18:10:00.000000','김예은',0,0,'','08:45:00.000000','2023-12-01',8,'1902005'),(6,'','18:10:00.000000','김태일',3,80000,'야근수당','08:45:00.000000','2023-12-01',8,'2302006'),(7,'','18:10:00.000000','문성빈',0,0,'','08:45:00.000000','2023-12-01',8,'2002007'),(8,'','18:10:00.000000','박종현',0,0,'','08:45:00.000000','2023-12-01',8,'2002008'),(9,'','18:10:00.000000','박주성',3,80000,'야근수당','08:45:00.000000','2023-12-01',8,'1503009'),(10,'','18:10:00.000000','박준석',3,80000,'야근수당','08:45:00.000000','2023-12-01',8,'1603010'),(11,'','18:10:00.000000','서예성',0,0,'','08:45:00.000000','2023-12-01',8,'2003011'),(12,'','18:10:00.000000','서주완',0,0,'','08:45:00.000000','2023-12-01',8,'1703012'),(13,'','18:10:00.000000','서준하',0,0,'','08:45:00.000000','2023-12-01',8,'1504013'),(14,'','18:10:00.000000','성하빈',0,0,'','08:45:00.000000','2023-12-01',8,'2204014'),(15,'연차',NULL,'이동규',0,0,'',NULL,'2023-12-01',0,'1704015'),(16,'연차',NULL,'이상효',0,0,'',NULL,'2023-12-01',0,'1804016'),(17,'','18:10:00.000000','임지환',3,80000,'야근수당','08:45:00.000000','2023-12-01',8,'2204017'),(18,'','18:10:00.000000','임호진',0,0,'','08:45:00.000000','2023-12-01',8,'1505018'),(19,'','18:10:00.000000','조현주',0,0,'','08:45:00.000000','2023-12-01',8,'1805019'),(20,'','18:10:00.000000','천준호',0,0,'','08:45:00.000000','2023-12-01',8,'2205020'),(21,'','18:10:00.000000','황정은',0,0,'','08:45:00.000000','2023-12-01',8,'1805021'),(22,'','18:10:00.000000','김가을',0,0,'','08:45:00.000000','2023-12-02',8,'1701001'),(23,'','18:10:00.000000','김선진',3,80000,'야근수당','08:45:00.000000','2023-12-02',8,'2001002'),(24,'','18:10:00.000000','김세중',0,0,'','08:45:00.000000','2023-12-02',8,'1701003'),(25,'','18:10:00.000000','김영훈',0,0,'','08:45:00.000000','2023-12-02',8,'1501004'),(26,'','18:10:00.000000','김예은',0,0,'','08:45:00.000000','2023-12-02',8,'1902005'),(27,'','18:10:00.000000','김태일',0,0,'','08:45:00.000000','2023-12-02',8,'2302006'),(28,'','18:10:00.000000','문성빈',3,80000,'야근수당','08:45:00.000000','2023-12-02',8,'2002007'),(29,'연차',NULL,'박종현',0,0,'',NULL,'2023-12-02',0,'2002008'),(30,'','18:10:00.000000','박주성',3,80000,'야근수당','08:45:00.000000','2023-12-02',8,'1503009'),(31,'','18:10:00.000000','박준석',3,80000,'야근수당','08:45:00.000000','2023-12-02',8,'1603010'),(32,'연차',NULL,'서예성',0,0,'',NULL,'2023-12-02',0,'2003011'),(33,'','18:10:00.000000','서주완',0,0,'','08:45:00.000000','2023-12-02',8,'1703012'),(34,'','18:10:00.000000','서준하',0,0,'','08:45:00.000000','2023-12-02',8,'1504013'),(35,'','18:10:00.000000','성하빈',0,0,'','08:45:00.000000','2023-12-02',8,'2204014'),(36,'','18:10:00.000000','이동규',0,0,'','08:45:00.000000','2023-12-02',8,'1704015'),(37,'','18:10:00.000000','이상효',0,0,'','08:45:00.000000','2023-12-02',8,'1804016'),(38,'','18:10:00.000000','임지환',0,0,'','08:45:00.000000','2023-12-02',8,'2204017'),(39,'','18:10:00.000000','임호진',0,0,'','08:45:00.000000','2023-12-02',8,'1505018'),(40,'','18:10:00.000000','조현주',0,0,'','08:45:00.000000','2023-12-02',8,'1805019'),(41,'','18:10:00.000000','천준호',0,0,'','08:45:00.000000','2023-12-02',8,'2205020'),(42,'','18:10:00.000000','황정은',0,0,'','08:45:00.000000','2023-12-02',8,'1805021'),(43,'','18:10:00.000000','김가을',0,0,'','08:45:00.000000','2023-12-03',8,'1701001'),(44,'','18:10:00.000000','김선진',0,0,'','08:45:00.000000','2023-12-03',8,'2001002'),(45,'','18:10:00.000000','김세중',0,0,'','08:45:00.000000','2023-12-03',8,'1701003'),(46,'','18:10:00.000000','김영훈',0,0,'','08:45:00.000000','2023-12-03',8,'1501004'),(47,'','18:10:00.000000','김예은',3,80000,'야근수당','08:45:00.000000','2023-12-03',8,'1902005'),(48,'','18:10:00.000000','김태일',3,80000,'야근수당','08:45:00.000000','2023-12-03',8,'2302006'),(49,'','18:10:00.000000','문성빈',0,0,'','08:45:00.000000','2023-12-03',8,'2002007'),(50,'','18:10:00.000000','박종현',0,0,'','08:45:00.000000','2023-12-03',8,'2002008'),(51,'','18:10:00.000000','박주성',0,0,'','08:45:00.000000','2023-12-03',8,'1503009'),(52,'','18:10:00.000000','박준석',0,0,'','08:45:00.000000','2023-12-03',8,'1603010'),(53,'','18:10:00.000000','서예성',0,0,'','08:45:00.000000','2023-12-03',8,'2003011'),(54,'','18:10:00.000000','서주완',3,80000,'야근수당','08:45:00.000000','2023-12-03',8,'1703012'),(55,'','18:10:00.000000','서준하',0,0,'','08:45:00.000000','2023-12-03',8,'1504013'),(56,'','18:10:00.000000','성하빈',0,0,'','08:45:00.000000','2023-12-03',8,'2204014'),(57,'','18:10:00.000000','이동규',0,0,'','08:45:00.000000','2023-12-03',8,'1704015'),(58,'','18:10:00.000000','이상효',3,80000,'야근수당','08:45:00.000000','2023-12-03',8,'1804016'),(59,'','18:10:00.000000','임지환',3,80000,'야근수당','08:45:00.000000','2023-12-03',8,'2204017'),(60,'','18:10:00.000000','임호진',0,0,'','08:45:00.000000','2023-12-03',8,'1505018'),(61,'','18:10:00.000000','조현주',0,0,'','08:45:00.000000','2023-12-03',8,'1805019'),(62,'','18:10:00.000000','천준호',0,0,'','08:45:00.000000','2023-12-03',8,'2205020'),(63,'','18:10:00.000000','황정은',0,0,'','08:45:00.000000','2023-12-03',8,'1805021');
/*!40000 ALTER TABLE `hr_work` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-25 17:52:14
