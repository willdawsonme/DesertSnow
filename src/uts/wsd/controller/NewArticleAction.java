package uts.wsd.controller;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;
import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

public class NewArticleAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Author author = (Author)request.getSession().getAttribute("user");
        String title = "New Article";
        String content = request.getParameter("content");
        String category = "General";
        Date publishedDate = new Date();
        Article article = new Article(author, title, content, category);

        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        articleDao.addArticle(article);

        return "index";
    }
}