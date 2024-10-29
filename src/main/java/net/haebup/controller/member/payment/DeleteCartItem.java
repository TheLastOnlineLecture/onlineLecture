package net.haebup.controller.member.payment;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dao.member.payment.PaymentDAO;

@WebServlet("/payments/user/deleteCartItem.do")
public class DeleteCartItem extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        StringBuilder jsonResponse = new StringBuilder();
        
        try {
            int paymentIdx = Integer.parseInt(request.getParameter("paymentIdx"));
            PaymentDAO paymentDAO = new PaymentDAO();
            int result = paymentDAO.deleteCartItem(paymentIdx);
            
            jsonResponse.append("{");
            jsonResponse.append("\"success\":").append(result > 0).append(",");
            jsonResponse.append("\"message\":\"").append(result > 0 ? "삭제되었습니다." : "삭제에 실패했습니다.").append("\"");
            jsonResponse.append("}");
            
        } catch (Exception e) {
            jsonResponse.append("{");
            jsonResponse.append("\"success\":false,");
            jsonResponse.append("\"message\":\"오류가 발생했습니다: ").append(e.getMessage()).append("\"");
            jsonResponse.append("}");
        }
        
        response.getWriter().write(jsonResponse.toString());
    }
}