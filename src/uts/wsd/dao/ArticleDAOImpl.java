package uts.wsd.dao;

import uts.wsd.model.Article;
import uts.wsd.converter.AuthorConverter;
import uts.wsd.converter.SchemaConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.servlet.ServletContext;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ArticleDAOImpl implements ArticleDAO {
    private String filePath;
    private LinkedList<Article> articles;
    private XStream xStream;

    /**
     * ArticleDAOImpl Constructor
     * - Sets up the filePath based on the servletContext.
     * - Sets up xStream to read from and write to XML files.
     */
    public ArticleDAOImpl(ServletContext servletContext) {
        filePath = servletContext.getRealPath("/WEB-INF/articles.xml");
        articles = new LinkedList<Article>();

        xStream = new XStream(new DomDriver());
        xStream.alias("articles", LinkedList.class);
        xStream.alias("article", Article.class);
        xStream.useAttributeFor(Article.class, "id");

        // Register object converters for xStream to use.
        xStream.registerConverter(new SchemaConverter(xStream.getMapper()));
        xStream.registerConverter(new AuthorConverter(servletContext));
        xStream.registerConverter(new DateConverter("yyyy-MM-dd'T'HH:mm:ssXXX", null));
    }


    /* CRUD Methods
       ====================================================================== */
    /**
     * addArticle(Article article)
     * - Adds the specified article to the XML file
     * - Generates a unique ID based on currently stored articles.
     */
    public void addArticle(Article article) {
        load();

        if (articles.size() > 0)
            article.setId(articles.getLast().getId() + 1);
        else
            article.setId(1);
        articles.add(article);

        save();
    }

    /**
     * findArticle(int id)
     * - Returns the article that matches the specified article ID.
     */
    public Article findArticle(int id) {
        load();

        for (int i = 0; i < articles.size(); i++)
            if (articles.get(i).getId() == id)
                return articles.get(i);

        return null;
    }

    /**
     * updateArticle(Article article)
     * - Finds the existing article with matching ID, and replaces it.
     */
    public void updateArticle(Article article) {
        int id = article.getId();
        load();

        for (int i = 0; i < articles.size(); i++)
            if (articles.get(i).getId() == id)
                articles.set(i, article);

        save();
    }

    /**
     * deleteArticle(Article article)
     * - Finds the existing article with matching ID, then deletes it.
     */
    public void deleteArticle(Article article) {
        int id = article.getId();
        load();

        for (int i = 0; i < articles.size(); i++)
            if (articles.get(i).getId() == id)
                articles.remove(i);

        save();
    }


    /* Methods for Searching
       ====================================================================== */

    /**
     * findAll()
     * - Returns the list of articles ordered by date.
     */
    public LinkedList<Article> findAll() {
        load();

        LinkedList<Article> articlesSorted = (LinkedList<Article>)articles.clone();
        Collections.sort(articlesSorted, new Comparator<Article>(){
           @Override
           public int compare(Article o1, Article o2){
                return o2.getPublishedDate().compareTo(o1.getPublishedDate());
           }
        });

        return articlesSorted;
    }

    /**
     * findByAuthor(int id)
     * - Returns the list of articles with matching Author ID.
     */
    public LinkedList<Article> findByAuthor(int id) {
        load();

        LinkedList<Article> articles = new LinkedList<Article>();
        for (Article article : findAll())
            if (article.getAuthor().getId() == id)
                articles.add(article);

        return articles;
    }


    /* File IO
       ====================================================================== */

    /**
     * load()
     * - Loads articles into the LinkedList via xStream.
     */
    private void load() {
        try {
            // Converts the XML file to a Java Ojbect
            articles = (LinkedList<Article>)xStream.fromXML(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }

    /**
     * save()
     * - Saves the LinkedList to XML via xStream.
     */
    private void save() {
        try {
            String xmlOutput = xStream.toXML(articles); // Converts the object to an XML String
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