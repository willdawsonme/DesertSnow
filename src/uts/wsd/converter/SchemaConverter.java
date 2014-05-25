package uts.wsd.converter;

import uts.wsd.model.Article;
import uts.wsd.model.Author;

import java.util.LinkedList;
import java.util.Iterator;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public class SchemaConverter extends CollectionConverter {

        public SchemaConverter(Mapper mapper) {
            super(mapper);
        }

        @Override
        public boolean canConvert(Class type) {
            return type.equals(LinkedList.class);
        }

        @Override
        public void marshal(Object source, HierarchicalStreamWriter writer,
                MarshallingContext context) {
            LinkedList list = (LinkedList)source;

            writer.addAttribute("xmlns", "http://rerun.com");
            writer.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

            if (list.element().getClass() == Article.class)
                writer.addAttribute("xsi:schemaLocation", "http://rerun.com schema/articles.xsd");
            else if (list.element().getClass() == Author.class)
                writer.addAttribute("xsi:schemaLocation", "http://rerun.com schema/authors.xsd");

            
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Object item = iterator.next();
                writeItem(item, context, writer);
            }
            
        }
}