package net.haebup.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dto.board.BoardDTO;
import net.haebup.dto.member.MemberDTO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/postWrite.do")
public class PostWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostWriteController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");  // POST 요청에서의 인코딩 설정
	    res.setContentType("text/html; charset=UTF-8");  // 응답 인코딩 설정
	    res.setCharacterEncoding("UTF-8");
		
		
//		MemberDTO user = (MemberDTO) req.getSession().getAttribute("user");
//		String userType = user.getUserType();
		String userType = "A";
		
		String boardType = req.getParameter("type");
//		System.out.println("타입확인 : "+boardType);
		
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoardType(boardType);  
	    boardDTO.setBoardTitle(req.getParameter("boardTitle"));
	    boardDTO.setBoardContent(req.getParameter("boardContent"));
	    boardDTO.setBoardWriter(req.getParameter("boardWriter"));
		    
		BoardDAO boardDAO = new BoardDAO();
		
		int row =0;
		try {
			row = boardDAO.insertBoard(boardDTO);
			if(row>0) {
				// P 자유게시판 D 자료실 N 공지사항 C 강의공지 R 수강후기
				// A 관리자 T 선생님 S01~~ 는 학생인데 걍 그외
				if ("A".equals(userType)||"T".equals(userType)) { 
				    if ("N".equals(boardType) || "D".equals(boardType) || "C".equals(boardType) || "P".equals(boardType)) {
				    	req.setAttribute("boardType", boardType);
				    	req.getRequestDispatcher("/gotoPostList.do").forward(req, res); 
				    } else {
				        res.getWriter().print("<script>alert('해당 게시판에 작성 권한이 없습니다.'); location.href='javascript:history.back();';</script>");
				        return; 
				    }
				    
				}
				// 선생님 권한 == 관리자 권한
//				else if ("T".equals(userType)) {
//				    if ("N".equals(boardType) || "D".equals(boardType) || "C".equals(boardType) || "P".equals(boardType)) {
//				    	res.sendRedirect("/gotoPostList.do");
//				    } else {
//				        res.getWriter().print("<script>alert('해당 게시판에 작성 권한이 없습니다.'); location.href='javascript:history.back();';</script>");
//				        return; 
//				    }
//				    
//				} 
				else {
				    if ("P".equals(boardType) || "R".equals(boardType)) {
				    	req.setAttribute("boardType", boardType);
				    	req.getRequestDispatcher("/gotoPostList.do").forward(req, res); 
				    } else {
				        res.getWriter().print("<script>alert('해당 게시판에 작성 권한이 없습니다.'); location.href='javascript:history.back();';</script>");
				        return; 
				    }
				}
			}
			else {
				System.out.println("등록 실패");
				res.getWriter().print("<script>alert('등록실패'); location.href='javascript:history.back();';</script>");
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
