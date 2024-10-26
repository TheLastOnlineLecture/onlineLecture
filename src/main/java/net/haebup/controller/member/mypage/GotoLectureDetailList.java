package net.haebup.controller.member.mypage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import net.haebup.dto.member.MemberDTO;
import net.haebup.dao.member.payment.PaymentDAO;
import java.sql.SQLException;
import net.haebup.dao.lecture.LectureDAO;
import net.haebup.dto.lecture.LectureDTO;

@WebServlet("/mypage/common/gotoLectureDetailList.do")
public class GotoLectureDetailList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("user");
        if(memberDTO == null){
            request.setAttribute("message", "로그인 후 이용해주세요.");
            request.getRequestDispatcher("/WEB-INF/user/login/login.jsp").forward(request, response);
            return;
        }
        String lectureCode = request.getParameter("lectureCode");
        PaymentDAO paymentDAO = new PaymentDAO();
        LectureDAO lectureDAO = new LectureDAO();
        LectureDTO lectureDTO = null;
        boolean isPaid = false;
        try {
            isPaid = paymentDAO.isPaid(memberDTO.getUserId(), lectureCode);
            if(isPaid){
                paymentDAO.updateLectureStartDate(memberDTO.getUserId(), lectureCode);
                lectureDTO = lectureDAO.getLectureDetail(lectureCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("lectureDTO", lectureDTO);
        request.getRequestDispatcher("/WEB-INF/common/myPage/lectureDetailList.jsp").forward(request, response);
    }
}
