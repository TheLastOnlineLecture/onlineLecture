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
        String boardCategory = request.getParameter("category");  
        
        // 값 확인용 추후 삭제
//        boardCategory = "SCI001";

        int pageNo = pageNoParam != null ? Integer.parseInt(pageNoParam) : 1;      
        int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10; 
        
     // 검색 유형 (title, writer, title+content)
        String searchType = request.getParameter("searchType");   
        String searchKeyword = request.getParameter("searchKeyword"); 

        BoardDAO boardDAO = new BoardDAO();

        try {
        	 int totalCount;
             List<BoardDTO> boardList;

             if (searchKeyword != null && !searchKeyword.isEmpty()) {
                 totalCount = boardDAO.getTotalCount(boardType, searchType, searchKeyword);
                 boardList = boardDAO.getSearchBoardList(pageSize, (pageNo - 1) * pageSize, boardType, boardCategory, searchType, searchKeyword);
             } else {
                 totalCount = boardDAO.getTotalCount(boardType, boardCategory);
                 boardList = boardDAO.getBoardListByPage(pageNo, pageSize, boardType, boardCategory);
             }
             

             System.out.println("========게시판 리스트 확인==========");
             for (BoardDTO boardDTO : boardList) {
                 System.out.println("idx : " + boardDTO.getBoardIdx());
                 System.out.println("title  : " + boardDTO.getBoardTitle());
                 System.out.println("writer : " + boardDTO.getBoardWriter());
                 System.out.println("reqdate : " + boardDTO.getBoardRegdate());
                 System.out.println("type : " + boardDTO.getBoardType());
                 System.out.println("category : " + boardDTO.getBoardCategory());
             }
             System.out.println("====================");

            // 10 -> blockSize 
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount, 10);

            request.setAttribute("boardType", boardType);
            request.setAttribute("boardList", boardList);         
            request.setAttribute("boardCategory", boardCategory);       
            request.setAttribute("pagination", pagination);       
            
            
            // P 자유게시판 D 자료실 N 공지사항 C 강의공지 R 수강후기
            // 자료업로드 되는거 P D
            // 안되는거 N C R
            switch(boardType) {
            	case "P" :  
            		request.getRequestDispatcher(request.getContextPath() +"WEB-INF/common/post/list.jsp").forward(request, response);
            		break;
            	case "D" :  
            		if(boardCategory != null) {
            			request.getRequestDispatcher(request.getContextPath() +"WEB-INF/common/lecture/fileList.jsp").forward(request, response);
            		} else {
            			request.getRequestDispatcher(request.getContextPath() +"WEB-INF/common/filePost/fileList.jsp").forward(request, response);
            		}
            		break;
            	case "N" :  
            		request.getRequestDispatcher(request.getContextPath() +"WEB-INF/common/noticePost/noticeList.jsp").forward(request, response);
            		break;
            	// 강의 코드 필요함
            	case "C" :  
            		if(boardCategory != null) {
            			request.getRequestDispatcher(request.getContextPath() +"WEB-INF/common/lecture/lectureNoticeList.jsp").forward(request, response);
            		}else {
            			request.getRequestDispatcher(request.getContextPath() +"WEB-INF/common/lecture/lectureNoticeList.jsp").forward(request, response);
            		}
            		break;
            	case "R" :  
            		if(boardCategory != null) {
            			request.getRequestDispatcher(request.getContextPath() +"WEB-INF/common/lecture/lectureReview.jsp").forward(request, response);
            		}else {
            			request.getRequestDispatcher(request.getContextPath() +"WEB-INF/common/lecture/lectureReview.jsp").forward(request, response);
            		}
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
