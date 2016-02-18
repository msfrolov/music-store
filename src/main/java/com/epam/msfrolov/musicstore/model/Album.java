package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.time.Duration;
import java.util.*;

public class Album extends CommercialMultimediaEntity implements Tracklist, Iterable<Track> {

    private List<Track> value;

    private Album() {
        this.value = new ArrayList<>();
    }

    public Album(String name) {
        this();
        this.setName(name);
        this.setPrice(Money.parse("KZT 0"));
        this.setDuration(Duration.ZERO);
    }

    @Override
    public Iterator<Track> iterator() {
        return value.iterator();
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
        if (file != null && !(this.contains(file))) {
            value.add(file);
            this.setDuration(this.getDuration().plus(file.getDuration()));
            this.setPrice(this.getPrice().plus(file.getPrice()));
            return true;
        }
        return false;

    }

    @Override
    public boolean remove(Track file) {
        if (file != null && this.contains(file)) {
            value.remove(file);
            this.setDuration(this.getDuration().minus(file.getDuration()));
            this.setPrice(this.getPrice().minus(file.getPrice()));
            return true;
        }
        return false;
    }

    @Override
    public void sort(Comparator<Track> comparator) {
        Collections.sort(this.value, comparator);
    }

    @Override
    public String toString() {
        return "{ COLLECTION " +
                " name: " + getName() +
                " number of tracks: =" + value.size() +
                " duration: " + Track.durationFormat(getDuration()) +
                " price: " + getPrice() +
                '}';
    }

    @Override
    public List<Track> getList() {
        return Collections.unmodifiableList(value);
    }

    @Override
    public boolean contains(Track track) {
        return value.contains(track);
    }

}
