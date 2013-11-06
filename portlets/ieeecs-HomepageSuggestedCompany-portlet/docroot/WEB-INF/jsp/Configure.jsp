<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<portlet:actionURL var="configureAction" portletMode="edit">
	<portlet:param name="action" value="homepageSuggestedCompanyConfigure"/>	
</portlet:actionURL>

<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>

<style type="text/css">

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
	
	.customConfigRow${id} {
		width: 95%;
		height: 600px;
		display: none;		
	}	
	
	.portletConfigRow${id} {
		width: 98%;
		border-top: 1px solid #CCCCCC;
		background-color: #E8E8E8;
		display: none;
		margin: 0 0 0 10px;
	}

</style>


<script type="text/javascript">

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
	<input type="hidden" id="source${id}" name="source${id}" value="CONFIG"/>
	<input type="hidden" id="showIntro${id}" name="showIntro${id}" value="${showIntro}"/>

	<div class="homepageSuggestedCompanyTitle"><u>Configuration for this HomepageSuggestedCompany Portlet</u>:</div>
	<div class="homepageSuggestedCompanyInstanceID">Portlet Instance ID: ${id}</div>

	<div class="homepageSuggestedCompanyMode">
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
				<option value="portletSection${id}">Portlet Configuration</option>
			</select>
		</div>
		<button class="btn pull-right topNavBackButton${id}" id="topNavBackButton${id}">Back</button>
		<div class="clearBoth"></div>
	</div>	

	<div class="customConfigIntroRow${id} introSection${id}">
		<div class="homepageSuggestedCompanyConfigIntroExplanation">
			<ul>
				<li>To be filled in later...</li>
			</ul>
		</div>
		<div class="showIntroExplanation"><input type="checkbox" name="showIntroCheckBox${id}" id="showIntroCheckBox${id}" <c:if test="${showIntro == 'ON' || showIntro == 'on'}">checked="checked"</c:if>/> <label for="showIntroCheckBox${id}">Show this 'Intro' tab first when in 'Preferences' mode.</label></div>
	</div>

	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Content Title Category</div>
		<div class="homepageSuggestedCompanyCell">
			<input type="text" id="defaultCategoryName${id}" name="defaultCategoryName${id}" value="${defaultCategoryName}" size="20"/>
		</div>
		<div class="clearBoth"></div>
	</div>	

	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Content Title to Display</div>
		<div class="homepageSuggestedCompanyCell">
			<select name="resourcePrimKey${id}" id="resourcePrimKey${id}">
		<c:forEach var="entryItem" items="${entryMap}" varStatus="chIndex">			
				<option value="${entryItem.key}" <c:if test="${entryItem.key == resourcePrimKey}"> selected="selected" </c:if>>${entryItem.value}</option>
		</c:forEach>			
			</select>
		</div>
		<div class="clearBoth"></div>
	</div>	

	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Portlet Border - Top <a href="/ieeecs-HomepageSuggestedCompany-portlet/templates/toolTip_PortletBorderTop.html?width=300" class="jTip" id="portletBorderTopToolTip${id}" name="What is pixel size and color of the Top Border?">(?)</a></div>
		<div class="homepageSuggestedCompanyCell">
			<div class="input-append">
				<input type="text" id="portletBorderPixelTop${id}" name="portletBorderPixelTop${id}" value="${portletBorderPixelTop}" size="1"/> px&nbsp;&nbsp; 
			  	<span class="add-on">px</span>
			</div>
			<br />
			<div class="input-prepend">
			  <span class="add-on">#</span>			
			  <input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,0);'}" type="text" id="portletBorderColorTop${id}" name="portletBorderColorTop${id}" value="${portletBorderColorTop}" size="5"/>
			</div>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Portlet Border - Bottom <a href="/ieeecs-HomepageSuggestedCompany-portlet/templates/toolTip_PortletBorderBottom.html?width=300" class="jTip" id="portletBorderBottomToolTip${id}" name="What is pixel size and color of the Bottom Border?">(?)</a></div>
		<div class="homepageSuggestedCompanyCell">
			<div class="input-append">
					<input type="text" id="portletBorderPixelBottom${id}" name="portletBorderPixelBottom${id}" value="${portletBorderPixelBottom}" size="1"/> 
				  	<span class="add-on">px</span>
				</div>
				<br />
				<div class="input-prepend">
				  <span class="add-on">#</span>			
					<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,2);'}" type="text" id="portletBorderColorBottom${id}" name="portletBorderColorBottom${id}" value="${portletBorderColorBottom}" size="5"/>
				</div>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Portlet Border - Left <a href="/ieeecs-HomepageSuggestedCompany-portlet/templates/toolTip_PortletBorderLeft.html?width=300" class="jTip" id="portletBorderLeftToolTip${id}" name="What is pixel size and color of the Left Border?">(?)</a></div>
		<div class="homepageSuggestedCompanyCell">
		
			<div class="input-append">
			<input type="text" id="portletBorderPixelLeft${id}" name="portletBorderPixelLeft${id}" value="${portletBorderPixelLeft}" size="1"/> 
				  	<span class="add-on">px</span>
				</div>
				<br />
				<div class="input-prepend">
				  <span class="add-on">#</span>			
					<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,3);'}" type="text" id="portletBorderColorLeft${id}" name="portletBorderColorLeft${id}" value="${portletBorderColorLeft}" size="5"/>
				</div>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Portlet Border - Right <a href="/ieeecs-HomepageSuggestedCompany-portlet/templates/toolTip_PortletBorderRight.html?width=300" class="jTip" id="portletBorderRightToolTip${id}" name="What is pixel size and color of the Right Border?">(?)</a></div>
		<div class="homepageSuggestedCompanyCell">
		
		<div class="input-append">
			<input type="text" id="portletBorderPixelRight${id}" name="portletBorderPixelRight${id}" value="${portletBorderPixelRight}" size="1"/> 
				  	<span class="add-on">px</span>
				</div>
				<br />
				<div class="input-prepend">
				  <span class="add-on">#</span>			
			<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,1);'}" type="text" id="portletBorderColorRight${id}" name="portletBorderColorRight${id}" value="${portletBorderColorRight}" size="5"/>
				</div>
		
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Portlet - Corner Radii (px) <a href="/ieeecs-HomepageSuggestedCompany-portlet/templates/toolTip_PortletCornerRadius.html?width=300" class="jTip" id="portletCornerRadiusToolTip${id}" name="What are the Corner Radii (px) for the Portlet?">(?)</a></div>
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
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Portlet Background Color <a href="/ieeecs-HomepageSuggestedCompany-portlet/templates/toolTip_PortletBackgroundColor.html?width=300" class="jTip" id="portletBackgroundColorToolTip${id}" name="What is Portlet background color?">(?)</a></div>
		<div class="homepageSuggestedCompanyCell">
		
		<div class="input-prepend">
				  <span class="add-on">#</span>			
			<input class="color {onImmediateChange:'updatePortletBackgroundColor${id}(this);'}" type="text" id="portletBackgroundColor${id}" name="portletBackgroundColor${id}" value="${portletBackgroundColor}" size="5"/>
				</div>
		
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="homepageSuggestedCompanyCellTitle">Inner Content Margins</div>
		<div class="homepageSuggestedCompanyCell">
			<input type="text" id="innerMargins${id}" name="innerMargins${id}" value="${innerMargins}" size="25"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
		
	<div class="homepageSuggestedCompanyRowButtons">
		<div class="homepageSuggestedCompanyMessage" id="message${id}"></div>
		<div class="modal-footer">
			<button class="btn btn-primary" name="saveConfiguration${id}" id="saveConfiguration${id}">Save</button>
			<button class="btn topNavBackButton${id}" id="topNavBackButton${id}">Back</button>
		</div>
		<div class="clearBoth"></div>
	</div>	
</form>

<script src="/ieeecs-HomepageSuggestedCompany-portlet/js/jtip.js" type="text/javascript"></script>
<script src="/ieeecs-HomepageSuggestedCompany-portlet/js/jscolor/jscolor.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	
	$("#saveConfiguration${id}").click(function() {			
		$("#message${id}").html("Your Configuration changes are being saved...").fadeOut(3000);
		$("#configureForm${id}").submit();
	});
	
	$("#configurationOptions${id}").change(function() {
		var thisSelected = $(this).val();
		$(".portletSection${id}").hide();		
		$("." + thisSelected).show();
	});	
	
	$("#portletTopLeftRadius${id}, #portletTopRightRadius${id}, #portletBottomLeftRadius${id}, #portletBottomRightRadius${id}").keyup(function() {	
		
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
		
	$("#topNavBackButton${id}").click(function() {
		$("#configureForm${id}").attr("action", "${viewAction}");
		$("#configureForm${id}").submit();
	});	
	
	$("#configurationOptions${id}").val("portletSection${id}");
	$(".portletSection${id}").show();

});
</script>
