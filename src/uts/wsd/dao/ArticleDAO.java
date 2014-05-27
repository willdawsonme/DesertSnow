package uts.wsd.dao;

import uts.wsd.model.Article;

import java.util.LinkedList;

/**
 * Provides an Data Access Object interface for the storage and retrieval of
 * a list of Articles.
 */
public interface ArticleDAO {
    /**
     * Provides CRUD Methods:
     * Create, Read, Update, Delete
     */
    public void addArticle(Article article);

    public Article findArticle(int id);

    public void updateArticle(Article article);

    public void deleteArticle(Article article);

    /**
     * Provides methods for searching Articles
     */
    public LinkedList<Article> findAll();

    public LinkedList<Article> findByAuthor(int id);
}