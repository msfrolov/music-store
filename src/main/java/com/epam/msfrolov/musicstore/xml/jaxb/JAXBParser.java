package com.epam.msfrolov.musicstore.xml.jaxb;

import com.epam.msfrolov.musicstore.xml.Parser;

public class JAXBParser<T> implements Parser<T> {
    private T currentUser;
    private Class clazz;

    public JAXBParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T getInstance() {
        return currentUser;
    }

    @Override
    public void buildSet(String fileName) {
        JAXBHandler<T> jaxbHandler = new JAXBHandler<>(clazz);
        currentUser = jaxbHandler.unmarshal(fileName);
    }
}
