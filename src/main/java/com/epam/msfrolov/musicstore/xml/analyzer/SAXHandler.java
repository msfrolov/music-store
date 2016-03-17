package com.epam.msfrolov.musicstore.xml.analyzer;

import com.epam.msfrolov.musicstore.util.FileHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static com.epam.msfrolov.musicstore.xml.HandlerClasses.*;

public class SAXHandler<T> extends DefaultHandler {
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
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentCh.setLength(0);
        pushElem(localName);
        if (classNames.contains(localName) && peekObj() != null) {
            Class currentClass = null;
            if (peekObj() instanceof List) {
                currentClass = getGenericType(peekNextToLastObj().getClass());
            } else if (checkField(localName, peekObj().getClass())) {
                Field currentField = getField(localName, peekObj().getClass());
                assert currentField != null;
                currentClass = currentField.getType();
            }
            Object o;
            if ((o = createInstance(currentClass)) != null) pushObj(o);
        } else if (localName.equalsIgnoreCase(clazz.getSimpleName())) {
            Object o;
            if ((o = createInstance(clazz)) != null) pushObj(o);
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentCh.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (classNames.contains(localName)) {
            if (!localName.equalsIgnoreCase(clazz.getSimpleName())) {
                if (peekNextToLastObj() instanceof List) {
                    setValue(peekNextToLastObj(), peekObj());
                } else if (checkField(localName, peekNextToLastObj().getClass())) {
                    setValue(peekNextToLastObj(), peekObj(), peekElem());
                }
            }
        } else {
            setValue(peekObj(), currentCh.toString().trim(), peekElem());
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
