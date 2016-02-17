package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.model.*;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Random;

public class Interactive {
    static Random random = new Random();
    static int numberOfTracks = 100;
    static int numberOfMusicCollection = 10;
    static int numberOfTracksForList = 50;

    static ArrayList<Track> trackArrayList = new ArrayList<>();
    static ArrayList<Album> albumArrayList = new ArrayList<>();
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

        for (int i = 0; i < numberOfMusicCollection; i++) {
            Album newAlbum = MusicCollectionFactory.createMusicCollection(trackArrayList);
            albumArrayList.add(newAlbum);
            System.out.println(newAlbum);
        }

    }

    public static void useExamplesUser() {
        System.out.println("--------------------Users---------------------------");
        System.out.println(user);
        StoreService.accrualMoneyInAccount(user, Money.parse("KZT 5000"));
        System.out.println(user);
        Album randomAlbum = albumArrayList.get(random.nextInt(albumArrayList.size()));
        StoreService.buyAlbum(randomAlbum, user);

        for (int i = 0; i < numberOfTracksForList; i++) {
            Track randomTrack = trackArrayList.get(random.nextInt(trackArrayList.size()));
            StoreService.buyTrack(randomTrack, user);
        }

    }

    public static void useUsersPlaylists() {
        for (int i = 0; i < numberOfTracks; i++) {
            Track randomTrack = trackArrayList.get(random.nextInt(trackArrayList.size()));
            if (!userPlaylist.add(randomTrack))
                System.out.println("You have not bought this track!");
        }
        System.out.println(userPlaylist);
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
        //TODO
        /*
        System.out.println(userPlaylist.filterByDuration());
        System.out.println(userPlaylist.filterByName());
        System.out.println(userPlaylist.filterByStyle(g));*/
    }
}
