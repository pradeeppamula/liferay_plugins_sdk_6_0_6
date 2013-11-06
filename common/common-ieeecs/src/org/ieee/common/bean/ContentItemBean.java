package org.ieee.common.bean;

public class ContentItemBean {

	private String type;
	private String label;
	private int bitValue;
	private String className;
		
	public ContentItemBean(String type, String label, int bitValue, String className) {
		super();
		this.type = type;
		this.label = label;
		this.bitValue = bitValue;
		this.className = className;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getBitValue() {
		return bitValue;
	}
	public void setBitValue(int bitValue) {
		this.bitValue = bitValue;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}