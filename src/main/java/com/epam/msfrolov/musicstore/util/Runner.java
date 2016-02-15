package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.model.Album;
import com.epam.msfrolov.musicstore.model.MusicCollectionFactory;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.model.TrackFactory;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        int numberOfTracks_ = 100;
        int numberOfMusicCollection = 10;

        ArrayList<Track> trackArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfTracks_; i++) {
            Track newTrack = TrackFactory.createTrack();
            trackArrayList.add(newTrack);
            System.out.println(newTrack);

        }
        ArrayList<Album> albumArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfMusicCollection; i++) {
            Album newAlbum = MusicCollectionFactory.createMusicCollection(trackArrayList);
            albumArrayList.add(newAlbum);
            System.out.println(newAlbum);
        }

    }
}
