package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.xml.jaxb.JAXBBuilder;
import com.epam.msfrolov.musicstore.xml.sax.SAXBuilder;

import static com.epam.msfrolov.musicstore.xml.BuilderFactory.BuilderType.*;

public class BuilderFactory {
    public Builder create(String builderType) {
        return this.create(valueOf(builderType.toUpperCase()));
    }

    public Builder create(BuilderType builderType) {
        if (builderType == JAXB)
            return new JAXBBuilder();
        else if (builderType == SAX)
            return new SAXBuilder();
//        else if (builderType == STAX)
//            return new StAXBuilder();
//        else if (builderType == DOM)
//            return new DOMBuilder();
        else
            throw new EnumConstantNotPresentException(builderType.getDeclaringClass(), builderType.name());
    }

    public enum BuilderType {
        JAXB, SAX, STAX, DOM
    }
}
