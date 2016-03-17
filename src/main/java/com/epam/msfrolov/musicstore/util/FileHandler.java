package com.epam.msfrolov.musicstore.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileHandler {
    public static String TRACK_ARTIST = "src/main/resources/track_artist";
    public static String TRACK_NAME = "src/main/resources/track_name";
    public static String TRACK_ALBUM = "src/main/resources/track_album";
    public static String MUSIC_STYLE = "src/main/resources/music_style";
    private static List<String> lines;
    private static String fileName;

    @SuppressWarnings("unchecked")
    public static <T> Map<T, T> getProperty(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(FileHandler.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new FileReadException(e);
        }
        Map<T, T> mapProperties = new HashMap<>();
        for (Object key : properties.keySet()) {
            String value = properties.getProperty((String) key);
            mapProperties.put((T) key, (T) value);
        }
        return mapProperties;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getPropertyValues(String filePath) {
        return (List<T>) (Arrays.asList(getProperty(filePath).values().toArray()));
    }

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

