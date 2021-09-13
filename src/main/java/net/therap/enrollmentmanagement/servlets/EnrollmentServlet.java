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

    private UserService userService;

    private CourseService courseService;

    public EnrollmentServlet() {
        enrollmentService = new EnrollmentService();
        userService = new UserService();
        courseService = new CourseService();
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
                    response.sendRedirect("enrollment.jsp");
                    break;

                case "/addEnrollment":
                    add(request, response);
                    break;

                case "/deleteEnrollment":
                    delete(request, response);
                    break;

                case "/updateEnrollmentLink":
                    long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
                    session.setAttribute("action", "update");
                    session.setAttribute("enrollmentId", enrollmentId);
                    response.sendRedirect("enrollment.jsp");
                    break;

                case "/updateEnrollment":
                    update(request, response);
                    break;

                default:
                    break;
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public void viewAllEnrollment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Enrollment> enrollmentList = enrollmentService.findAll();

        HttpSession session = request.getSession();
        session.setAttribute("enrollment_list", enrollmentList);
        response.sendRedirect("enrollmentList.jsp");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String courseCode = request.getParameter("courseCode");

        User user = userService.findByName(name);
        Course course = courseService.findByCourseCode(courseCode);

        enrollmentService.saveOrUpdate(getEnrollment(0, user, course));
        response.sendRedirect("enrollmentList.jsp");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
        String name = request.getParameter("name");
        String courseCode = request.getParameter("courseCode");

        User user = userService.findByName(name);
        Course course = courseService.findByCourseCode(courseCode);

        enrollmentService.saveOrUpdate(getEnrollment(enrollmentId, user, course));
        response.sendRedirect("enrollmentList.jsp");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));

        EnrollmentDao enrollmentDao = new EnrollmentDao();
        enrollmentDao.delete(enrollmentId);
        response.sendRedirect("enrollmentList.jsp");
    }

    public Enrollment getEnrollment(long enrollmentId, User user, Course course) {
        Enrollment enrollment;
        if (enrollmentId > 0) {
            enrollment = enrollmentService.find(enrollmentId);
        } else {
            enrollment = new Enrollment();
        }
        enrollment.setUser(user);
        enrollment.setCourse(course);

        return enrollment;
    }
}
