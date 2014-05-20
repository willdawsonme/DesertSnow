package uts.wsd.dao;

import uts.wsd.model.Article;

import java.util.LinkedList;

public interface ArticleDAO {
    // CRUD
    public void addArticle(Article article);

    public Article findArticle(int id);

    public void updateArticle(Article article);

    public void deleteArticle(Article article);

    // Searching
    public LinkedList<Article> findAll();
}