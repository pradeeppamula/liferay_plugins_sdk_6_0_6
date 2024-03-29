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
	<a href="${bc_publicationUrl}" title="${bc_publicationName}">${bc_publicationName}</a> > 
	${bc_volumeName}<br/>	
</div>
<div class="breadCrumbsPageInformation"><i class="icon-cog pageInformationIcon"></i></div>
<div class="clearBoth"></div>

<div class="sectionalHeaderVolumeIssue">
	<div class="sectionalHeaderVolumeIssueCover"><img src="/ieeecs-DigitalLibrary-portlet/images/mco20130400c1.jpg"/></div>
	<div class="sectionalHeaderVolumeIssueLogo"><img src="/ieeecs-DigitalLibrary-portlet/images/${idPrefix}_green.png" /></div>
	<div class="clearBoth"></div>
	<div class="sectionalHeaderVolumeIssueLabel">${volumeAndIssueLabel}</div>
	<div class="sectionalHeaderVolumeIssueSearch"><%--SEARCH WILL GO HERE --%>
	<form id="journalSearchForm" target="_blank" action="/portal/web/search/simple" method="get">
		<div class="sectionalHeaderVolumeIssueSearchFRM">
			
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
		<div class="sectionalHeaderVolumeIssueSearchBtn">
			<input type="image" src="/ieeecs-DigitalLibrary-portlet/images/CSDL_SearchIcon.png">
		</div>
	</form>
	</div>
	<div class="sectionalHeaderVolumeIssueSocial">
		<div class="sectionalHeaderVolumeIssueSocialRSS"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_RSSIcon.png"/></div>
		<div class="sectionalHeaderVolumeIssueSocialShare">Share</div>
		<div class="sectionalHeaderVolumeIssueSocialSubscribe">Subscribe</div>
	</div>
</div>

<div id="digitalLibraryContainer">
	<div id="digitalLibraryInnerContainer">
	
		<div class="volumeSpecific">
			<i class="icon-sign-blank volumeIcon"></i> ${bc_volumeName}
		</div>	
	
		<c:forEach var="issue" items="${issueMap}" varStatus="issueIndex">
			<div class="issue">	
				<div class="issueNumberLabel"><a href="${issue.value.url}" title="${issueNumberLabel}${issue.value.issueNumber}" style="text-decoration: none;">${issueNumberLabel}${issue.value.issueNumber} - </a></div>
				<div class="issuePeriod"><a href="${issue.value.url}" title="${issue.value.title}" style="text-decoration: none;">${issue.value.title}</a></div>
				<div class="clearBoth"></div>
			</div>
		</c:forEach>
		<div class="clearBoth"></div>

	</div>
</div>

<script>

	$(document).ready(function() {
		
		$(".breadCrumbsPageInformation").popover({
			placement:'left',
			title: 'Pattern #7', 
			content: "Loaded in ${totalTimeMS}"
		});			
		
	});		

</script>

</c:if>