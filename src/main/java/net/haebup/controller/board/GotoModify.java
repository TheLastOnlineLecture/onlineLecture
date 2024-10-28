package net.haebup.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dto.board.BoardDTO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/gotoPostModify.do")
public class GotoModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardIdx = Integer.parseInt(request.getParameter("idx")); 
        BoardDAO boardDAO = new BoardDAO();
        
        try {
            BoardDTO boardDTO = boardDAO.getBoardDetail(boardIdx); 
            
            if (boardDTO != null) {
            	request.setAttribute("boardDTO", boardDTO); 
                request.getRequestDispatcher("/WEB-INF/common/post/modify.jsp").forward(request, response);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
