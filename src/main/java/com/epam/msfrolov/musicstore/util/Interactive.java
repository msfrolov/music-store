package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.model.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Interactive {
    public static void print() {
        Random random = new SecureRandom();

        int numberOfTracks_ = 100;
        int numberOfMusicCollection = 10;

        System.out.println("--------------------Tracks---------------------------");

        ArrayList<Track> trackArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfTracks_; i++) {
            Track newTrack = TrackFactory.createTrack();
            trackArrayList.add(newTrack);
            System.out.println(newTrack);

        }

        System.out.println("---------------------Albums--------------------------");

        ArrayList<Album> albumArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfMusicCollection; i++) {
            Album newAlbum = MusicCollectionFactory.createMusicCollection(trackArrayList);
            albumArrayList.add(newAlbum);
            System.out.println(newAlbum);
        }

        System.out.println("--------------------Users---------------------------");

        User user = User.createRandomUser();
        user.plusMoney(1000);
        System.out.println(user);

        Playlist userPlaylist = user.createPlaylist(FileHandling.getRandomLine(FileHandling.MUSIC_STYLE));

        user.buyAlbum(albumArrayList.get(random.nextInt(albumArrayList.size())), userPlaylist);

        user.buyTrack(trackArrayList.get(random.nextInt(trackArrayList.size())), userPlaylist);

        System.out.println(userPlaylist);
    }
}
