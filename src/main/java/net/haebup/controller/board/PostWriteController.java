package net.haebup.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dao.board.file.FileDAO;
import net.haebup.dto.board.BoardDTO;
import net.haebup.dto.board.file.FileDTO;
import net.haebup.dto.member.MemberDTO;
import net.haebup.utils.fileUtil.FileIOUtil;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/postWrite.do")
@MultipartConfig(
	    maxFileSize = 1024 * 1024 * 1,  // 1MB
	    maxRequestSize = 1024 * 1024 * 10) // 10MB
public class PostWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostWriteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		MemberDTO user = (MemberDTO) request.getSession().getAttribute("user");
		String userType = user.getUserType();
		System.out.println("userType + " +  userType);
		// String userType = "A";
		
		String boardType = request.getParameter("type");
//		System.out.println("타입확인 : "+boardType);
		
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardType(boardType);  
        boardDTO.setBoardTitle(request.getParameter("boardTitle"));
        boardDTO.setBoardContent(request.getParameter("boardContent"));
        boardDTO.setBoardWriter(request.getParameter("boardWriter"));

        // 게시글 작성 권한 확인
//        if (!checkUser(userType, boardType)) {
//            request.setAttribute("msg", "해당 게시판에 작성 권한이 없습니다.");
//            request.setAttribute("url", "javascript:history.back();"); 
//            request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);
//            return;
//        }

        BoardDAO boardDAO = new BoardDAO();
        int boardIdx = 0;

        try {
            boardIdx = boardDAO.insertBoard(boardDTO);
            
            if (boardIdx > 0) { 
                Part filePart = request.getPart("attachedFile"); 
                if (filePart != null && filePart.getSize() > 0) {
                    String filePath = FileIOUtil.uploadBoardAttachment(request, "attachedFile");

                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setBoardIdx(boardIdx); 
                    fileDTO.setFileName(filePart.getSubmittedFileName());
                    fileDTO.setFilePath(filePath);
                    fileDTO.setFileSize((int) filePart.getSize());

                    FileDAO fileDAO = new FileDAO();
                    fileDAO.insertFile(fileDTO);
                    
                }
                request.setAttribute("type", boardType);
                request.setAttribute("msg", "게시글 작성 성공했습니다.");
 
                request.setAttribute("url", "/gotoPostList.do?type="+boardType); 
                request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);
            } else {
            	 request.setAttribute("msg", "게시글이 작성되지 않았습니다.");
                 request.setAttribute("url", "javascript:history.back();"); 
                 request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("<script>alert('오류 발생: " + e.getMessage() + "'); location.href='javascript:history.back();';</script>");
        }
	}
	
//	private boolean checkUser(String userType, String boardType) {
//	    // 관리자 A: N 공지사항, D 자료실, C 강의공지, P 자유게시판
//	    if ("A".equals(userType)) {
//	        return "N".equals(boardType) || "D".equals(boardType) || "C".equals(boardType) || "P".equals(boardType);
//	    } 
//	    // 선생님 T: D 자료실, C 강의공지, P 자유게시판
//	    else if ("T".equals(userType)) {
//	        return "D".equals(boardType) || "C".equals(boardType) || "P".equals(boardType);
//	    }
//	    // 일반 학생: P 자유게시판, R 수강후기
//	    else {
//	        return "P".equals(boardType) || "R".equals(boardType);
//	    }
//	}
}
