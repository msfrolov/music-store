package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Playlist extends MultimediaEntity implements Tracklist {

    private final User owner;
    private ArrayList<Track> tracklist = new ArrayList<>();

    public Playlist() {
        this.owner = User.ADMIN;
    }

    public Playlist(User owner) {
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
    public boolean add(Track file) {
        if (!this.tracklist.contains(file)) {
            tracklist.add(file);
            this.setDuration(this.getDuration().plus(file.getDuration()));
            return true;
        }
        return false;
    }

    @Override
    public void sort(Comparator<Track> comparator) {
        Collections.sort(this.tracklist, comparator);
    }

    @Override
    public List<Track> getList() {
        return Collections.unmodifiableList(tracklist);
    }

    @Override
    public String toString() {
        return "PLAYLIST {" +
                " owner: " + owner +
                " name: " + this.getName() +
                " number of tracks: " + this.tracklist.size() +
                " duration: " + (new SimpleDateFormat("mmm:ss").format(new Date(this.getDuration().toMillis()))) +
                '}';
    }
}

