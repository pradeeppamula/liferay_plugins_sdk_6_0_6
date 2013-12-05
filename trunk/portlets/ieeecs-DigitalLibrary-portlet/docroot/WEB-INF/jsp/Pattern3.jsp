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
	<a href="${bc_proceedingAlphaNumUrl}" title="${proceedingLabel}: ${bc_proceedingAlphaNum}">${bc_proceedingAlphaNum}</a> >  
	<a href="${bc_proceedingAbbrevUrl}" title="${bc_proceedingAbbrev}">${bc_proceedingAbbrev}</a>
</div>
<div class="breadCrumbsPageInformation"><i class="icon-cog pageInformationIcon"></i></div>
<div class="clearBoth"></div>

<div class="sectionalHeaderAbstractProceedingsConferenceName">${issueBean.title} ${issueBean.subTitle}</div>

<div class="sectionalHeaderAbstract">	
	<div class="sectionalHeaderAbstractProceedingsTitleText"><h2>${abstractContent.title}</h2></div>	
	<div class="sectionalHeaderAbstractProceedingsText">${issueBean.metadataMap.location}  </div>
	<div class="sectionalHeaderAbstractProceedingsText">${issueBean.metadataMap.conferenceStartDate} to ${issueBean.metadataMap.conferenceEndDate}</div>
	<div class="sectionalHeaderAbstractProceedingsText">ISBN:  ${issueBean.metadataMap.isbn}  </div>	
	<div class="sectionalHeaderAbstractProceedingsText">pp:  ${abstractContent.length}</div>	
	<c:forEach var="creator" items="${abstractContent.creatorList}" varStatus="creatorIndex">
		<div class="sectionalHeaderAbstractCreator">${creator.givenName} ${creator.surname}<c:if test="${creator.sponsor != ''}">, ${creator.sponsor}</c:if></div>
	</c:forEach>
	<div class="sectionalHeaderAbstractDOI">DOI Bookmark:  <a href="${doiUrlPrefix}${abstractContent.doi}" target="_blank">${doiUrlPrefix}${abstractContent.doi}</a></div>
	<div class="sectionalHeaderAbstractSearch"><%--SEARCH WILL GO HERE --%></div>
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
			title: 'Pattern #3', 
			content: "Loaded in ${totalTimeMS}"
		});			
		
	});		

</script>

</c:if>