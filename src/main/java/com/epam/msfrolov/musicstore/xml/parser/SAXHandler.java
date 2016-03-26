package com.epam.msfrolov.musicstore.xml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@SuppressWarnings("unchecked")
public class SAXHandler<T> extends DefaultHandler {
    private Class clazz;
    private XMLHandler<T> xmlHandler;

    public SAXHandler(Class<T> clazz) {
        this.clazz = clazz;
        xmlHandler = new XMLHandler<>(this.clazz);
    }

    public T getResult() {
        return (T) xmlHandler.getResult();
    }

    //PARSE
    @Override
    public void startDocument() throws SAXException {
        xmlHandler.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        xmlHandler.startElement(localName);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        xmlHandler.characters(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        xmlHandler.endElement();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void endDocument() throws SAXException {
        xmlHandler.endDocument();
    }

}
