package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserFactoryTest {

    private static final Logger log = LoggerFactory.getLogger(ParserFactoryTest.class);

    @Test
    public void testParserFactory() throws Exception {
        log.info("Analyzers:");
        log.info("JAXB");
        createFactory("jaxb");
        log.info("SAX");
        createFactory("sax");
        log.info("StAX");
        createFactory("stax");
        log.info("DOM");
        createFactory("dom");
    }

    private void createFactory(String typeBuilder) throws XMLParserException {
        ParserFactory parserFactory = new ParserFactory();
        Parser<User> parser = parserFactory.createParser(typeBuilder, User.class);
        User instance = parser.parse("src/test/resources/test.xml");
        log.info("Unmarshal instance: {}", instance.toStringWithDetails());
    }
}
