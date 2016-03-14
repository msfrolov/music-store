package com.epam.msfrolov.musicstore.xml;

public interface Builder<T> {
    T getInstance();

    void buildSet(String fileName);
}
