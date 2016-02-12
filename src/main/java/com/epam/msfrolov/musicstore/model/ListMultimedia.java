package com.epam.msfrolov.musicstore.model;

import java.util.Comparator;

public interface ListMultimedia {
    boolean add(CommercialMultimediaEntity file);

    boolean remove(CommercialMultimediaEntity file);

    void sort(Comparator comparator);
}
