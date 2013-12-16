<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">

	#digitalLibraryInnerContainer {
		margin: ${innerMargins};
	}

	#digitalLibraryContainer {
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
 
	 [class^="icon-"], 
	 [class*=" icon-"] {
	    background-position: 18px 18px;
	    background-repeat: no-repeat;
	    display: inline-block;
	    height: 18px;
	    line-height: 10px;
	    margin-top: 1px;
	    vertical-align: text-top;
	    width: 18px;
	}

</style>

<div class="breadCrumbsContainer">
	<a href="${csdlContextPathPrefix}" title="${csdlHomeLabel}">${csdlHomeLabel}</a> >
	<a href="${bc_proceedingAlphaNumUrl}" title="${proceedingLabel}: ${bc_proceedingAlphaNum}">${bc_proceedingAlphaNum}</a> >  
	<a href="${bc_proceedingAbbrevUrl}" title="${bc_proceedingAbbrev}">${bc_proceedingAbbrev}</a>	
</div>
<div class="breadCrumbsPageInformation"><i class="icon-cog pageInformationIcon"></i></div>
<div class="clearBoth"></div>

<div class="sectionalHeaderTableOfContents">
	<div class="sectionalHeaderTableOfContentsProceedingsHeader">${issueBean.title} ${issueBean.subTitle}</div>
	<div class="sectionalHeaderTableOfContentsProceedingsText">${issueBean.metadataMap.location}  </div>
	<div class="sectionalHeaderTableOfContentsProceedingsText">${issueBean.metadataMap.conferenceStartDate} to ${issueBean.metadataMap.conferenceEndDate}</div>
	<div class="sectionalHeaderTableOfContentsProceedingsText">ISBN:  ${issueBean.metadataMap.isbn}  </div>	
	<div class="sectionalHeaderTableOfContentsProceedingsLabel">${tableOfContentsLabel}</div>
	

	<div class="sectionalHeaderTableOfContentsSearch"><%--SEARCH WILL GO HERE --%>
	
	<form id="journalSearchForm" target="_blank" action="/portal/web/search/simple" method="get">
		<div class="sectionalHeaderTableOfContentsSearchFRM">
			
					<input type="hidden" value="simplesearch" name="action"/>
					<input type="hidden" value="7777" name="yearTo"/>
					<input type="hidden" value="1960" name="yearFrom"/>
					<input type="hidden" value="12"	name="monthTo"/>
					<input type="hidden" value="01"	name="monthFrom"/>
					<input type="hidden" value="DocWeight"	name="sortField"/> 
					<input type="hidden" value="descending"	name="sortOrder"/>
					<input type="hidden" value="yes" name="checkAbstract"/>
					<input type="hidden" value="${searchDatabases}" name="searchDatabases"/>
					<input type="text" maxlength="256"	 value="" name="queryText1"/>											
		</div>
		<div class="sectionalHeaderTableOfContentsSearchBtn">
			<input type="image" src="/ieeecs-DigitalLibrary-portlet/images/CSDL_SearchIcon.png">
		</div>
		</form>
	</div>
	<div class="sectionalHeaderTableOfContentsSocial">
		<div class="sectionalHeaderTableOfContentsSocialRSS"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_RSSIcon.png"/></div>
		<div class="sectionalHeaderTableOfContentsSocialShare">Share</div>
		<div class="sectionalHeaderTableOfContentsSocialSubscribe">Subscribe</div>
	</div>
</div>

