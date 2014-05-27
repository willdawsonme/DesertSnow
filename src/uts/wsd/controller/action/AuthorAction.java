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

/**
 * AuthorAction
 * - Executed when the users requests /author?id=x.
 * - Handles POST parameters and creates a new article, or returns errors.
 */
public class AuthorAction implements Action {
    HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;

        try {
            Author user = (Author)request.getSession().getAttribute("user");
            int id = Integer.parseInt(request.getParameter("id"));
            
            AuthorDAO authorDao = new AuthorDAOImpl(request.getSession().getServletContext());
            Author author = authorDao.findAuthor(id);

            if (author == null) // Checks if there is an author with that ID value
                request.setAttribute("error", "There is no author with that ID.");
            else {
                LinkedList<Article> articles = visibleArticles(id, user);

                request.setAttribute("author", authorXml(author));
                // Returns a maximum of three user articles to display.
                request.setAttribute("articles", (articles.size() > 3 ? articles.subList(0, 3) : articles));
            }
        } catch (java.lang.NumberFormatException e) { // Ensures the ID is an integer.
            request.setAttribute("error", "You must specify an integer ID to view an author.");
        } catch (Exception e) { // Default error response.
            request.setAttribute("error", "An error occured. Please try again.");
            System.out.println(e.getStackTrace());
        }

        return "author";
    }

    /**
     * visibleArticles(int id, Author user)
     * - Ensures that only articles that the user is allowed to see get returned.
     */
    private LinkedList<Article> visibleArticles(int id, Author user) {
        ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
        LinkedList<Article> visible = new LinkedList<Article>();

        for (Article article : articleDao.findByAuthor(id)) {
            // If the user is logged in, add the article.
            // If they're not logged in, only add the article if it is public.
            if (user != null || article.getVisibility().equals("public"))
                visible.add(article);
        }

        return visible;
    }

    /**
     * author(Author author)
     * - Returns an object as an XML String for XSLT processing.
     */
    private String authorXml(Author author) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("author", Author.class);
        xStream.useAttributeFor(Author.class, "id");
        xStream.omitField(Author.class, "password");
        xStream.registerConverter(new DateConverter("d MMM Y", null));
        return xStream.toXML(author);
    }
}