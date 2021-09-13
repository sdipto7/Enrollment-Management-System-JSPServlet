package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.dao.EnrollmentDao;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.service.EnrollmentService;
import net.therap.enrollmentmanagement.service.UserService;

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

    private EnrollmentService enrollmentService;

    public EnrollmentServlet() {
        enrollmentService = new EnrollmentService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (Objects.nonNull(session.getAttribute("userName"))) {
            String uri = request.getServletPath();

            switch (uri) {
                case "/viewEnrollmentButton":
                    viewAllEnrollment(request, response);
                    break;

                case "/addEnrollmentButton":
                    session.setAttribute("action", "add");
                    response.sendRedirect("save_enrollment.jsp");
                    break;

                case "/addEnrollment":
                    addEnrollment(request, response);
                    break;

                case "/deleteEnrollment":
                    deleteEnrollment(request, response);
                    break;

                case "/updateEnrollmentLink":
                    long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
                    session.setAttribute("action", "update");
                    session.setAttribute("enrollmentId", enrollmentId);
                    response.sendRedirect("save_enrollment.jsp");
                    break;

                case "/updateEnrollment":
                    updateEnrollment(request, response);
                    break;

                default:
                    break;
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void viewAllEnrollment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Enrollment> enrollmentList = enrollmentService.findAll();

        HttpSession session = request.getSession();
        session.setAttribute("enrollment_list", enrollmentList);
        response.sendRedirect("view_enrollment.jsp");
    }

    public void addEnrollment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String courseCode = request.getParameter("courseCode");

        UserService userService = new UserService();
        User user = userService.findByName(name);
        CourseService courseService = new CourseService();
        Course course = courseService.findByCourseCode(courseCode);

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);

        enrollmentService.saveOrUpdate(enrollment);
        response.sendRedirect("view_enrollment.jsp");
    }

    public void updateEnrollment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
        String name = request.getParameter("name");
        String courseCode = request.getParameter("courseCode");

        UserService userService = new UserService();
        User user = userService.findByName(name);
        CourseService courseService = new CourseService();
        Course course = courseService.findByCourseCode(courseCode);
        
        Enrollment enrollment = enrollmentService.find(enrollmentId);
        enrollment.setUser(user);
        enrollment.setCourse(course);

        enrollmentService.saveOrUpdate(enrollment);
        response.sendRedirect("view_enrollment.jsp");
    }

    public void deleteEnrollment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));

        EnrollmentDao enrollmentDao = new EnrollmentDao();
        enrollmentDao.delete(enrollmentId);
        response.sendRedirect("view_enrollment.jsp");
    }
}
