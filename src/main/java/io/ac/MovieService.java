package io.ac;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class MovieService {

    public List<MovieEntity> findAll() {
        return MovieEntity.listAll();
    }

    @Transactional
    public MovieEntity create(String title) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(title);
        movieEntity.persist();
        return movieEntity;
    }

    @Transactional
    public MovieEntity update(Long movieId, String title) {
        MovieEntity movieEntity = MovieEntity.findById(movieId);
        movieEntity.setTitle(title);
        movieEntity.persist();
        return movieEntity;
    }

    @Transactional
    public void delete(Long movieId) {
        MovieEntity.deleteById(movieId);
    }
}
