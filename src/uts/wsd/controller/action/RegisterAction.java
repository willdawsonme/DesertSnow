package uts.wsd.controller.action;

import uts.wsd.model.Author;
import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class RegisterAction implements Action {
    private HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        HashMap<String, String> errors = validate();

        if (errors.size() == 0) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date birth = dateFormatter.parse(param("birth"));

            AuthorDAO authorDao = new AuthorDAOImpl(request.getSession().getServletContext());
            Author author = new Author(param("email"), param("password"), param("name"), param("biography"), birth);
            authorDao.addAuthor(author);

            request.getSession().setAttribute("user", author);
            return "account";
        } else {
            request.setAttribute("errors", errors);
            return "register";
        }
    }

    private HashMap<String, String> validate() {
        HashMap<String, String> errors = new HashMap<String, String>();

        if (!paramSet("email"))
            errors.put("email", "Email cannot be left blank.");
        if (!paramSet("password"))
            errors.put("password", "Password cannot be left blank.");
        if (!paramSet("name"))
            errors.put("name", "Name cannot be left blank.");
        if (!paramSet("birth"))
            errors.put("birth", "Date of Birth cannot be left blank.");

        return errors;
    }

    private String param(String key) {
        return request.getParameter(key);
    }

    private Boolean paramSet(String key) {
        return (param(key).equals("") ? false : true);
    }
}