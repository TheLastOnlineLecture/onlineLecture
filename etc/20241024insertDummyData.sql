-- 선생님 회원 더미 데이터 삽입 (각 과목당 2명씩)
INSERT INTO TBL_MEMBER (user_id, user_pwd, salt, user_name, user_nickname, user_email, user_phone, user_birth, user_type) VALUES
('teacher_kor1', 'hashed_password', 'salt_value', '김국어', '국어선생님1', 'korean1@teacher.com', '01012345678', '1980-01-01', 'T01'),
('teacher_kor2', 'hashed_password', 'salt_value', '박국어', '국어선생님2', 'korean2@teacher.com', '01012345679', '1981-01-02', 'T01'),
('teacher_eng1', 'hashed_password', 'salt_value', '이영어', '영어선생님1', 'english1@teacher.com', '01023456789', '1982-02-02', 'T02'),
('teacher_eng2', 'hashed_password', 'salt_value', '최영어', '영어선생님2', 'english2@teacher.com', '01023456790', '1983-02-03', 'T02'),
('teacher_math1', 'hashed_password', 'salt_value', '박수학', '수학선생님1', 'math1@teacher.com', '01034567890', '1985-03-03', 'T03'),
('teacher_math2', 'hashed_password', 'salt_value', '정수학', '수학선생님2', 'math2@teacher.com', '01034567891', '1986-03-04', 'T03'),
('teacher_sci1', 'hashed_password', 'salt_value', '최과학', '과학선생님1', 'science1@teacher.com', '01045678901', '1988-04-04', 'T04'),
('teacher_sci2', 'hashed_password', 'salt_value', '강과학', '과학선생님2', 'science2@teacher.com', '01045678902', '1989-04-05', 'T04'),
('teacher_soc1', 'hashed_password', 'salt_value', '정사회', '사회선생님1', 'social1@teacher.com', '01056789012', '1990-05-05', 'T05'),
('teacher_soc2', 'hashed_password', 'salt_value', '윤사회', '사회선생님2', 'social2@teacher.com', '01056789013', '1991-05-06', 'T05');

-- 강의 더미 데이터 삽입 (각 과목당 3개씩)
INSERT INTO TBL_LECTURE (lecture_code, lecture_name, lecture_price, lecture_limit_date, teacher_id) VALUES
('KOR001', '고등 국어 기초', 50000, '2025-12-31', 'teacher_kor1'),
('KOR002', '수능 국어 마스터', 80000, '2025-12-31', 'teacher_kor1'),
('KOR003', '문학 작품 분석', 70000, '2025-12-31', 'teacher_kor2'),
('ENG001', '중등 영어 문법', 60000, '2025-12-31', 'teacher_eng1'),
('ENG002', '토익 고득점 비법', 70000, '2025-12-31', 'teacher_eng1'),
('ENG003', '영어 회화 마스터', 75000, '2025-12-31', 'teacher_eng2'),
('MATH001', '중학 수학 기초', 55000, '2025-12-31', 'teacher_math1'),
('MATH002', '수능 수학 실전', 85000, '2025-12-31', 'teacher_math1'),
('MATH003', '고등 수학 심화', 90000, '2025-12-31', 'teacher_math2'),
('SCI001', '중등 과학 종합', 65000, '2025-12-31', 'teacher_sci1'),
('SCI002', '고등 물리 심화', 75000, '2025-12-31', 'teacher_sci1'),
('SCI003', '생명과학 집중 강의', 80000, '2025-12-31', 'teacher_sci2'),
('SOC001', '한국사 핵심 정리', 55000, '2025-12-31', 'teacher_soc1'),
('SOC002', '세계사 주요 사건', 65000, '2025-12-31', 'teacher_soc1'),
('SOC003', '사회문화 개념 정리', 60000, '2025-12-31', 'teacher_soc2');

-- 강의 상세 더미 데이터 삽입 (각 강의당 10개씩)
INSERT INTO TBL_LECTURE_DETAIL (lecture_code, lecture_detail_content, lecture_detail_file_path, lecture_detail_file_name, lecture_detail_file_size)
SELECT 
    l.lecture_code,
    CONCAT(l.lecture_name, ' - ', CASE 
        WHEN l.lecture_code LIKE 'KOR%' THEN '국어'
        WHEN l.lecture_code LIKE 'ENG%' THEN '영어'
        WHEN l.lecture_code LIKE 'MATH%' THEN '수학'
        WHEN l.lecture_code LIKE 'SCI%' THEN '과학'
        WHEN l.lecture_code LIKE 'SOC%' THEN '사회'
    END, ' ', numbers.n, '강'),
    CONCAT('/lectures/', LOWER(SUBSTRING(l.lecture_code, 1, 3)), '/', LOWER(SUBSTRING(l.lecture_code, 1, 3)), '_lecture_', numbers.n, '.mp4'),
    CONCAT(LOWER(SUBSTRING(l.lecture_code, 1, 3)), '_lecture_', numbers.n, '.mp4'),
    FLOOR(500000000 + RAND() * 1500000000)
