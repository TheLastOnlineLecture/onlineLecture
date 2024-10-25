ALTER TABLE TBL_PAYMENT MODIFY COLUMN payment_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
-- 회원 유형 데이터 삽입 (이미 존재하지 않는 경우)
INSERT IGNORE INTO TBL_MEMBER_TYPE (user_type, user_type_name) VALUES
('S01', '학생'),
('T01', '국어 선생님'),
('T02', '영어 선생님'),
('T03', '수학 선생님'),
('T04', '과학 선생님'),
('T05', '사회 선생님'),
('A00','어드민');

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
    CONCAT('/lectures/', LOWER(SUBSTRING(l.lecture_code, 1, 3)), '/', LOWER(l.lecture_code), '_lecture_', numbers.n, '.mp4'),
    CONCAT(LOWER(l.lecture_code), '_lecture_', numbers.n, '.mp4'),
    FLOOR(500000000 + RAND() * 1500000000)
FROM 
    TBL_LECTURE l
CROSS JOIN 
    (SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 
     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) numbers;

-- 학생 회원 더미 데이터 삽입 (50명)
INSERT INTO TBL_MEMBER (user_id, user_pwd, salt, user_name, user_nickname, user_email, user_phone, user_birth, user_type)
SELECT 
    CONCAT('student', LPAD(seq.n, 3, '0')),
    'hashed_password',
    'salt_value',
    CONCAT('학생', seq.n),
    CONCAT('열공이', seq.n),
    CONCAT('student', seq.n, '@example.com'),
    CONCAT('010', LPAD(FLOOR(RAND() * 100000000), 8, '0')),
    DATE_ADD('2000-01-01', INTERVAL FLOOR(RAND() * 3650) DAY),
    'S01'
FROM 
    (SELECT @row := @row + 1 AS n FROM 
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) t1,
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) t2,
        (SELECT @row:=0) t3 LIMIT 50) seq;

-- 결제 더미 데이터 삽입 (200개)
INSERT INTO TBL_PAYMENT (user_id, lecture_code, payment_date, payment_status, lecture_start_date)
SELECT 
    m.user_id,
    l.lecture_code,
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 180) DAY) as payment_date,
    CASE WHEN RAND() < 0.9 THEN 'P' ELSE 'R' END as payment_status,
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 179) DAY) as lecture_start_date
FROM 
    TBL_MEMBER m
CROSS JOIN 
    TBL_LECTURE l
WHERE 
    m.user_type = 'S01'
ORDER BY 
    RAND()
LIMIT 200;

-- 게시판 더미 데이터 삽입 (300개)
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
        '의 내용입니다. ', REPEAT('더미 텍스트 ', 10)) as board_content,
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
LIMIT 300;

-- 댓글 더미 데이터 삽입 (600개)
INSERT INTO TBL_COMMENT (post_idx, user_id, comment_content)
SELECT 
    b.board_idx as post_idx,
    (SELECT user_id FROM TBL_MEMBER ORDER BY RAND() LIMIT 1) as user_id,
    CONCAT('이것은 댓글 내용입니다. ', REPEAT('더미 댓글 ', 5)) as comment_content
FROM 
    TBL_BOARD b
CROSS JOIN 
    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) numbers
ORDER BY 
    RAND()
LIMIT 600;

-- QnA 더미 데이터 삽입 (150개)
INSERT INTO TBL_QNA (qna_title, qna_content, qna_writer, qna_type, qna_category)
SELECT 
    CASE 
        WHEN qna_type = 'G' THEN CONCAT('일반 QnA ', ROW_NUMBER() OVER (PARTITION BY qna_type ORDER BY RAND()))
        ELSE CONCAT(l.lecture_name, ' QnA ', ROW_NUMBER() OVER (PARTITION BY qna_type, l.lecture_code ORDER BY RAND()))
    END as qna_title,
    CONCAT('이것은 QnA 내용입니다. ', REPEAT('더미 QnA ', 8)) as qna_content,
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
LIMIT 150;

-- QnA 댓글 더미 데이터 삽입 (300개)
INSERT INTO TBL_QNA_COMMENT (qna_comment_content, qna_idx, qna_comment_writer)
SELECT 
    CONCAT('이것은 QnA 댓글 내용입니다. ', REPEAT('더미 QnA 댓글 ', 4)) as qna_comment_content,
    q.qna_idx,
    CASE 
        WHEN q.qna_type = 'T' THEN (SELECT user_id FROM TBL_MEMBER WHERE user_type LIKE 'T%' ORDER BY RAND() LIMIT 1)
        ELSE (SELECT user_id FROM TBL_MEMBER ORDER BY RAND() LIMIT 1)
    END as qna_comment_writer
FROM 
    TBL_QNA q
CROSS JOIN
    (SELECT 1 UNION SELECT 2) numbers
ORDER BY 
    RAND()
LIMIT 300;

-- 리뷰 더미 데이터 삽입 (100개)
INSERT INTO TBL_REVIEW (lecture_code, user_id, review_content, review_rating)
SELECT 
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
        REPEAT('추천합니다! ', 3)) as review_content,
    FLOOR(RAND() * 5) + 1 as review_rating
FROM 
    TBL_PAYMENT p
WHERE 
    p.payment_status = 'P'
ORDER BY 
    RAND()
LIMIT 100;

-- 성적표 더미 데이터 삽입 (80개)
INSERT INTO TBL_REPORT_CARD (lecture_code, report_card_name, user_id)
SELECT 
    p.lecture_code,
    CONCAT(l.lecture_name, ' - ', DATE_FORMAT(CURDATE() - INTERVAL FLOOR(RAND() * 180) DAY, '%Y년 %m월 성적표')) as report_card_name,
    p.user_id
FROM 
    TBL_PAYMENT p
JOIN 
    TBL_LECTURE l ON p.lecture_code = l.lecture_code
WHERE 
    p.payment_status = 'P'
ORDER BY 
    RAND()
LIMIT 80;

-- 학생 강의 진행 상황 더미 데이터 삽입 (1000개)
INSERT INTO STUDENT_LECTURE_PROGRESS (user_id, lecture_detail_idx, is_completed, completion_date)
SELECT 
    p.user_id,
    ld.lecture_detail_idx,
    CASE WHEN RAND() < 0.7 THEN 1 ELSE 0 END as is_completed,
    CASE WHEN RAND() < 0.7 THEN DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 90) DAY) ELSE NULL END as completion_date
FROM 
    TBL_PAYMENT p
JOIN 
    TBL_LECTURE_DETAIL ld ON p.lecture_code = ld.lecture_code
WHERE 
    p.payment_status = 'P'
ORDER BY 
    RAND()
LIMIT 1000;

-- 파일 더미 데이터 삽입 (100개)
INSERT INTO TBL_FILE (file_name, file_size, file_path, board_idx)
SELECT 
    CONCAT('file_', FLOOR(RAND() * 1000), '.', ELT(FLOOR(RAND() * 4) + 1, 'pdf', 'doc', 'jpg', 'zip')) as file_name,
    FLOOR(RAND() * 10000000) as file_size,
    CONCAT('/uploads/', DATE_FORMAT(CURDATE(), '%Y/%m/'), FLOOR(RAND() * 1000), '.', ELT(FLOOR(RAND() * 4) + 1, 'pdf', 'doc', 'jpg', 'zip')) as file_path,
    board_idx
FROM 
    TBL_BOARD
WHERE 
    board_type IN ('D', 'C')
ORDER BY 
    RAND()
LIMIT 100;