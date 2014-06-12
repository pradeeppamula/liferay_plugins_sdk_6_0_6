package org.ieeecs.communities.bean;

import com.liferay.portlet.asset.model.AssetEntry;

import java.util.ArrayList;
import java.util.List;


public class AssetCalendarBean {

	private List<AssetEntry> assetEntryList = new ArrayList<AssetEntry>();
	private String backgroundColor;
	private String borderColor;
	private String textColor;
	
	public List<AssetEntry> getAssetEntryList() {
		return assetEntryList;
	}
	public void setAssetEntryList(List<AssetEntry> assetEntryList) {
		this.assetEntryList = assetEntryList;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getTextColor() {
		return textColor;
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
		
}
