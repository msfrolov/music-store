package com.epam.msfrolov.musicstore.xml.sax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler<T> extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(SAXHandler.class);

    private T result;

    public SAXHandler(Class<T> clazz) {
        try {
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public T getResult() {
        return result;
    }

    @Override
    public void startDocument() throws SAXException {
        log.info("startdocument {}", result.getClass());
        log.info("startdocument");
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
        log.info("enddocument");
    }
}
