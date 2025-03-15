package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@AllArgsConstructor
class ArtistAdder {

    private final ArtistRepository artistRepository;
    private final AlbumAdder albumAdder;

    ArtistDto addArtist(String name) {
        Artist artist = new Artist(name);
        Artist save = artistRepository.save(artist);
        return new ArtistDto(save.getId(), save.getName());
    }

    ArtistDto addArtistWithDefaultAlbumAndSong(ArtistRequestDto dto) {
        Artist save = saveArtistWithDefaultAlbumAndSong(dto.name());
        return new ArtistDto(save.getId(), save.getName());
    }

    private Artist saveArtistWithDefaultAlbumAndSong(String name) {
        Artist artist = new Artist(name);

        Album album = new Album();
        album.setTitle("default-album: " + UUID.randomUUID());
        album.setReleaseDate(LocalDateTime.now().toInstant(ZoneOffset.UTC));

        Song song = new Song("default-song-name: " + UUID.randomUUID());
        album.addSongToAlbum(song);
        artist.addAlbum(album);
        return artistRepository.save(artist);
    }

    private Artist saveArtist(String name) {
        Artist artist = new Artist(name);
        return artistRepository.save(artist);
    }
}
