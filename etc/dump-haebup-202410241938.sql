-- MySQL dump 10.13  Distrib 9.0.1, for macos15.0 (arm64)
--
-- Host: 127.0.0.1    Database: haebup
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `STUDENT_LECTURE_PROGRESS`
--

DROP TABLE IF EXISTS `STUDENT_LECTURE_PROGRESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `STUDENT_LECTURE_PROGRESS` (
  `progress_idx` int unsigned NOT NULL AUTO_INCREMENT COMMENT '수강 진행 ID',
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '학생 ID',
  `lecture_detail_idx` int NOT NULL COMMENT '강의 상세 ID',
  `is_completed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '수강 완료 여부',
  `completion_date` datetime DEFAULT NULL COMMENT '수강 완료 일시',
  PRIMARY KEY (`progress_idx`),
  KEY `fk_progress_user` (`user_id`),
  KEY `fk_progress_lecture_detail` (`lecture_detail_idx`),
  CONSTRAINT `fk_progress_lecture_detail` FOREIGN KEY (`lecture_detail_idx`) REFERENCES `TBL_LECTURE_DETAIL` (`lecture_detail_idx`),
  CONSTRAINT `fk_progress_user` FOREIGN KEY (`user_id`) REFERENCES `TBL_MEMBER` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='학생 상세강의 수강 진행 정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_BOARD`
--

