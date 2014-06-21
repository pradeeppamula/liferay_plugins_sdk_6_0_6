package org.ieee.common.bean;


public class ContentBean {

	private String imagePath;
	private String url;
	private long id;
	private String title;
	private String urlTitle;
	private String description;
	private String dateTime;
	private long dateTimeMS;
	private String type;
	private String subType;
	private long groupId;	
	private String target;
	private String channel;
	private boolean peerReviewed;
	private String subCategories;
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getUrlTitle() {
		return urlTitle;
	}
	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public long getDateTimeMS() {
		return dateTimeMS;
	}
	public void setDateTimeMS(long dateTimeMS) {
		this.dateTimeMS = dateTimeMS;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}	
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public boolean isPeerReviewed() {
		return peerReviewed;
	}
	public void setPeerReviewed(boolean peerReviewed) {
		this.peerReviewed = peerReviewed;
	}
	public String getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(String subCategories) {
		this.subCategories = subCategories;
	}

}