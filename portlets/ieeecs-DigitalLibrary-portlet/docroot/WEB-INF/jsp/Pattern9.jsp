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

</style>

<div class="breadCrumbsContainer">
	<a href="${csdlContextPathPrefix}" title="${csdlHomeLabel}">${csdlHomeLabel}</a> > 
	<a href="${bc_publicationUrl}" title="${bc_publicationName}">${bc_publicationName}</a> > 
	<a href="${bc_volumeUrl}" title="${bc_volumeName}">${bc_volumeName}</a> >
	<a href="${bc_issueUrl}" title="${bc_issueName} ${issueBean.title}">${bc_issueName} ${issueBean.title}</a><br/>	
</div>
<div class="breadCrumbsPageInformation"><i class="icon-cog pageInformationIcon"></i></div>
<div class="clearBoth"></div>

<div class="sectionalHeaderAbstract">
	<div class="sectionalHeaderAbstractLogo"><img src="/ieeecs-DigitalLibrary-portlet/images/${idPrefix}_green.png" /></div>
	<div class="sectionalHeaderAbstractText"><h2>${abstractContent.title}</h2></div>
	<div class="sectionalHeaderAbstractText">${issueBean.title} ${issueBean.subTitle}</div>
	<div class="sectionalHeaderAbstractText">pp:  ${abstractContent.length}</div>	
	<c:forEach var="creator" items="${abstractContent.creatorList}" varStatus="creatorIndex">
		<div class="sectionalHeaderAbstractCreator">${creator.givenName} ${creator.surname}<c:if test="${creator.sponsor != ''}">, ${creator.sponsor}</c:if></div>
	</c:forEach>
	<div class="sectionalHeaderAbstractDOI">DOI Bookmark:  <a href="${doiUrlPrefix}${abstractContent.doi}" target="_blank">${doiUrlPrefix}${abstractContent.doi}</a></div>
	<div class="sectionalHeaderAbstractSearch"><%--SEARCH WILL GO HERE --%>
	<form id="journalSearchForm" target="_blank" action="/portal/web/search/simple" method="get">
		<div class="sectionalHeaderAbstractSearchFRM">
			
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
		<div class="sectionalHeaderAbstractSearchBtn">
			<input type="image" src="/ieeecs-DigitalLibrary-portlet/images/CSDL_SearchIcon.png">
		</div>
	</form>
	</div>
	<div class="sectionalHeaderAbstractSocial">
		<div class="sectionalHeaderAbstractSocialRSS"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_RSSIcon.png"/></div>
		<div class="sectionalHeaderAbstractSocialShare">Share</div>
		<div class="sectionalHeaderAbstractSocialSubscribe">Subscribe</div>
	</div>
</div>

<div id="digitalLibraryContainer">
	<div id="digitalLibraryInnerContainer">
	
	<c:if test="${abstractContent.summary != ''}">
		<div class="abstractHeader">ABSTRACT</div>
		<div class="abstractText">${abstractContent.summary}</div>	
	</c:if>
	
	<c:forEach var="reference" items="${abstractContent.referenceMap}" varStatus="referenceIndex">
		<c:if test="${referenceIndex.index == 0}">
			<div class="abstractHeader">REFERENCES</div>
		</c:if>
		<div class="abstractReference">${reference.value.display}</div>	  
	</c:forEach>
	
	<c:if test="${abstractContent.keywords != ''}">
		<div class="abstractHeader">INDEX TERMS</div>
		<div class="abstractText">${abstractContent.keywords}</div>	
	</c:if>	

		<div class="abstractHeader">CITATION</div>
		<div class="abstractText">
		<c:forEach var="creator" items="${abstractContent.creatorList}" varStatus="creatorIndex">
			${not creatorIndex.first && creatorIndex.last ? ' and ' : ''}${creator.givenName} ${creator.surname},  
		</c:forEach>		
		"${abstractContent.title}", <i>${abstractContent.subTitle}</i>, ${volumeAbbrev} ${issueBean.metadataMap.volumeNumber}, ${issueAbbrev} ${issueBean.metadataMap.issueNumber},
		${issueBean.title} ${bc_volumeName}, pp. ${abstractContent.length}, doi:${abstractContent.doi}
		</div>
	</div>
</div>

<script>

	$(document).ready(function() {
		
		$(".breadCrumbsPageInformation").popover({
			placement:'left',
			title: 'Pattern #9', 
			content: "Loaded in ${totalTimeMS}"
		});			
		
	});		

</script>

</c:if>