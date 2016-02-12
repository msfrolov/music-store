package com.epam.msfrolov.musicstore.model;

import java.util.Comparator;

public class Album extends CommercialMultimediaEntity implements Indexable, ListMultimedia {
    private static int INDEX;



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

    @Override
    public int createIndex() {
        return INDEX++;
    }
}
