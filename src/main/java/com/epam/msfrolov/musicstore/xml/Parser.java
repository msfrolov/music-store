package com.epam.msfrolov.musicstore.xml;

//Unmarshal
public interface Parser<T> {
    T parse(String fileName) throws XMLParserException;
}
