package org.traccar.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	public User(){}
	
	public String username;
	public String password;

	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
}
