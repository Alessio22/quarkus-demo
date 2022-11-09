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
public class MovieEntity extends PanacheEntity {
    private String title;

    public MovieDTO toDto() {
        return MovieDTO.builder()
                .id(this.id)
                .title(this.title)
                .build();
    }
}
