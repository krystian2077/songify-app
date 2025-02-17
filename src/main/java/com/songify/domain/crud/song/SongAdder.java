package com.songify.domain.crud.song;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
class SongAdder {
    private final SongRepository songRepository;

    SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    Song addSong(Song song) {
        log.info("adding new song: {}", song);
        return songRepository.save(song);
    }
}
