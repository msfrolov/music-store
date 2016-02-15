package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.model.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Interactive {
    static Random random = new SecureRandom();
    static int numberOfTracks_ = 100;
    static int numberOfMusicCollection = 10;
    static ArrayList<Track> trackArrayList = new ArrayList<>();
    static ArrayList<Album> albumArrayList = new ArrayList<>();
    static User user = User.createRandomUser();
    static Playlist userPlaylist = user.createPlaylist(FileHandling.getRandomLine(FileHandling.MUSIC_STYLE));

    public static void printExamplesUserAndUsersPlaylist() {
        System.out.println("--------------------Users---------------------------");

        user.plusMoney(1000);
        System.out.println(user);

        user.buyAlbum(albumArrayList.get(random.nextInt(albumArrayList.size())), userPlaylist);

        user.buyTrack(trackArrayList.get(random.nextInt(trackArrayList.size())), userPlaylist);

        System.out.println(userPlaylist);
    }

    public static void printExamplesAlbums() {
        System.out.println("---------------------Albums--------------------------");
        for (int i = 0; i < numberOfMusicCollection; i++) {
            Album newAlbum = MusicCollectionFactory.createMusicCollection(trackArrayList);
            albumArrayList.add(newAlbum);
            System.out.println(newAlbum);
        }
    }

    public static void printExamplesTracks() {
        System.out.println("--------------------Tracks---------------------------");

        for (int i = 0; i < numberOfTracks_; i++) {
            Track newTrack = TrackFactory.createTrack();
            trackArrayList.add(newTrack);
            System.out.println(newTrack);

        }
    }
}
