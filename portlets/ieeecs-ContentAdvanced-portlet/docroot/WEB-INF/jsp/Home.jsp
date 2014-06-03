<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">

	#contentAdvancedContainer${id} {
		height: ${portletHeight}px;
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

	<c:if test="${cssBlock != ''}">
	${cssBlock}
	</c:if>

</style>

<c:if test="${fallbackJS}">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
</c:if>

<div id="contentAdvancedContainer${id}">

	<c:if test="${jsBlockExternalPre != ''}">
	${jsBlockExternalPre}
	</c:if>
	
	<c:if test="${jsBlockInternalPre != ''}">
	<script language="Javascript">
		${jsBlockInternalPre}
	</script>
	</c:if>	
	
	<c:if test="${htmlBlock != ''}">
	${htmlBlock}
	</c:if>
	
	<c:if test="${jsBlockExternalPost != ''}">
	${jsBlockExternalPost}
	</c:if>
			
	<c:if test="${jsBlockInternalPost != ''}">
	<script language="Javascript">	
		${jsBlockInternalPost}  
	</script>	
	</c:if>
	
</div>

</c:if>