<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<portlet:actionURL var="configureAction" portletMode="edit">
	<portlet:param name="action" value="contentContainerConfigure"/>	
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
	
	#firstCornerRadii, #previousCornerRadii, #nextCornerRadii, #lastCornerRadii {
		border: 1px solid #${paginationBorderColor};
		background-color: #${paginationBackgroundColor};	
	}
	
	#numberedCornerRadii {
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
	
	#firstCornerRadii {
		border-top-left-radius: ${firstTopLeftRadius}px ${firstTopLeftRadius}px;
		-moz-border-top-left-radius: ${firstTopLeftRadius}px ${firstTopLeftRadius}px;
		border-top-right-radius: ${firstTopRightRadius}px ${firstTopRightRadius}px;		
		-moz-border-top-right-radius: ${firstTopRightRadius}px ${firstTopRightRadius}px;		
		border-bottom-left-radius: ${firstBottomLeftRadius}px ${firstBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${firstBottomLeftRadius}px ${firstBottomLeftRadius}px;
		border-bottom-right-radius: ${firstBottomRightRadius}px ${firstBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${firstBottomRightRadius}px ${firstBottomRightRadius}px;
	}	
	
	#previousCornerRadii {
		border-top-left-radius: ${previousTopLeftRadius}px ${previousTopLeftRadius}px;
		-moz-border-top-left-radius: ${previousTopLeftRadius}px ${previousTopLeftRadius}px;
		border-top-right-radius: ${previousTopRightRadius}px ${previousTopRightRadius}px;		
		-moz-border-top-right-radius: ${previousTopRightRadius}px ${previousTopRightRadius}px;		
		border-bottom-left-radius: ${previousBottomLeftRadius}px ${previousBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${previousBottomLeftRadius}px ${previousBottomLeftRadius}px;
		border-bottom-right-radius: ${previousBottomRightRadius}px ${previousBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${previousBottomRightRadius}px ${previousBottomRightRadius}px;
	}		
	
	#nextCornerRadii {
		border-top-left-radius: ${nextTopLeftRadius}px ${nextTopLeftRadius}px;
		-moz-border-top-left-radius: ${nextTopLeftRadius}px ${nextTopLeftRadius}px;
		border-top-right-radius: ${nextTopRightRadius}px ${nextTopRightRadius}px;		
		-moz-border-top-right-radius: ${nextTopRightRadius}px ${nextTopRightRadius}px;		
		border-bottom-left-radius: ${nextBottomLeftRadius}px ${nextBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${nextBottomLeftRadius}px ${nextBottomLeftRadius}px;
		border-bottom-right-radius: ${nextBottomRightRadius}px ${nextBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${nextBottomRightRadius}px ${nextBottomRightRadius}px;
	}	
	
	#lastCornerRadii {
		border-top-left-radius: ${lastTopLeftRadius}px ${lastTopLeftRadius}px;
		-moz-border-top-left-radius: ${lastTopLeftRadius}px ${lastTopLeftRadius}px;
		border-top-right-radius: ${lastTopRightRadius}px ${lastTopRightRadius}px;		
		-moz-border-top-right-radius: ${lastTopRightRadius}px ${lastTopRightRadius}px;		
		border-bottom-left-radius: ${lastBottomLeftRadius}px ${lastBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${lastBottomLeftRadius}px ${lastBottomLeftRadius}px;
		border-bottom-right-radius: ${lastBottomRightRadius}px ${lastBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${lastBottomRightRadius}px ${lastBottomRightRadius}px;
	}	

	#containerPaginationDimensionsExample {
		width: ${paginationWidth}px;
		height: ${paginationHeight}px;
		text-align: center;
		padding: ${paginationOffset}px 0 0 0;		
		border: 1px solid #${paginationBorderColor};
		background-color: #${paginationBackgroundColor};	
	}		
	
	#containerNumberedDimensionsExample {
		width: ${numberedWidth}px;
		height: ${numberedHeight}px;
		text-align: center;
		padding: ${numberedOffset}px 0 0 0;		
		border: 1px solid #${numberedBorderColor};
		background-color: #${numberedBackgroundColor};	
	}			
	
</style>


<script type="text/javascript">	
	
	function updateCSSBorderColorPagination(color) {
		$("#firstCornerRadii").css({"border":"1px solid #" + color});
		$("#previousCornerRadii").css({"border":"1px solid #" + color});
		$("#nextCornerRadii").css({"border":"1px solid #" + color});
		$("#lastCornerRadii").css({"border":"1px solid #" + color});
		$("#containerPaginationDimensionsExample").css({"border":"1px solid #" + color});
	}
	
	function updateCSSBackgroundColorPagination(color) {
		$("#firstCornerRadii").css({"background-color":"#" + color});
		$("#previousCornerRadii").css({"background-color":"#" + color});	
		$("#nextCornerRadii").css({"background-color":"#" + color});	
		$("#lastCornerRadii").css({"background-color":"#" + color});
		$("#containerPaginationDimensionsExample").css({"background-color":"#" + color});
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
	
	function updateCSSBorderColorNumbered(color) {
		$("#numberedCornerRadii").css({"border":"1px solid #" + color});
		$("#containerNumberedDimensionsExample").css({"border":"1px solid #" + color});
	}	
	
	function updateCSSBackgroundColorNumbered(color) {
		$("#numberedCornerRadii").css({"background-color":"#" + color});
		$("#containerNumberedDimensionsExample").css({"background-color":"#" + color});
	}	

</script>

<form id="configureForm" name="configureForm" method="POST" action="${configureAction}">	
	<input type="hidden" name="source" id="source" value="CONFIG"/>
	<input type="hidden" id="showIntro" name="showIntro" value="${showIntro}"/>

	<div class="contentContainerTitle"><u>Configuration for this Content Container Portlet</u>:</div>
	
	<div class="contentContainerInstanceID">Portlet Instance ID: ${id}</div>
	
	<div class="contentContainerMode">
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
				<option value="portletSection">Portlet Configuration</option>
				<option value="paginationSection">Pagination Configuration</option>
			</select>
		</div>
		<div class="topNavBackButton" id="topNavBackButton">Back</div>
		<div class="clearBoth"></div>
	</div>		

	<div class="configContainerExplanationRow introSection">
		<div class="contentContainerConfigExplanation">
			<ul>
				<li>This portlet will display the contents of a Liferay <b>"Web Content"</b> article or <b>"Blog"</b> post.</li>
				<li>While this portlet can be configured to be used on its own, (advanced users recommended), best performance can be reached by combining it with the "Content List Portlet".</li>
				<li>Required parameters passed in:
					<ul>
						<li>INTEGER "g" : <u>example</u> g=12052</li>
						<li>STRING "type" : <u>example</u> type=article </li>
						<li>STRING "urltitle" : <u>example</u> urltitle=long-article-test</li>
					</ul>
				</li>
				<li>Examples of a complete URL:
					<ul>
						<li>/portal/blog1/content?g=12052&type=podcast&urlTitle=cnp-blog-1-second-entry</li>
						<li>/portal/computingnow/cloud/content?g=16228&type=article&urlTitle=long-article-test</li>
					</ul>
				</li>			
			</ul>
		</div>
		<div class="showIntroExplanation"><input type="checkbox" name="showIntroCheckBox" id="showIntroCheckBox" <c:if test="${showIntro == 'ON' || showIntro == 'on'}">checked="checked"</c:if>/> <label for="showIntroCheckBox">Show this 'Intro' tab first when in 'Preferences' mode.</label></div>
	</div>

	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Target Name (ID) <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_TargetName.html?width=300" class="jTip" id="targetNameToolTip" name="What is the unique name (ID) for this portlet?">(?)</a></div>
		<div class="configContainerCell">
			<input type="text" id="targetName" name="targetName" value="${targetName}" size="15"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Comment System On/Off</div>
		<div class="configContainerCell">
			<select id="commentSystem" name="commentSystem">
				<option value="ON" <c:if test="${commentSystem == 'ON'}"> selected="selected" </c:if>>ON</option>
				<option value="OFF" <c:if test="${commentSystem == 'OFF'}"> selected="selected" </c:if>>OFF</option>
			</select>	
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Container Height Offset <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_ContainerHeightOffset.html?width=300" class="jTip" id="containerHeightOffsetToolTip" name="How would you like to adjust the content container height?">(?)</a></div>
		<div class="configContainerCell">
			<input type="text" id="containerHeightOffset" name="containerHeightOffset" value="${containerHeightOffset}" size="5"/> px
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Hide Pagination Controls <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_Pagination.html?width=300" class="jTip" id="paginationToolTip" name="Is pagination required?">(?)</a></div>
		<div class="contentContainerConfigCell">
			<select id="pagination" name="pagination">
				<option value="NO" <c:if test="${pagination == 'NO'}"> selected="selected" </c:if>>No</option>
				<option value="YES" <c:if test="${pagination == 'YES'}"> selected="selected" </c:if>>Yes</option>
			</select>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow portletSection">
		<div class="contentContainerConfigCellTitle">Scroll Duration <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_ScrollDuration.html?width=300" class="jTip" id="scrollDurationToolTip" name="How fast should the list scroll?">(?)</a></div>
		<div class="contentContainerConfigCell">
			<input type="text" id="scrollDuration" name="scrollDuration" value="${scrollDuration}" size="5"/> milliseconds
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Pagination Text <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PaginationText.html?width=300" class="jTip" id="paginationTextToolTip" name="Determine the Pagination button text.">(?)</a></div>
		<div class="contentContainerConfigCell">
			<div class="containerPaginationTextLabel">FIRST PAGE:</div> 
			<div class="containerPaginationTextInput"><input type="text" id="frstText" name="frstText" value="${frstText}" size="15"/></div>
			<div class="containerPaginationTextLabel">PREVIOUS PAGE:</div> 
			<div class="containerPaginationTextInput"><input type="text" id="prevText" name="prevText" value="${prevText}" size="15"/></div>
			<div class="containerPaginationTextLabel">NEXT PAGE:</div> 
			<div class="containerPaginationTextInput"><input type="text" id="nextText" name="nextText" value="${nextText}" size="15"/></div>			
			<div class="containerPaginationTextLabel">LAST PAGE:</div> 
			<div class="containerPaginationTextInput"><input type="text" id="lastText" name="lastText" value="${lastText}" size="15"/></div>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Pagination Dimensions <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PaginationDimensions.html?width=300" class="jTip" id="paginationDimensionsToolTip" name="How wide/high should the Pagination Buttons be?">(?)</a></div>
		<div class="containerPaginationDimensionsContainer">
			<div class="containerPaginationDimensionsSettings">
				<div class="containerPaginationExampleLabel">Width (px)</div>
				<div class="containerPaginationExampleInput"><input type="text" id="paginationWidth" name="paginationWidth" value="${paginationWidth}" size="3"/></div>
				<div class="clearBoth"></div>
				<div class="containerPaginationExampleLabel">Height (px)</div>
				<div class="containerPaginationExampleInput"><input type="text" id="paginationHeight" name="paginationHeight" value="${paginationHeight}" size="3"/></div>
				<div class="clearBoth"></div>
				<div class="containerPaginationExampleLabel">Text Offset (px)</div>
				<div class="containerPaginationExampleInput"><input type="text" id="paginationOffset" name="paginationOffset" value="${paginationOffset}" size="3"/></div>
			</div>
			<div class="containerPaginationDimensionsExample">
				<div id="containerPaginationDimensionsExample">NEXT</div>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Pagination - Border Color <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PaginationBorderColor.html?width=300" class="jTip" id="paginationBorderColorToolTip" name="What is the Pagination Button Border Color?">(?)</a></div>
		<div class="contentContainerConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBorderColorPagination(this);'}" type="text" id="paginationBorderColor" name="paginationBorderColor" value="${paginationBorderColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Pagination - Background Color <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PaginationBackgroundColor.html?width=300" class="jTip" id="paginationBackgroundColorToolTip" name="What is the Pagination Button Background Color?">(?)</a></div>
		<div class="contentContainerConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBackgroundColorPagination(this);'}" type="text" id="paginationBackgroundColor" name="paginationBackgroundColor" value="${paginationBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">First - Corner Radii (px) <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_FirstCornerRadius.html?width=300" class="jTip" id="firstCornerRadiusToolTip" name="What are the Corner Radii (px) for the 'First' Button?">(?)</a></div>
		<div class="cornerRadiusCell" id="firstCornerRadii">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="firstTopLeftRadius" name="firstTopLeftRadius" value="${firstTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="firstTopRightRadius" name="firstTopRightRadius" value="${firstTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="firstBottomLeftRadius" name="firstBottomLeftRadius" value="${firstBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="firstBottomRightRadius" name="firstBottomRightRadius" value="${firstBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Previous - Corner Radii (px) <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PreviousCornerRadius.html?width=300" class="jTip" id="previousCornerRadiusToolTip" name="What are the Corner Radii (px) for the 'Previous' Button?">(?)</a></div>
		<div class="cornerRadiusCell" id="previousCornerRadii">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="previousTopLeftRadius" name="previousTopLeftRadius" value="${previousTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="previousTopRightRadius" name="previousTopRightRadius" value="${previousTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="previousBottomLeftRadius" name="previousBottomLeftRadius" value="${previousBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="previousBottomRightRadius" name="previousBottomRightRadius" value="${previousBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Next - Corner Radii (px) <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_NextCornerRadius.html?width=300" class="jTip" id="nextCornerRadiusToolTip" name="What are the Corner Radii (px) for the 'Next' Button?">(?)</a></div>
		<div class="cornerRadiusCell" id="nextCornerRadii">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="nextTopLeftRadius" name="nextTopLeftRadius" value="${nextTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="nextTopRightRadius" name="nextTopRightRadius" value="${nextTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="nextBottomLeftRadius" name="nextBottomLeftRadius" value="${nextBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="nextBottomRightRadius" name="nextBottomRightRadius" value="${nextBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Last - Corner Radii (px) <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_LastCornerRadius.html?width=300" class="jTip" id="lastCornerRadiusToolTip" name="What are the Corner Radii (px) for the 'Last' Button?">(?)</a></div>
		<div class="cornerRadiusCell" id="lastCornerRadii">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="lastTopLeftRadius" name="lastTopLeftRadius" value="${lastTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="lastTopRightRadius" name="lastTopRightRadius" value="${lastTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="lastBottomLeftRadius" name="lastBottomLeftRadius" value="${lastBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="lastBottomRightRadius" name="lastBottomRightRadius" value="${lastBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>				
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Numbered - Corner Radii (px) <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_NumberedCornerRadius.html?width=300" class="jTip" id="numberedCornerRadiusToolTip" name="What are the Corner Radii (px) for the 'Numbered' Buttons?">(?)</a></div>
		<div class="cornerRadiusCell" id="numberedCornerRadii">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="numberedTopLeftRadius" name="numberedTopLeftRadius" value="${numberedTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="numberedTopRightRadius" name="numberedTopRightRadius" value="${numberedTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="numberedBottomLeftRadius" name="numberedBottomLeftRadius" value="${numberedBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="numberedBottomRightRadius" name="numberedBottomRightRadius" value="${numberedBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Numbered Dimensions <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_NumberedDimensions.html?width=300" class="jTip" id="numberedDimensionsToolTip" name="How wide/high should the 'Numbered' Buttons be?">(?)</a></div>
		<div class="containerPaginationDimensionsContainer">
			<div class="containerPaginationDimensionsSettings">
				<div class="containerPaginationExampleLabel">Width (px)</div>
				<div class="containerPaginationExampleInput"><input type="text" id="numberedWidth" name="numberedWidth" value="${numberedWidth}" size="3"/></div>
				<div class="clearBoth"></div>
				<div class="containerPaginationExampleLabel">Height (px)</div>
				<div class="containerPaginationExampleInput"><input type="text" id="numberedHeight" name="numberedHeight" value="${numberedHeight}" size="3"/></div>
				<div class="clearBoth"></div>
				<div class="containerPaginationExampleLabel">Text Offset (px)</div>
				<div class="containerPaginationExampleInput"><input type="text" id="numberedOffset" name="numberedOffset" value="${numberedOffset}" size="3"/></div>
			</div>
			<div class="containerNumberedDimensionsExample">
				<div id="containerNumberedDimensionsExample">1</div>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Numbered - Border Color <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_NumberedBorderColor.html?width=300" class="jTip" id="numberedBorderColorToolTip" name="What is the Numbered Button Border Color?">(?)</a></div>
		<div class="contentContainerConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBorderColorNumbered(this);'}" type="text" id="numberedBorderColor" name="numberedBorderColor" value="${numberedBorderColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow paginationSection">
		<div class="contentContainerConfigCellTitle">Numbered - Background Color <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_NumberedBackgroundColor.html?width=300" class="jTip" id="numberedBackgroundColorToolTip" name="What is the Numbered Button Background Color?">(?)</a></div>
		<div class="contentContainerConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBackgroundColorNumbered(this);'}" type="text" id="numberedBackgroundColor" name="numberedBackgroundColor" value="${numberedBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Portlet Border - Top <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PortletBorderTop.html?width=300" class="jTip" id="portletBorderTopToolTip" name="What is pixel size and color of the Top Border?">(?)</a></div>
		<div class="configContainerCell">
			<input type="text" id="portletBorderPixelTop" name="portletBorderPixelTop" value="${portletBorderPixelTop}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,0);'}" type="text" id="portletBorderColorTop" name="portletBorderColorTop" value="${portletBorderColorTop}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Portlet Border - Bottom <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PortletBorderBottom.html?width=300" class="jTip" id="portletBorderBottomToolTip" name="What is pixel size and color of the Bottom Border?">(?)</a></div>
		<div class="configContainerCell">
			<input type="text" id="portletBorderPixelBottom" name="portletBorderPixelBottom" value="${portletBorderPixelBottom}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,2);'}" type="text" id="portletBorderColorBottom" name="portletBorderColorBottom" value="${portletBorderColorBottom}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Portlet Border - Left <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PortletBorderLeft.html?width=300" class="jTip" id="portletBorderLeftToolTip" name="What is pixel size and color of the Left Border?">(?)</a></div>
		<div class="configContainerCell">
			<input type="text" id="portletBorderPixelLeft" name="portletBorderPixelLeft" value="${portletBorderPixelLeft}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,3);'}" type="text" id="portletBorderColorLeft" name="portletBorderColorLeft" value="${portletBorderColorLeft}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Portlet Border - Right <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PortletBorderRight.html?width=300" class="jTip" id="portletBorderRightToolTip" name="What is pixel size and color of the Right Border?">(?)</a></div>
		<div class="configContainerCell">
			<input type="text" id="portletBorderPixelRight" name="portletBorderPixelRight" value="${portletBorderPixelRight}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor(this,1);'}" type="text" id="portletBorderColorRight" name="portletBorderColorRight" value="${portletBorderColorRight}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Portlet - Corner Radii (px) <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PortletCornerRadius.html?width=300" class="jTip" id="portletCornerRadiusToolTip" name="What are the Corner Radii (px) for the Portlet?">(?)</a></div>
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
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Portlet Background Color <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_PortletBackgroundColor.html?width=300" class="jTip" id="portletBackgroundColorToolTip" name="What is Portlet background color?">(?)</a></div>
		<div class="configContainerCell">
			#<input class="color {onImmediateChange:'updatePortletBackgroundColor(this);'}" type="text" id="portletBackgroundColor" name="portletBackgroundColor" value="${portletBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="configContainerRow portletSection">
		<div class="configContainerCellTitle">Rest API <a href="/ieeecs-ContentContainer-portlet/templates/toolTip_RestAPI.html?width=450" class="jTip" id="restAPIToolTip" name="Where is the Rest API located?">(?)</a></div>
		<div class="configContainerCell">
			<input type="text" id="restAPI" name="restAPI" value="${restAPI}" size="35"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="configContainerRowButtons">
		<div class="configContainerMessage" id="message"></div>
		<div class="configContainerSave"><input type="button" name="saveConfiguration" id="saveConfiguration" value="Save"/></div>
		<div class="clearBoth"></div>
	</div>	
</form>	


<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jscolor/jscolor.js"></script>
<script type="text/javascript" src="/ieeecs-ContentContainer-portlet/js/jtip.js"></script>

<script>
$(document).ready(function() {

	$("#saveConfiguration").click(function() {		
		
		if ( $("#showIntroCheckBox").is(':checked') ) {
			$("#showIntro").val("ON");
		} else {
			$("#showIntro").val("OFF");
		}		
		
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
	
	$("#paginationWidth").keyup(function() {	
		var newWidth = $(this).val();
		$("#containerPaginationDimensionsExample").css({"width":newWidth + "px"});
	});
	
	$("#paginationHeight").keyup(function() {	
		var newHeight = $(this).val();
		$("#containerPaginationDimensionsExample").css({"height":newHeight + "px"});
	});
	
	$("#paginationOffset").keyup(function() {	
		var newOffset = $(this).val();
		$("#containerPaginationDimensionsExample").css({"padding":newOffset + "px 0 0 0"});
	});	
	
	$("#numberedWidth").keyup(function() {	
		var newWidth = $(this).val();
		$("#containerNumberedDimensionsExample").css({"width":newWidth + "px"});
	});
	
	$("#numberedHeight").keyup(function() {	
		var newHeight = $(this).val();
		$("#containerNumberedDimensionsExample").css({"height":newHeight + "px"});
	});
	
	$("#numberedOffset").keyup(function() {	
		var newOffset = $(this).val();
		$("#containerNumberedDimensionsExample").css({"padding":newOffset + "px 0 0 0"});
	});	
	
	$("#configurationOptions").change(function() {			
		var thisSelected = $(this).val();		
		$(".introSection, .paginationSection, .portletSection").hide();		
		$("." + thisSelected).show();
	});	
	
	$("#topNavBackButton").click(function() {
		$("#configureForm").attr("action", "${viewAction}");
		$("#configureForm").submit();
	});
	
	<c:if test="${showIntro == 'ON' || showIntro == 'on'}">
		$(".introSection").show();
	</c:if>
	<c:if test="${showIntro == 'OFF' || showIntro == 'off'}">
		$("#configurationOptions").val("portletSection");
		$(".portletSection").show();
	</c:if>

});
</script>