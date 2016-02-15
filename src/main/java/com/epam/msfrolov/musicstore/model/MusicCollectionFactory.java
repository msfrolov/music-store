package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.util.FileHandling;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class MusicCollectionFactory {
    public static MusicCollection createMusicCollection(List<Track> trackList){
        String track_name = "src/main/resources/track_name";
        MusicCollection newMusicCollection = new MusicCollection(FileHandling.getRandomLine(track_name));

        Random random = new SecureRandom();
        int numberOfSongs = (random.nextInt(trackList.size()));
        for (int i = 0; i < numberOfSongs; i++) {
            newMusicCollection.add(trackList.get(random.nextInt(trackList.size())));
        }

        return newMusicCollection;
    }
}
