package com.epam.msfrolov.musicstore.xml.jaxb;

import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.xml.Builder;
import com.epam.msfrolov.musicstore.xml.jaxb.JAXBHandler;

public class JAXBBuilder implements Builder{
    private User currentUser;

    @Override
    public User getInstance() {
        return currentUser;
    }

    @Override
    public void buildSet(String fileName) {
        JAXBHandler jaxbHandler = new JAXBHandler();
        currentUser = jaxbHandler.unmarshal(fileName);
    }
}
