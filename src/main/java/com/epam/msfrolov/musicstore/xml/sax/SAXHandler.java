package com.epam.msfrolov.musicstore.xml.sax;

import com.epam.msfrolov.musicstore.xml.util.QueueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.epam.msfrolov.musicstore.xml.util.HandlerClasses.checkPresenceField;
import static com.epam.msfrolov.musicstore.xml.util.HandlerClasses.getAllField;

public class SAXHandler<T> extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(SAXHandler.class);

    private T result;
    private Class<T> clazz;
    private Object currentObject;
    private List<Field> currentList;
    private StringBuilder currentCharacters;

    private QueueMap<Object, List<Field>> queueMap = new QueueMap<>();

    public SAXHandler(Class<T> clazz) {
        this.clazz = clazz;
        currentCharacters = new StringBuilder();
    }

    public T getResult() {
        return result;
    }


    @Override
    public void startDocument() throws SAXException {
        log.info("startDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        log.info("  < {}", localName);
        if (localName.equals(clazz.getName())) {
            //Create main class instance
            try {
                result = clazz.newInstance();
                currentObject = result;
                currentList = new ArrayList<>();
                queueMap.push(currentObject, currentList);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (checkPresenceField(localName, currentObject.getClass())) {
                //fill in current class

                Field field = null;
                try {
                    field = currentObject.getClass().getField(localName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                currentList.add(field);

            } else {
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        log.info("     = ch /{}/", new String(ch, start, length).trim());
        currentCharacters.append(ch, start, length);
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
