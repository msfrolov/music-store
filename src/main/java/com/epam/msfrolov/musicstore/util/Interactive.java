package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.model.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Interactive {
    static Random random = new SecureRandom();
    static int numberOfTracks_ = 100;
    static int numberOfMusicCollection = 10;
    static int numberOfTracksForList = 50;

    static ArrayList<Track> trackArrayList = new ArrayList<>();
    static ArrayList<Album> albumArrayList = new ArrayList<>();
    static User user = UserFactory.getRandomUser();
    static Playlist firstUserPlaylist = user.createPlaylist(FileHandling.getRandomLine(FileHandling.MUSIC_STYLE));
    static Playlist secondUserPlaylist = user.createPlaylist(FileHandling.getRandomLine(FileHandling.MUSIC_STYLE));


    public static void useExamplesTracks() {
        System.out.println("--------------------Tracks---------------------------");

        for (int i = 0; i < numberOfTracks_; i++) {
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

        user.plusMoney(1000);
        System.out.println(user);

        user.buyAlbum(albumArrayList.get(random.nextInt(albumArrayList.size())), firstUserPlaylist);

        for (int i = 0; i < numberOfTracksForList; i++) {
            user.buyTrack(trackArrayList.get(random.nextInt(trackArrayList.size())), firstUserPlaylist);
        }
    }

    public static void useUsersPlaylists() {
        System.out.println(firstUserPlaylist);
        System.out.println(firstUserPlaylist.toStringList());
        firstUserPlaylist.sort(Track.COMPARE_NAME);
        System.out.println(firstUserPlaylist.toStringList());
        firstUserPlaylist.sort(Track.COMPARE_ID);
        System.out.println(firstUserPlaylist.toStringList());
        firstUserPlaylist.sort(Track.COMPARE_DURATION);
        System.out.println(firstUserPlaylist.toStringList());
    }

    public static void useCollectionFilter() {
        Album filterAlbum =  Album.createAlbumByPriceRange(trackArrayList, 80, 90);
        System.out.println(filterAlbum.toString());
    }
}
