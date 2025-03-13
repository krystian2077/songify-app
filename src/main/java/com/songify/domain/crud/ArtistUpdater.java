package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ArtistUpdater {

    private final ArtistRetriever artistRetriever;

    ArtistDto updateArtistNameById(Long artistId, String name) {
        Artist artist = artistRetriever.findById(artistId);
        artist.setName(name);
        return new ArtistDto(artist.getId(), artist.getName());
    }
}
