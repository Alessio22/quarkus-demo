package io.ac.resources;

import io.ac.dtos.CreateMovieDTO;
import io.ac.entities.MovieEntity;
import io.ac.services.MovieService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("movies")
@Tag(name = "Movie resource", description = "A simple resource")
public class MoviesResource {
    private Logger LOGGER = Logger.getLogger(MoviesResource.class);

    @Inject
    JsonWebToken jwt;

    @Inject
    MovieService movieService;

    @GET
    @RolesAllowed({"ADMIN", "USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("directorId") Long directorId, @Context SecurityContext ctx
    ) {
        LOGGER.info(getResponseString(ctx));
        return Response
                .ok(
                        movieService.findAll(directorId).stream().map(MovieEntity::toDto)
                )
                .build();
    }

    @GET
    @RolesAllowed({"ADMIN", "USER"})
    @Path("{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(
            @PathParam("movieId") Long movieId, @Context SecurityContext ctx
    ) {
        LOGGER.info(getResponseString(ctx));
        return Response.ok(movieService.findById(movieId).toDto()).build();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateMovieDTO createMovieDTO, @Context SecurityContext ctx) {
        LOGGER.info(getResponseString(ctx));
        return Response
                .ok(
                        movieService.create(createMovieDTO.getTitle(), createMovieDTO.getDirectorId()).toDto()
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
            CreateMovieDTO createMovieDTO,
            @Context SecurityContext ctx
    ) {
        LOGGER.info(getResponseString(ctx));
        return Response.ok(
                movieService.update(movieId, createMovieDTO.getTitle(), createMovieDTO.getDirectorId()).toDto()
        ).build();
    }

    @DELETE
    @RolesAllowed({"ADMIN"})
    @Path("{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("movieId") Long movieId, @Context SecurityContext ctx
    ) {
        LOGGER.info(getResponseString(ctx));
        movieService.delete(movieId);
        return Response.noContent().build();
    }

    private String getResponseString(SecurityContext ctx) {
        String name;
        if (ctx.getUserPrincipal() == null) {
            name = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
            name = ctx.getUserPrincipal().getName();
        }
        return String.format("subject: %s,"
                        + " isHttps: %s,"
                        + " authScheme: %s,"
                        + " hasJWT: %s",
                name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt());
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}
