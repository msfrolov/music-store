package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.model.Playlist;
import com.epam.msfrolov.musicstore.model.Style;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.model.User;

import java.time.Duration;
import java.util.List;

public class PlaylistFilter {

    public static Playlist filterByName(List<Track> source, String substring, User user) {
        Playlist newPlaylist = new Playlist(user);
        for (Track track : source) {
            if (track.getName().contains(substring))
                newPlaylist.add(track);
        }
        return newPlaylist;
    }

    public Playlist filterByStyle(List<Track> source, Style style, User user) {
        Playlist newPlaylist = new Playlist(user);
        for (Track track : source)
            if (track.getStyle() == style)
                newPlaylist.add(track);
        return newPlaylist;
    }

    public Playlist filterByDuration(List<Track> source, Duration min, Duration max, User user) {
        Playlist newPlaylist = new Playlist(user);
        for (Track track : source)
            if (track.getDuration().compareTo(min) >= 0 &&
                    track.getDuration().compareTo(max) <= 0)
                newPlaylist.add(track);

        return newPlaylist;
    }
}