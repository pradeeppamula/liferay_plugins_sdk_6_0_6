package org.ieeecs.csdl.bean;

public class ReferenceBean {

	private String referenceId;
	private String display;
	
	public ReferenceBean() {}
	
	public ReferenceBean(String referenceId, String display) {
		super();
		this.referenceId = referenceId;
		this.display = display;
	}
	
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
		
}