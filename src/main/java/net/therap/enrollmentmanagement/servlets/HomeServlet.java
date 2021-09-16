package net.therap.enrollmentmanagement.servlets;

import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.util.SessionUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loggedInUser = SessionUtil.getLoggedInUser(request);

        if (Objects.isNull(loggedInUser)) {
            response.sendRedirect("login.jsp");
        }
    }
}
