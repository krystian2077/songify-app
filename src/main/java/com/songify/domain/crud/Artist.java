package com.songify.domain.crud;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
class Artist extends BaseEntity {

    @Id
    @GeneratedValue(generator = "artist_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "artist_id_seq",
            sequenceName = "artist_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Album> albums = new HashSet<>();

    Artist(final String name) {
        this.name = name;
    }

    void removeAlbum(Album album) {
        albums.remove(album);
    }

    void addAlbum(Album album) {
        albums.add(album);
        album.addArtist(this);
    }
}
