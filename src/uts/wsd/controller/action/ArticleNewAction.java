package uts.wsd.controller.action;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;
import uts.wsd.model.Article;
import uts.wsd.model.Author;
import uts.wsd.model.LoremIpsum;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ArticleNewAction
 * - Executed when the users requests /article/new.
 * - Handles POST parameters and creates a new article, or returns errors.
 */
public class ArticleNewAction implements Action {
    private HttpServletRequest request;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        HashMap<String, String> errors = validate();

        // Generates a Lipsum string for the user if requested
        if (paramSet("lipsum")) {
            LoremIpsum lipsum = new LoremIpsum();
            request.setAttribute("lipsum", lipsum.getLipsum());
            errors.remove("content"); // Removes errors when the content is blank
        }

        // Only creates the article if no errors are set.
        if (errors.size() == 0) {
            Author author = (Author)request.getSession().getAttribute("user");

            ArticleDAO articleDao = new ArticleDAOImpl(request.getSession().getServletContext());
            Article article = new Article(author, param("title"), param("preview"), param("content"), param("category"), param("visibility"));
            articleDao.addArticle(article);
            
            return "article?id=" + article.getId();
        } else {
            request.setAttribute("errors", errors);
            return "article/new";
        }
    }

    /**
     * validate()
     * - Populates an key/value errors map for incorrectly set parameters
     */
    private HashMap<String, String> validate() {
        HashMap<String, String> errors = new HashMap<String, String>();

        if (!paramSet("title"))
            errors.put("title", "Title cannot be left blank.");
        if (!paramSet("preview"))
            errors.put("preview", "Preview cannot be left blank.");
        if (!paramSet("content"))
            errors.put("content", "Content cannot be left blank.");
        if (!paramSet("category"))
            errors.put("category", "Category cannot be left blank.");

        return errors;
    }

    /**
     * param(String key)
     * - Helper to determine the value of a parameter.
     */
    private String param(String key) {
        return request.getParameter(key);
    }

    /**
     * paramSet(String key)
     * - Helper to determine if a parameter is set
     */
    private Boolean paramSet(String key) {
        return (param(key) == null || param(key).equals("") ? false : true);
    }
}