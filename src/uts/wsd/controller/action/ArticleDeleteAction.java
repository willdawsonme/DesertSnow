package uts.wsd.controller.action;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

public class ArticleDeleteAction implements Action {
    private HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        
        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        Article article = articleDao.findArticle(id);
        articleDao.deleteArticle(article);

        return "";
    }
}