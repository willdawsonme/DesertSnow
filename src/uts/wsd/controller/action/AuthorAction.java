package uts.wsd.controller.action;

import uts.wsd.model.Author;
import uts.wsd.model.Article;

import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;
import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedList;

public class AuthorAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        AuthorDAO authorDao = new AuthorDAOImpl(request.getSession().getServletContext());
        Author author = authorDao.findAuthor(id);

        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        LinkedList<Article> articles = articleDao.findByAuthor(id);

        request.setAttribute("author", author);
        request.setAttribute("articles", (articles.size() > 3 ? articles.subList(0, 3) : articles));
        return "author";
    }
}