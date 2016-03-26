package com.epam.msfrolov.musicstore.xml.parser;

import com.epam.msfrolov.musicstore.util.FileHandler;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static com.epam.msfrolov.musicstore.util.ReflectUtil.*;

public class XMLHandler<T> {
    private T result;
    private StringBuilder currentCh;
    private Object currentObject;
    private String currentElement;
    private Deque<Object> objects;
    private Deque<String> elements;
    private List<String> classNames;
    private Class clazz;

    public XMLHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    //PARSE
    public void startDocument() {
        currentCh = new StringBuilder();
        objects = new ArrayDeque<>();
        elements = new ArrayDeque<>();
        classNames = FileHandler.getPropertyValues("class.names.properties");
    }

    public void startElement(String localName) {
        currentCh.setLength(0);
        pushElem(localName);
        if (isComplexTypeName(peekElem()) && peekObj() != null) {
            Class currentClass = null;
            if (isCurrentObjectList()) {
                currentClass = getGenericType(peekNextToLastObj().getClass());
            } else if (checkField(peekElem(), peekObj().getClass())) {
                Field currentField = getField(peekElem(), peekObj().getClass());
                assert currentField != null;
                currentClass = currentField.getType();
            }
            Object o;
            if ((o = createInstance(currentClass)) != null) pushObj(o);
        } else if (isCurrentElementRoot()) {
            Object o;
            if ((o = createInstance(clazz)) != null) pushObj(o);
        }
    }

    private boolean isCurrentObjectList() {
        return peekObj() instanceof List;
    }


    public void characters(String s) {
        currentCh.append(s.trim());
    }

    public void characters(char[] ch, int start, int length) {
        currentCh.append(new String(ch, start, length).trim());
    }

    public void endElement() {
        if (isComplexTypeName(peekElem())) {
            if (!isCurrentElementRoot()) {
                if (peekNextToLastObj() instanceof List) {
                    setValue(peekNextToLastObj(), peekObj());
                } else if (checkField(peekElem(), peekNextToLastObj().getClass())) {
                    setValue(peekNextToLastObj(), peekObj(), peekElem());
                }
            }
            popObj();
        } else {
            setValue(peekObj(), currentCh.toString().trim(), peekElem());
        }
        popElem();
    }

    @SuppressWarnings("unchecked")
    public void endDocument() {
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

    public T getResult() {
        return result;
    }

    private boolean isComplexTypeName(String localName) {
        return classNames.contains(localName);
    }

    private boolean isCurrentElementRoot() {
        return peekElem().equalsIgnoreCase(clazz.getSimpleName());
    }
}
