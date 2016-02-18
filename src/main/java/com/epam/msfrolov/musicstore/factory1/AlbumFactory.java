package com.epam.msfrolov.musicstore.factory1;

import com.epam.msfrolov.musicstore.model.Album;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.util.ServiceRandom;

import java.util.List;

public class AlbumFactory {
    public static Album createRandomAlbum(List<Track> trackList) {
        Album newAlbum = new Album(ServiceRandom.getRandomStyle());
        int numberOfSongs = 12;
        for (int i = 0; i < numberOfSongs; i++) {
            newAlbum.add(trackList.get(ServiceRandom.random.nextInt(trackList.size())));
        }

        newAlbum.setId(ServiceRandom.getRandomId());
        return newAlbum;
    }
}
