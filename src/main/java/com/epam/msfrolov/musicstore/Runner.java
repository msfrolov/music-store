package com.epam.msfrolov.musicstore;

import com.epam.msfrolov.musicstore.model.MusicCollection;
import com.epam.msfrolov.musicstore.model.MusicCollectionFactory;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.model.TrackFactory;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        int numberOfTracks_= 40;
        int numberOfMusicCollection = 10;

        ArrayList<Track> trackArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfTracks_; i++) {
            trackArrayList.add(TrackFactory.createTrack());
        }
        ArrayList<MusicCollection> musicCollectionArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfMusicCollection; i++) {
            musicCollectionArrayList.add(MusicCollectionFactory.createMusicCollection(trackArrayList));
        }

    }
}
