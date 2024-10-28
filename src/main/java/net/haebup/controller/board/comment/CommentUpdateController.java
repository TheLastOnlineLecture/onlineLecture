package net.haebup.controller.board.comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.comment.CommentDAO;
import net.haebup.dto.board.comment.BoardCommentDTO;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/commentUpdate.do")
public class CommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int commentIdx = Integer.parseInt(request.getParameter("commentIdx"));
	        String updatedContent = request.getParameter("commentContent");
	        String userId = (String) request.getSession().getAttribute("userId"); 

	        CommentDAO commentDAO = new CommentDAO();
	        try {
	            BoardCommentDTO comment = commentDAO.selectCommentIdx(commentIdx); 
	            if (comment.getUserId().equals(userId)) { 
	                comment.setCommentContent(updatedContent);
	                commentDAO.updateComment(comment); 
	                request.setAttribute("msg", "댓글 수정 완료.");
	                request.setAttribute("url", "/gotoQnaDetail.do");
	                request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);
	            } else {
	            	request.setAttribute("msg", "댓글 수정 실패");
	                request.setAttribute("url", "/gotoQnaDetail.do");
	                request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 

}
