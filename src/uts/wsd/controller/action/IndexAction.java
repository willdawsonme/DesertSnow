package uts.wsd.controller.action;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;
import uts.wsd.model.Article;
import uts.wsd.model.Author;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * IndexAction
 * - Executed when the users requests /.
 * - Returns the articles as an XML string.
 */
public class IndexAction implements Action {
    HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;

        Author user = (Author)request.getSession().getAttribute("user");
        LinkedList<Article> articles = visibleArticles(user);

        request.setAttribute("articles", articlesXml(articles));
        return "index";
    }

    /**
     * visibleArticles(Author user)
     * - Ensures that only articles that the user is allowed to see get returned.
     */
    private LinkedList<Article> visibleArticles(Author user) {
        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        LinkedList<Article> visible = new LinkedList<Article>();
        for (Article article : articleDao.findAll()) {
            // If the user is logged in, add the article.
            // If they're not logged in, only add the article if it is public.
            if (user != null || article.getVisibility().equals("public"))
                visible.add(article);
        }

        return visible;
    }

    /**
     * articlesXml(LinkedList<Article> articles)
     * - Returns an object as an XML String for XSLT processing.
     */
    private String articlesXml(LinkedList<Article> articles) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("articles", LinkedList.class);
        xStream.alias("article", Article.class);
        xStream.useAttributeFor(Article.class, "id");
        xStream.useAttributeFor(Author.class, "id");
        xStream.omitField(Author.class, "password");
        xStream.registerConverter(new DateConverter("d MMM Y", null));
        return xStream.toXML(articles);
    }
}