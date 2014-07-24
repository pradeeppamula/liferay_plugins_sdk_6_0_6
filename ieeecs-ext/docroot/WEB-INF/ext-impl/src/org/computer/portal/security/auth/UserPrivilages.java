package org.computer.portal.security.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.computer.auth.service.bean.Group;
import org.computer.authentication.hibernate.Institution;


public class UserPrivilages {

	private List<Group> groups = new ArrayList<Group>();
	
	private List<String> roles = new ArrayList<String>();
	
	private Institution institution; 
	
	private boolean staff;
	
	private boolean member;
	
	private boolean cap;
	
	private String CAPOrganization;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public boolean hasRole(String role) {
		return roles.contains(role);
	}
	
	public boolean hasGroup(String role) {
		return roles.contains(role);
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public boolean isStaff() {
		return staff;
	}

	public void setStaff(boolean staff) {
		this.staff = staff;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	public boolean isCap() {
		return cap;
	}

	public void setCap(boolean cap) {
		this.cap = cap;
	}

	public String getCAPOrganization() {
		return CAPOrganization;
	}

	public void setCAPOrganization(String cAPOrganization) {
		CAPOrganization = cAPOrganization;
	}
	
}
