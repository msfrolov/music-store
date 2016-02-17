package com.epam.msfrolov.musicstore.factory;

import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.util.FileHandling;

public class UserFactory {
    public static User getRandomUser() {
        return new User(FileHandling.getRandomLine(FileHandling.TRACK_ARTIST));
    }
}
