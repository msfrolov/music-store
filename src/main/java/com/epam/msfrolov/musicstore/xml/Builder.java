package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.model.User;

public interface Builder<T> {
    T getInstance();
    void buildSet(String fileName);
}
