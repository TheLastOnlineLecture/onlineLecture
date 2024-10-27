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
        
        // 임의값 추후 삭제 
//        String qnaCategory = "KOR001";

        int pageNo = pageNoParam != null ? Integer.parseInt(pageNoParam) : 1;      
        int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10; 
        QnaDAO qnaDAO = new QnaDAO();

        try {
            int totalCount = qnaDAO.getTotalCount(qnaType);
            List<QnaDTO> qnaList = qnaDAO.selectQnaListByType(pageNo, pageSize, qnaType);
            System.out.println("=== QnA List Data ===");
            for (QnaDTO qna : qnaList) {
                System.out.println("idx: " + qna.getQnaIdx());
                System.out.println("Title: " + qna.getQnaTitle());
                System.out.println("Content: " + qna.getQnaContent());
                System.out.println("Writer: " + qna.getQnaWriter());
                System.out.println("Regdate: " + qna.getQnaRegdate());
                System.out.println("----------------------");
            }
            System.out.println("====================");
            
            // 10 -> blockSize 
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount, 10);

            request.setAttribute("qnaType", qnaType);
            request.setAttribute("qnaCategory", qnaCategory);
            request.setAttribute("qnaList", qnaList);         
            request.setAttribute("pagination", pagination);       
            
            
            // G 관리자 1:1 , T 선생님 Qna
            // T 선생님일때 강의코드 받기?
            if (qnaType.equals("G")) {
                request.getRequestDispatcher("WEB-INF/common/inquiy/list.jsp").forward(request, response);
            } else if (qnaType.equals("T")) {
                if (qnaCategory != null && !qnaCategory.isEmpty()) {
                    request.getRequestDispatcher("WEB-INF/teacher/qna/qnaList.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("WEB-INF/common/lecture/qnaList.jsp").forward(request, response);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
