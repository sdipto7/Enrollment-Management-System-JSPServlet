package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
public class LoginServlet extends HttpServlet {

    private EntityManager em;

    private UserService userService;

    public LoginServlet() {
        em = EntityManagerSingleton.getInstance().getEntityManager();
        userService = new UserService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        User user = userService.findByCredential(getCredential(userName, password));

        if (Objects.nonNull(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public Credential getCredential(String userName, String password) {
        Query query = em.createQuery("FROM Credential c WHERE c.userName = :userName AND c.password = :password");
        query.setParameter("userName", userName);
        query.setParameter("password", password);

        return (Credential) query.getSingleResult();
    }
}