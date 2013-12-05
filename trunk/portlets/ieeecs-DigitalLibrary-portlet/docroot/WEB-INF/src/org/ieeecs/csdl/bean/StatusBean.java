package org.ieeecs.csdl.bean;

public class StatusBean {

	private String message 		= "";
	private String messageType	= "";
	
	public static final String DEBUG = "DEBUG";
	public static final String ERROR = "ERROR";
	public static final String INFO  = "INFO";
	public static final String WARN  = "WARN";
		
	public StatusBean() {}
	
	public StatusBean(String message, String messageType) {
		super();
		this.message = message;
		this.messageType = messageType;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
}