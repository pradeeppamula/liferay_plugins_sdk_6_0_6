package org.ieeecs.csdl.bean;

public class PublicationBean {

	private String pubType;
	private String idPrefix;
	private String url;
	private String startYear;
	private String endYear;
	private String startVolume;
	private boolean rapidPosts;
	private boolean prePrints;
	private String productId;
	private String display;
	private Integer listOrder;
	
	public String getPubType() {
		return pubType;
	}
	public void setPubType(String pubType) {
		this.pubType = pubType;
	}
	public String getIdPrefix() {
		return idPrefix;
	}
	public void setIdPrefix(String idPrefix) {
		this.idPrefix = idPrefix;
	}	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getStartVolume() {
		return startVolume;
	}
	public void setStartVolume(String startVolume) {
		this.startVolume = startVolume;
	}
	public boolean isRapidPosts() {
		return rapidPosts;
	}
	public void setRapidPosts(boolean rapidPosts) {
		this.rapidPosts = rapidPosts;
	}
	public boolean isPrePrints() {
		return prePrints;
	}
	public void setPrePrints(boolean prePrints) {
		this.prePrints = prePrints;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

}