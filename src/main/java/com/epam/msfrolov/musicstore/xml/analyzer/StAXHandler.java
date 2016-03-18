package com.epam.msfrolov.musicstore.xml.analyzer;

import com.epam.msfrolov.musicstore.util.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static com.epam.msfrolov.musicstore.xml.HandlerClasses.*;

public class StAXHandler<T> {
    private static final Logger log = LoggerFactory.getLogger(StAXHandler.class);
    private Class clazz;
    private StringBuilder currentCh;
    private Object currentObject;
    private String currentElement;
    private Deque<Object> objects;
    private Deque<String> elements;
    private List<String> classNames;

    public StAXHandler(Class clazz) {
        this.clazz = clazz;
    }

    public T parse(XMLStreamReader streamReader) {
        T result = null;
        currentCh = new StringBuilder();
        objects = new ArrayDeque<>();
        elements = new ArrayDeque<>();
        classNames = FileHandler.getPropertyValues("class.names.properties");
        try {
            while (streamReader.hasNext()) {
                int type = streamReader.next();
                if (type == XMLStreamReader.START_ELEMENT) {
                    String localName = streamReader.getLocalName();
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
                } else if (type == XMLStreamReader.CHARACTERS) {
                    currentCh.append(streamReader.getText().trim());
                } else if (type == XMLStreamReader.END_ELEMENT) {
                    String localName = streamReader.getLocalName();
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
                } else if (type == XMLStreamReader.END_DOCUMENT) {
                    //noinspection unchecked
                    result = (T) peekObj();
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return result;
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
