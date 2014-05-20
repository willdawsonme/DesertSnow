package uts.wsd.controller;

import uts.wsd.model.Author;

import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        AuthorDAO authorDao = new AuthorDAOImpl(request.getServletContext());
        Author author = authorDao.findAuthor(id);

        request.setAttribute("author", author);
        return "author";
    }
}