<div id="digitalLibraryContainer">
	<div id="digitalLibraryInnerContainer">
	
	<c:set var="lastCategoryTItle" value=""/>
	<c:forEach var="contentItem" items="${tableOfContentsList}" varStatus="contentItemIndex">
		
		<c:choose>
			<c:when test="${lastCategoryTItle != contentItem.categoryTitle }">
				
				<c:choose>
					<c:when test="${contentItemIndex.index == 0}">
						<div class="tableOfContentsHeading tableOfContentsHeadingFirst">
					</c:when>
					<c:otherwise>
						<div class="tableOfContentsHeading">
					</c:otherwise>
				</c:choose>				
				
					<div class="tableOfContentsHeadingIcon"><i class="icon-sign-blank"></i></div> 
					<div class="tableOfContentsHeadingTitle">${contentItem.categoryTitle}</div>
					<div class="clearBoth"></div>
				</div>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		
		<div class="tableOfContentsLineItemContainer">
		
			<div class="tableOfContentsLineItem">
				<div class="tableOfContentsLineItemTitle">
					<a href="${proceedingUrlPrefix}/${contentItem.filename}/abs/htm" title="${contentItem.title}">${contentItem.title}</a>
					<c:choose>
						<c:when test="${contentItem.price == '0'}">
							<span class="lineItemType">(HTML)</span>
						</c:when>
						<c:otherwise>
							<span class="lineItemType">(Abstract)</span>
						</c:otherwise>
					</c:choose>
				</div>				
				<c:forEach var="creator" items="${contentItem.creatorList}" varStatus="creatorIndex">
					<div class="tableOfContentsLineItemCreator">${creator.givenName} ${creator.surname}<c:if test="${creator.sponsor != ''}">, ${creator.sponsor}</c:if></div>
				</c:forEach>
				<div class="tableOfContentsLineItemLength">pp. ${contentItem.length}</div>
			</div>
			
			<div class="tableOfContentsLineOptions">			
				<ul class="nav nav-pills">
					<li class="tableOfContentsLineOptionsAbstract" id="tableOfContentsLineOptionsAbstract_${contentItemIndex.index}">
						<div class="tableOfContentsLineOptionsAbstractIcon"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_AbstractIcon.png"/></div>
						<div class="tableOfContentsLineOptionsAbstractText"><a href="${proceedingUrlPrefix}/${contentItem.filename}/abs/htm" id="tableOfContentsLineOptionsAbstract_${contentItemIndex.index}_abstract">${buttonAbstract}</a></div>
						<div class="clearBoth"></div>
					</li>
					<li class="dropdown tableOfContentsLineOptionsFullText" id="tableOfContentsLineOptionsFullText_${contentItemIndex.index}">
						<div class="tableOfContentsLineOptionsFullTextIcon"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_FullTextIcon.png"/></div>
						<div class="tableOfContentsLineOptionsFullTextText">
							<a class="dropdown-toggle" id="fullTextDropdown_${contentItemIndex.index}" role="button" data-toggle="dropdown" href="javascript: empty()">${buttonFullText}</b></a>
							<ul id="tableOfContentsLineOptionsFullTextMenu_${contentItemIndex.index}" class="dropdown-menu" role="menu" aria-labelledby="fullTextDropdown_${contentItemIndex.index}">
								<li class="tableOfContentsLineOption">
									<div class="tableOfContentsLineOptionsPDFIcon"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_PDFIcon.png"/></div>
									<div class="tableOfContentsLineOptionsPDFText">${buttonPDF} <span id="pdfSize_${contentItemIndex.index}">(30KB)</span></div>
									<div class="clearBoth"></div>								
								</li>
								<li class="tableOfContentsLineOption">
									<div class="tableOfContentsLineOptionsHTMLIcon"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_HTMLIcon.png"/></div>
									<div class="tableOfContentsLineOptionsHTMLText">${buttonHTML}</div>
									<div class="clearBoth"></div>								
								</li>
								<li class="tableOfContentsLineOption">
									<div class="tableOfContentsLineOptionsXPLOREIcon"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_XploreIcon.png"/></div>
									<div class="tableOfContentsLineOptionsXPLOREText">${buttonXPLORE}</div>
									<div class="clearBoth"></div>								
								</li>
								<li class="tableOfContentsLineOption">
									<div class="tableOfContentsLineOptionsOPENURLIcon"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_OpenURLIcon.png"/></div>
									<div class="tableOfContentsLineOptionsOPENURLText">${buttonOpenUrl}</div>
									<div class="clearBoth"></div>								
								</li>																
							</ul>							
						</div>
					</li>
					<li class="tableOfContentsLineOptionsPurchase">
						<div class="tableOfContentsLineOptionsPurchaseIcon"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_BuyIcon.png"/></div>
						<div class="tableOfContentsLineOptionsPurchaseText">${buttonBuy}</div>
						<div class="clearBoth"></div>				
					</li>
				</ul>			
			</div>

		</div>

		<c:set var="lastCategoryTItle" value="${contentItem.categoryTitle}"/>
	</c:forEach>
	
	</div>
</div>

<script>

	$(document).ready(function() {
		
		$(".tableOfContentsLineOptionsFullText").click(function(e) {
			e.stopPropagation();
			var thisId = $(this).attr("id");
			var thisIndex = thisId.replace("tableOfContentsLineOptionsFullText_", "");						
			$("#fullTextDropdown_" + thisIndex + ".dropdown-toggle").dropdown("toggle");
		});
		
		$(".tableOfContentsLineOptionsAbstract").click(function(e) {
			var thisId = $(this).attr("id");
			window.location = $("#" + thisId + "_abstract").attr("href");
		});
		
		$(".breadCrumbsPageInformation").popover({
			placement:'left',
			title: 'Pattern #2', 
			content: "Loaded in ${totalTimeMS}"
		});			

	});	
	
	function empty() {}

</script>

</c:if>