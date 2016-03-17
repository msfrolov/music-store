package com.epam.msfrolov.musicstore.xml;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class TempClass {
    private static final Logger log = LoggerFactory.getLogger(TempClass.class);

    @Test
    @SuppressWarnings("unchecked")
    public void tempMethod() throws Exception {

        Field tr = TempClass.class.getDeclaredField("tracks");
        ParameterizedType genericType = (ParameterizedType) tr.getGenericType();
        Class clazz = (Class) genericType.getActualTypeArguments()[0];
        System.out.println(clazz.getName());

    }
}
