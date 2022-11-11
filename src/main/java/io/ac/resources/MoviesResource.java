package io.ac.resources;

import io.ac.dtos.CreateMovieDTO;
import io.ac.entities.MovieEntity;
import io.ac.services.MovieService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("movies")
@Tag(name = "Movie resource", description = "A simple resource")
public class MoviesResource {

    @Inject
    MovieService movieService;

    @GET
    @RolesAllowed({"ADMIN", "USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("directorId") Long directorId
    ) {
        return Response
                .ok(
                        movieService.findAll(directorId).stream().map(MovieEntity::toDto)
                )
                .build();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateMovieDTO createMovieDTO) {
        return Response
                .ok(
                        movieService.create(createMovieDTO.getTitle(), createMovieDTO.getDirectorId())
                )
                .build();
    }

    @PUT
    @RolesAllowed({"ADMIN"})
    @Path("{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("movieId") Long movieId,
            CreateMovieDTO createMovieDTO
    ) {
        return Response.ok(
                movieService.update(movieId, createMovieDTO.getTitle(), createMovieDTO.getDirectorId())
        ).build();
    }

    @DELETE
    @RolesAllowed({"ADMIN"})
    @Path("{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("movieId") Long movieId
    ) {
        movieService.delete(movieId);
        return Response.noContent().build();
    }
}
