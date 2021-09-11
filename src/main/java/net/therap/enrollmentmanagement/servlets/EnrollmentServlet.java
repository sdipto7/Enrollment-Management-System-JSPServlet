package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.dao.CourseDao;
import net.therap.enrollmentmanagement.dao.EnrollmentDao;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
public class EnrollmentServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (Objects.isNull(session.getAttribute("userName"))) {
            response.sendRedirect("index.jsp");
        } else {
            EnrollmentDao enrollmentDao = new EnrollmentDao();
            List<Enrollment> enrollmentList = enrollmentDao.findAll();
            session.setAttribute("enrollment_list", enrollmentList);
            response.sendRedirect("view_enrollment.jsp");
        }
    }
}
