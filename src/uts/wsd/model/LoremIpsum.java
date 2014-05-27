package uts.wsd.model;

import java.util.LinkedList;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

public class LoremIpsum {
    public String getLipsum() throws ParserConfigurationException, SAXException, IOException {
        Client client = Client.create();
        WebResource webResource = client.resource("http://www.lipsum.com/feed/xml?amount=1&what=paras&start=0");
        ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        
        String output = response.getEntity(String.class);

        return returnParagraph(output);
    }

    private String returnParagraph(String output) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(output));
        Document document = builder.parse(is);
        Element root = document.getDocumentElement();
        Element lipsum = (Element)root.getElementsByTagName("lipsum").item(0);
        return lipsum.getFirstChild().getNodeValue();
    }
}