package com.epam.msfrolov.musicstore.factory;

import com.epam.msfrolov.musicstore.model.Album;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.util.ServiceRandom;

import java.util.List;

public class AlbumFactory {
    public static Album createRandomAlbum(List<Track> trackList, int numberOfSong) {
        Album newAlbum = new Album(ServiceRandom.getRandomStyle());
        for (int i = 0; i < numberOfSong; i++) {
            newAlbum.add(trackList.get(ServiceRandom.random.nextInt(trackList.size())));
        }

        newAlbum.setId(ServiceRandom.getRandomId());
        return newAlbum;
    }
}
