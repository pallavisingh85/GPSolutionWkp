package org.traccar.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeviceProducer {
	
	public String latitude;
	public String longitude;

	public DeviceProducer() {}
	
	public DeviceProducer(String latitude, String longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
}
