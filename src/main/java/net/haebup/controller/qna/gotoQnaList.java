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

        int pageNo = pageNoParam != null ? Integer.parseInt(pageNoParam) : 1;      
        int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10; 

        QnaDAO qnaDAO = new QnaDAO();

        try {
            int totalCount = qnaDAO.getTotalCount(qnaType);
            List<QnaDTO> qnaList = qnaDAO.selectQnaListByType(pageNo, pageSize, qnaType);
            
            // 10 -> blockSize 
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount, 10);

            request.setAttribute("qnaType", qnaType);
            request.setAttribute("qnaList", qnaList);         
            request.setAttribute("pagination", pagination);       
            
            
            // P 자유게시판 D 자료실 N 공지사항 C 강의공지 R 수강후기
            switch(qnaType) {
            	case "G" :  
            		request.getRequestDispatcher("WEB-INF/common/post/list.jsp").forward(request, response);
            		break;
            	case "T" :  
            		request.getRequestDispatcher("WEB-INF/common/filePost/list.jsp").forward(request, response);
            		break;
            }
//            System.out.println("Pagination Object: " + pagination);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
