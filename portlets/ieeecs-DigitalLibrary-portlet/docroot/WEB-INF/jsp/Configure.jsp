<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<portlet:actionURL var="configureAction" portletMode="edit">
	<portlet:param name="action" value="digitalLibraryConfigure"/>	
</portlet:actionURL>

<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>

<style type="text/css">

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

<%------------ Bootstrap overrides for this Portlet ---------- --%>

	input[type="text"] {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    background-image: url("../images/forms/input_shadow.png");
	    background-repeat: no-repeat;
	    border-color: #BFBFBF #DEDEDE #DEDEDE #BFBFBF;
	    border-image: none;
	    border-style: solid;
	    border-width: 1px;
	    font: 1em Arial,Helvetica,Verdana,sans-serif;
	    padding: 5px 1px;
	}

</style>


<script type="text/javascript">

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
	<input type="hidden" id="source" name="source" value="CONFIG"/>

	<div class="digitalLibraryTitle"><u>Configuration for this Digital Library Portlet</u>:</div>
	
	<div class="digitalLibraryInstanceID">Portlet Instance ID: ${id}</div>
	
	<div class="digitalLibraryMode">
		This portlet is currently :
		<select id="portletMode" name="portletMode">
			<option value="ACTIVATED" <c:if test="${portletMode == 'ACTIVATED'}"> selected="selected" </c:if>>Active (Visible on Site)</option>
			<option value="DEACTIVATED" <c:if test="${portletMode == 'DEACTIVATED'}"> selected="selected" </c:if>>Not Active (Invisible on Site)</option>
		</select>	
	</div>	

	<div class="topNavOptions">
		<div class="topNavOption">
			<select id="configurationOptions" name="configurationOptions" class="configurationOptions">
				<option value="portletSection">Portlet Configuration</option>				
				<option value="publicationVariablesSection">Publication Variables</option>
				<option value="textAndLabelsSection">Text and Labels</option>
				<option value="csdlStructureNamesSection">CSDL Structure Names</option>
				<option value="csdlUrlComponentsSection">CSDL URL Components</option>
				<option value="idSection">CSDL IDs</option>
				<option value="buttonTextSection">Button Text</option>
				<option value="miscSettingsSection">Misc Settings</option>
			</select>
		</div>
		<div class="topNavBackButton" id="topNavBackButton">Back</div>
		<div class="clearBoth"></div>
	</div>	
	
	<%--  PORTLET SECTION  --%>		

	<div class="portletConfigRow portletSection">
		<div class="digitalLibraryCellTitle">Portlet Border - Top <a href="/ieeecs-DigitalLibrary-portlet/templates/toolTip_PortletBorderTop.html?width=300" class="jTip" id="portletBorderTopToolTip" name="What is pixel size and color of the Top Border?">(?)</a></div>
		<div class="digitalLibraryCell">
			<input type="text" id="portletBorderPixelTop" name="portletBorderPixelTop" value="${portletBorderPixelTop}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,0);'}" type="text" id="portletBorderColorTop" name="portletBorderColorTop" value="${portletBorderColorTop}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow portletSection">
		<div class="digitalLibraryCellTitle">Portlet Border - Bottom <a href="/ieeecs-DigitalLibrary-portlet/templates/toolTip_PortletBorderBottom.html?width=300" class="jTip" id="portletBorderBottomToolTip" name="What is pixel size and color of the Bottom Border?">(?)</a></div>
		<div class="digitalLibraryCell">
			<input type="text" id="portletBorderPixelBottom" name="portletBorderPixelBottom" value="${portletBorderPixelBottom}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,2);'}" type="text" id="portletBorderColorBottom" name="portletBorderColorBottom" value="${portletBorderColorBottom}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow portletSection">
		<div class="digitalLibraryCellTitle">Portlet Border - Left <a href="/ieeecs-DigitalLibrary-portlet/templates/toolTip_PortletBorderLeft.html?width=300" class="jTip" id="portletBorderLeftToolTip" name="What is pixel size and color of the Left Border?">(?)</a></div>
		<div class="digitalLibraryCell">
			<input type="text" id="portletBorderPixelLeft" name="portletBorderPixelLeft" value="${portletBorderPixelLeft}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,3);'}" type="text" id="portletBorderColorLeft" name="portletBorderColorLeft" value="${portletBorderColorLeft}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow portletSection">
		<div class="digitalLibraryCellTitle">Portlet Border - Right <a href="/ieeecs-DigitalLibrary-portlet/templates/toolTip_PortletBorderRight.html?width=300" class="jTip" id="portletBorderRightToolTip" name="What is pixel size and color of the Right Border?">(?)</a></div>
		<div class="digitalLibraryCell">
			<input type="text" id="portletBorderPixelRight" name="portletBorderPixelRight" value="${portletBorderPixelRight}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,1);'}" type="text" id="portletBorderColorRight" name="portletBorderColorRight" value="${portletBorderColorRight}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow portletSection">
		<div class="digitalLibraryCellTitle">Portlet - Corner Radii (px) <a href="/ieeecs-DigitalLibrary-portlet/templates/toolTip_PortletCornerRadius.html?width=300" class="jTip" id="portletCornerRadiusToolTip" name="What are the Corner Radii (px) for the Portlet?">(?)</a></div>
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
	
	<div class="portletConfigRow portletSection">
		<div class="digitalLibraryCellTitle">Portlet Background Color <a href="/ieeecs-DigitalLibrary-portlet/templates/toolTip_PortletBackgroundColor.html?width=300" class="jTip" id="portletBackgroundColorToolTip" name="What is Portlet background color?">(?)</a></div>
		<div class="digitalLibraryCell">
			#<input class="color {onImmediateChange:'updatePortletBackgroundColor(this);'}" type="text" id="portletBackgroundColor" name="portletBackgroundColor" value="${portletBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<%--  MISC SETTINGS SECTION  --%>	
	
	<div class="portletConfigRow miscSettingsSection">
		<div class="digitalLibraryCellTitle">Inner Content Margins</div>
		<div class="digitalLibraryCell">
			<input type="text" id="innerMargins" name="innerMargins" value="${innerMargins}" size="25"/>;
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow miscSettingsSection">
		<div class="digitalLibraryCellTitle">Rest API</div>
		<div class="digitalLibraryCell">
			<input type="text" id="restAPI" name="restAPI" value="${restAPI}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow miscSettingsSection">
		<div class="digitalLibraryCellTitle">Featured Articles Slide-Down Delay</div>
		<div class="digitalLibraryCell">
			<input type="text" id="homePageFeaturedSliderDelay" name="homePageFeaturedSliderDelay" value="${homePageFeaturedSliderDelay}" size="5"/>ms
		</div>
		<div class="clearBoth"></div>
	</div>		
									
	<%--  PUBLICATION VARIABLES SECTION  --%>	
	
	<div class="portletConfigRow publicationVariablesSection">
		<div class="digitalLibraryCellTitle">Magazines</div>
		<div class="digitalLibraryCell">
			<input type="text" id="mags" name="mags" value="${mags}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>		

	<div class="portletConfigRow publicationVariablesSection">
		<div class="digitalLibraryCellTitle">Transactions</div>
		<div class="digitalLibraryCell">
			<input type="text" id="trans" name="trans" value="${trans}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow publicationVariablesSection">
		<div class="digitalLibraryCellTitle">Letters</div>
		<div class="digitalLibraryCell">
			<input type="text" id="letters" name="letters" value="${letters}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow publicationVariablesSection">
		<div class="digitalLibraryCellTitle">Proceedings</div>
		<div class="digitalLibraryCell">
			<input type="text" id="proceedings" name="proceedings" value="${proceedings}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow publicationVariablesSection">
		<div class="digitalLibraryCellTitle">PrePrints</div>
		<div class="digitalLibraryCell">
			<input type="text" id="prePrints" name="prePrints" value="${prePrints}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow publicationVariablesSection">
		<div class="digitalLibraryCellTitle">RapidPosts</div>
		<div class="digitalLibraryCell">
			<input type="text" id="rapidPosts" name="rapidPosts" value="${rapidPosts}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>											
								
	<%--  TEXT AND LABELS SECTION  --%>
		
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">CSDL Header Title</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlHeaderTitle" name="csdlHeaderTitle" value="${csdlHeaderTitle}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">CSDL Header Introduction</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlHeaderIntroduction" name="csdlHeaderIntroduction" value="${csdlHeaderIntroduction}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">CSDL Header Introduction Image Path</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlHeaderIntroductionImagePath" name="csdlHeaderIntroductionImagePath" value="${csdlHeaderIntroductionImagePath}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>						
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Subscription Sentence</div>
		<div class="digitalLibraryCell">
			<input type="text" id="subscriptionBlurb" name="subscriptionBlurb" value="${subscriptionBlurb}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">CSDL Search Title</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlSearchTitle" name="csdlSearchTitle" value="${csdlSearchTitle}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Magazines Description</div>
		<div class="digitalLibraryCell">
			<input type="text" id="magazinesDescription" name="magazinesDescription" value="${magazinesDescription}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Transactions Description</div>
		<div class="digitalLibraryCell">
			<input type="text" id="transactionsDescription" name="transactionsDescription" value="${transactionsDescription}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Letters Description</div>
		<div class="digitalLibraryCell">
			<input type="text" id="lettersDescription" name="lettersDescription" value="${lettersDescription}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Proceedings Description</div>
		<div class="digitalLibraryCell">
			<input type="text" id="proceedingsDescription" name="proceedingsDescription" value="${proceedingsDescription}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>						
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">PrePrints Description</div>
		<div class="digitalLibraryCell">
			<input type="text" id="prePrintsDescription" name="prePrintsDescription" value="${prePrintsDescription}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">RapidPosts Description</div>
		<div class="digitalLibraryCell">
			<input type="text" id="rapidPostsDescription" name="rapidPostsDescription" value="${rapidPostsDescription}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Magazine Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="magazineLabel" name="magazineLabel" value="${magazineLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Transaction Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="transactionLabel" name="transactionLabel" value="${transactionLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Letter Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="letterLabel" name="letterLabel" value="${letterLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Proceeding Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="proceedingLabel" name="proceedingLabel" value="${proceedingLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>						
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Resource Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="resourceLabel" name="resourceLabel" value="${resourceLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Resources Article ID</div>
		<div class="digitalLibraryCell">
			<input type="text" id="resourceArticleId" name="resourceArticleId" value="${resourceArticleId}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Resource Default Text</div>
		<div class="digitalLibraryCell">
			<input type="text" id="resourceDefaultText" name="resourceDefaultText" value="${resourceDefaultText}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Proceedings URL Segment</div>
		<div class="digitalLibraryCell">
			<input type="text" id="proceedingsUrlSegment" name="proceedingsUrlSegment" value="${proceedingsUrlSegment}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Volume Abbreviation</div>
		<div class="digitalLibraryCell">
			<input type="text" id="volumeAbbrev" name="volumeAbbrev" value="${volumeAbbrev}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Issue Abbreviation</div>
		<div class="digitalLibraryCell">
			<input type="text" id="issueAbbrev" name="issueAbbrev" value="${issueAbbrev}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>						
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Issue Number Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="issueNumberLabel" name="issueNumberLabel" value="${issueNumberLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">PrePrints Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="prePrintsLabel" name="prePrintsLabel" value="${prePrintsLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">RapidPosts Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="rapidPostsLabel" name="rapidPostsLabel" value="${rapidPostsLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>							
			
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">CSDL Home Link Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlHomeLabel" name="csdlHomeLabel" value="${csdlHomeLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>					
				
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Volume and Issue Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="volumeAndIssueLabel" name="volumeAndIssueLabel" value="${volumeAndIssueLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow textAndLabelsSection">
		<div class="digitalLibraryCellTitle">Table of Contents Label</div>
		<div class="digitalLibraryCell">
			<input type="text" id="tableOfContentsLabel" name="tableOfContentsLabel" value="${tableOfContentsLabel}" size="30"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<%--  CSDL STRUCTURE NAMES SECTION  --%>	
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">Publication Type</div>
		<div class="digitalLibraryCell">
			<input type="text" id="pubType" name="pubType" value="${pubType}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">ID Prefix</div>
		<div class="digitalLibraryCell">
			<input type="text" id="idPrefix" name="idPrefix" value="${idPrefix}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">File Name</div>
		<div class="digitalLibraryCell">
			<input type="text" id="fileName" name="fileName" value="${fileName}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">File Detail</div>
		<div class="digitalLibraryCell">
			<input type="text" id="fileDetail" name="fileDetail" value="${fileDetail}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">File Extension</div>
		<div class="digitalLibraryCell">
			<input type="text" id="fileType" name="fileType" value="${fileType}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">Year (Volume)</div>
		<div class="digitalLibraryCell">
			<input type="text" id="year" name="year" value="${year}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">Catalog Number</div>
		<div class="digitalLibraryCell">
			<input type="text" id="catalogNumber" name="catalogNumber" value="${catalogNumber}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">Volume Number</div>
		<div class="digitalLibraryCell">
			<input type="text" id="volumeNumber" name="volumeNumber" value="${volumeNumber}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow csdlStructureNamesSection">
		<div class="digitalLibraryCellTitle">Issue Number</div>
		<div class="digitalLibraryCell">
			<input type="text" id="issueNumber" name="issueNumber" value="${issueNumber}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>									

	<%--  CSDL URL COMPONENTS SECTION  --%>
	
	<div class="portletConfigRow csdlUrlComponentsSection">
		<div class="digitalLibraryCellTitle">CDSL Context Path</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlContextPath" name="csdlContextPath" value="${csdlContextPath}" size="40"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow csdlUrlComponentsSection">
		<div class="digitalLibraryCellTitle">CSDL Index Page</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlIndexPage" name="csdlIndexPage" value="${csdlIndexPage}" size="40"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow csdlUrlComponentsSection">
		<div class="digitalLibraryCellTitle">CSDL List Page</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlListPage" name="csdlListPage" value="${csdlListPage}" size="40"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow csdlUrlComponentsSection">
		<div class="digitalLibraryCellTitle">CSDL Table of Contents Page</div>
		<div class="digitalLibraryCell">
			<input type="text" id="csdlToCPage" name="csdlToCPage" value="${csdlToCPage}" size="40"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow csdlUrlComponentsSection">
		<div class="digitalLibraryCellTitle">PrePrints URL</div>
		<div class="digitalLibraryCell">
			<input type="text" id="prePrintsUrl" name="prePrintsUrl" value="${prePrintsUrl}" size="40"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow csdlUrlComponentsSection">
		<div class="digitalLibraryCellTitle">RapidPosts URL</div>
		<div class="digitalLibraryCell">
			<input type="text" id="rapidPostsUrl" name="rapidPostsUrl" value="${rapidPostsUrl}" size="40"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow csdlUrlComponentsSection">
		<div class="digitalLibraryCellTitle">DOI URL Prefix</div>
		<div class="digitalLibraryCell">
			<input type="text" id="doiUrlPrefix" name="doiUrlPrefix" value="${doiUrlPrefix}" size="40"/>
		</div>
		<div class="clearBoth"></div>
	</div>										
	
	<%--  ID SECTION  --%>
	
	<div class="portletConfigRow idSection">
		<div class="digitalLibraryCellTitle">PrePrints Year</div>
		<div class="digitalLibraryCell">
			<input type="text" id="prePrintsYear" name="prePrintsYear" value="${prePrintsYear}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow idSection">
		<div class="digitalLibraryCellTitle">PrePrints Issue Number</div>
		<div class="digitalLibraryCell">
			<input type="text" id="prePrintsIssueNumber" name="prePrintsIssueNumber" value="${prePrintsIssueNumber}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow idSection">
		<div class="digitalLibraryCellTitle">RapidPosts Year</div>
		<div class="digitalLibraryCell">
			<input type="text" id="rapidPostsYear" name="rapidPostsYear" value="${rapidPostsYear}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow idSection">
		<div class="digitalLibraryCellTitle">RapidPosts Issue Number</div>
		<div class="digitalLibraryCell">
			<input type="text" id="rapidPostsIssueNumber" name="rapidPostsIssueNumber" value="${rapidPostsIssueNumber}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>						
	
	<%--  BUTTON TEXT SECTION  --%>		
					
	<div class="portletConfigRow buttonTextSection">
		<div class="digitalLibraryCellTitle">Abstract</div>
		<div class="digitalLibraryCell">
			<input type="text" id="buttonAbstract" name="buttonAbstract" value="${buttonAbstract}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow buttonTextSection">
		<div class="digitalLibraryCellTitle">Full Text</div>
		<div class="digitalLibraryCell">
			<input type="text" id="buttonFullText" name="buttonFullText" value="${buttonFullText}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow buttonTextSection">
		<div class="digitalLibraryCellTitle">Buy</div>
		<div class="digitalLibraryCell">
			<input type="text" id="buttonBuy" name="buttonBuy" value="${buttonBuy}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow buttonTextSection">
		<div class="digitalLibraryCellTitle">PDF</div>
		<div class="digitalLibraryCell">
			<input type="text" id="buttonPDF" name="buttonPDF" value="${buttonPDF}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow buttonTextSection">
		<div class="digitalLibraryCellTitle">HTML</div>
		<div class="digitalLibraryCell">
			<input type="text" id="buttonHTML" name="buttonHTML" value="${buttonHTML}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow buttonTextSection">
		<div class="digitalLibraryCellTitle">IEEE Xplore</div>
		<div class="digitalLibraryCell">
			<input type="text" id="buttonXPLORE" name="buttonXPLORE" value="${buttonXPLORE}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="portletConfigRow buttonTextSection">
		<div class="digitalLibraryCellTitle">Open URL</div>
		<div class="digitalLibraryCell">
			<input type="text" id="buttonOpenUrl" name="buttonOpenUrl" value="${buttonOpenUrl}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>								


	<div class="digitalLibraryRowButtons">
		<div class="digitalLibraryMessage" id="message"></div>
		<div class="digitalLibrarySave"><input type="button" name="saveConfiguration" id="saveConfiguration" value="Save"/></div>
		<div class="clearBoth"></div>
	</div>	
		
</form>

<script src="/ieeecs-DigitalLibrary-portlet/js/jtip.js" type="text/javascript"></script>
<script src="/ieeecs-DigitalLibrary-portlet/js/jscolor/jscolor.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	
	$("#saveConfiguration").click(function() {	
		$("#message").html("Your Configuration changes are being saved...").fadeOut(3000);
		$("#configureForm").submit();
	});
	
	$("#configurationOptions").change(function() {
		var thisSelected = $(this).val();
		$(".portletSection, .publicationVariablesSection, .textAndLabelsSection, .csdlStructureNamesSection, .csdlUrlComponentsSection, .idSection, .buttonTextSection, .miscSettingsSection").hide();		
		$("." + thisSelected).show();
	});	
	
	$("#portletTopLeftRadius, #portletTopRightRadius, #portletBottomLeftRadius, #portletBottomRightRadius").keyup(function() {	
		
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
		
	$("#topNavBackButton").click(function() {
		$("#configureForm").attr("action", "${viewAction}");
		$("#configureForm").submit();
	});	

	$("#configurationOptions").val("portletSection");
	$(".portletSection").show();

});
</script>