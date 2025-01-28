package com.songify.song.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name = "song")
public class Song {

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String artist;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Song() {

    }

    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public Song(String name, String artist, Long id) {
        this.name = name;
        this.artist = artist;
        this.id = id;
    }
}
