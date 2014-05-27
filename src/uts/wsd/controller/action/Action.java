package uts.wsd.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action Interface
 * - Defines the execute method for all request actions.
 */
public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}