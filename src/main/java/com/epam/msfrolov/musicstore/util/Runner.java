package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.model.Track;

import java.time.Duration;

public class Runner {
    public static void main(String[] args) {
        System.out.println(Track.durationFormat(Duration.ofSeconds(1253L).plusSeconds(1235)));
        Interactive.useExamplesTracks();
        Interactive.useExamplesAlbums();
        Interactive.useExamplesUser();
        Interactive.useUsersPlaylists();
        Interactive.useIterator();
    }
}