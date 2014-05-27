package uts.wsd.controller.action;

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

public class AuthorAction implements Action {
    HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        try {
            Author user = (Author)request.getSession().getAttribute("user");
            int id = Integer.parseInt(request.getParameter("id"));
            
            AuthorDAO authorDao = new AuthorDAOImpl(request.getSession().getServletContext());
            Author author = authorDao.findAuthor(id);

            if (author == null)
                request.setAttribute("error", "There is no author with that ID.");
            else {
                LinkedList<Article> articles = visibleArticles(id, user);

                request.setAttribute("author", authorXml(author));
                request.setAttribute("articles", (articles.size() > 3 ? articles.subList(0, 3) : articles));
            }
        } catch (java.lang.NumberFormatException e) {
            request.setAttribute("error", "You must specify an integer ID to view an author.");
        } catch (Exception e) {
            request.setAttribute("error", "An error occured. Please try again.");
            System.out.println(e.getStackTrace());
        }

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

    private String authorXml(Author author) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("author", Author.class);
        xStream.useAttributeFor(Author.class, "id");
        xStream.omitField(Author.class, "password");
        xStream.registerConverter(new DateConverter("d MMM Y", null));
        return xStream.toXML(author);
    }
}