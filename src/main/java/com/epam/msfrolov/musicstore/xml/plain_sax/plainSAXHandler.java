package com.epam.msfrolov.musicstore.xml.plain_sax;

import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.xml.util.QueueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;

public class PlainSAXHandler<T> extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(PlainSAXHandler.class);

    private User result;
    private Object currentObject;


    private QueueMap<Object, Field> queueMap = new QueueMap<>();

    public PlainSAXHandler(Class<T> clazz) {
        result = new User();
    }

    public T getResult() {
        return (T) result;
    }


    @Override
    public void startDocument() throws SAXException {
        log.info("startDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        log.info("  < {}", localName);


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        log.info("     = ch /{}/", new String(ch, start, length).trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        log.info("  > {}", localName);
    }

    @Override
    public void endDocument() throws SAXException {
        log.info("endDocument");
    }
}
