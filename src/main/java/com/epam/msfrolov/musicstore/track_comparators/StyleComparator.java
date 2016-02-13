package com.epam.msfrolov.musicstore.track_comparators;

import com.epam.msfrolov.musicstore.model.Track;

import java.util.Comparator;

public class StyleComparator  implements Comparator<Track> {
    @Override
    public int compare(Track track1, Track track2) {
        return track1.getStyle().compareTo(track2.getStyle());
    }
}