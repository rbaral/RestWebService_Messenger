/**
 * 
 */
package org.baral.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.baral.rest.messenger.database.DatabaseClass;
import org.baral.rest.messenger.exception.DataNotFoundException;
import org.baral.rest.messenger.model.Message;

/**
 * @author rbaral
 *
 */
public class MessageService {

	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public static void main(String args[]){
		MessageService msg = new MessageService();
		System.out.println(msg.getAllMessages().size());
		msg.removeMessage(1L);
		System.out.println(msg.getAllMessages().size());
	}
	
	public MessageService(){
		messages.put(1L,new Message(1,"Message 1","ramesh"));
		messages.put(2L,new Message(2,"Message 2","ramesh baral"));
		
	}
	public List<Message> getAllMessages(){
		/*List<Message> messageList = new ArrayList<Message>();
		messageList.add(new Message(1,"Message 1","ramesh"));
		messageList.add(new Message(2,"Message 2","ramesh baral"));
		return messageList;*/
		return (new ArrayList<Message>(messages.values()));
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> returnMessageList = new ArrayList<Message>();
		for(Long msgID:messages.keySet()){
			String created = messages.get(msgID).getCreated().toString();
			System.out.println("checking "+created+" and "+year+"..."+created.indexOf(year));
			if(created.indexOf(String.valueOf(year))>=0)
				returnMessageList.add(messages.get(msgID));
		}
		return returnMessageList;
	}
	
	public Message getMessage(Long messageID){
		Message message = messages.get(messageID);
		if(message==null){
			throw new DataNotFoundException("Exception Occured, message with id:"+messageID+" was not found");
		}
		return message;
	}
	
	public Message addMessage(Message mess){
		mess.setMessageID(messages.size()+1);
		messages.put(mess.getMessageID(), mess);
		System.out.println("message added, now it has following messages");
		for(Long key:messages.keySet()){
			System.out.println(messages.get(key).toString());
		}
		return mess;
	}
	
	public Message updateMessage(Message mess){
		if(mess.getMessageID()<=0)
			return null;
		else{
			messages.put(mess.getMessageID(), mess);
			return messages.get(mess.getMessageID());
		}
	}
	
	public Message removeMessage(Long messageID){
		System.out.println("removing message with ID:"+messageID);
		return messages.remove(messageID);
	}
}
