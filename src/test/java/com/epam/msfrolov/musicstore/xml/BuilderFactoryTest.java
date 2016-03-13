package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderFactoryTest {

    private static final Logger log = LoggerFactory.getLogger(BuilderFactoryTest.class);

    @Test
    public void testBuilders() throws Exception {
        testBuilder("jaxb");
        testBuilder("sax");
    }

    private void testBuilder(String typeBuilder) {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder builder = builderFactory.create(typeBuilder, User.class);
        builder.buildSet("src/test/resources/test.xml");

        Object instance = builder.getInstance();
        User user = (User) instance;

        log.debug("Oh, it's alive! {}", user.toStringWithDetails());
    }
}
