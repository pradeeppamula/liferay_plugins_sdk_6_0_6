<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">

	.title {
		float: left;
		font: ${titleFont};
		color: #${titleColor};
		margin: 3px 0px 0px 10px;
		display: none;	
	}	
	
	#filterReset {
		color: #${titleColor};
	}
	
	.filtered {
		color: #${titleColor};
		font: 12px arial,sans-serif;
	}	

	#communityCalendarContainer {
		margin: 0; 
		padding: 0 0 20px 0;
		border-top: ${portletBorderPixelTop}px solid #${portletBorderColorTop};
		border-right: ${portletBorderPixelRight}px solid #${portletBorderColorRight};
		border-bottom: ${portletBorderPixelBottom}px solid #${portletBorderColorBottom};
		border-left: ${portletBorderPixelLeft}px solid #${portletBorderColorLeft};
		background-color: #${portletBackgroundColor};	
		border-top-left-radius: ${portletTopLeftRadius}px ${portletTopLeftRadius}px;
		-moz-border-top-left-radius: ${portletTopLeftRadius}px ${portletTopLeftRadius}px;
		border-top-right-radius: ${portletTopRightRadius}px ${portletTopRightRadius}px;		
		-moz-border-top-right-radius: ${portletTopRightRadius}px ${portletTopRightRadius}px;		
		border-bottom-left-radius: ${portletBottomLeftRadius}px ${portletBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${portletBottomLeftRadius}px ${portletBottomLeftRadius}px;
		border-bottom-right-radius: ${portletBottomRightRadius}px ${portletBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${portletBottomRightRadius}px ${portletBottomRightRadius}px;	
		overflow-x: hidden; 
		overflow-y: auto;
	}

	#communityCalendarHeaderBar {
		padding: 5px 5px 5px 5px;
		margin: ${titleTopMargin}px 0 ${titleBottomMargin}px 0;
		border: 1px solid #${titleBorderColor};
		background-color: #${titleBackgroundColor};
		border-top-left-radius: ${titleTopLeftRadius}px ${titleTopLeftRadius}px;
		-moz-border-top-left-radius: ${titleTopLeftRadius}px ${titleTopLeftRadius}px;
		border-top-right-radius: ${titleTopRightRadius}px ${titleTopRightRadius}px;		
		-moz-border-top-right-radius: ${titleTopRightRadius}px ${titleTopRightRadius}px;		
		border-bottom-left-radius: ${titleBottomLeftRadius}px ${titleBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${titleBottomLeftRadius}px ${titleBottomLeftRadius}px;
		border-bottom-right-radius: ${titleBottomRightRadius}px ${titleBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${titleBottomRightRadius}px ${titleBottomRightRadius}px;
		line-height: 1.0em;
	}
	
	#calendar {
		width: 95%;
		margin: 0 auto;
	}
	
	.fc-state-highlight { 
		background: #${highlightBackgroundColor};
	}	
	
	.customEventCell {
		${eventCSS}
	}
	
	.eventHomePageWindow {
		display: none;
		z-index: 20000;
		position: fixed;
		top: ${modalTopOffset}px;
		left: 0;
		width: 100%;
		background-color: #FFFFFF;
		border-top: 3px solid #000000;
	}	
	
</style>

<!-- TOP HEADER/TITLE BAR -->
<div id="communityCalendarHeaderBar" class="communityCalendarHeaderBar">
	<div class="title" id="titleText"></div>
	<div class="filterReset" id="filterReset">RESET</div>
	<div class="filterInputContainer" id="filterInputContainer">
		<input type="text" size="35" id="filterValue" name="filterValue" value="${searchInputText}" class="filterInput"/>
	</div>			
	<div class="clearBoth"></div>
</div>

<%-- ************************ --%>
<%--     Calendar Content     --%>
<%-- ************************ --%>
<div id="communityCalendarContainer">
	<div class="communityCalendarModeViews">
		<div class="communityCalendarModeButtonLeft" id="calendarMode">Calendar</div>
		<div class="communityCalendarModeButtonRight" id="listMode">List</div>
		<div class="clearBoth"></div>
	</div>
	<div class="communityCalendarMessages" id="messages"></div>
	<div class="clearBoth"></div>
	<div class="calendarContainers">
		<div class="calendarView" id="calendar"></div>
		<div class="listView" id="calendarList"></div>
	</div>
	<div class="clearBoth"></div>
</div>

<%-- ************************ --%>
<%--     Event Information    --%>
<%-- ************************ --%>
<div class="eventInformationModal" id="infoModal">
	<div class="eventInformationTitle" id="eventTitle"></div>
	<div class="eventInformationDescription" id="eventDescription"></div>
	<div class="eventInformationLocation" id="eventLocation"></div>
	<div class="eventInformationTimes" id="startTime"></div>
	<div class="eventInformationTimes" id="endTime"></div>
	<div class="eventInformationUrl" id="eventUrl"><a href="" id="eventUrlLink"></a></div>
</div>

<%-- ************************ --%>
<%--    List Item Template    --%>
<%-- ************************ --%>
<div id="listTemplate" style="display: none;">
	<div class="listItemEntry" id="listEntry_[%=id%]">
		<div class="listItemEntryDate">[%=dateRange%] of [%=monthName%] [%=year%]</div>
		<div class="listItemEntryTitle"><a href="javascript: showEventInfoUsingId([%=id%]);">[%=title%]</a></div> 
		<div class="clearBoth"></div> 
	</div>
</div>

<%-- ************************ --%>
<%--  Event Home Page Window  --%>
<%-- ************************ --%>
<div class="eventHomePageWindow">
	<div class="closeEventHomePageWindow">${backToText}</div>
</div>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/main.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/underscore.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/backbone.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/models.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/jtip.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/gcal.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/ejs_production.js"></script>




<iframe style="display:none;" id="testIFrame" src="http://newton.computer.org/confcal.nsf/calendarfeed?openagent"></iframe>

<script language="Javascript">

	$.ajaxSetup({ cache: false });

	<%-- ***************************************************** 
		Initial Values/Variables 
	***************************************************** --%>
	var initialTitle = "${titleOfCalendar}"; 
	var hideTitle = "${titleVisible}";
	var hideFilter = "${filtering}";
	var eventCollection = new EventItemCollection();
	var filterCollection = new EventItemCollection();
	var monthArray = ["January","February","March","April","May","June","July","August","September","October","November","December"];
	
	eventCollection.comparator = function(contentItem) {
		var startSeconds = contentItem.get("start");
		return startSeconds;		
	};	
		
	<%-- *****************************************************
		Initialize the Title Text
	***************************************************** --%>
	populateTitle(initialTitle);
	
	<%-- *****************************************************
		Hide/Show the Title
	***************************************************** --%>
	if ( hideTitle.toUpperCase() == "NO" ) {
		$("#titleText").show();			
	} else {
		$("#titleText").hide();
	}
	
	<%-- *****************************************************
		Hide/Show the Filter Input/Reset
	***************************************************** --%>
	if ( hideFilter.toUpperCase() == "NO" ) {
		$("#filterReset").show();
		$("#filterInputContainer").show();
	} else {
		$("#filterReset").hide();
		$("#filterInputContainer").hide();		
	}	
	
	<%-- *****************************************************
		If Title and Filter are to be hidden, hide the 
		entire Bar
	***************************************************** --%>
	if ( hideTitle.toUpperCase() == "YES" && hideFilter.toUpperCase() == "YES" ) {
		$("#communityCalendarHeaderBar").hide();
	} else {
		$("#communityCalendarHeaderBar").show();	
	}	
	
	<%-- *****************************************************
		Change the Content Calendar title at will...
	***************************************************** --%>
	function populateTitle(titleText) {
		if ( hideTitle == "NO" ) {
			$("#titleText").html(titleText);
		}		
	}				
	
	<%-- *****************************************************
		Populate and configure the calendar
	***************************************************** --%>	
	var communityCalendarJSON = ${communityCalendarJSON};

	$(document).ready(function() {	
	
	$('#calendar').fullCalendar({
		slotMinutes: ${slotMinutes},
		allDaySlot: true,
		aspectRatio: ${aspectRatio},
		weekMode: "liquid",
		header: {
			left: "prev,next",
			center: "title",
			right: "month,agendaWeek,today"
		},
		editable: false,
		eventColor: "#CCCCCC",
		eventTextColor: "#000000",
		events: communityCalendarJSON.list,
		eventClick: function(calEvent, jsEvent, view) {
			showEventInfo(calEvent.title, 
					calEvent.description, 
					calEvent.eventLocation, 
					calEvent.eventStartDateTime, 
					calEvent.eventEndDateTime, 
					calEvent.url, 
					100, 600, 200, 800);
			return false;
		}
	});	
	
	});	

	function showEventInfo(title, description, eventLocation, eventStartDateTime, eventEndDateTime, url, minHeight, minWidth, maxHeight, maxWidth) {
		
		$("html, body").animate({"scrollTop": 0 }, 0);
		$("#eventTitle").html(title);
		$("#eventDescription").html(description);
		$("#eventLocation").html("<u>Location</u>: " + eventLocation);
		
		if ( eventStartDateTime.indexOf("00:00") > -1 ) {
			$("#startTime").html("<u>Start Date/Time</u>: " + eventStartDateTime.substring(0, 10));
		} else {
			$("#startTime").html("<u>Start Date/Time</u>: " + eventStartDateTime);
		}
		
		if ( eventEndDateTime.indexOf("00:00") > -1 ) {
			$("#endTime").html("<u>End Date/Time</u>: " + eventEndDateTime.substring(0, 10));
		} else {
			$("#endTime").html("<u>End Date/Time</u>: " + eventEndDateTime);
		}		
		
		if ( url == "" || url == "http://" ) {
			$("#eventUrlLink").html("Link currently not available.");
			$("#eventUrlLink").attr("href", "");			
		} else {
			$("#eventUrlLink").html("For more information, please follow this link.");	
			$("#eventUrlLink").attr("href", "javascript: showEventHomePage('" + url + "');");			
		}
						
		$("#infoModal").modal({"minHeight": minHeight, "minWidth":minWidth, "maxHeight": maxHeight, "maxWidth":maxWidth});		
	}
	
	function showEventInfoUsingId(id) {

		var selectedItem = $("#listEntry_" + id);
		var eventData = selectedItem.data("eventData");
		showEventInfo(eventData.title, 
				eventData.description, 
				eventData.eventLocation, 
				eventData.eventStartDateTime, 
				eventData.eventEndDateTime, 
				eventData.url, 
				100, 600, 200, 800);
	}
	
	function showEventHomePage(url) {

		location.href += "#eventPopUp";
		
		var browserWindowHeight = $(window).height();
		$(".eventHomePageWindow").height(browserWindowHeight - ${modalTopOffset});	
		
		var popupIFrame = $("<iframe/>");
		popupIFrame.attr("src", url);
		popupIFrame.attr("scrolling", "auto");	
		$(".eventHomePageWindow").append(popupIFrame);
		
		$.modal.close();		
		$(".eventHomePageWindow").show();		
	}
	
	$(".closeEventHomePageWindow").click(function() {
		location.hash = "";		
	});
	
	<%-- *****************************************************
	    Handle that pesky Back button
	***************************************************** --%>
	$(window).on('hashchange',function() {
		if ( location.hash == "" ) {			
		    history.pushState("", document.title, window.location.pathname); 	
			$(".eventHomePageWindow iframe").remove();
			$(".eventHomePageWindow").hide();
		}
	});	
	
	<%-- *****************************************************
		Calendar Mode Views
	***************************************************** --%>
	$("#calendarMode").css({"background-color":"#727272", "color":"#FFFFFF", "border":"1px solid #333333"});
	
	$("#listMode").click(function() {
		$("#calendarMode").css({"background-color":"#EEEEEE", "color":"#000000", "border":"1px solid #DDDDDD"});
		$(this).css({"background-color":"#727272", "color":"#FFFFFF", "border":"1px solid #333333"});
		$(".calendarView").fadeOut(300, function() {
			$(".listView").show();			
		});
	});
	
	$("#calendarMode").click(function() {
		$("#listMode").css({"background-color":"#EEEEEE", "color":"#000000", "border":"1px solid #DDDDDD"});
		$(this).css({"background-color":"#727272", "color":"#FFFFFF", "border":"1px solid #333333"});		
		$(".listView").fadeOut(300, function() {
			$(".calendarView").show();	
			$("#filterReset").click();
		});
	});
	
	<%-- *****************************************************
		Filter functions
	***************************************************** --%>
	$("#filterValue").keyup(function() {
		var currentValue = $(this).val();
		
		if ( currentValue.length >= 3 ) {
			$("#listMode").click();
			performFilter();
		}		
	});
	
	$("#filterValue").click(function() {
		$(this).val("");
		populateListView(eventCollection);
	});	
	
	$("#filterReset").click(function() {
		$("#filterValue").val("");
		$("#filterValue").click();
		$("#filterValue").val("${searchInputText}");
	});	
	
	function performFilter() {
	
		<%-- Reinit the collection --%>
		filterCollection = new EventItemCollection();	
	
		<%-- Get the filter word(s) --%>
		var filterValue = $("#filterValue").val().toUpperCase();
	
		<%-- Perform the filtering using the Backbone "filter" functionality 
			 In this case I'm searching for the word(s) in the "title" and "description" --%>
		var filterArray = eventCollection.filter(function(event) {
			var currentTitle = event.get("title").toUpperCase();
			var currentDescription = event.get("description").toUpperCase();
			return currentTitle.indexOf(filterValue) > -1 || currentDescription.indexOf(filterValue) > -1;
		});	
		
		var filterSize = filterArray.length;
	
		if ( filterSize > 0 ) {	
		
			<%-- From Array to List, so we can use the Backbone/Underscore "each" later on. --%>
			for (var i in filterArray) {		
				filterCollection.add(filterArray[i]);
			}
			
			<%-- Repopulate the Calendar List --%>
			populateListView(filterCollection);

		} else {
			$("#calendarList").html("");
			var html = new EJS({url: "/ieeecs-CommunityCalendar-portlet/js/template/emptyResults.ejs"}).render();
			var htmlObj = $(html);			
			$("#calendarList").append(htmlObj);				
		}
	}		
	
	<%-- *****************************************************
		Populate the "eventCollection" with events, for both the 
		"List" view as well as filtering.  Check for messages to
		post at the top of the Calendar.
				
		The "message" concept is mainly for admin/moderator 
		warning, hopefully issues will not make it to Production.  
		However, make the message presentable, warning the user  
		that there might be temporary date issues with an entry 
		or two.
	***************************************************** --%>		
	var messages = "";
	$("#messages").html(messages);
	
	$.each(communityCalendarJSON.list, function(index, listItem) {
		
    	var item = new EventItemModel();
    	item.set({"urlTitle": listItem.urlTitle});
    	item.set({"dateTimeS": listItem.dateTimeS});
    	item.set({"eventEndDateTimeMS": listItem.eventEndDateTimeMS});
    	item.set({"imagePath": listItem.imagePath});
    	item.set({"allDay": listItem.allDay});
    	item.set({"dateTime": listItem.dateTime});
    	item.set({"type": listItem.type});
    	item.set({"url": listItem.url});
    	item.set({"id": listItem.id});
    	item.set({"eventStartDateTime": listItem.eventStartDateTime});
    	item.set({"groupId": listItem.groupId});
    	item.set({"eventStartDateTimeS": listItem.eventStartDateTimeS});
    	item.set({"eventEndDateTime": listItem.eventEndDateTime});
    	item.set({"title": listItem.title});
    	item.set({"dateTimeMS": listItem.dateTimeMS});
    	item.set({"start": listItem.start});
    	item.set({"description": listItem.description});
    	item.set({"subType": listItem.subType});
    	item.set({"eventStartDateTimeMS": listItem.eventStartDateTimeMS});
    	item.set({"target": listItem.target});
    	item.set({"channel": listItem.channel});
    	item.set({"eventEndDateTimeS": listItem.eventEndDateTimeS});
    	item.set({"end": listItem.end});
    	item.set({"eventMonth": listItem.eventMonth});
    	item.set({"eventYear": listItem.eventYear});
    	item.set({"eventLocation": listItem.eventLocation});
    	eventCollection.add(item);	
		
		var currentMessage = listItem.message;
		if ( currentMessage != "" ) {
			var title = listItem.title;
			var startDate = listItem.eventStartDateTime;
			var endDate = listItem.eventEndDateTime;
			currentMessage = currentMessage + title + " (" + startDate + " to " + endDate + ")<br/>";
		}
		messages += currentMessage;
	});
	
	$("#messages").html(messages);
	
	<%-- *****************************************************
		Now populate the list view with the event data from 
		above.  The "htmlObj.data()" mechanism is used to supply 
		data to a the event info popup window, when the list 
		event has been clicked.
	***************************************************** --%>	
	populateListView(eventCollection);
	
	function populateListView(collectionObject) {
		
		$("#calendarList").html("");
		
		var lastMonth = 0;
		var lastYear = 0;
					
		collectionObject.each(function(eventItemModel) {
			
			var currentMonth = eventItemModel.attributes.eventMonth;
			var currentYear = eventItemModel.attributes.eventYear;

			if ( lastYear != currentYear ) {				
				lastYear = currentYear;
				var htmlYear = $("<div class='headerYear'><u>" + currentYear + "</u></div>");
				$("#calendarList").append(htmlYear);	
			}			
			
			if ( lastMonth != currentMonth ) {				
				lastMonth = currentMonth;
				var monthName = monthArray[currentMonth];
				var htmlMonth = $("<div class='headerMonth'>" + monthName + "</div>");
				$("#calendarList").append(htmlMonth);	
			}
			
			var eventStartDateTimeS = eventItemModel.attributes.eventStartDateTimeS;
			var eventEndDateTimeS = eventItemModel.attributes.eventEndDateTimeS;
			var eventStartDate = new Date( eventStartDateTimeS * 1000 );
			var eventEndDate = new Date( eventEndDateTimeS * 1000 );
			var startDay = eventStartDate.getDate();
			var endDay = eventEndDate.getDate();
			
			var dateRange = "";
			if ( startDay == endDay ) {
				dateRange = daySuffix(startDay);
			} else {
				dateRange = daySuffix(startDay) + " to " + daySuffix(endDay);
			}
			
			var data = {};
			data.id = eventItemModel.attributes.id;
			data.dateRange = dateRange;
			data.monthName = monthArray[currentMonth];
			data.year = currentYear;
			data.title = eventItemModel.attributes.title;
			data.description = eventItemModel.attributes.description;
			data.url = eventItemModel.attributes.url;
			data.eventLocation = eventItemModel.attributes.eventLocation;
			data.eventStartDateTime = eventItemModel.attributes.eventStartDateTime;
			data.eventEndDateTime = eventItemModel.attributes.eventEndDateTime;			
			
			var html = new EJS({element: "listTemplate"}).render(data);
			var htmlObj = $(html);
			htmlObj.data("eventData", data);
			$("#calendarList").append(htmlObj);	
		});
	}
	
	function daySuffix(dayValue) {
		
		var dayWithSuffix = dayValue;
		
		switch (parseInt(dayValue,10)) {
			case 1: case 21: case 31:
				dayWithSuffix = dayValue + "st";
				break;
			case 2: case 22:
				dayWithSuffix = dayValue + "nd";
				break;
			case 3: case 23:
				dayWithSuffix = dayValue + "rd";
				break;
			default:
				dayWithSuffix = dayValue + "th";
				break;
		}

		return dayWithSuffix;		
	}
</script>

</c:if>
