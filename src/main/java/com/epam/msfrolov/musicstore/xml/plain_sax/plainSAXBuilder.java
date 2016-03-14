package com.epam.msfrolov.musicstore.xml.plain_sax;

import com.epam.msfrolov.musicstore.xml.Builder;
import com.epam.msfrolov.musicstore.xml.sax.SAXHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class PlainSAXBuilder<T> implements Builder<T> {

    XMLReader reader;
    PlainSAXHandler<T> handler;
    private T result;

    public PlainSAXBuilder(Class<T> clazz) {
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
