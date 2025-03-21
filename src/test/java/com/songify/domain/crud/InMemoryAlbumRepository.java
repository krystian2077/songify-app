package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class InMemoryAlbumRepository implements AlbumRepository {

    Map<Long, Album> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public Album save(Album album) {
        long index = this.index.getAndIncrement();
        db.put(index, album);
        album.setId(index);
        return album;
    }

    @Override
    public Optional<Album> findById(Long albumId) {
        return Optional.ofNullable(db.get(albumId));

    }

    @Override
    public Set<Album> findAll() {
        return Set.of();
    }

    @Override
    public Optional<AlbumInfo> findAlbumByIdWithSongsAndArtists(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Album> findAllAlbumsByArtistId(Long id) {
        return db.values().stream()
                .filter(album -> album.getArtists()
                        .stream()
                        .anyMatch(artist -> artist.getId().equals(id)))
                .collect(Collectors.toSet());
    }

    @Override
    public int deleteByIdIn(Collection<Long> ids) {
        ids.forEach(id -> db.remove(id));
        return 0;
    }
}
