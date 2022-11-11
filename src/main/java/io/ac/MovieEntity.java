package io.ac;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity extends PanacheEntity {
    private String title;

    @ManyToOne
    private DirectorEntity director;

    public MovieDTO toDto() {
        return MovieDTO.builder()
                .id(this.id)
                .title(this.title)
                .director(this.director.toDto())
                .build();
    }

    public static List<MovieEntity> findAllByDirectorId(Long directorId) {
        return list("director.id", directorId);
    }
}
