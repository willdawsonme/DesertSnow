package uts.wsd.controller.action;

import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;
import uts.wsd.model.Author;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * RegisterAction
 * - Executed when the users requests /register.
 * - Handles POST parameters and creates a new Author, or returns errors.
 */
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
        } 

        request.setAttribute("errors", errors);
        return "register";
    }

    /**
     * validate()
     * - Populates an key/value errors map for incorrectly set parameters
     */
    private HashMap<String, String> validate() {
        HashMap<String, String> errors = new HashMap<String, String>();


        if (!paramSet("email"))
            errors.put("email", "Email cannot be left blank.");

        if (!paramSet("password"))
            errors.put("password", "Password cannot be left blank.");

        if (!paramSet("name"))
            errors.put("name", "Name cannot be left blank.");

        /* If the birth parameter is set, we try to parse the birth string. */
        if (!paramSet("birth"))
            errors.put("birth", "Date of Birth cannot be left blank.");
        else {
            try {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                dateFormatter.parse(param("birth"));
            } catch (java.text.ParseException e) {
                errors.put("birth", "Date of Birth must be in the correct format.");
            }
        }

        return errors;
    }

    /**
     * param(String key)
     * - Helper to determine the value of a parameter.
     */
    private String param(String key) {
        return request.getParameter(key);
    }

    /**
     * paramSet(String key)
     * - Helper to determine if a parameter is set
     */
    private Boolean paramSet(String key) {
        return (param(key) == null || param(key).equals("") ? false : true);
    }
}