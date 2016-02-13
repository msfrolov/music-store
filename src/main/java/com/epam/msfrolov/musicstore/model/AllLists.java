package com.epam.msfrolov.musicstore.model;

import java.util.ArrayList;

public class AllLists {
    private static ArrayList<Tracklist> allLists;

    public static void addTrackList(Tracklist tracklist) {
        if (!allLists.contains(tracklist))
            allLists.add(tracklist);
    }

    public static void recalculateValues(Track track) {
        for (Tracklist allList : allLists) {
            allList.recalculateValues(track);
        }
    }
}

