package org.computer.auth.service.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 
 * @author Mervyn Naicker
 * 
 */

@Entity
@Table(name = "user_")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3363139075036741671L;

	private Integer userId;

	private String cid;

	private Set<Group> groups;
	
	private List<Role> roles;
	
	private List<String> allroles;	
	
	@Id
	@Column(name = "userId", nullable = false, length = 11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "cid", nullable = false, length = 45)
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_group", joinColumns = { @JoinColumn(name = "userId") }, 
				inverseJoinColumns = { @JoinColumn(name = "groupId") })

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "userId") }, 
				inverseJoinColumns = { @JoinColumn(name = "roleId") })
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Transient
	public List<String> getAllroles() {
		return allroles;
	}

	public void setAllroles(List<String> allroles) {
		this.allroles = allroles;
	}

}
