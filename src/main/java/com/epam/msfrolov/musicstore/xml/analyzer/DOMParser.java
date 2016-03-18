package com.epam.msfrolov.musicstore.xml.analyzer;

import com.epam.msfrolov.musicstore.xml.Parser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMParser<T> implements Parser<T> {
    Class clazz;

    public DOMParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T parse(String fileName) {
        DOMHandler<T> domHandler = new DOMHandler<>(clazz);
        Document document = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(fileName);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return domHandler.parse(document);
    }
}
