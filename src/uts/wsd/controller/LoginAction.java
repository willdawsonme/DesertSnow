package uts.wsd.controller;

import uts.wsd.model.Author;

import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthorDAO authorDao = new AuthorDAOImpl(request.getSession().getServletContext());
        Author author = authorDao.login(email, password);
        
        if (author != null) {
            request.getSession().setAttribute("user", author);
            return "";
        } else {
            request.setAttribute("errors", "Unknown email or password. Please try again.");
            return "login";
        }
    }
}