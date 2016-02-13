package com.epam.msfrolov.musicstore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Playlist extends MultimediaEntity implements Indexable, TrackList {

    private static int INDEX;
    private final User owner;
    private ArrayList<Track> tracklist;

    public Playlist() {
        this.setId(createIndex());
        this.owner = User.ADMIN;
    }

    public Playlist(User owner) {
        this.setId(createIndex());
        this.owner = owner;

    }

    public Playlist(User owner, String name) {
        this.setId(createIndex());
        this.setName(name);
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
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

