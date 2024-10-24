package net.haebup.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletePost.do")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardIdx = Integer.parseInt(request.getParameter("idx")); 
        BoardDAO boardDAO = new BoardDAO();
        
        try {
			boardDAO.deleteByBoardIdx(boardIdx);
			response.sendRedirect("gotoPostList.do");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
