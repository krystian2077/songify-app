package com.songify.domain.crud;

import com.songify.domain.crud.dto.GenreDto;
import com.songify.domain.crud.dto.SongDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongRetriever {

    private final SongRepository songRepository;

    Song findSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " not found"));
    }

    List<SongDto> findAll(Pageable pageable) {
        log.info("retrieving all songs: ");
//        return songRepository.findAll(pageable)
//                .stream()
//                .map(song -> SongDto.builder()
//                        .id(song.getId())
//                        .name(song.getName())
//                        .name(song.getName())
//                        .build())
//                .toList();

        List<SongDto> songDtoList = new ArrayList<>();
        List<Song> songs = songRepository.findAll(pageable);

        for (Song song : songs) {
            Genre genre = song.getGenre();
            GenreDto genreDto = new GenreDto(genre.getId(), genre.getName());

            SongDto songDto = SongDto.builder()
                    .id(song.getId())
                    .name(song.getName())
                    .genre(genreDto)
                    .build();

            songDtoList.add(songDto);
        }
        return songDtoList;
    }

    SongDto findSongDtoById(Long id) {
        return songRepository.findById(id)
                .map(song -> SongDto.builder()
                        .id(song.getId())
                        .name(song.getName())
                        .build())
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " not found"));
    }

    void existsById(Long id) {
        if (!songRepository.existsById(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
    }
}


