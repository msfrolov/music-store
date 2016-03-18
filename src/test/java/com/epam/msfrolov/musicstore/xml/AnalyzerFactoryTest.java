package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalyzerFactoryTest {

    private static final Logger log = LoggerFactory.getLogger(AnalyzerFactoryTest.class);

    @Test
    public void testParserFactory() throws Exception {
        log.info("Analyzers:");
        log.info("JAXB");
        createFactory("jaxb");
        log.info("SAX");
        createFactory("sax");
        log.info("StAX");
        createFactory("stax");
    }

    private void createFactory(String typeBuilder) {
        AnalyzerFactory analyzerFactory = new AnalyzerFactory();
        Parser<User> parser = analyzerFactory.createParser(typeBuilder, User.class);
        User instance = parser.parse("src/test/resources/test.xml");
        log.info("Unmarshal instance: {}", instance.toStringWithDetails());
    }
}
