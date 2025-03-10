package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class GenreDeleter {

    private final GenreRepository genreRepository;

    void deleteById(Long genreId) {
        int i = genreRepository.deleteById(genreId);
        if (i != 1) {
            throw new GenreWasNotDeletedException("Genre with id: " + genreId + " was not deleted");
        }
    }
}
