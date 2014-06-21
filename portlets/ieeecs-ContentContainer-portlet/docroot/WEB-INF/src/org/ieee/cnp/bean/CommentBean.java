package org.ieee.cnp.bean;

public class CommentBean {

	private String name;
	private String title;
	private String body;
	private String avatarUrl;
	private String dateCreated;
	private Long dateCreatedMS;
	private int votes;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}	
	public Long getDateCreatedMS() {
		return dateCreatedMS;
	}
	public void setDateCreatedMS(Long dateCreatedMS) {
		this.dateCreatedMS = dateCreatedMS;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}		
}