package com.epam.msfrolov.musicstore.factory;

import com.epam.msfrolov.musicstore.model.Metadata;
import com.epam.msfrolov.musicstore.model.Style;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.util.FileHandling;
import org.joda.money.Money;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

public class TrackFactory {


    private static Random random = new SecureRandom();

    private static String getRandomName() {
        return FileHandling.getRandomLine(FileHandling.TRACK_NAME);
    }

    private static String getRandomStyle() {
        return FileHandling.getRandomLine(FileHandling.MUSIC_STYLE);
    }

    private static String getRandomAlbum() {
        return FileHandling.getRandomLine(FileHandling.TRACK_ALBUM);
    }

    private static String getRandomArtist() {
        return FileHandling.getRandomLine(FileHandling.TRACK_ARTIST);
    }

    private static Long getRandomFileSize() {
        // Get file size from 1 to 9 megabyte - result in bytes;
        return (long) ((random.nextInt(9) + 1) * 1024 * 1024);
    }

    private static String getRandomComment() {
        return FileHandling.getRandomLine(FileHandling.TRACK_ALBUM);
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
