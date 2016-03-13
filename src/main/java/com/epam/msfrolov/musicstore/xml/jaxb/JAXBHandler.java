package com.epam.msfrolov.musicstore.xml.jaxb;

import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.xml.Builder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static javax.xml.bind.JAXBContext.newInstance;

public class JAXBHandler{
    private JAXBContext jc;
    private User currentUser;

    public JAXBHandler() {
        try {
            jc = newInstance(User.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void marshal(User user, String filePath){
        try {
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(user, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public User unmarshal(String filePath){
        User user = null;
        try {
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            FileReader reader = new FileReader(filePath);
            user = (User) unmarshaller.unmarshal(reader);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }


}
