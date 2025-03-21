package com.songify.domain.crud;

import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

class InMemorySongRepository implements SongRepository {

    Map<Long, Song> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public List<Song> findAll(Pageable pageable) {
        return List.of();
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return Optional.ofNullable(db.get(songId));
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateById(Long id, Song newSong) {

    }

    @Override
    public Song save(Song song) {
        long index = this.index.getAndIncrement();
        db.put(index, song);
        song.setId(index);
        song.setGenre(new Genre(1L, "default"));
        return song;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public int deleteByIdIn(Collection<Long> ids) {
        ids.forEach(id -> db.remove(id));
        return 0;
    }
}
