package uts.wsd.dao;

import uts.wsd.model.Author;

import java.util.LinkedList;

import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

// XStream
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.converters.basic.DateConverter;

public class AuthorDAOImpl implements AuthorDAO {
    private String filePath;
    private LinkedList<Author> authors;
    private XStream xStream;

    public AuthorDAOImpl(String contextPath) {
        filePath = contextPath + "/WEB-INF/authors.xml";
        authors = new LinkedList<Author>();

        xStream = new XStream(new DomDriver());
        xStream.alias("authors", LinkedList.class);
        xStream.alias("author", Author.class);
        xStream.useAttributeFor(Author.class, "id");
        xStream.registerConverter(new DateConverter("yyyy-MM-dd'T'HH:mm:ssXXX", null));
    }

    // CRUD
    public void addAuthor(Author author) {
        load();

        if (authors.size() > 0)
            author.setId(authors.getLast().getId() + 1);
        else
            author.setId(1);
        authors.add(author);

        save();
    }

    public Author findAuthor(int id) {
        load();

        for (int i = 0; i < authors.size(); i++)
            if (authors.get(i).getId() == id)
                return authors.get(i);

        return null;
    }

    public void updateAuthor(Author author) {
        int id = author.getId();
        load();

        for (int i = 0; i < authors.size(); i++)
            if (authors.get(i).getId() == id)
                authors.set(i, author);

        save();
    }

    public void deleteAuthor(Author author) {
        int id = author.getId();
        load();

        for (int i = 0; i < authors.size(); i++)
            if (authors.get(i).getId() == id)
                authors.remove(i);

        save();
    }

    // Searching
    public LinkedList<Author> findAll() {
        load();
        return authors;
    }

    // Other
    public Author login(String email, String password) {
        load();
        for (Author author : authors) {
            System.out.println("Login: " + email + ", " + password);
            if (author.login(email, password)) {
                System.out.println("Success");
                return author;
            }
        }

        return null;
    }

    // File IO
    private void load() {
        try {
            authors = (LinkedList<Author>)xStream.fromXML(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }

    private void save() {
        try {
            String xmlOutput = xStream.toXML(authors);
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