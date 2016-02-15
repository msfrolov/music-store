package com.epam.msfrolov.musicstore.util;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileHandling {
    private static List<String> lines;
    private static String fileName;

    private static Random rnd = new SecureRandom();

    public static String getRandomLine(String fn) throws IllegalArgumentException {
        if (fn == null) throw new IllegalArgumentException();
        fileName = fn;
        readFileToList();
        return lines.get(rnd.nextInt(lines.size()));
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

