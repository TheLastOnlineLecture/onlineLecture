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

@WebServlet("/deletePost.do")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardIdx = Integer.parseInt(request.getParameter("idx"));
        String boardType = request.getParameter("type");
        String boardCategory = request.getParameter("category"); 

        BoardDAO boardDAO = new BoardDAO();
        BoardDTO boardDTO = null; 
        String writerId = null; 

        try {
            // 게시글 정보 가져오기
            boardDTO = boardDAO.getBoardDetail(boardIdx);
            if (boardDTO == null) {
                response.getWriter().print("<script>alert('게시글을 찾을 수 없습니다.'); location.href='javascript:history.back();';</script>");
                return;
            }

            writerId = boardDTO.getBoardWriter(); 
            MemberDTO user = (MemberDTO) request.getSession().getAttribute("user");
            String loginId = user != null ? user.getUserId() : null; 

            // 삭제 권한 확인
            if (loginId == null || !writerId.equals(loginId)) {
                response.getWriter().print("<script>alert('삭제 권한이 없습니다.'); location.href='javascript:history.back();';</script>");
                return;
            }

            // 게시글 삭제
            int result = boardDAO.deleteByBoardIdx(boardIdx);
            String msg, url;

            if (result > 0) {
                msg = "게시글이 삭제되었습니다.";
                url = getRedirectUrl(request, boardType, boardCategory);
            } else {
                msg = "게시글 삭제에 실패했습니다.";
                url = "javascript:history.back();";
            }

            request.setAttribute("msg", msg);
            request.setAttribute("url", url); 
            request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg", "오류 발생: " + e.getMessage());
            request.setAttribute("url", "javascript:history.back();");
            request.getRequestDispatcher("/WEB-INF/common/commonArea/successAlert.jsp").forward(request, response);
        }
    }

    private String getRedirectUrl(HttpServletRequest request, String boardType, String boardCategory) {
        String url = "";
        switch (boardType) {
            case "P":
                url = request.getContextPath() + "/WEB-INF/common/post/list.jsp";
                break;
            case "D":
                url = request.getContextPath() + "/WEB-INF/common/lecture/fileList.jsp";
                break;
            case "N":
                url = request.getContextPath() + "/WEB-INF/common/noticePost/noticeList.jsp";
                break;
            case "C":
                url = request.getContextPath() + "/WEB-INF/common/lecture/lectureNoticeList.jsp";
                break;
            case "R":
                url = request.getContextPath() + "/WEB-INF/common/lecture/lectureReview.jsp";
                break;
            default:
                url = "javascript:history.back();";
                break;
        }
        // 카테고리 추가
        if (boardCategory != null) {
            url += "?category=" + boardCategory;
        }
        return url;
    }
	}