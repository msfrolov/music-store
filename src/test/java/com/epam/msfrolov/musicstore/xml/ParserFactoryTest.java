package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserFactoryTest {

    private static final Logger log = LoggerFactory.getLogger(ParserFactoryTest.class);

    @Test
    public void testBuilders() throws Exception {
        //testBuilder("jaxb");
        //testBuilder("sax");
        testBuilder("plainsax");
    }

    private void testBuilder(String typeBuilder) {
        ParserFactory parserFactory = new ParserFactory();
        Parser parser = parserFactory.create(typeBuilder, User.class);
        parser.buildSet("src/test/resources/test.xml");

        Object instance = parser.getInstance();
        User user = (User) instance;

        log.debug("Oh, it's alive! {}", user.toStringWithDetails());
    }
}
