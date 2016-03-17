package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.factory.TrackFactory;
import com.epam.msfrolov.musicstore.factory.UserFactory;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.util.StoreService;
import com.epam.msfrolov.musicstore.xml.jaxb.JAXBHandler;
import org.joda.money.Money;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class SAXTest {
    private static final Logger log = LoggerFactory.getLogger(JAXBTest.class);

    @Test
    public void jaxbTest() throws Exception {
        //GIVEN
        //Create new instance:
        String fileName = "src/test/resources/testJAXB.xml";

        User user = UserFactory.getRandomUser();
        StoreService.accrualMoneyInAccount(user, Money.parse("KZT 150000"));
        for (int i = 0; i < 20; i++) {
            Track track = TrackFactory.createTrack();
            StoreService.buyTrack(track, user);
        }

        //Marshal instance
        JAXBHandler<User> jaxbHandler = new JAXBHandler<>(User.class);
        jaxbHandler.marshal(user, fileName);
        //WHAN
        //Parse with SAX
        ParserFactory parserFactory = new ParserFactory();
        Parser<User> parser = parserFactory.create("sax", User.class);
        User instance = parser.parse(fileName);

        //THAN
        assertEquals(user, instance);

        log.debug("User before serialize {}", "\n" + user.toStringWithDetails());
        log.debug("User after serialize {}", "\n" + instance.toStringWithDetails());
    }
}
