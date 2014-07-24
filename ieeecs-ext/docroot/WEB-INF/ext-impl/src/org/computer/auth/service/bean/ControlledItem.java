package org.computer.auth.service.bean;


public interface ControlledItem {
	
	public abstract KeyCategory getKeyCategory();

	public abstract java.math.BigInteger getAccessMask();

	public abstract Long getId();
}
