package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.dao.CredentialDao;
import net.therap.enrollmentmanagement.domain.Credential;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        CredentialDao credentialDao = new CredentialDao();
        Credential credential = credentialDao.check(name, password);

        if (Objects.nonNull(credential)){
            HttpSession session = request.getSession();
            session.setAttribute("userName", name);
            response.sendRedirect("welcome.jsp");
        }else{
            response.sendRedirect("index.jsp");
        }
    }
}