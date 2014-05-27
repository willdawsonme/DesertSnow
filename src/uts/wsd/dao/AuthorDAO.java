package uts.wsd.dao;

import uts.wsd.model.Author;

import java.util.LinkedList;

/**
 * Provides an Data Access Object interface for the storage and retrieval of
 * a list of Authors.
 */
public interface AuthorDAO {
    /**
     * Provides CRUD Methods:
     * Create, Read, Update, Delete
     */
    public void addAuthor(Author author);

    public Author findAuthor(int id);

    public void updateAuthor(Author author);

    public void deleteAuthor(Author author);

    /**
     * Provides methods for searching Authors
     */
    public LinkedList<Author> findAll();

    /**
     * Provides a login method for the list of Authors
     */
    public Author login(String email, String password);
}