<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">
	#contentContainer {
		margin: 0 0 30px 0; 
		padding: 10px 20px 0 20px;
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
	}
	
	.numberedPage {
		float: left;
		width: ${numberedWidth}px;
		height: ${numberedHeight}px;
		cursor: pointer;
		margin: 7px 4px 0px 4px;
		text-align: center;
		padding: ${numberedOffset}px 0 0 0;
		border: 1px solid #${numberedBorderColor};
		background-color: #${numberedBackgroundColor};		
		border-top-left-radius: ${numberedTopLeftRadius}px ${numberedTopLeftRadius}px;
		-moz-border-top-left-radius: ${numberedTopLeftRadius}px ${numberedTopLeftRadius}px;
		border-top-right-radius: ${numberedTopRightRadius}px ${numberedTopRightRadius}px;		
		-moz-border-top-right-radius: ${numberedTopRightRadius}px ${numberedTopRightRadius}px;		
		border-bottom-left-radius: ${numberedBottomLeftRadius}px ${numberedBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${numberedBottomLeftRadius}px ${numberedBottomLeftRadius}px;
		border-bottom-right-radius: ${numberedBottomRightRadius}px ${numberedBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${numberedBottomRightRadius}px ${numberedBottomRightRadius}px;		
	}	
	
	#frstPage, #prevPage, #nextPage, #lastPage {
		float: left;
		width: ${paginationWidth}px;
		height: ${paginationHeight}px;
		cursor: pointer;		
		text-align: center;
		padding: ${paginationOffset}px 0 0 0;
	}
	
	#frstPage {	
		margin: 15px 5px 5px 0;
	}	
	
	#prevPage, #nextPage, #lastPage {
		margin: 15px 5px 5px 5px;
	}
	
	#nextPage, #lastPage {
		border: 1px solid #${paginationBorderColor};
		background-color: #${paginationBackgroundColor};
	}	
	
	#frstPage {
		border-top-left-radius: ${firstTopLeftRadius}px ${firstTopLeftRadius}px;
		-moz-border-top-left-radius: ${firstTopLeftRadius}px ${firstTopLeftRadius}px;
		border-top-right-radius: ${firstTopRightRadius}px ${firstTopRightRadius}px;		
		-moz-border-top-right-radius: ${firstTopRightRadius}px ${firstTopRightRadius}px;		
		border-bottom-left-radius: ${firstBottomLeftRadius}px ${firstBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${firstBottomLeftRadius}px ${firstBottomLeftRadius}px;
		border-bottom-right-radius: ${firstBottomRightRadius}px ${firstBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${firstBottomRightRadius}px ${firstBottomRightRadius}px;
	}	
	
	#prevPage {
		border-top-left-radius: ${previousTopLeftRadius}px ${previousTopLeftRadius}px;
		-moz-border-top-left-radius: ${previousTopLeftRadius}px ${previousTopLeftRadius}px;
		border-top-right-radius: ${previousTopRightRadius}px ${previousTopRightRadius}px;		
		-moz-border-top-right-radius: ${previousTopRightRadius}px ${previousTopRightRadius}px;		
		border-bottom-left-radius: ${previousBottomLeftRadius}px ${previousBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${previousBottomLeftRadius}px ${previousBottomLeftRadius}px;
		border-bottom-right-radius: ${previousBottomRightRadius}px ${previousBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${previousBottomRightRadius}px ${previousBottomRightRadius}px;
	}		
	
	#nextPage {
		border-top-left-radius: ${nextTopLeftRadius}px ${nextTopLeftRadius}px;
		-moz-border-top-left-radius: ${nextTopLeftRadius}px ${nextTopLeftRadius}px;
		border-top-right-radius: ${nextTopRightRadius}px ${nextTopRightRadius}px;		
		-moz-border-top-right-radius: ${nextTopRightRadius}px ${nextTopRightRadius}px;		
		border-bottom-left-radius: ${nextBottomLeftRadius}px ${nextBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${nextBottomLeftRadius}px ${nextBottomLeftRadius}px;
		border-bottom-right-radius: ${nextBottomRightRadius}px ${nextBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${nextBottomRightRadius}px ${nextBottomRightRadius}px;
	}	
	
	#lastPage {
		border-top-left-radius: ${lastTopLeftRadius}px ${lastTopLeftRadius}px;
		-moz-border-top-left-radius: ${lastTopLeftRadius}px ${lastTopLeftRadius}px;
		border-top-right-radius: ${lastTopRightRadius}px ${lastTopRightRadius}px;		
		-moz-border-top-right-radius: ${lastTopRightRadius}px ${lastTopRightRadius}px;		
		border-bottom-left-radius: ${lastBottomLeftRadius}px ${lastBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${lastBottomLeftRadius}px ${lastBottomLeftRadius}px;
		border-bottom-right-radius: ${lastBottomRightRadius}px ${lastBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${lastBottomRightRadius}px ${lastBottomRightRadius}px;
	}	
		
</style>

<%-- ********************************* --%>
<%--       Social Login Library        --%>
<%--    (Needs to be first on page)    --%>
<%-- ********************************* --%>
<script type="text/javascript">
	var currentURL = window.location.href;
	var oneall_js_protocol = (("https:" == document.location.protocol) ? "https" : "http");
	document.write(unescape("%3Cscript src='" + oneall_js_protocol + "://computersociety.api.oneall.com/socialize/library.js' type='text/javascript'%3E%3C/script%3E"));
</script>

<%-- ********************************* --%>
<%--   Container Holding All - START   --%>
<%-- ********************************* --%>
<div id="contentAndControlsContainer">

<%-- ********************************* --%>
<%--     Title, Creator(s), Date       --%>
<%-- ********************************* --%>
<c:if test="${articleSource != 'CSDL' }">
	<div class="contentIntro">
		<div class="contentIntroTitle" id="CONTENT_TITLE_${targetName}">${contentTitle}</div>
		<div class="contentIntroCreatorAndDate" id="CONTENT_CREATORDISPLAYDATE_${targetName}">${creator} ${displayDate}</div>
	</div>
</c:if>
<c:if test="${articleSource == 'CSDL' }">
	<div class="contentIntro">
		<div class="contentIntroTitle" id="CONTENT_TITLE_${targetName}"></div>
		<div class="contentIntroCreatorAndDate" id="CONTENT_CREATORDISPLAYDATE_${targetName}"></div>
	</div>
</c:if>

<%-- ********************************* --%>
<%--        Peer Reviewed Icon         --%>
<%-- ********************************* --%>
<div class="peerReviewedIcon_LargeRight"></div>

<div id="contentContainer">

<%-- ********************************* --%>
<%--   Help the reader by maximizing   --%>
<%--   the readable area to fit the    --%>
<%--   screen.                         --%>
<%-- ********************************* --%>
	<div class="toTheTop">
		<a href="/ieeecs-ContentContainer-portlet/templates/toolTip_ToTheTop.html?width=300" class="jTip" id="toTheTopToolTip" name="Maximize / Minimize Article Area"><img src="/ieeecs-ContentContainer-portlet/images/bullet_wide_arrow_up.png" id="toTheTopArrow"/></a>
	</div>
	
<%-- ********************************* --%>
<%--           Viewing Mode            --%>
<%-- ********************************* --%>
	<div class="viewAs">
		<a href="/ieeecs-ContentContainer-portlet/templates/toolTip_ViewAs.html?width=300" class="jTip" id="viewAsToolTip" name="Click to toggle 'Full' View or 'Page' View."><img src="/ieeecs-ContentContainer-portlet/images/viewAsOne.png" id="viewAs"/></a>
	</div>	

<%-- ********************************* --%>
<%--        Font Size Changer          --%>
<%-- ********************************* --%>
<c:if test="${articleSource != 'CSDL' }">
	<div class="fontChanger" id="fontChanger">		
		<a id="jfontsize-plus" class="jfontsize-button" href="#">A+</a>
		<a id="jfontsize-default" class="jfontsize-button" href="#">A</a>
		<a id="jfontsize-minus" class="jfontsize-button" href="#">A-</a>	
	</div>
</c:if>

<%-- ********************************* --%>
<%--        Scrollable Content         --%>
<%-- ********************************* --%>
<c:if test="${articleSource != 'CSDL' }">
	<div class="contentContainerHolder" id="CONTENT_${targetName}">${theContent}</div>
</c:if>

<c:if test="${articleSource == 'CSDL' }">
	<div class="contentContainerHolder" id="CONTENT_${targetName}">
		<c:if test="${headerImagePath != '' }"><img src="${headerImagePath}" id="contentContainerheaderImage"/><br/></c:if>
		<div class="peerReviewedIcon_CSDL_LargeRight"></div>
		${theContent}
	</div>
</c:if>
	
<%-- ********************************* --%>
<%--       Bottom Navigation Bar       --%>
<%-- ********************************* --%>
	<div class="contentNavHolder" id="contentNavHolder">		
		<div class="frstPage" id="frstPage"><div class="frstText" id="frstText">${frstText}</div></div>
		<div class="prevPage" id="prevPage"><div class="prevText" id="prevText">${prevText}</div></div>		
		<div class="nextPage" id="nextPage"><div class="nextText" id="nextText">${nextText}</div></div>
		<div class="lastPage" id="lastPage"><div class="lastText" id="lastText">${lastText}</div></div>		
		<div class="clearBoth"></div>
		<div class="pagesLabel">Page(s): </div>		
	</div>
	<div class="clearBoth"></div>

</div>	

<%-- ********************************* --%>
<%--         Related Content           --%>
<%-- ********************************* --%>	
<c:if test="${relatedContentHTML != ''}">
	<div class="relatedContent" id="relatedContent">Related Content:</div>	
	<div class="relatedContentHTML">
 		${relatedContentHTML}
	</div>	
</c:if>

<%-- ********************************* --%>
<%--           Multimedia              --%>
<%-- ********************************* --%>	
<c:if test="${multiMediaHTML != ''}">
	<div class="multiMedia" id="multiMedia">Multimedia:</div>	
	<div class="multiMediaHTML">
 		${multiMediaHTML}
	</div>	
</c:if>

<%-- ********************************* --%>
<%-- Comment Template for new/existing --%>
<%-- ********************************* --%>	
<div id="commentTemplate" class="commentTemplate">
	<div class="newComment">
		<div class="newCommentName">[%= name %]</div>
		<div class="newCommentDate">[%= createDate %]</div>
		<div class="clearBoth"></div>
	</div>
	<div class="newCommentBody">[%= comment %]</div>
	<div class="clearBoth"></div>	
</div>
	
<%-- ********************************* --%>
<%--          Social Sharing           --%>
<%-- ********************************* --%>	
<c:if test="${isSocialShareActive}">
	<div class="shareWith" id="shareWith">Share this:</div>
	
	<div class="oas_box oas_box_btns_s" id="shareWithLinkList" data-url="" style="width: 85%;">
		<span class="oas_btn oas_btn_facebook" title="Share with Facebook"></span>
		<span class="oas_btn oas_btn_google_bookmarks" title="Share with Google"></span>
		<span class="oas_btn oas_btn_linkedin" title="Share with LinkedIn"></span>
		<span class="oas_btn oas_btn_twitter" title="Share with Twitter"></span>		
		<span class="oas_btn oas_btn_digg" title="Share with Digg"></span>
		<span class="oas_btn oas_btn_reddit" title="Share with Reddit"></span>
		<span class="oas_btn oas_btn_stumbleupon" title="Share with StumbleUpon"></span>
		<span class="oas_btn oas_btn_tumblr" title="Share with Tumblr"></span>  		
	</div>	
</c:if>
	
<%-- ********************************* --%>
<%-- If the Social Login bar is needed --%>
<%-- ********************************* --%>

<c:if test="${showSocialLoginBar}">

	<div class="commentOn">Please login to enter a comment:</div>
	
	<div id="social_login_container"></div>
	
	<script type="text/javascript">		
		oneall.api.plugins.social_login.build("social_login_container", {
			'providers' :  ['facebook', 'google', 'linkedin', 'openid', 'twitter', 'yahoo'], 
			'grid_size_x': '6',
			'callback_uri': currentURL + '#comments-top'
	 	});		
	</script>

</c:if>

<%-- ************************************* --%>
<%-- If the New Comment section is needed  --%>
<%-- ************************************* --%>

<c:if test="${showNewCommentSection}">

	<div class="commentNewContainer">
		<div id="commentTitle" class="commentTitle">Add New Comment</div>
		<div id="commentName" class="commentName">Name <input name="commentFullName" id="commentFullName" value="${commentFullName}" size="40"/></div>
		<div id="commentBody" class="commentBody">
			<input type="hidden" id="urlTitle" name="urlTitle" value="${urlTitle}"/>		
			<textarea id="newComment" name="newComment"></textarea>
		</div>
		<div id="commentButtons" class="commentButtons">
			<div class="floatLeft"><input type="button" value="Cancel" id="cancelComment" name="cancelComment" style="width: 65px;"/></div>
			<div class="commentMessage" id="commentMessage"></div>
			<div class="floatRight"><input type="button" value="Post" id="postComment" name="postComment" style="width: 65px;"/></div>		
			<div class="clearBoth"></div>
		</div>
	</div>
	
	<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jwysiwyg/jquery.wysiwyg.js"></script>
	<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jwysiwyg/controls/wysiwyg.link.js"></script>
	<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jwysiwyg/controls/wysiwyg.table.js"></script>
	<script language="Javascript">
	
		var defaultCommentMessage = "Type your comment here.";
	
		$('#newComment').wysiwyg({
			initialContent: defaultCommentMessage,
			iFrameClass: "newCommentIFrame",
			controls: {
				insertImage: { visible: false },
				createLink: { visible: false },
				unLink: { visible: false },
				increaseFontSize: { visible: true },
				decreaseFontSize: { visible: true }
			},
		    events: {
		    	click: function(event) {
		    		var thisValue = $("#newComment").wysiwyg("getContent");
					if ( thisValue.trim() == defaultCommentMessage ) {
						$("#newComment").wysiwyg("setContent", "");
					}						
		    	},
		    	blur: function(event) {
		    		var thisValue = $("#newComment").wysiwyg("getContent");
					if ( thisValue.trim() == "<br>" || thisValue.trim() == "" ) {
						$("#newComment").wysiwyg("setContent", defaultCommentMessage);
					}						
		    	},    	
		    	focus: function(event) {
		    		var thisValue = $("#newComment").wysiwyg("getContent");
					if ( thisValue.trim() == defaultCommentMessage ) {
						$("#newComment").wysiwyg("setContent", "");
					}						
		    	}		    	
		    }
		});
		
		$("#cancelComment").click(function() {
			$("#newComment").wysiwyg("setContent", defaultCommentMessage);
			$("#commentMessage").html("");	
		});
		
		$("#postComment").click(function() {
			
			var currentVal = $("#newComment").wysiwyg("getContent");
			currentVal = currentVal.trim();
			$("#commentMessage").html("").show();

			if ( currentVal == "" || currentVal == defaultCommentMessage ) {
				$("#commentMessage").html("Please enter a comment.");
			} else {
				$("#commentMessage").html("Saving your Post...");
				
				var fullName = $("#commentFullName").val();				
				var postData = {"comment":escape(currentVal), "commentParentId":"${commentParentId}", "commentParentType":"${commentParentType}", "fullName":fullName};
				
				$.ajax({
					  type: 'POST',
					  url: "${restAPI}comment/insert",
					  data: postData,
					  success: function(data) {

						  if ( data == "" ) {							  
							  $("#commentMessage").html("Session timed-out. Please copy your comment, refresh this page, and login again.");
						  } else {
							  var jsonData = eval('(' + data + ')');
							  var status = jsonData.status;
							  var comment = jsonData.comment;
							  var message = jsonData.message;
							  var name = jsonData.fullName;
							  var createDate = jsonData.createDate;
							  
							  if ( status == "error" ) {
								  $("#commentMessage").html(message);						  
							  } else if ( status == "success" ) {								  
								  var html = new EJS({element: "commentTemplate"}).render({"name":name, "createDate":createDate, "comment":comment});
								  $("#commentList").append(html);
								  $("#newComment").wysiwyg("setContent", defaultCommentMessage);
								  $("#commentMessage").html("Your comment has been posted. Thank you.").fadeOut(3000);
							  } else {
								  $("#commentMessage").html("Please try saving your comment again...");						 
							  }	  
						  }			  
					  }
				});
			}				
		});	
	
	</script>

</c:if>

<%-- *************************** --%>
<%-- Show the existing comments  --%>
<%-- *************************** --%>	
<a name="comments-top"></a>

<div id="commentList"></div>

<div class="clearBoth"></div>	

<%-- ********************************* --%>
<%--   Container Holding All - END     --%>
<%-- ********************************* --%>
</div>

<c:if test="${fallbackJS}">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jquery.scrollTo-min.js"></script>	
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/underscore.js"></script>
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/backbone.js"></script>
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/ejs_production.js"></script>
</c:if>

<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jtip.js"></script>
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jquery.jfontsize-1.0.pack.js"></script>
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/facescroll.js">
	/***********************************************
	* FaceScroll custom scrollbar (c) Dynamic Drive (www.dynamicdrive.com)
	* This notice MUST stay intact for legal use
	* Visit http://www.dynamicdrive.com/ for this script and 100s more.
	***********************************************/
</script>	

<script type="text/javascript">

	<%-- ***************************************************** 
		Let's handle the jQuery AJAX cache first.
	***************************************************** --%>
	$.ajaxSetup({ cache: false });

	<%-- *****************************************************
		Comments
	***************************************************** --%>
	populateComments("${commentParentId}", "${commentParentType}");

	function populateComments(commentParentId, commentParentType) {

		$("#commentList").html("");
		
		var postData = {"commentParentId":commentParentId, "commentParentType":commentParentType};
		
		$.ajax({
			type: 'POST',
			url: "${restAPI}comment/query",
			data: postData,
			success: function(data) {

				var jsonData = eval('(' + data + ')');
				
				if ( jsonData.length > 0 ) {
					
					var comments = jsonData[0].comments;
					
					for (var key in comments) {					
				    	var comment = new CommentModel();
				    	comment.set({"name": comments[key].fullName});
				    	comment.set({"comment": comments[key].comment});
				    	comment.set({"createDate": comments[key].createDate}); 	
				    	var html = new EJS({element: "commentTemplate"}).render(comment.attributes);
						$("#commentList").append(html);    	
					}					
				}
			}
		});
	}
		
	<%-- ***************************************************** 
		Now set some common item objects
	***************************************************** --%>	
	var ListItemModel = Backbone.Model.extend({
	    defaults: {
	    	"imagePath":"",
	    	"url":"",
	    	"id":"",
	    	"title":"",
	    	"description":"",
	    	"dateTime":"",
	    	"dateTimeMS":0,
	    	"type":""
	    }
	}); 

	var CommentModel = Backbone.Model.extend({
	    defaults: {
	    	"name":"",
	    	"title":"",
	    	"body":"",
	    	"avatarUrl":"",
	    	"dateCreated":"",
	    	"dateCreatedMS":0,
	    	"votes":0   		
	    }
	}); 

	var ListItemCollection = Backbone.Collection.extend({
	    model: ListItemModel
	}); 

	var CommentCollection = Backbone.Collection.extend({
	    model: CommentModel
	}); 	

	<%-- ***************************************************** 
		Initial Values/Variables 
		CUSTOMIZABLE (change to whatever you want) 
		Note:  1. scrollDuration - milliseconds, set to "0" for instant page change
	***************************************************** --%> 
	var hideNavButtons = "${pagination}";
	var scrollDuration = ${scrollDuration};	

	<%-- *****************************************************
		Initial Values/Variables
		DYNAMIC throughout JS and within "intialDimensions()"	
	***************************************************** --%>		
	var currentTop = 0;		
	var currentPageNumber = 0;
	var totalNumberOfPages = 0;
	var totalInnerContentHeight = 0;
	var contentHeightOffset = ${containerHeightOffset};
	var divHeight = 0;	
	var enableViewAs = true;

	<%-- *****************************************************
		DIV Scrollbar position and Page Dimensions
	***************************************************** --%>
	function getCurrentTop() {
		var scrollContentTop = $(".alt-scroll-content").css("top");
		scrollContentTop = scrollContentTop.replace("px","");
		currentTop = Math.floor( Math.abs(scrollContentTop) );
	}	
	
	function getDivHeight() {		
		var finalHeight = 0;		
		var browserWindowHeight = $(window).height();
		finalHeight = browserWindowHeight + contentHeightOffset;		
		return finalHeight;		
	}
	
	function intialDimensions() {				
		currentTop = 0;
		currentPageNumber = 1;
		divHeight = getDivHeight();
		$(".contentContainerHolder").height(divHeight);
		totalInnerContentHeight = $(".alt-scroll-content").height();		
		totalNumberOfPages = Math.ceil(totalInnerContentHeight / divHeight);
		if ( totalNumberOfPages == 1) {
			$("#contentNavHolder").hide();
		} else {
			$("#contentNavHolder").show();
		}
	}
	
	function singlePageDimensions() {		
		currentTop = 0;
		currentPageNumber = 1;
		divHeight = $(".alt-scroll-content").height();
		$(".contentContainerHolder").height(divHeight);
		totalInnerContentHeight = divHeight;		
		totalNumberOfPages = 1;	
		$("#contentNavHolder").hide();
	}	
	
	<%-- *****************************************************
		HIDE/SHOW functions
	***************************************************** --%>
	function hideButton(pageObj, textObj) {	
		var currentDisplay = $(pageObj).css("background-color");
		if ( currentDisplay != "#EEEEEE" ) {
			$(pageObj).css({"border": "1px solid #${paginationBorderColor}"});
			$(pageObj).css({"background-color": "#EEEEEE"});	
			$(textObj).fadeOut(500);		
		}
	}
	
	function showButton(pageObj, textObj) {	
		var currentDisplay = $(pageObj).css("background-color");
		if ( currentDisplay != "#${paginationBackgroundColor}" ) {
			$(pageObj).css({"border": "1px solid #${paginationBorderColor}"});
			$(pageObj).css({"background-color": "#${paginationBackgroundColor}"});
			$(textObj).fadeIn(500);		
		}
	}	
	
	function hideAllButtons() {
		hideButton("#frstPage", "#frstText");
		hideButton("#prevPage", "#prevText");
		hideButton("#nextPage", "#nextText");	
		hideButton("#lastPage", "#lastText");
		hideButton("#numberedPage", "#numberedText");	
	}
	
	function showAllButtons() {
		showButton("#frstPage", "#frstText");
		showButton("#prevPage", "#prevText");
		showButton("#nextPage", "#nextText");	
		showButton("#lastPage", "#lastText");
		showButton("#numberedPage", "#numberedText");
	}	
	
	function resetButtons() {
		hideButton("#frstPage", "#frstText");
		hideButton("#prevPage", "#prevText");			
		showButton("#nextPage", "#nextText");	
		showButton("#lastPage", "#lastText");	
		showButton("#numberedPage", "#numberedText");
	}
	
	function hideShowButtons() {
		if ( currentTop == 0 || currentPageNumber == 1 ) {
			hideButton("#prevPage", "#prevText");
			hideButton("#frstPage", "#frstText");
		} else {
			showButton("#prevPage", "#prevText");
			showButton("#frstPage", "#frstText");
		}	
		if ( currentTop + divHeight >= totalInnerContentHeight || currentPageNumber == totalNumberOfPages ) {
			hideButton("#nextPage", "#nextText");
			hideButton("#lastPage", "#lastText");
		} else {
			showButton("#nextPage", "#nextText");
			showButton("#lastPage", "#lastText");			
		}		
	}
	
	function appendNumberedButtons() {
				
		for ( var page = totalNumberOfPages; page >= 1; page-- ) {
			
			var pageObj = $('<div class="numberedPage" id="numberedPage' + page + '"><div class="numberedText">' + page + '</div></div>');
			pageObj.click(function() {
				var currentPageNumber = $(this).children("div").html();
				highlightNumberedButton(this);
				buttonPush("PAGE_" + currentPageNumber, scrollDuration);
			});
			
			$(".pagesLabel").after(pageObj);
			
			if ( page == 1 ) {
				highlightNumberedButton(pageObj);
			}
		}		
	}
	
	function removeNumberedButtons() {	
		$(".numberedPage").remove();			
	}
	
	function highlightNumberedButton(obj) {
		$(".numberedPage").css({"border": "1px solid #${numberedBorderColor}"});
		$(obj).css({"border": "1px solid #000000"});				
	}
	
	<%-- *****************************************************
		Button Push function
	***************************************************** --%>
	function buttonPush(value, duration) {

		var newPosition = "0px";

		if ( value == "FIRST" ) {
			currentPageNumber = 1;			
		} else if ( value == "PREVIOUS" ) {
			currentPageNumber--;
			currentPageNumber = currentPageNumber == 0 ? 1 : currentPageNumber;
		} else if ( value == "NEXT" ) {
			currentPageNumber++;
			currentPageNumber = currentPageNumber > totalNumberOfPages ? totalNumberOfPages : currentPageNumber;
		} else if ( value == "LAST" ) {
			currentPageNumber = totalNumberOfPages;
		} else if ( value.indexOf("PAGE") > -1 ) {
			currentPageNumber = value.split("_",-1)[1];
		}	
		
		if ( value != "FIRST" ) {
			newPosition = (currentPageNumber-1) * divHeight;
			var pageReadabilityOffset = currentPageNumber * 50;
			newPosition = newPosition - pageReadabilityOffset;
			newPosition = newPosition < 0 ? 0 : newPosition;
			newPosition += "px";
		}

		$(".alt-scroll-content").animate({top:"-"+newPosition}, 500, function() {	
			getCurrentTop();
			hideShowButtons();
			highlightNumberedButton( $("#numberedPage" + currentPageNumber) );	
		});
	}
	
	function mouseWheelAndDragHandler() {
		
		getCurrentTop();
		currentPageNumber = Math.ceil(currentTop / divHeight);
		
		if ( currentPageNumber == 0 ) {
			currentPageNumber = 1;
		} else if ( divHeight + currentTop == totalInnerContentHeight ) {
			currentPageNumber = totalNumberOfPages;
		}

		hideShowButtons();
		highlightNumberedButton( $("#numberedPage" + currentPageNumber) );		
	}

jQuery(function(){

	<%-- *****************************************************
		Scrolling Container
	***************************************************** --%>	
	$(".contentContainerHolder").alternateScroll();

	<%-- *****************************************************
	    Font Size Changer         
	***************************************************** --%>		
<c:if test="${articleSource != 'CSDL' }">
	$(".alt-scroll-content, .alt-scroll-content p").jfontsize({
	    btnMinusMaxHits: 2,
	    btnPlusMaxHits: 7,
	    sizeChange: 2		
	});

	$(".jfontsize-button").click(function() {
		intialDimensions();
		$(".numberedPage").remove();
		appendNumberedButtons();
	});	
</c:if>	

	<%-- *****************************************************
		Peer Reviewed        
	***************************************************** --%>	
<c:if test="${peerReviewed && articleSource != 'CSDL'}">
	$(".peerReviewedIcon_LargeRight").show();
</c:if>
<c:if test="${peerReviewed && articleSource == 'CSDL'}">
	var csdlPeerReviewedTop = $("#contentContainerheaderImage").height() + 20;
	$(".peerReviewedIcon_CSDL_LargeRight").css("top", csdlPeerReviewedTop+"px");
	$(".peerReviewedIcon_CSDL_LargeRight").show();
</c:if>

	<%-- *****************************************************
	    Help the reader by maximizing the readable area to    
	    fit the screen.                        
	***************************************************** --%>	
	$(".toTheTop").click(function() {

		var thePosition = 0;
		var currentPosition = 0;
		var offset = 20;
		var contentContainerPosition = $("#contentAndControlsContainer").offset();

		if ( $.browser.safari || $.browser.chrome ) {
			currentPosition = $("body").scrollTop();
		} else {
			currentPosition = $("html,body").scrollTop();
		}
		
		if ( currentPosition == contentContainerPosition.top - offset ) {			
			$("#toTheTopArrow").attr("src", "/ieeecs-ContentContainer-portlet/images/bullet_wide_arrow_up.png");			
		} else {
			thePosition = contentContainerPosition.top - offset;
			$("#toTheTopArrow").attr("src", "/ieeecs-ContentContainer-portlet/images/bullet_wide_arrow_down.png");			
		}

		$("html, body").animate({"scrollTop": thePosition}, 800);		
	});

	<%-- ***************************************************** 
		Initialize Values/Variables 
	***************************************************** --%>		
	intialDimensions();
	document.title = "${pageTitle}";
	$("#shareWithLinkList").attr("data-url", currentURL);
	
	<c:if test="${articleSource == 'CSDL' }">
		$(".html-pdf").hide();
		$(".html-adobe").hide();
		$("td:contains('Download Content')").parent().hide();
	</c:if>		

	<%-- *****************************************************
		For each "page", place a Numbered Button for 
		Navigation purposes.  If we have content that spans
		only a single page, disable the "ViewAs" functionality
		and shrink the container to fit the content.
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
		Since we need to account for the Mobile/Smartphone "drag" event and 
		the web "mousewheel" event, this will change the current page number 
		value.  Otherwise, when a Next/Previous navigation button is pressed, 
		the scrolling action will not be the expected one, (as the 
		currently visible page will be very different from the "currentPageNumber" 
		value.
	***************************************************** --%>	
	$(".contentContainerHolder").bind("mousewheel", function(event, delta) {		
		mouseWheelAndDragHandler();
		return true;
	});		
	
	$(".contentContainerHolder").bind("drag", function() {
		mouseWheelAndDragHandler();
		return true;
	});
	
	<%-- *****************************************************
		Button Navigation functions
	***************************************************** --%>
	$("#frstPage").click(function() {
		buttonPush('FIRST', scrollDuration);
	});
	
	$("#prevPage").click(function() {			
		buttonPush('PREVIOUS', scrollDuration);		
	});
	
	$("#nextPage").click(function() {
		buttonPush('NEXT', scrollDuration);
	});	
	
	$("#lastPage").click(function() {
		buttonPush('LAST', scrollDuration);
	});	
	
	$(".viewAs, #viewAs").click(function() {
		
		if ( enableViewAs ) {
			
			var source = $("#viewAs").attr("src");

			if ( source.indexOf("viewAsOne") > -1 ) {
				$("#viewAs").attr("src", "/ieeecs-ContentContainer-portlet/images/viewAsMultiple.png");
				$("#frstPage, #prevPage, #nextPage, #lastPage").css({"cursor":"auto"});
				singlePageDimensions();
				hideShowButtons();
			} else {
				$("#viewAs").attr("src", "/ieeecs-ContentContainer-portlet/images/viewAsOne.png");
				$("#frstPage, #prevPage, #nextPage, #lastPage").css({"cursor":"pointer"});
				intialDimensions();
				hideShowButtons();
				buttonPush('FIRST', scrollDuration);
			}
		}
	});	

});
	
</script>

</c:if>