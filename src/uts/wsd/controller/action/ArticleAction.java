package uts.wsd.controller.action;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;
import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleAction implements Action {
    HttpServletRequest request;
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;

        Author user = (Author)request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        
        Article article = visibleArticle(id, user);

        request.setAttribute("article", article);
        return "article";
    }

    private Article visibleArticle(int id, Author user) {
        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        Article article = articleDao.findArticle(id);
        if (article.getVisibility().equals("authors") && user == null)
            article = null;

        return article;
    }
}