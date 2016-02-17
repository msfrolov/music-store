package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.factory.TrackFactory;
import com.epam.msfrolov.musicstore.factory.UserFactory;
import com.epam.msfrolov.musicstore.util.ServiceRandom;
import com.epam.msfrolov.musicstore.util.StoreService;

import static org.junit.Assert.*;

public class UserTest {

    @org.junit.Test
    public void testGetBoughtTracks() throws Exception {

        User user = UserFactory.getRandomUser();
        Album album = new Album(ServiceRandom.getRandomStyle());
        System.out.println("alb" + album);
        for (int i = 0; i < 17; i++) {
            Track track = TrackFactory.createTrack();
            System.out.println("alb" + album);
            System.out.println(track);
            System.out.println("alb" + album);
            System.out.println(album.add(track));
            System.out.println("alb" + album);
            StoreService.accrualMoneyInAccount(user, track.getPrice());
        }
        for (Track track:album) {
            StoreService.buyTrack(track, user);
        }
       /* System.out.println(user);
        System.out.println(user.getBoughtTracks());*/
        assertEquals(album.getList(), user.getBoughtTracks());


    }
}