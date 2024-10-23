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
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoardType("boardType");  
	    boardDTO.setBoardTitle("boardTitle");
	    boardDTO.setBoardContent("boardContent");
	    boardDTO.setBoardWriter(req.getParameter("userId"));
		    
		BoardDAO boardDAO = new BoardDAO();
		
		int row =0;
		try {
			row = boardDAO.insertBoard(boardDTO);
			
			
			if(row>0) {
				System.out.println("등록 성공");
				res.sendRedirect("/gotoList.do");
			}
			else {
				System.out.println("등록 실패");
				res.getWriter().print("<script>  <script>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	    

}
