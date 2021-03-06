package com.epam.msfrolov.musicstore.model;

public class Metadata extends BaseEntity{
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
}
