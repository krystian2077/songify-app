package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SongRetreiver {

    private final SongRepository songRepository;

    SongRetreiver(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll() {
        log.info("retrieving all songs: ");
        return songRepository.findAll();
    }

    public List<Song> findAllLimitedBy(Integer limit) {
        return songRepository.findAll().stream().limit(limit).toList();
    }
}


