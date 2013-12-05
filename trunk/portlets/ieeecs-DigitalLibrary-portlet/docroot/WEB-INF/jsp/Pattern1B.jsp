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
	${bc_proceedingAbbrev}
</div>
<div class="breadCrumbsPageInformation"><i class="icon-cog pageInformationIcon"></i></div>
<div class="clearBoth"></div>

<div id="digitalLibraryContainer">
	<div id="digitalLibraryInnerContainer">
	
		<div class="proceedingYearConference">
			<div class="proceedingYearConferenceDescription"><p>${productBean.title}</p></div>
		</div>				

		<c:forEach var="proceedingYear" items="${proceedingsYearMap}" varStatus="proceedingsYearIndex">		
		
			<div class="proceedingYear">
				<div class="proceedingYearText">${proceedingYear.key}</div>
			</div>

			<c:forEach var="proceedingIssue" items="${proceedingYear.value}" varStatus="proceedingsYearIssueIndex">				
			<div class="proceedingName">
				<div class="proceedingNameIcon"><i class="icon-sign-blank volumeIcon"></i></div>
				<div class="proceedingNameTitle"><a href="${proceedingIssue.url}" title="${proceedingIssue.title}">${proceedingIssue.title}</a></div>
				<div class="clearBoth"></div>
			</div>			
			</c:forEach>
		
		</c:forEach>			

	</div>
</div>

<script>

	$(document).ready(function() {

		$(".proceedingName").hover(
			function () {
				$(".volumeIcon").css("color", "#CCCCCC");
				$(this).find("i").css("color", "#0A6BA7");
			},
			function () {
				$(".volumeIcon").css("color", "#CCCCCC");
			}
		);		
		
		$(".breadCrumbsPageInformation").popover({
			placement:'left',
			title: 'Pattern #1B', 
			content: "Loaded in ${totalTimeMS}"
		});			
		
	});		

</script>

</c:if>