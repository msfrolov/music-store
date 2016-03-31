package com.epam.msfrolov.musicstore.model;

import javax.xml.bind.annotation.*;
import java.time.Duration;
import java.util.*;

@XmlAccessorType(XmlAccessType.NONE)
public class Playlist extends MultimediaEntity implements Tracklist, Iterable<Track> {

    private final User owner;

    @XmlElementWrapper(name = "tracks")
    @XmlElements({
            @XmlElement(name = "track")
    })
    private List<Track> tracks = new ArrayList<>();

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
        return tracks.iterator();
    }

    @Override
    public boolean add(Track file) {
        if ((owner != User.ADMIN)
                && (owner.getBoughtTracks().contains(file))
                && (!this.tracks.contains(file)) || this.getId().equals(owner.getBoughtTracks().getId())) {
            tracks.add(file);
            this.setDuration(this.getDuration().plus(file.getDuration()));
            return true;
        }
        return false;
    }

    public boolean addBuy(Track file) {
        if ((owner != User.ADMIN)
                && (owner.getBoughtTracks().contains(file))
                && (!this.tracks.contains(file))) {
            System.out.println("ADD  " + file);
            tracks.add(file);
            this.setDuration(this.getDuration().plus(file.getDuration()));
            return true;
        }
        return false;
    }


    @Override
    public boolean remove(Track file) {
        if (this.tracks.indexOf(file) < 0) {
            tracks.remove(file);
            this.setDuration(this.getDuration().minus(file.getDuration()));
            return true;
        }
        return false;
    }

    @Override
    public void sort(Comparator<Track> comparator) {
        Collections.sort(this.tracks, comparator);
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
        return Collections.unmodifiableList(tracks);
    }

    public int size() {
        return tracks.size();
    }

    @Override
    public String toString() {
        return "PLAYLIST {" +
                " name: " + this.getName() +
                " number of tracks: " + this.tracks.size() +
                " duration: " + Track.durationFormat(getDuration()) +
                '}';
    }

    public String toStringList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PLAYLIST {");
        stringBuilder.append("\n");
        for (Track track : tracks) {
            stringBuilder.append(track);
            stringBuilder.append("\n");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    @Override
    public boolean contains(Track track) {
        return tracks.contains(track);
    }
}

