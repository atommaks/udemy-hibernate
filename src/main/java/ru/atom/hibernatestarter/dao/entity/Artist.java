package ru.atom.hibernatestarter.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "artists")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Artist {
    @EmbeddedId
    private ArtistInfo artistInfo;
    private Integer chartPlace;
}
