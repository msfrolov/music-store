package com.epam.msfrolov.musicstore.xml.sax;

import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.xml.HandlerClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.epam.msfrolov.musicstore.xml.HandlerClasses.getField;
import static com.epam.msfrolov.musicstore.xml.HandlerClasses.getStringAsClassName;

public class PlainSAXHandler<T> extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(PlainSAXHandler.class);

    private User result;
    private LinkedList<String> elements;
    private LinkedList<Object> objects;
    private Object currentObject;
    private StringBuilder stringBuilder;
    private List<String> invalidClasses; //are placed a string that are not name of classes.
    private String modelPackage = "com.epam.msfrolov.musicstore.model.";

    public PlainSAXHandler(Class<T> clazz) {
        this.elements = new LinkedList<>();
        this.objects = new LinkedList<>();
        this.stringBuilder = new StringBuilder();
        this.invalidClasses = new ArrayList<>();

    }

    public T getResult() {
        return (T) result;
    }


    @Override
    public void startDocument() throws SAXException {
        log.info("statDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        log.info("  < {}", localName);

        stringBuilder.setLength(0);
        pushElement(localName);
        log.debug("print elements: {}", elements);

        String className = getStringAsClassName(localName);
        //if ()
        log.debug(" - 1add elements  {}: ", elements);
        log.debug(" - 1add objects   {}: ", objects);
        if ("list".equalsIgnoreCase(localName)){
            log.debug(" -  LIST add elements  {}: ", elements);
            log.debug(" - LIST add objects   {}: ", objects);
            //pushObject(ArrayList);
    }else if(!invalidClasses.contains(localName)) {
            try {
                log.debug("try create class: {}", modelPackage + className);
                Class clazz = Class.forName(modelPackage + className);
                pushObject(clazz.newInstance());
            } catch (ClassNotFoundException e) {
                log.debug("cannot create a class: {}", modelPackage + className);
                invalidClasses.add(localName);
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("cannot create an instance of the class: {}" + modelPackage + className, e);
            }
        }
        log.debug(" - 2add elements  {}: ", elements);
        log.debug(" - 2add objects   {}: ", objects);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        log.info("     = ch /{}/  Last elem:" + lastElement() + "///", new String(ch, start, length).trim());
        stringBuilder.append(ch, start, length);
        if (elements.size() == 1) {
            log.debug("nothing");
        } else if (lastElement().equalsIgnoreCase("duration")
                || lastElement().equalsIgnoreCase("price")
                || lastElement().equalsIgnoreCase("style")
                || lastElement().equalsIgnoreCase("value")
                || lastElement().equalsIgnoreCase("boughttracks")) {
            log.debug("DEBUG");
        } else if (lastElement().equalsIgnoreCase(lastObject().getClass().getSimpleName()) && objects.size() > 1) {//if we are in the method of "character" of the CURRENT CLASS
            try {
                Field currentField = getField(lastElement(), nextToLastObject().getClass()); //get field of next to last object for to set the current object in it
                currentField.setAccessible(true);
                currentField.set(nextToLastObject(), lastObject());
            } catch (NullPointerException e) {
                e.printStackTrace();
                log.debug("field " + "\"" + lastElement() + "\"" + " is not found, in LTN Cl:" + nextToLastObject().getClass(), e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.debug("wtf", e);
            }
        } else if (lastElement().equalsIgnoreCase(nextToLastObject().getClass().getSimpleName())) {  //if we are in the method of "character" of the FIELDS current class
            if (HandlerClasses.checkContainsField(lastElement(), lastObject().getClass())) {
                try {
                    Field currentField = getField(lastElement(), lastObject().getClass());
                    currentField.setAccessible(true);
                    currentField.set(lastObject(), stringBuilder);
                } catch (NullPointerException e) {
                    log.debug("field " + "\"" + lastElement() + "\"" + " is not found, in Last Cl:" + lastObject().getClass(), e);
                } catch (IllegalAccessException e) {
                    log.debug("wtf", e);
                }
            }
        } else {    //It's impossible =)
            log.debug(" - It's impossible!");
            log.debug(" - there may yet!");
            log.debug(" - elements  {}: ", elements);
            log.debug(" - objects   {}: ", objects);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        log.info("  > {}", localName);

        log.debug(" - before delete elements  {}: ", elements);
        log.debug(" - before delete  objects   {}: ", objects);

        popElements();
        if (localName.equalsIgnoreCase(lastObject().getClass().getSimpleName())) {
            log.debug("delete current object ({})", lastObject());
            popObject();
        }
        log.debug(" - after delete elements  {}: ", elements);
        log.debug(" - after delete  objects   {}: ", objects);
    }


    @Override
    public void endDocument() throws SAXException {
        log.info("endDocument");
    }

    private Object nextToLastObject() {
        if (objects.size() < 2)
            return lastObject();
        return objects.get(objects.size() - 2);
    }

    private Object lastObject() {
        return objects.peekLast();
    }

    private Object popObject() {
        return objects.pollLast();
    }

    private void pushObject(Object object) {
        objects.addLast(object);
    }

    private String lastElement() {
        return elements.peekLast();
    }

    private String nextToLastElement() {
        if (elements.size() < 2)
            return lastElement();
        return elements.get(elements.size() - 2);
    }

    private String popElements() {
        return elements.pollLast();
    }

    private void pushElement(String element) {
        elements.addLast(element);
    }
}
