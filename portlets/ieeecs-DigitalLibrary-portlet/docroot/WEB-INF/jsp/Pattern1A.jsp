<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<%--
<portlet:resourceURL var="getIssues" id="getIssues" />
--%>

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
	${bc_proceedingAlphaNum}
</div>
<div class="breadCrumbsPageInformation"><i class="icon-cog pageInformationIcon"></i></div>
<div class="clearBoth"></div>

<div id="digitalLibraryContainer">
	<div id="digitalLibraryInnerContainer">
	
		<div class="proceedingListHeader">
			<div class="proceedingListAcronymLabel">Acronym</div>
			<div class="proceedingListConferenceNameLabel">Conference Name</div>
			<div class="clearBoth"></div>
		</div>	

		<c:forEach var="proceeding" items="${proceedingsMap}" varStatus="proceedingsIndex">		
		<div class="proceedingListItem ${proceedingsIndex.index % 2 == 0 ? 'proceedingListItemEven' : 'proceedingListItemOdd'}" id="proceedingListItem_${proceedingsIndex.index}">
			<div class="proceedingListAcronym"><p><a href="${csdlContextPathPrefix}/proceedings/${proceeding.value.metadataMap.idprefix}/${csdlIndexPage}" id="proceedingListItem_${proceedingsIndex.index}_link">${proceeding.key}</a></p></div>
			<div class="proceedingListConferenceName"><p>${proceeding.value.title}</p></div>
			<div class="clearBoth"></div>		
		</div>			
		</c:forEach>			

	</div>
</div>

<script>

	$(document).ready(function() {

		$(".proceedingListItem").click(function(e) {
			var thisId = $(this).attr("id");
			window.location = $("#" + thisId + "_link").attr("href");
		});
		
		$(".breadCrumbsPageInformation").popover({
			placement:'left',
			title: 'Pattern #1A', 
			content: "Loaded in ${totalTimeMS}"
		});		
		
	});		

</script>

</c:if>