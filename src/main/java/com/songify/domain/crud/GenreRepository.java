package com.songify.domain.crud;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

interface GenreRepository extends Repository<Genre, Long> {
    Genre save(Genre genre);

    @Modifying
    @Query("delete from Genre g where g.id = :id")
    int deleteById(Long id);
}
