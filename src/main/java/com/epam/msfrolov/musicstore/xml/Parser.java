package com.epam.msfrolov.musicstore.xml;

public interface Parser<T> {
    T getInstance();

    void buildSet(String fileName);
}
