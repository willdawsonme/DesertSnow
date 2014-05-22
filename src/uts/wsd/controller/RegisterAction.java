package uts.wsd.controller;

import uts.wsd.model.Author;
import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class RegisterAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String biography = request.getParameter("biography");
        String birthString = request.getParameter("birth");

        if (verifyEmail(email) && verifyPassword(password) && name != "" && biography != "" && birthString != "") {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date birth = dateFormatter.parse(birthString);

            AuthorDAO authorDao = new AuthorDAOImpl(request.getSession().getServletContext());
            Author author = new Author(email, password, name, biography, birth);
            authorDao.addAuthor(author);

            request.getSession().setAttribute("user", author);
            return "";
        } else {
            request.setAttribute("errors", "Not all fields were correct. Please try again.");
            return "register";
        }
    }

    private boolean verifyEmail(String email) {
        return email != null;
    }

    private boolean verifyPassword(String password) {
        return password != null;
    }
}