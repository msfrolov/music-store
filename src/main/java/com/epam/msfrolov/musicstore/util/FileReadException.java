package com.epam.msfrolov.musicstore.util;

import java.io.IOException;

public class FileReadException extends RuntimeException {
    public FileReadException(IOException e) {
        super(e);
    }
}
