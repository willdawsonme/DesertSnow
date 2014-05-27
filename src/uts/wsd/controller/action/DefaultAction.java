package uts.wsd.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Default Action
 * - The default action executed when no mappings are specified
 */
public class DefaultAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String servletPath = request.getServletPath();
        return servletPath.substring(1); // Returns the request path without the initial forward slash
    }
}