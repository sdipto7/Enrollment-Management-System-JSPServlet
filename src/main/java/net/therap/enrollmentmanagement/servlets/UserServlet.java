package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.dao.UserDao;
import net.therap.enrollmentmanagement.domain.User;
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
public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet() {
        userService = new UserService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (Objects.nonNull(session.getAttribute("userName"))) {
            String uri = request.getServletPath();

            switch (uri) {
                case "/viewUserButton":
                    viewAllUsers(request, response);
                    break;

                case "/addUserButton":
                    session.setAttribute("action", "add");
                    response.sendRedirect("save_user.jsp");
                    break;

                case "/addUser":
                    addUser(request, response);
                    break;

                case "/deleteUser":
                    deleteUser(request, response);
                    break;

                case "/updateUserLink":
                    long userId = Long.parseLong(request.getParameter("userId"));
                    session.setAttribute("action", "update");
                    session.setAttribute("userId", userId);
                    response.sendRedirect("save_user.jsp");
                    break;

                case "/updateUser":
                    updateUser(request, response);
                    break;

                default:
                    break;
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void viewAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> userList = userService.findAll();

        HttpSession session = request.getSession();
        session.setAttribute("user_list", userList);
        response.sendRedirect("view_user.jsp");
    }

    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        User user = new User();
        user.setName(name);

        userService.saveOrUpdate(user);
        response.sendRedirect("view_user.jsp");
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Long.parseLong(request.getParameter("userId"));
        String name = request.getParameter("name");

        User user = userService.find(userId);
        user.setName(name);

        userService.saveOrUpdate(user);
        response.sendRedirect("view_user.jsp");
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Long.parseLong(request.getParameter("userId"));

        UserDao userDao = new UserDao();
        userDao.delete(userId);
        response.sendRedirect("view_user.jsp");
    }
}