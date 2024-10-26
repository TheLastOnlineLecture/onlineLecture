package net.haebup.dao.board;
import java.util.List;
import net.haebup.dto.board.BoardDTO;
import net.haebup.utils.DatabaseUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO implements IFBoardDAO{
    
    // 게시물 목록 조회 : 게시물 타입에 따른 게시물 목록 조회 p = 자유게시판, n = 공지사항, d : 자료실, c:강의공지 
    @Override  
    public List<BoardDTO> getBoardList(int limit, int offset, String boardType) throws SQLException{
        String sql = "SELECT * FROM tbl_board WHERE board_type = ? ORDER BY board_regdate DESC LIMIT ? OFFSET ?";
        List<BoardDTO> boardList = new ArrayList<>();
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardType,limit,offset})){
                ResultSet rs = dbUtil.executeQuery();
                while(rs.next()){
                    BoardDTO boardDTO = new BoardDTO();
                    boardDTO.setBoardIdx(rs.getInt("board_idx"));
                    boardDTO.setBoardType(rs.getString("board_type"));
                    boardDTO.setBoardTitle(rs.getString("board_title"));
                    boardDTO.setBoardWriter(rs.getString("board_writer"));
                    boardDTO.setBoardRegdate(rs.getString("board_regdate"));
                    boardList.add(boardDTO);
                }
                return boardList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("게시물 조회 중 오류가 발생하였습니다."+e);
        }
    }
    
    public List<BoardDTO> getBoardListByPage(int pageNo, int pageSize, String boardType) throws SQLException {
        int limit = pageSize;                          
        int offset = (pageNo - 1) * pageSize;          

        return getBoardList(limit, offset, boardType);
    }

    // 내 게시물 목록 조회
    @Override
    public List<BoardDTO> getUserBoardList(int limit, int offset, String boardType, String userId) throws SQLException{
        String sql = "SELECT * FROM tbl_board WHERE board_type = ? AND board_writer = ? ORDER BY board_idx DESC LIMIT ? OFFSET ?";
        List<BoardDTO> boardList = new ArrayList<>();
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardType,userId,limit,offset})){
                ResultSet rs = dbUtil.executeQuery();
                while(rs.next()){
                    BoardDTO boardDTO = new BoardDTO();
                    boardDTO.setBoardIdx(rs.getInt("board_idx"));
                    boardDTO.setBoardType(rs.getString("board_type"));
                    boardDTO.setBoardTitle(rs.getString("board_title"));
                    boardDTO.setBoardWriter(rs.getString("board_writer"));
                    boardDTO.setBoardRegdate(rs.getString("board_regdate"));
                    boardList.add(boardDTO);
                }
                return boardList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("게시물 조회 중 오류가 발생하였습니다."+e);
        }
    }

    // 게시물 개수 조회
    @Override
    public int getTotalCount(String boardType) throws SQLException{
        String sql = "SELECT COUNT(*) FROM tbl_board WHERE board_type = ?";
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardType})){
                ResultSet rs = dbUtil.executeQuery();
                if(rs.next()){
                    return rs.getInt(1);
                }
                return 0;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("게시물 개수 조회 중 오류가 발생하였습니다."+e);
        }
    }

    // 내 게시물 개수 조회
    @Override
    public int getUserTotalCount(String boardType, String userId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM tbl_board WHERE board_type = ? AND board_writer = ?";
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardType,userId})){
                ResultSet rs = dbUtil.executeQuery();
                if(rs.next()){
                    return rs.getInt(1);
                }
                return 0;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("내 게시물 개수 조회 중 오류가 발생하였습니다."+e);
        }
    }

    // 게시물 상세 조회 리턴 null 이면 존재하지 않는 게시물
    @Override
    public BoardDTO getBoardDetail(int boardIdx) throws SQLException{
        String sql = "SELECT * FROM tbl_board WHERE board_idx = ?";
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardIdx})){
                ResultSet rs = dbUtil.executeQuery();
                if(rs.next()){
                    BoardDTO boardDTO = new BoardDTO();
                    boardDTO.setBoardIdx(rs.getInt("board_idx"));
                    boardDTO.setBoardType(rs.getString("board_type"));
                    boardDTO.setBoardTitle(rs.getString("board_title"));
                    boardDTO.setBoardContent(rs.getString("board_content"));
                    boardDTO.setBoardWriter(rs.getString("board_writer"));
                    boardDTO.setBoardRegdate(rs.getString("board_regdate"));
                    return boardDTO;
                }else{
                    return null;
                }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("게시물 상세 조회 중 오류가 발생하였습니다."+e);
        }
    }

    @Override
    public int deleteByBoardIdx(int boardIdx) throws SQLException{
        String sql = "DELETE FROM tbl_board WHERE board_idx = ?";
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardIdx})){
                return dbUtil.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("게시물 삭제 중 오류가 발생하였습니다."+e);
        }
    }

    @Override
    public int updateBoard(BoardDTO boardDTO) throws SQLException{
        String sql = "UPDATE tbl_board SET board_title = ?, board_content = ? WHERE board_idx = ?";
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardDTO.getBoardTitle(),boardDTO.getBoardContent(),boardDTO.getBoardIdx()})){
                return dbUtil.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("게시물 수정 중 오류가 발생하였습니다."+e);
        }
    }

//    @Override
//    public int insertBoard(BoardDTO boardDTO) throws SQLException{
//        String sql = "INSERT INTO tbl_board (board_type, board_title, board_content, board_writer) VALUES (?, ?, ?, ?)";
//        try(Connection conn = DBConnPool.getConnection();
//            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardDTO.getBoardType(),boardDTO.getBoardTitle(),boardDTO.getBoardContent(),boardDTO.getBoardWriter()})){
//                return dbUtil.executeUpdate();
//                
//                
//        }catch(SQLException e){
//            e.printStackTrace();
//            throw new RuntimeException("게시물 등록 중 오류가 발생하였습니다."+e);
//        }
//    }
    
    public int insertBoard(BoardDTO boardDTO) throws SQLException {
        String sql = "INSERT INTO tbl_board (board_type, board_title, board_content, board_writer) VALUES (?, ?, ?, ?)";
        int boardIdx = 0;  // 새로 생성된 게시글의 ID를 저장할 변수
        
        try (Connection conn = DBConnPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, boardDTO.getBoardType());
            pstmt.setString(2, boardDTO.getBoardTitle());
            pstmt.setString(3, boardDTO.getBoardContent());
            pstmt.setString(4, boardDTO.getBoardWriter());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                	boardIdx = rs.getInt(1);  
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("게시물 등록 중 오류가 발생하였습니다." + e);
        }
        
        return boardIdx;  
    }
    
    //내가 쓴글 조회
    public List<BoardPost> getPostsByWriter(String writer) {
        List<BoardPost> posts = new ArrayList<>();

}
