package com.epam.msfrolov.musicstore.xml.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandlerClasses {
    private static final Logger log = LoggerFactory.getLogger(HandlerClasses.class);

    public static boolean checkPresenceField(String fieldName, Class clazz) {
        List<Field> allField = getAllField(clazz);
        for (Field field : allField) {
            log.debug("current field: {}, field name: {}", field.getName(), fieldName);
            if (field.getName().equals(fieldName))
                return true;
        }
        return false;
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


}
