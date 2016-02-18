package com.epam.msfrolov.musicstore.util;

import org.joda.money.Money;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

public class ServiceRandom {

    public static Random random = new Random();

    public static String getRandomName() {
        return FileHandling.getRandomLine(FileHandling.TRACK_NAME);
    }

    public static String getRandomStyle() {
        return FileHandling.getRandomLine(FileHandling.MUSIC_STYLE);
    }

    public static String getRandomAlbum() {
        return FileHandling.getRandomLine(FileHandling.TRACK_ALBUM);
    }

    public static String getRandomArtist() {
        return FileHandling.getRandomLine(FileHandling.TRACK_ARTIST);
    }

    public static Long getRandomFileSize() {
        // Get file size from 1 to 9 megabyte - result in bytes;
        return (long) ((random.nextInt(9) + 1) * 1024 * 1024);
    }

    public static String getRandomComment() {
        return FileHandling.getRandomLine(FileHandling.TRACK_ALBUM);
    }

    public static Money getRandomPrise() {
        return Money.parse("KZT " + (random.nextInt(500) + 50));
    }

    public static Duration getRandomDuration() {
        return Duration.ofSeconds((long) (random.nextInt(300) + 61));
    }

    public static Integer getRandomId() {
        // Get file size from 1 to 9 megabyte - result in bytes;
        return random.nextInt(99999);
    }
}
