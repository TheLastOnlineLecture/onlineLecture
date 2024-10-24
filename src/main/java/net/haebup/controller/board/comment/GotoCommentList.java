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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gotoCommentList.do")
public class GotoCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postIdx = Integer.parseInt(request.getParameter("boardIdx"));
        CommentDAO commentDAO = new CommentDAO();
        
        try {
            // 댓글 목록 조회
            List<BoardCommentDTO> commentList = commentDAO.selectCommentList(postIdx);
            System.out.println("Fetched Comment List: " + commentList); 
            
            // 조회된 댓글이 있을 경우
            if (commentList != null && !commentList.isEmpty()) {
                for (BoardCommentDTO comment : commentList) {
                    System.out.println("Comment ID: " + comment.getCommentIdx() +
                                       ", Content: " + comment.getCommentContent() +
                                       ", Registered Date: " + comment.getCommentRegdate() +
                                       ", User ID: " + comment.getUserId());
                }
            } else {
                System.out.println("No comments found for board index: " + postIdx);
            }
            request.setAttribute("commentList", commentList); 
            request.getRequestDispatcher("/gotoPostDetail.do?idx=" + postIdx).forward(request, response); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
