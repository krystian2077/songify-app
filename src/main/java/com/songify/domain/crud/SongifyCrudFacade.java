package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.dto.AlbumInfo;
import com.songify.domain.crud.dto.AlbumRequestDto;
import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import com.songify.domain.crud.dto.GenreDto;
import com.songify.domain.crud.dto.GenreRequestDto;
import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.dto.SongRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class SongifyCrudFacade {

    private final SongRetriever songRetriever;
    private final SongUpdater songUpdater;
    private final SongDeleter songDeleter;
    private final SongAdder songAdder;
    private final ArtistAdder artistAdder;
    private final GenreAdder genreAdder;
    private final GenreRetriever genreRetriever;
    private final AlbumAdder albumAdder;
    private final ArtistRetriever artistRetriever;
    private final AlbumRetriever albumRetriever;
    private final ArtistDeleter artistDeleter;
    private final ArtistAssigner artistAssigner;
    private final ArtistUpdater artistUpdater;
    private final GenreAssigner genreAssigner;
    private final SongAssigner songAssigner;

    public void deleteArtistByIdWithAlbumsAndSongs(Long artistId) {
        artistDeleter.deleteArtistByIdWithAlbumsAndSongs(artistId);
    }

    public ArtistDto addArtist(ArtistRequestDto dto) {
        return artistAdder.addArtist(dto.name());
    }

    public AlbumDto addAlbumWithSong(AlbumRequestDto dto) {
        return albumAdder.addAlbum(dto.songIds(), dto.tittle(), dto.releaseDate());
    }

    public void addArtistToAlbum(Long artistId, Long albumId) {
        artistAssigner.addArtistToAlbum(artistId, albumId);
    }

    public ArtistDto addArtistWithDefaultAlbumAndSong(ArtistRequestDto dto) {
        return artistAdder.addArtistWithDefaultAlbumAndSong(dto);
    }

    public ArtistDto updateArtistNameById(Long artistId, String name) {
        return artistUpdater.updateArtistNameById(artistId, name);
    }

    public GenreDto addGenre(GenreRequestDto dto) {
        return genreAdder.addGenre(dto.name());
    }

    public SongDto addSong(SongRequestDto dto) {
        return songAdder.addSong(dto);
    }

    public Set<ArtistDto> findAllArtists(Pageable pageable) {
        return artistRetriever.findAllArtists(pageable);
    }

    public List<SongDto> findAllSongs(Pageable pageable) {
        return songRetriever.findAll(pageable);
    }

    public AlbumInfo findAlbumByIdWithArtistsAndSongs(Long id) {
        return albumRetriever.findAlbumByIdWithArtistsAndSongs(id);
    }

    public SongDto findSongDtoById(Long id) {
        return songRetriever.findSongDtoById(id);
    }

    public void updateSongById(Long id, SongDto newSongDto) {
        songRetriever.existsById(id);
        Song songValidatedAndReadyToUpdate = Song.builder()
                .name(newSongDto.name())
                .build();
        songUpdater.updateById(id, songValidatedAndReadyToUpdate);
    }

    public SongDto updateSongPartiallyById(Long id, SongDto songFromRequest) {
        songRetriever.existsById(id);
        Song songFromDatabase = songRetriever.findSongById(id);
        Song.SongBuilder builder = Song.builder();
        if (songFromRequest.name() != null) {
            builder.name(songFromRequest.name());
        } else {
            builder.name(songFromDatabase.getName());
        }

//        if (songFromRequest.getArtist() != null) {
//            builder.artist(songFromRequest.getArtist());
//        } else {
//            builder.artist(songFromDatabase.getArtist());
//        }
        Song toSave = builder.build();
        songUpdater.updateById(id, toSave);
        return SongDto.builder()
                .id(toSave.getId())
                .name(toSave.getName())
                .build();

    }

    public void deleteSongById(Long id) {
        songRetriever.existsById(id);
        songDeleter.deleteById(id);
    }

    public Set<AlbumDto> findAlbumsByArtistId(Long artistId) {
        return albumRetriever.findAlbumsDtoByArtistId(artistId);
    }

    long countArtistsByAlbumId(Long albumId) {
        return albumRetriever.countArtistsByAlbumId(albumId);
    }

    AlbumDto findAlbumById(Long albumId) {
        return albumRetriever.findDtoById(albumId);
    }

    public Set<AlbumDto> finfAllAlbums() {
        return albumRetriever.findAll();
    }

    public Set<GenreDto> retrieveGenres() {
        return genreRetriever.findAll();
    }

    public void assignGenreToSong(Long genreId, Long songId) {
        genreAssigner.assignGenreToSong(genreId, songId);
    }

    public AlbumDto addSongToAlbum(Long albumId, Long songId) {
        return songAssigner.assignSongToAlbum(albumId, songId);
    }
}
