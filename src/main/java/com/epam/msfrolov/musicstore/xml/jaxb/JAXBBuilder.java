package com.epam.msfrolov.musicstore.xml.jaxb;

import com.epam.msfrolov.musicstore.xml.Builder;

public class JAXBBuilder<T> implements Builder<T> {
    private T currentUser;
    private Class clazz;

    public JAXBBuilder(Class<T> clazz) {
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
