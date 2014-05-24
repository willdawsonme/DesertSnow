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
    HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;

        Author user = (Author)request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        
        AuthorDAO authorDao = new AuthorDAOImpl(request.getSession().getServletContext());
        Author author = authorDao.findAuthor(id);
        
        LinkedList<Article> articles = visibleArticles(id, user);

        request.setAttribute("author", author);
        request.setAttribute("articles", (articles.size() > 3 ? articles.subList(0, 3) : articles));
        return "author";
    }

    private LinkedList<Article> visibleArticles(int id, Author user) {
        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        LinkedList<Article> articles = articleDao.findByAuthor(id);
        for (Article article : articles)
            if (article.getVisibility().equals("authors") && user == null)
                articles.remove(article);

        return articles;
    }
}