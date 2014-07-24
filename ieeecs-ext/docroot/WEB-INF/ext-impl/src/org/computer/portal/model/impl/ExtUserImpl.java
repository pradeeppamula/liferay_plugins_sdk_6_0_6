package org.computer.portal.model.impl;

import org.computer.portal.model.ExtUser;


public class ExtUserImpl extends ExtUserModelImpl implements ExtUser {
    public ExtUserImpl() {
    }
    
    public String toString() {
    	return "userID: " + this.getUserId() + ", " +
    	       "is Member: " + this.isCsMember() + ", " +
    	       "is Staff : " + this.isStaff() + ", " +
    	       "can Create ICs: " + this.getCanCreateInstantCommunities();
    }
}
