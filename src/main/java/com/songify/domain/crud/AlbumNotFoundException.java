package com.songify.domain.crud;

class AlbumNotFoundException extends RuntimeException {
    AlbumNotFoundException(String message) {
        super(message);
    }
}
