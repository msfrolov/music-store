package com.epam.msfrolov.musicstore.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static javax.xml.bind.JAXBContext.newInstance;

public class JAXBHandler<T> {
    private JAXBContext jc;

    public JAXBHandler(Class<T> clazz) {
        try {
            jc = newInstance(clazz);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void marshal(T object, String filePath) {
        try {
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public T unmarshal(String filePath) {
        T object = null;
        try {
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            FileReader reader = new FileReader(filePath);
            object = (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }


}
