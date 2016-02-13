package com.epam.msfrolov.musicstore.TrackComparators;

import com.epam.msfrolov.musicstore.model.Track;

import java.util.Comparator;

public class DurationComparator implements Comparator<Track> {
    @Override
    public int compare(Track track1, Track track2) {
        return track1.getDuration().compareTo(track2.getDuration());
    }
}