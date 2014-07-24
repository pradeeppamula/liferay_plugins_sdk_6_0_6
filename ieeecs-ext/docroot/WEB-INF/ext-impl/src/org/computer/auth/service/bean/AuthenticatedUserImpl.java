/**
 * 
 */
package org.computer.auth.service.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.directory.Attribute;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.computer.auth.util.Configs;

/**
 * @author wberks
 * 
 */
public class AuthenticatedUserImpl implements AuthenticatedUser, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6922528638213897142L;
	private static final String STAFF_OU = "ou=staff";
	private static final String CS_OU = "OU=Computer Society";
	private boolean isStaff = false;
	private String departmentNumber;
	private String manager;

	public boolean hasAccess(PurchasedItem item) {
		_log.debug("chech access for item: " + item.getItemCode());
		
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.net.security.Principal#getName()
	 */
	
	public String getName() {
		return this.userName;
	}

	public void setName(String name) {
		this.userName = name;
	}

	public boolean isValid() {
		return isStaff() || this.valid;
	}

	public boolean isStaff() {
		return isStaff || "STAFF".equals(businessCategory);
	}


	public boolean isCAP() {
		return "CAP".equalsIgnoreCase(this.businessCategory);
	}

	public String getCAPOrganization() {
		if (this.isCAP())
			return this.getOrganizationalUnit();
		else
			return "";
	}

	public void setValid(boolean valid) {
		_log.debug("Set user valid to " + valid);
		this.valid = valid;
	}

	public boolean isInstitution() {
		return false;
	}

	public boolean isLoadBalancer() {
		return false;
	}

	public void setBusinessCategory(Attribute businessCategory) {
		if (businessCategory == null)
			return;
		try {
			this.businessCategory = (String) businessCategory.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get businessCategory from LDAP");
		}
	}

	public void setCid(Attribute cid) {
		try {
			this.cid = (String) cid.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName() + ": unable to get CID from LDAP");
		}
	}

	// Added by Pradeep for DepartmentNumber
	public void setDepartmentNumber(Attribute departmentNumber) {
		try {
			this.departmentNumber = (String) departmentNumber.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get DepartmentNumber from LDAP");
		}
	}

	// Added by Pradeep for Manager
	public void setManager(Attribute manager) {
		try {
			this.manager = (String) manager.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get Manager from LDAP");
		}
	}

	public void setCountry(Attribute country) {
		try {
			this.country = (String) country.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get country from LDAP");
		}
	}

	public void setFirstName(Attribute firstName) {
		try {
			this.firstName = (String) firstName.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get firstName from LDAP");
		}
	}

	public void setFullName(Attribute fullName) {
		try {
			this.fullName = (String) fullName.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get fullName from LDAP");
		}
	}

	public void setGrade(Attribute grade) {
		try {
			this.grade = (String) grade.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get grade from LDAP");
		}
	}

	public void setLastName(Attribute lastName) {
		try {
			this.lastName = (String) lastName.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get lastName from LDAP");
		}
	}

	public void setMail(Attribute mail) {
		if (mail == null)
			return;
		try {
			this.mail = (String) mail.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName() + ": unable to get mail from LDAP");
		}
	}

	public void setOrganization(Attribute organization) {
		if (organization == null)
			return;
		try {
			this.organization = (String) organization.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get organization from LDAP");
		}
	}

	public void setOrganizationalUnit(Attribute organizationalUnit) {
		_log.debug("setOrganizationalUnit");
		if (organizationalUnit == null)
			return;
		try {
			int count = organizationalUnit.size();
			for (int i = 0; i < count; i++) {
				String ou = (String) organizationalUnit.get(i);
				_log.debug("ou = " + ou);
				if (Configs.IGNORED_OU.contains(ou))
					continue;
				_log.debug("set ou");
				this.organizationalUnit = ou;
			}
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get organization from LDAP");
		}
	}

	public void setStaff(Attribute distinguishedName) {
		_log.debug("setIsStaff");
		boolean hasStaffOU = false;
		boolean hasCSOU = false;
		if (distinguishedName == null)
			return;
		try {
			int count = distinguishedName.size();
			for (int i = 0; i < count; i++) {
				String dn = (String) distinguishedName.get(i);
				String dns[] = dn.split(",");
				for (String dName : dns) {
					if (STAFF_OU.equalsIgnoreCase(dName)) {
						hasStaffOU = true;
					}
					if (CS_OU.equalsIgnoreCase(dName)) {
						hasCSOU = true;
					}
				}

			}
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get staff from LDAP");
		}
		isStaff = hasCSOU && hasStaffOU;
	}

	public void setPublication(Attribute publications) {
		if (publications == null)
			return;
		try {
			int publicationCount = publications.size();
			for (int i = 0; i < publicationCount; i++) {
				Subscription publication = new Subscription(
						(String) publications.get(i));
				if (publication.isActive())
					this.publications.put(publication.getName(), publication);
			}
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get publications from LDAP");
		}
	}

	public void setSociety(Attribute societies) {
		if (societies == null)
			return;
		try {
			int societyCount = societies.size();
			for (int i = 0; i < societyCount; i++) {
				Subscription society = new Subscription(
						(String) societies.get(i));
				if (society.isActive())
					this.societies.put(society.getName(), society);
			}
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get societies from LDAP");
		}
	}

	public boolean isMember() {
		if (this.societies != null) {
			for (Subscription society : this.societies.values()) {
				if (Configs.CS_MEMBER_CODE.equalsIgnoreCase(society.getName())
						&& society.isActive())
					return true;
			}
		}
		return false;
	}

	public void setType(Attribute type) {
		try {
			this.type = (String) type.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName() + ": unable to get type from LDAP");
		}
	}

	public void setStatus(Attribute status) {
		try {
			this.status = (String) status.get();
			_log.debug("Check status: " + this.status);
			this.setValid("AC".equalsIgnoreCase(this.status));
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get status from LDAP");
			this.setValid(false);
		}
	}

	public void setUid(Attribute uid) {
		try {
			this.uid = (String) uid.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName() + ": unable to get UID from LDAP");
		}
	}

	/**
	 * @return the businessCategory
	 */
	public String getBusinessCategory() {
		return this.businessCategory;
	}

	/**
	 * @return the cid
	 */
	public String getCid() {
		return this.cid;
	}

	public String getDepartmentNumber() {
		return this.departmentNumber;
	}

	public String getManager() {
		return this.manager;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return this.grade;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return this.organization;
	}

	public String getOrganizationalUnit() {
		return this.organizationalUnit;
	}

	/**
	 * @return the publications
	 */
	public Collection<Subscription> getPublications() {
		return this.publications.values();
	}

	private String getPublicationsStr() {
		StringBuffer pubs = new StringBuffer();
		for (Subscription pub : this.publications.values()) {
			if (pubs.length() > 0)
				pubs.append("^");
			pubs.append(pub.toCookieValue());
		}
		return pubs.toString();
	}

	/**
	 * @return the societies
	 */
	
	public Collection<Subscription> getSocieties() {
		return this.societies.values();
	}

	private String getSocietiesStr() {
		StringBuffer socs = new StringBuffer();
		for (Subscription soc : this.societies.values()) {
			if (socs.length() > 0)
				socs.append("^");
			socs.append(soc.toCookieValue());
		}
		return socs.toString();
	}

	private List<String> getSubscriptionCodes() {
		List<String> results = new Vector<String>();

		// If the user is valid
		if (this.isValid()) {
			// First, look through the societies to see if they are a computer
			// society member
			for (Subscription society : this.societies.values()) {
				if (Configs.CS_MEMBER_CODE.equals(society.getName()))
					results.add(society.getName());
			}

			// Now, on to the publications
			for (Subscription publication : this.publications.values()) {
				results.add(publication.getName());
			}

			// Finally, check for their business category (STAFF, etc.)
			results.add(this.getBusinessCategory());
			if (isStaff()) {
				results.add("STAFF");
			}
		}
		// if the user is not valid, just give him access to the subscription
		// codes
		else {
			for (Subscription publication : this.publications.values()) {
				results.add(publication.getName());
			}
		}
		return results;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return this.uid;
	}

