package com.songify.domain.crud;

import com.songify.domain.crud.dto.GenreDto;
import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.dto.SongLanguageDto;
import com.songify.domain.crud.dto.SongRequestDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongAdder {

    private final SongRepository songRepository;

    SongDto addSong(SongRequestDto songDto) {
        SongLanguageDto language = songDto.language();
        SongLanguage songLanguage = SongLanguage.valueOf(language.name());
        Song song = new Song(songDto.name(), songDto.releaseDate(), songDto.duration(), songLanguage);

        log.info("adding new song: {}", song);

        Song save = songRepository.save(song);
        return new SongDto(save.getId(), save.getName(), new GenreDto(save.getGenre().getId(), save.getGenre().getName()));
    }
}
