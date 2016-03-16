package com.epam.msfrolov.musicstore.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HandlerClasses {
    private static final Logger log = LoggerFactory.getLogger(HandlerClasses.class);

    public static boolean checkContainsField(String fieldName, Class clazz) {
        List<Field> allField = getAllField(clazz);
        for (Field field : allField) {
            log.debug("current field: {}, field name: {}", field.getName(), fieldName);
            if (field.getName().equalsIgnoreCase(fieldName))
                return true;
        }
        return false;
    }

    public static Field getField(String fieldName, Class clazz) {
        for (Field field : getAllField(clazz)) {
            if (field.getName().equalsIgnoreCase(fieldName))
                return field;
        }
        return null;
    }

    public static List<Field> getAllField(Class clazz) {
        List<Field> fields = new ArrayList<>();
        List<Class> classes = getAllSuperClasses(clazz);
        for (Class currentClass : classes) {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
        }
        return fields;
    }

    public static List<Class> getAllSuperClasses(Class clazz) {
        List<Class> classes = new ArrayList<>();
        classes.add(clazz);
        Class currentClass = clazz;
        while (Object.class != (currentClass = currentClass.getSuperclass())) {
            classes.add(currentClass);
        }
        return classes;
    }

    public static List<Object> getClasses() {
        List<Object> classes = new ArrayList<>();
        for (Iterator iterator = sun.misc.Service.providers(com.epam.msfrolov.musicstore.model.BaseEntity.class);
             iterator.hasNext(); ) {
            try {
                classes.add(iterator.next());
            } catch (Throwable e) {
                log.error("Unknown error", e);
            }
        }
        return classes;
    }

    public static String getStringAsClassName(String nameIsInvalid) {
        return (nameIsInvalid.substring(0, 1).toUpperCase() + nameIsInvalid.substring(1));
    }
}
