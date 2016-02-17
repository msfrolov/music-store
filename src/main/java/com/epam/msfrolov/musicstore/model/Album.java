package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Album extends CommercialMultimediaEntity implements Tracklist {

    private List<Track> tracklist;

    private Album() {
        this.tracklist = new ArrayList<>();
    }

    public Album(String name) {
        this();
        this.setName(name);
    }

    @Override
    protected void setPrice(Money price) {
        super.setPrice(price);
    }

    @Override
    protected void setDuration(Duration duration) {
        super.setDuration(duration);
    }

    @Override
    public boolean add(Track file) {
        if (file==null)
            return false;
        if (!this.tracklist.contains(file)) {
            tracklist.add(file);
            this.setDuration(this.getDuration().plus(file.getDuration()));
            if (this.getPrice() == null)
                this.setPrice(file.getPrice());
            else
            this.setPrice(this.getPrice().plus(file.getPrice()));
            return true;
        }
        return false;
    }

    @Override
    public void sort(Comparator<Track> comparator) {
        Collections.sort(this.tracklist, comparator);
    }

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
    public String toString() {
        return "{ COLLECTION " +
                " name: " + getName() +
                " number of tracks: =" + tracklist.size() +
                " duration: " + Track.durationFormat(getDuration()) +
                " price: " + getPrice() +
                '}';
    }

    @Override
    public List<Track> getList() {
        return Collections.unmodifiableList(tracklist);
    }
}
