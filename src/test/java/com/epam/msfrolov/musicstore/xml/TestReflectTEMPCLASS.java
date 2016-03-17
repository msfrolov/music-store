package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.factory.TrackFactory;
import com.epam.msfrolov.musicstore.factory.UserFactory;
import com.epam.msfrolov.musicstore.model.Playlist;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.util.StoreService;
import org.joda.money.Money;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestReflectTEMPCLASS {
    private static final Logger log = LoggerFactory.getLogger(TestReflectTEMPCLASS.class);

    @Test
    public void testReflect() throws Exception {
        User user = UserFactory.getRandomUser();
        StoreService.accrualMoneyInAccount(user, Money.parse("KZT 150000"));
        for (int i = 0; i < 20; i++) {
            Track track = TrackFactory.createTrack();
            StoreService.buyTrack(track, user);
        }
        log.debug("User example {}", "\n" + user.toStringWithDetails());

        log.debug("getDeclaringClass {}", user.getClass().getDeclaringClass());

        Field[] declaredFields = user.getClass().getDeclaredFields();
        log.debug("declaredFields");
        for (Field field : declaredFields) {
            log.debug("field: {}", field);
            log.debug("  // name {} //   type {}", field.getName(), field.getType());
        }


    }

    private List<Track> tracks;

    @Test
    @SuppressWarnings("unchecked")
    public void test1123() throws Exception {
//        List<Track> tracks = new ArrayList<>();
//        tracks.add(TrackFactory.createTrack());
//        tracks.add(TrackFactory.createTrack());
//        tracks.add(TrackFactory.createTrack());
//
//        Object o = (Object) tracks;
//
//        List<Track> tracks1 = (List<Track>) o;
//        System.out.println("Warning!");
//        System.out.println(tracks1);

        Field tr = TestReflectTEMPCLASS.class.getDeclaredField("tracks");
        ParameterizedType genericType = (ParameterizedType) tr.getGenericType();
        Class clazz = (Class) genericType.getActualTypeArguments()[0];
        System.out.println(clazz.getName());

    }
}
