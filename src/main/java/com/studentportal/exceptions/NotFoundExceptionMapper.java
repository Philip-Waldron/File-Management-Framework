package com.studentportal.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        ExceptionMessage exMessage = new ExceptionMessage(e.getMessage(),404);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exMessage).build();
    }
}
