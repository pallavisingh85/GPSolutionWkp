package testClient;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.traccar.model.User;


public class RestClient {

	public static void main(String[] args) {
		 Client client = ClientBuilder.newClient();
		
		 final Response res = client.target("http://localhost:8080/GPSolutionServer/rest/dataservice/sayPlainTextHello").request().post(null); // or head() if you only want the headers
		 
		 User user = new User("pallavi", "pal");
		 //final Response res = client.target("http://localhost:8080/GPSolutionServer/rest/userservice/login").
				// request(MediaType.APPLICATION_JSON).post(String.class, user);
		 	
		    System.out.println(res.readEntity(String.class));
		 
		    client.close();
		  }

		  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/de.vogella.jersey.first").build();
		  }

	}

