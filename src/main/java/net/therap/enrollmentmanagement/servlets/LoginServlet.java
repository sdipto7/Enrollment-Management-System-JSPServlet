package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    private UserService userService;

    public LoginServlet() {
        userService = new UserService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        User user = userService.findByCredential(userName, password);

        if (Objects.nonNull(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}