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

<div class="sectionalHeaderHomePublications">
	<div class="sectionalHeaderHomePublicationsLabel">${csdlHeaderTitle}</div>
	<div class="sectionalHeaderHomePublicationsCover"><img src="${csdlHeaderIntroductionImagePath}"/></div>
	<div class="sectionalHeaderHomePublicationsIntroText">
		<div>${csdlHeaderIntroduction}</div>		
		<div class="sectionalHeaderHomePublicationsSocialSubscribe">Subscribe</div>
		<div class="sectionalHeaderHomePublicationsSocialSubscribeText">${subscriptionBlurb}</div>
	</div>
	<div class="clearBoth"></div>
</div>

<div class="mainSearchContainer">
	<div class="mainSearchLabel">${csdlSearchTitle}</div>	
	<div class="mainSearch">
		<div class="mainSearchControls">
		<%--SEARCH WILL GO HERE --%>
		</div>
		<div class="mainSearchButtonContainer">
			<div class="mainSearchButton">
				<img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_SearchIcon.png"/>
			</div>
		</div>
		<div class="clearBoth"></div>
	</div>
</div>

<div id="digitalLibraryContainer">
	<div id="digitalLibraryInnerContainer">
	
		<div class="magazineListing">
			<div class="magazineListingHeader">
				<div class="magazineListingImage"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_MagazinesIcon.png" width="20" height="25"/></div>
				<div class=magazineListingTitle>${magazineLabel}</div>
				<div class="clearBoth"></div>
			</div>
			<div class="magazineListingDescription">${magazinesDescription}</div>
			<c:forEach var="magazineItem" items="${magazineMap}" varStatus="magazineIndex">	
			
				<c:choose>
					<c:when test="${magazineIndex.index == 0}">
						<div class="linkItem linkItemTop">
					</c:when>
					<c:otherwise>
						<div class="linkItem">
					</c:otherwise>
				</c:choose>			
	
					<div class="linkItemBullet"><i class="icon-sign-blank publicationIcon"></i></div>
					<div class="linkItemAnchor"><a href="${magazineItem.value.url}" title="${magazineItem.value.display}">${magazineItem.value.display}</a></div>	
					<div class="clearBoth"></div>	
				</div>
			</c:forEach>		
		</div>
		
		<div class="transactionListing">
			<div class="transactionListingHeader">
				<div class="transactionListingImage"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_TransactionsIcon.png" width="30" height="25"/></div>
				<div class=transactionListingTitle>${transactionLabel}</div>
				<div class="clearBoth"></div>
			</div>		
			<div class="transactionListingDescription">${transactionsDescription}</div>
			<c:forEach var="transactionItem" items="${transactionMap}" varStatus="transactionIndex">	
			
				<c:choose>
					<c:when test="${transactionIndex.index == 0}">
						<div class="linkItemFull linkItemTop">
					</c:when>
					<c:otherwise>
						<div class="linkItemFull">
					</c:otherwise>
				</c:choose>
				
					<div class="linkItemBullet"><i class="icon-sign-blank publicationIcon"></i></div>
					<div class="linkItemAnchor"><a href="${transactionItem.value.url}" title="${transactionItem.value.display}">${transactionItem.value.display}</a></div>	
					<div class="clearBoth"></div>				
				</div>
			</c:forEach>		
		</div>		
	
		<div class="clearBoth"></div>	
	
		<div class="letterListing">
			<div class="letterListingHeader">
				<div class="letterListingImage"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_LettersIcon.png" width="27" height="25"/></div>
				<div class=letterListingTitle>${letterLabel}</div>
				<div class="clearBoth"></div>
			</div>			
			<div class="letterListingDescription">${lettersDescription}</div>
			<c:forEach var="letterItem" items="${letterMap}" varStatus="letterIndex">	
			
				<c:choose>
					<c:when test="${letterIndex.index == 0}">
						<div class="linkItemFull linkItemTop">
					</c:when>
					<c:otherwise>
						<div class="linkItemFull">
					</c:otherwise>
				</c:choose>			
	
					<div class="linkItemBullet"><i class="icon-sign-blank publicationIcon"></i></div>
					<div class="linkItemAnchor"><a href="${letterItem.value.url}" title="${letterItem.value.display}">${letterItem.value.display}</a></div>	
					<div class="clearBoth"></div>	
				</div>
			</c:forEach>		
		</div>	
		
		<div class="proceedingListing">
			<div class="proceedingListingHeader">
				<div class="proceedingListingImage"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_ProceedingsIcon.png" width="24" height="25"/></div>
				<div class=proceedingListingTitle>${proceedingLabel}</div>
				<div class="clearBoth"></div>
			</div>
			<div class="proceedingListingDescription">${proceedingsDescription}</div>	
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/a/${csdlListPage}">A</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/b/${csdlListPage}">B</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/c/${csdlListPage}">C</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/d/${csdlListPage}">D</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/e/${csdlListPage}">E</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/f/${csdlListPage}">F</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/g/${csdlListPage}">G</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/h/${csdlListPage}">H</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/i/${csdlListPage}">I</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/j/${csdlListPage}">J</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/k/${csdlListPage}">K</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/l/${csdlListPage}">L</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/m/${csdlListPage}">M</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/n/${csdlListPage}">N</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/o/${csdlListPage}">O</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/p/${csdlListPage}">P</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/q/${csdlListPage}">Q</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/r/${csdlListPage}">R</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/s/${csdlListPage}">S</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/t/${csdlListPage}">T</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/u/${csdlListPage}">U</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/v/${csdlListPage}">V</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/w/${csdlListPage}">W</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/x/${csdlListPage}">X</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/y/${csdlListPage}">Y</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/z/${csdlListPage}">Z</a></div>
			<div class="proceedingLink"><a href="${csdlContextPathPrefix}/${proceedingsUrlSegment}/0-9/${csdlListPage}">0-9</a></div>												
			<div class="clearBoth"></div>
		</div>			

		<div class="resourceListing">
			<div class="resourceListingHeader">
				<div class="resourceListingImage"><img src="/ieeecs-DigitalLibrary-portlet/images/CSDL_ResourcesIcon.png" width="20" height="25"/></div>
				<div class=resourceListingTitle>${resourceLabel}</div>
				<div class="clearBoth"></div>
			</div>			
			<div class=resourceListingContent>${resourcesSection}</div>
		</div>		

	</div>
</div>

<script>

	$(document).ready(function() {
		
		$(".headerSlider").slideDown(${homePageFeaturedSliderDelay});
		
		$(".proceedingLink").click(function(e) {
			window.location = $(this).children("a").attr("href");
		});	
		
		$(".linkItem, .linkItemFull").hover(
				function () {
					$(".publicationIcon").css("color", "#CCCCCC");
					$(this).find("i").css("color", "#0A6BA7");
				},
				function () {
					$(".publicationIcon").css("color", "#CCCCCC");
				}
		);				
		
	});		

</script>

</c:if>