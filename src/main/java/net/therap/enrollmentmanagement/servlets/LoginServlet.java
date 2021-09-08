package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    private EntityManager entityManager = EntityManagerSingleton.getInstance().getEntityManager();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String pass = request.getParameter("password");

        Query query;
        if (name.equals("admin")) {
            query = entityManager.createQuery("FROM Credential c where c.name = :name");
        } else {
            query = entityManager.createQuery("SELECT u.credential FROM User u where u.name = :name");
        }
        query.setParameter("name", name);
        Credential credential = null;
        try {
            credential = (Credential) query.getSingleResult();
        } catch (Exception ex) {
            response.sendRedirect("index.jsp");
            out.println("Please enter your username correctly");
        }

        assert credential != null;
        if (credential.getPassword().equals(pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", name);
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("index.jsp");
            out.println("Please enter your password correctly");
        }
    }
}