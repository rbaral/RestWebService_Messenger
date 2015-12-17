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

import org.baral.rest.messenger.model.Profile;
import org.baral.rest.messenger.service.ProfileService;


@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService service = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return service.getAllProfiles();
	}
	
	@POST
	public Profile addProfile(Profile prof){
		return service.addProfile(prof);
	}
	
	@PUT
	@Path("/{profilename}")
	public Profile updateProfile(@PathParam("profilename")String profName, Profile prof){
		prof.setProfileName(profName);
		return service.updateProfile(prof);
	}
	
	@DELETE
	@Path("/{profilename}")
	public Profile deleteProfile(@PathParam("profilename")String profileName){
		return service.removeProfile(profileName);
	}
	
	@Path("/{profilename}")
	@GET 
	public Profile getProfile(@PathParam("profilename")String profName){
		return service.getProfile(profName);
	}
}
