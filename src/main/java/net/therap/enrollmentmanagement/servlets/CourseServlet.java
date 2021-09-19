package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
public class CourseServlet extends HttpServlet {

    private CourseService courseService;

    public CourseServlet() {
        courseService = new CourseService();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (SessionUtil.checkValidLogin(request, response)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        Action action = Action.getAction(request.getParameter("action"));
        switch (action) {
            case SAVE:
                save(request, response);
                break;

            case UPDATE:
                update(request, response);
                break;

            default:
                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (SessionUtil.checkValidLogin(request, response)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        Action action = Action.getAction(request.getParameter("action"));
        switch (action) {
            case VIEW:
                showAll(request, response);
                break;

            case EDIT:
                edit(request, response);
                break;

            case DELETE:
                delete(request, response);
                break;

            default:
                break;
        }
    }

    public void showAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("courseList", courseService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/courseList.jsp");
        requestDispatcher.forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long courseId = Long.parseLong(request.getParameter("courseId"));
        if (courseId == 0) {
            request.setAttribute("action", "save");
        } else {
            request.setAttribute("action", "update");
            request.setAttribute("course", courseService.find(courseId));
            request.setAttribute("courseId", courseId);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/course.jsp");
        requestDispatcher.forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String courseCode = request.getParameter("courseCode");
        String courseTitle = request.getParameter("courseTitle");

        courseService.saveOrUpdate(getOrCreateCourse(0, courseCode, courseTitle));
        showAll(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long courseId = Long.parseLong(request.getParameter("courseId"));
        String courseCode = request.getParameter("courseCode");
        String courseTitle = request.getParameter("courseTitle");

        courseService.saveOrUpdate(getOrCreateCourse(courseId, courseCode, courseTitle));
        showAll(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long courseId = Long.parseLong(request.getParameter("courseId"));

        courseService.delete(courseId);
        showAll(request, response);
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