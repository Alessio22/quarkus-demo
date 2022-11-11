package io.ac;

import io.smallrye.jwt.build.Jwt;
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

    @POST
    @PermitAll
    @Path(("login"))
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(LoginRequestDTO loginRequestDTO) {
        UserEntity userEntity = userService.findByUsernameAndPassword(loginRequestDTO);
        if (Objects.isNull(userEntity)) {
            return Response.status(401).build();
        }
        String jwt = Jwt.issuer("quarkus-demo")
                .subject(userEntity.getId().toString())
                .groups(userEntity.getRole().name())
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign();
        return Response.ok(jwt).build();
    }

}
