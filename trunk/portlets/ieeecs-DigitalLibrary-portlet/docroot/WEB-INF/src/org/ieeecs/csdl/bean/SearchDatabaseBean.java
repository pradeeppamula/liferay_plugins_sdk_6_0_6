package org.ieeecs.csdl.bean;

public class SearchDatabaseBean {
	private String abbrev;
	private String idPrefix;
	private String searchDatabase;
	
	
	/**
	 * @return the idPrefix
	 */
	public String getIdPrefix() {
		return idPrefix;
	}
	/**
	 * @param idPrefix the idPrefix to set
	 */
	public void setIdPrefix(String idPrefix) {
		this.idPrefix = idPrefix;
	}
	/**
	 * @return the abbrev
	 */
	public String getAbbrev() {
		return abbrev;
	}
	/**
	 * @param abbrev the abbrev to set
	 */
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
	/**
	 * @return the searchDatabase
	 */
	public String getSearchDatabase() {
		return searchDatabase;
	}
	/**
	 * @param searchDatabase the searchDatabase to set
	 */
	public void setSearchDatabase(String searchDatabase) {
		this.searchDatabase = searchDatabase;
	}
	
}
