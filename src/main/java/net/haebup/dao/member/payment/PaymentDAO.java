package net.haebup.dao.member.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.haebup.dto.member.payment.PaymentDTO;
import net.haebup.utils.DatabaseUtil.DBConnPool;
import net.haebup.utils.DatabaseUtil.DbQueryUtil;
import net.haebup.dto.lecture.LectureDTO;

public class PaymentDAO {

    // 결제내역 조회
    public List<PaymentDTO> getPaymentList(int limit, int offset, String userId) throws SQLException {
        String sql = "SELECT * FROM tbl_payment WHERE user_id = ? ORDER BY payment_idx DESC LIMIT = ? OFFSET = ?";
        List<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
        try (Connection conn = DBConnPool.getConnection();
                DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { userId, limit, offset })) {
            ResultSet rs = dbUtil.executeQuery();
            while (rs.next()) {
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setPaymentIdx(rs.getInt("payment_idx"));
                paymentDTO.setUserId(rs.getString("user_id"));
                paymentDTO.setLectureCode(rs.getString("lecture_code"));
                paymentDTO.setPaymentDate(rs.getString("payment_date"));
                paymentDTO.setPaymentStatus(rs.getString("payment_status"));
                paymentDTO.setLectureStartDate(rs.getString("lecture_start_date"));
                paymentList.add(paymentDTO);
            }
            return paymentList;
        }
    }

    // 결제내역 총 개수 조회
    public int getPaymentCount(String userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_payment WHERE user_id = ?";
        try (Connection conn = DBConnPool.getConnection();
                DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { userId })) {
            ResultSet rs = dbUtil.executeQuery();
            return rs.getInt(1);
        }
    }

    // 결제상태 업데이트
    public int updatePaymentStatus(int paymentIdx, String paymentStatus) throws SQLException {
        String sql = "UPDATE tbl_payment SET payment_status = ? WHERE payment_idx = ?";
        try (Connection conn = DBConnPool.getConnection();
                DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { paymentStatus, paymentIdx })) {
            return dbUtil.executeUpdate();
        }
    }

    //장바구니 추가
    public int addCart(String userId, String lectureCode) throws SQLException {
        String sql = "INSERT INTO tbl_payment (user_id, lecture_code, payment_status) VALUES (?, ?, 'I')";
        try (Connection conn = DBConnPool.getConnection();
                DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { userId, lectureCode })) {
            return dbUtil.executeUpdate();
        }
    }
    //장바구니 조회
    public List<LectureDTO> getCartList(String userId) throws SQLException {
        String sql = "SELECT * FROM tbl_payment WHERE user_id = ? AND payment_status = 'I'";
        List<LectureDTO> cartList = new ArrayList<LectureDTO>();
        try (Connection conn = DBConnPool.getConnection();
                DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { userId })) {
            ResultSet rs = dbUtil.executeQuery();
            while (rs.next()) {
                LectureDTO lectureDTO = new LectureDTO();
                lectureDTO.setLectureCode(rs.getString("lecture_code"));
                lectureDTO.setLectureName(rs.getString("lecture_name"));
                cartList.add(lectureDTO);
            }
            return cartList;
        }
    }
    public boolean isLectureInCart(String userId, String lectureCode) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TBL_PAYMENT WHERE user_id = ? AND lecture_code = ? AND payment_status = 'I'";
        try (Connection conn = DBConnPool.getConnection();
                DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { userId, lectureCode })) {
            ResultSet rs = dbUtil.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // 여러 강의 장바구니 추가
    public int addMultipleToCart(String userId, List<String> lectureCodes) throws SQLException {
        String sql = "INSERT IGNORE INTO tbl_payment (user_id, lecture_code, payment_date, payment_status) " +
                     "VALUES (?, ?, CURRENT_TIMESTAMP, 'I')";

        int successCount = 0;

        try (Connection conn = DBConnPool.getConnection()) {
            for (String lectureCode : lectureCodes) {
                try (DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{userId, lectureCode})) {
                    int result = dbUtil.executeUpdate();
                    if (result > 0) successCount++;
                }
            }
        }

        return successCount;
    }
}
