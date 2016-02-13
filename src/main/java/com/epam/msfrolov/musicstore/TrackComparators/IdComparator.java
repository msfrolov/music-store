package com.epam.msfrolov.musicstore.TrackComparators;

import com.epam.msfrolov.musicstore.model.Track;

import java.util.Comparator;

public class IdComparator implements Comparator<Track> {
    @Override
    public int compare(Track track1, Track track2) {
        return Integer.compare(track1.getId(), track2.getId());
    }
}
