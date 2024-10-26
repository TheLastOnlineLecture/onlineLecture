package net.haebup.controller.qna;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dao.qna.QnaDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteQna.do")
public class DeleteQna extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qnaIdx = Integer.parseInt(request.getParameter("idx")); 
		QnaDAO qnaDAO = new QnaDAO();
        
        try {
        	qnaDAO.deleteQna(qnaIdx);
			request.setAttribute("qnaIdx", qnaIdx);
			request.getRequestDispatcher("gotoQnaList.do").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
