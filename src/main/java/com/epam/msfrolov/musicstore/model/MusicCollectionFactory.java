package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.util.FileHandling;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class MusicCollectionFactory {
    public static MusicCollection createMusicCollection(List<Track> trackList) {
        MusicCollection newMusicCollection = new MusicCollection(FileHandling.getRandomLine(FileHandling.MUSIC_STYLE));
        Random random = new SecureRandom();
        int numberOfSongs = (random.nextInt(trackList.size()));
        for (int i = 0; i < numberOfSongs; i++) {
            newMusicCollection.add(trackList.get(random.nextInt(trackList.size())));
        }
        return newMusicCollection;
    }
}
