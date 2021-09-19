package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (SessionUtil.checkValidLogin(request, response)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
