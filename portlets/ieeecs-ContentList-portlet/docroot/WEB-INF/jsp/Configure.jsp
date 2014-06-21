<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<portlet:actionURL var="configureAction" portletMode="edit">
	<portlet:param name="action" value="homeConfigure"/>	
</portlet:actionURL>

<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>

<style type="text/css">

	/* Override "legacy" Theme settings where they set the background to grey */
	.columns-max #column-1 {
	    background: none repeat scroll 0 0 #FFFFFF;
	    border: 1px solid #FFFFFF;
	}

	#titleCornerRadii${id} {
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
	
	#previousCornerRadii${id}, #nextCornerRadii${id} {
		border: 1px solid #${paginationBorderColor};
		background-color: #${paginationBackgroundColor};	
	}
	
	#portletCornerRadii${id} {
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
	
	#previousCornerRadii${id} {
		border-top-left-radius: ${previousTopLeftRadius}px ${previousTopLeftRadius}px;
		-moz-border-top-left-radius: ${previousTopLeftRadius}px ${previousTopLeftRadius}px;
		border-top-right-radius: ${previousTopRightRadius}px ${previousTopRightRadius}px;		
		-moz-border-top-right-radius: ${previousTopRightRadius}px ${previousTopRightRadius}px;		
		border-bottom-left-radius: ${previousBottomLeftRadius}px ${previousBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${previousBottomLeftRadius}px ${previousBottomLeftRadius}px;
		border-bottom-right-radius: ${previousBottomRightRadius}px ${previousBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${previousBottomRightRadius}px ${previousBottomRightRadius}px;
	}		
	
	#nextCornerRadii${id} {
		border-top-left-radius: ${nextTopLeftRadius}px ${nextTopLeftRadius}px;
		-moz-border-top-left-radius: ${nextTopLeftRadius}px ${nextTopLeftRadius}px;
		border-top-right-radius: ${nextTopRightRadius}px ${nextTopRightRadius}px;		
		-moz-border-top-right-radius: ${nextTopRightRadius}px ${nextTopRightRadius}px;		
		border-bottom-left-radius: ${nextBottomLeftRadius}px ${nextBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${nextBottomLeftRadius}px ${nextBottomLeftRadius}px;
		border-bottom-right-radius: ${nextBottomRightRadius}px ${nextBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${nextBottomRightRadius}px ${nextBottomRightRadius}px;
	}	

	#paginationDimensionsExample${id} {
		width: ${paginationWidth}px;
		height: ${paginationHeight}px;
		text-align: center;
		padding: ${paginationOffset}px 0 0 0;		
		border: 1px solid #${paginationBorderColor};
		background-color: #${paginationBackgroundColor};	
	}	
	
	.contentListConfigIntroRow${id} {
		width: 95%;
		display: none;		
	}		
</style>

<script type="text/javascript">

	function updateCSSBorderColor${id}(color) {
		$("#titleCornerRadii${id}").css({"border":"1px solid #" + color});
	}
	
	function updateCSSBackgroundColor${id}(color) {
		$("#titleCornerRadii${id}").css({"background-color":"#" + color});				
	}
	
	function updateCSSBorderColorPagination${id}(color) {
		$("#previousCornerRadii${id}").css({"border":"1px solid #" + color});
		$("#nextCornerRadii${id}").css({"border":"1px solid #" + color});
		$("#paginationDimensionsExample${id}").css({"border":"1px solid #" + color});
	}
	
	function updateCSSBackgroundColorPagination${id}(color) {
		$("#previousCornerRadii${id}").css({"background-color":"#" + color});	
		$("#nextCornerRadii${id}").css({"background-color":"#" + color});	
		$("#paginationDimensionsExample${id}").css({"background-color":"#" + color});
	}	
	
	function updatePortletBackgroundColor${id}(color) {
		$("#portletCornerRadii${id}").css({"background-color":"#" + color});
	}
	
	function updatePortletBorderColor${id}(color, border) {
		if ( border == 0 ) {
			$("#portletCornerRadii${id}").css({"border-top-color":"#" + color});
		} else if ( border == 1 ) {
			$("#portletCornerRadii${id}").css({"border-right-color":"#" + color});
		} else if ( border == 2 ) {
			$("#portletCornerRadii${id}").css({"border-bottom-color":"#" + color});
		} else if ( border == 3 ) {
			$("#portletCornerRadii${id}").css({"border-left-color":"#" + color});
		}		
	}

</script>

<form id="configureForm${id}" name="configureForm${id}" method="POST" action="${configureAction}">
	<input type="hidden" name="contentListData${id}" id="contentListData${id}" value=""/>
	<input type="hidden" name="source${id}" id="source${id}" value="CONFIG"/>
	<input type="hidden" id="cssBlock${id}" name="cssBlock${id}" value=""/>
	<input type="hidden" id="htmlBlock${id}" name="htmlBlock${id}" value=""/>
	<input type="hidden" id="showIntro${id}" name="showIntro${id}" value="${showIntro}"/>	

	<div class="contentListTitle"><u>Configuration for this Content List Portlet</u>:</div>
	
	<div class="contentListInstanceID">Portlet Instance ID: ${id} ${dingaDinga}</div>
	
	<div class="contentListMode">
		This portlet is currently :
		<select id="portletMode${id}" name="portletMode${id}">
			<option value="ACTIVATED" <c:if test="${portletMode == 'ACTIVATED'}"> selected="selected" </c:if>>Active (Visible on Site)</option>
			<option value="DEACTIVATED" <c:if test="${portletMode == 'DEACTIVATED'}"> selected="selected" </c:if>>Not Active (Invisible on Site)</option>
		</select>	
	</div>

	<div class="topNavOptions">
		<div class="topNavOption">
			<select id="configurationOptions${id}" name="configurationOptions${id}" class="configurationOptions">
				<option value="introSection${id}">Intro</option>
				<option value="channelSection${id}">Channel Selection</option>
				<option value="filterSection${id}">Filter/Search</option>
				<option value="optionsSection${id}">List Options</option>
				<option value="paginationSection${id}">Pagination Configuration</option>
				<option value="portletSection${id}">Portlet Configuration</option>
				<option value="titleSection${id}">Title Configuration</option>
				<option value="supplementSection${id}">Supplement This List</option>
				<option value="layoutSection${id}">List Item Layout(s)</option>
			</select>
		</div>
		<div class="topNavBackButton" id="topNavBackButton${id}">Back</div>
		<div class="clearBoth"></div>
	</div>	

	<div class="contentListConfigIntroRow${id} introSection${id}">
		<div class="contentListConfigExplanation">
			<ul>
				<li>This portlet will display a list of Liferay <b>"Web Content"</b> or <b>"Blog"</b> entries.</li>
				<li>The format of each list item can be configured within the "List Item Layout(s)" section.
				<li>The configurable sections are:
					<ul>
						<li>Channel Selection : From what channels will this list display entries?</li>
						<li>Filter/Search : Allow filtering/searching of this list.</li>
						<li>List Options : Options such as # of records to pull from the database, Channel names, etc.</li>
						<li>Pagination Configuration : Pagination button settings.</li>
						<li>Portlet Configuration : Portlet border and background settings.</li>
						<li>Title Configuration : Title value, border, and background settings.</li>
						<li>Supplement This List : Follow the on-screen parameters and you can manually add to this list.</li>
						<li>List Item Layout(s) : What format should each list item follow?</li>
					</ul>
				</li>			
			</ul>
		</div>
		<div class="showIntroExplanation"><input type="checkbox" name="showIntroCheckBox${id}" id="showIntroCheckBox${id}" <c:if test="${showIntro == 'ON' || showIntro == 'on'}">checked="checked"</c:if>/> <label for="showIntroCheckBox${id}">Show this 'Intro' tab first when in 'Preferences' mode.</label></div>
	</div>

	<div class="contentListConfigRow channelSection${id}">
		<div class="contentListConfigCellTitle">
			Show content from...
		</div>
		<div class="contentListConfigCell">
			<select name="community${id}" id="community${id}">
		<c:forEach var="communityItem" items="${communityMap}" varStatus="cmIndex">			
				<option value="${communityItem.value.groupId}" <c:if test="${communityItem.value.selected == 'selected'}"> selected="selected"</c:if>>${communityItem.value.name}</option>
		</c:forEach>
			</select>			
		</div>
		<div class="clearBoth"></div>
	</div>	
	<div class="contentListConfigRow channelSection${id}">
		<div class="contentListConfigCellTitle">
			Where the content is a(n)...
		</div>
		<div class="contentListConfigCell">
			<c:forEach var="contentItem" items="${contentItemMap}" varStatus="itemStatus">
				<div class="contentToDisplayLabel" id="contentBitCheckbox${id}_${itemStatus.count}_label">${contentItem.value.label}</div>
				<div class="contentToDisplayCheckbox">
					<input type="checkbox" name="contentBitCheckbox${id}" id="contentBitCheckbox${id}_${itemStatus.count}" value="${contentItem.key}"/>
				</div>
				<div class="clearBoth"></div>
			</c:forEach>			
		</div>
		<div class="clearBoth"></div>
	</div>	
	<div class="contentListConfigRow channelSection${id}">
		<div class="contentListConfigCellTitle">
			Existing in Channel...
		</div>
		<div class="contentListConfigCell">
			<select name="channel${id}" id="channel${id}">
				<option value="" selected="selected">Not Assigned to a Channel</option>
		<c:forEach var="channelItem" items="${channelMap}" varStatus="chIndex">			
				<option value="${channelItem.value.categoryId}">${channelItem.value.name}</option>
		</c:forEach>			
			</select>			
			<div class="contentListMoreLess" id="contentListMoreLess${id}">[More]</div>			
		</div>
		<div class="clearBoth"></div>
	</div>		
	<div class="contentListConfigRow moreLess${id}">
		<div class="contentListConfigCellTitle">
			...of the Type(s):
		</div>
		<div class="contentListConfigTypesCell">
			<div class="contentListConfigTypesSelection">
			<c:forEach var="vocabularyItem" items="${vocabularyMap}" varStatus="vIndex">		
				<div class="contentListConfigTypes">
					<div class="contentListConfigVocabularyName">${vocabularyItem.key}</div>		
				<c:forEach var="vocabularyCategoryItem" items="${vocabularyItem.value}" varStatus="vIndex">
					<div class="contentListChannelSelectionBox">
						<input type="checkbox" id="categoryBox${id}_${vocabularyCategoryItem.value.categoryId}" name="categoryBox${id}_${vocabularyCategoryItem.value.categoryId}" value="${vocabularyCategoryItem.value.categoryId}"/>
					</div>
					<div class="contentListChannelSelectionLabel">
						<label for="categoryBox${id}_${vocabularyCategoryItem.value.categoryId}">${vocabularyCategoryItem.key}</label>
					</div>
					<div class="clearBoth"></div>		
				</c:forEach>
				</div>		
			</c:forEach>
			</div>
		</div>
		<div class="clearBoth"></div>
	</div>		
	<div class="contentListConfigRow channelSection${id}">
		<div class="contentListConfigCellTitle">&nbsp;</div>
		<div class="contentListConfigCell">
			<div class="addButton" id="addButton${id}">Add +</div>
		</div>
		<div class="clearBoth"></div>
	</div>		
	<div class="contentListConfigRow channelSection${id}">
		<div class="contentListSummaryTitle">
			<b>Content to be Displayed in this List</b>:
		</div>
	</div>	
	<div class="contentListConfigRow channelSection${id}">
		<div class="contentListSummaryList" id="contentListSummaryList${id}"></div>
	</div>		
	
	<div class="contentListConfigRow channelSection${id}">
		<div class="contentListConfigCellTitle">Sub-Categories</div>
		<div class="contentListConfigCell">
			<input type="text" id="subCategories${id}" name="subCategories${id}" value="${subCategories}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow channelSection${id}">
		<div class="contentListConfigCellTitle">&nbsp;</div>
		<div class="contentListConfigCell"><b>Use |  symbol for multiple values. <br/>Leading and ending "space" characters are truncated.</b></div>
		<div class="clearBoth"></div>
	</div>	

	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Page Mode <a href="/ieeecs-ContentList-portlet/templates/toolTip_PageMode.html?width=300" class="jTip" id="pageModeToolTip${id}" name="When a Content List link is clicked, where will it go?">(?)</a></div>
		<div class="contentListConfigCell">
			<select id="pageMode${id}" name="pageMode${id}">
				<option value="new" <c:if test="${pageMode == 'new'}"> selected="selected" </c:if>>View in New Page</option>
				<option value="current" <c:if test="${pageMode == 'current'}"> selected="selected" </c:if>>View in Current Page</option>				
			</select><br/>
			<div id="pageModeLabel${id}" class="pageModeLabel">Container Target Name</div> 
			<div id="pageModeInput${id}" class="pageModeInput"><input type="text" id="pageModeTarget${id}" name="pageModeTarget${id}" value="${pageModeTarget}" size="10"/></div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Hide Title <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleVisible.html?width=300" class="jTip" id="titleVisibleToolTip${id}" name="Is the Title required?">(?)</a></div>
		<div class="contentListConfigCell">
			<select id="titleVisible${id}" name="titleVisible${id}">
				<option value="NO" <c:if test="${titleVisible == 'NO'}"> selected="selected" </c:if>>No</option>
				<option value="YES" <c:if test="${titleVisible == 'YES'}"> selected="selected" </c:if>>Yes</option>
			</select>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Title <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleText.html?width=300" class="jTip" id="titleTextToolTip${id}" name="What is the Title of this Content List?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="titleOfList${id}" name="titleOfList${id}" value="${titleOfList}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Title - Top Margin <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleTopMargin.html?width=300" class="jTip" id="titleTopMarginToolTip${id}" name="What is the Title Top Margin?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="titleTopMargin${id}" name="titleTopMargin${id}" value="${titleTopMargin}" size="5"/> px
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Title - Bottom Margin <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleBottomMargin.html?width=300" class="jTip" id="titleBottomMarginToolTip${id}" name="What is the Title Bottom Margin?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="titleBottomMargin${id}" name="titleBottomMargin${id}" value="${titleBottomMargin}" size="5"/> px
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Title Color <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleColor.html?width=300" class="jTip" id="titleColorToolTip" name="What is the Title Color?">(?)</a></div>
		<div class="contentListConfigCell">
			#<input class="color" type="text" id="titleColor${id}" name="titleColor${id}" value="${titleColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Title Font <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleFont.html?width=300" class="jTip" id="titleFontToolTip" name="What is the Title Font?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="titleFont${id}" name="titleFont${id}" value="${titleFont}" size="50"/> ;
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Title - Border Color <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleBorderColor.html?width=300" class="jTip" id="titleBorderColorToolTip${id}" name="What is the Title Border Color?">(?)</a></div>
		<div class="contentListConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBorderColor${id}(this);'}" type="text" id="titleBorderColor${id}" name="titleBorderColor${id}" value="${titleBorderColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Title - Background Color <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleBackgroundColor.html?width=300" class="jTip" id="titleBackgroundColorToolTip${id}" name="What is the Title Background Color?">(?)</a></div>
		<div class="contentListConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBackgroundColor${id}(this);'}" type="text" id="titleBackgroundColor${id}" name="titleBackgroundColor${id}" value="${titleBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow titleSection${id}">
		<div class="contentListConfigCellTitle">Title - Corner Radii (px) <a href="/ieeecs-ContentList-portlet/templates/toolTip_TitleCornerRadius.html?width=300" class="jTip" id="titleCornerRadiusToolTip${id}" name="What are the Corner Radii (px) for the Title Header?">(?)</a></div>
		<div class="cornerRadiusCell" id="titleCornerRadii${id}">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="titleTopLeftRadius${id}" name="titleTopLeftRadius${id}" value="${titleTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="titleTopRightRadius${id}" name="titleTopRightRadius${id}" value="${titleTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="titleBottomLeftRadius${id}" name="titleBottomLeftRadius${id}" value="${titleBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="titleBottomRightRadius${id}" name="titleBottomRightRadius${id}" value="${titleBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle"># Line Items Per Page <a href="/ieeecs-ContentList-portlet/templates/toolTip_PerPage.html?width=300" class="jTip" id="perPageToolTip${id}" name="How many line items per page?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="perPage${id}" name="perPage${id}" value="${perPage}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow filterSection${id}">
		<div class="contentListConfigCellTitle">Hide Filter Controls <a href="/ieeecs-ContentList-portlet/templates/toolTip_Filter.html?width=300" class="jTip" id="filteringToolTip${id}" name="Is filter/search required?">(?)</a></div>
		<div class="contentListConfigCell">
			<select id="filtering${id}" name="filtering${id}">
				<option value="NO" <c:if test="${filtering == 'NO'}"> selected="selected" </c:if>>No</option>
				<option value="YES" <c:if test="${filtering == 'YES'}"> selected="selected" </c:if>>Yes</option>
			</select>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow filterSection${id}">
		<div class="contentListConfigCellTitle">Search Text <a href="/ieeecs-ContentList-portlet/templates/toolTip_SearchText.html?width=300" class="jTip" id="titleSearchTextToolTip${id}" name="What does the Search Input Text say?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="searchInputText${id}" name="searchInputText${id}" value="${searchInputText}" size="25"/>
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="contentListConfigRow paginationSection${id}">
		<div class="contentListConfigCellTitle">Hide Pagination Controls <a href="/ieeecs-ContentList-portlet/templates/toolTip_Pagination.html?width=300" class="jTip" id="paginationToolTip${id}" name="Is pagination required?">(?)</a></div>
		<div class="contentListConfigCell">
			<select id="pagination${id}" name="pagination${id}">
				<option value="NO" <c:if test="${pagination == 'NO'}"> selected="selected" </c:if>>No</option>
				<option value="YES" <c:if test="${pagination == 'YES'}"> selected="selected" </c:if>>Yes</option>
			</select>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Scroll Duration <a href="/ieeecs-ContentList-portlet/templates/toolTip_ScrollDuration.html?width=300" class="jTip" id="scrollDurationToolTip${id}" name="How fast should the list scroll?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="scrollDuration${id}" name="scrollDuration${id}" value="${scrollDuration}" size="5"/> milliseconds
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Initial # of Records <a href="/ieeecs-ContentList-portlet/templates/toolTip_InitialChunk.html?width=300" class="jTip" id="initialChunkToolTip${id}" name="How many records should I pull from the DB, initially?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="initialChunk${id}" name="initialChunk${id}" value="${initialChunk}" size="5"/> records
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">"Chunk" Record Size <a href="/ieeecs-ContentList-portlet/templates/toolTip_AsyncChunk.html?width=300" class="jTip" id="asyncChunkToolTip${id}" name="How many records should I pull from the DB, asynchronously?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="asyncChunk${id}" name="asyncChunk${id}" value="${asyncChunk}" size="5"/> records
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Total Records to Pull <a href="/ieeecs-ContentList-portlet/templates/toolTip_RecordsToPull.html?width=300" class="jTip" id="recordsToPullToolTip${id}" name="How many total records should I pull from the DB?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="recordsToPull${id}" name="recordsToPull${id}" value="${recordsToPull}" size="5"/> records
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">URL Target Name 
		<a href="/ieeecs-ContentList-portlet/templates/toolTip_URLTargetName1.html?width=450" class="jTip" id="urlTargetNameToolTip1${id}" name="Where are the links of this Content List pointing to?">(?)</a>
		<a href="/ieeecs-ContentList-portlet/templates/toolTip_URLTargetName2.html?width=500" class="jTip" id="urlTargetNameToolTip2${id}" name="Some Examples">(?)</a>
		</div>
		<div class="contentListConfigCell">
			<input type="text" id="urlTargetName${id}" name="urlTargetName${id}" value="${urlTargetName}" size="10"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Channel Vocabulary Name <a href="/ieeecs-ContentList-portlet/templates/toolTip_ChannelVocabularyName.html?width=450" class="jTip" id="channelVocabularyNameToolTip${id}" name="Where should I get my Channel names?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="channelVocabularyName${id}" name="channelVocabularyName${id}" value="${channelVocabularyName}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Content Type Vocabulary Name <a href="/ieeecs-ContentList-portlet/templates/toolTip_ContentTypeVocabularyName.html?width=450" class="jTip" id="contentTypeVocabularyNameToolTip${id}" name="Where should I get my Content Type names?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="contentTypeVocabularyName${id}" name="contentTypeVocabularyName${id}" value="${contentTypeVocabularyName}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Properties File Name <a href="/ieeecs-ContentList-portlet/templates/toolTip_PropertiesFileName.html?width=300" class="jTip" id="propertiesFileNameToolTip${id}" name="What are the valid Communities / Vocabularies for this list?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="propertiesFile${id}" name="propertiesFile${id}" value="${propertiesFile}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>				
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Rest API <a href="/ieeecs-ContentList-portlet/templates/toolTip_RestAPI.html?width=450" class="jTip" id="restAPIToolTip${id}" name="Where is the Rest API located?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="restAPI${id}" name="restAPI${id}" value="${restAPI}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Public Servlet Mapping</div>
		<div class="contentListConfigCell">
			<input type="text" id="publicServletMapping${id}" name="publicServletMapping${id}" value="${publicServletMapping}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Article Image Path</div>
		<div class="contentListConfigCell">
			<input type="text" id="articleImagePath${id}" name="articleImagePath${id}" value="${articleImagePath}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Blog Image Path</div>
		<div class="contentListConfigCell">
			<input type="text" id="blogImagePath${id}" name="blogImagePath${id}" value="${blogImagePath}" size="60"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Default Image Path</div>
		<div class="contentListConfigCell">
			<input type="text" id="defaultImagePath${id}" name="defaultImagePath${id}" value="${defaultImagePath}" size="60"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow optionsSection${id}">
		<div class="contentListConfigCellTitle">Display Date Format</div>
		<div class="contentListConfigCell">
			<input type="text" id="displayDateFormat${id}" name="displayDateFormat${id}" value="${displayDateFormat}" size="50"/>
		</div>
		<div class="clearBoth"></div>
	</div>				
	
	<div class="contentListConfigRow paginationSection${id}">
		<div class="contentListConfigCellTitle">Pagination Text <a href="/ieeecs-ContentList-portlet/templates/toolTip_PaginationText.html?width=300" class="jTip" id="paginationTextToolTip${id}" name="Determine the Pagination button text.">(?)</a></div>
		<div class="contentListConfigCell">
			<div class="paginationTextLabel">PREVIOUS PAGE:</div> 
			<div class="paginationTextInput"><input type="text" id="prevText${id}" name="prevText${id}" value="${prevText}" size="15"/></div>
			<div class="clearBoth"></div>
			<div class="paginationTextLabel">NEXT PAGE:</div> 
			<div class="paginationTextInput"><input type="text" id="nextText${id}" name="nextText${id}" value="${nextText}" size="15"/></div>
			<div class="clearBoth"></div>			
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow paginationSection${id}">
		<div class="contentListConfigCellTitle">Pagination Dimensions <a href="/ieeecs-ContentList-portlet/templates/toolTip_PaginationDimensions.html?width=300" class="jTip" id="paginationDimensionsToolTip${id}" name="How wide/high should the Pagination Buttons be?">(?)</a></div>
		<div class="paginationDimensionsContainer">
			<div class="paginationDimensionsSettings">
				<div class="paginationExampleLabel">Width (px)</div>
				<div class="paginationExampleInput"><input type="text" id="paginationWidth${id}" name="paginationWidth${id}" value="${paginationWidth}" size="3"/></div>
				<div class="clearBoth"></div>
				<div class="paginationExampleLabel">Height (px)</div>
				<div class="paginationExampleInput"><input type="text" id="paginationHeight${id}" name="paginationHeight${id}" value="${paginationHeight}" size="3"/></div>
				<div class="clearBoth"></div>
				<div class="paginationExampleLabel">Text Offset (px)</div>
				<div class="paginationExampleInput"><input type="text" id="paginationOffset${id}" name="paginationOffset${id}" value="${paginationOffset}" size="3"/></div>
			</div>
			<div class="paginationDimensionsExample">
				<div id="paginationDimensionsExample${id}">NEXT</div>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="contentListConfigRow paginationSection${id}">
		<div class="contentListConfigCellTitle">Pagination - Border Color <a href="/ieeecs-ContentList-portlet/templates/toolTip_PaginationBorderColor.html?width=300" class="jTip" id="paginationBorderColorToolTip${id}" name="What is the Pagination Button Border Color?">(?)</a></div>
		<div class="contentListConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBorderColorPagination${id}(this);'}" type="text" id="paginationBorderColor${id}" name="paginationBorderColor${id}" value="${paginationBorderColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow paginationSection${id}">
		<div class="contentListConfigCellTitle">Pagination - BG Color <a href="/ieeecs-ContentList-portlet/templates/toolTip_PaginationBackgroundColor.html?width=300" class="jTip" id="paginationBackgroundColorToolTip${id}" name="What is the Pagination Button Background Color?">(?)</a></div>
		<div class="contentListConfigCell">
			#<input class="color {onImmediateChange:'updateCSSBackgroundColorPagination${id}(this);'}" type="text" id="paginationBackgroundColor${id}" name="paginationBackgroundColor${id}" value="${paginationBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow paginationSection${id}">
		<div class="contentListConfigCellTitle">Previous - Corner Radii (px) <a href="/ieeecs-ContentList-portlet/templates/toolTip_PreviousCornerRadius.html?width=300" class="jTip" id="previousCornerRadiusToolTip${id}" name="What are the Corner Radii (px) for the 'Previous' Button?">(?)</a></div>
		<div class="cornerRadiusCell" id="previousCornerRadii${id}">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="previousTopLeftRadius${id}" name="previousTopLeftRadius${id}" value="${previousTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="previousTopRightRadius${id}" name="previousTopRightRadius${id}" value="${previousTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="previousBottomLeftRadius${id}" name="previousBottomLeftRadius${id}" value="${previousBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="previousBottomRightRadius${id}" name="previousBottomRightRadius${id}" value="${previousBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow paginationSection${id}">
		<div class="contentListConfigCellTitle">Next - Corner Radii (px) <a href="/ieeecs-ContentList-portlet/templates/toolTip_NextCornerRadius.html?width=300" class="jTip" id="nextCornerRadiusToolTip${id}" name="What are the Corner Radii (px) for the 'Next' Button?">(?)</a></div>
		<div class="cornerRadiusCell" id="nextCornerRadii${id}">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="nextTopLeftRadius${id}" name="nextTopLeftRadius${id}" value="${nextTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="nextTopRightRadius${id}" name="nextTopRightRadius${id}" value="${nextTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="nextBottomLeftRadius${id}" name="nextBottomLeftRadius${id}" value="${nextBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="nextBottomRightRadius${id}" name="nextBottomRightRadius${id}" value="${nextBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow portletSection${id}">
		<div class="contentListConfigCellTitle">Portlet Border - Top <a href="/ieeecs-ContentList-portlet/templates/toolTip_PortletBorderTop.html?width=300" class="jTip" id="portletBorderTopToolTip${id}" name="What is pixel size and color of the Top Border?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="portletBorderPixelTop${id}" name="portletBorderPixelTop${id}" value="${portletBorderPixelTop}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,0);'}" type="text" id="portletBorderColorTop${id}" name="portletBorderColorTop${id}" value="${portletBorderColorTop}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow portletSection${id}">
		<div class="contentListConfigCellTitle">Portlet Border - Bottom <a href="/ieeecs-ContentList-portlet/templates/toolTip_PortletBorderBottom.html?width=300" class="jTip" id="portletBorderBottomToolTip${id}" name="What is pixel size and color of the Bottom Border?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="portletBorderPixelBottom${id}" name="portletBorderPixelBottom${id}" value="${portletBorderPixelBottom}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,2);'}" type="text" id="portletBorderColorBottom${id}" name="portletBorderColorBottom${id}" value="${portletBorderColorBottom}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="contentListConfigRow portletSection${id}">
		<div class="contentListConfigCellTitle">Portlet Border - Left <a href="/ieeecs-ContentList-portlet/templates/toolTip_PortletBorderLeft.html?width=300" class="jTip" id="portletBorderLeftToolTip${id}" name="What is pixel size and color of the Left Border?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="portletBorderPixelLeft${id}" name="portletBorderPixelLeft${id}" value="${portletBorderPixelLeft}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,3);'}" type="text" id="portletBorderColorLeft${id}" name="portletBorderColorLeft${id}" value="${portletBorderColorLeft}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="contentListConfigRow portletSection${id}">
		<div class="contentListConfigCellTitle">Portlet Border - Right <a href="/ieeecs-ContentList-portlet/templates/toolTip_PortletBorderRight.html?width=300" class="jTip" id="portletBorderRightToolTip${id}" name="What is pixel size and color of the Right Border?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="portletBorderPixelRight${id}" name="portletBorderPixelRight${id}" value="${portletBorderPixelRight}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,1);'}" type="text" id="portletBorderColorRight${id}" name="portletBorderColorRight${id}" value="${portletBorderColorRight}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow portletSection${id}">
		<div class="contentListConfigCellTitle">Portlet - Corner Radii (px) <a href="/ieeecs-ContentList-portlet/templates/toolTip_PortletCornerRadius.html?width=300" class="jTip" id="portletCornerRadiusToolTip${id}" name="What are the Corner Radii (px) for the Portlet?">(?)</a></div>
		<div class="cornerRadiusCell" id="portletCornerRadii${id}">
			<div class="cornerRadiusLabel">Top Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="portletTopLeftRadius${id}" name="portletTopLeftRadius${id}" value="${portletTopLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="portletTopRightRadius${id}" name="portletTopRightRadius${id}" value="${portletTopRightRadius}" size="1"/></div>
			<div class="cornerRadiusLabel">Top Right</div>
			<div class="clearBoth"></div>
			<div class="cornerRadiusLabel">Bottom Left</div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="portletBottomLeftRadius${id}" name="portletBottomLeftRadius${id}" value="${portletBottomLeftRadius}" size="1"/></div>
			<div class="cornerRadiusInput"><input type="text" class="inputKeyup" id="portletBottomRightRadius${id}" name="portletBottomRightRadius${id}" value="${portletBottomRightRadius}" size="1"/></div> 			
			<div class="cornerRadiusLabel">Bottom Right</div>	
			<div class="clearBoth"></div>		
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow portletSection${id}">
		<div class="contentListConfigCellTitle">Portlet Background Color <a href="/ieeecs-ContentList-portlet/templates/toolTip_PortletBackgroundColor.html?width=300" class="jTip" id="portletBackgroundColorToolTip${id}" name="What is Portlet background color?">(?)</a></div>
		<div class="contentListConfigCell">
			#<input class="color {onImmediateChange:'updatePortletBackgroundColor${id}(this);'}" type="text" id="portletBackgroundColor${id}" name="portletBackgroundColor${id}" value="${portletBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow portletSection${id}">
		<div class="contentListConfigCellTitle">Portlet Container Height<a href="/ieeecs-ContentList-portlet/templates/toolTip_PortletContainerHeight.html?width=300" class="jTip" id="portletContainerHeightToolTip${id}" name="What is height of the Content List Container?">(?)</a></div>
		<div class="contentListConfigCell">
			<input type="text" id="portletContainerHeight${id}" name="portletContainerHeight${id}" value="${portletContainerHeight}" size="2"/> px
		</div>
		<div class="clearBoth"></div>
	</div>			
	
	<div class="contentListConfigRow portletSection${id}">
		<div class="contentListConfigCellTitle">Portlet Container Item CSS<a href="/ieeecs-ContentList-portlet/templates/toolTip_PortletContainerItemCSS.html?width=300" class="jTip" id="portletContainerItemCSSToolTip${id}" name="What is CSS for each Content List item/entry?">(?)</a></div>
		<div class="contentListConfigCell">
			<textarea id="portletContainerItemCSS${id}" name="portletContainerItemCSS${id}" rows="10" cols="24" class="containerItemTextArea">${portletContainerItemCSS}</textarea>
		</div>
		<div class="clearBoth"></div>
	</div>		
	
	<div class="contentListConfigRow supplementSection${id}">
		<div class="contentListConfigCellTitle">
			Add Other Content Items			
		</div>
		<div class="supplementConfigCell">
			<pre>
{
"<span style="color: blue;"><b>imageUrl</b></span>":"FULL URL TO IMAGE THUMBNAIL",
"<span style="color: blue;"><b>title</b></span>":"TITLE OF THIS ITEM",
"<span style="color: blue;"><b>dateTime</b></span>":"FORMAT LIKE YYYY-MM-DD HH:MM",
"<span style="color: blue;"><b>description</b></span>":"SHORT DESCRIPTION FOR THIS ITEM",
"<span style="color: blue;"><b>url</b></span>":"FULL URL TO THE ITEM LANDING PAGE",
"<span style="color: blue;"><b>subType</b></span>":"WHATEVER YOU FEEL IS BEST OR LEAVE BLANK",
"<span style="color: blue;"><b>channel</b></span>":"CHANNEL NAME. LEAVE BLANK IF NOT ASSIGNED TO A CHANNEL",
"<span style="color: blue;"><b>peerReviewed</b></span>":"HAS THIS ENTRY BEEN PEER REVIEWED. true/false",
"<span style="color: blue;"><b>target</b></span>":"TARGET DESTINATION OF NEW PAGE: _self, _blank, etc."
},   <=== comma only needed if you have additional Items					
			</pre>				
			<textarea id="supplement${id}" name="supplement${id}" class="supplementTextArea">${supplement}</textarea>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListConfigRow layoutSection${id}">
		<div class="contentListConfigCellTitle">Layout Type <a href="/ieeecs-ContentList-portlet/templates/toolTip_LayoutType.html?width=300" class="jTip" id="layoutTypeToolTip${id}" name="How should this content be displayed?">(?)</a></div>
		<div class="contentListConfigCell">
			<select id="uiLayout${id}" name="uiLayout${id}">
				<option value="standard" <c:if test="${uiLayout == 'standard'}"> selected="selected" </c:if>>Full Image, Title, Date, Description</option>
				<option value="minimal1" <c:if test="${uiLayout == 'minimal1'}"> selected="selected" </c:if>>Small Image, Title, Date, Description</option>
				<option value="minimal2" <c:if test="${uiLayout == 'minimal2'}"> selected="selected" </c:if>>Title, Date, Description</option>				
				<option value="titleDateLink" <c:if test="${uiLayout == 'titleDateLink'}"> selected="selected" </c:if>>Title, Date</option>
				<option value="descriptionText" <c:if test="${uiLayout == 'descriptionText'}"> selected="selected" </c:if>>Description (text)</option>
				<option value="descriptionLink" <c:if test="${uiLayout == 'descriptionLink'}"> selected="selected" </c:if>>Description (link)</option>
				<option value="titleLink" <c:if test="${uiLayout == 'titleLink'}"> selected="selected" </c:if>>Title (link)</option>
				<option value="custom" <c:if test="${uiLayout == 'custom'}"> selected="selected" </c:if>>Custom (Advanced)</option>				
			</select>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="contentListCustomConfigInstructionRow customEditor${id}">
		<div class="contentListCustomConfigCellTitle">
			<u>Custom Content List Item</u><br/>
			<ul>
				<li>Recommended knowledge of HTML/CSS</li>
				<li>While you can use the CSS / HTML blocks below, it's probably going to be less error-prone if you develop first, then copy/paste the finished code into the two blocks below.</li>
				<li><a href="/ieeecs-ContentList-portlet/templates/toolTip_LayoutExamples.html" id="layoutExamplesToolTip${id}" target="_blank">Example(s)</a></li>
			</ul>					
		</div>
		<div class="contentListCustomConfigCellInstructions">
			<u>Available and commonly used</u>:<br/>
			<ul>
				<li><span style="color: blue;">[%=imagePath%]</span> - path to the thumbnail image used</li>
				<li><span style="color: blue;">[%=url%]</span> - the URL to the page that the list entry represents</li>
				<li><span style="color: blue;">[%=title%]</span> - the title</li>
				<li><span style="color: blue;">[%=urlTitle%]</span> - the SEO compliant, URL title</li>
				<li><span style="color: blue;">[%=description%]</span> - a short description</li>
				<li><span style="color: blue;">[%=dateTime%]</span> - the Date/Time of the list entry</li>
				<li><span style="color: blue;">[%=type%]</span> - Article, Blog Post, or Podcast.</li>
				<li><span style="color: blue;">[%=subType%]</span> - Webinar, Interview, Conference, Book, Whitepaper, etc.</li>
				<li><span style="color: blue;">[%=target%]</span> - Will the new link open in the same browser, or new (_self or _blank)</li>
				<li><span style="color: blue;">[%=channel%]</span> - the Channel name that the list entry belongs to. ()Leave blank if N/A.)</li>
			</ul>	
			<br/>
			<u>Available but not commonly used</u>:<br/>
			<ul>
				<li><span style="color: blue;">[%=id%]</span> - the distinct ID of the list entry</li>
				<li><span style="color: blue;">[%=groupId%]</span> - the Community ID that the list entry belongs to</li>
				<li><span style="color: blue;">[%=dateTimeMS%]</span> - the Date/Time of the list entry in Milliseconds</li>
			<ul>							
		</div>
		<div class="clearBoth"></div>		
	</div>	
	
	<div class="contentListCustomConfigRow customEditor${id}">
		CSS Block
		<div id="editor_cssSection${id}" name="editor_cssSection${id}" class="contentListCustomBlock"></div>
	</div>	
	
	<div class="contentListCustomConfigRow customEditor${id}">
		HTML Block
		<div id="editor_htmlSection${id}" name="editor_htmlSection${id}" class="contentListCustomBlock"></div>
	</div>	
		
	<div class="contentListConfigRowButtons">
		<div class="contentListConfigMessage" id="message${id}"></div>
		<div class="contentListConfigSave"><input type="button" name="saveConfiguration${id}" id="saveConfiguration${id}" value="Save"/></div>
		<div class="clearBoth"></div>
	</div>	
</form>

<c:if test="${fallbackJS}">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
</c:if>

<script src="/ieeecs-ContentList-portlet/js/jtip.js" type="text/javascript"></script>
<script src="/ieeecs-ContentList-portlet/js/jscolor/jscolor.js" type="text/javascript"></script>
<script src="/ieeecs-ContentList-portlet/js/ace/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="/ieeecs-ContentList-portlet/js/ace/theme-textmate.js" type="text/javascript" charset="utf-8"></script>
<script src="/ieeecs-ContentList-portlet/js/ace/mode-css.js" type="text/javascript" charset="utf-8"></script>
<script src="/ieeecs-ContentList-portlet/js/ace/mode-html.js" type="text/javascript" charset="utf-8"></script>

<script>
$(document).ready(function() {
	
	<%-- ***************************************************** --%>
	<%--  Setup of Ace Editors                                 --%>
	<%-- ***************************************************** --%>	
	var CSSMode${id} = require("ace/mode/css").Mode;
	var HTMLMode${id} = require("ace/mode/html").Mode;
	
	var editor_cssSection${id} = ace.edit("editor_cssSection${id}");
	editor_cssSection${id}.setTheme("ace/theme/textmate");
	editor_cssSection${id}.getSession().setMode(new CSSMode${id}());
	editor_cssSection${id}.getSession().getDocument().insertLines(0, ${cssBlock});
	
	var editor_htmlSection${id} = ace.edit("editor_htmlSection${id}");
	editor_htmlSection${id}.setTheme("ace/theme/textmate");
	editor_htmlSection${id}.getSession().setMode(new HTMLMode${id}());
	editor_htmlSection${id}.getSession().getDocument().insertLines(0, ${htmlBlock}); 	
	
	$("#uiLayout${id}").change(function() {
		if ( $("#uiLayout${id}").val() == "custom" ) {
			$(".customEditor${id}").show();
		} else {
			$(".customEditor${id}").hide();
		}
	});	

	<%-- ***************************************************** --%>
	<%--  This is the data for this list, in JSON form.        --%>
	<%-- ***************************************************** --%>
	var contentListData${id} = ${contentListData};
	
	<%-- ***************************************************** --%>
	<%--  Populate the Display with the settings for this list --%>
	<%-- ***************************************************** --%>
	for ( key in contentListData${id} ) {
		
		var uiId = contentListData${id}[key].uiId;
		var description = contentListData${id}[key].description;
		
		var newDisplayItem = $('<div class="contentListSummaryListItem" id="description${id}_' + uiId + '">* ' + description + '</div>');
		var removeDisplayItem = $('<div class="contentListSummaryListItemRemove" id="remove${id}_' + uiId + '">REMOVE</div>');
		removeDisplayItem.click(function() {
			var thisId = $(this).attr("id");
			var thisUiId = thisId.replace("remove${id}_" , "");
			$("#description${id}_" + thisUiId).remove();
			$("#remove${id}_" + thisUiId).remove();
			
			for ( key in contentListData${id} ) {				
				var uiId = contentListData${id}[key].uiId;
				if ( uiId == thisUiId ) {
					contentListData${id}.splice(key, 1);
				}
			}			
		});
		var clearBoth = $('<div class="clearBoth"></div>');
		$("#contentListSummaryList${id}").append(newDisplayItem);
		$("#contentListSummaryList${id}").append(removeDisplayItem);
		$("#contentListSummaryList${id}").append(clearBoth);			
	}

	$("#addButton${id}").click(function() {
		
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
		var communityId = $("#community${id}").val();
		var communityName = $("#community${id} option:selected").text();
		newContentConfig["groupId"] = communityId;
		
		<%-- ************* --%>
		<%--     TYPE      --%>
		<%-- ************* --%>
		var tally = 0;	
		var contentTypes = "";		
		$('input[name=contentBitCheckbox${id}]').each(function () {			
			if ( this.checked ) {
				var current = parseInt($(this).val());
				tally += current;
				var currentId = $(this).attr("id");
				var currentLabel = $("#" + currentId + "_label").html();
				contentTypes = contentTypes + currentLabel + "s, ";	
			}
		});	
		if ( contentTypes != "" ) {
			contentTypes = contentTypes.substring(0, contentTypes.length - 2);
		}
		var contentBit = tally;
		newContentConfig["contentBit"] = contentBit;
			
		<%-- ************* --%>
		<%--    CHANNEL    --%>
		<%-- ************* --%>
		newContentConfig["categories"] = [];
		var channelId = $("#channel${id}").val();
		var channelName = $("#channel${id} option:selected").text(); 
		
		if ( channelId != "" ) {
			newContentConfig["categories"].push(channelId);
		}
		
		<%-- ************* --%>
		<%--   CATEGORIES  --%>
		<%-- ************* --%>
		var categories = [];
		var categoryNames = "";
		$("input[id^='categoryBox${id}']:checked").each(function() {			
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
		newContentConfig["description"] = description;
		
		<%-- ************* --%>
		<%-- ADD TO ARRAY  --%>
		<%-- ************* --%>	
		contentListData${id}.push(newContentConfig);
				
		<%-- ************* --%>
		<%--    DISPLAY    --%>
		<%-- ************* --%>	
		var newDisplayItem = $('<div class="contentListSummaryListItem" id="description${id}_' + uiId + '">* ' + description + '</div>');
		var removeDisplayItem = $('<div class="contentListSummaryListItemRemove" id="remove${id}_' + uiId + '">REMOVE</div>');
		removeDisplayItem.click(function() {
			var thisId = $(this).attr("id");
			var thisUiId = thisId.replace("remove${id}_" , "");
			$("#description${id}_" + thisUiId).remove();
			$("#remove${id}_" + thisUiId).remove();
			
			for ( key in contentListData${id} ) {				
				var uiId = contentListData${id}[key].uiId;
				if ( uiId == thisUiId ) {
					contentListData${id}.splice(key, 1);
				}
			}			
		});
		var clearBoth = $('<div class="clearBoth"></div>');
		$("#contentListSummaryList${id}").append(newDisplayItem);
		$("#contentListSummaryList${id}").append(removeDisplayItem);
		$("#contentListSummaryList${id}").append(clearBoth);

		<%-- ************* --%>
		<%--     RESET     --%>
		<%-- ************* --%>	
		$('input[name=contentBitCheckbox${id}]').each(function () {			
			$(this).attr("checked", false);
		});					
		$("input[id^='categoryBox${id}']:checked").each(function() {			
			$(this).attr("checked", false);
		});		
		$("#channel${id}").val("");
				
	});
	
	$("#contentListMoreLess${id}").click(function() {

        if ($(".moreLess${id}").css("display") == "none"){
            $(".moreLess${id}").css("display", "block");
            $(this).html("[Less]");
        } else {
            $(".moreLess${id}").css("display", "none");
            $(this).html("[More]");
        }				
	});
	
	$("#saveConfiguration${id}").click(function() {
		
		var cssBlockLines = editor_cssSection${id}.getSession().getDocument().getAllLines();
		var htmlBlockLines = editor_htmlSection${id}.getSession().getDocument().getAllLines();
		
		if ( cssBlockLines[ cssBlockLines.length-1 ] == "" ) {
			cssBlockLines.pop();
		}
		if ( htmlBlockLines[ htmlBlockLines.length-1 ] == "" ) {
			htmlBlockLines.pop();
		}		
		
		cssBlockLines = JSON.stringify(cssBlockLines, null, 10);
		htmlBlockLines = JSON.stringify(htmlBlockLines, null, 10);
		
		$("#cssBlock${id}").val(cssBlockLines);
		$("#htmlBlock${id}").val(htmlBlockLines);		
		
	    $("#contentListData${id}").val( JSON.stringify(contentListData${id}) );
	    	  
		var uiLayout = $("#uiLayout${id}").val();
		var perPage = $("#perPage${id}").val();

		if ( uiLayout == "" || perPage == "" ) {
			alert("'Content to Display', 'Layout Type', and '# Per Page' are required.");
		} else {
			
			if ( $("#showIntroCheckBox${id}").is(':checked') ) {
				$("#showIntro${id}").val("ON");
			} else {
				$("#showIntro${id}").val("OFF");
			}			
			
			$("#message${id}").html("Your Configuration changes are being saved...").fadeOut(3000);
			$("#configureForm${id}").submit();
		}
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
	
	$("#paginationWidth${id}").keyup(function() {	
		var newWidth = $(this).val();
		$("#paginationDimensionsExample${id}").css({"width":newWidth + "px"});
	});
	
	$("#paginationHeight${id}").keyup(function() {	
		var newHeight = $(this).val();
		$("#paginationDimensionsExample${id}").css({"height":newHeight + "px"});
	});
	
	$("#paginationOffset${id}").keyup(function() {	
		var newOffset = $(this).val();
		$("#paginationDimensionsExample${id}").css({"padding":newOffset + "px 0 0 0"});
	});	
	
	$("#nextTopLeftRadius${id}").keyup(function() {
		var pixelValue = $(this).val();
		$("#paginationDimensionsExample${id}").css({"border-top-left-radius":pixelValue + "px " + pixelValue + "px"});
		$("#paginationDimensionsExample${id}").css({"-moz-border-top-left-radius":pixelValue + "px " + pixelValue + "px"});	
	});	
	
	$("#nextBottomLeftRadius${id}").keyup(function() {	
		var pixelValue = $(this).val();
		$("#paginationDimensionsExample${id}").css({"border-bottom-left-radius":pixelValue + "px " + pixelValue + "px"});
		$("#paginationDimensionsExample${id}").css({"-moz-border-bottom-left-radius":pixelValue + "px " + pixelValue + "px"});
	});	
	
	$("#nextTopRightRadius${id}").keyup(function() {	
		var pixelValue = $(this).val();
		$("#paginationDimensionsExample${id}").css({"border-top-right-radius":pixelValue + "px " + pixelValue + "px"});
		$("#paginationDimensionsExample${id}").css({"-moz-border-top-right-radius":pixelValue + "px " + pixelValue + "px"});	
	});	
	
	$("#nextBottomRightRadius${id}").keyup(function() {
		var pixelValue = $(this).val();
		$("#paginationDimensionsExample${id}").css({"border-bottom-right-radius":pixelValue + "px " + pixelValue + "px"});
		$("#paginationDimensionsExample${id}").css({"-moz-border-bottom-right-radius":pixelValue + "px " + pixelValue + "px"});
	});		

	$("#nextText${id}").keyup(function() {
		var newText = $(this).val();
		$("#paginationDimensionsExample${id}").html(newText);
	});	
	
	$("#portletBorderPixelTop${id}").keyup(function() {
		var newPixel = $(this).val();
		$("#portletCornerRadii${id}").css({"border-top-width":newPixel+"px"});
	});	
	
	$("#portletBorderPixelRight${id}").keyup(function() {
		var newPixel = $(this).val();
		$("#portletCornerRadii${id}").css({"border-right-width":newPixel+"px"});
	});	
	
	$("#portletBorderPixelBottom${id}").keyup(function() {
		var newPixel = $(this).val();
		$("#portletCornerRadii${id}").css({"border-bottom-width":newPixel+"px"});
	});	
	
	$("#portletBorderPixelLeft${id}").keyup(function() {
		var newPixel = $(this).val();
		$("#portletCornerRadii${id}").css({"border-left-width":newPixel+"px"});
	});	
	
	$("#pageMode${id}").change(function() {
		var value = $(this).val();
		if ( value == "current") {
			$("#pageModeInput${id}").show();
			$("#pageModeLabel${id}").show();
		} else {
			$("#pageModeInput${id}").hide();
			$("#pageModeLabel${id}").hide();
		}
	});	
	
	$("#configurationOptions${id}").change(function() {		

		var thisSelected = $(this).val();		

		$(".introSection${id}, .channelSection${id}, .filterSection${id}, .optionsSection${id}, .paginationSection${id}, .portletSection${id}, .titleSection${id}, .moreLess${id}, .supplementSection${id}, .layoutSection${id}, .customEditor${id}").hide();	
		$("#contentListMoreLess${id}").html("[More]");
		
		if ( ("${uiLayout}" == "custom" && thisSelected == "layoutSection${id}") || ($("#uiLayout${id}").val() == "custom" && thisSelected == "layoutSection${id}") ) {
			$(".customEditor${id}").show();
		}
				
		$("." + thisSelected).show();
	});	
	
	var pageMode${id} = "${pageMode}";
	if ( pageMode${id} == "current") {
		$("#pageModeInput${id}").show();
		$("#pageModeLabel${id}").show();
	}
	
	function randomString(stringLength) {
		var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
		var rString = '';
		for (var i = 0; i < stringLength; i++) {
			var rNum = Math.floor(Math.random() * chars.length);
			rString += chars.substring(rNum,rNum+1);
		}
		return rString;
	}	

	$("#topNavBackButton${id}").click(function() {
		$("#configureForm${id}").attr("action", "${viewAction}");
		$("#configureForm${id}").submit();
	});		
	
	<c:if test="${showIntro == 'ON' || showIntro == 'on'}">
		$(".introSection${id}").show();
	</c:if>
	<c:if test="${showIntro == 'OFF' || showIntro == 'off'}">
		$("#configurationOptions${id}").val("channelSection${id}");
		$(".channelSection${id}").show();
	</c:if>
	
});
</script>