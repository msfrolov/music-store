package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Playlist extends MultimediaEntity implements Indexable, Tracklist {

    private static int INDEX;
    private final User owner;
    private ArrayList<Track> tracklist;

    public Playlist() {
        AllLists.addTrackList(this);
        this.setId(createIndex());
        this.owner = User.ADMIN;
    }

    public Playlist(User owner) {
        AllLists.addTrackList(this);
        this.setId(createIndex());
        this.owner = owner;

    }

    public Playlist(User owner, String name) {
        this(owner);
        this.setName(name);
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
    
    @Override
    public void recalculateValues(Track track) {
        if (!tracklist.contains(track)) return;
        Duration totalDuration = Duration.ZERO;
        for (Track currentTrack:this.tracklist) {
            totalDuration = totalDuration.plus(currentTrack.getDuration());
        }
        this.setDuration(totalDuration);
    }
}

