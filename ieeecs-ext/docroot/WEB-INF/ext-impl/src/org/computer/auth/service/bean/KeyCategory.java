package org.computer.auth.service.bean;

/**
 * KeyCategory generated by MyEclipse Persistence Tools
 */

public class KeyCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4631373824696989661L;
	
	private String	name;
	private String  code;

	// Constructors

	/** default constructor */
	public KeyCategory() {}

	/** full constructor */
	public KeyCategory(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public KeyCategory(String name) {
		this.name = name;
		this.code = "";
	}

	// Property accessors

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public KeyCategory copyOf() {
		KeyCategory copy = new KeyCategory(this.name, this.code);
		return copy;
	}

}