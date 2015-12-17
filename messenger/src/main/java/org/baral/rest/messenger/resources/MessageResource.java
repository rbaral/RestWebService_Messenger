package org.baral.rest.messenger.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.baral.rest.messenger.model.Link;
import org.baral.rest.messenger.model.Message;
import org.baral.rest.messenger.resources.bean.MessageFilterBean;
import org.baral.rest.messenger.service.MessageService;

@Path("messages")
@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	/*@GET
    @Produces(MediaType.TEXT_HTML)
	public String getMessages(){
		return "Hello World!!!!";
	}*/
	MessageService service = new MessageService();
	
	/**
	 * this method demonstrates the usage of BeanParam, where the individual
	 * params can be grouped to a class and the Jersey will understand the params
	 * and map them to the object of the MessageFilterBean
	 * @param filterBean
	 * @return
	 */
	@GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		List<Message> messageList = null;
		List<Message> allMessageList = null;
		if(filterBean.getYear()>0){
			allMessageList = service.getAllMessagesForYear(filterBean.getYear());
			if(filterBean.getStart()>0 && filterBean.getSize()>0 && filterBean.getStart()<allMessageList.size() &&(filterBean.getStart()+filterBean.getSize())<allMessageList.size()){
				allMessageList=allMessageList.subList(filterBean.getStart(), filterBean.getStart()+filterBean.getSize());
			}else if(filterBean.getSize()==0 && filterBean.getStart()<allMessageList.size()){
				allMessageList = allMessageList.subList(filterBean.getStart(), allMessageList.size());
			}else if(filterBean.getStart()==0 && filterBean.getSize()<allMessageList.size()){
				allMessageList = allMessageList.subList(0, filterBean.getSize());
			}else
				allMessageList = new ArrayList<Message>();//blank arraylist
		}else{
			allMessageList = service.getAllMessages();
		}
		return allMessageList;
	}
	
	/*@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,@QueryParam("start") int start,@QueryParam("size") int size){
		List<Message> messageList = null;
		List<Message> allMessageList = null;
		if(year>0){
			allMessageList = service.getAllMessagesForYear(year);
			if(start>0 && size>0 && start<allMessageList.size() &&(start+size)<allMessageList.size()){
				allMessageList=allMessageList.subList(start, start+size);
			}else if(size==0 && start<allMessageList.size()){
				allMessageList = allMessageList.subList(start, allMessageList.size());
			}else if(start==0 && size<allMessageList.size()){
				allMessageList = allMessageList.subList(0, size);
			}else
				allMessageList = new ArrayList<Message>();//blank arraylist
		}else{
			allMessageList = service.getAllMessages();
		}
		return allMessageList;
	}*/
	
	/**
	 * this sends the JSON response for the message
	 * that has messageID in the request
	 * @param messageID
	 * @return
	 */
	@GET
    @Path("/{messageID}")
	public Message getMessage(@PathParam("messageID") Long messageID, @Context UriInfo uriInfo){
		Message message = service.getMessage(messageID);
		String uri = getURIForSelf(uriInfo, message);
		message.addLink(uri, "self");
		uri = getURIForProfile(uriInfo, message);
		message.addLink(uri, "profile");
		uri = getURIForComments(uriInfo, message);
		message.addLink(uri, "comment");
		//message.getLinksList().addAll(getURIForComments(uriInfo, message));
		return message;
	}

	/**
	 * @param uriInfo
	 * @param message
	 * @return
	 */
	private String getURIForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getMessageID()))
				.build()
				.toString();
		return uri;
	}
	
	private String getURIForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
		return uri;
	}
	
	/*private List<Link> getURIForComments(UriInfo uriInfo, Message message) {
		String uri = "";
		List<Link> commentsLink= new ArrayList<Link>();
		for(long commentId:message.getComments().keySet()){
			uri = uriInfo.getBaseUriBuilder()
					.path(CommentsResource.class)
					.path(String.valueOf(commentId))
					.path(message.getComments().get(commentId).getMessage())
					.build()
					.toString();
			Link link = new Link();
			link.setLink(uri);
			link.setRel("comment");
			commentsLink.add(link);
		}
		System.out.println(" message "+message.getMessageID()+" has "+message.getComments().size()+" comments");
		return commentsLink;
	}*/
	
	private String getURIForComments(UriInfo uriInfo, Message message){
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentsResource.class)
				.resolveTemplate("messageID", message.getMessageID())
				.build()
				.toString();
		return uri;
	}
	
	/**
	 * this method accepts the JSON parameter for Message object
	 * and updates the corresponding Message object in the list
	 * @param mess
	 * @return
	 */
	@PUT
    @Path("/{messageID}")
	public Message updateMessage(@PathParam("messageID")Long messageID,Message mess){
		mess.setMessageID(messageID);
		return service.updateMessage(mess);
	}
	
	/**
	 * deletes a message whose id is taken from the URL
	 * @param messageID
	 * @param mess
	 * @return
	 */
	@DELETE
    @Path("/{messageID}")
	public Message deleteMessage(@PathParam("messageID")Long messageID){
		return service.removeMessage(messageID);
	}
	
	/**
	 * this method will handle the urls of the form messages/XX/XX
	 * and treat the first one as the message id and the second as
	 * the attribute and return the value of the attribute from the
	 * corresponding message, if available
	 * @param messageID
	 * @param attribute
	 * @return
	 */
	@GET
    @Produces(MediaType.APPLICATION_XML)
	@Path("/{messageID}/{attribute}")
	public String getMessage(@PathParam("messageID") Long messageID,@PathParam("attribute") String attribute){
		Message msg= service.getMessage(messageID);
		String attribValue="";
		if(attribute.equalsIgnoreCase("author"))
			attribValue = msg.getAuthor();
		else if(attribute.equalsIgnoreCase("created"))
			attribValue = msg.getCreated().toString();
		else if(attribute.equalsIgnoreCase("message"))
			attribValue = msg.getMessage();
		return attribValue;
	}
	
	/**
	 * the client can send a POST request with JSON arguments of the form
	 * of Message object and this will be converted to the Message object and 
	 * added to the list of messages
	 * update:sends the status code using the response
	 * @param mess
	 * @return
	 */
	@POST
	public Response addMessage(Message mess,@Context UriInfo uriInfo){
		Message newMessage = service.addMessage(mess);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getMessageID())).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
		/*return Response.status(Status.CREATED)
				.entity(newMessage)
				.build();*/
		//return service.addMessage(mess);
	}
	
	@Path("/{messageID}/comments")
	public CommentsResource getCommentResource(){
		return new CommentsResource();
	}

}
