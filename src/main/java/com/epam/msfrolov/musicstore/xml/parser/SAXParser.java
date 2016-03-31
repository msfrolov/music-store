package com.epam.msfrolov.musicstore.xml.parser;

import com.epam.msfrolov.musicstore.xml.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SAXParser<T> implements Parser<T> {
    Class clazz;

    public SAXParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T parse(String fileName) {
        SAXHandler<T> handler = new SAXHandler<>(clazz);
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            e.printStackTrace();

        }
        return handler.getResult();
    }
}
