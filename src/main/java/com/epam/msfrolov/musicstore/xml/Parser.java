package com.epam.msfrolov.musicstore.xml;

public interface Parser<T> {
    T parse(String fileName);
}
