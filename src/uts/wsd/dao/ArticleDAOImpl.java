package uts.wsd.dao;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import uts.wsd.converter.AuthorConverter;

import java.util.LinkedList;

import javax.servlet.ServletContext;

import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

// XStream
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.converters.basic.DateConverter;

public class ArticleDAOImpl implements ArticleDAO {
    private String filePath;
    private LinkedList<Article> articles;
    private XStream xStream;

    public ArticleDAOImpl(ServletContext servletContext) {
        filePath = servletContext.getRealPath("/WEB-INF/articles.xml");
        articles = new LinkedList<Article>();

        xStream = new XStream(new DomDriver());
        xStream.alias("articles", LinkedList.class);
        xStream.alias("article", Article.class);
        xStream.useAttributeFor(Article.class, "id");
        xStream.registerConverter(new AuthorConverter(servletContext));
        xStream.registerConverter(new DateConverter("yyyy-MM-dd'T'HH:mm:ssXXX", null));
    }

    // CRUD
    public void addArticle(Article article) {
        load();

        if (articles.size() > 0)
            article.setId(articles.getLast().getId() + 1);
        else
            article.setId(1);
        articles.add(article);

        save();
    }

    public Article findArticle(int id) {
        load();

        for (int i = 0; i < articles.size(); i++)
            if (articles.get(i).getId() == id)
                return articles.get(i);

        return null;
    }

    public void updateArticle(Article article) {
        int id = article.getId();
        load();

        for (int i = 0; i < articles.size(); i++)
            if (articles.get(i).getId() == id)
                articles.set(i, article);

        save();
    }

    public void deleteArticle(Article article) {
        int id = article.getId();
        load();

        for (int i = 0; i < articles.size(); i++)
            if (articles.get(i).getId() == id)
                articles.remove(i);

        save();
    }

    // Searching
    public LinkedList<Article> findAll() {
        load();
        return articles;
    }

    public LinkedList<Article> findByAuthor(int id) {
        load();

        LinkedList<Article> articles = new LinkedList<Article>();
        for (Article article : this.articles)
            if (article.getAuthor().getId() == id)
                articles.add(article);

        return articles;
    }

    // File IO
    private void load() {
        try {
            articles = (LinkedList<Article>)xStream.fromXML(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }

    private void save() {
        try {
            String xmlOutput = xStream.toXML(articles);
            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write("<?xml version=\"1.0\"?>\n".getBytes("UTF-8")); // Write XML header, as XStream doesn't do that for us.
            byte[] bytes = xmlOutput.getBytes("UTF-8");
            outputStream.write(bytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}