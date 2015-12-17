/**
 * 
 */
package org.baral.rest.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.baral.rest.messenger.model.Message;
import org.baral.rest.messenger.model.Profile;

/**
 * @author rbaral
 *
 */
public class DatabaseClass {

	private static Map<Long,Message> messages =new HashMap<Long,Message>();
	private static Map<String,Profile> profiles = new HashMap<String,Profile>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
}
