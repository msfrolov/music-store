package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.util.FileHandling;

import java.util.List;
import java.util.Random;

public class MusicCollectionFactory {
    public static Album createMusicCollection(List<Track> trackList) {
        Album newAlbum = new Album(FileHandling.getRandomLine(FileHandling.MUSIC_STYLE));
        Random random = new Random();
        int numberOfSongs = (random.nextInt(trackList.size()));
        for (int i = 0; i < numberOfSongs; i++) {
            newAlbum.add(trackList.get(random.nextInt(trackList.size())));
        }
        return newAlbum;
    }
}
