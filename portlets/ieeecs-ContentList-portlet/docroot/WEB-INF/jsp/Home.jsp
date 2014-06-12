<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">

	.contentItemWrapper${id} {
		position: relative;
		${portletContainerItemCSS}
	}
	
	.title${id} {
		float: left;
		font: ${titleFont};
		color: #${titleColor};
		margin: 3px 0px 0px 10px;
		display: none;	
	}	
	
	#filterReset${id} {
		color: #${titleColor};
	}
	
	.filtered${id} {
		color: blue;
	}	

	#contentListContainer${id} {
		margin: 0; 
		padding: 20px 10px 0 10px;
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
		resize: both; 
		overflow-x: hidden;
		overflow-y: auto;
		height: ${portletContainerHeight}px;
	}

	#contentListHeaderBar${id} {
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
	
	#prevPage${id}, #nextPage${id}  {
		float: right;
		width: ${paginationWidth}px;
		height: ${paginationHeight}px;
		cursor: pointer;
		margin: 15px 5px 5px 5px;
		text-align: center;
		padding: ${paginationOffset}px 0 0 0;
		border: 1px solid #${paginationBorderColor};
		background-color: #${paginationBackgroundColor};		
	}

	#prevPage${id} {	
		border-top-left-radius: ${previousTopLeftRadius}px ${previousTopLeftRadius}px;
		-moz-border-top-left-radius: ${previousTopLeftRadius}px ${previousTopLeftRadius}px;
		border-top-right-radius: ${previousTopRightRadius}px ${previousTopRightRadius}px;		
		-moz-border-top-right-radius: ${previousTopRightRadius}px ${previousTopRightRadius}px;		
		border-bottom-left-radius: ${previousBottomLeftRadius}px ${previousBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${previousBottomLeftRadius}px ${previousBottomLeftRadius}px;
		border-bottom-right-radius: ${previousBottomRightRadius}px ${previousBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${previousBottomRightRadius}px ${previousBottomRightRadius}px;
	}		
	
	#nextPage${id} {	
		border-top-left-radius: ${nextTopLeftRadius}px ${nextTopLeftRadius}px;
		-moz-border-top-left-radius: ${nextTopLeftRadius}px ${nextTopLeftRadius}px;
		border-top-right-radius: ${nextTopRightRadius}px ${nextTopRightRadius}px;		
		-moz-border-top-right-radius: ${nextTopRightRadius}px ${nextTopRightRadius}px;		
		border-bottom-left-radius: ${nextBottomLeftRadius}px ${nextBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${nextBottomLeftRadius}px ${nextBottomLeftRadius}px;
		border-bottom-right-radius: ${nextBottomRightRadius}px ${nextBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${nextBottomRightRadius}px ${nextBottomRightRadius}px;
	}	
	
	.overlayScreen${id} {
		position: absolute;
		top: 0;
		left: 0;
		background-color: #CCCCCC;	
		z-index: 10;
		opacity: 0;
		filter: alpha(opacity = 0);
		cursor: pointer;
	}
	
	#paginationMessage${id} {
		float: left;
		width: 40%;
		font: bold 14px arial, helvetica, sans-serif;
		color: blue;
		margin: 20px 0 0 0;
	}

</style>

<%-- ********************************* --%>
<%--   Container Holding All - START   --%>
<%-- ********************************* --%>
<div id="contentAndButtonsContainer${id}">

<%-- ********************************* --%>
<%--        Top Header/Title Bar       --%>
<%-- ********************************* --%>
<div id="contentListHeaderBar${id}" class="contentListHeaderBar">
	<div class="title${id}" id="titleText${id}"></div>
	<div class="filterReset" id="filterReset${id}">RESET</div>
	<div class="filterInputContainer" id="filterInputContainer${id}">
		<input type="text" size="30" id="filterValue${id}" name="filterValue${id}" value="${searchInputText}" class="filterInput"/>
	</div>	
	<div class="clearBoth"></div>
</div>

<%-- ************************ --%>
<%--    Scrollable Content    --%>
<%-- ************************ --%>
<div id="contentListContainer${id}" class="lazyLoadImages">
	<div id="scrollableListContent${id}">
	<noscript>
		<c:forEach var="contentItem" items="${contentList}" varStatus="cnIndex">
		<div class="contentItemWrapper${id}">	
			<div class="listItemThumbImage">
				<a href="${contentItem.url}" target="${contentItem.target}"><img class="lazy" src="/ieeecs-ContentList-portlet/images/default.png" alt="${contentItem.title}" data-original="${contentItem.imagePath}"/></a>                  
			</div>
			<div class="standardContentItemBody">
				<div class="standardContentItemText">
					<h1><c:if test="${contentItem.channel != ''}"><span>${contentItem.channel} - </span></c:if><a href="${contentItem.url}" target="${contentItem.target}">${contentItem.title}</a></h1>
					<h2>${contentItem.dateTime}</h2>
					<p><c:if test="${contentItem.subType != ''}"><span>${contentItem.subType}: </span></c:if><c:if test="${contentItem.subType == ''}"><span>ARTICLE: </span></c:if> ${contentItem.description}</p>
				</div>
				<div class="clearBoth"></div>
			</div>
			<div class="clearBoth"></div>
		</div>		
		</c:forEach>	
	</noscript>	
	</div>
</div>

<%-- ************************ --%>
<%--   Bottom Navigation Bar  --%>
<%-- ************************ --%>
<div class="navHolder" id="navHolder${id}">
	<div id="paginationMessage${id}"></div>
	<div class="nextPage" id="nextPage${id}"><div class="nextText" id="nextText${id}">${nextText}</div></div>
	<div class="prevPage" id="prevPage${id}"><div class="prevText" id="prevText${id}">${prevText}</div></div>
	<div class="clearBoth"></div>
</div>

<%-- ************************ --%>
<%--  Filter Results Message  --%>
<%-- ************************ --%>
<div id="filterResultsMessage${id}" class="filterResultsMessage"></div>

<%-- ************************ --%>
<%--  Content List Templates  --%>
<%-- ************************ --%>
<div id="standard${id}" style="display: none;">
	<div class="contentItemWrapper${id}">
		[% if(peerReviewed) { %]<div class="peerReviewedIcon_LargeRight"></div>[% } %]
		<div class="listItemThumbImage">
			<a href="[%=url%]" target="[%=target%]"><img class="lazy" src="/ieeecs-ContentList-portlet/images/default.png" data-original="[%=imagePath%]" alt="[%=title%]"/></a>                  
		</div>
		<div class="standardContentItemBody">
			<div class="standardContentItemText">
				<h1>[% if(channel!='') { %]<span>[%=channel%] - </span>[% } %]<a href="[%=url%]" target="[%=target%]">[%=title%]</a></h1>
				<h2>[%=dateTime%]</h2>
				<p>[% if(subType!='') { %]<span>[%=subType%]: </span>[% } else { %]<span>ARTICLE: </span>[% } %] [%=description%]</p>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<div id="minimal1${id}" style="display: none;">
	<div class="contentItemWrapper${id}">	
		[% if(peerReviewed) { %]<div class="peerReviewedIcon_SmallRight"></div>[% } %]
		<div class="listItemSmallThumbImage">
			<a href="[%=url%]" target="[%=target%]"><img class="lazy" src="/ieeecs-ContentList-portlet/images/default.png" data-original="[%=imagePath%]" alt="[%=title%]"/></a>                  
		</div>
		<div class="minimal1ContentItemBody">
			<div class="minimal1ContentItemText">
				<h1><a href="[%=url%]" target="[%=target%]">[%=title%]</a></h1>
				<h2>[%=dateTime%]</h2>
				<p>[%=subType%] [%=description%]</p>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<div id="minimal2${id}" style="display: none;">
	<div class="contentItemWrapper${id}">	
		[% if(peerReviewed) { %]<div class="peerReviewedIcon_SmallRight"></div>[% } %]
		<div class="minimal2ContentItemBody">
			<div class="minimal2ContentItemText">
				<h1><a href="[%=url%]" target="[%=target%]">[%=title%]</a></h1>
				<h2>[%=dateTime%]</h2>
				<p>[%=subType%] [%=description%]</p>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<div id="titleLink${id}" style="display: none;">
	<div class="contentItemWrapper${id}">	
		[% if(peerReviewed) { %]<div class="peerReviewedIcon_SmallRight"></div>[% } %]
		<div class="titleLinkContentItemBody">
			<div class="titleLinkContentItemText">
				<h1><a href="[%=url%]" target="[%=target%]">[%=title%]</a></h1>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<div id="titleDateLink${id}" style="display: none;">
	<div class="contentItemWrapper${id}">	
		[% if(peerReviewed) { %]<div class="peerReviewedIcon_SmallRight"></div>[% } %]
		<div class="titleDateLinkContentItemBody">
			<div class="titleDateLinkContentItemText">
				<h1><a href="[%=url%]" target="[%=target%]">[%=title%]</a></h1>
				<h2>[%=dateTime%]</h2>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<div id="descriptionText${id}" style="display: none;">
	<div class="contentItemWrapper${id}">	
		[% if(peerReviewed) { %]<div class="peerReviewedIcon_SmallRight"></div>[% } %]
		<div class="descTextContentItemBody">
			<div class="descTextContentItemText">
				<p>[%=description%]</p>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<div id="descriptionLink${id}" style="display: none;">
	<div class="contentItemWrapper${id}">	
		[% if(peerReviewed) { %]<div class="peerReviewedIcon_SmallRight"></div>[% } %]
		<div class="descLinkContentItemBody">
			<div class="descLinkContentItemText">
				<a href="[%=url%]" target="[%=target%]">[%=description%]</a>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<div id="custom${id}" style="display: none;">
	<style type="text/css">
		${cssBlock}	
	</style>
	<div class="contentItemWrapper${id}">	
		${htmlBlock}
	</div>
</div>

<div id="emptyResults${id}" style="display: none;">
	<div class="emptyResultsItemWrapper">
		<div class="emptyResultsItemBody">
			<div class="emptyResultsItemText">
				<h1>There are no entries that match your criteria.</h1>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<%-- ********************** --%>
<%--  Custom Links can be   --%>
<%--  external links.       --%>
<%-- ********************** --%>
<form name="externalLinkForm${id}" id="externalLinkForm${id}" target="_blank" action="" method="POST"></form>

<%-- ********************************* --%>
<%--   Container Holding All - END     --%>
<%-- ********************************* --%>
</div>


<c:if test="${fallbackJS}">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/ieeecs-ContentList-portlet/js/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript" src="/ieeecs-ContentList-portlet/js/jquery.scrollTo-min.js"></script>
<script type="text/javascript" src="/ieeecs-ContentList-portlet/js/underscore.js"></script>
<script type="text/javascript" src="/ieeecs-ContentList-portlet/js/backbone.js"></script>
<script type="text/javascript" src="/ieeecs-ContentList-portlet/js/ejs_production.js"></script>
<script type="text/javascript" src="/ieeecs-ContentList-portlet/js/jquery.mousewheel.js"></script> 	
</c:if>

<script type="text/javascript" src="/ieeecs-ContentList-portlet/js/jquery.touchwipe.min.js"></script>
<script type="text/javascript" src="/ieeecs-ContentList-portlet/js/jquery.jfontsize-1.0.pack.js"></script>

<script language="Javascript">

	<%-- ***************************************************** 
		Let's handle the jQuery AJAX cache first.
	***************************************************** --%>
	$.ajaxSetup({ cache: false });

	<%-- ***************************************************** 
		Now set some common item objects
	***************************************************** --%>	
	var ListItemModel = Backbone.Model.extend({
	    defaults: {
	    	"imagePath":"",
	    	"url":"",
	    	"id":"",
	    	"title":"",
	    	"urlTitle":"",
	    	"description":"",
	    	"dateTime":"",
	    	"dateTimeMS":0,
	    	"type":"",
	    	"subType":"",
	    	"groupId":0,
	    	"target":"_self",
	    	"channel":"",
	    	"peerReviewed":false
	    }
	}); 
	
	var ListItemCollection = Backbone.Collection.extend({
	    model: ListItemModel
	}); 

	<%-- ***************************************************** 
		Initial Values/Variables 
		CUSTOMIZABLE (change to whatever you want) 
		Note:  1. filteredTitle - $ is used in the "replace" of the "performFilter()" function
		       2. scrollDuration - milliseconds, set to "0" for instant page change
	***************************************************** --%>
	var initialTitle${id} = "${titleOfList}";
	var filteredTitle${id} = " <span class='filtered${id}'>(Displaying $ entries)</span>"; 
	var itemsPerPage${id} = ${perPage};
	var displayThisManyItems${id} = itemsPerPage${id} * 3;
	var hideTitle${id} = "${titleVisible}";
	var hideFilter${id} = "${filtering}";
	var hideNavButtons${id} = "${pagination}";
	var scrollDuration${id} = ${scrollDuration};
	var pageMode${id} = "${pageMode}";
	var pageModeTarget${id} = "${pageModeTarget}";
	var filterIsOn${id} = false;
	
	<%-- *****************************************************
		Initial Values/Variables
		STATIC	
	***************************************************** --%>	
	var contentCollection${id} = new ListItemCollection();		
	var filterCollection${id} = new ListItemCollection();
	var scrollMovementValue${id} = $("#contentListContainer${id}").height();
	var lastItemDisplayed${id} = 0;	

	contentCollection${id}.comparator = function(contentItem) {
		var ms = contentItem.get("dateTimeMS");
		return -ms;		
	};	
	
	<%-- *****************************************************
		Initialize the Title Text and get the Content
	***************************************************** --%>
	populateTitle${id}(initialTitle${id});
	getInitialContent${id}();
	getRemainingContent${id}();
	
	<%-- *****************************************************
		Hide/Show the Title
		The Title is hidden by default
	***************************************************** --%>
	if ( hideTitle${id}.toUpperCase() == "NO" ) {
		$("#titleText${id}").show();
	} else {
		$("#titleText${id}").hide();
	}
	
	<%-- *****************************************************
		Hide/Show the Filter Input/Reset
		The Filter Input/Reset are hidden by default
	***************************************************** --%>
	if ( hideFilter${id}.toUpperCase() == "NO" ) {
		$("#filterReset${id}").show();
		$("#filterInputContainer${id}").show();
	} else {
		$("#filterReset${id}").hide();
		$("#filterInputContainer${id}").hide();		
	}

	<%-- *****************************************************
		If Title and Filter are to be hidden, hide the 
		entire Bar
	***************************************************** --%>
	if ( hideTitle${id}.toUpperCase() == "YES" && hideFilter${id}.toUpperCase() == "YES" ) {
		$("#contentListHeaderBar${id}").hide();
	} else {
		$("#contentListHeaderBar${id}").show();	
	}	
	
	<%-- *****************************************************
		Hide/Show the Bottom Navigation Buttons
		The Bottom Navigation Buttons are hidden by default
	***************************************************** --%>
	if ( hideNavButtons${id}.toUpperCase() == "NO" ) {
		$("#navHolder${id}").show();
	} else {
		$("#navHolder${id}").hide();
	}
	
	<%-- *****************************************************
		Populate the Main Scrollable DIV with the collection items
	***************************************************** --%>
	function populateContentListContainer${id}(collectionObject, clearList, start, end) {

		var forLimit = 0;
		if ( collectionObject.size() >= end ) {
			forLimit = end;
		} else {
			forLimit = collectionObject.size();
		}

		if ( clearList ) {			
			$("#scrollableListContent${id}").html("");
		}
		
		for ( var collectionIndex = start; collectionIndex < forLimit; collectionIndex++ ) {
			var listItemModel = collectionObject.models[collectionIndex];
			addItemToContentListContainer${id}(listItemModel);
		}
		
		if ( clearList ) {			
			$("#contentListContainer${id}").scrollTo("0px", scrollDuration${id});
		}		
		
		lastItemDisplayed${id} = end;
	}
	
	function addItemToContentListContainer${id}(listItemModel) {

		var type = listItemModel.attributes.type;					
		var html = new EJS({element: '${uiLayout}${id}'}).render(listItemModel.attributes);
		var htmlObj = $(html);
		$("#scrollableListContent${id}").append(htmlObj);
		
		<%-- *****************************************************
			CONTENT CONTAINER PORTLET				
			If a target Content Container Portlet is set, then
			the 
		***************************************************** --%>			
		if ( pageMode${id} == "current" && pageModeTarget${id} != "" && pageModeTarget${id} != undefined ) {
			
			var targetObj = $("#CONTENT_" + pageModeTarget${id});
	
			if( typeof targetObj != "undefined" ) {

				<%-- The width and height of the overlay DIV needs to be the same as the content it'll cover --%>
				var containerWidth = htmlObj.width();
				var containerHeight = htmlObj.height();
				var overlayDIV = $('<div class="overlayScreen${id}"></div>');
				overlayDIV.data("fullURL", listItemModel.attributes.url);
				overlayDIV.data("target", listItemModel.attributes.target);
				overlayDIV.css({"width":"100%", "height":"100%"});
		
				<%-- When clicking on the overlay DIV, the AJAX call should be made. --%>
				overlayDIV.click(function() {

					var fullURL = $(this).data("fullURL");
					var target = $(this).data("target");

					$(".overlayScreen${id}").css({"opacity":"0", "filter":"alpha(opacity = 0)"});
					$(this).css({"opacity":"0.3", "filter":"alpha(opacity = 30)"});	
					
					if ( type == "supplement" || target == "_blank" ) {
						
						$("#externalLinkForm${id}").attr("action", fullURL);
						$("#externalLinkForm${id}").submit();
		
					} else {
						
						<%-- The "#contentAndControlsContainer" object is within the target "Content Container" portlet that this "Content List" portlet points to. --%>
						<%-- We'll hide it, change its contents, and then show it again. --%>
						$("#contentAndControlsContainer").fadeOut(300, function() {
							
							var encodedTitle = escape(listItemModel.attributes.urlTitle);
							var url = "${restAPI}" + listItemModel.attributes.type + "/" + listItemModel.attributes.groupId + "/" + encodedTitle;
						
							$.ajax({
								type: 'POST',
								url: url,
								success: function(data) {
								
									<%-- After successful data retrieval, fade out the old and fade in the new. --%>
									var jsonData = eval('(' + data + ')');
									var status = jsonData.status;
									var body = jsonData.body;
									var title = jsonData.title;
									var displayDate = jsonData.displayDate;
									var creator = jsonData.creator;
									var urlTitle = jsonData.urlTitle;
									var comments = jsonData.commentArray;	
									var pageTitle = jsonData.pageTitle;
									var articleSource = jsonData.articleSource;
									var headerImagePath = jsonData.headerImagePath;
									var commentParentId = jsonData.commentParentId;
									var commentParentType = jsonData.commentParentType;
									var peerReviewed = jsonData.peerReviewed;
															
									<%-- *****************************************************
										Scrolling Container
									***************************************************** --%>	
									$("#CONTENT_" + pageModeTarget${id}).alternateScroll("remove");										
		
									if ( articleSource == "CSDL" ) {
										
										var headerImageObj = $('<img alt="Header Image" src="' + headerImagePath + '"/>');
										headerImageObj.attr("id","contentContainerheaderImage");
										$("#CONTENT_" + pageModeTarget${id}).html(body);	
							
										if ( peerReviewed ) {					
											var peerReviewedObj = $('<div class="peerReviewedIcon_CSDL_LargeRight"></div>');
											peerReviewedObj.css("display","block");
											$("#CONTENT_" + pageModeTarget${id}).prepend(peerReviewedObj);												
											if ($(".peerReviewedIcon_LargeRight").length > 0) {
												$(".peerReviewedIcon_LargeRight").hide();
											}											
										} else {
											if ($(".peerReviewedIcon_LargeRight").length > 0) {
												$(".peerReviewedIcon_LargeRight").hide();
											}
											if ($(".peerReviewedIcon_CSDL_LargeRight").length > 0) {
												$(".peerReviewedIcon_CSDL_LargeRight").hide();
											}																			
										}
										
										$("#CONTENT_" + pageModeTarget${id}).prepend(headerImageObj);
										$("#CONTENT_TITLE_" + pageModeTarget${id}).hide();
										$("#CONTENT_CREATORDISPLAYDATE_" + pageModeTarget${id}).hide();	
										$(".html-pdf").hide();
										$(".html-adobe").hide();
										$("td:contains('Download Content')").parent().hide();	
										$("#fontChanger").hide();
										
									} else {										
										$("#CONTENT_" + pageModeTarget${id}).html(body);
										$("#CONTENT_TITLE_" + pageModeTarget${id}).html(title);	
										$("#CONTENT_TITLE_" + pageModeTarget${id}).show();
										$("#CONTENT_CREATORDISPLAYDATE_" + pageModeTarget${id}).html(creator + " " + displayDate);	
										$("#CONTENT_CREATORDISPLAYDATE_" + pageModeTarget${id}).show();
										$("#fontChanger").show();	
										
										if ( peerReviewed ) {
											$(".peerReviewedIcon_LargeRight").show();
										} else {
											$(".peerReviewedIcon_LargeRight").hide();
										}
										if ($(".peerReviewedIcon_CSDL_LargeRight").length > 0) {
											$(".peerReviewedIcon_CSDL_LargeRight").hide();
										}
									}
									
									$("#contentAndControlsContainer").fadeIn(300);
							
									<%-- *****************************************************
										Scrolling Container
									***************************************************** --%>	
									$("#CONTENT_" + pageModeTarget${id}).alternateScroll();	
									
									<%-- *****************************************************
										Peer Reviewed, after the Header Image has been rendered
									***************************************************** --%>									
									if ( peerReviewed ) {
										var peerReviewedObjTop = $("#contentContainerheaderImage").height() + 20;
										$(".peerReviewedIcon_CSDL_LargeRight").css("top", peerReviewedObjTop+"px");
									}	
									
									<%-- ***************************************************** 
										Initialize Values/Variables 
									***************************************************** --%>	
									intialDimensions();
									document.title = pageTitle;
									$("#shareWithLinkList").attr("data-url", fullURL);
									$(".numberedPage").remove();
									
									<%-- *****************************************************
									    Font Size Changer         
									***************************************************** --%>		
									if ( articleSource != "CSDL" ) {
										$(".alt-scroll-content, .alt-scroll-content p").jfontsize({
										    btnMinusMaxHits: 2,
										    btnPlusMaxHits: 5,
										    sizeChange: 2		
										});
										
										$(".jfontsize-button").click(function() {
											intialDimensions();
											$(".numberedPage").remove();
											appendNumberedButtons();
										});	
									}	
									
									<%-- *****************************************************
										For each "page", place a Numbered Button for 
										Navigation purposes.
									***************************************************** --%>	
									if ( totalInnerContentHeight >= divHeight ) {
										appendNumberedButtons();
										enableViewAs = true;
									} else {
										$("#viewAs").attr("src", "/ieeecs-ContentContainer-portlet/images/viewAsOnlyOne.png");
										enableViewAs = false;
										var newContentHeight = $(".alt-scroll-content").height();
										$("#CONTENT_${targetName}").height(newContentHeight + 100);
									}									
									
									<%-- *****************************************************
										Hide/Show the Bottom Navigation Buttons
										The Bottom Navigation Buttons are hidden by default
									***************************************************** --%>
									if ( hideNavButtons.toUpperCase() == "NO" && totalNumberOfPages > 1 ) {
										$("#contentNavHolder").show();
									} else {
										$("#contentNavHolder").hide();
									}
									hideShowButtons();		
									
									<%-- *****************************************************
										Comments
									***************************************************** --%>
									populateComments(commentParentId, commentParentType);									
									
									<%-- *****************************************************
										Update Title and URL
									***************************************************** --%>
									document.title = title;									
									window.history.pushState({"html":body,"pageTitle":pageTitle},"", fullURL);
				
								}
							});
						});						
						
					}  <%-- if ( type == "supplement" || target == "_blank" ) { --%>

				});
				
				htmlObj.append(overlayDIV);
			}
		}		
	}
	
	<%-- *****************************************************
		Change the Content List title at will...
	***************************************************** --%>
	function populateTitle${id}(titleText) {
		if ( hideTitle${id} == "NO" ) {
			$("#titleText${id}").html(titleText);
		}		
	}	
	
	function populteFilteredTitle${id}(titleText) {
		if ( titleText == "" ) {
			$("#filterResultsMessage${id}").hide();
			$("#filterResultsMessage${id}").html("");			
		} else {
			$("#filterResultsMessage${id}").html(titleText);
			$("#filterResultsMessage${id}").show();		
		}		
	}
	
	<%-- *****************************************************
		Button Navigation functions
	***************************************************** --%>
	function getCurrentTop${id}() {
		return $("#contentListContainer${id}").scrollTop();
	}	
	
	var lastPosition${id} = -1;
	function scrollMovement${id}(direction, duration, controlType) {

		var currentPosition = getCurrentTop${id}();
		var totalContentHeight = $("#scrollableListContent${id}").height();
		var appendContentOffset = scrollMovementValue${id} * 2;
		var nextElementSetZone = totalContentHeight - appendContentOffset;
		var newPosition = 0;

		if ( direction == "UP" ) {
			
			newPosition = currentPosition - scrollMovementValue${id};

			if ( newPosition <= -scrollMovementValue${id} ) {				
				$("#paginationMessage${id}").html("You've reached the top of the list.");
				$("#paginationMessage${id}").fadeIn(100, function() {
					$("#paginationMessage${id}").fadeOut(1000, function() {
						$("#paginationMessage${id}").html("");						
					});					
				});
			} else {
				
				if ( newPosition < 0 ) {
					newPosition = 0;
				}				
				if ( controlType == "BUTTON" || controlType == "TOUCH" ) {
					$("#contentListContainer${id}").scrollTo(newPosition+"px", duration);					
				}	
			}
	
		} else if ( direction == "DOWN") {			
			
			if ( currentPosition > nextElementSetZone ) {				
				var start = lastItemDisplayed${id};
				var end = lastItemDisplayed${id} + itemsPerPage${id};
			
				if ( filterIsOn${id} ) {
					populateContentListContainer${id}(filterCollection${id}, false, start, end);
				} else {
					populateContentListContainer${id}(contentCollection${id}, false, start, end);
				}
			}
			
			if ( controlType == "BUTTON" || controlType == "TOUCH" ) {								
				newPosition = currentPosition + scrollMovementValue${id};
				$("#contentListContainer${id}").scrollTo(newPosition+"px", duration);
			}			

			lastPosition${id} = currentPosition;	
			
			if ( lastPosition${id} == currentPosition && totalContentHeight - scrollMovementValue${id} == currentPosition ) {
				$("#paginationMessage${id}").html("You've reached the bottom of the list.");
				$("#paginationMessage${id}").fadeIn(100, function() {
					$("#paginationMessage${id}").fadeOut(1000, function() {
						$("#paginationMessage${id}").html("");						
					});					
				});
			}
		}			
		
	}
	
	$("#prevPage${id}").click(function() {
		scrollMovement${id}("UP", scrollDuration${id}, "BUTTON");		
	});
	
	$("#nextPage${id}").click(function() {
		scrollMovement${id}("DOWN", scrollDuration${id}, "BUTTON");
	});	
	
	
	<%-- *****************************************************
		Filter functions
	***************************************************** --%>
	$("#filterValue${id}").keyup(function(e) {	
		var code = (e.keyCode ? e.keyCode : e.which);			
		if ( (code >= 48 && code <= 57 ) || 
		     (code >= 65 && code <= 90 ) || 
		     code == 32 || code == 188 || code == 190 || code == 191 || code == 219 || code == 221 || code == 222 ) {
			performFilter${id}();
		}
	});
	
	$("#filterValue${id}").click(function() {	
		filterIsOn${id} = false;
		$(this).val("");
		populateContentListContainer${id}(contentCollection${id}, true, 0, displayThisManyItems${id});
		populteFilteredTitle${id}("");	
	});	
	
	$("#filterReset${id}").click(function() {
		$("#filterValue${id}").click();
		$("#filterValue${id}").val("${searchInputText}");
		$("#scrollableListContent${id}").animate({top:"0px"}, 500, function() { });
	});	
	
	function performFilter${id}() {

		<%-- Get the filter word(s) --%>
		var filterValue = $("#filterValue${id}").val().toUpperCase();
		
		if ( filterValue == "" ) {
			$("#filterReset${id}").click();
		}
		
		if ( filterValue.length >= 3 ) {
	
			<%-- Reinit the collection --%>
			filterCollection${id} = new ListItemCollection();	
		
			<%-- Perform the filtering using the Backbone "filter" functionality 
				 In this case I'm searching for the word(s) in the "title" and "description" --%>
			var filterArray = contentCollection${id}.filter(function(event) {
				var currentTitle = event.get("title").toUpperCase();
				var currentDescription = event.get("description").toUpperCase();
				var currentSubType = event.get("subType").toUpperCase();
				var currentChannel = event.get("channel").toUpperCase();
				var currentPeerReviewed = event.get("peerReviewed");
				return currentTitle.indexOf(filterValue) > -1 || 
				       currentDescription.indexOf(filterValue) > -1 || 
				       currentSubType.indexOf(filterValue) > -1 || 
				       currentChannel.indexOf(filterValue) > -1 ||  
				       (filterValue.toUpperCase().substring(0,4) == "PEER" && currentPeerReviewed);
			});	
			
			var filterSize = filterArray.length;
	
			if ( filterSize > 0 ) {	

				filterIsOn${id} = true;

				<%-- From Array to List, so we can use the Backbone/Underscore "each" later on. --%>
				for (var i in filterArray) {		
					filterCollection${id}.add(filterArray[i]);
				}
				
				<%-- Repopulate the Content List and set the Title --%>
				populateContentListContainer${id}(filterCollection${id}, true, 0, displayThisManyItems${id});
				populteFilteredTitle${id}(filteredTitle${id}.replace("$",filterSize));
	
			} else {
				$("#scrollableListContent${id}").html("");
				var html = new EJS({element: 'emptyResults${id}'}).render();
				var htmlObj = $(html);			
				$("#scrollableListContent${id}").append(htmlObj);
				populteFilteredTitle${id}("");		
			}
		}
	}	
	
	<%-- *****************************************************
		Populate the collection with the data and get 
		the scrolling DIV ready to roll.
	***************************************************** --%>	
	function getInitialContent${id}() {
		
		var initialContentList = ${initialContentListJSON};
	
		_.each(initialContentList.list, function (listItem, itemIndex) {				
			addToCollection(listItem);
		});
		
		populateContentListContainer${id}(contentCollection${id}, false, 0, displayThisManyItems${id});
	}
	
	function getRemainingContent${id}() {
		
		var url = "${restAPI}" + "contentList";
		var initialChunk = ${initialChunk};
		var asyncChunk   = ${asyncChunk};
		var totalRecords = ${totalRecords};

		for ( var count = initialChunk; count < totalRecords+1; count = count + asyncChunk ) {
						
			var start = count;
			var end = count + asyncChunk;
			var processData = {};			
			processData.start = start;
			processData.end = end;
			processData.id = "${id}";
		
			$.ajax({
				type: 'POST',
				url: url,
				data: processData,
				success: function(data) {	
					
					var jsonData = eval('(' + data + ')');
					_.each(jsonData.list, function (listItem, itemIndex) {				
						addToCollection(listItem);
					});
				}
			});	
		}	
	}
	
	function addToCollection(listItem) {
    	var item = new ListItemModel();	 
    	item.set({"imagePath": listItem.imagePath});
    	item.set({"url": listItem.url});
    	item.set({"id": listItem.id});
    	item.set({"groupId": listItem.groupId});
    	item.set({"title": listItem.title});
    	item.set({"urlTitle": listItem.urlTitle});
    	item.set({"description": listItem.description});
    	item.set({"dateTime": listItem.dateTime});
    	item.set({"dateTimeMS": listItem.dateTimeMS});
    	item.set({"type": listItem.type});
    	item.set({"subType": listItem.subType});
    	item.set({"target": listItem.target});
    	item.set({"channel": listItem.channel});
    	item.set({"peerReviewed": listItem.peerReviewed});    	
    	contentCollection${id}.add(item);	
	}
			
	jQuery(function(){

		<%-- *****************************************************
			We need to account for the Mobile/Smartphone "drag" event, 
			the web "mousewheel" event, and the DIV "scroll" event.
		***************************************************** --%>	
		$("#contentListContainer${id}").bind("mousewheel", function(event, delta) {		

			if ( delta < 0 ) {
				scrollMovement${id}("DOWN", scrollDuration${id}, "MOUSEWHEEL");
			} else {
				scrollMovement${id}("UP", scrollDuration${id}, "MOUSEWHEEL");
			}
	
			return true;
		});		

		$("#contentListContainer${id}").touchwipe({
		     wipeLeft: function() { 
		    	 scrollMovement${id}("DOWN", scrollDuration${id}, "TOUCH");
		     },
		     wipeRight: function() { 
		    	 scrollMovement${id}("UP", scrollDuration${id}, "TOUCH");
		     },
		     wipeUp: function() { 
		    	 scrollMovement${id}("UP", scrollDuration${id}, "TOUCH");
		     },
		     wipeDown: function() { 
		    	 scrollMovement${id}("DOWN", scrollDuration${id}, "TOUCH");
		     },
		     min_move_x: 20,
		     min_move_y: 20,
		     preventDefaultEvents: true
		});		
		
		
		var lastPositionForDirectionalScroll = 0;
		$("#contentListContainer${id}").scroll(function() {
			
			var currentPosition = getCurrentTop${id}();
			var difference = currentPosition - lastPositionForDirectionalScroll;

			if ( difference > 0 ) {
				scrollMovement${id}("DOWN", scrollDuration${id}, "SCROLLBAR");
			} else {
				scrollMovement${id}("UP", scrollDuration${id}, "SCROLLBAR");
			}
			lastPositionForDirectionalScroll = currentPosition;
		});
		
	});	
	
</script>

</c:if>