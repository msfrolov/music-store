package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.xml.unparser.JAXBUnparser;

import static com.epam.msfrolov.musicstore.xml.UnparserFactory.BuilderType.JAXB;
import static com.epam.msfrolov.musicstore.xml.UnparserFactory.BuilderType.valueOf;

public class UnparserFactory {
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
