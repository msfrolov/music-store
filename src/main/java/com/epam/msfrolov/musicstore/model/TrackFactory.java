package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.util.FileHandling;
import org.joda.money.Money;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

public class TrackFactory {
    private static String track_artist = "src/main/resources/track_artist";
    private static String track_name = "src/main/resources/track_name";
    private static String track_album = "src/main/resources/track_album";
    private static String music_style = "src/main/resources/music_style";

    private static Random random = new SecureRandom();

    private static String getRandomName() {
        return FileHandling.getRandomLine(track_name);
    }

    private static String getRandomStyle() {
        return FileHandling.getRandomLine(music_style);
    }

    private static String getRandomAlbum() {
        return FileHandling.getRandomLine(track_album);
    }

    private static String getRandomArtist() {
        return FileHandling.getRandomLine(track_artist);
    }

    private static Long getRandomFileSize() {
        // Get file size from 1 to 9 megabyte - result in bytes;
        return (long) ((random.nextInt(9)+1) * 1024 * 1024);
    }

    private static String getRandomComment() {
        return FileHandling.getRandomLine(track_album);
    }

    private static Money getRandomPrise() {
        return Money.parse("KZT " + (random.nextInt(500) + 50));
    }

    private static Duration getRandomDuration() {
        return Duration.ofSeconds((long) (random.nextInt(300) + 61));
    }

    public static Track createTrack() {
        Metadata details = new Metadata(getRandomAlbum(), getRandomArtist(), getRandomFileSize(), getRandomComment());
        String name = getRandomName();
        Duration duration = getRandomDuration();
        Money price = getRandomPrise();
        Style style = new Style(getRandomStyle());
        return new Track(name, style, duration, price, details);
    }
}
