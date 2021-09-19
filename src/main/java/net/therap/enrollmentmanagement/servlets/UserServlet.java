package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Role;
import net.therap.enrollmentmanagement.domain.User;
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
public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet() {
        userService = new UserService();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User loggedInUser = SessionUtil.getLoggedInUser(request);

        if (Objects.isNull(loggedInUser)) {
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
        User loggedInUser = SessionUtil.getLoggedInUser(request);

        if (Objects.isNull(loggedInUser)) {
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
                long userId = Long.parseLong(request.getParameter("userId"));
                if (userId == 0) {
                    request.setAttribute("action", "add");
                } else {
                    request.setAttribute("action", "update");
                    request.setAttribute("user", userService.find(userId));
                    request.setAttribute("userId", userId);
                }
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/user.jsp");
                requestDispatcher.forward(request, response);
                break;

            case DELETE:
                delete(request, response);
                break;

            default:
                break;
        }
    }

    public void showAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("userList", userService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/userList.jsp");
        requestDispatcher.forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String role = request.getParameter("role");

        userService.saveOrUpdate(getOrCreateUser(0, name, role));
        showAll(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long userId = Long.parseLong(request.getParameter("userId"));
        String name = request.getParameter("name");
        String role = request.getParameter("role");

        userService.saveOrUpdate(getOrCreateUser(userId, name, role));
        showAll(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long userId = Long.parseLong(request.getParameter("userId"));

        userService.delete(userId);
        showAll(request, response);
    }

    public User getOrCreateUser(long userId, String name, String role) {
        User user;
        if (userId > 0) {
            user = userService.find(userId);
        } else {
            user = new User();
        }
        user.setName(name);
        user.setRole(Role.valueOf(role.toUpperCase()));

        return user;
    }
}