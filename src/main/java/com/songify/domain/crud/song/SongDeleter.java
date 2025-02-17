package com.songify.domain.crud.song;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
class SongDeleter {
    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    SongDeleter(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    void deleteById(Long id) {
        songRetriever.existsById(id);
        log.info("deleting song with id: {}", id);
        songRepository.deleteById(id);
    }
}
