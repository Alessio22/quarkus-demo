package io.ac.services;

import io.ac.entities.DirectorEntity;
import io.ac.entities.MovieEntity;
import org.jboss.logging.Logger;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Singleton
public class MovieService {
    private Logger LOGGER = Logger.getLogger(MovieService.class);

    public List<MovieEntity> findAll(Long directorId) {
        if (Objects.nonNull(directorId)) {
            LOGGER.info("Find all movies of directorId="+directorId.toString());
            return MovieEntity.findAllByDirectorId(directorId);
        }
        LOGGER.info("Find all movies");
        return MovieEntity.listAll();
    }

    @Transactional
    public MovieEntity create(String title, Long directorId) {
        LOGGER.info("Create new movie");
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(title);
        movieEntity.setDirector(
                (DirectorEntity) DirectorEntity.findByIdOptional(directorId).orElseThrow()
        );
        movieEntity.persist();
        LOGGER.info("The movie has been created");
        return movieEntity;
    }

    @Transactional
    public MovieEntity update(Long movieId, String title, Long directorId) {
        LOGGER.info("Update movie with id="+movieId.toString());
        MovieEntity movieEntity = (MovieEntity) MovieEntity.findByIdOptional(movieId).orElseThrow();
        movieEntity.setTitle(title);
        movieEntity.setDirector(DirectorEntity.findById(directorId));
        movieEntity.persist();
        LOGGER.info("The movie has been updated");
        return movieEntity;
    }

    @Transactional
    public void delete(Long movieId) {
        LOGGER.info("Delete movie with id="+movieId.toString());
        MovieEntity.deleteById(movieId);
        LOGGER.info("The movie has been deleted");
    }
}
