package com.epam.msfrolov.musicstore.xml.analyzer;

import com.epam.msfrolov.musicstore.xml.Parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StAXParser<T> implements Parser<T> {
    private Class clazz;

    public StAXParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T parse(String fileName) {
        StAXHandler<T> handler = new StAXHandler<>(clazz);
        XMLStreamReader streamReader = null;
        try {
            InputStream inputStream = new FileInputStream(new File(fileName));
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            streamReader = xmlInputFactory.createXMLStreamReader(inputStream);
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return handler.parse(streamReader);
    }
}
