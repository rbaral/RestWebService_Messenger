/**
 * 
 */
package org.baral.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.baral.rest.messenger.database.DatabaseClass;
import org.baral.rest.messenger.model.Comment;
import org.baral.rest.messenger.model.ErrorMessage;
import org.baral.rest.messenger.model.Message;

/**
 * @author rbaral
 *
 */
public class CommentsService {

	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllcomments(long messageID){
		/*List<Comment> CommentList = new ArrayList<Comment>();
		CommentList.add(new Comment(1,"Comment 1","ramesh"));
		CommentList.add(new Comment(2,"Comment 2","ramesh baral"));
		return CommentList;*/
		Map<Long,Comment> comments = messages.get(messageID).getComments();
		return (new ArrayList<Comment>(comments.values()));
	}
	
	public Comment getComment(Long messageID, Long commentID){
		Message message = messages.get(messageID);
		ErrorMessage errorMessage = new ErrorMessage("Record Not Found",404,"http://rbaral.java.com");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		if(message ==null){
			throw new WebApplicationException(response);
		}
		Comment comment = message.getComments().get(commentID);
		if(comment ==null)
			throw new NotFoundException(response);
		return comment;
	}
	
	public Comment addComment(Long messageID, Comment comm){
		Map<Long,Comment> comments = messages.get(messageID).getComments();
		comm.setId(comments.size()+1);
		comments.put(comm.getId(), comm);
		return comm;
	}
	
	public Comment updateComment(long messageID, Comment comm){
		Map<Long,Comment> comments = messages.get(messageID).getComments();
		if(comm.getId()<=0)
			return null;
		else{
			comments.put(comm.getId(), comm);
			return comm;
		}
	}
	
	public Comment removeComment(long messageID, long CommentID){
		System.out.println("removing Comment with ID:"+CommentID);
		Map<Long,Comment> comments = messages.get(messageID).getComments();
		return comments.remove(CommentID);
	}
}
