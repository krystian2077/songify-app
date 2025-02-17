package com.songify.infrastructure.controller.error;

import com.songify.domain.crud.song.SongNotFoundException;
import com.songify.infrastructure.controller.SongRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = SongRestController.class)
@Log4j2
public class SongErrorHandler {

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorSongResponseDto handleException(SongNotFoundException exception) {
        log.warn("SongNotFoundException while accessing song");
        return new ErrorSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
