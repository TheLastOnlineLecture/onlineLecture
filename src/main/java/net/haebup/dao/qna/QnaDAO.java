package net.haebup.dao.qna;
import java.util.List;
import net.haebup.utils.DatabaseUtil.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import net.haebup.dto.qna.QnaDTO;

//QnA 유형 G : 일반 QnA, T : 선생님 QnA
public class QnaDAO{
    //질문 목록 조회
    public List<QnaDTO> selectQnaListByType(int limit, int offset, String qnaType) throws SQLException{
        String sql = "SELECT * FROM qna WHERE qna_type = ? ORDER BY qna_idx DESC LIMIT ? OFFSET ?";
        List<QnaDTO> qnaList = new ArrayList<>();
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{qnaType, limit, offset})){
                ResultSet rs = dbUtil.executeQuery();
                while(rs.next()){
                    QnaDTO qnaDTO = new QnaDTO();
                    qnaDTO.setQnaIdx(rs.getInt("qna_idx"));
                    qnaDTO.setQnaType(rs.getString("qna_type"));
                    qnaDTO.setQnaTitle(rs.getString("qna_title"));
                    qnaDTO.setQnaContent(rs.getString("qna_content"));
                    qnaList.add(qnaDTO);
                }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("질문 목록 조회 중 오류가 발생하였습니다."+e);
        }
        return qnaList;
    }

    //질문 등록
    //qna_type : G, T
    //qna_category : 강의 코드 또는 null가능(1대1 질문일경우 질문타입 가능)
    //null 일 경우 일반 QnA (G)
    public int insertQna(QnaDTO qnaDTO) throws SQLException{
        String sql = "INSERT INTO qna (qna_type,qna_category, qna_title, qna_content) VALUES (?, ?, ?, ?)";
        int result = 0;
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{qnaDTO.getQnaType(), qnaDTO.getQnaCategory(), qnaDTO.getQnaTitle(), qnaDTO.getQnaContent()})){
                result = dbUtil.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("질문 등록 중 오류가 발생하였습니다."+e);
        }
        return result;
    }

    public int updateQna(QnaDTO qnaDTO) throws SQLException{
        String sql = "UPDATE qna SET qna_title = ?, qna_content = ? WHERE qna_idx = ?";
        int result = 0;
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{qnaDTO.getQnaTitle(), qnaDTO.getQnaContent(), qnaDTO.getQnaIdx()})){
                result = dbUtil.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("질문 수정 중 오류가 발생하였습니다."+e);
        }
        return result;
    }

    //질문 삭제
    public int deleteQna(int qnaIdx) throws SQLException{
        String sql = "DELETE FROM qna WHERE qna_idx = ?";
        int result = 0; 
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{qnaIdx})){
                result = dbUtil.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("질문 삭제 중 오류가 발생하였습니다."+e);
        }
        return result;
    }

    //질문 상세 조회
    public QnaDTO selectQnaDetail(int qnaIdx) throws SQLException{
        String sql = "SELECT * FROM qna WHERE qna_idx = ?";
        QnaDTO qnaDTO = null;
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{qnaIdx})){
                ResultSet rs = dbUtil.executeQuery();
                if(rs.next()){
                    qnaDTO = new QnaDTO();
                    qnaDTO.setQnaIdx(rs.getInt("qna_idx"));
                    qnaDTO.setQnaType(rs.getString("qna_type"));
                    qnaDTO.setQnaCategory(rs.getString("qna_category"));
                    qnaDTO.setQnaTitle(rs.getString("qna_title"));
                    qnaDTO.setQnaContent(rs.getString("qna_content"));
                }
            }
        return qnaDTO;
    }
    
}
