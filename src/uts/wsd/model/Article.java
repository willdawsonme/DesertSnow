package uts.wsd.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")

public class Article implements Serializable {
    private int id;
    private Author author;
    private String title;
	private String preview;
    private String content;
    private String category;
    private Date publishedDate;
    private String visibility;

    public Article(Author author, String title, String preview, String content, String category, String visibility) {
        this.author = author;
        this.title = title;
		this.preview = preview;
        this.content = content;
        this.category = category;
        this.visibility = visibility;
        this.publishedDate = new Date(); /* The publishedDate is set to the current time. */
    }

    /* Field Getters & Setters
       ====================================================================== */
       
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
	
	public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}