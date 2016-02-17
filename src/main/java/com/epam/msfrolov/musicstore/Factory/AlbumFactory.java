package com.epam.msfrolov.musicstore.factory;

import com.epam.msfrolov.musicstore.model.Album;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.util.ServiceRandom;

import java.util.List;
import java.util.Random;

public class AlbumFactory {
    public static Album createMusicCollection(List<Track> trackList) {
        Album newAlbum = new Album(ServiceRandom.getRandomStyle());
        Random random = new Random();
        int numberOfSongs = (random.nextInt(trackList.size()));
        for (int i = 0; i < numberOfSongs; i++) {
            newAlbum.add(trackList.get(random.nextInt(trackList.size())));
        }
        return newAlbum;
    }
}
