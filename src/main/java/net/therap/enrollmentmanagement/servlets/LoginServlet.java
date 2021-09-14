package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.dao.CredentialDao;
import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.Role;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        CredentialDao credentialDao = new CredentialDao();
        Credential credential = credentialDao.check(name, password);

        if (Objects.nonNull(credential)) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", name);
            session.setAttribute("admin", Role.ADMIN);
            session.setAttribute("user", Role.USER);

            if (credential.getRole() == Role.ADMIN) {
                session.setAttribute("role", Role.ADMIN);
            } else {
                session.setAttribute("role", Role.USER);
            }
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}