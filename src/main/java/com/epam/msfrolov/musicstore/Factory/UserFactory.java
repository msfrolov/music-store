package com.epam.msfrolov.musicstore.factory;

import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.util.ServiceRandom;

public class UserFactory {
    public static User getRandomUser() {
        return new User(ServiceRandom.getRandomArtist());
    }
}
