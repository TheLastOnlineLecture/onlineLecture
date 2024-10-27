package net.haebup.dao.board.comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.haebup.dto.board.comment.MyCommentDTO;
import net.haebup.utils.DatabaseUtil.DBConnPool;
import net.haebup.utils.DatabaseUtil.DbQueryUtil;

public class MyCommentDAO {
	
	 // 댓글 전체 리스트
    public List<MyCommentDTO> getMyComments(String userId, int limit, int offset) {
        List<MyCommentDTO> comments = new ArrayList<>();
        String sql = "SELECT comment_idx AS myCommentIdx, user_id AS myCommentWriter, comment_content AS myCommentContent, " +
                     "comment_regdate AS myCommentRegdate, 'comment' AS myCommentType " +
                     "FROM tbl_comment WHERE user_id = ? " +
                     "UNION ALL " +
                     "SELECT qna_comment_idx AS myCommentIdx, qna_comment_writer AS myCommentWriter, qna_comment_content AS myCommentContent, " +
                     "qna_comment_regdate AS myCommentRegdate, 'qna_comment' AS myCommentType " +
                     "FROM tbl_qna_comment WHERE qna_comment_writer = ? " +
                     "ORDER BY myCommentRegdate DESC " +
                     "LIMIT ? OFFSET ?"; 

        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{userId, userId, limit, offset})) {
            
            ResultSet rs = dbUtil.executeQuery(); 
            while (rs.next()) {
                MyCommentDTO comment = new MyCommentDTO();
                comment.setMyCommentIdx(rs.getInt("myCommentIdx"));
                comment.setMyCommentWriter(rs.getString("myCommentWriter"));
                comment.setMyCommentContent(rs.getString("myCommentContent"));
                comment.setMyCommentRegdate(rs.getString("myCommentRegdate"));
                comment.setMyCommentType(rs.getString("myCommentType"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    
    // 페이징
    public List<MyCommentDTO> getTotalComment(String userId, int pageNo, int pageSize) throws SQLException {
        int limit = pageSize;                          
        int offset = (pageNo - 1) * pageSize;          
        return getMyComments(userId, limit, offset);
    }
    
    // 전체 댓글 수
    public int getTotalCommentsCount(String userId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM (" +
                     "SELECT comment_idx FROM tbl_comment WHERE user_id = ? " +
                     "UNION ALL " +
                     "SELECT qna_comment_idx FROM tbl_qna_comment WHERE qna_comment_writer = ?) AS combined";

        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{userId, userId})) {

            try (ResultSet rs = dbUtil.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return count;
    }
}
