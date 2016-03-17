package com.epam.msfrolov.musicstore.xml.analyzer;

import com.epam.msfrolov.musicstore.xml.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SAXParser<T> implements Parser<T> {

    XMLReader reader;
    SAXHandler<T> handler;

    public SAXParser(Class<T> clazz) {
        handler = new SAXHandler<>(clazz);
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T parse(String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return handler.getResult();
    }
}
