package uts.wsd.model;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")

public class Article implements Serializable {
    private int id;
    private Author author;
    private String title;
    private String content;
    private String category;
    private Date publishedDate;

    public Article(Author author, String title, String content, String category) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.category = category;

        this.publishedDate = new Date();
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory() {
        this.category = category;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}