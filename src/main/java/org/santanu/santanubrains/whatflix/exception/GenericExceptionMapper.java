package org.santanu.santanubrains.whatflix.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.santanu.santanubrains.whatflix.response.ErrorResponse;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {

		ErrorResponse errorResponse = new ErrorResponse(ExceptionConfig.GENERIC_INTERNAL_SERVER_ERROR, ex.getMessage(),
				ExceptionConfig.ERROR_URL);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorResponse).type(MediaType.APPLICATION_JSON)
				.build();
	}
}
