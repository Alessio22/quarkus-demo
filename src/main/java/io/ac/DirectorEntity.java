package io.ac;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Entity;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DirectorEntity extends PanacheEntity {
    private String firstname;
    private String lastname;

    public DirectorDTO toDto() {
        return DirectorDTO.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .build();
    }

}
