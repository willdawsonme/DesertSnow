package uts.wsd.dao;

import uts.wsd.converter.SchemaConverter;
import uts.wsd.model.Author;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.LinkedList;

import javax.servlet.ServletContext;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class AuthorDAOImpl implements AuthorDAO {
    private String filePath;
    private LinkedList<Author> authors;
    private XStream xStream;

    /**
     * AuthorDAOImpl Constructor
     * - Sets up the filePath based on the servletContext.
     * - Sets up xStream to read from and write to XML files.
     */
    public AuthorDAOImpl(ServletContext servletContext) {
        filePath = servletContext.getRealPath("/WEB-INF/authors.xml");
        authors = new LinkedList<Author>();

        xStream = new XStream(new DomDriver());
        xStream.alias("authors", LinkedList.class);
        xStream.alias("author", Author.class);
        xStream.useAttributeFor(Author.class, "id");

        // Register object converters for xStream to use.
        xStream.registerConverter(new SchemaConverter(xStream.getMapper()));
        xStream.registerConverter(new DateConverter("yyyy-MM-dd'T'HH:mm:ssXXX", null));
    }

    /* CRUD Methods
       ====================================================================== */
    /**
     * addAuthor(Author author)
     * - Adds the specified author to the XML file
     * - Generates a unique ID based on currently stored authors.
     */
    public void addAuthor(Author author) {
        load();

        if (authors.size() > 0)
            author.setId(authors.getLast().getId() + 1);
        else
            author.setId(1);
        authors.add(author);

        save();
    }

    /**
     * findAuthor(int id)
     * - Returns the author that matches the specified article ID.
     */
    public Author findAuthor(int id) {
        load();

        for (int i = 0; i < authors.size(); i++)
            if (authors.get(i).getId() == id)
                return authors.get(i);

        return null;
    }

    /**
     * updateAuthor(Author author)
     * - Finds the existing author with matching ID, and replaces it.
     */
    public void updateAuthor(Author author) {
        int id = author.getId();
        load();

        for (int i = 0; i < authors.size(); i++)
            if (authors.get(i).getId() == id)
                authors.set(i, author);

        save();
    }

    /**
     * deleteAuthor(Author author)
     * - Finds the existing author with matching ID, then deletes it.
     */
    public void deleteAuthor(Author author) {
        int id = author.getId();
        load();

        for (int i = 0; i < authors.size(); i++)
            if (authors.get(i).getId() == id)
                authors.remove(i);

        save();
    }


    /* Methods for Searching
       ====================================================================== */

    /**
     * findAll()
     * - Returns the list of authors.
     */
    public LinkedList<Author> findAll() {
        load();
        return authors;
    }

    /* Other
       ====================================================================== */

    /**
     * login(String email, String password)
     * - Returns the Author that matches the login.
     */
    public Author login(String email, String password) {
        load();
        for (Author author : authors) {
            if (author.login(email, password)) {
                System.out.println("Login Success: " + email + " - " + password);
                return author;
            }
        }
        System.out.println("Login Failure: " + email + " - " + password);
        return null;
    }


    /* File IO
       ====================================================================== */

    /**
     * load()
     * - Loads authors into the LinkedList via xStream.
     */
    private void load() {
        try {
            // Converts the XML file to a Java Ojbect
            authors = (LinkedList<Author>)xStream.fromXML(new FileReader(filePath));
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