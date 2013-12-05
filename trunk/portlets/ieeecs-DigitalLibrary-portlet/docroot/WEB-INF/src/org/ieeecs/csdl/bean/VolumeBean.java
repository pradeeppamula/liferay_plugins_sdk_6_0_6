package org.ieeecs.csdl.bean;

public class VolumeBean {

	private String year;
	private String volumeNumber;
	private String url;
	private boolean prePrint;
	private boolean rapidPost;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVolumeNumber() {
		return volumeNumber;
	}
	public void setVolumeNumber(String volumeNumber) {
		this.volumeNumber = volumeNumber;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isPrePrint() {
		return prePrint;
	}
	public void setPrePrint(boolean prePrint) {
		this.prePrint = prePrint;
	}
	public boolean isRapidPost() {
		return rapidPost;
	}
	public void setRapidPost(boolean rapidPost) {
		this.rapidPost = rapidPost;
	}
	
}