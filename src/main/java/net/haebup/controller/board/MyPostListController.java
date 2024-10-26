package net.haebup.controller.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.MyPostDAO;
import net.haebup.dao.board.comment.MyCommentDAO;
import net.haebup.dto.board.MyPostDTO;
import net.haebup.dto.board.comment.MyCommentDTO;
import net.haebup.dto.member.MemberDTO;
import net.haebup.utils.PaginationUtil.Pagination;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/myStudyRoom/common/gotoWriteDetail.do")
public class MyPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	 MemberDTO user = (MemberDTO) request.getSession().getAttribute("user");
//	 String writer = user.getUserId();
	// 임의 지정한 값, 추후 수정
	 String writer = "student1"; 

     // 게시글 관련 파라미터
     String postPageNoParam = request.getParameter("postPageNo");
     String postPageSizeParam = request.getParameter("postPageSize");
     int postPageNo = postPageNoParam != null ? Integer.parseInt(postPageNoParam) : 1;
     int postPageSize = postPageSizeParam != null ? Integer.parseInt(postPageSizeParam) : 5;

     // 댓글 관련 파라미터
     String commentPageNoParam = request.getParameter("commentPageNo");
     String commentPageSizeParam = request.getParameter("commentPageSize");
     int commentPageNo = commentPageNoParam != null ? Integer.parseInt(commentPageNoParam) : 1;
     int commentPageSize = commentPageSizeParam != null ? Integer.parseInt(commentPageSizeParam) : 5;

     try {
         MyPostDAO postDAO = new MyPostDAO();
         MyCommentDAO commentDAO = new MyCommentDAO();

         // 게시글 페이징
         int totalPostCount = postDAO.getTotalPost(writer, writer);
         List<MyPostDTO> postList = postDAO.getPostsByWriter(postPageSize, (postPageNo - 1) * postPageSize, writer, writer);
         Pagination postPagination = new Pagination(postPageNo, postPageSize, totalPostCount, 10);

         // 댓글 페이징
         int totalCommentCount = commentDAO.getTotalCommentsCount(writer);
         List<MyCommentDTO> commentList = commentDAO.getTotalComment(writer, commentPageNo, commentPageSize);
         Pagination commentPagination = new Pagination(commentPageNo, commentPageSize, totalCommentCount, 10);

         // 속성 설정
         request.setAttribute("postList", postList);
         request.setAttribute("postPagination", postPagination);
         request.setAttribute("commentList", commentList);
         request.setAttribute("commentPagination", commentPagination);
         request.setAttribute("writer", writer);

         request.getRequestDispatcher("/WEB-INF/common/myStudyRoom/writeList.jsp").forward(request, response);

     } catch (Exception e) {
         e.printStackTrace();
         response.getWriter().write("서버 오류가 발생했습니다.");
     }
 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
 }
}