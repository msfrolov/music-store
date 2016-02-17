package com.epam.msfrolov.musicstore.model;

import java.time.Duration;
import java.util.*;

public class Playlist extends MultimediaEntity implements Tracklist, Iterable<Track> {

    private final User owner;
    private List<Track> value = new ArrayList<>();

    public Playlist() {
        this.owner = User.ADMIN;
        this.setDuration(Duration.ZERO);
    }

    public Playlist(User owner) {
        this.owner = owner;
        this.setDuration(Duration.ZERO);
    }

    public Playlist(User owner, String name) {
        this(owner);
        this.setName(name);
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public Iterator<Track> iterator() {
        return value.iterator();
    }

    @Override
    public boolean add(Track file) {
        if (owner != User.ADMIN)
            if (!(owner.getBoughtTracks().indexOf(file)<0))
                return false;
        if (!this.value.contains(file)) {
            value.add(file);
            this.setDuration(this.getDuration().plus(file.getDuration()));
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Track file) {
        if (this.value.indexOf(file)<0) {
            value.remove(file);
            this.setDuration(this.getDuration().minus(file.getDuration()));
            return true;
        }
        return false;
    }

    @Override
    public void sort(Comparator<Track> comparator) {
        Collections.sort(this.value, comparator);
    }

    public Playlist filterByName(String substring) {
        Playlist newPlaylist = new Playlist(this.owner);
        for (Track track : this.getList()) {
            if (track.getName().contains(substring))
                newPlaylist.add(track);
        }
        return newPlaylist;
    }


    public Playlist filterByStyle(Style style) {
        Playlist newPlaylist = new Playlist(this.owner);
        for (Track track : this.getList())
            if (track.getStyle() == style)
                newPlaylist.add(track);
        return newPlaylist;
    }


    public Playlist filterByDuration(Duration min, Duration max) {
        Playlist newPlaylist = new Playlist(this.owner);
        for (Track track : this.getList())
            if (track.getDuration().compareTo(min) >= 0 &&
                    track.getDuration().compareTo(max) <= 0)
                newPlaylist.add(track);
        return newPlaylist;
    }


    @Override
    public List<Track> getList() {
        return Collections.unmodifiableList(value);
    }

    @Override
    public String toString() {
        return "PLAYLIST {" +
                " owner: " + owner +
                " name: " + this.getName() +
                " number of tracks: " + this.value.size() +
                " duration: " + Track.durationFormat(getDuration()) +
                '}';
    }

    public String toStringList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PLAYLIST {");
        stringBuilder.append("\n");
        for (Track track : value) {
            stringBuilder.append(track);
            stringBuilder.append("\n");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}