FROM 
    TBL_LECTURE l
CROSS JOIN 
    (SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 
     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) numbers;

-- 학생 회원 더미 데이터 삽입
INSERT INTO TBL_MEMBER (user_id, user_pwd, salt, user_name, user_nickname, user_email, user_phone, user_birth, user_type) VALUES
('student1', 'hashed_password', 'salt_value', '학생일', '열공맨', 'student1@example.com', '01098765432', '2000-03-15', 'S01'),
('student2', 'hashed_password', 'salt_value', '학생이', '공부왕', 'student2@example.com', '01087654321', '2001-05-20', 'S01'),
('student3', 'hashed_password', 'salt_value', '학생삼', '수능전사', 'student3@example.com', '01076543210', '2002-11-10', 'S01'),
('student4', 'hashed_password', 'salt_value', '학생사', '문제풀이고수', 'student4@example.com', '01065432109', '2003-07-07', 'S01'),
('student5', 'hashed_password', 'salt_value', '학생오', '성적상승중', 'student5@example.com', '01054321098', '2004-09-25', 'S01');

-- 결제 더미 데이터 삽입
INSERT INTO TBL_PAYMENT (payment_idx, user_id, lecture_code, payment_date, payment_status, lecture_start_date)
SELECT 
    ROW_NUMBER() OVER () as payment_idx,
    m.user_id,
    l.lecture_code,
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 30) DAY) as payment_date,
    'P' as payment_status,
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 29) DAY) as lecture_start_date
FROM 
    TBL_MEMBER m
CROSS JOIN 
    TBL_LECTURE l
WHERE 
    m.user_type = 'S01'
ORDER BY 
    RAND()
LIMIT 20;

-- 게시판 더미 데이터 삽입
INSERT INTO TBL_BOARD (board_title, board_content, board_type, board_writer, board_category)
SELECT 
    CASE 
        WHEN board_type = 'P' THEN CONCAT('자유게시판 글 ', ROW_NUMBER() OVER (PARTITION BY board_type ORDER BY RAND()))
        WHEN board_type = 'N' THEN CONCAT('공지사항 ', ROW_NUMBER() OVER (PARTITION BY board_type ORDER BY RAND()))
        WHEN board_type = 'D' THEN CONCAT('자료실 게시물 ', ROW_NUMBER() OVER (PARTITION BY board_type ORDER BY RAND()))
        WHEN board_type = 'C' THEN CONCAT(l.lecture_name, ' 공지사항 ', ROW_NUMBER() OVER (PARTITION BY board_type, l.lecture_code ORDER BY RAND()))
        WHEN board_type = 'R' THEN CONCAT(l.lecture_name, ' 수강후기 ', ROW_NUMBER() OVER (PARTITION BY board_type, l.lecture_code ORDER BY RAND()))
    END as board_title,
    CONCAT('이것은 ', 
        CASE 
            WHEN board_type = 'P' THEN '자유게시판'
            WHEN board_type = 'N' THEN '공지사항'
            WHEN board_type = 'D' THEN '자료실'
            WHEN board_type = 'C' THEN '강의 공지'
            WHEN board_type = 'R' THEN '수강후기'
        END, 
        '의 내용입니다. ', REPEAT('더미 텍스트 ', 5)) as board_content,
    board_type,
    CASE 
        WHEN board_type IN ('N', 'D', 'C') THEN (SELECT user_id FROM TBL_MEMBER WHERE user_type LIKE 'T%' ORDER BY RAND() LIMIT 1)
        ELSE (SELECT user_id FROM TBL_MEMBER WHERE user_type = 'S01' ORDER BY RAND() LIMIT 1)
    END as board_writer,
    CASE 
        WHEN board_type IN ('C', 'R') THEN l.lecture_code
        ELSE NULL
    END as board_category
FROM 
    (SELECT 'P' as board_type UNION ALL SELECT 'N' UNION ALL SELECT 'D' UNION ALL SELECT 'C' UNION ALL SELECT 'R') types
CROSS JOIN 
    TBL_LECTURE l
ORDER BY 
    RAND()
LIMIT 100;

