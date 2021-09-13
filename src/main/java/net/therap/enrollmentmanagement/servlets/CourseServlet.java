package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.dao.CourseDao;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.service.CourseService;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (Objects.nonNull(session.getAttribute("userName"))) {
            String uri = request.getServletPath();

            switch (uri) {
                case "/viewCourseButton":
                    viewAllCourse(request, response);
                    break;

                case "/addCourseButton":
                    session.setAttribute("action", "add");
                    response.sendRedirect("save_course.jsp");
                    break;

                case "/addCourse":
                    addCourse(request, response);
                    break;

                case "/deleteCourse":
                    deleteCourse(request, response);
                    break;

                case "/updateCourseLink":
                    long courseId = Long.parseLong(request.getParameter("courseId"));
                    session.setAttribute("action", "update");
                    session.setAttribute("courseId", courseId);
                    response.sendRedirect("save_course.jsp");
                    break;

                case "/updateCourse":
                    updateCourse(request, response);
                    break;

                default:
                    break;
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void viewAllCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Course> courseList = courseService.findAll();

        HttpSession session = request.getSession();
        session.setAttribute("course_list", courseList);
        response.sendRedirect("view_course.jsp");
    }

    public void addCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String courseCode = request.getParameter("courseCode");
        String courseTitle = request.getParameter("courseTitle");

        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseTitle(courseTitle);

        courseService.saveOrUpdate(course);
        response.sendRedirect("view_course.jsp");
    }

    public void updateCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long courseId = Long.parseLong(request.getParameter("courseId"));
        String courseCode = request.getParameter("courseCode");
        String courseTitle = request.getParameter("courseTitle");

        Course course = courseService.find(courseId);
        course.setCourseCode(courseCode);
        course.setCourseTitle(courseTitle);

        courseService.saveOrUpdate(course);
        response.sendRedirect("view_course.jsp");
    }

    public void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long courseId = Long.parseLong(request.getParameter("courseId"));

        CourseDao courseDao = new CourseDao();
        courseDao.delete(courseId);
        response.sendRedirect("view_course.jsp");
    }
}