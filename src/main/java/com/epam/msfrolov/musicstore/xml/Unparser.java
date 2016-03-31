package com.epam.msfrolov.musicstore.xml;

//Marshal
public interface Unparser<T> {
    void unparse(T object, String fileName);
}
