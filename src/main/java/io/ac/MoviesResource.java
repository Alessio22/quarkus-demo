package io.ac;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("movies")
@Tag(name = "Movie resource", description = "A simple resource")
public class MoviesResource {

    @Inject
    MovieService movieService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .ok(
                        movieService.findAll().stream().map(MovieEntity::toDto)
                )
                .build();
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateMovieDTO createMovieDTO) {
        return Response
                .ok(
                        movieService.create(createMovieDTO.getTitle())
                )
                .build();
    }

    @PUT
    @Path("{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("movieId") Long movieId,
            CreateMovieDTO createMovieDTO
    ) {
        return Response.ok(
                movieService.update(movieId, createMovieDTO.getTitle())
        ).build();
    }

    @DELETE
    @Path("{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("movieId") Long movieId
    ) {
        movieService.delete(movieId);
        return Response.ok().build();
    }
}
