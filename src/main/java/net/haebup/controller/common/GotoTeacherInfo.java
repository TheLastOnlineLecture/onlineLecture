package net.haebup.controller.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.haebup.dto.member.MemberDTO;
import net.haebup.dao.member.MemberDAO;
import net.haebup.dto.lecture.LectureDTO;
import net.haebup.dao.lecture.LectureDAO;
import java.sql.SQLException;

import java.io.IOException;
import java.util.List;

@WebServlet("/gotoTeacherInfo.do")
public class GotoTeacherInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String teacherId = req.getParameter("teacherId");
        MemberDAO memberDao = new MemberDAO();
        LectureDAO lectureDao = new LectureDAO();
        try {
            MemberDTO memberDto = memberDao.getUserInfo(teacherId);
            List<LectureDTO> lectures = lectureDao.getLectureListByTeacherId(teacherId);
            req.setAttribute("teacherInfo", memberDto);
            req.setAttribute("lectureList", lectures);
            req.getRequestDispatcher("/WEB-INF/common/lecture/teacherInfo.jsp").forward(req, res);
        } catch (SQLException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터베이스 오류가 발생했습니다.");
        }
    }
}
