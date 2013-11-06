/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.util
 * @created July 11, 2013
 * @description This class will represent the Article for CSDL Bundles
 */
package org.ieeecs.communities.bean;

public class ArticleBean {
	private String id;
	private String imgURL;
	private String title;
	private String date;
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
}
