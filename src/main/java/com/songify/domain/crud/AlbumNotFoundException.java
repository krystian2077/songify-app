package com.songify.domain.crud;

public class AlbumNotFoundException extends RuntimeException {
    AlbumNotFoundException(String message) {
        super(message);
    }
}
