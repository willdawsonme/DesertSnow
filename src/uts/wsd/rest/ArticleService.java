package uts.wsd.rest;

import uts.wsd.model.Article;
import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;

import java.util.LinkedList;
import java.util.HashMap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/articles")
public class ArticleService {
    @Context private ServletContext servletContext;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public LinkedList<Article> get(@DefaultValue("") @QueryParam("category") String category, @DefaultValue("") @QueryParam("startDate") String startDate, @DefaultValue("") @QueryParam("endDate") String endDate) {
        HashMap<String, String> errors = new HashMap<String, String>();
        ArticleDAO articleDao = new ArticleDAOImpl(servletContext);
        LinkedList<Article> articles = articleDao.findAll();

        if (!category.equals(""))
            articles = filterCategory(articles, category);
        
        if (!startDate.equals(""))
            if (parseDate(endDate) != null)
                articles = filterStartDate(articles, parseDate(startDate));
            else
                errors.put("startDate", "Must be in the format yyyy-MM-dd'T'HH:mm:ss");
        
        if (!endDate.equals(""))
            if (parseDate(endDate) != null)
                articles = filterEndDate(articles, parseDate(endDate));
            else
                errors.put("endDate", "Must be in the format yyyy-MM-dd'T'HH:mm:ss");

        if (errors.size() == 0)
            return articles;
        else
            return new LinkedList<Article>();
    }

    private LinkedList<Article> filterCategory(LinkedList<Article> articles, String category) {
        LinkedList<Article> filtered = new LinkedList<Article>();

        for (Article article : articles)
            if (article.getCategory().equals(category))
                filtered.add(article);

        return filtered;
    }

    private LinkedList<Article> filterStartDate(LinkedList<Article> articles, Date date) {
        LinkedList<Article> filtered = new LinkedList<Article>();
        
        for (Article article : articles)
            if (article.getPublishedDate().compareTo(date) > 0)
                filtered.add(article);

        return filtered;
    }

    private LinkedList<Article> filterEndDate(LinkedList<Article> articles, Date date) {
        LinkedList<Article> filtered = new LinkedList<Article>();
        
        for (Article article : articles)
            if (article.getPublishedDate().compareTo(date) < 0)
                filtered.add(article);

        return filtered;
    }

    private Date parseDate(String date) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dateFormatter.parse(date.replace(' ', '+'));
        } catch (java.text.ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