DROP TABLE IF EXISTS `TBL_BOARD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_BOARD` (
  `board_idx` int unsigned NOT NULL AUTO_INCREMENT COMMENT '게시물 아이디',
  `board_title` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '게시물 제목',
  `board_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '게시물 내용',
  `board_regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '게시물 등록일',
  `board_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '게시물 유형 (P: 자유게시판, N: 공지사항, D: 자료실, C: 강의 공지, R: 수강후기)\n카테고리 ( 강의 코드만 들어올수있게 )',
  `board_writer` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '게시물 작성자 아이디',
  `board_category` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '"C , R " 일때 null 안됨~',
  PRIMARY KEY (`board_idx`),
  KEY `fk_board_writer` (`board_writer`),
  KEY `fk_board_category` (`board_category`),
  CONSTRAINT `fk_board_category` FOREIGN KEY (`board_category`) REFERENCES `TBL_LECTURE` (`lecture_code`),
  CONSTRAINT `fk_board_writer` FOREIGN KEY (`board_writer`) REFERENCES `TBL_MEMBER` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_COMMENT`
--

DROP TABLE IF EXISTS `TBL_COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_COMMENT` (
  `comment_idx` int NOT NULL AUTO_INCREMENT,
  `post_idx` int unsigned NOT NULL COMMENT '댓글 대상 게시물 아이디',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '댓글 작성자 아이디',
  `comment_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '댓글 내용',
  `comment_regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 작성일',
  PRIMARY KEY (`comment_idx`),
  KEY `fk_comment_post` (`post_idx`),
  KEY `fk_comment_user` (`user_id`),
  CONSTRAINT `fk_comment_post` FOREIGN KEY (`post_idx`) REFERENCES `TBL_BOARD` (`board_idx`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `TBL_MEMBER` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_FILE`
--

DROP TABLE IF EXISTS `TBL_FILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_FILE` (
  `file_idx` int unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '파일 이름',
  `file_size` bigint NOT NULL COMMENT '파일 사이즈',
  `file_path` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '파일 경로',
  `board_idx` int unsigned NOT NULL COMMENT '게시물 아이디',
  PRIMARY KEY (`file_idx`),
  KEY `fk_file_board` (`board_idx`),
  CONSTRAINT `fk_file_board` FOREIGN KEY (`board_idx`) REFERENCES `TBL_BOARD` (`board_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_LECTURE`
--

DROP TABLE IF EXISTS `TBL_LECTURE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_LECTURE` (
  `lecture_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '강의 코드',
  `lecture_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '강의 이름',
  `lecture_price` int unsigned NOT NULL COMMENT '강의 가격',
  `lecture_regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '강의 등록일',
  `lecture_limit_date` datetime NOT NULL COMMENT '강의 제한일',
  `teacher_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '강의 선생님 아이디',
  PRIMARY KEY (`lecture_code`),
  KEY `fk_lecture_teacher` (`teacher_id`),
  CONSTRAINT `fk_lecture_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `TBL_MEMBER` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_LECTURE_DETAIL`
--

DROP TABLE IF EXISTS `TBL_LECTURE_DETAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_LECTURE_DETAIL` (
  `lecture_detail_idx` int NOT NULL AUTO_INCREMENT COMMENT '강의 상세 아이디',
  `lecture_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '강의 코드',
  `lecture_detail_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '강의 상세 내용',
  `lecture_detail_file_path` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '강의 상세 파일 경로',
  `lecture_detail_file_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '강의 상세 파일 이름',
  `lecture_detail_file_size` bigint NOT NULL COMMENT '강의 상세 파일 사이즈',
  PRIMARY KEY (`lecture_detail_idx`),
  KEY `fk_lecture_detail_lecture` (`lecture_code`),
  CONSTRAINT `fk_lecture_detail_lecture` FOREIGN KEY (`lecture_code`) REFERENCES `TBL_LECTURE` (`lecture_code`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_MEMBER`
--

DROP TABLE IF EXISTS `TBL_MEMBER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_MEMBER` (
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '회원 아이디',
  `user_pwd` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '회원 비밀번호',
  `salt` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '회원 비밀번호 솔트',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '회원 이름',
  `user_nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '회원 닉네임',
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '회원 이메일',
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '회원 전화번호',
  `user_birth` date DEFAULT NULL COMMENT '회원 생년월일',
  `user_regdate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '회원 가입일',
  `user_type` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '회원 유형 코드',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uq_user_nickname` (`user_nickname`),
  KEY `fk_member_type` (`user_type`),
  CONSTRAINT `fk_member_type` FOREIGN KEY (`user_type`) REFERENCES `TBL_MEMBER_TYPE` (`user_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_MEMBER_TYPE`
--

DROP TABLE IF EXISTS `TBL_MEMBER_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_MEMBER_TYPE` (
  `user_type` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '회원 유형 코드',
  `user_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '회원 유형 이름',
  PRIMARY KEY (`user_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_PAYMENT`
--

DROP TABLE IF EXISTS `TBL_PAYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_PAYMENT` (
  `payment_idx` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '결제 학생 아이디',
  `lecture_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '결제 강의 코드',
  `payment_date` datetime NOT NULL COMMENT '결제 날짜',
  `payment_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '결제 상태 (P: 결제 완료, R: 환불 ,I : 결제 미완료)',
  `lecture_start_date` datetime DEFAULT NULL COMMENT '수강 시작일',
  PRIMARY KEY (`payment_idx`),
  KEY `fk_payment_user` (`user_id`),
  KEY `fk_payment_lecture` (`lecture_code`),
  CONSTRAINT `fk_payment_lecture` FOREIGN KEY (`lecture_code`) REFERENCES `TBL_LECTURE` (`lecture_code`),
  CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `TBL_MEMBER` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_QNA`
--

DROP TABLE IF EXISTS `TBL_QNA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_QNA` (
  `qna_idx` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'QnA 아이디',
  `qna_title` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'QnA 제목',
  `qna_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT 'QnA 내용',
  `qna_regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'QnA 등록일',
  `qna_writer` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'QnA 작성자 아이디',
  `qna_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'QnA 유형 G : 일반 QnA, T : 선생님 QnA',
  `qna_category` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'QnA 카테고리 일반 QnA 유형 코드, 강의 코드',
  PRIMARY KEY (`qna_idx`),
  KEY `fk_qna_writer` (`qna_writer`),
  CONSTRAINT `fk_qna_writer` FOREIGN KEY (`qna_writer`) REFERENCES `TBL_MEMBER` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_QNA_COMMENT`
--

DROP TABLE IF EXISTS `TBL_QNA_COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_QNA_COMMENT` (
  `qna_comment_idx` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'QnA 댓글 아이디',
  `qna_comment_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT 'QnA 댓글 내용',
  `qna_comment_regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'QnA 댓글 등록일',
  `qna_idx` int unsigned NOT NULL COMMENT 'QnA 아이디',
  `qna_comment_writer` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'QnA 댓글 작성자 아이디',
  PRIMARY KEY (`qna_comment_idx`),
  KEY `fk_qna_comment_qna` (`qna_idx`),
  KEY `fk_qna_comment_writer` (`qna_comment_writer`),
  CONSTRAINT `fk_qna_comment_qna` FOREIGN KEY (`qna_idx`) REFERENCES `TBL_QNA` (`qna_idx`),
  CONSTRAINT `fk_qna_comment_writer` FOREIGN KEY (`qna_comment_writer`) REFERENCES `TBL_MEMBER` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_REPORT_CARD`
--

DROP TABLE IF EXISTS `TBL_REPORT_CARD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_REPORT_CARD` (
  `report_card_idx` int unsigned NOT NULL AUTO_INCREMENT COMMENT '성적표 아이디',
  `lecture_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '성적표 대상 강의 코드',
  `report_card_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '성적표 이름',
  `report_card_regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '성적표 등록일',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '성적표 작성자 아이디',
  PRIMARY KEY (`report_card_idx`),
  KEY `fk_report_card_lecture` (`lecture_code`),
  KEY `fk_report_card_user` (`user_id`),
  CONSTRAINT `fk_report_card_lecture` FOREIGN KEY (`lecture_code`) REFERENCES `TBL_LECTURE` (`lecture_code`),
  CONSTRAINT `fk_report_card_user` FOREIGN KEY (`user_id`) REFERENCES `TBL_MEMBER` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TBL_REVIEW`
--

DROP TABLE IF EXISTS `TBL_REVIEW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TBL_REVIEW` (
  `review_idx` int unsigned NOT NULL AUTO_INCREMENT,
  `lecture_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '리뷰 대상 강의 코드',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '리뷰 작성자 아이디',
  `review_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '리뷰 내용',
  `review_rating` int unsigned NOT NULL COMMENT '리뷰 평점',
  `review_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '리뷰 작성일',
  PRIMARY KEY (`review_idx`),
  KEY `fk_review_lecture` (`lecture_code`),
  KEY `fk_review_user` (`user_id`),
  CONSTRAINT `fk_review_lecture` FOREIGN KEY (`lecture_code`) REFERENCES `TBL_LECTURE` (`lecture_code`),
  CONSTRAINT `fk_review_user` FOREIGN KEY (`user_id`) REFERENCES `TBL_MEMBER` (`user_id`),
  CONSTRAINT `chk_review_rating` CHECK ((`review_rating` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'haebup'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-24 19:38:44
