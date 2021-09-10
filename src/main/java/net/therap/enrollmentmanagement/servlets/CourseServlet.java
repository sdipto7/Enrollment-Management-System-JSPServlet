package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.dao.CourseDao;
import net.therap.enrollmentmanagement.domain.Course;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
public class CourseServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        if (Objects.isNull(session.getAttribute("userName"))) {
            response.sendRedirect("index.jsp");
        } else {
            CourseDao courseDao = new CourseDao();
            List<Course> courseList = courseDao.findAll();
            session.setAttribute("course_list", courseList);
            response.sendRedirect("view_courses.jsp");
        }
    }
}
