package org.computer.auth.service.bean;

import java.util.Collection;
import java.util.Map;

public interface  AuthenticatedUser extends java.security.Principal, java.io.Serializable {

	public int checkAccess(String className);
	
	/**
	 * Check for access to a purchased item.
	 * 
	 * @param item
	 * @return TRUE if available - meaning that the user has purchased access to the item
	 */
	public abstract boolean hasAccess(PurchasedItem item);
	
	public abstract boolean isValid();
	
	public String getInstitutionName();
	
	public String getInstitutionImage();
	
	public String getInstitutionImageWidth();
	
	public String getInstitutionImageHeight();
	
	public Map<String, String> getOpenURLConfiguration();
	
	public abstract boolean isInstitution();
	
	public abstract boolean isLoadBalancer();
	
	public KeyRing getKeyRing();
	
	public boolean isMember() ;
	
	public boolean isStaff();
	
	public boolean isCAP();
	
	public String getCAPOrganization();
	
	public Collection<Subscription> getSocieties() ;
	
	public String getSocietiesAsString() ;
	
	public Collection<Subscription> getPublications() ;
	
	public String getPublicationsAsString() ;
	
	public String getCid() ;
	
	public String getName() ;
	
	public String getMemberNumber() ;
	
	public String getDepartmentNumber(); // Added by Pradeep
	
	public String getManager();      // Added by Pradeep
	
	
 	public String toString() ;
	
	@Override
	public abstract boolean equals(Object obj);
	
	
	/**
	 * Session attribute containing the authenticated user
	 */
	public final static String	USER	= "authenticatedUser";
	
	/**
	 * Request parameter name used when passing the user's ticket
	 */
	public final static String TICKET = "ticket";
	
	/**
	 * Request parameter name used when passing the user's ID
	 */
	public final static String USER_ID = "userId";

	public static final String ANONYMOUS_USER = "anonymous-IP";
			
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() ;

	/**
	 * @return the lastName
	 */
	public String getLastName()	;
	/**
	 * @return the fullName
	 */
	public String getFullName() ;

	
	public String getMail();

	public String getBusinessCategory();

	public String getCountry();

	public String getOrganization();

	public String getGrade();

	public String getStatus();

	public String getType();

	public String getUid();

	public Map<String, Subscription> getSubscriptions();
	
}
