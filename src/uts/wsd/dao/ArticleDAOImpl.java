package uts.wsd.dao;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import java.util.LinkedList;

import uts.wsd.converter.AuthorConverter;

import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

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
            outputStream.write("<?xml version=\"1.0\"?>\n".getBytes("UTF-8")); //write XML header, as XStream doesn't do that for us
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