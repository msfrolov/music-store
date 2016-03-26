package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.xml.analyzer.DOMParser;
import com.epam.msfrolov.musicstore.xml.analyzer.JAXBParser;
import com.epam.msfrolov.musicstore.xml.analyzer.SAXParser;
import com.epam.msfrolov.musicstore.xml.analyzer.StAXParser;

import static com.epam.msfrolov.musicstore.xml.ParserFactory.BuilderType.*;

public class ParserFactory {

    public <T> Parser<T> createParser(String parserType, Class<T> clazz) {
        BuilderType type = valueOf(parserType.toUpperCase());
        if (type == JAXB) {
            return new JAXBParser<>(clazz);
        } else if (type == SAX) {
            return new SAXParser<>(clazz);
        } else if (type == STAX) {
            return new StAXParser<>(clazz);
        } else if (type == DOM) {
            return new DOMParser<>(clazz);
        } else
            throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
    }

    public enum BuilderType {
        JAXB, SAX, STAX, DOM
    }
}
