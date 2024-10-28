package net.haebup.controller.qna;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dao.qna.QnaDAO;
import net.haebup.dto.board.BoardDTO;
import net.haebup.dto.qna.QnaDTO;
import net.haebup.utils.PaginationUtil.Pagination;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/gotoQnaList.do")
public class gotoQnaList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String pageNoParam = request.getParameter("pageNo");
	        String pageSizeParam = request.getParameter("pageSize");
	        String qnaType = request.getParameter("type");  
	        String qnaCategory = request.getParameter("category");
	        
	        //확인용 추후 삭제
//	        qnaCategory = "MATH003";
	        String searchType = request.getParameter("searchType");   
	        String searchKeyword = request.getParameter("searchKeyword"); 

	        int pageNo = pageNoParam != null ? Integer.parseInt(pageNoParam) : 1;      
	        int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10; 

	        QnaDAO qnaDAO = new QnaDAO();

	        try {
	            int totalCount;
	            List<QnaDTO> qnaList;

	            // 검색 조건이 있을 때와 없을 때를 구분
	            if (searchKeyword != null && !searchKeyword.isEmpty()) {
	                totalCount = qnaDAO.getTotalCount(qnaType, qnaCategory, searchType, searchKeyword);
	                qnaList = qnaDAO.selectQnaListByType(pageSize, (pageNo - 1) * pageSize, qnaType, qnaCategory, searchType, searchKeyword);
	            } else {
	                totalCount = qnaDAO.getTotalCount(qnaType, qnaCategory, null, null);
	                qnaList = qnaDAO.selectQnaListByType(pageSize, (pageNo - 1) * pageSize, qnaType, qnaCategory, null, null);
	            }

	            System.out.println("=== QnA List Data ===");
	            for (QnaDTO qna : qnaList) {
	                System.out.println("idx: " + qna.getQnaIdx());
	                System.out.println("Title: " + qna.getQnaTitle());
	                System.out.println("Content: " + qna.getQnaContent());
	                System.out.println("Writer: " + qna.getQnaWriter());
	                System.out.println("Regdate: " + qna.getQnaRegdate());
	            }
	            System.out.println("====================");
	            
	            Pagination pagination = new Pagination(pageNo, pageSize, totalCount, 10);

	            request.setAttribute("qnaType", qnaType);
	            request.setAttribute("qnaCategory", qnaCategory);
	            request.setAttribute("qnaList", qnaList);         
	            request.setAttribute("pagination", pagination);       
	            
	            // G : 일반 QnA, T : 선생님 QnA
	            if ("G".equals(qnaType)) {
	                request.getRequestDispatcher("WEB-INF/common/inquiy/list.jsp").forward(request, response);
	            } else if ("T".equals(qnaType)) {
	                if (qnaCategory != null && !qnaCategory.isEmpty()) {
	                    request.getRequestDispatcher("WEB-INF/teacher/qna/qnaList.jsp").forward(request, response);
	                } else {
	                    request.getRequestDispatcher("WEB-INF/common/lecture/qnaList.jsp").forward(request, response);
	                }
	            }

	            System.out.println("Pagination: " + pagination);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
