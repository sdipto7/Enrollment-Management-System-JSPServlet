package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.service.EnrollmentService;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User loggedInUser = SessionUtil.getLoggedInUser(request);

        if (Objects.isNull(loggedInUser)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Action action = Action.getAction(request.getParameter("action"));
            switch (action) {
                case ADD:
                    save(request, response);
                    break;

                case UPDATE:
                    update(request, response);
                    break;

                default:
                    break;
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User loggedInUser = SessionUtil.getLoggedInUser(request);

        if (Objects.isNull(loggedInUser)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Action action = Action.getAction(request.getParameter("action"));
            switch (action) {
                case VIEW:
                    showAll(request, response);
                    break;

                case EDIT:
                    long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
                    if (enrollmentId == 0) {
                        request.setAttribute("action", "add");
                    } else {
                        request.setAttribute("action", "update");
                        request.setAttribute("enrollment", enrollmentService.find(enrollmentId));
                        request.setAttribute("enrollmentId", enrollmentId);
                    }
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/enrollment.jsp");
                    requestDispatcher.forward(request, response);
                    break;

                case DELETE:
                    delete(request, response);
                    break;

                default:
                    break;
            }
        }
    }

    public void showAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("enrollmentList", enrollmentService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/enrollmentList.jsp");
        requestDispatcher.forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("name");
        String courseCode = request.getParameter("courseCode");

        enrollmentService.saveOrUpdate(getOrCreateEnrollment(0, userName, courseCode));
        showAll(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
        String userName = request.getParameter("name");
        String courseCode = request.getParameter("courseCode");

        enrollmentService.saveOrUpdate(getOrCreateEnrollment(enrollmentId, userName, courseCode));
        showAll(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));

        enrollmentService.delete(enrollmentId);
        showAll(request, response);
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
