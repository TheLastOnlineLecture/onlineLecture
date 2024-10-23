package net.haebup.dao.member.payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.haebup.dto.member.payment.PaymentDTO;
import net.haebup.utils.DatabaseUtil.DBConnPool;
import net.haebup.utils.DatabaseUtil.DbQueryUtil;

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

    public int getPaymentCount(String userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_payment WHERE user_id = ?";
        try (Connection conn = DBConnPool.getConnection();
                DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { userId })) {
            ResultSet rs = dbUtil.executeQuery();
            return rs.getInt(1);
        }
    }

    public void updatePaymentStatus(int paymentIdx, String paymentStatus) throws SQLException {
        String sql = "UPDATE tbl_payment SET payment_status = ? WHERE payment_idx = ?";
        try (Connection conn = DBConnPool.getConnection();
                DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { paymentStatus, paymentIdx })) {
            dbUtil.executeUpdate();
        }
    }

    

}
