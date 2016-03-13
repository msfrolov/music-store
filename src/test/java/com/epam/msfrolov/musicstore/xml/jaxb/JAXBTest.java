package com.epam.msfrolov.musicstore.xml.jaxb;

import com.epam.msfrolov.musicstore.factory.TrackFactory;
import com.epam.msfrolov.musicstore.factory.UserFactory;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.util.StoreService;
import org.joda.money.Money;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class JAXBTest {
    private static final Logger log = LoggerFactory.getLogger(JAXBTest.class);

    @Test
    public void jaxbTest() throws Exception {
        //GIVEN
        User user = UserFactory.getRandomUser();
        StoreService.accrualMoneyInAccount(user, Money.parse("KZT 150000"));
        for (int i = 0; i < 20; i++) {
            Track track = TrackFactory.createTrack();
            StoreService.buyTrack(track, user);
        }
        log.debug("User before serialize {}", "\n" + user.toStringWithDetails());

        //WHAN
        JAXBHandler<User> jaxbHandler = new JAXBHandler<>(User.class);
        String fileName = "src/test/resources/testJAXB.xml";
        jaxbHandler.marshal(user, fileName);
        User user1 = jaxbHandler.unmarshal(fileName);
        log.debug("User after serialize {}", "\n" + user1.toStringWithDetails());

        //THAN
        assertEquals(user, user1);
    }
}
