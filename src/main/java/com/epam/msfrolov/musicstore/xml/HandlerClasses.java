package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.model.Style;
import com.epam.msfrolov.musicstore.xml.adapter.DurationXmlAdapter;
import com.epam.msfrolov.musicstore.xml.adapter.IntegerXmlAdapter;
import com.epam.msfrolov.musicstore.xml.adapter.MoneyXmlAdapter;
import com.epam.msfrolov.musicstore.xml.adapter.StyleXmlAdapter;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HandlerClasses {
    private static final Logger log = LoggerFactory.getLogger(HandlerClasses.class);

    public static boolean checkField(String fieldName, Class clasz) {
        List<Field> allField = getAllFields(clasz);
        for (Field field : allField) {
            log.debug("Compare: {} and {} in clazz " + clasz.getSimpleName(), field.getName(), fieldName);
            if (field.getName().equalsIgnoreCase(fieldName))
                return true;
        }
        return false;
    }

    public static Field getField(String fieldName, Class clazz) {
        for (Field field : getAllFields(clazz)) {
            if (field.getName().equalsIgnoreCase(fieldName))
                return field;
        }
        return null;
    }

    public static List<Field> getAllFields(Class clazz) {
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

    public static Object createInstance(Class clazz) {
        Object instance = null;
        try {
            if (clazz == List.class) instance = new ArrayList<>();
            else instance = clazz.newInstance();
        } catch (InstantiationException e) {
            log.error("Constructor is not found!", e); //crush app
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            log.error("No access to create an instance!", e); //crush app
            e.printStackTrace();
        }
        return instance;
    }

    public static Class getGenericType(Class clasz) {
        Class foundClass = null;
        try {
            Field field = clasz.getDeclaredField("tracks");
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
            foundClass = (Class) genericType.getActualTypeArguments()[0];
        } catch (NoSuchFieldException e) {
            log.error("Can not return type of generic!", e);
            e.printStackTrace();
        }
        return foundClass;
    }

    public static void setValue(Object object, Object value) {
        setValue(object, value, "");
    }

    @SuppressWarnings("unchecked")
    public static void setValue(Object object, Object value, String fieldName) {
        try {
            if (object instanceof List) {
                ((List) object).add(value);
                return;
            }
            Field field = getField(fieldName, object.getClass());
            assert field != null;
            field.setAccessible(true);
            //if (value.getClass() == String.class && ((Class) field.getType()) != String.class) {
            Class modifyClass = (Class) field.getType();
            value = modifyTypeValue(value, modifyClass);
            //}
            field.set(object, value);
        } catch (IllegalAccessException e) {
            log.error("Can not set the value in the field!", e);
            e.printStackTrace();
        }
    }

    private static Object modifyTypeValue(Object value, Class clazz) {
        Object object = null;
        String stringValue = "";
        if (value.getClass() == String.class)
            stringValue = (String) value;
        try {
            if (clazz == Integer.class) {
                object = new IntegerXmlAdapter().unmarshal(stringValue);
            } else if (clazz == Money.class) {
                object = new MoneyXmlAdapter().unmarshal(stringValue);
            } else if (clazz == Duration.class) {
                object = new DurationXmlAdapter().unmarshal(stringValue);
            } else if (clazz == Style.class) {
                object = new StyleXmlAdapter().unmarshal(stringValue);
            } else
                object = value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }


}
