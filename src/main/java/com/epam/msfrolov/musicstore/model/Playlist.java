package com.epam.msfrolov.musicstore.model;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Playlist extends MultimediaEntity implements Tracklist {

    private final User owner;
    private List<Track> value = new ArrayList<>();

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
        if (!this.value.contains(file)) {
            value.add(file);
            this.setDuration(this.getDuration().plus(file.getDuration()));
            return true;
        }
        return false;
    }

    @Override
    public void sort(Comparator<Track> comparator) {
        Collections.sort(this.value, comparator);
    }

    public Playlist filterByName(Playlist source, String substring, User user, String name) {
        Playlist newPlaylist = new Playlist(user, name);
        for (Track track : source.getList()) {
            if (track.getName().contains(name))
                newPlaylist.add(track);
        }
        return newPlaylist;
    }

    public Playlist filterByStyle(Playlist source, Style style, User user) {
        Playlist newPlaylist = new Playlist(user);
        for (Track track : source.getList())
            if (track.getStyle() == style)
                newPlaylist.add(track);
        return newPlaylist;
    }

    public Playlist filterByDuration(Playlist source, Duration min, Duration max, User user) {
        Playlist newPlaylist = new Playlist(user);
        for (Track track : source.getList())
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
                " duration: " + (new SimpleDateFormat("mmm:ss").format(new Date(this.getDuration().toMillis()))) +
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

