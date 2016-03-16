package com.epam.msfrolov.musicstore.xml.sax;

import com.epam.msfrolov.musicstore.xml.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class PlainSAXParser<T> implements Parser<T> {

    XMLReader reader;
    PlainSAXHandler<T> handler;
    private T result;

    public PlainSAXParser(Class<T> clazz) {
        handler = new PlainSAXHandler<>(clazz);
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    //todo Exception
    @Override
    public void buildSet(String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        result = handler.getResult();
    }

    @Override
    public T getInstance() {
        return result;
    }

}
