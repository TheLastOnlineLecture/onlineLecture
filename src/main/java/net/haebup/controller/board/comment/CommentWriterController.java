package net.haebup.controller.board.comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.comment.CommentDAO;
import net.haebup.dto.board.comment.BoardCommentDTO;
import net.haebup.dto.member.MemberDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/postCommentWrite.do")
public class CommentWriterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int postIdx = Integer.parseInt(request.getParameter("boardIdx"));
	    String commentContent = request.getParameter("commentContent");
//	    MemberDTO user = (MemberDTO) request.getSession().getAttribute("user");
//	    String userId = user.getUserId();    

	    System.out.println("postIdx: " + postIdx);
	    System.out.println("Comment Content: " + commentContent);
	    System.out.println("User ID: " + userId);

	    BoardCommentDTO commentDTO = new BoardCommentDTO();
	    commentDTO.setPostIdx(postIdx);
	    commentDTO.setCommentContent(commentContent);
	    commentDTO.setUserId(userId);

	    CommentDAO commentDAO = new CommentDAO();
	    try {
	        int result = commentDAO.insertComment(commentDTO); 
	        System.out.println("Insert Result: " + result); 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "댓글 등록 중 오류가 발생하였습니다.");
	    }

	    request.getRequestDispatcher("/gotoPostDetail.do?idx=" + postIdx).forward(request, response); 
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
