package com.epam.msfrolov.musicstore.xml.analyzer;

import com.epam.msfrolov.musicstore.xml.Unparser;

public class JAXBUnparser<T> implements Unparser<T> {

    Class clazz;

    public JAXBUnparser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void unparse(T object, String fileName) {
        JAXBHandler<T> jaxbHandler = new JAXBHandler<>(clazz);
        jaxbHandler.unmarshal(fileName);
    }
}
