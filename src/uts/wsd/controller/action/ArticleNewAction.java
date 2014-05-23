package uts.wsd.controller.action;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

public class ArticleNewAction implements Action {
    private HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        HashMap<String, String> errors = validate();

        if (errors.size() == 0) {
            Author author = (Author)request.getSession().getAttribute("user");

            ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
            Article article = new Article(author, param("title"), param("content"), param("category"));
            articleDao.addArticle(article);
            
            return "article?id=" + article.getId();
        } else {
            request.setAttribute("errors", errors);
            return "article/new";
        }
    }

    private HashMap<String, String> validate() {
        HashMap<String, String> errors = new HashMap<String, String>();

        if (!paramSet("title"))
            errors.put("title", "Title cannot be left blank.");
        if (!paramSet("content"))
            errors.put("content", "Content cannot be left blank.");
        if (!paramSet("category"))
            errors.put("category", "Category cannot be left blank.");

        return errors;
    }

    private String param(String key) {
        return request.getParameter(key);
    }

    private Boolean paramSet(String key) {
        return (param(key).equals("") ? false : true);
    }
}