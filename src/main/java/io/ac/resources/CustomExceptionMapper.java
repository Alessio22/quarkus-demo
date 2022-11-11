package io.ac.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CustomExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            // Overwrite error message
            Response originalErrorResponse = ((WebApplicationException) exception).getResponse();
            return Response.fromResponse(originalErrorResponse)
                    .entity(originalErrorResponse.getStatusInfo().getReasonPhrase())
                    .build();
        }
        // Special mappings
        else if (exception instanceof IllegalArgumentException) {
            return Response.status(400).entity(exception.getMessage()).build();
        }
        // Use 500 (Internal Server Error) for all other
        else {
            return Response.serverError().entity("Internal Server Error").build();
        }
    }
}
