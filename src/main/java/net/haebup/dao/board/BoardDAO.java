package net.haebup.dao.board;
import java.util.List;
import net.haebup.dto.board.BoardDTO;

public class BoardDAO implements IFBoardDAO{

    @Override
    public List<BoardDTO> getBoardList(int borderIdx, int limit, int offfset , String boardType) {
        return null;
    }

    @Override
    public int getBoardListTotalCount(int borderIdx, String boardType) {
        return 0;
    }

    @Override
    public BoardDTO getBoardDetail(int boardIdx) {
        return null;
    }

    @Override
    public boolean deleteByBoardIdx(int boardIdx) {
        return false;
    }

    @Override
    public boolean updateBoard(BoardDTO boardDTO) {
        return false;
    }

    @Override
    public boolean insertBoard(BoardDTO boardDTO) {
        return false;
    }
    
}
