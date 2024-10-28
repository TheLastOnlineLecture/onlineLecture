package net.haebup.dao.lecture;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import net.haebup.dto.lecture.LectureDTO;
import net.haebup.utils.DatabaseUtil.DBConnPool;
import net.haebup.utils.DatabaseUtil.DbQueryUtil;

public class LectureAdminDAO {

    // 강의 추가 메소드
    public int insertLecture(LectureDTO lectureDTO) throws SQLException {
        String sql = "INSERT INTO TBL_LECTURE (lecture_code, lecture_name, lecture_price, lecture_limit_date, teacher_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] {
                 lectureDTO.getLectureCode(),
                 lectureDTO.getLectureName(),
                 lectureDTO.getLecturePrice(),
                 lectureDTO.getLectureLimitDate(),
                 lectureDTO.getTeacherId()
             })) {
            return dbUtil.executeUpdate();
        }
    }

    // 강의 수정 메소드
    public int updateLecture(LectureDTO lectureDTO) throws SQLException {
        String sql = "UPDATE TBL_LECTURE SET lecture_name = ?, lecture_price = ?, lecture_limit_date = ?, teacher_id = ? WHERE lecture_code = ?";
        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] {
                 lectureDTO.getLectureName(),
                 lectureDTO.getLecturePrice(),
                 lectureDTO.getLectureLimitDate(),
                 lectureDTO.getTeacherId(),
                 lectureDTO.getLectureCode()
             })) {
            return dbUtil.executeUpdate();
        }
    }

    // 강의 삭제 메소드
    public int deleteLecture(String lectureCode) throws SQLException {
        String sql = "DELETE FROM TBL_LECTURE WHERE lecture_code = ?";
        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { lectureCode })) {
            return dbUtil.executeUpdate();
        }
    }

    // 강의 상세 정보 조회 메소드
    public LectureDTO getLectureDetail(String lectureCode) throws SQLException {
        String sql = "SELECT * FROM TBL_LECTURE WHERE lecture_code = ?";
        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { lectureCode })) {
            ResultSet rs = dbUtil.executeQuery();
            if (rs.next()) {
                LectureDTO lectureDTO = new LectureDTO();
                lectureDTO.setLectureCode(rs.getString("lecture_code"));
                lectureDTO.setLectureName(rs.getString("lecture_name"));
                lectureDTO.setLecturePrice(rs.getInt("lecture_price"));
                lectureDTO.setLectureLimitDate(rs.getString("lecture_limit_date"));
                lectureDTO.setTeacherId(rs.getString("teacher_id"));
                return lectureDTO;
            }
        }
        return null;
    }

    // 모든 강의 조회 메소드
    public List<LectureDTO> getAllLectures() throws SQLException {
        List<LectureDTO> lectures = new ArrayList<>();
        String sql = "SELECT * FROM TBL_LECTURE";
        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] {})) {
            ResultSet rs = dbUtil.executeQuery();
            while (rs.next()) {
                LectureDTO lectureDTO = new LectureDTO();
                lectureDTO.setLectureCode(rs.getString("lecture_code"));
                lectureDTO.setLectureName(rs.getString("lecture_name"));
                lectureDTO.setLecturePrice(rs.getInt("lecture_price"));
                lectureDTO.setLectureLimitDate(rs.getString("lecture_limit_date"));
                lectureDTO.setTeacherId(rs.getString("teacher_id"));
                lectures.add(lectureDTO);
            }
        }
        return lectures;
    }
}
