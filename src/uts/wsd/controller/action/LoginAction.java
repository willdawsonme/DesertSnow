package uts.wsd.controller.action;

import uts.wsd.model.Author;

import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginAction
 * - Executed when the users requests /login.
 * - Attempts a user login, setting session variables or returning errors.
 */
public class LoginAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthorDAO authorDao = new AuthorDAOImpl(request.getSession().getServletContext());
        Author author = authorDao.login(email, password);
        
        if (author != null) { // The user logged in successfully.
            request.getSession().setAttribute("user", author);
            return "";
        } else { // The user login failed.
            request.setAttribute("error", "Unknown email or password. Please try again.");
            return "login";
        }
    }
}