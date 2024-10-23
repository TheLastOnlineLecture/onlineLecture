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
		
		String pageName= req.getParameter("page_name");
		String pathName = req.getParameter("path_name");
		
		String path = "/WEB-INF/"+ pathName + pageName+".jsp";
		
		req.getRequestDispatcher(path).forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
