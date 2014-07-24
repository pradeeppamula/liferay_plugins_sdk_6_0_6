package org.computer.auth.service.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

public class Subscription implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -276120021879099932L;
	
	private String name = "";
	private String cid = "";
	private Calendar expirationDate = Calendar.getInstance();	
	private Calendar startDate = null;
	private boolean active = false;
	private boolean subscribed;
	private String ldapValue;
	private Integer packageId;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	
	
	public Subscription() {
		super();
	}



	public Subscription(String ldapValue) {
		// Save the original value
		this.ldapValue = ldapValue;
		
		// The ldap value comes in the form of
		// <name>.<expirationDate>
		String[] values = ldapValue.split(",", 2);
		this.name = values[0];
		
		// The expiration date is in the form
		// YYYYMMdd

		try {
			this.expirationDate.setTime(dateFormat.parse(values[1]));
			this.active = this.expirationDate.after(Calendar.getInstance());
		} catch (Exception e) {
			this.active = false;
		}	
	}
	
	
	
	public Subscription(String name, Calendar expirationDate, Calendar startDate) {		
		this.name = name;
		this.expirationDate = expirationDate;
		this.startDate = startDate;
		Calendar now = Calendar.getInstance();
		active = (this.startDate == null || this.startDate.before(now)) && (this.expirationDate == null || this.expirationDate.after(now));
	}



	public String getName() {
		return this.name;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public Calendar getExpirationDate() {
		return this.expirationDate;
	}
	
	public String toCookieValue() {
		return this.name + "," + dateFormat.format(this.expirationDate.getTime());
	}
	
	protected String getLdapValue() {
		return this.ldapValue;
	}
	
	public static String toString(Collection<Subscription> subscriptions) {
		StringBuffer buf = new StringBuffer();
		for (Iterator<Subscription> itr = subscriptions.iterator(); itr.hasNext(); ) {
			Subscription sub = itr.next();
			if (buf.length() > 0) buf.append(':');
			buf.append(sub.getLdapValue());
		}
		return buf.toString();
	}
	
	public static Collection<Subscription> fromString(String value) {
		Collection<Subscription> values = new ArrayList<Subscription>();
		String strValues[] = value.split(":");
		for (int i = 0; i < strValues.length; i++) {
			if (strValues[i].trim().length() == 0) continue;
			values.add(new Subscription(strValues[i]));
		}
		return values;
	}



	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}



	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg) {
		boolean equals = true;
		
		if(arg ==null || !(arg instanceof Subscription))
		{
			equals = false;
		}
		Subscription subToCompare = (Subscription)arg;
		equals = equals && ((name !=null && name.equals(subToCompare.getName())) || (name==null && subToCompare.getName()==null));
		equals = equals && ((startDate !=null && startDate.equals(subToCompare.getStartDate())) || (startDate==null && subToCompare.getStartDate()==null));
		equals = equals && ((expirationDate !=null && expirationDate.equals(subToCompare.getExpirationDate())) || (expirationDate==null && subToCompare.getExpirationDate()==null));
		return equals;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int expDateInt = expirationDate==null?31:31+expirationDate.hashCode();
		int startDateInt = startDate==null?17:17+startDate.hashCode();
		int nameInt = name==null?13:13+name.hashCode();
		return getClass().getName().hashCode()+nameInt+startDateInt+expDateInt;		
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}



	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}



	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}



	/**
	 * @return the subscribed
	 */
	public boolean isSubscribed() {
		return subscribed;
	}



	/**
	 * @param subscribed the subscribed to set
	 */
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}



	/**
	 * @return the packageId
	 */
	public Integer getPackageId() {
		return packageId;
	}



	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	
}
