package org.ieee.common.bean;


public class CommunityContentBean extends ContentBean {

	private String expirationDateTime;
	private long expirationDateTimeMS;
	private String creator;
	private boolean comments;
	private boolean social;
	private String multiMedia;
	private String relatedContent;
	private String allSubTypes;
	private String categories;
	private Long resourcePrimKey;
	private String content;
	private boolean visible;
	
	public String getExpirationDateTime() {
		return expirationDateTime;
	}
	public void setExpirationDateTime(String expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}
	public long getExpirationDateTimeMS() {
		return expirationDateTimeMS;
	}
	public void setExpirationDateTimeMS(long expirationDateTimeMS) {
		this.expirationDateTimeMS = expirationDateTimeMS;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public boolean isComments() {
		return comments;
	}
	public void setComments(boolean comments) {
		this.comments = comments;
	}
	public boolean isSocial() {
		return social;
	}
	public void setSocial(boolean social) {
		this.social = social;
	}
	public String getMultiMedia() {
		return multiMedia;
	}
	public void setMultiMedia(String multiMedia) {
		this.multiMedia = multiMedia;
	}
	public String getRelatedContent() {
		return relatedContent;
	}
	public void setRelatedContent(String relatedContent) {
		this.relatedContent = relatedContent;
	}
	public String getAllSubTypes() {
		return allSubTypes;
	}
	public void setAllSubTypes(String allSubTypes) {
		this.allSubTypes = allSubTypes;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public Long getResourcePrimKey() {
		return resourcePrimKey;
	}
	public void setResourcePrimKey(Long resourcePrimKey) {
		this.resourcePrimKey = resourcePrimKey;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}