package net.therap.enrollmentmanagement.util;

import net.therap.enrollmentmanagement.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rumi.dipto
 * @since 9/16/21
 */
public class SessionUtil {

    public static User getLoggedInUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("currentUser");
    }
}
