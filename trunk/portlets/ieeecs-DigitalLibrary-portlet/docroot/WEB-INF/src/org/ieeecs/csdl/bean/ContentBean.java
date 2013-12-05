package org.ieeecs.csdl.bean;

import java.util.*;


public class ContentBean extends BaseCSDLBean {

	private String mediaFormats;
	private String summary;
	private String keywords;	
	private String contentId;
	private String defaultPrice;
	private String copyright;
	private String publicationDate;
	private String contentType;
	private String uri;	
	private String xplore;
	private String subTitle;
	private String length;
	private String filename;
	private String peerReview;
	private String doi;
	private String categoryTitle;
	private PackageBean packageBean;
	private Map<String,ReferenceBean> referenceMap = new HashMap<String,ReferenceBean>();
	private List<AssociatedContentBean> associatedContentList = new ArrayList<AssociatedContentBean>();
	private List<String> categoryList = new ArrayList<String>();
	private List<CreatorBean> creatorList = new ArrayList<CreatorBean>();
	
	public String getMediaFormats() {
		return mediaFormats;
	}
	public void setMediaFormats(String mediaFormats) {
		this.mediaFormats = mediaFormats;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getDefaultPrice() {
		return defaultPrice;
	}
	public void setDefaultPrice(String defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getXplore() {
		return xplore;
	}
	public void setXplore(String xplore) {
		this.xplore = xplore;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPeerReview() {
		return peerReview;
	}
	public void setPeerReview(String peerReview) {
		this.peerReview = peerReview;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}	
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public PackageBean getPackageBean() {
		return packageBean;
	}
	public void setPackageBean(PackageBean packageBean) {
		this.packageBean = packageBean;
	}
	public Map<String, ReferenceBean> getReferenceMap() {
		return referenceMap;
	}
	public void setReferenceMap(Map<String, ReferenceBean> referenceMap) {
		this.referenceMap = referenceMap;
	}
	public void putReferenceInReferenceMap(String key, ReferenceBean value) {
		this.referenceMap.put(key, value);
	}		
	public List<AssociatedContentBean> getAssociatedContentList() {
		return associatedContentList;
	}
	public void setAssociatedContentList(List<AssociatedContentBean> associatedContentList) {
		this.associatedContentList = associatedContentList;
	}
	public void addAssociatedContentBeanToAssociatedContentList(AssociatedContentBean associatedContentBean) {
		this.associatedContentList.add(associatedContentBean);
	}		
	public List<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	public void addCategoryToCategoryList(String category) {
		this.categoryList.add(category);
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