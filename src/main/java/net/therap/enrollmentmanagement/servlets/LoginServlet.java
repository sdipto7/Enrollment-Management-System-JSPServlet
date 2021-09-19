package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
        requestDispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Credential credential = new Credential(userName, password);
        User user = userService.findByCredential(credential);

        if (Objects.nonNull(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/home.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}