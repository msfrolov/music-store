package com.epam.msfrolov.musicstore.model;

import java.util.Comparator;

public class Playlist extends MultimediaEntity implements Indexable, ListMultimedia {

    private static int INDEX;
    private final User owner;

    public Playlist(User owner) {
        this.setId(createIndex());
        this.owner = owner;

    }

    public Playlist(String name, User owner) {
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
    public boolean add(CommercialMultimediaEntity file) {
        return false;
    }

    @Override
    public boolean remove(CommercialMultimediaEntity file) {
        return false;
    }

    @Override
    public void sort(Comparator comparator) {

    }
}
