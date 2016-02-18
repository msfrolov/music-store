package com.epam.msfrolov.musicstore.util;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileHandling {
    public static String TRACK_ARTIST = "src/main/resources/track_artist";
    public static String TRACK_NAME = "src/main/resources/track_name";
    public static String TRACK_ALBUM = "src/main/resources/track_album";
    public static String MUSIC_STYLE = "src/main/resources/music_style";
    private static List<String> lines;
    private static String fileName;

    public static String getRandomLine(String fn) throws IllegalArgumentException {
        if (fn == null) throw new IllegalArgumentException();
        fileName = fn;
        readFileToList();
        return lines.get(ServiceRandom.random.nextInt(lines.size()));
}

    private static void readFileToList() {
        lines = new ArrayList<>();
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

