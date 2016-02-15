package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.util.FileHandling;

public class UserFactory {
    public static User getRandomUser() {
        return new User(FileHandling.getRandomLine(FileHandling.TRACK_ARTIST));
    }
}
