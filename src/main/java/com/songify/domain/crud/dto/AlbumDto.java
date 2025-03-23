package com.songify.domain.crud.dto;

import lombok.Builder;

@Builder
public record AlbumDto(Long id, String name) {
}
