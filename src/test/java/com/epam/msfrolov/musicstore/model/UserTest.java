package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.factory.TrackFactory;
import com.epam.msfrolov.musicstore.factory.UserFactory;
import com.epam.msfrolov.musicstore.util.ServiceRandom;
import com.epam.msfrolov.musicstore.util.StoreService;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @org.junit.Test
    public void testGetBoughtTracks() throws Exception {

        User user = UserFactory.getRandomUser();
        Album album = new Album(ServiceRandom.getRandomStyle());

        for (int i = 0; i < 17; i++) {
            Track track = TrackFactory.createTrack();
            album.add(track);
            StoreService.accrualMoneyInAccount(user, track.getPrice());
        }

        for (Track track : album) {
            StoreService.buyTrack(track, user);
        }

        assertEquals(album.getList(), user.getBoughtTracks().getList());

    }
}