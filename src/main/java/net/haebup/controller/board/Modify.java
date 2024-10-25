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

@WebServlet("/modifyPost.do")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		int boardIdx = Integer.parseInt(request.getParameter("idx"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardIdx(boardIdx);
        boardDTO.setBoardTitle(title);
        boardDTO.setBoardContent(content);

        BoardDAO boardDAO = new BoardDAO();

	        try {
	            boardDAO.updateBoard(boardDTO); 
	            response.sendRedirect("gotoDetail.do?idx=" + boardIdx); 
	        } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
