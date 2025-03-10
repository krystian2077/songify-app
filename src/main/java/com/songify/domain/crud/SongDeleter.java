package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Log4j2
@Service
@Transactional
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongDeleter {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;
    private final GenreDeleter genreDeleter;

    void deleteById(Long id) {
        log.info("deleting song by id: {}", id);
        songRepository.deleteById(id);
    }


    void deleteAllSongsById(Set<Long> songsIds) {
        songRepository.deleteByIdIn(songsIds);
    }
}