-- 댓글 더미 데이터 삽입
INSERT INTO TBL_COMMENT (comment_idx, post_idx, user_id, comment_content)
SELECT 
    ROW_NUMBER() OVER () as comment_idx,
    b.board_idx as post_idx,
    (SELECT user_id FROM TBL_MEMBER ORDER BY RAND() LIMIT 1) as user_id,
    CONCAT('이것은 댓글 내용입니다. ', REPEAT('더미 댓글 ', 3)) as comment_content
FROM 
    TBL_BOARD b
CROSS JOIN 
    (SELECT 1 UNION SELECT 2 UNION SELECT 3) numbers
ORDER BY 
    RAND()
LIMIT 200;

-- QnA 더미 데이터 삽입
INSERT INTO TBL_QNA (qna_title, qna_content, qna_writer, qna_type, qna_category)
SELECT 
    CASE 
        WHEN qna_type = 'G' THEN CONCAT('일반 QnA ', ROW_NUMBER() OVER (PARTITION BY qna_type ORDER BY RAND()))
        ELSE CONCAT(l.lecture_name, ' QnA ', ROW_NUMBER() OVER (PARTITION BY qna_type, l.lecture_code ORDER BY RAND()))
    END as qna_title,
    CONCAT('이것은 QnA 내용입니다. ', REPEAT('더미 QnA ', 4)) as qna_content,
    (SELECT user_id FROM TBL_MEMBER WHERE user_type = 'S01' ORDER BY RAND() LIMIT 1) as qna_writer,
    qna_type,
    CASE 
        WHEN qna_type = 'T' THEN l.lecture_code
        ELSE NULL
    END as qna_category
FROM 
    (SELECT 'G' as qna_type UNION ALL SELECT 'T') types
CROSS JOIN 
    TBL_LECTURE l
ORDER BY 
    RAND()
LIMIT 50;

-- QnA 댓글 더미 데이터 삽입
INSERT INTO TBL_QNA_COMMENT (qna_comment_content, qna_idx, qna_comment_writer)
SELECT 
    CONCAT('이것은 QnA 댓글 내용입니다. ', REPEAT('더미 QnA 댓글 ', 2)) as qna_comment_content,
    q.qna_idx,
    CASE 
        WHEN q.qna_type = 'T' THEN (SELECT user_id FROM TBL_MEMBER WHERE user_type LIKE 'T%' ORDER BY RAND() LIMIT 1)
        ELSE (SELECT user_id FROM TBL_MEMBER ORDER BY RAND() LIMIT 1)
    END as qna_comment_writer
FROM 
    TBL_QNA q
ORDER BY 
    RAND()
LIMIT 100;

-- 리뷰 더미 데이터 삽입
INSERT INTO TBL_REVIEW (review_idx, lecture_code, user_id, review_content, review_rating)
SELECT 
    ROW_NUMBER() OVER () as review_idx,
    p.lecture_code,
    p.user_id,
    CONCAT('이 강의는 ', 
        CASE FLOOR(RAND() * 5) + 1
            WHEN 1 THEN '매우 유익했습니다. '
            WHEN 2 THEN '도움이 되었습니다. '
            WHEN 3 THEN '괜찮았습니다. '
            WHEN 4 THEN '좋았습니다. '
            ELSE '훌륭했습니다. '
        END,
        REPEAT('추천합니다! ', 2)) as review_content,
    FLOOR(RAND() * 5) + 1 as review_rating
FROM 
    TBL_PAYMENT p
WHERE 
    p.payment_status = 'P'
ORDER BY 
    RAND()
LIMIT 40;

-- 성적표 더미 데이터 삽입
INSERT INTO TBL_REPORT_CARD (lecture_code, report_card_name, user_id)
SELECT 
    p.lecture_code,
    CONCAT(l.lecture_name, ' - ', DATE_FORMAT(CURDATE(), '%Y년 %m월 성적표')) as report_card_name,
    p.user_id
FROM 
    TBL_PAYMENT p
JOIN 
    TBL_LECTURE l ON p.lecture_code = l.lecture_code
WHERE 
    p.payment_status = 'P'
ORDER BY 
    RAND()
LIMIT 30;

-- 학생 강의 진행 상황 더미 데이터 삽입
INSERT INTO STUDENT_LECTURE_PROGRESS (user_id, lecture_detail_idx, is_completed, completion_date)
SELECT 
    p.user_id,
    ld.lecture_detail_idx,
    CASE WHEN RAND() < 0.7 THEN 1 ELSE 0 END as is_completed,
    CASE WHEN RAND() < 0.7 THEN DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 30) DAY) ELSE NULL END as completion_date
FROM 
    TBL_PAYMENT p
JOIN 
    TBL_LECTURE_DETAIL ld ON p.lecture_code = ld.lecture_code
WHERE 
    p.payment_status = 'P'
ORDER BY 
    RAND()
LIMIT 500;