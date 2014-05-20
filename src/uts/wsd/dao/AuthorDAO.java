package uts.wsd.dao;

import uts.wsd.model.Author;

import java.util.LinkedList;

public interface AuthorDAO {
    // CRUD
    public void addAuthor(Author author);

    public Author findAuthor(int id);

    public void updateAuthor(Author author);

    public void deleteAuthor(Author author);

    // Searching
    public LinkedList<Author> findAll();

    // Other
    public Author login(String email, String password);
}