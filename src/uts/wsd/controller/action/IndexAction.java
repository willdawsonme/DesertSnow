package uts.wsd.controller.action;

import uts.wsd.converter.AuthorConverter;
import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;
import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;
import uts.wsd.model.Article;
import uts.wsd.model.Author;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class IndexAction implements Action {
    HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;

        Author user = (Author)request.getSession().getAttribute("user");
        LinkedList<Article> articles = visibleArticles(user);

        request.setAttribute("articles", articlesXml(articles));
        return "index";
    }

    private LinkedList<Article> visibleArticles(Author user) {
        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        LinkedList<Article> articles = articleDao.findAll();
        for (Article article : articles)
            if (article.getVisibility().equals("authors") && user == null)
                articles.remove(article);

        return articles;
    }

    private String articlesXml(LinkedList<Article> articles) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("articles", LinkedList.class);
        xStream.alias("article", Article.class);
        xStream.useAttributeFor(Article.class, "id");
        xStream.useAttributeFor(Author.class, "id");
        xStream.registerConverter(new DateConverter("d MMM Y", null));
        return xStream.toXML(articles);
    }
}