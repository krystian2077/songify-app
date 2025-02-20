package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Log4j2
@Service
@Transactional
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongAdder {
    private final SongRepository songRepository;

    Song addSong(Song song) {
        log.info("adding new song: {}", song);
//        song.setAlbum(Album.builder()
//                .id(2L)
//                .build());
        song.setDuration(200L);
        song.setReleaseDate(Instant.now());
        return songRepository.save(song);
    }
}
