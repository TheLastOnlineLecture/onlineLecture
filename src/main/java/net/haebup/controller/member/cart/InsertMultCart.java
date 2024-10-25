package net.haebup.controller.member.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.sql.SQLException;
import net.haebup.dao.member.payment.PaymentDAO;
import net.haebup.dto.member.MemberDTO;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/lecture/user/insertMultCart.do")
public class InsertMultCart extends HttpServlet {
    private PaymentDAO paymentDAO = new PaymentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDTO user = (MemberDTO) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/member/login.do");
            return;
        }
        String userId = user.getUserId();

        String[] lectureCodes = request.getParameterValues("selectedLectures");
        if (lectureCodes == null || lectureCodes.length == 0) {
            request.setAttribute("message", "선택된 강의가 없습니다.");
            request.getRequestDispatcher("/lecture/common/lectureList.do").forward(request, response);
            return;
        }

        List<String> lectureCodeList = Arrays.asList(lectureCodes);

        try {
            int addedCount = paymentDAO.addMultipleToCart(userId, lectureCodeList);
            if (addedCount > 0) {
                request.setAttribute("message", addedCount + "개의 강의가 장바구니에 추가되었습니다.");
            } else {
                request.setAttribute("message", "장바구니 추가에 실패했습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "데이터베이스 오류가 발생했습니다.");
        }

        request.getRequestDispatcher("/lecture/common/lectureList.do").forward(request, response);
    }
}
