package net.haebup.controller.qna.qnaComment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.comment.CommentDAO;
import net.haebup.dao.qna.qnaComment.QnaCommentDAO;
import net.haebup.dto.board.comment.BoardCommentDTO;
import net.haebup.dto.qna.qnaComment.QnaCommentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gotoQnaCommentList.do")
public class GotoQnaCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qnaIdx = Integer.parseInt(request.getParameter("qnaIdx"));
        QnaCommentDAO qnacommentDAO = new QnaCommentDAO();
        
        try {
            // 댓글 목록 조회
            List<QnaCommentDTO> commenQnatList = qnacommentDAO.selectQnaCommentList(qnaIdx);
            System.out.println("Fetched Comment List: " + commenQnatList); 
            
//            // 조회된 댓글이 있을 경우
//            if (commentList != null && !commentList.isEmpty()) {
//                for (BoardCommentDTO comment : commentList) {
//                    System.out.println("--------------GotocommentListk-------시작"+ 
//                    				 "+Comment ID: " + comment.getCommentIdx() +
//                                       ", Content: " + comment.getCommentContent() +
//                                       ", Registered Date: " + comment.getCommentRegdate() +
//                                       ", User ID: " + comment.getUserId());
//                }
//            } else {
//                System.out.println("No comments board index: " + postIdx);
//            }
            request.setAttribute("commenQnatList", commenQnatList); 
            request.getRequestDispatcher("/gotoPostDetail.do?idx=" + qnaIdx).forward(request, response); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
