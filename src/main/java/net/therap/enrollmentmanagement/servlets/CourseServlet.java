package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.util.SessionUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
public class CourseServlet extends HttpServlet {

    private CourseService courseService;

    public CourseServlet() {
        courseService = new CourseService();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loggedInUser = SessionUtil.getLoggedInUser(request);

        if (Objects.isNull(loggedInUser)) {
            response.sendRedirect("login.jsp");
        } else {
            HttpSession session = request.getSession();
            Action action = Action.getAction(request.getParameter("action"));
            switch (action) {
                case VIEWLIST:
                    viewAllCourse(request, response);
                    break;

                case EDIT:
                    long courseId = Long.parseLong(request.getParameter("courseId"));
                    if (courseId == 0) {
                        session.setAttribute("action", "add");
                    } else {
                        session.setAttribute("action", "update");
                        session.setAttribute("courseId", courseId);
                    }
                    response.sendRedirect("course.jsp");
                    break;

                case DELETE:
                    delete(request, response);
                    break;

                default:
                    break;
            }
        }
    }

    public void viewAllCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Course> courseList = courseService.findAll();

        HttpSession session = request.getSession();
        session.setAttribute("courseList", courseList);
        response.sendRedirect("courseList.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String courseCode = request.getParameter("courseCode");
        String courseTitle = request.getParameter("courseTitle");

        courseService.saveOrUpdate(getOrCreateCourse(0, courseCode, courseTitle));
        viewAllCourse(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long courseId = Long.parseLong(request.getParameter("courseId"));
        String courseCode = request.getParameter("courseCode");
        String courseTitle = request.getParameter("courseTitle");

        courseService.saveOrUpdate(getOrCreateCourse(courseId, courseCode, courseTitle));
        viewAllCourse(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long courseId = Long.parseLong(request.getParameter("courseId"));

        courseService.delete(courseId);
        viewAllCourse(request, response);
    }

    public Course getOrCreateCourse(long courseId, String courseCode, String courseTitle) {
        Course course;
        if (courseId > 0) {
            course = courseService.find(courseId);
        } else {
            course = new Course();
        }
        course.setCourseCode(courseCode);
        course.setCourseTitle(courseTitle);

        return course;
    }
}