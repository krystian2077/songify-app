package com.songify.domain.crud.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record AlbumDto(Long id, String name, Set<Long> songsIds) {
}
