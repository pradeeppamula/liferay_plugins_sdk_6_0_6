<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<portlet:actionURL var="configureAction" portletMode="edit">
	<portlet:param name="action" value="homeConfigure"/>	
</portlet:actionURL>

<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>

<style type="text/css">

	#titleCornerRadii {
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
	}
	
	#portletCornerRadii {
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

</style>

<script type="text/javascript">

	function updateCSSBorderColor(color) {
		$("#titleCornerRadii").css({"border":"1px solid #" + color});
	}
	
	function updateCSSBackgroundColor(color) {
		$("#titleCornerRadii").css({"background-color":"#" + color});				
	}
		
	
	function updatePortletBackgroundColor(color) {
		$("#portletCornerRadii").css({"background-color":"#" + color});
	}
	
	function updatePortletBorderColor(color, border) {
		if ( border == 0 ) {
			$("#portletCornerRadii").css({"border-top-color":"#" + color});
		} else if ( border == 1 ) {
			$("#portletCornerRadii").css({"border-right-color":"#" + color});
		} else if ( border == 2 ) {
			$("#portletCornerRadii").css({"border-bottom-color":"#" + color});
		} else if ( border == 3 ) {
			$("#portletCornerRadii").css({"border-left-color":"#" + color});
		}		
	}

</script>

