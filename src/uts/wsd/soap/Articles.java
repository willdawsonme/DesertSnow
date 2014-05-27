package uts.wsd.soap;

import uts.wsd.dao.ArticleDAO;
import uts.wsd.dao.ArticleDAOImpl;
import uts.wsd.model.Article;

import java.util.LinkedList;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 * Articles SOAP Service
 * - A SOAP implementation for listing and deleting articles
 */
@WebService
public class Articles {
    @Resource
    private WebServiceContext context;

    @WebMethod
    public LinkedList<Article> getArticles() {
        ServletContext servletContext = (ServletContext)context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        ArticleDAO articleDao = new ArticleDAOImpl(servletContext);
        
        return articleDao.findAll();
    }

    @WebMethod
    public Article deleteArticle(int id) {
        ServletContext servletContext = (ServletContext)context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        ArticleDAO articleDao = new ArticleDAOImpl(servletContext);

        Article article = articleDao.findArticle(id);
        articleDao.deleteArticle(article);
        return article;
    }
}