package uts.wsd.controller.action;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ArticleAction implements Action {
    HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;

        try {
            Author user = (Author)request.getSession().getAttribute("user");
            int id = Integer.parseInt(param("id"));
            Article article = visibleArticle(id, user);

            if (article == null)
                request.setAttribute("error", "There is no article with that ID.");
            else
                request.setAttribute("article", articleXml(article));
        } catch (java.lang.NumberFormatException e) {
            request.setAttribute("error", "You must specify an integer ID to view an article.");
        } catch (Exception e) {
            request.setAttribute("error", "An error occured. Please try again.");
            System.out.println(e.getMessage());
        }

        return "article";
    }

    private Article visibleArticle(int id, Author user) {
        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        Article article = articleDao.findArticle(id);
        if (article == null || (article.getVisibility().equals("authors") && user == null))
            return null;

        return article;
    }

    private String param(String key) {
        return request.getParameter(key);
    }

    private String articleXml(Article article) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("article", Article.class);
        xStream.alias("author", Author.class);
        xStream.useAttributeFor(Article.class, "id");
        xStream.useAttributeFor(Author.class, "id");
        xStream.omitField(Author.class, "password");
        xStream.registerConverter(new DateConverter("d MMM Y", null));
        return xStream.toXML(article);
    }
}