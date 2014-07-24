package org.computer.auth.service.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author Mervyn Naicker
 * 
 */

@Entity
@Table(name = "role_")
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6130319154143107031L;
	
	@Id
	@Column(name = "roleId", nullable = false)
	private Integer roleId;
	
	@Column(name = "roleName", nullable = false)
	private String name;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
