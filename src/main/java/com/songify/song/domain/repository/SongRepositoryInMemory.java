package com.songify.song.domain.repository;

import com.songify.song.domain.model.Song;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongRepositoryInMemory implements SongRepository {

    Map<Integer, Song> database = new HashMap<>(Map.of(
            1, new Song("Barcelona", "Alan Walker"),
            2, new Song("Demons", "Imagine Dragons"),
            3, new Song("Lose Control", "Teddy Smith"),
            4, new Song("Wiz Khalifa", "See You Again")
    ));

    @Override
    public Song save(Song song) {
        database.put(database.size() + 1, song);
        return song;
    }

    @Override
    public List<Song> findAll() {
        return database.values().stream().toList();
    }
}
