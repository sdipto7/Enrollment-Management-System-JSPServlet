package net.therap.enrollmentmanagement.util;

import net.therap.enrollmentmanagement.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/16/21
 */
public class SessionUtil {

    public static User getLoggedInUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("currentUser");
    }

    public static boolean checkValidLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.isNull(getLoggedInUser(request))) {
            return true;
        }

        return false;
    }
}
