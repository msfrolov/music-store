package com.epam.msfrolov.musicstore.xml.sax;

import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.xml.Builder;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SAXBuilder implements Builder {

    XMLReader reader;
    SAXHandler handler;
    private User result;

    //todo Exception
    public SAXBuilder() {
        handler = new SAXHandler();
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        result = handler.getResult();
    }

    @Override
    public User getInstance() {
        return result;
    }

}
