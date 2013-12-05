package org.ieeecs.csdl.bean;

import java.text.SimpleDateFormat;
import java.util.*;

import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;

import org.ieeecs.csdl.util.DigitalLibraryUtil;


public class BaseCSDLBean {

	private String title;
	private String price;
	private String updateTime;
	private String description;
	private StatusBean status;
	private Map<String,String> metadataMap = new HashMap<String,String>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatusBean getStatus() {
		return status;
	}
	public void setStatus(StatusBean status) {
		this.status = status;
	}
	public void setStatus(String message, String messageType) {
		this.status = new StatusBean(message, messageType); 
	}
	public Map<String, String> getMetadataMap() {
		return metadataMap;
	}
	public void setMetadataMap(Map<String, String> metadataMap) {
		this.metadataMap = metadataMap;
	}
	public void putInMetadataMap(String key, String value) {
		this.metadataMap.put(key, value);
	}		
	public void populateMetadataMap(JSONArray keyValuePairs) {
		
		try {
		
			if ( null != keyValuePairs && keyValuePairs.length() > 0 ) {
				for ( int valIndex = 0; valIndex < keyValuePairs.length(); valIndex++ ) {
					JSONObject contentNamePair = keyValuePairs.getJSONObject(valIndex);
					String currentName    = DigitalLibraryUtil.getString(contentNamePair, "name");
					String currentContent = DigitalLibraryUtil.getString(contentNamePair, "content");
					this.putInMetadataMap(currentName, currentContent);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void populateMetadataMap(JSONArray keyValuePairs, String dateMask) {
		
		try {
		
			if ( null != keyValuePairs && keyValuePairs.length() > 0 ) {
				for ( int valIndex = 0; valIndex < keyValuePairs.length(); valIndex++ ) {
					JSONObject contentNamePair = keyValuePairs.getJSONObject(valIndex);
					String currentName    = DigitalLibraryUtil.getString(contentNamePair, "name");
					String currentContent = DigitalLibraryUtil.getString(contentNamePair, "content");
					
					if ( "conferenceStartDate".equalsIgnoreCase(currentName) || "conferenceEndDate".equalsIgnoreCase(currentName) ) {
						
						Date date = (new SimpleDateFormat(dateMask)).parse(currentContent);
						String monthName = new SimpleDateFormat("MMMM").format(date);
						String dayNumber = new SimpleDateFormat("dd").format(date);
						currentContent = monthName + "-" + dayNumber;	
					}
					
					this.putInMetadataMap(currentName, currentContent);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}