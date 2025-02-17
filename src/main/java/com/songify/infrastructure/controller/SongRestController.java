package com.songify.infrastructure.controller;

import com.songify.domain.crud.song.Song;
import com.songify.domain.crud.song.SongCrudFacade;
import com.songify.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.songify.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.infrastructure.controller.dto.response.CreateSongResponseDto;
import com.songify.infrastructure.controller.dto.response.DeleteSongResponseDto;
import com.songify.infrastructure.controller.dto.response.GetAllSongsResponseDto;
import com.songify.infrastructure.controller.dto.response.GetSongResponseDto;
import com.songify.infrastructure.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.songify.infrastructure.controller.dto.response.UpdateSongResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.songify.infrastructure.controller.SongMapper.mapFromCreateSongRequestDtoToSong;
import static com.songify.infrastructure.controller.SongMapper.mapFromPartiallyUpdateSongRequestDtoToSong;
import static com.songify.infrastructure.controller.SongMapper.mapFromSongToCreateSongResponseDto;
import static com.songify.infrastructure.controller.SongMapper.mapFromSongToDeleteSongResponseDto;
import static com.songify.infrastructure.controller.SongMapper.mapFromSongToGetAllSongsResponseDto;
import static com.songify.infrastructure.controller.SongMapper.mapFromSongToGetSongResponseDto;
import static com.songify.infrastructure.controller.SongMapper.mapFromSongToPartiallyUpdateSongResponseDto;
import static com.songify.infrastructure.controller.SongMapper.mapFromSongToUpdateSongResponseDto;
import static com.songify.infrastructure.controller.SongMapper.mapFromUpdateSongRequestDtoToSong;

@RestController
@Log4j2
@RequestMapping("/songs")
@AllArgsConstructor
public class SongRestController {

    private final SongCrudFacade songFacade;

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        List<Song> allSongs = songFacade.findAll(pageable);
        GetAllSongsResponseDto response = mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Long id,
                                                          @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        Song song = songFacade.findSongById(id);
        GetSongResponseDto response = mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        Song song = mapFromCreateSongRequestDtoToSong(request);
        Song savedSong = songFacade.addSong(song);
        CreateSongResponseDto body = mapFromSongToCreateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Long id) {
        songFacade.deleteById(id);
        DeleteSongResponseDto body = mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateSongRequestDto request) {

        Song newSong = mapFromUpdateSongRequestDtoToSong(request);
        songFacade.songFacade(id, newSong);
        UpdateSongResponseDto body = mapFromSongToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Long id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request) {

        Song updatedSong = mapFromPartiallyUpdateSongRequestDtoToSong(request);
        Song savedSong = songFacade.updatePartiallyById(id, updatedSong);
        PartiallyUpdateSongResponseDto body = mapFromSongToPartiallyUpdateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }
}
