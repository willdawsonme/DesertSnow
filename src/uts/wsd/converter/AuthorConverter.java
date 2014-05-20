package uts.wsd.converter;

import uts.wsd.model.Author;

import uts.wsd.dao.AuthorDAO;
import uts.wsd.dao.AuthorDAOImpl;

import javax.servlet.ServletContext;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AuthorConverter implements Converter {

        private ServletContext servletContext;

        public AuthorConverter(ServletContext servletContext) {
                this.servletContext = servletContext;
        }

        public boolean canConvert(Class clazz) {
                return clazz.equals(Author.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
                Author author = (Author) value;
                writer.addAttribute("id", String.valueOf(author.getId()));
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                AuthorDAO authorDao = new AuthorDAOImpl(servletContext);
                Author author = authorDao.findAuthor(Integer.parseInt(reader.getAttribute("id")));
                return author;
        }

}