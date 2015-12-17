package org.baral.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.baral.rest.messenger.database.DatabaseClass;
import org.baral.rest.messenger.model.Profile;

public class ProfileService {
private Map<String,Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("ramesh", new Profile(1L,"ramesh","ramesh","baral"));
		
	}
	public List<Profile> getAllProfiles(){
		return (new ArrayList<Profile>(profiles.values()));
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile prof){
		prof.setId(profiles.size()+1);
		profiles.put(prof.getProfileName(), prof);
		return prof;
	}
	
	public Profile updateProfile(Profile prof){
		if(prof.getProfileName().isEmpty())
			return null;
		else{
			profiles.put(prof.getProfileName(), prof);
			//return profiles.get(prof.getProfileName());
			return prof;
		}
	}
	
	public Profile removeProfile(String profileName){
		System.out.println("removing profile with name:"+profileName);
		return profiles.remove(profileName);
	}
}
