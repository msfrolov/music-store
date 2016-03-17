package com.epam.msfrolov.musicstore.xml;

import java.io.IOException;

public class XMLParserException extends RuntimeException {
    public XMLParserException(Exception e) {
        super(e);
    }
}
