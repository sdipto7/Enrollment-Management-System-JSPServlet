package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.Role;
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                save(request, response);
                response.sendRedirect("userList.jsp");
                break;
            case "update":
                update(request, response);
                response.sendRedirect("userList.jsp");
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
                case "userList":
                    viewAllUsers(request, response);
                    response.sendRedirect("userList.jsp");
                    break;

                case "addClick":
                    session.setAttribute("action", "add");
                    response.sendRedirect("user.jsp");
                    break;

//                case "add":
//                    save(request, response);
//                    response.sendRedirect("userList.jsp");
//                    break;

                case "delete":
                    delete(request, response);
                    response.sendRedirect("userList.jsp");
                    break;

                case "updateClick":
                    long userId = Long.parseLong(request.getParameter("userId"));
                    session.setAttribute("action", "update");
                    session.setAttribute("userId", userId);
                    response.sendRedirect("user.jsp");
                    break;

//                case "update":
//                    update(request, response);
//                    response.sendRedirect("userList.jsp");
//                    break;
//
//                default:
//                    break;
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public void viewAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> userList = userService.findAll();

        HttpSession session = request.getSession();
        session.setAttribute("userList", userList);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String role = request.getParameter("role");

        userService.saveOrUpdate(getOrCreateUser(0, name, role));
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Long.parseLong(request.getParameter("userId"));
        String name = request.getParameter("name");
        String role = request.getParameter("role");

        userService.saveOrUpdate(getOrCreateUser(userId, name, role));
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Long.parseLong(request.getParameter("userId"));

        userService.delete(userId);
    }

    public User getOrCreateUser(long userId, String name, String role) {
        User user;
        if (userId > 0) {
            user = userService.find(userId);
        } else {
            user = new User();
        }
        user.setName(name);
        user.setRole(role.equalsIgnoreCase("ADMIN") ? Role.ADMIN : Role.USER);

        return user;
    }
}