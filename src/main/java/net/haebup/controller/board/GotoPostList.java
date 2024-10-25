package net.haebup.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dto.board.BoardDTO;
import net.haebup.utils.PaginationUtil.Pagination;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/gotoPostList.do")
public class GotoPostList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        String pageSizeParam = request.getParameter("pageSize");
        String boardType = request.getParameter("type");  

        int pageNo = pageNoParam != null ? Integer.parseInt(pageNoParam) : 1;      
        int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10; 

        BoardDAO boardDAO = new BoardDAO();

        try {
            int totalCount = boardDAO.getTotalCount(boardType);
            List<BoardDTO> boardList = boardDAO.getBoardListByPage(pageNo, pageSize, boardType);
            
            // 10 -> blockSize 
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount, 10);

            request.setAttribute("boardType", boardType);
            request.setAttribute("boardList", boardList);         
            request.setAttribute("pagination", pagination);       
            
            
            // P 자유게시판 D 자료실 N 공지사항 C 강의공지 R 수강후기
            switch(boardType) {
            	case "P" :  
            		request.getRequestDispatcher("WEB-INF/common/post/list.jsp").forward(request, response);
            		break;
            	case "D" :  
            		request.getRequestDispatcher("WEB-INF/common/filePost/list.jsp").forward(request, response);
            		break;
            	case "N" :  
            		request.getRequestDispatcher("WEB-INF/common/noticePost/noticeList.jsp").forward(request, response);
            		break;
            		
            		
            	// 강의 코드 필요함
            	case "C" :  
            		request.getRequestDispatcher("WEB-INF/common/post/list.jsp").forward(request, response);
            		break;
            	case "R" :  
            		request.getRequestDispatcher("WEB-INF/common/post/list.jsp").forward(request, response);
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
