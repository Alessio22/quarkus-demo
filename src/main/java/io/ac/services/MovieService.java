package io.ac.services;

import io.ac.entities.DirectorEntity;
import io.ac.entities.MovieEntity;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Singleton
public class MovieService {

    public List<MovieEntity> findAll(Long directorId) {
        if (Objects.nonNull(directorId)) {
            return MovieEntity.findAllByDirectorId(directorId);
        }
        return MovieEntity.listAll();
    }

    @Transactional
    public MovieEntity create(String title, Long directorId) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(title);
        movieEntity.setDirector(
                (DirectorEntity) DirectorEntity.findByIdOptional(directorId)
                        .orElseThrow()
        );
        movieEntity.persist();
        return movieEntity;
    }

    @Transactional
    public MovieEntity update(Long movieId, String title, Long directorId) {
        MovieEntity movieEntity = MovieEntity.findById(movieId);
        movieEntity.setTitle(title);
        movieEntity.setDirector(DirectorEntity.findById(directorId));
        movieEntity.persist();
        return movieEntity;
    }

    @Transactional
    public void delete(Long movieId) {
        MovieEntity.deleteById(movieId);
    }
}
