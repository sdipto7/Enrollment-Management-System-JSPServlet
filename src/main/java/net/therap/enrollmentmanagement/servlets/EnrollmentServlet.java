package net.therap.enrollmentmanagement.servlets;

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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                save(request, response);
                response.sendRedirect("enrollmentList.jsp");
                break;

            case "update":
                update(request, response);
                response.sendRedirect("enrollmentList.jsp");
                break;

            default:
                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (Objects.nonNull(session.getAttribute("currentUser"))) {
            String action = request.getParameter("action");

            switch (action) {
                case "enrollmentList":
                    viewAllEnrollment(request, response);
                    response.sendRedirect("enrollmentList.jsp");
                    break;

                case "addClick":
                    session.setAttribute("action", "add");
                    response.sendRedirect("enrollment.jsp");
                    break;

                case "delete":
                    delete(request, response);
                    response.sendRedirect("enrollmentList.jsp");
                    break;

                case "updateClick":
                    long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
                    session.setAttribute("action", "update");
                    session.setAttribute("enrollmentId", enrollmentId);
                    response.sendRedirect("enrollment.jsp");
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
        session.setAttribute("enrollmentList", enrollmentList);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("name");
        String courseCode = request.getParameter("courseCode");

        enrollmentService.saveOrUpdate(getOrCreateEnrollment(0, userName, courseCode));
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
        String userName = request.getParameter("name");
        String courseCode = request.getParameter("courseCode");

        enrollmentService.saveOrUpdate(getOrCreateEnrollment(enrollmentId, userName, courseCode));
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));

        enrollmentService.delete(enrollmentId);
    }

    public Enrollment getOrCreateEnrollment(long enrollmentId, String userName, String courseCode) {
        Enrollment enrollment;
        if (enrollmentId > 0) {
            enrollment = enrollmentService.find(enrollmentId);
        } else {
            enrollment = new Enrollment();
        }
        User user = userService.findByName(userName);
        Course course = courseService.findByCourseCode(courseCode);

        enrollment.setUser(user);
        enrollment.setCourse(course);

        return enrollment;
    }
}
