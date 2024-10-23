package net.haebup.dao.board;
import java.util.List;
import net.haebup.dto.board.BoardDTO;
import net.haebup.utils.DatabaseUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO implements IFBoardDAO{
    
    @Override  
    public List<BoardDTO> getBoardList(int limit, int offset, String boardType) throws SQLException{
        String sql = "SELECT * FROM board WHERE board_type = ? ORDER BY board_idx DESC LIMIT ? OFFSET ?";
        List<BoardDTO> boardList = new ArrayList<>();
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardType,limit,offset})){
                ResultSet rs = dbUtil.executeQuery();
                while(rs.next()){
                    BoardDTO boardDTO = new BoardDTO();
                    boardDTO.setBoardIdx(rs.getInt("board_idx"));
                    boardDTO.setBoardType(rs.getString("board_type").charAt(0));
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

    @Override
    public List<BoardDTO> getUserBoardList(int limit, int offset, String boardType, String userId) throws SQLException{
        String sql = "SELECT * FROM board WHERE board_type = ? AND board_writer = ? ORDER BY board_idx DESC LIMIT ? OFFSET ?";
        List<BoardDTO> boardList = new ArrayList<>();
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{boardType,userId,limit,offset})){
                ResultSet rs = dbUtil.executeQuery();
                while(rs.next()){
                    BoardDTO boardDTO = new BoardDTO();
                    boardDTO.setBoardIdx(rs.getInt("board_idx"));
                    boardDTO.setBoardType(rs.getString("board_type").charAt(0));
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

    @Override
    public int getTotalCount(String boardType) throws SQLException{
        String sql = "SELECT COUNT(*) FROM board WHERE board_type = ?";
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

    @Override
    public int getUserTotalCount(String boardType, String userId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM board WHERE board_type = ? AND board_writer = ?";
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

    @Override
    public BoardDTO getBoardDetail(int boardIdx) throws SQLException{
        return null;
    }

    @Override
    public int deleteByBoardIdx(int boardIdx) throws SQLException{
        return 0;
    }

    @Override
    public int updateBoard(BoardDTO boardDTO) throws SQLException{
        return 0;
    }

    @Override
    public int insertBoard(BoardDTO boardDTO) throws SQLException{
        return 0;
    }

}
