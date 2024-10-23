
ALTER TABLE `TBL_LECTURE_DETAIL` ADD COLUMN `lecture_detail_file_path` varchar(200) NOT NULL COMMENT '강의 상세 파일 경로' AFTER `lecture_detail_content`;
ALTER TABLE `TBL_LECTURE_DETAIL` ADD COLUMN `lecture_detail_file_name` bigint NOT NULL COMMENT '강의 상세 파일 이름' AFTER `lecture_detail_file_path`;
ALTER TABLE `TBL_LECTURE_DETAIL` ADD COLUMN `lecture_detail_file_size` bigint NOT NULL COMMENT '강의 상세 파일 사이즈' AFTER `lecture_detail_file_name`;
/*
강의 상세 파일 경로 추가
강의 상세 파일 이름 추가
강의 상세 파일 사이즈 추가
*/
ALTER TABLE `TBL_FILE` ADD COLUMN `file_size` bigint NOT NULL COMMENT '파일 사이즈' AFTER `file_name`;
/*
게시판 파일 사이즈 추가
*/
CREATE TABLE `STUDENT_LECTURE_PROGRESS` (
  `progress_id` int NOT NULL AUTO_INCREMENT COMMENT '수강 진행 ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '학생 ID',
  `lecture_detail_idx` int NOT NULL COMMENT '강의 상세 ID',
  `is_completed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '수강 완료 여부',
  `completion_date` datetime DEFAULT NULL COMMENT '수강 완료 일시',
  PRIMARY KEY (`progress_id`),
  KEY `fk_progress_user` (`user_id`),
  KEY `fk_progress_lecture_detail` (`lecture_detail_idx`),
  CONSTRAINT `fk_progress_user` FOREIGN KEY (`user_id`) REFERENCES `TBL_MEMBER` (`user_id`),
  CONSTRAINT `fk_progress_lecture_detail` FOREIGN KEY (`lecture_detail_idx`) REFERENCES `TBL_LECTURE_DETAIL` (`lecture_detail_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='학생 상세강의 수강 진행 정보';
/*
학생 상세강의 수강 진행 정보 추가
*/


