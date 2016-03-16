package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.xml.jaxb.JAXBParser;
import com.epam.msfrolov.musicstore.xml.sax.PlainSAXParser;
import com.epam.msfrolov.musicstore.xml.sax.SAXParser;

import static com.epam.msfrolov.musicstore.xml.ParserFactory.BuilderType.*;

public class ParserFactory {

    public <T> Parser create(String builderType, Class<T> clazz) {
        Parser<T> parser = null;
        BuilderType type = valueOf(builderType.toUpperCase());

        if (type == JAXB) {
            parser = new JAXBParser<>(clazz);
            return parser;
        } else if (type == SAX) {
            parser = new SAXParser<>(clazz);
        } else if (type == STAX) {
            //builder = new StAXBuilder();
        } else if (type == DOM) {
            //parser = new DOMBuilder();
        } else if (type == PLAINSAX) {
            parser = new PlainSAXParser<>(clazz);
        } else
            throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        return parser;
    }


    public enum BuilderType {
        JAXB, SAX, STAX, DOM, PLAINSAX
    }
}
