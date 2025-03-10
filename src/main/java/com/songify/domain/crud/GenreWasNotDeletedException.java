package com.songify.domain.crud;

class GenreWasNotDeletedException extends RuntimeException {
    GenreWasNotDeletedException(String message) {
        super(message);
    }
}
