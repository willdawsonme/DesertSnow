package uts.wsd.controller;

import uts.wsd.controller.action.Action;
import uts.wsd.controller.action.ActionFactory;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

public class RequestMediator extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(request);
            String servletPath = request.getServletPath();
            String view = action.execute(request, response);

            if (view.equals(servletPath.substring(1))) {
                request.getRequestDispatcher("/WEB-INF/view/" + view.replace("/", "") + ".jsp").forward(request, response);
            }
            else {
                response.sendRedirect(request.getContextPath() + "/" + view); // We'd like to fire redirect in case of a view change as result of the action.
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