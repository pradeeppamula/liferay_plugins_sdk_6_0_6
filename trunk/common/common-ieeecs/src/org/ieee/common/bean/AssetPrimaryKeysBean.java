package org.ieee.common.bean;

import com.liferay.portlet.asset.model.AssetCategory;

import java.util.*;


public class AssetPrimaryKeysBean {

	private long entryId;
	private long groupId;
	private long companyId;
	private long userId;
	private long contentId;
	private List<AssetCategory> categoryList = new ArrayList<AssetCategory>();
	
	public long getEntryId() {
		return entryId;
	}
	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	public List<AssetCategory> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<AssetCategory> categoryList) {
		this.categoryList = categoryList;
	}
	public void addCategory(AssetCategory category) {
		this.categoryList.add(category);
	}
}