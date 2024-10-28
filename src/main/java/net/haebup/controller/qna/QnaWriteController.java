
package net.haebup.controller.qna;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import net.haebup.dao.board.BoardDAO;
import net.haebup.dao.board.file.FileDAO;
import net.haebup.dao.qna.QnaDAO;
import net.haebup.dto.board.BoardDTO;
import net.haebup.dto.board.file.FileDTO;
import net.haebup.dto.member.MemberDTO;
import net.haebup.dto.qna.QnaDTO;
import net.haebup.utils.fileUtil.FileIOUtil;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/qnaWrite.do")
public class QnaWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		MemberDTO user = (MemberDTO) request.getSession().getAttribute("user");
		String userType = user.getUserType();
		String userId = user.getUserId();
//		String userType = "A";
//		
		String qnaType = request.getParameter("type");
//		System.out.println("타입확인 : "+qnaType);
		
		QnaDTO qnaDTO = new QnaDTO();
		qnaDTO.setQnaType(qnaType);  
		System.out.println("qna 게시글 type"+qnaType);
		System.out.println("dsd"+request.getParameter("qnaTitle"));
		qnaDTO.setQnaTitle(request.getParameter("qnaTitle"));
		qnaDTO.setQnaContent(request.getParameter("qnaContent"));
		qnaDTO.setQnaWriter(request.getParameter("qnaWriter"));

		System.out.println(request.getParameter("qnaContent"));
        // 게시글 작성 권한 확인
//        if (!checkUser(userType, boardType)) {
//            response.getWriter().print("<script>alert('해당 게시판에 작성 권한이 없습니다.'); location.href='javascript:history.back();';</script>");
//            return;
//        }

        QnaDAO qndDAO = new QnaDAO();
        int result = 0;
        try {
        	result = qndDAO.insertQna(qnaDTO);
        	
        	if(result>0) {
        		request.setAttribute("msg", "qna 등록 성공.");
                request.setAttribute("url", "/gotoQnaList.do?type=" + qnaType);
                request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);
        	}else {
        		request.setAttribute("msg", "qna 등록실패");
                request.setAttribute("url", "/gotoQnaList.do?type=" + qnaType);
                request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("<script>alert('오류 발생: " + e.getMessage() + "'); location.href='javascript:history.back();';</script>");
        }
	}
	
//	private boolean checkUser(String userType, String boardType) {
//        // 관리자A 선생님T : N 공지사항, D 자료실, C 강의공지, P 자유게시판
//        if ("A".equals(userType) || "T".equals(userType)) {
//            return "N".equals(boardType) || "D".equals(boardType) || "C".equals(boardType) || "P".equals(boardType);
//        }
//        // 일반 학생 P자유게시판 R수강후기
//        else {
//            return "P".equals(boardType) || "R".equals(boardType);
//        }
//    }
}
