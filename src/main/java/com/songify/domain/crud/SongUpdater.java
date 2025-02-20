package com.songify.domain.crud;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongUpdater {

    private final SongRepository songRepository;

    void updateById(Long id, Song newSong) {
        songRepository.updateById(id, newSong);
    }


//    Song updatePartiallyById(Long id, Song songFromRequest) {
//        Song songFromDatabase = songRetriever.findSongById(id);
//        Song.SongBuilder builder = Song.builder();
//
//        if (songFromRequest.getName() != null) {
//            builder.name(songFromRequest.getName());
//        } else {
//            builder.name(songFromDatabase.getName());
//        }
//        if (songFromRequest.getArtist() != null) {
//            builder.artist(songFromRequest.getArtist());
//        } else {
//            builder.artist(songFromDatabase.getArtist());
//        }
//        Song toSave = builder.build();
//        updateById(id, toSave);
//        return toSave;
//    }

    // Dirty checking version
//    public void updateById(Long id, Song newSong) {
//        Song songById = songRetriever.findSongById(id);
//        songById.setName(newSong.getName());
//        songById.setArtist(newSong.getArtist());
//    }
}
