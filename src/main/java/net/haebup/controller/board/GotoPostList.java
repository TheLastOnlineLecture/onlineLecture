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

@WebServlet("/gotoPostList.do")
public class GotoPostList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        String pageSizeParam = request.getParameter("pageSize");

        int pageNo = pageNoParam != null ? Integer.parseInt(pageNoParam) : 1;      
        int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10; 

        BoardDAO boardDAO = new BoardDAO();
        String boardType = "P";  

        try {
            int totalCount = boardDAO.getTotalCount(boardType);
            List<BoardDTO> boardList = boardDAO.getBoardListByPage(pageNo, pageSize, boardType);
            
            // 10 -> blockSize 
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount, 10);

            request.setAttribute("boardList", boardList);         
            request.setAttribute("pagination", pagination);       
            
//            System.out.println("Pagination Object: " + pagination);
            request.getRequestDispatcher("WEB-INF/common/post/list.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
