package com.epam.msfrolov.musicstore.xml.jaxb;

import com.epam.msfrolov.musicstore.xml.Parser;

public class JAXBParser<T> implements Parser<T> {
    private Class clazz;

    public JAXBParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T parse(String fileName) {
        JAXBHandler<T> jaxbHandler = new JAXBHandler<>(clazz);
        return jaxbHandler.unmarshal(fileName);
    }
}
