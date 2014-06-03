<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<portlet:actionURL var="configureAction" portletMode="edit">
	<portlet:param name="action" value="contentAdvancedConfigure"/>	
</portlet:actionURL>

<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>

<style type="text/css">

	/* Override "legacy" Theme settings where they set the background to grey */
	.columns-max #column-1 {
	    background: none repeat scroll 0 0 #FFFFFF;
	    border: 1px solid #FFFFFF;
	}

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
	
	.customConfigIntroRow${id} {
		width: 95%;
		display: none;		
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
	
	.css${id}_InfoBlock,
	.html${id}_InfoBlock, 
	.intPre${id}_InfoBlock,
	.intPost${id}_InfoBlock {
		width: 98%;
		height: 550px;	
		margin: 0 0 0 10px;
		background-color: #FFFFFF;
		z-index: 100;	
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
	<input type="hidden" id="cssBlock${id}" name="cssBlock${id}" value=""/>
	<input type="hidden" id="htmlBlock${id}" name="htmlBlock${id}" value=""/>
	<input type="hidden" id="jsBlockInternalPre${id}" name="jsBlockInternalPre${id}" value=""/>
	<input type="hidden" id="jsBlockInternalPost${id}" name="jsBlockInternalPost${id}" value=""/>	
	<input type="hidden" id="source${id}" name="source${id}" value="CONFIG"/>
	<input type="hidden" id="showIntro${id}" name="showIntro${id}" value="${showIntro}"/>

	<div class="contentAdvancedTitle"><u>Configuration for this Content Advanced Portlet</u>:</div>
	
	<div class="contentAdvancedInstanceID">Portlet Instance ID: ${id}</div>
	
	<div class="contentAdvancedMode">
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
				<option value="cssSection${id}">CSS Block</option>
				<option value="htmlSection${id}">HTML Block</option>
				<option value="jsSectionInternalPre${id}">Internal Javascript (Pre-HTML)</option>
				<option value="jsSectionInternalPost${id}">Internal Javascript (Post-HTML)</option>
				<option value="jsSectionExternal${id}">External Javascript (Pre and Post HTML)</option>
				<option value="portletSection${id}">Portlet Configuration</option>
				<option value="viewSource${id}">View All Code Blocks</option>
			</select>
		</div>
		<div class="topNavBackButton" id="topNavBackButton${id}">Back</div>
		<div class="clearBoth"></div>
	</div>	

	<div class="customConfigIntroRow${id} introSection${id}">
		<div class="contentAdvancedConfigIntroExplanation">
			<ul>
				<li>Please note that you'll need CSS / HTML / Javascript knowledge to use this advanced portlet.</li>
				<li>Incorrect usage of the Javascript blocks of this portlet can cause issues page-wide.</li>
				<li>Therefore, please test the usage of this advanced portlet on another "test" page before using on a live site.</li>
				<li>The code will be rendered in the following order:
					<ol>
						<li>CSS Block</li>
						<li>External Javascript : Pre-HTML</li>
						<li>Internal Javascript : Pre-HTML</li>
						<li>HTML</li>
						<li>External Javascript : Post-HTML</li>
						<li>Internal Javascript : Post-HTML</li>
					</ol>
				</li>				
			</ul>
		</div>
		<div class="showIntroExplanation"><input type="checkbox" name="showIntroCheckBox${id}" id="showIntroCheckBox${id}" <c:if test="${showIntro == 'ON' || showIntro == 'on'}">checked="checked"</c:if>/> <label for="showIntroCheckBox${id}">Show this 'Intro' tab first when in 'Preferences' mode.</label></div>
	</div>

	<div class="customConfigRow${id} cssSection${id}">
		<div class="contentAdvancedBlockInformationButton" id="css${id}_Info"></div>
		<div class="contentAdvancedConfigExplanation" id="css${id}_InfoCopy">
			<ul>
				<li>Inline CSS - The <b>&lt;style&gt;</b> and <b>&lt;/style&gt;</b> tags are not needed.</li>
				<li>The CSS in this section can be used in other portlets and page-specific HTML as well.</li>
				<li>You can alter the CSS of this portlet itself by defining attributes for "#contentAdvancedContainer" and add the "Instance ID" from above.</li>
				<li>For example:
					<ul>
						<li style="font: 10px arial, helvetica, sans-serif;">
						<pre>
#contentAdvancedContainer${id} {
    padding: 50px;
}						
						</pre>
						</li>
					</ul>
				</li>				
			</ul>
		</div>		
		<div id="editor_cssSection${id}" name="editor_cssSection${id}" class="css${id}_InfoBlock"></div>
	</div>	
	
	<div class="customConfigRow${id} htmlSection${id}">
		<div class="contentAdvancedBlockInformationButton" id="html${id}_Info"></div>
		<div class="contentAdvancedConfigExplanation" id="html${id}_InfoCopy">
			<ul>
				<li>HTML only.</li>
				<li>Javascript and CSS should be entered in their respective editors.</li>
			</ul>
		</div>	
		<div id="editor_htmlSection${id}" name="editor_htmlSection${id}" class="html${id}_InfoBlock"></div>
	</div>	
	
	<div class="customConfigRow${id} jsSectionInternalPre${id}">	
		<div class="contentAdvancedBlockInformationButton" id="intPre${id}_Info"></div>
		<div class="contentAdvancedConfigExplanation" id="intPre${id}_InfoCopy">
			<ul>
				<li>Inline JS - The <b>&lt;script&gt;</b> and <b>&lt;/script&gt;</b> tags are not needed.</li>
				<li>This javascript will render <b>before</b> the HTML.</li>
			</ul>
		</div>			
		<div id="editor_jsSectionInternalPre${id}" name="editor_jsSectionInternalPre${id}" class="intPre${id}_InfoBlock"></div>		
	</div>	
	
	<div class="customConfigRow${id} jsSectionInternalPost${id}">
		<div class="contentAdvancedBlockInformationButton" id="intPost${id}_Info"></div>	
		<div class="contentAdvancedConfigExplanation" id="intPost${id}_InfoCopy">
			<ul>
				<li>Inline JS - The <b>&lt;script&gt;</b> and <b>&lt;/script&gt;</b> tags are not needed.</li>
				<li>This javascript will render <b>after</b> the HTML.</li>
			</ul>
		</div>
		<div id="editor_jsSectionInternalPost${id}" name="editor_jsSectionInternalPost${id}" class="intPost${id}_InfoBlock"></div>		
	</div>				

	<div class="customConfigRow${id} jsSectionExternal${id}">	
		<div class="contentAdvancedConfigExternalExplanation">
			<ul>
				<li>External JS libraries.</li>
				<li>For example, you can enter an entire &lt;script&gt;...&lt;/script&gt; line:
					<ul>
						<li style="font: 9px arial, helvetica, sans-serif;">&lt;script type="text/javascript" src="http://ajax.booya.com/libs/jQbooya/1.7/jQbooya.min.js"&gt;&lt;/script&gt;</li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="contentAdvancedConfigExplanationExternal">PRE : Loaded <b>before</b> the Internal JS (Pre), HTML, and Internal JS (Post):</div>		
		<div class="contentAdvancedJSExternalBlock">
			<textarea cols="50" rows="10" id="jsBlockExternalPre${id}" name="jsBlockExternalPre${id}">${jsBlockExternalPre}</textarea>
		</div>	
		<div class="contentAdvancedConfigExplanationExternal">POST : Loaded <b>after</b> the Internal JS (Pre) and HTML, but <b>before</b> the Internal JS (Post):</div>		
		<div class="contentAdvancedJSExternalBlock">
			<textarea cols="50" rows="10" id="jsBlockExternalPost${id}" name="jsBlockExternalPost${id}">${jsBlockExternalPost}</textarea>
		</div>
	</div>

	<div class="portletConfigRow${id} portletSection${id}">
		<div class="contentAdvancedCellTitle">Portlet Border - Top <a href="/ieeecs-ContentAdvanced-portlet/templates/toolTip_PortletBorderTop.html?width=300" class="jTip" id="portletBorderTopToolTip${id}" name="What is pixel size and color of the Top Border?">(?)</a></div>
		<div class="contentAdvancedCell">
			<input type="text" id="portletBorderPixelTop${id}" name="portletBorderPixelTop${id}" value="${portletBorderPixelTop}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,0);'}" type="text" id="portletBorderColorTop${id}" name="portletBorderColorTop${id}" value="${portletBorderColorTop}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="contentAdvancedCellTitle">Portlet Border - Bottom <a href="/ieeecs-ContentAdvanced-portlet/templates/toolTip_PortletBorderBottom.html?width=300" class="jTip" id="portletBorderBottomToolTip${id}" name="What is pixel size and color of the Bottom Border?">(?)</a></div>
		<div class="contentAdvancedCell">
			<input type="text" id="portletBorderPixelBottom${id}" name="portletBorderPixelBottom${id}" value="${portletBorderPixelBottom}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,2);'}" type="text" id="portletBorderColorBottom${id}" name="portletBorderColorBottom${id}" value="${portletBorderColorBottom}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="contentAdvancedCellTitle">Portlet Border - Left <a href="/ieeecs-ContentAdvanced-portlet/templates/toolTip_PortletBorderLeft.html?width=300" class="jTip" id="portletBorderLeftToolTip${id}" name="What is pixel size and color of the Left Border?">(?)</a></div>
		<div class="contentAdvancedCell">
			<input type="text" id="portletBorderPixelLeft${id}" name="portletBorderPixelLeft${id}" value="${portletBorderPixelLeft}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,3);'}" type="text" id="portletBorderColorLeft${id}" name="portletBorderColorLeft${id}" value="${portletBorderColorLeft}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="contentAdvancedCellTitle">Portlet Border - Right <a href="/ieeecs-ContentAdvanced-portlet/templates/toolTip_PortletBorderRight.html?width=300" class="jTip" id="portletBorderRightToolTip${id}" name="What is pixel size and color of the Right Border?">(?)</a></div>
		<div class="contentAdvancedCell">
			<input type="text" id="portletBorderPixelRight${id}" name="portletBorderPixelRight${id}" value="${portletBorderPixelRight}" size="1"/> px&nbsp;&nbsp; 
			#<input class="color {onImmediateChange:'updatePortletBorderColor${id}(this,1);'}" type="text" id="portletBorderColorRight${id}" name="portletBorderColorRight${id}" value="${portletBorderColorRight}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>	
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="contentAdvancedCellTitle">Portlet - Corner Radii (px) <a href="/ieeecs-ContentAdvanced-portlet/templates/toolTip_PortletCornerRadius.html?width=300" class="jTip" id="portletCornerRadiusToolTip${id}" name="What are the Corner Radii (px) for the Portlet?">(?)</a></div>
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
		<div class="contentAdvancedCellTitle">Portlet Background Color <a href="/ieeecs-ContentAdvanced-portlet/templates/toolTip_PortletBackgroundColor.html?width=300" class="jTip" id="portletBackgroundColorToolTip${id}" name="What is Portlet background color?">(?)</a></div>
		<div class="contentAdvancedCell">
			#<input class="color {onImmediateChange:'updatePortletBackgroundColor${id}(this);'}" type="text" id="portletBackgroundColor${id}" name="portletBackgroundColor${id}" value="${portletBackgroundColor}" size="5"/>
		</div>
		<div class="clearBoth"></div>
	</div>
	
	<div class="portletConfigRow${id} portletSection${id}">
		<div class="contentAdvancedCellTitle">Portlet Height</div>
		<div class="contentAdvancedCell">
			<input type="text" id="portletHeight${id}" name="portletHeight${id}" value="${portletHeight}" size="2"/> px
		</div>
		<div class="clearBoth"></div>
	</div>	

	<div class="contentAdvancedRowButtons">
		<div class="contentAdvancedMessage" id="message${id}"></div>
		<div class="contentAdvancedSave"><input type="button" name="saveConfiguration${id}" id="saveConfiguration${id}" value="Save"/></div>
		<div class="clearBoth"></div>
	</div>	
		
</form>

<div id="viewSourceModal" style="display: none; overflow: auto;"><textarea></textarea></div>

<c:if test="${fallbackJS}">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/ieeecs-ContentAdvanced-portlet/js/jquery.simplemodal.js"></script>
</c:if>

<script src="/ieeecs-ContentAdvanced-portlet/js/jtip.js" type="text/javascript"></script>
<script src="/ieeecs-ContentAdvanced-portlet/js/jscolor/jscolor.js" type="text/javascript"></script>
<script src="/ieeecs-ContentAdvanced-portlet/js/ace/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="/ieeecs-ContentAdvanced-portlet/js/ace/theme-textmate.js" type="text/javascript" charset="utf-8"></script>
<script src="/ieeecs-ContentAdvanced-portlet/js/ace/mode-javascript.js" type="text/javascript" charset="utf-8"></script>
<script src="/ieeecs-ContentAdvanced-portlet/js/ace/mode-css.js" type="text/javascript" charset="utf-8"></script>
<script src="/ieeecs-ContentAdvanced-portlet/js/ace/mode-html.js" type="text/javascript" charset="utf-8"></script>

<script>
$(document).ready(function() {
	
	var CSSMode${id} = require("ace/mode/css").Mode;
	var HTMLMode${id} = require("ace/mode/html").Mode;
	var JavaScriptMode${id} = require("ace/mode/javascript").Mode;
	
	var editor_cssSection${id} = ace.edit("editor_cssSection${id}");
	editor_cssSection${id}.setTheme("ace/theme/textmate");
	editor_cssSection${id}.getSession().setMode(new CSSMode${id}());
	editor_cssSection${id}.getSession().getDocument().insertLines(0, ${cssBlock});
	
	var editor_htmlSection${id} = ace.edit("editor_htmlSection${id}");
	editor_htmlSection${id}.setTheme("ace/theme/textmate");
	editor_htmlSection${id}.getSession().setMode(new HTMLMode${id}());
	editor_htmlSection${id}.getSession().getDocument().insertLines(0, ${htmlBlock}); 
    
	var editor_jsSectionInternalPre${id} = ace.edit("editor_jsSectionInternalPre${id}");
	editor_jsSectionInternalPre${id}.setTheme("ace/theme/textmate");
	editor_jsSectionInternalPre${id}.getSession().setMode(new JavaScriptMode${id}());	
	editor_jsSectionInternalPre${id}.getSession().getDocument().insertLines(0, ${jsBlockInternalPre});	
	
	var editor_jsSectionInternalPost${id} = ace.edit("editor_jsSectionInternalPost${id}");
	editor_jsSectionInternalPost${id}.setTheme("ace/theme/textmate");
	editor_jsSectionInternalPost${id}.getSession().setMode(new JavaScriptMode${id}());	
	editor_jsSectionInternalPost${id}.getSession().getDocument().insertLines(0, ${jsBlockInternalPost});
    
	$("#saveConfiguration${id}").click(function() {	
		
		var cssBlockLines = editor_cssSection${id}.getSession().getDocument().getAllLines();
		var htmlBlockLines = editor_htmlSection${id}.getSession().getDocument().getAllLines();
		var jsBlockInternalPreLines = editor_jsSectionInternalPre${id}.getSession().getDocument().getAllLines();
		var jsBlockInternalPostLines = editor_jsSectionInternalPost${id}.getSession().getDocument().getAllLines();
			
		if ( cssBlockLines[ cssBlockLines.length-1 ] == "" ) {
			cssBlockLines.pop();
		}
		if ( htmlBlockLines[ htmlBlockLines.length-1 ] == "" ) {
			htmlBlockLines.pop();
		}
		if ( jsBlockInternalPreLines[ jsBlockInternalPreLines.length-1 ] == "" ) {
			jsBlockInternalPreLines.pop();
		}
		if ( jsBlockInternalPostLines[ jsBlockInternalPostLines.length-1 ] == "" ) {
			jsBlockInternalPostLines.pop();
		}
				
		cssBlockLines = JSON.stringify(cssBlockLines, null, 10);
		htmlBlockLines = JSON.stringify(htmlBlockLines, null, 10);
		jsBlockInternalPreLines = JSON.stringify(jsBlockInternalPreLines, null, 10);
		jsBlockInternalPostLines = JSON.stringify(jsBlockInternalPostLines, null, 10);

		$("#cssBlock${id}").val(cssBlockLines);
		$("#htmlBlock${id}").val(htmlBlockLines);
		$("#jsBlockInternalPre${id}").val(jsBlockInternalPreLines);
		$("#jsBlockInternalPost${id}").val(jsBlockInternalPostLines);

		if ( $("#showIntroCheckBox${id}").is(':checked') ) {
			$("#showIntro${id}").val("ON");
		} else {
			$("#showIntro${id}").val("OFF");
		}
		
		$("#message${id}").html("Your Configuration changes are being saved...").fadeOut(3000);
		$("#configureForm${id}").submit();
	});
	
	$("#configurationOptions${id}").change(function() {		

		var thisSelected = $(this).val();
		
		if ( thisSelected == "viewSource${id}" ) {
			
			var browserWindowHeight = $(window).height();
			var browserWindowWidth = $(window).width();
			var snapshotModalHeight = Math.floor(browserWindowHeight/1.5) + 100;
			var snapshotModalWidth = Math.floor(browserWindowWidth/1.5) + 100;
			var snapshotTextAreaHeight = snapshotModalHeight - 30;
			var snapshotTextAreaWidth = snapshotModalWidth - 25; 
			var codeSnapshot = getAllSnapshots();
			$("#viewSourceModal textarea").css({"height":snapshotTextAreaHeight+"px", "width":snapshotTextAreaWidth+"px"});
			$("#viewSourceModal textarea").val(codeSnapshot);
			$("#viewSourceModal").modal({"minHeight": snapshotModalHeight, "minWidth":snapshotModalWidth, "maxHeight": snapshotModalHeight+50, "maxWidth":snapshotModalWidth+50});
			
		} else {
			
			$(".introSection${id}, .cssSection${id}, .htmlSection${id}, .jsSectionInternalPre${id}, .jsSectionInternalPost${id}, .jsSectionExternal${id}, .portletSection${id}").hide();		
			$("." + thisSelected).show();
			
			if ( thisSelected == "cssSection${id}" ) {
				editor_cssSection${id}.focus();
			} else if ( thisSelected == "htmlSection${id}" ) {
				editor_htmlSection${id}.focus();	
			} else if ( thisSelected == "jsSectionInternalPost${id}" ) {
				editor_jsSectionInternalPost${id}.focus();
			} else if ( thisSelected == "jsSectionInternalPre${id}" ) {
				editor_jsSectionInternalPre${id}.focus();
			}
		}

	});	
	
	function getAllSnapshots() {
		var explanation = "Displayed in the order that they will be rendered:\n============================================================================\n\n\n";
		var cssSnapshot = "CSS:\n------------------------------------\n\n" + getCSSSnapshot() + "\n\n";
		var htmlSnapshot = "HTML:\n------------------------------------\n\n" + getHTMLSnapshot() + "\n\n";
		var internalPreSnapShot = "INTERNAL JAVASCRIPT (Pre-HTML):\n------------------------------------------------\n\n" + getInternalJSPreHTMLSnapshot() + "\n\n";
		var internalPostSnapShot = "INTERNAL JAVASCRIPT (Post-HTML):\n------------------------------------------------\n\n" + getInternalJSPostHTMLSnapshot() + "\n\n";
		var externalPreSnapShot = "EXTERNAL JAVASCRIPT (Pre-HTML):\n------------------------------------------------\n\n" + getExternalJSPreHTMLSnapshot() + "\n\n";
		var externalPostSnapShot = "EXTERNAL JAVASCRIPT (Post-HTML):\n------------------------------------------------\n\n" + getExternalJSPostHTMLSnapshot() + "\n\n";
		return explanation + cssSnapshot + externalPreSnapShot + internalPreSnapShot + htmlSnapshot + externalPostSnapShot + internalPostSnapShot;
	}
	
	function getCSSSnapshot() {		
		var cssBlockLines = editor_cssSection${id}.getSession().getDocument().getAllLines();
		var cssSnapShot = "";
		for ( var i=0; i < cssBlockLines.length; i++ ) {
			cssSnapShot = cssSnapShot + cssBlockLines[i] + "\n";
		}		
		return cssSnapShot;
	}
	
	function getHTMLSnapshot() {
		var htmlBlockLines = editor_htmlSection${id}.getSession().getDocument().getAllLines();
		var htmlSnapShot = "";
		for ( var i=0; i < htmlBlockLines.length; i++ ) {
			htmlSnapShot = htmlSnapShot + htmlBlockLines[i] + "\n";
		}		
		return htmlSnapShot;
	}
	
	function getInternalJSPreHTMLSnapshot() {
		var jsBlockInternalPreLines = editor_jsSectionInternalPre${id}.getSession().getDocument().getAllLines();
		var internalPreSnapShot = "";
		for ( var i=0; i < jsBlockInternalPreLines.length; i++ ) {
			internalPreSnapShot = internalPreSnapShot + jsBlockInternalPreLines[i] + "\n";
		}		
		return internalPreSnapShot;
	}
	
	function getInternalJSPostHTMLSnapshot() {
		var jsBlockInternalPostLines = editor_jsSectionInternalPost${id}.getSession().getDocument().getAllLines();
		var internalPostSnapShot = "";
		for ( var i=0; i < jsBlockInternalPostLines.length; i++ ) {
			internalPostSnapShot = internalPostSnapShot + jsBlockInternalPostLines[i] + "\n";
		}		
		return internalPostSnapShot;
	}	
	
	function getExternalJSPreHTMLSnapshot() {
		return $("#jsBlockExternalPre${id}").val();
	}	
	
	function getExternalJSPostHTMLSnapshot() {
		return $("#jsBlockExternalPost${id}").val();
	}	
	
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
	
	$(".contentAdvancedBlockInformationButton").click(function() {		
		var thisId = $(this).attr("id");		
		if ( $("#" + thisId + "Copy").is(":visible") ) {
			$("#" + thisId + "Copy").hide();
			$("." + thisId + "Block").css("height", "550px");
		} else {
			$("#" + thisId + "Copy").show();
			$("." + thisId + "Block").css("height", "350px");
		}
	});
		
	$("#topNavBackButton${id}").click(function() {
		$("#configureForm${id}").attr("action", "${viewAction}");
		$("#configureForm${id}").submit();
	});	
	
	<c:if test="${showIntro == 'ON' || showIntro == 'on'}">
		$(".introSection${id}").show();
	</c:if>
	<c:if test="${showIntro == 'OFF' || showIntro == 'off'}">
		$("#configurationOptions${id}").val("cssSection${id}");
		$(".cssSection${id}").show();
	</c:if>

});
</script>