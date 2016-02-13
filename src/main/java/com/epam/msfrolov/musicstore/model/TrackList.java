package com.epam.msfrolov.musicstore.model;

import java.util.Comparator;

public interface TrackList {
    boolean add(Track file);

    boolean remove(Track file);

    void sort(Comparator<Track> comparator);
}
