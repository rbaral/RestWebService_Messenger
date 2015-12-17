package org.baral.rest.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.baral.rest.messenger.model.Comment;
import org.baral.rest.messenger.service.CommentsService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentsResource {

	CommentsService service = new CommentsService();
	
	/*@GET
	public String test(){
		return "test sub resource";
	}*/
	
	/**
	 * the Pathparam can be used to access the
	 * parameter of the parent URL as well
	 * @param messageID
	 * @param commentID
	 * @return
	 */
	/*@GET
	@Path("/{commentID}")
	public String getComments(@PathParam("messageID") String messageID, @PathParam("commentID") String commentID){
		return "returning comment:"+commentID+" of message:"+messageID;
	}*/
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId){
		return service.getAllcomments(messageId);
	}
	
	@POST
	public Comment addMessage(@PathParam("messageId") long messageId, Comment comm){
		return service.addComment(messageId, comm);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comm){
		comm.setId(commentId);
		return service.updateComment(messageId, comm);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment deleteMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
		return service.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
		return service.getComment(messageId, commentId);
	}
}
