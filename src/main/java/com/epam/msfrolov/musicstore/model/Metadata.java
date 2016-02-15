package com.epam.msfrolov.musicstore.model;

public class Metadata {
    public final String ALBUM;
    public final String ARTIST;
    public final Long SIZE_BYTE;
    public final String COMMENT;

    public Metadata(String album, String artist, Long size_byte, String comment) {
        ALBUM = album;
        ARTIST = artist;
        SIZE_BYTE = size_byte;
        COMMENT = comment;
    }

    public String getRandomALBUM() {
        return ALBUM;
    }

    public String getRandomARTIST() {
        return ARTIST;
    }

    public Long getRandomSIZE_BYTE() {
        return SIZE_BYTE;
    }

    public String getRandomCOMMENT() {
        return COMMENT;
    }
}
