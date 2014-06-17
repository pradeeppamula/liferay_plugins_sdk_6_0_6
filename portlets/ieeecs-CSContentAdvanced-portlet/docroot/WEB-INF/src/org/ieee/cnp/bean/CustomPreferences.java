package org.ieee.cnp.bean;

public class CustomPreferences {
	
	private String name;
	private String value;
	private String defaultValue;
	
	public CustomPreferences( String name, String defaultValue) {
		this.name = name;
		//this.value = value;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	

}
