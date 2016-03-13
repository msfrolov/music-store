package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.model.User;

public interface Builder {
    User getInstance();
    void buildSet(String fileName);
}
