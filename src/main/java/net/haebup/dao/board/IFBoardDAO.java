package net.haebup.dao.board;
import java.util.List;
import net.haebup.dto.board.BoardDTO;

public interface IFBoardDAO {

    public List<BoardDTO> getBoardList(int borderIdx, int limit, int offfset , String boardType);

    public int getBoardListTotalCount(int borderIdx, String boardType);

    public BoardDTO getBoardDetail(int boardIdx);

    public boolean deleteByBoardIdx(int boardIdx);

    public boolean updateBoard(BoardDTO boardDTO);

    public boolean insertBoard(BoardDTO boardDTO);

}