//	public String toString() {
//		return "Name: " + this.fullName + "\nUser name: " + this.userName
//				+ "\nCID: " + this.cid + "\nBusiness Category: "
//				+ this.businessCategory + "\nKeys: \n"
//				+ this.getKeyRing().toString();
//	}

	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof AuthenticatedUserImpl))
			return false;
		if (!this.getCid().equals(((AuthenticatedUserImpl) obj).getCid()))
			return false;
		if (!this.getUid().equals(((AuthenticatedUserImpl) obj).getUid()))
			return false;
		return true;
	}


	/**
	 * LDAP based attributes
	 */
	private String firstName = "";
	private String lastName = "";
	private String fullName = "";
	private String userName = "";
	private String uid = "";
	private String cid = "";
	private String mail = "";
	private String organization = "";
	private Hashtable<String, Subscription> publications = new Hashtable<String, Subscription>();
	private Hashtable<String, Subscription> societies = new Hashtable<String, Subscription>();
	private String type = "";
	private String status = "";
	private String businessCategory = "";
	private String grade = "";
	private String country = "";
	private String organizationalUnit = "";
	private String memberNumber = "";

	private boolean valid = false;

	private KeyRing keyring;

	private static Log _log = LogFactory.getLog(AuthenticatedUser.class);

	public void setMemberNumber(Attribute member) {
		try {
			this.memberNumber = (String) member.get();
		} catch (Exception e) {
			_log.info(e.getClass().getName()
					+ ": unable to get Member Number from LDAP");
		}

	}

	@Override
	public int checkAccess(String className) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInstitutionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstitutionImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstitutionImageWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstitutionImageHeight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getOpenURLConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getSocietiesAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPublicationsAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Subscription> getSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.computer.auth.service.bean.KeyRing getKeyRing() {
		// TODO Auto-generated method stub
		return null;
	}

	
//	public boolean hasAccess(org.computer.authentication.PurchasedItem item) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	
//	public boolean hasAccess(org.computer.auth.service.bean.PurchasedItem item) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	
//	public boolean hasAccess(org.computer.auth.service.bean.PurchasedItem item) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
