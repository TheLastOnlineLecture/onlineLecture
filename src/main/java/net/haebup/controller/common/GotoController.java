package net.haebup.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dto.member.MemberDTO;
import net.haebup.dao.member.MemberDAO;
import java.sql.SQLException;

import java.io.IOException;
// import java.io.PrintWriter;

@WebServlet("/goto.do")
public class GotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// @Override
	// public void init() throws ServletException {
	// 	MemberDTO memberDto = new MemberDTO();
	// }
	// 페이지 이동
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// String pageName= req.getParameter("page_name");
		// String pathName = req.getParameter("path_name");
		
		// String path = "WEB-INF/" + pathName + "/" + pageName + ".jsp";
		// req.getRequestDispatcher(path).forward(req, res);

		String pageName = req.getParameter("page");
		String type = req.getParameter("type");  
		System.out.println("goto"+pageName);
		
		switch(pageName){
			case "register":
				req.getRequestDispatcher("/WEB-INF/common/member/register.jsp").forward(req, res);
				break;
			case "registerSelect":
				req.getRequestDispatcher("/WEB-INF/common/member/registerSelectPage.jsp").forward(req, res);
				break;
			case "modify":
				MemberDTO memberDto = (MemberDTO) req.getSession().getAttribute("user");
				if (memberDto == null) {
					req.setAttribute("error", "로그인 후 이용해주세요.");
					req.getRequestDispatcher("/goto.do?page=login").forward(req, res);
					return;
				}else{
					try {
						memberDto = new MemberDAO().getUserInfo(memberDto.getUserId());
						req.setAttribute("modifyUser", memberDto);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					req.getRequestDispatcher("/WEB-INF/common/member/modify.jsp").forward(req, res);
				}
				break;
			case "mypage":
				req.getRequestDispatcher("/WEB-INF/common/myPage/myPage.jsp").forward(req, res);
				break;
			case "user/qna/write":
				req.setAttribute("qnaType", type);
				req.getRequestDispatcher("/WEB-INF/common/inquiy/write.jsp").forward(req, res);
				break;
			case "post/write":
				req.setAttribute("boardType", type);
				req.getRequestDispatcher("/WEB-INF/common/post/write.jsp").forward(req, res);
				break;
			case "admin/notice/write":
				req.getRequestDispatcher("/WEB-INF/admin/notice/write.jsp").forward(req, res);
				break;
			case "admin/filePost/write":
				req.getRequestDispatcher("/WEB-INF/admin/filePost/write.jsp").forward(req, res);
				break;
			case "admin/login":
				req.getRequestDispatcher("/WEB-INF/admin/adminLogin.jsp").forward(req, res);
				break;
			default:
				req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, res);
				break;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}