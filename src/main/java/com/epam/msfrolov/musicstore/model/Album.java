package com.epam.msfrolov.musicstore.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Album extends CommercialMultimediaEntity implements Indexable, TrackList {
    private static int INDEX;
    private ArrayList<Track> tracklist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Album album = (Album) o;

        return tracklist != null ? tracklist.equals(album.tracklist) : album.tracklist == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tracklist != null ? tracklist.hashCode() : 0);
        return result;
    }

    @Override
    public int createIndex() {
        return INDEX++;
    }

    @Override
    public boolean add(Track file) {
        if (!this.tracklist.contains(file)) {
            tracklist.add(file);

            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Track file) {
        if (!this.tracklist.contains(file)) {
            tracklist.remove(file);
            return true;
        }
        return false;
    }

    @Override
    public void sort(Comparator<Track> comparator) {
        Collections.sort(this.tracklist, comparator);
    }
}
