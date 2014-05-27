package uts.wsd.converter;

import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;
import uts.wsd.model.Author;

import javax.servlet.ServletContext;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * AuthorConverter
 * - Used by xStream to only output the attribute of an author, instead of all fields.
 * - Reduces data redundancies when storing XML files.
 */
public class AuthorConverter implements Converter {
        private ServletContext servletContext;

        public AuthorConverter(ServletContext servletContext) {
            this.servletContext = servletContext;
        }

        // Tells xStream which class this converter is used for
        public boolean canConvert(Class clazz) {
            return clazz.equals(Author.class);
        }

        // Only write the id attribute to XML
        public void marshal(Object value, HierarchicalStreamWriter writer,
                MarshallingContext context) {
            Author author = (Author) value;
            writer.addAttribute("id", String.valueOf(author.getId()));
        }

        // When reading, get the Author object to reference in the Article object
        public Object unmarshal(HierarchicalStreamReader reader,
                UnmarshallingContext context) {
            AuthorDAO authorDao = new AuthorDAOImpl(servletContext);
            Author author = authorDao.findAuthor(Integer.parseInt(reader.getAttribute("id")));
            return author;
        }
}