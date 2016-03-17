package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.xml.jaxb.JAXBParser;
import com.epam.msfrolov.musicstore.xml.sax.PlainSAXParser;
import com.epam.msfrolov.musicstore.xml.sax.SAXParser;

import static com.epam.msfrolov.musicstore.xml.ParserFactory.BuilderType.*;

public class ParserFactory {

    public <T> Parser<T> create(String builderType, Class<T> clazz) {
        BuilderType type = valueOf(builderType.toUpperCase());
        if (type == JAXB) {
            return new JAXBParser<>(clazz);
        } else if (type == SAX) {
            return new SAXParser<>(clazz);
        } else if (type == STAX) {
            //return new StAXBuilder();
        } else if (type == DOM) {
            //return new DOMBuilder();
        } else if (type == PLAINSAX) {
            return new PlainSAXParser<>(clazz);
        } else
            throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        return null;
    }

    public enum BuilderType {
        JAXB, SAX, STAX, DOM, PLAINSAX
    }
}
