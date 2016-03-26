package com.epam.msfrolov.musicstore.xml.analyzer;

import com.epam.msfrolov.musicstore.xml.Parser;
import com.epam.msfrolov.musicstore.xml.XMLParserException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMParser<T> implements Parser<T> {
    Class clazz;
    XMLHandler<T> xmlHandler;

    public DOMParser(Class<T> clazz) {
        this.clazz = clazz;
        xmlHandler = new XMLHandler<>(clazz);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public T parse(String fileName) throws XMLParserException {
        Document document = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(fileName);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        xmlHandler.startDocument();
        Element element = document.getDocumentElement();
        selectNode(element);
        xmlHandler.endDocument();
        return xmlHandler.getResult();

    }

    private void selectNode(Node node) {
        if (node.getNodeType() == 1) {
            Element el = (Element) node;
            String localName = el.getTagName().trim();
            xmlHandler.startElement(localName);

        } else if (node.getNodeType() == 3) {
            Text el = (Text) node;
            String character = el.getWholeText();
            xmlHandler.characters(character);
        }
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                selectNode(item);
            }
            xmlHandler.endElement();
        }
    }

}
