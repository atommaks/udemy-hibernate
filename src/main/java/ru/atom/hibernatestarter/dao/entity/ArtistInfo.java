package ru.atom.hibernatestarter.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class ArtistInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -7504772004129446545L;
    
    private String nickname;
    private String firstname;
    private String lastname;
}
