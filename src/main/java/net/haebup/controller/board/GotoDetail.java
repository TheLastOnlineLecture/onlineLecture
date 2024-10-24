package net.haebup.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dao.board.comment.CommentDAO;
import net.haebup.dto.board.BoardDTO;
import net.haebup.dto.board.comment.BoardCommentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/gotoPostDetail.do")
public class GotoDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardIdx = Integer.parseInt(request.getParameter("idx"));
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = null;

		try {
		    boardDTO = boardDAO.getBoardDetail(boardIdx);
		    if (boardDTO != null) {
		        request.setAttribute("boardDTO", boardDTO);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		 CommentDAO commentDAO = new CommentDAO();
	        try {
	            // 댓글 목록 조회
	            List<BoardCommentDTO> commentList = commentDAO.selectCommentList(boardIdx);
	            // 출력 값 확인
	            for (BoardCommentDTO comment : commentList) {
	            	System.out.println("----------"); 
	                System.out.println("Comment Index: " + comment.getCommentIdx());
	                System.out.println("Board Index: " + comment.getPostIdx());
	                System.out.println("Comment Content: " + comment.getCommentContent());
	                System.out.println("Comment Registration Date: " + comment.getCommentRegdate());
	                System.out.println("User ID: " + comment.getUserId());
	                System.out.println("----------"); 
	            }
	            
	            // 댓글 리스트를 요청에 추가
	            request.setAttribute("commentList", commentList);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		request.getRequestDispatcher("WEB-INF/common/post/detail.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
