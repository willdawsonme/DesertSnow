package uts.wsd.controller.action;

import uts.wsd.model.Author;
import uts.wsd.model.Article;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedList;

public class AccountAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Author user = (Author)request.getSession().getAttribute("user");

        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        LinkedList<Article> articles = articleDao.findByAuthor(user.getId());

        request.setAttribute("articles", articles);
        return "account";
    }
}