/**
 * 
 */
package org.ieeecs.communities.bean;

public class ArticleBean {
	private String id;
	private String imgURL;
	private String title;
	private String date;
	private String shortDescription;
	private String likes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return this.date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getShortDescription() {
		return this.shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLikes() {
		return this.likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
}
