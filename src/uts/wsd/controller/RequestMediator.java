package uts.wsd.controller;

import uts.wsd.controller.action.Action;
import uts.wsd.controller.action.ActionFactory;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

/**
 * RequestMediator
 * - When called, it executes the matching action and forwards or redirects to the correct page
 * 
 * Request/Action pattern from http://balusc.blogspot.com.au/2010/08/using-mvc-design-pattern-with.html
 */
public class RequestMediator extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(request);
            String servletPath = request.getServletPath(); // Gets the value of the current URL
            String view = action.execute(request, response); // Finds out where to go next

            // If we're on the right page, just display the JSP view.
            if (view.equals(servletPath.substring(1))) {
                request.getRequestDispatcher("/WEB-INF/view/" + view.replace("/", "") + ".jsp").forward(request, response);
            }
            // If we need to be on a different page, send a redirect.
            else {
                response.sendRedirect(request.getContextPath() + "/" + view);
            }
        } catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }
}