<form id="configureForm" name="configureForm" method="POST" action="${configureAction}">
	<input type="hidden" name="communityCalendarData" id="communityCalendarData" value=""/>
	<input type="hidden" name="source" id="source" value="CONFIG"/>	
	<input type="hidden" id="showIntro" name="showIntro" value="${showIntro}"/>

	<div class="communityCalendarTitle"><u>Configuration for this Content Calendar Portlet</u>:</div>
	
	<div class="communityCalendarInstanceID">Portlet Instance ID: ${id}</div>
	
	<div class="communityCalendarMode">
		This portlet is currently :
		<select id="portletMode" name="portletMode">
			<option value="ACTIVATED" <c:if test="${portletMode == 'ACTIVATED'}"> selected="selected" </c:if>>Active (Visible on Site)</option>
			<option value="DEACTIVATED" <c:if test="${portletMode == 'DEACTIVATED'}"> selected="selected" </c:if>>Not Active (Invisible on Site)</option>
		</select>	
	</div>

	<div class="topNavOptions">
		<div class="topNavOption">
			<select id="configurationOptions" name="configurationOptions" class="configurationOptions">
				<option value="introSection">Intro</option>
				<option value="channelSection">Channel Configuration</option>
				<option value="filterSection">Filter/Search</option>
				<option value="calendarSection">Calendar Aesthetics</option>
				<option value="optionsSection">Calendar Configuration</option>
				<option value="portletSection">Portlet Configuration</option>
				<option value="titleSection">Title Configuration</option>
				<option value="dataFeedSection">Data Feed</option>
			</select>
		</div>
		<div class="topNavBackButton" id="topNavBackButton">Back</div>
		<div class="clearBoth"></div>
	</div>

	<div class="configCalendarExplanationRow introSection">
		<div class="communityCalendarConfigExplanation">
			<ul>
				<li>This portlet will display a Calendar of events.</li>
				<li>These events either come from the Liferay <b>"Web Content"</b> section, or from an external data feed.</li>
				<li>The configurable sections are:
					<ul>
						<li>Channels : What channel/category will the Liferay "Web Content" event come from?</li>
						<li>Filter/Search : Allow filtering/searching of the events within the current calendar</li>
						<li>Calendar Aesthetics : time intervals, 'today' highlight color, 'event' CSS, etc.</li>
						<li>Calendar Configuration : Options such as # of records to pull from the database, Channel names, etc.</li>
						<li>Portlet Configuration : Portlet border and background settings.</li>
						<li>Title Configuration : Title value, border, and background settings.</li>
						<li>Data Feed : External data feed URL</li>
					</ul>
				</li>			
			</ul>
		</div>
		<div class="showIntroExplanation"><input type="checkbox" name="showIntroCheckBox" id="showIntroCheckBox" <c:if test="${showIntro == 'ON' || showIntro == 'on'}">checked="checked"</c:if>/> <label for="showIntroCheckBox">Show this 'Intro' tab first when in 'Preferences' mode.</label></div>
	</div>

	<div class="communityCalendarConfigRow channelSection">
		<div class="communityCalendarConfigCellTitle">
			Show content from...
		</div>
		<div class="communityCalendarConfigCell">
			<select name="channel" id="channel">
				<option value="" selected="selected">Not Assigned to a Channel</option>
		<c:forEach var="channelItem" items="${channelMap}" varStatus="chIndex">			
				<option value="${channelItem.value.categoryId}">${channelItem.value.name}</option>
		</c:forEach>			
			</select>			
			<div class="communityCalendarMoreLess" id="communityCalendarMoreLess">[More]</div>			
		</div>
		<div class="clearBoth"></div>
	</div>		
	<div class="communityCalendarConfigRow moreLess">
		<div class="communityCalendarConfigCellTitle">
			...of the Type(s):
		</div>
		<div class="communityCalendarConfigTypesCell">
			<div class="communityCalendarConfigTypesSelection">
			<c:forEach var="vocabularyItem" items="${vocabularyMap}" varStatus="vIndex">		
				<div class="communityCalendarConfigTypes">
					<div class="communityCalendarConfigVocabularyName">${vocabularyItem.key}</div>		
				<c:forEach var="vocabularyCategoryItem" items="${vocabularyItem.value}" varStatus="vIndex">
					<div class="communityCalendarChannelSelectionBox">
						<input type="checkbox" id="categoryBox_${vocabularyCategoryItem.value.categoryId}" name="categoryBox_${vocabularyCategoryItem.value.categoryId}" value="${vocabularyCategoryItem.value.categoryId}"/>
					</div>
					<div class="communityCalendarChannelSelectionLabel">
						<label for="categoryBox_${vocabularyCategoryItem.value.categoryId}">${vocabularyCategoryItem.key}</label>
					</div>
					<div class="clearBoth"></div>		
				</c:forEach>
				</div>		
			</c:forEach>
			</div>
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="communityCalendarConfigRow channelSection">
		<div class="communityCalendarConfigCellTitle">Event Background Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_EventBackgroundColor.html?width=300" class="jTip" id="eventBackgroundColorToolTip" name="What is the Event Background Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color" type="text" id="eventBackgroundColor" name="eventBackgroundColor" value="CCCCCC" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow channelSection">
		<div class="communityCalendarConfigCellTitle">Event Border Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_EventBorderColor.html?width=300" class="jTip" id="eventBorderColorToolTip" name="What is the Event Border Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color" type="text" id="eventBorderColor" name="eventBorderColor" value="CCCCCC" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="communityCalendarConfigRow channelSection">
		<div class="communityCalendarConfigCellTitle">Event Text Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_EventTextColor.html?width=300" class="jTip" id="eventTextColorToolTip" name="What is the Event Text Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color" type="text" id="eventTextColor" name="eventTextColor" value="000000" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow channelSection">
		<div class="communityCalendarConfigCellTitle">
			&nbsp;
		</div>
		<div class="communityCalendarConfigCell">
			<div class="addButton" id="addButton">Add +</div>			
		</div>
		<div class="clearBoth"></div>
	</div>		
		
	<div class="communityCalendarConfigRow channelSection">
		<div class="communityCalendarSummaryTitle">
			<b>Content to be Displayed in this List</b>:
		</div>
	</div>	
	<div class="communityCalendarConfigRow channelSection">
		<div class="communityCalendarSummaryList" id="communityCalendarSummaryList"></div>
	</div>		

	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Hide Title <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleVisible.html?width=300" class="jTip" id="titleVisibleToolTip" name="Is the Title required?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<select id="titleVisible" name="titleVisible">
				<option value="NO" <c:if test="${titleVisible == 'NO'}"> selected="selected" </c:if>>No</option>
				<option value="YES" <c:if test="${titleVisible == 'YES'}"> selected="selected" </c:if>>Yes</option>
			</select>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Title <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleText.html?width=300" class="jTip" id="titleTextToolTip" name="What is the Title of this Content List?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="titleOfCalendar" name="titleOfCalendar" value="${titleOfCalendar}" size="40"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Title - Top Margin <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleTopMargin.html?width=300" class="jTip" id="titleTopMarginToolTip" name="What is the Title Top Margin?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="titleTopMargin" name="titleTopMargin" value="${titleTopMargin}" size="5"/> px
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Title - Bottom Margin <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleBottomMargin.html?width=300" class="jTip" id="titleBottomMarginToolTip" name="What is the Title Bottom Margin?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="titleBottomMargin" name="titleBottomMargin" value="${titleBottomMargin}" size="5"/> px
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Title Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleColor.html?width=300" class="jTip" id="titleColorToolTip" name="What is the Title Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color" type="text" id="titleColor" name="titleColor" value="${titleColor}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Title Font <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleFont.html?width=300" class="jTip" id="titleFontToolTip" name="What is the Title Font?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="titleFont" name="titleFont" value="${titleFont}" size="35"/> ;
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Title - Border Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleBorderColor.html?width=300" class="jTip" id="titleBorderColorToolTip" name="What is the Title Border Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBorderColor(this);'}" type="text" id="titleBorderColor" name="titleBorderColor" value="${titleBorderColor}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Title - Background Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleBackgroundColor.html?width=300" class="jTip" id="titleBackgroundColorToolTip" name="What is the Title Background Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBackgroundColor(this);'}" type="text" id="titleBackgroundColor" name="titleBackgroundColor" value="${titleBackgroundColor}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="communityCalendarConfigRow titleSection">
		<div class="communityCalendarConfigCellTitle">Title - Corner Radii (px) <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_TitleCornerRadius.html?width=300" class="jTip" id="titleCornerRadiusToolTip" name="What are the Corner Radii (px) for the Title Header?">(?)</a></div>
		<div class="cornerRadiusCell" id="titleCornerRadii">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="titleTopLeftRadius" name="titleTopLeftRadius" value="${titleTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="titleTopRightRadius" name="titleTopRightRadius" value="${titleTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="titleBottomLeftRadius" name="titleBottomLeftRadius" value="${titleBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="titleBottomRightRadius" name="titleBottomRightRadius" value="${titleBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>				

	<div class="communityCalendarConfigRow optionsSection">
		<div class="communityCalendarConfigCellTitle"># DB Records to Pull <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_RecordsToPull.html?width=300" class="jTip" id="recordsToPullToolTip" name="How many records should I pull from the DB?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="recordsToPull" name="recordsToPull" value="${recordsToPull}" size="5"/> records
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow optionsSection">
		<div class="communityCalendarConfigCellTitle">Event Popup Window Offset <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_EventPopupWindowOffset.html?width=300" class="jTip" id="eventPopupWindowOffsetToolTip" name="How far from the Top Browser border should the Popup window start?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="modalTopOffset" name="modalTopOffset" value="${modalTopOffset}" size="5"/> px
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="communityCalendarConfigRow optionsSection">
		<div class="communityCalendarConfigCellTitle">URL Target Name 
		<a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_URLTargetName1.html?width=450" class="jTip" id="urlTargetNameToolTip1" name="Where are the links of this Content List pointing to?">(?)</a>
		<a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_URLTargetName2.html?width=500" class="jTip" id="urlTargetNameToolTip2" name="Some Examples">(?)</a>
		</div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="urlTargetName" name="urlTargetName" value="${urlTargetName}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="communityCalendarConfigRow optionsSection">
		<div class="communityCalendarConfigCellTitle">Channel Vocabulary Name <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_ChannelVocabularyName.html?width=450" class="jTip" id="channelVocabularyNameToolTip" name="Where should I get my Channel names?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="channelVocabularyName" name="channelVocabularyName" value="${channelVocabularyName}" size="35"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow optionsSection">
		<div class="communityCalendarConfigCellTitle">Content Type Vocabulary Name <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_ContentTypeVocabularyName.html?width=450" class="jTip" id="contentTypeVocabularyNameToolTip" name="Where should I get my Content Type names?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="contentTypeVocabularyName" name="contentTypeVocabularyName" value="${contentTypeVocabularyName}" size="35"/>
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="communityCalendarConfigRow optionsSection">
		<div class="communityCalendarConfigCellTitle">Rest API <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_RestAPI.html?width=450" class="jTip" id="restAPIToolTip" name="Where is the Rest API located?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="restAPI" name="restAPI" value="${restAPI}" size="35"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="communityCalendarConfigRow optionsSection">
		<div class="communityCalendarConfigCellTitle">Public Servlet Mapping</div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="publicServletMapping" name="publicServletMapping" value="${publicServletMapping}" size="35"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="communityCalendarConfigRow portletSection">
		<div class="communityCalendarConfigCellTitle">Portlet Border - Top <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_PortletBorderTop.html?width=300" class="jTip" id="portletBorderTopToolTip" name="What is pixel size and color of the Top Border?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="portletBorderPixelTop" name="portletBorderPixelTop" value="${portletBorderPixelTop}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,0);'}" type="text" id="portletBorderColorTop" name="portletBorderColorTop" value="${portletBorderColorTop}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow portletSection">
		<div class="communityCalendarConfigCellTitle">Portlet Border - Bottom <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_PortletBorderBottom.html?width=300" class="jTip" id="portletBorderBottomToolTip" name="What is pixel size and color of the Bottom Border?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="portletBorderPixelBottom" name="portletBorderPixelBottom" value="${portletBorderPixelBottom}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,2);'}" type="text" id="portletBorderColorBottom" name="portletBorderColorBottom" value="${portletBorderColorBottom}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="communityCalendarConfigRow portletSection">
		<div class="communityCalendarConfigCellTitle">Portlet Border - Left <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_PortletBorderLeft.html?width=300" class="jTip" id="portletBorderLeftToolTip" name="What is pixel size and color of the Left Border?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="portletBorderPixelLeft" name="portletBorderPixelLeft" value="${portletBorderPixelLeft}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,3);'}" type="text" id="portletBorderColorLeft" name="portletBorderColorLeft" value="${portletBorderColorLeft}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="communityCalendarConfigRow portletSection">
		<div class="communityCalendarConfigCellTitle">Portlet Border - Right <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_PortletBorderRight.html?width=300" class="jTip" id="portletBorderRightToolTip" name="What is pixel size and color of the Right Border?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="portletBorderPixelRight" name="portletBorderPixelRight" value="${portletBorderPixelRight}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,1);'}" type="text" id="portletBorderColorRight" name="portletBorderColorRight" value="${portletBorderColorRight}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow portletSection">
		<div class="communityCalendarConfigCellTitle">Portlet - Corner Radii (px) <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_PortletCornerRadius.html?width=300" class="jTip" id="portletCornerRadiusToolTip" name="What are the Corner Radii (px) for the Portlet?">(?)</a></div>
		<div class="cornerRadiusCell" id="portletCornerRadii">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="portletTopLeftRadius" name="portletTopLeftRadius" value="${portletTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="portletTopRightRadius" name="portletTopRightRadius" value="${portletTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="portletBottomLeftRadius" name="portletBottomLeftRadius" value="${portletBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="portletBottomRightRadius" name="portletBottomRightRadius" value="${portletBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow portletSection">
		<div class="communityCalendarConfigCellTitle">Portlet Background Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_PortletBackgroundColor.html?width=300" class="jTip" id="portletBackgroundColorToolTip" name="What is Portlet background color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color {onImmediateChange:'updatePortletBackgroundColor(this);'}" type="text" id="portletBackgroundColor" name="portletBackgroundColor" value="${portletBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
		
	<div class="communityCalendarConfigRow calendarSection">
		<div class="communityCalendarConfigCellTitle">Slot Minutes <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_SlotMinutes.html?width=300" class="jTip" id="slotMinutesToolTip" name="The frequency for displaying time slots, in minutes.">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<select id="slotMinutes" name="slotMinutes">
				<option value="5" <c:if test="${slotMinutes == '5'}"> selected="selected" </c:if>>5 min intervals</option>
				<option value="10" <c:if test="${slotMinutes == '10'}"> selected="selected" </c:if>>10 min intervals</option>
				<option value="15" <c:if test="${slotMinutes == '15'}"> selected="selected" </c:if>>15 min intervals</option>
				<option value="20" <c:if test="${slotMinutes == '20'}"> selected="selected" </c:if>>20 min intervals</option>
				<option value="30" <c:if test="${slotMinutes == '30'}"> selected="selected" </c:if>>30 min intervals</option>
			</select>  (Default is 15)
		</div>
		<div class="clearBoth"></div>
	</div>		
		
	<div class="communityCalendarConfigRow calendarSection">
		<div class="communityCalendarConfigCellTitle">Aspect Ratio <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_AspectRatio.html?width=300" class="jTip" id="aspectRatioToolTip" name="What is the width-to-height ratio?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="aspectRatio" name="aspectRatio" value="${aspectRatio}" size="5"/> (Default is 1.35)
		</div>
		<div class="clearBoth"></div>
	</div>			
		
	<div class="communityCalendarConfigRow calendarSection">
		<div class="communityCalendarConfigCellTitle">"Today" Hightlight Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_HighlightBackgroundColor.html?width=300" class="jTip" id="highlightBackgroundColorToolTip" name="What is the 'Today' highlight color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color" type="text" id="highlightBackgroundColor" name="highlightBackgroundColor" value="${highlightBackgroundColor}" size="8"/> (Default is EEEEEE)
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="communityCalendarConfigRow calendarSection">
		<div class="communityCalendarConfigCellTitle">"Event" CSS <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_EventCSS.html?width=300" class="jTip" id="eventCSSToolTip" name="What is the CSS for the 'Event' element(s)?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<textarea id="eventCSS" name="eventCSS" rows="10" cols="24" class="eventCSSTextArea">${eventCSS}</textarea>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow calendarSection">
		<div class="communityCalendarConfigCellTitle">Pop-Up Back Button Text</div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="backToText" name="backToText" value="${backToText}" size="25"/>
		</div>
		<div class="clearBoth"></div>
	</div>			
		
	<div class="communityCalendarConfigRow filterSection">
		<div class="communityCalendarConfigCellTitle">Hide Filter Controls <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_Filter.html?width=300" class="jTip" id="filteringToolTip" name="Is filter/search required?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<select id="filtering" name="filtering">
				<option value="NO" <c:if test="${filtering == 'NO'}"> selected="selected" </c:if>>No</option>
				<option value="YES" <c:if test="${filtering == 'YES'}"> selected="selected" </c:if>>Yes</option>
			</select>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="communityCalendarConfigRow filterSection">
		<div class="communityCalendarConfigCellTitle">Search Text <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_SearchText.html?width=300" class="jTip" id="titleSearchTextToolTip" name="What does the Search Input Text say?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="searchInputText" name="searchInputText" value="${searchInputText}" size="25"/>
		</div>
		<div class="clearBoth"></div>
	</div>				
		
	<div class="communityCalendarConfigRow dataFeedSection">
		<div class="communityCalendarConfigCellTitle">External Data Feed</div>
		<div class="communityCalendarConfigCell">
			<input type="text" id="dataFeed" name="dataFeed" value="${dataFeed}" size="75"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow dataFeedSection">
		<div class="communityCalendarConfigCellTitle">External Data Feed Type</div>
		<div class="communityCalendarConfigCell">
			<select id="dataFeedType" name="dataFeedType">
				<option value="FILE" <c:if test="${dataFeedType == 'FILE'}"> selected="selected" </c:if>>File on File System</option>
				<option value="URL" <c:if test="${dataFeedType == 'URL'}"> selected="selected" </c:if>>URL</option>
			</select>		
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="communityCalendarConfigRow dataFeedSection">
		<div class="communityCalendarConfigCellTitle">Event Background Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_EventBackgroundColor.html?width=300" class="jTip" id="externalEventBackgroundColorToolTip" name="What is the Event Background Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color" type="text" id="externalEventBackgroundColor" name="externalEventBackgroundColor" value="${externalEventBackgroundColor}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="communityCalendarConfigRow dataFeedSection">
		<div class="communityCalendarConfigCellTitle">Event Border Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_EventBorderColor.html?width=300" class="jTip" id="externalEventBorderColorToolTip" name="What is the Event Border Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color" type="text" id="externalEventBorderColor" name="externalEventBorderColor" value="${externalEventBorderColor}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="communityCalendarConfigRow dataFeedSection">
		<div class="communityCalendarConfigCellTitle">Event Text Color <a href="/ieeecs-CommunityCalendar-portlet/templates/toolTip_EventTextColor.html?width=300" class="jTip" id="externalEventTextColorToolTip" name="What is the Event Text Color?">(?)</a></div>
		<div class="communityCalendarConfigCell">
			#<input class="color" type="text" id="externalEventTextColor" name="externalEventTextColor" value="${externalEventTextColor}" size="8"/>
		</div>
		<div class="clearBoth"></div>
	</div>						
		
	<div class="communityCalendarConfigRowButtons">
		<div class="communityCalendarConfigMessage" id="message"></div>
		<div class="communityCalendarConfigSave"><input type="button" name="saveConfiguration" id="saveConfiguration" value="Save"/></div>
		<div class="clearBoth"></div>
	</div>	
</form>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/jtip.js"></script>
<script type="text/javascript" src="/ieeecs-CommunityCalendar-portlet/js/jscolor/jscolor.js"></script>

<script>
$(document).ready(function() {

	<%-- ***************************************************** --%>
	<%--  This is the data for this list, in JSON form.        --%>
	<%-- ***************************************************** --%>
	var communityCalendarData = ${communityCalendarData};
	
	<%-- ***************************************************** --%>
	<%--  Populate the Display with the settings for this list --%>
	<%-- ***************************************************** --%>
	for ( key in communityCalendarData ) {
		
		var uiId = communityCalendarData[key].uiId;
		var description = communityCalendarData[key].description;
		
		var newDisplayItem = $('<div class="communityCalendarSummaryListItem" id="description_' + uiId + '">* ' + description + '</div>');
		var removeDisplayItem = $('<div class="communityCalendarSummaryListItemRemove" id="remove_' + uiId + '">REMOVE</div>');
		removeDisplayItem.click(function() {
			var thisId = $(this).attr("id");
			var thisUiId = thisId.replace("remove_" , "");
			$("#description_" + thisUiId).remove();
			$("#remove_" + thisUiId).remove();
			
			for ( key in communityCalendarData ) {				
				var uiId = communityCalendarData[key].uiId;
				if ( uiId == thisUiId ) {
					communityCalendarData.splice(key, 1);
				}
			}			
		});
		var clearBoth = $('<div class="clearBoth"></div>');
		$("#communityCalendarSummaryList").append(newDisplayItem);
		$("#communityCalendarSummaryList").append(removeDisplayItem);
		$("#communityCalendarSummaryList").append(clearBoth);			
	}

	$("#addButton").click(function() {
		
		<%-- ************* --%>
		<%-- ADD TO OBJECT --%>
		<%-- ************* --%>			
		var newContentConfig = {};
		var rightNow = new Date();
		var uiId = rightNow.getTime() + randomString(8);
		newContentConfig["uiId"] = uiId;		
		
		<%-- ************* --%>
		<%--   COMMUNITY   --%>
		<%-- ************* --%>
		var communityId = "${communityId}";
		var communityName = "${communityName}";
		newContentConfig["groupId"] = communityId;
		
		<%-- ************* --%>
		<%--     TYPE      --%>
		<%-- ************* --%>		
		var contentTypes = "Articles";
		newContentConfig["contentBit"] = 1;
			
		<%-- ************* --%>
		<%--    CHANNEL    --%>
		<%-- ************* --%>
		newContentConfig["categories"] = [];
		var channelId = $("#channel").val();
		var channelName = $("#channel option:selected").text(); 
		
		if ( channelId != "" ) {
			newContentConfig["categories"].push(channelId);
		}
		
		<%-- ************* --%>
		<%--   CATEGORIES  --%>
		<%-- ************* --%>
		var categories = [];
		var categoryNames = "";
		$("input[id^='categoryBox']:checked").each(function() {			
			var value = $(this).val();
			var thisId = $(this).attr("id");
			categories.push(value);
			newContentConfig["categories"].push(value);
			var name = $("label[for='" + thisId + "']").html();
			categoryNames = categoryNames + name + ", ";
		});
		if ( categoryNames != "" ) {
			categoryNames = categoryNames.substring(0, categoryNames.length - 2);
		}		
		
		<%-- ********************** --%>
		<%-- EVENT BACKGROUND COLOR --%>
		<%-- ********************** --%>		
		var bgColor = $("#eventBackgroundColor").val();
		newContentConfig["bgColor"] = bgColor != "" ? bgColor : "CCCCCC";
		
		<%-- ******************* --%>
		<%-- EVENT BORDER COLOR  --%>
		<%-- ******************* --%>
		var borderColor = $("#eventBorderColor").val();
		newContentConfig["borderColor"] = borderColor != "" ? borderColor : "CCCCCC";		
				
		<%-- **************** --%>
		<%-- EVENT TEXT COLOR --%>
		<%-- **************** --%>			
		var textColor = $("#eventTextColor").val();
		newContentConfig["textColor"] = textColor != "" ? textColor : "000000";
		
		<%-- ************* --%>
		<%--  DESCRIPTION  --%>
		<%-- ************* --%>		
		var description = communityName + " (" + contentTypes + ") ";		
		if ( channelName == "Not Assigned to a Channel" ) {
			description = description + "not assigned to a Channel";
		} else {
			description = description + "in '" + channelName + "'";
		}			
		
		if ( categoryNames != "" ) {
			description = description + ", of Type: " + categoryNames;
		}	
		description = description + "<div>" +
									"Background Color: #<span style='color: #FFFFFF; background-color: #" + bgColor + "; padding: 3px;'>" + bgColor + "</span>, " +
									"Border Color: #<span style='color: #FFFFFF; background-color: #" + borderColor + "; padding: 3px;'>" + borderColor + "</span>, " +
									"Text Color: #<span style='color: #FFFFFF; background-color: #" + textColor + "; padding: 3px;'>" + textColor + "</span>" +
									"</div>";
		newContentConfig["description"] = description;

		<%-- ************* --%>
		<%-- ADD TO ARRAY  --%>
		<%-- ************* --%>	
		communityCalendarData.push(newContentConfig);
				
		<%-- ************* --%>
		<%--    DISPLAY    --%>
		<%-- ************* --%>	
		var newDisplayItem = $('<div class="communityCalendarSummaryListItem" id="description_' + uiId + '">* ' + description + '</div>');		
		var removeDisplayItem = $('<div class="communityCalendarSummaryListItemRemove" id="remove_' + uiId + '">REMOVE</div>');
		removeDisplayItem.click(function() {
			var thisId = $(this).attr("id");
			var thisUiId = thisId.replace("remove_" , "");
			$("#description_" + thisUiId).remove();
			$("#remove_" + thisUiId).remove();
			
			for ( key in communityCalendarData ) {				
				var uiId = communityCalendarData[key].uiId;
				if ( uiId == thisUiId ) {
					communityCalendarData.splice(key, 1);
				}
			}			
		});
		var clearBoth = $('<div class="clearBoth"></div>');
		$("#communityCalendarSummaryList").append(newDisplayItem);
		$("#communityCalendarSummaryList").append(removeDisplayItem);
		$("#communityCalendarSummaryList").append(clearBoth);

		<%-- ************* --%>
		<%--     RESET     --%>
		<%-- ************* --%>					
		$("input[id^='categoryBox']:checked").each(function() {			
			$(this).attr("checked", false);
		});	
		$("#eventBackgroundColor").val("CCCCCC");
		$("#eventBorderColor").val("CCCCCC");
		$("#eventTextColor").val("000000");
		document.getElementById("eventBackgroundColor").color.fromString('CCCCCC')
		document.getElementById("eventBorderColor").color.fromString('CCCCCC')
		document.getElementById("eventTextColor").color.fromString('000000')
		$("#channel").val("");
				
	});
	
	$("#communityCalendarMoreLess").click(function() {

        if ($(".moreLess").css("display") == "none"){
            $(".moreLess").css("display", "block");
            $(this).html("[Less]");
        } else {
            $(".moreLess").css("display", "none");
            $(this).html("[More]");
        }				
	});
	
	$("#saveConfiguration").click(function() {
		
		if ( $("#showIntroCheckBox").is(':checked') ) {
			$("#showIntro").val("ON");
		} else {
			$("#showIntro").val("OFF");
		}		
		
	    $("#communityCalendarData").val( JSON.stringify(communityCalendarData) );
		$("#message").html("Your Configuration changes are being saved...").fadeOut(3000);
		$("#configureForm").submit();
	});
	
	$(".inputKeyup").keyup(function() {	
		
		var pixelValue = $(this).val();
		var thisId = $(this).attr("id");
		var containerId = "#" + $(this).parent().parent().attr("id");
		
		if ( thisId.indexOf("TopLeft") > -1 ) {
			$(containerId).css({"border-top-left-radius":pixelValue + "px " + pixelValue + "px"});
			$(containerId).css({"-moz-border-top-left-radius":pixelValue + "px " + pixelValue + "px"});
		} else if ( thisId.indexOf("BottomLeft") > -1 ) {
			$(containerId).css({"border-bottom-left-radius":pixelValue + "px " + pixelValue + "px"});
			$(containerId).css({"-moz-border-bottom-left-radius":pixelValue + "px " + pixelValue + "px"});
		} else if ( thisId.indexOf("TopRight") > -1 ) {
			$(containerId).css({"border-top-right-radius":pixelValue + "px " + pixelValue + "px"});
			$(containerId).css({"-moz-border-top-right-radius":pixelValue + "px " + pixelValue + "px"});
		} else if ( thisId.indexOf("BottomRight") > -1 ) {
			$(containerId).css({"border-bottom-right-radius":pixelValue + "px " + pixelValue + "px"});
			$(containerId).css({"-moz-border-bottom-right-radius":pixelValue + "px " + pixelValue + "px"});
		}	
	});	
	
	$("#portletBorderPixelTop").keyup(function() {
		var newPixel = $(this).val();
		$("#portletCornerRadii").css({"border-top-width":newPixel+"px"});
	});	
	
	$("#portletBorderPixelRight").keyup(function() {
		var newPixel = $(this).val();
		$("#portletCornerRadii").css({"border-right-width":newPixel+"px"});
	});	
	
	$("#portletBorderPixelBottom").keyup(function() {
		var newPixel = $(this).val();
		$("#portletCornerRadii").css({"border-bottom-width":newPixel+"px"});
	});	
	
	$("#portletBorderPixelLeft").keyup(function() {
		var newPixel = $(this).val();
		$("#portletCornerRadii").css({"border-left-width":newPixel+"px"});
	});	
	
	$("#configurationOptions").change(function() {			
		var thisSelected = $(this).val();	
		$(".introSection, .channelSection, .optionsSection, .portletSection, .titleSection, .calendarSection, .filterSection, .moreLess, .dataFeedSection").hide();	
		$("#communityCalendarMoreLess").html("[More]");		
		$("." + thisSelected).show();
	});	
	
	function randomString(stringLength) {
		var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
		var rString = '';
		for (var i = 0; i < stringLength; i++) {
			var rNum = Math.floor(Math.random() * chars.length);
			rString += chars.substring(rNum,rNum+1);
		}
		return rString;
	}	

	$("#topNavBackButton").click(function() {
		$("#configureForm").attr("action", "${viewAction}");
		$("#configureForm").submit();
	});		
	
	<c:if test="${showIntro == 'ON' || showIntro == 'on'}">
		$(".introSection").show();
	</c:if>
	<c:if test="${showIntro == 'OFF' || showIntro == 'off'}">
		$("#configurationOptions").val("channelSection");
		$(".channelSection").show();
	</c:if>
	
});
</script>
