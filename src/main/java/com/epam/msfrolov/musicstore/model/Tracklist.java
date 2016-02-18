package com.epam.msfrolov.musicstore.model;

import java.util.Comparator;
import java.util.List;

public interface Tracklist {
    boolean add(Track file);

    boolean remove(Track file);

    void sort(Comparator<Track> comparator);

    List<Track> getList();

    boolean contains(Track track);
}
