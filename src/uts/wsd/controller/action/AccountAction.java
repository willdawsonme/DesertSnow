package uts.wsd.controller.action;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;
import uts.wsd.model.Article;
import uts.wsd.model.Author;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AccountAction
 * - Executed when the users requests /account
 * - Returns a list of that users articles.
 */
public class AccountAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Author user = (Author)request.getSession().getAttribute("user");

        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        LinkedList<Article> articles = articleDao.findByAuthor(user.getId());

        request.setAttribute("articles", articles);
        return "account";
    }
}