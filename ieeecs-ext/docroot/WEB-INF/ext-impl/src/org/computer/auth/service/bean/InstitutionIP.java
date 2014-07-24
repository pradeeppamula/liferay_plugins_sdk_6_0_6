package org.computer.auth.service.bean;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="institution_ip")
public class InstitutionIP  implements Serializable {

	private static final long serialVersionUID = 7066726767985463096L;

	private Integer institutionId;
	private BigInteger fromIP;
	private BigInteger toIP;
	private Integer institutionIPId;
	
	private UserInstitution institution;  
	
	@Column(name = "institutionId", nullable = false, length = 11)
	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
	
	@Column(name = "fromIP", nullable = false, length = 20)
	public BigInteger getFromIP() {
		return fromIP;
	}
	
	public void setFromIP(BigInteger fromIP) {
		this.fromIP = fromIP;
	}
	
	@Column(name = "toIP", nullable = false, length = 20)
	public BigInteger getToIP() {
		return toIP;
	}

	public void setToIP(BigInteger toIP) {
		this.toIP = toIP;
	}
	
	@Id
	@Column(name = "institutionIPId", nullable = false, length = 11)
	public Integer getInstitutionIPId() {
		return institutionIPId;
	}

	public void setInstitutionIPId(Integer institutionIPId) {
		this.institutionIPId = institutionIPId;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="institutionIPId")
	public UserInstitution getInstitution() {
		return institution;
	}

	public void setInstitution(UserInstitution institution) {
		this.institution = institution;
	}

}
