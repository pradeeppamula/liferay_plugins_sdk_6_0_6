package org.ieeecs.communities.bean;


public class CalendarBean {

	private String imagePath;
	private String url;
	private long id;
	private String title;
	private String urlTitle;
	private String description;
	private String dateTime;
	private long dateTimeMS;
	private long dateTimeS;
	private String type;
	private String subType;
	private long groupId;	
	private String target;
	private String channel;
	private boolean allDay;
	private long start;		// long as the "FullCalendar" lib will expect UNIX timestamp (UTC)
	private long end;		// long as the "FullCalendar" lib will expect UNIX timestamp (UTC)
	private int eventMonth;
	private int eventYear;
	private String eventStartDateTime;
	private String eventEndDateTime;
	private long eventStartDateTimeMS;
	private long eventEndDateTimeMS;
	private long eventStartDateTimeS;
	private long eventEndDateTimeS;	
	private String backgroundColor;
	private String borderColor;
	private String textColor;
	private String className;
	private String message;
	private String eventLocation;
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getUrlTitle() {
		return urlTitle;
	}
	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public long getDateTimeMS() {
		return dateTimeMS;
	}
	public void setDateTimeMS(long dateTimeMS) {
		this.dateTimeMS = dateTimeMS;
	}	
	public long getDateTimeS() {
		return dateTimeS;
	}
	public void setDateTimeS(long dateTimeS) {
		this.dateTimeS = dateTimeS;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}	
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}	
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}	
	public int getEventMonth() {
		return eventMonth;
	}
	public void setEventMonth(int eventMonth) {
		this.eventMonth = eventMonth;
	}
	public int getEventYear() {
		return eventYear;
	}
	public void setEventYear(int eventYear) {
		this.eventYear = eventYear;
	}
	public String getEventStartDateTime() {
		return eventStartDateTime;
	}
	public void setEventStartDateTime(String eventStartDateTime) {
		this.eventStartDateTime = eventStartDateTime;
	}
	public String getEventEndDateTime() {
		return eventEndDateTime;
	}
	public void setEventEndDateTime(String eventEndDateTime) {
		this.eventEndDateTime = eventEndDateTime;
	}
	public long getEventStartDateTimeMS() {
		return eventStartDateTimeMS;
	}
	public void setEventStartDateTimeMS(long eventStartDateTimeMS) {
		this.eventStartDateTimeMS = eventStartDateTimeMS;
	}
	public long getEventEndDateTimeMS() {
		return eventEndDateTimeMS;
	}
	public void setEventEndDateTimeMS(long eventEndDateTimeMS) {
		this.eventEndDateTimeMS = eventEndDateTimeMS;
	}
	public long getEventStartDateTimeS() {
		return eventStartDateTimeS;
	}
	public void setEventStartDateTimeS(long eventStartDateTimeS) {
		this.eventStartDateTimeS = eventStartDateTimeS;
	}
	public long getEventEndDateTimeS() {
		return eventEndDateTimeS;
	}
	public void setEventEndDateTimeS(long eventEndDateTimeS) {
		this.eventEndDateTimeS = eventEndDateTimeS;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

}
