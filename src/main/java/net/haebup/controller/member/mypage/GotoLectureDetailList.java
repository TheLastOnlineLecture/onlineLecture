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
import net.haebup.dto.lecture.lectureDetail.LectureDetailDTO;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mypage/common/gotoLectureDetailList.do")
public class GotoLectureDetailList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("user");
        if(memberDTO == null){
            request.setAttribute("message", "로그인 후 이용해주세요.");
            request.getRequestDispatcher("/goto.do?page=login").forward(request, response);
            return;
        }
        String[] lectureCodes = request.getParameterValues("lectureCode");
        PaymentDAO paymentDAO = new PaymentDAO();
        LectureDAO lectureDAO = new LectureDAO();
        List<LectureDTO> lectureDTOs = new ArrayList<>();
        try {
            for (String lectureCode : lectureCodes) {
                if (paymentDAO.isPaid(memberDTO.getUserId(), lectureCode)) {
                    paymentDAO.updateLectureStartDate(memberDTO.getUserId(), lectureCode);
                    LectureDTO lectureDTO = lectureDAO.getLectureDetail(lectureCode);
                    if (lectureDTO != null) {
                        List<LectureDetailDTO> details = lectureDAO.getLectureDetails(lectureCode);
                        lectureDTO.setLectureDetails(details);
                        lectureDTOs.add(lectureDTO);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("lectureDTOs", lectureDTOs);
        request.getRequestDispatcher("/WEB-INF/common/myPage/lectureDetailList.jsp").forward(request, response);
    }
}
