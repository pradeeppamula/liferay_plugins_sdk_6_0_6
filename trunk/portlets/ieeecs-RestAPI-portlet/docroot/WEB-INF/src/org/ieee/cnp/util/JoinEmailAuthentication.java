package org.ieee.cnp.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class JoinEmailAuthentication extends Authenticator {

	public String username;
	public String password;
	
    public JoinEmailAuthentication() {
		super();
	}
    
    public JoinEmailAuthentication(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}    

	protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
	
}