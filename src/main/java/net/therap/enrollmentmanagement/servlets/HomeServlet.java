package net.therap.enrollmentmanagement.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (Objects.isNull(session.getAttribute("currentUser"))) {
            response.sendRedirect("login.jsp");
        }
    }
}
