package uts.wsd.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LogoutAction
 * - Executed when the users requests /logout.
 * - Unsets the user sesion and returns to the index.
 */
public class LogoutAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("user");
        return "";
    }
}