package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.xml.analyzer.*;

import static com.epam.msfrolov.musicstore.xml.AnalyzerFactory.BuilderType.*;

public class AnalyzerFactory {

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

    public <T> Unparser<T> createUnparser(String unparserType, Class<T> clazz) {
        BuilderType type = valueOf(unparserType.toUpperCase());
        if (type == JAXB) {
            return new JAXBUnparser<>(clazz);
        } else
            throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
    }


    public enum BuilderType {
        JAXB, SAX, STAX, DOM
    }
}
