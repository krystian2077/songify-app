package com.songify.domain.songplayer;

import com.songify.domain.crud.SongifyCrudFacade;
import com.songify.domain.crud.dto.SongDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SongPlayerFacadeTest {

    SongifyCrudFacade songifyCrudFacade = mock(SongifyCrudFacade.class);
    YoutubeHttpClient youtubeHttpClient = mock(YoutubeHttpClient.class);

    SongPlayerFacade songPlayerFacade = new SongPlayerFacade(
            songifyCrudFacade,
            youtubeHttpClient
    );

    @Test
    @DisplayName("Should return success when played song with id")
    public void should_return_success_when_played_song_with_id() {
        // given
        when(songifyCrudFacade.findSongDtoById(any())).thenReturn(
                SongDto.builder()
                        .id(1L)
                        .name("mockitoooo")
                        .build());

        when(youtubeHttpClient.playSongByName(any())).thenReturn("success");

        // when
        String result = songPlayerFacade.playSongWithId(1L);

        // then
        assertThat(result).isEqualTo("success");
    }
}