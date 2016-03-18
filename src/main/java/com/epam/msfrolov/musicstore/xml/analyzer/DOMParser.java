package com.epam.msfrolov.musicstore.xml.analyzer;

import com.epam.msfrolov.musicstore.xml.Parser;

public class DOMParser<T> implements Parser<T> {
    Class clazz;

    public DOMParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T parse(String fileName) {
        return null;
    }
}
