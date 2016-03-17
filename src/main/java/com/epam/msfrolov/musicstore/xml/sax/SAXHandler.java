package com.epam.msfrolov.musicstore.xml.sax;

import com.epam.msfrolov.musicstore.util.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static com.epam.msfrolov.musicstore.xml.HandlerClasses.*;

public class SAXHandler<T> extends DefaultHandler {
    private static final Logger log = LoggerFactory.getLogger(SAXHandler.class);
    private T result;
    private StringBuilder currentCh;
    private Object currentObject;
    private String currentElement;
    private Deque<Object> objects;
    private Deque<String> elements;
    private List<String> classNames;
    private Class clazz;

    public SAXHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getResult() {
        return result;
    }

    //PARSE
    @Override
    public void startDocument() throws SAXException {
        currentCh = new StringBuilder();
        objects = new ArrayDeque<>();
        elements = new ArrayDeque<>();
        classNames = FileHandler.getPropertyValues("class.names.properties");
        log.debug("classNames: ", classNames);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        log.info("<startElement> = |{}|", localName);
        pushElem(localName);
        log.debug("Obj   {}/{}", currentObject, objects);
        log.debug("Elem  {}/{}", currentElement, elements);
        if (classNames.contains(localName) && (peekObj() != null
                && ((peekObj() instanceof List) || checkField(localName, peekObj().getClass())))) {
            log.debug("Зашоль");
            Class currentClass;
            if (peekObj() instanceof List) {
                currentClass = getGenericType(peekNextToLastObj().getClass());
                log.debug("FOUND THIS TYPE {}", currentClass);
                log.debug("Obj   {}/{}", currentObject, objects);
                log.debug("Elem  {}/{}", currentElement, elements);
            } else {
                Field currentField = getField(localName, peekObj().getClass());
                log.debug("поля нашель");
                assert currentField != null;
                currentClass = currentField.getType();
            }
            log.debug("currentClass: {}", currentClass);
            Object o;
            if ((o = createInstance(currentClass)) != null) pushObj(o);
        } else if (localName.equalsIgnoreCase(clazz.getSimpleName())) {
            Object o;
            if ((o = createInstance(clazz)) != null) pushObj(o);
        } else if (checkField(localName, peekObj().getClass())){
            log.debug("Simple Field!");
        } else {
            log.debug("Field is not found!");
        }

        log.debug("Obj   {}/{}", currentObject, objects);
        log.debug("Elem  {}/{}", currentElement, elements);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        log.info("<characters> = |{}|", new String(ch, start, length).trim());
        currentCh.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        log.info("<endElement> = |{}|", localName);
        log.debug("Obj   {}/{}", currentObject, objects);
        log.debug("Elm  {}/{}", currentElement, elements);
        if (classNames.contains(localName)){


            
        }


        popElem();
        if (classNames.contains(localName)) {
            popObj();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void endDocument() throws SAXException {
        result = (T) peekObj();
        log.debug("result = {}", result);
    }

    //OBJECTS
    private void pushObj(Object o) {
        if (currentObject != null) {
            objects.addLast(currentObject);
        }
        currentObject = o;
    }

    private Object peekObj() {
        return currentObject;
    }

    private Object peekNextToLastObj() {
        return objects.peekLast();
    }

    private Object popObj() {
        if (objects.size() == 0) {
            return currentObject;
        }
        return currentObject = objects.pollLast();
    }

    //ELEMENTS
    private void pushElem(String s) {
        if (currentElement != null) {
            elements.addLast(currentElement);
        }
        currentElement = s;
    }

    private String peekElem() {
        return currentElement;
    }

    private String peekNextToLastElem() {
        return elements.peekLast();
    }

    private String popElem() {
        return currentElement = elements.pollLast();
    }

}
