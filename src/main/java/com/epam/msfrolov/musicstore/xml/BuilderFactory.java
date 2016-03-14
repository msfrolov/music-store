package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.xml.jaxb.JAXBBuilder;
import com.epam.msfrolov.musicstore.xml.plain_sax.PlainSAXBuilder;
import com.epam.msfrolov.musicstore.xml.sax.SAXBuilder;

import static com.epam.msfrolov.musicstore.xml.BuilderFactory.BuilderType.*;

public class BuilderFactory {

    public <T> Builder create(String builderType, Class<T> clazz) {
        Builder<T> builder;
        BuilderType type = valueOf(builderType.toUpperCase());

        if (type == JAXB) {
            builder = new JAXBBuilder<>(clazz);
            return builder;
        } else if (type == SAX) {
            builder = new SAXBuilder<>(clazz);
        }
//        else if (type == STAX)
//            builder = new StAXBuilder();
//        else if (type == DOM)
//            builder = new DOMBuilder();
        else if (type == PLAINSAX)
            builder = new PlainSAXBuilder<>(clazz);
        else
            throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        return builder;
    }


    public enum BuilderType {
        JAXB, SAX, STAX, DOM, PLAINSAX
    }
}
