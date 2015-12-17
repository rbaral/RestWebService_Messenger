package org.baral.rest.messenger.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.baral.rest.messenger.model.ErrorMessage;

/**
 * this exception mapper is called whenever an invalid URL is
 * accessed within this application
 * @author rbaral
 *
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		System.out.println(">>>>>>>>>>>>>>>>>Generic exception was caught");
		/*ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(500);
		errorMessage.setError(ex.getMessage());
		errorMessage.setErrorDoc("http://rbaral.java.com");*/
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),500,"http://rbaral.java.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
