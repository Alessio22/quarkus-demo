package io.ac.resources;

import io.ac.dtos.LoginRequestDTO;
import io.ac.dtos.LoginResponseDTO;
import io.ac.entities.UserEntity;
import io.ac.services.UserService;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("auth")
@Tag(name = "Auth resource", description = "A simple auth resource")
public class AuthResource {

    @Inject
    UserService userService;

    @ConfigProperty(name = "quarkus-demo.jwt.expiredIn", defaultValue = "3600")
    String expiredIn;

    @POST
    @PermitAll
    @Path(("login"))
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(LoginRequestDTO loginRequestDTO) {
        UserEntity userEntity = userService.findByUsernameAndPassword(loginRequestDTO);
        if (Objects.isNull(userEntity)) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
        String accessToken = Jwt.issuer("quarkus-demo")
                .subject(userEntity.getId().toString())
                .groups(userEntity.getRole().name())
                .expiresAt(System.currentTimeMillis() + Integer.parseInt(expiredIn))
                .sign();
        return Response.ok(
                LoginResponseDTO.builder()
                        .accessToken(accessToken)
                        .expiredIn(Integer.parseInt(expiredIn))
                        .build()
        ).build();
    }

}
