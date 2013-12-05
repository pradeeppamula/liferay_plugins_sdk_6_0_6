<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">

	#communityPollInnerContainer${id} {
		margin: ${innerMargins};
	}

	#communityPollContainer${id} {
		border-top: ${portletBorderPixelTop}px solid #${portletBorderColorTop};
		border-right: ${portletBorderPixelRight}px solid #${portletBorderColorRight};
		border-bottom: ${portletBorderPixelBottom}px solid #${portletBorderColorBottom};
		border-left: ${portletBorderPixelLeft}px solid #${portletBorderColorLeft};		
		border-top-left-radius: ${portletTopLeftRadius}px ${portletTopLeftRadius}px;
		-moz-border-top-left-radius: ${portletTopLeftRadius}px ${portletTopLeftRadius}px;
		border-top-right-radius: ${portletTopRightRadius}px ${portletTopRightRadius}px;		
		-moz-border-top-right-radius: ${portletTopRightRadius}px ${portletTopRightRadius}px;		
		border-bottom-left-radius: ${portletBottomLeftRadius}px ${portletBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${portletBottomLeftRadius}px ${portletBottomLeftRadius}px;
		border-bottom-right-radius: ${portletBottomRightRadius}px ${portletBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${portletBottomRightRadius}px ${portletBottomRightRadius}px;			
	}	
	
	#communityPollContainer${id} {	
		background-color: #${portletBackgroundColor};	
	}
	
<c:if test="${canInlineEdit}">
	#editorModal${id} {
		background-color: #FFFFFF;	
	} 	
</c:if>
</style>

<div id="communityPollContainer${id}">
<c:if test="${canInlineEdit}">
	<div id="editContent${id}" class="editContent"></div>
</c:if>	
	<div id="communityPollInnerContainer${id}">
	This is the Question?<br/>
	Choice #1<br/>
	Choice #2<br/>
	Choice #3<br/>
	Choice #4<br/>
	</div>
</div>

<c:if test="${canInlineEdit}">
<div id="editorModal${id}" class="editorModal">
	<div class="editorModalSave" id="editorModalSave${id}">Save</div>	
	<div class="editorModalCancel" id="editorModalCancel${id}">Cancel</div>	
	<div class="clearBoth"></div>
	<div>
		Question <input type="text"/><br/>
		Choice #1 <input type="radio"/><br/>
		Choice #2 <input type="radio"/><br/>
		Choice #3 <input type="radio"/><br/>
		Choice #4 <input type="radio"/><br/>
	</div>		
</div>	
</c:if>

<script>
<c:if test="${canInlineEdit}">
	$(document).ready(function() {		
		
		$("#editContent${id}").click(function() {	
			$(this).hide();	
			$("#editorModal${id}").fadeIn(500);
		});
		
		$("#editorModalCancel${id}").click(function() {				
			$("#editContent${id}").show();
			$("#editorModal${id}").fadeOut(500);
		});		
	
		$("#editorModalSave${id}").click(function() {			
			
		});			
		
	});	
</c:if>

</script>

</c:if>
