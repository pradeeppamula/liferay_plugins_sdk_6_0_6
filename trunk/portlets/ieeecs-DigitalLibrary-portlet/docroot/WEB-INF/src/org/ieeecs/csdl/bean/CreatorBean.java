package org.ieeecs.csdl.bean;

public class CreatorBean {

	private String givenName;
	private String surname;
	private String role;
	private String sponsor;
	
	public CreatorBean() {}
	
	public CreatorBean(String givenName, String surname, String role, String sponsor) {
		super();
		this.givenName = givenName;
		this.surname = surname;
		this.role = role;
		this.sponsor = sponsor;
	}

	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	
}