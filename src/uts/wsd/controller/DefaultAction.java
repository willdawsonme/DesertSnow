package uts.wsd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String servletPath = request.getServletPath();
        return servletPath.substring(1);
    }
}