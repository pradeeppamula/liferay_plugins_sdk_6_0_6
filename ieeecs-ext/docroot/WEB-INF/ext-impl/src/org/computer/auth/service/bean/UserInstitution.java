package org.computer.auth.service.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * 
 * @author Mervyn Naicker
 * 
 */

@Entity
@Table(name = "institution")
public class UserInstitution implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3363139075036741671L;

	private Integer institutionId;

	private String institutionName;

	private List<Group> groups;
	
	private List<Role> roles;
	
	@Id
	@Column(name = "institutionId", nullable = false, length = 11)
	public Integer getinstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	@Column(name = "institutionName", nullable = false, length = 45)
	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "institution_group", joinColumns = { @JoinColumn(name = "institutionId") }, 
				inverseJoinColumns = { @JoinColumn(name = "groupId") })
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "institution_role", joinColumns = { @JoinColumn(name = "institutionId") }, 
				inverseJoinColumns = { @JoinColumn(name = "roleId") })
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
