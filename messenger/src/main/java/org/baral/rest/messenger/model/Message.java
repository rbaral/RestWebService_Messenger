/**
 * 
 */
package org.baral.rest.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author rbaral
 *
 */

@XmlRootElement
public class Message {

	private long messageID;
	private String message;
	private Date created;
	private String author;
	private Map<Long,Comment> comments = new HashMap<>();
	private List<Link> linksList = new ArrayList<Link>();
	
	/**
	 * the default constructor is required,
	 * else there is 404 error
	 */
	public Message(){
		
	}
	public Message(long id, String message, String auth){
		this.messageID = id;
		this.message = message;
		this.created = new Date();
		this.author = auth;
	}
	public long getMessageID() {
		return messageID;
	}
	public void setMessageID(long messageID) {
		this.messageID = messageID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString(){
		return "ID:"+getMessageID()+" author:"+getAuthor()+" message:"+getMessage();
	}
	//xml transient as we dont want the comments to be shown when the message is pulled
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	public List<Link> getLinksList() {
		return linksList;
	}
	public void setLinksList(List<Link> linksList) {
		this.linksList = linksList;
	}
	
	public void addLink(String link, String rel){
		Link l = new Link();
		l.setLink(link);
		l.setRel(rel);
		this.linksList.add(l);
	}
}
