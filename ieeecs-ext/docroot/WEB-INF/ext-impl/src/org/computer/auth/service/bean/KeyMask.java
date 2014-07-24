package org.computer.auth.service.bean;

import java.io.Serializable;
import java.math.BigInteger;


public class KeyMask implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5773764939346512626L;
	
	private String		code;
	private Pricing	    price;
	private BigInteger 	keyMask;
	private KeyCategory	keyCategory;

	// Constructors

	/** default constructor */
	public KeyMask() {}

	/** full constructor */
	protected KeyMask(String code, Pricing price, BigInteger keyMask, KeyCategory keyCategory) {
		this.code = code;
		this.price = price;
		this.keyMask = keyMask;
		this.keyCategory = keyCategory;
	}

	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Pricing getPricing() {
		return this.price;
	}

	public void setPricing(Pricing price) {
		this.price = price;
	}

	public BigInteger getMask() {
		return this.keyMask;
	}

	public void setMask(BigInteger keyMask) {
		this.keyMask = keyMask;
	}

	public KeyCategory getKeyCategory() {
		return this.keyCategory;
	}

	public void setKeyCategory(KeyCategory keyCategory) {
		this.keyCategory = keyCategory;
	}

	public KeyMask merge(KeyMask newKey) {
		if (this.keyMask.or(newKey.getMask()).equals(this.keyMask))
			return this;
		KeyMask newMask = new KeyMask();
		newMask.code = this.code + ":" + newKey.getCode();
		newMask.price = this.price;
		newMask.keyCategory = this.keyCategory;
		newMask.keyMask = this.keyMask.or(newKey.getMask());
		return newMask;
	}

	public String toString() {
		return "code: '" + this.code + "',\tpricing: " + this.price.getPrice() + ",\tmask: "
				+ this.keyMask.toString(16);
	}
	
	public KeyMask copyOf() {
		KeyMask copy = new KeyMask(this.code, 
				                   this.price.copyOf(), 
				                   new BigInteger(this.keyMask.toByteArray()), 
				                   this.keyCategory.copyOf());
		return copy;
	}
	
}