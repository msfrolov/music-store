package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.time.Duration;
import java.util.*;

public class Album extends CommercialMultimediaEntity implements Tracklist, Iterable<Track> {

    private List<Track> tracklist;

    private Album() {
        this.tracklist = new ArrayList<>();
    }

    public Album(String name) {
        this();
        this.setName(name);
    }

    @Override
    public Iterator<Track> iterator() {
        return tracklist.iterator();
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
        if (file == null)
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
