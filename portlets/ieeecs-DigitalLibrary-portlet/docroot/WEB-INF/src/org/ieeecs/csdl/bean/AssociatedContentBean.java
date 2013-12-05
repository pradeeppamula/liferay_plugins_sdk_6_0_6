package org.ieeecs.csdl.bean;

import java.util.*;


public class AssociatedContentBean extends BaseCSDLBean {

	private String price;
	private String link;
	private String displayString;
	private String linkString;
	private String mediaType;
	private String associatedContentId;
	private String linkType;
	private List<CreatorBean> creatorList = new ArrayList<CreatorBean>();
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDisplayString() {
		return displayString;
	}
	public void setDisplayString(String displayString) {
		this.displayString = displayString;
	}
	public String getLinkString() {
		return linkString;
	}
	public void setLinkString(String linkString) {
		this.linkString = linkString;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getAssociatedContentId() {
		return associatedContentId;
	}
	public void setAssociatedContentId(String associatedContentId) {
		this.associatedContentId = associatedContentId;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public List<CreatorBean> getCreatorList() {
		return creatorList;
	}
	public void setCreatorList(List<CreatorBean> creatorList) {
		this.creatorList = creatorList;
	}
	public void addCreatorToCreatorList(CreatorBean creatorBean) {
		this.creatorList.add(creatorBean);
	}
		
}