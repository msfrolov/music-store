package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Album extends CommercialMultimediaEntity implements  Tracklist {
    private static int INDEX;
    private ArrayList<Track> tracklist;

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
        if (!this.tracklist.contains(file)) {
            tracklist.add(file);
            this.setDuration(this.getDuration().plus(file.getDuration()));
            this.setPrice(this.getPrice().plus(file.getPrice()));
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
}
