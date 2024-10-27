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
import java.text.SimpleDateFormat;

public class BoardDAO implements IFBoardDAO{
    
    // 게시물 목록 조회 : 게시물 타입에 따른 게시물 목록 조회 p = 자유게시판, n = 공지사항, d : 자료실, c:강의공지 
    @Override  
    public List<BoardDTO> getBoardList(int limit, int offset, String boardType, String boardCategory) throws SQLException {
        String sql = "SELECT * FROM tbl_board WHERE board_type = ?";
        
        if (boardCategory != null) {
            sql += " AND board_category = ?";
        }
        
        sql += " ORDER BY board_regdate DESC LIMIT ? OFFSET ?";

        List<BoardDTO> boardList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add(boardType);

        if (boardCategory != null) {
            params.add(boardCategory);
        }
        
        params.add(limit);
        params.add(offset);

        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, params.toArray())) {

            ResultSet rs = dbUtil.executeQuery();
            while (rs.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setBoardIdx(rs.getInt("board_idx"));
                boardDTO.setBoardType(rs.getString("board_type"));
                boardDTO.setBoardCategory(rs.getString("board_category"));
                boardDTO.setBoardTitle(rs.getString("board_title"));
                boardDTO.setBoardWriter(rs.getString("board_writer"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(rs.getTimestamp("board_regdate"));
                boardDTO.setBoardRegdate(formattedDate);

                boardList.add(boardDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("게시물 조회 중 오류가 발생하였습니다." + e);
        }
        return boardList;
    }
	
    public List<BoardDTO> getSearchBoardList(int limit, int offset, String boardType, String boardCategory, String searchType, String searchKeyword) throws SQLException {
        String sql = "SELECT * FROM tbl_board WHERE board_type = ?";
        
        if (boardCategory != null) {
            sql += " AND board_category = ?";
        }

        if ("title".equals(searchType)) {
            sql += " AND board_title LIKE ?";
        } else if ("writer".equals(searchType)) {
            sql += " AND board_writer LIKE ?";
        } else if ("title_content".equals(searchType)) {
            sql += " AND (board_title LIKE ? OR board_content LIKE ?)";
        } else {
            throw new IllegalArgumentException("유효하지 않은 검색 타입입니다.");
        }

        sql += " ORDER BY board_regdate DESC LIMIT ? OFFSET ?";

        List<BoardDTO> boardList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add(boardType);

        if (boardCategory != null) {
            params.add(boardCategory);
        }

        String searchParam = "%" + searchKeyword + "%";
        if ("title".equals(searchType) || "writer".equals(searchType)) {
            params.add(searchParam);
            params.add(limit);
            params.add(offset);
        } else if ("title_content".equals(searchType)) {
            params.add(searchParam);
            params.add(searchParam);
            params.add(limit);
            params.add(offset);
        }

        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, params.toArray())) {

            ResultSet rs = dbUtil.executeQuery();
            while (rs.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setBoardIdx(rs.getInt("board_idx"));
                boardDTO.setBoardType(rs.getString("board_type"));
                boardDTO.setBoardCategory(rs.getString("board_category"));
                boardDTO.setBoardTitle(rs.getString("board_title"));
                boardDTO.setBoardWriter(rs.getString("board_writer"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(rs.getTimestamp("board_regdate"));
                boardDTO.setBoardRegdate(formattedDate);
                boardList.add(boardDTO);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("게시물 조회 중 오류가 발생하였습니다." + e);
        }
        return boardList;
    }
    
    public List<BoardDTO> getBoardListByPage(int pageNo, int pageSize, String boardType, String boardCategory) throws SQLException {
        int limit = pageSize;                          
        int offset = (pageNo - 1) * pageSize;          

        return getBoardList(limit, offset, boardType, boardCategory);
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
    public int getTotalCount(String boardType, String boardCategory) throws SQLException{
        String sql = "SELECT COUNT(*) FROM tbl_board WHERE board_type = ?";
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardType, boardCategory})){
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
    
 // 게시물 개수 조회 (검색 조건 포함)
    public int getTotalCount(String boardType, String searchType, String searchKeyword) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_board WHERE board_type = ?";
        StringBuilder queryBuilder = new StringBuilder(sql);

        if ("title".equals(searchType)) {
            queryBuilder.append(" AND board_title LIKE ?");
        } else if ("writer".equals(searchType)) {
            queryBuilder.append(" AND board_writer LIKE ?");
        } else if ("title_content".equals(searchType)) {
            queryBuilder.append(" AND (board_title LIKE ? OR board_content LIKE ?)");
        }

        try (Connection conn = DBConnPool.getConnection();
             DbQueryUtil dbUtil = new DbQueryUtil(conn, queryBuilder.toString(), buildQueryParams(boardType, searchType, searchKeyword))) {
            ResultSet rs = dbUtil.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("게시물 개수 조회 중 오류가 발생하였습니다." + e);
        }
    }
    
    // 쿼리 파라미터를 생성하는 메서드
    private Object[] buildQueryParams(String boardType, String searchType, String searchKeyword) {
        if (searchType.equals("title") || searchType.equals("writer")) {
            return new Object[]{boardType, "%" + searchKeyword + "%"};
        } else if (searchType.equals("title_content")) {
            return new Object[]{boardType, "%" + searchKeyword + "%", "%" + searchKeyword + "%"};
        }
        return new Object[]{boardType}; 
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
        String sql = "INSERT INTO tbl_board (board_type, board_title, board_content, board_writer, board_category) VALUES (?, ?, ?, ?, ?)";
        int boardIdx = 0;  // 새로 생성된 게시글의 ID를 저장할 변수
        
        try (Connection conn = DBConnPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, boardDTO.getBoardType());
            pstmt.setString(2, boardDTO.getBoardTitle());
            pstmt.setString(3, boardDTO.getBoardContent());
            pstmt.setString(4, boardDTO.getBoardWriter());
            pstmt.setString(5, boardDTO.getBoardCategory());
            
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
}
