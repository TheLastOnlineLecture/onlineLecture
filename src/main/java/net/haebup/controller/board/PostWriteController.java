package net.haebup.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dto.board.BoardDTO;

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
				System.out.println("등록 성공");
				res.sendRedirect("/gotoPostList.do");
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
