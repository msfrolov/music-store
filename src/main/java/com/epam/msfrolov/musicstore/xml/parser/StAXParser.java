package com.epam.msfrolov.musicstore.xml.parser;

import com.epam.msfrolov.musicstore.xml.Parser;
import com.epam.msfrolov.musicstore.xml.XMLParserException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SuppressWarnings({"Duplicates", "unchecked", "ConstantConditions"})
public class StAXParser<T> implements Parser<T> {
    private Class clazz;

    public StAXParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T parse(String fileName) throws XMLParserException {
        XMLStreamReader streamReader = null;
        try {
            InputStream inputStream = new FileInputStream(new File(fileName));
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            streamReader = xmlInputFactory.createXMLStreamReader(inputStream);
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        XMLHandler<T> xmlHandler = new XMLHandler<>(clazz);
        try {
            xmlHandler.startDocument();
            while (streamReader.hasNext()) {
                int type = streamReader.next();
                if (type == XMLStreamReader.START_ELEMENT) {
                    xmlHandler.startElement(streamReader.getLocalName());
                } else if (type == XMLStreamReader.CHARACTERS) {
                    xmlHandler.characters(streamReader.getText());
                } else if (type == XMLStreamReader.END_ELEMENT) {
                    xmlHandler.endElement();
                } else if (type == XMLStreamReader.END_DOCUMENT) {
                    xmlHandler.endDocument();
                }
            }
        } catch (XMLStreamException e) {
            throw new XMLParserException("StAX parser - XMLStreamException", e);
        }
        return xmlHandler.getResult();
    }
}
