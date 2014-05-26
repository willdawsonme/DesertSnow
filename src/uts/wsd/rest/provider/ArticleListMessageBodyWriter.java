package uts.wsd.rest.provider;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import uts.wsd.converter.AuthorConverter;

import java.util.LinkedList;
import java.nio.charset.Charset;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.converters.basic.DateConverter;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.lang.annotation.Annotation;

import java.io.OutputStream;
import java.io.IOException;
import java.io.DataOutputStream;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class ArticleListMessageBodyWriter implements MessageBodyWriter<LinkedList<Article>> {
    private XStream xStream;

    public ArticleListMessageBodyWriter() {
        xStream = new XStream(new DomDriver());
        xStream.alias("articles", LinkedList.class);
        xStream.alias("article", Article.class);
        xStream.useAttributeFor(Article.class, "id");
        xStream.useAttributeFor(Author.class, "id");
        xStream.registerConverter(new AuthorConverter(null));
        xStream.registerConverter(new DateConverter("yyyy-MM-dd'T'HH:mm:ssXXX", null));
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == LinkedList.class;
    }

    @Override
    public long getSize(LinkedList<Article> articles, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(LinkedList<Article> articles, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        String xmlOutput = xStream.toXML(articles);
        entityStream.write(xmlOutput.getBytes("UTF-8"));
    }
}