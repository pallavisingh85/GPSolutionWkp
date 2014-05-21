package org.traccar.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.traccar.model.User;

@Path("/userservice")
public class UserWebservice {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public String login(User user){
		
		return user.username;
	}
}
