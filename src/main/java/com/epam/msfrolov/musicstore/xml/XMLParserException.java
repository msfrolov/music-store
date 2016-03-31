package com.epam.msfrolov.musicstore.xml;

public class XMLParserException extends RuntimeException {
    public XMLParserException(String exceptionMessage, Exception e) {
        super(exceptionMessage, e);
    }
}
