package org.ieee.cnp.bean;

import java.util.*;


public class GroupBean {

	private Long groupId;
	private String name;
	private String friendlyUrl;
	private String selected;
	private List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFriendlyUrl() {
		return friendlyUrl;
	}
	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
	}	
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public List<CategoryBean> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryBean> categoryList) {
		this.categoryList = categoryList;
	}
	public void addCategory(CategoryBean cb) {
		this.categoryList.add(cb);
	}
}