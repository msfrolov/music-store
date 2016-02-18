package com.epam.msfrolov.musicstore.factory1;

import com.epam.msfrolov.musicstore.model.User;
import com.epam.msfrolov.musicstore.util.ServiceRandom;

public class UserFactory {
    public static User getRandomUser() {
        User user = new User(ServiceRandom.getRandomArtist());
        user.setId(ServiceRandom.getRandomId());
        return user;
    }
}
