package net.haebup.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dto.board.BoardDTO;
import net.haebup.utils.PaginationUtil.Pagination;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/gotoTotalPostList.do")
public class gotoTotalPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        String pageSizeParam = request.getParameter("pageSize");

        int pageNo = pageNoParam != null ? Integer.parseInt(pageNoParam) : 1;
        int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10;

        String boardCategory = request.getParameter("category");
        String boardType = request.getParameter("type");
        String searchType = request.getParameter("searchType");
        String searchKeyword = request.getParameter("keyword");
        	
        // 기본값 설정
        if (boardType == null) boardType = ""; 
        if (searchType == null) searchType = ""; 
        if (searchKeyword == null) searchKeyword = ""; 
        if (boardCategory == null) boardCategory = ""; 

        BoardDAO boardDAO = new BoardDAO();

        try {
            int totalCount = boardDAO.getTotalCount(boardType, searchType, searchKeyword);

            List<BoardDTO> boardList = boardDAO.totalBoardList(pageSize, (pageNo - 1) * pageSize, boardType, searchType, searchKeyword, boardCategory);

            Pagination pagination = new Pagination(pageNo, pageSize, totalCount, 10);
            System.out.println(pagination.toString());

            request.setAttribute("boardList", boardList);
            request.setAttribute("pagination", pagination);

//            for(BoardDTO boardDTO : boardList) {
//                System.out.println("전체 게시판 제목 확인: " + boardDTO.getBoardTitle());
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "게시판 정보를 가져오는 도중 오류가 발생했습니다.");
        }

        request.getRequestDispatcher("/WEB-INF/admin/post/postList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}