package org.computer.auth.service.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.persistence.CascadeType;

/**
 * 
 * @author Mervyn Naicker
 * 
 */

@Entity
@Table(name = "group_")
public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2454103416999611115L;
	@Id
	@Column(name = "groupId", nullable = false, length = 10)
	private Integer groupId;
	
	@Column(name = "groupName", nullable = false,length = 50)
	private String groupName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "GROUP_ROLE", joinColumns = { @JoinColumn(name = "groupId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
	private Set<Role> roles;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
