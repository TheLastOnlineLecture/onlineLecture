package net.haebup.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

 @WebServlet("/goto.do")
public class gotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 페이지 이동
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// String pageName= req.getParameter("page_name");
		// String pathName = req.getParameter("path_name");
		
		// String path = "WEB-INF/" + pathName + "/" + pageName + ".jsp";
		// req.getRequestDispatcher(path).forward(req, res);
		
		String pageName = req.getParameter("page");
		String boardType = req.getParameter("type");  
		System.out.println("goto"+pageName);
		switch(pageName){
			case "login":
				req.getRequestDispatcher("/WEB-INF/common/member/login.jsp").forward(req, res);
				break;
			case "register":
				req.getRequestDispatcher("/WEB-INF/common/member/register.jsp").forward(req, res);
				break;
			case "registerSelect":
				req.getRequestDispatcher("/WEB-INF/common/member/registerSelectPage.jsp").forward(req, res);
				break;
			case "modify":
				req.getRequestDispatcher("/WEB-INF/common/member/modify.jsp").forward(req, res);
				break;
			case "mypage":
				req.getRequestDispatcher("/WEB-INF/common/myPage/myPage.jsp").forward(req, res);
				break;
			case "user/qna/write":
				req.setAttribute("boardType", boardType);
				req.getRequestDispatcher("/WEB-INF/common/qna/write.jsp").forward(req, res);
				break;
			case "post/write":
				req.setAttribute("boardType", boardType);
				req.getRequestDispatcher("/WEB-INF/common/post/write.jsp").forward(req, res);
				break;
			case "admin/notice/write":
				req.getRequestDispatcher("/WEB-INF/admin/notice/write.jsp").forward(req, res);
				break;
			case "admin/filePost/write":
				req.getRequestDispatcher("/WEB-INF/admin/filePost/write.jsp").forward(req, res);
				break;
			default:
				req.getRequestDispatcher("/WEB-INF/common/index.jsp").forward(req, res);
				break;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}