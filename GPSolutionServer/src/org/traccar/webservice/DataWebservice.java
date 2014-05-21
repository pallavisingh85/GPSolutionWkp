package org.traccar.webservice;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.traccar.TrackerEventHandler;
import org.traccar.listner.MyContextListner;
import org.traccar.model.DeviceConsumer;
import org.traccar.model.DeviceProducer;
import org.traccar.model.Position;
@Path("/dataservice")
public class DataWebservice {

	  // This method is called if TEXT_PLAIN is request
	  @POST
	  @Produces(MediaType.TEXT_PLAIN)
	  @Path("/sayPlainTextHello")
	  public String sayPlainTextHello() {
		  
		  TrackerEventHandler t = new TrackerEventHandler( MyContextListner.service.getDataManager());
		  Position p = new Position();
		  p.setDeviceId(123456789l);
		  p.setTime(new Date());
		  p.setLatitude(77.85);
		  p.setLongitude(85.66);
		  p.setAltitude(77.77);
		  p.setCourse(33.33);
		  p.setValid(true);
		  p.setAddress("abc");
		  p.setExtendedInfo("other");
		  p.setSpeed(55.55);
		  p.setId(123456789l);
		  
		  t.processSinglePosition(p); 
	    return "Hello Jerse";
	  }

	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("/getPosition")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public DeviceProducer getPosition(){
		  
		  DeviceProducer p = new DeviceProducer("55.4","77.7");
		  return p;
	  }
	  
	  // This method is called if XML is request
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	  }

	  // This method is called if HTML is request
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }
}
