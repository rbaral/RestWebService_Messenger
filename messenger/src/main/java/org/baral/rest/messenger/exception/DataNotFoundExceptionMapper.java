package org.baral.rest.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.baral.rest.messenger.model.ErrorMessage;

//register this class to JAX-RS using @provider annotation
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"http://rbaral.java.com");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

	
}
