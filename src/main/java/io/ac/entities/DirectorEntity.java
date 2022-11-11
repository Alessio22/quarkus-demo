package io.ac.entities;

import io.ac.dtos.DirectorDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "directors")
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
