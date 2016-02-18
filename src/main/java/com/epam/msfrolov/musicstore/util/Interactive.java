package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.factory1.AlbumFactory;
import com.epam.msfrolov.musicstore.factory1.TrackFactory;
import com.epam.msfrolov.musicstore.factory1.UserFactory;
import com.epam.msfrolov.musicstore.model.*;
import org.joda.money.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Interactive {
    public static Random random = ServiceRandom.random;
    static int numberOfTracks = 100;
    static int numberOfAlbum = 12;
    static int numberOfTracksForList = 100;

    static List<Track> trackArrayList = new ArrayList<>();
    static List<Album> albumArrayList = new ArrayList<>();
    static User user = UserFactory.getRandomUser();
    static Playlist userPlaylist = user.createPlaylist(FileHandling.getRandomLine(FileHandling.MUSIC_STYLE));


    public static void useExamplesTracks() {
        System.out.println("--------------------Tracks---------------------------");

        for (int i = 0; i < numberOfTracks; i++) {
            Track newTrack = TrackFactory.createTrack();
            trackArrayList.add(newTrack);
            System.out.println(newTrack);
        }
    }

    public static void useExamplesAlbums() {
        System.out.println("---------------------Albums--------------------------");

        for (int i = 0; i < numberOfAlbum; i++) {
            Album newAlbum = AlbumFactory.createRandomAlbum(trackArrayList);
            albumArrayList.add(newAlbum);
            System.out.println(newAlbum);
        }

    }

    public static void useExamplesUser() {
        System.out.println("--------------------Users---------------------------");
        System.out.println(user);
        StoreService.accrualMoneyInAccount(user, Money.parse("KZT 99999"));
        System.out.println(user);
        Album randomAlbum = albumArrayList.get(random.nextInt(albumArrayList.size()));
        StoreService.buyAlbum(randomAlbum, user);

        for (Track track:trackArrayList) {
            StoreService.buyTrack(track, user);
        }
    }

    public static void useUsersPlaylists() {
        for (int i = 0; i < numberOfTracks; i++) {
            Track randomTrack = trackArrayList.get(random.nextInt(trackArrayList.size()));
            if (!userPlaylist.add(randomTrack))
                System.out.println("You have not bought this track! " + i );
        }
        System.out.println(userPlaylist);

        System.out.println(userPlaylist.toStringList());

        userPlaylist.sort(Track.COMPARE_STYLE);
        System.out.println("COMPARE_STYLE");
        System.out.println(userPlaylist.toStringList());

        userPlaylist.sort(Track.COMPARE_NAME);
        System.out.println("COMPARE_NAME");
        System.out.println(userPlaylist.toStringList());

        userPlaylist.sort(Track.COMPARE_ID);
        System.out.println("COMPARE_ID");
        System.out.println(userPlaylist.toStringList());

        userPlaylist.sort(Track.COMPARE_DURATION);
        System.out.println("COMPARE_DURATION");
        System.out.println(userPlaylist.toStringList());

        System.out.println("filterByDuration");
        System.out.println(userPlaylist.filterByDuration(Duration.ofSeconds(200), Duration.ofSeconds(300)));

        System.out.println("filterByName");
        System.out.println(userPlaylist.filterByName("Beatles"));

        System.out.println("filterByStyle");
        System.out.println(userPlaylist.filterByStyle(new Style(FileHandling.getRandomLine(FileHandling.MUSIC_STYLE))));
    }

    public static void useIterator() {
        Album randomAlbum = albumArrayList.get(random.nextInt(albumArrayList.size()));

        System.out.println("Iterator Album");
        for (Track track : randomAlbum) {
            System.out.println(track);
        }

        System.out.println("Iterator Playlist");
        for (Track track : userPlaylist) {
            System.out.println(track);
        }

    }
}
