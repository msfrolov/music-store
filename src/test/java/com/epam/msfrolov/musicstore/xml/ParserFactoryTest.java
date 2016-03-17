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
        testBuilder("sax");
    }

    private void testBuilder(String typeBuilder) {
        ParserFactory parserFactory = new ParserFactory();
        Parser<User> parser = parserFactory.create(typeBuilder, User.class);
        User instance = parser.parse("src/test/resources/test.xml");
        log.debug("Oh, it's alive! {}", instance.toStringWithDetails());
    }
}
