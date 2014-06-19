<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='configureAjaxHandler' id='configureAjaxHandler' />

<portlet:actionURL var="configureAction" portletMode="edit">
	<portlet:param name="action" value="cscontentAdvancedConfigure"/>
</portlet:actionURL>

<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>

<style type="text/css">
	#portlet-corner-radii-${id} {
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

	section[class$="section-${id}"] {
		height: 800px;
		display: none;		
	}

	.intro-section-${id} { height: 320px !important; }
	.js-external-section-${id} {  height: 600px !important; }
	.portlet-section-${id} {  height: 400px !important; }

	.css${id}_InfoBlock,
	.html${id}_InfoBlock, 
	.intPre${id}_InfoBlock,
	.intPost${id}_InfoBlock {
		width: 98%;
		height: 650px;
		margin: 0 0 0 10px;
		background-color: #FFFFFF;
		z-index: 100;	
	}
	.alert-success, .alert-danger { display: none; }
	.popover-help { color: #3498db; }
</style>
<div class="container">
    <h4>Content Advanced Portlet Configuration</h2>
    <h6>Instance ID: <em>${id}</em></h4>
    <a href="#" class="btn btn-default btn-cancel-${id} pull-right">Back</a>
</div>
<form id="configure-form-${id}" name="configure-form-${id}" role="form" class="form-horizontal">
    <input type="hidden" id="css-block-${id}" name="css-block-${id}" value=""/>
    <input type="hidden" id="html-block-${id}" name="html-block-${id}" value=""/>
    <input type="hidden" id="js-block-internal-pre-${id}" name="js-block-internal-pre-${id}" value=""/>
    <input type="hidden" id="js-block-internal-post-${id}" name="js-block-internal-post-${id}" value=""/>
    <input type="hidden" id="show-intro-${id}" name="show-intro-${id}" value="${showIntro}"/>

    <div class="form-group">
        <label for="portlet-mode-${id}" class="col-sm-1 control-label">Mode</label>
        <div class="col-sm-6">
            <select id="portlet-mode-${id}" name="portlet-mode-${id}" class="form-control">
                <option value="ACTIVATED" <c:if test="${portletMode == 'ACTIVATED'}"> selected="selected" </c:if>>Active (Visible)</option>
                <option value="DEACTIVATED" <c:if test="${portletMode == 'DEACTIVATED'}"> selected="selected" </c:if>>Not Active (Not Visible)</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="configuration-options-${id}" class="col-sm-1 control-label">Section</label>
        <div class="col-sm-6">
            <select id="configuration-options-${id}" name="configuration-options-${id}" class="form-control">
                <option value="intro-section-${id}">Intro</option>
                <option value="css-section-${id}">CSS Block</option>
                <option value="html-section-${id}">HTML Block</option>
                <option value="js-internal-pre-section-${id}">Internal Javascript (Pre-HTML)</option>
                <option value="js-internal-post-section-${id}">Internal Javascript (Post-HTML)</option>
                <option value="js-external-section-${id}">External Javascript (Pre and Post HTML)</option>
                <option value="portlet-section-${id}">Portlet Configuration</option>
                <option value="view-source-${id}">View All Code Blocks</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <section class="intro-section-${id}">
            <div class="alert alert-info">
                Please note that you'll need CSS / HTML / Javascript knowledge to use this advanced portlet.
                Incorrect usage of the Javascript blocks of this portlet can cause issues page-wide.
                Therefore, please test the usage of this advanced portlet on another "test" page before using on a live site.
                <br />
                The code will be rendered in the following order:
                <ol>
                    <li>CSS Block</li>
                    <li>External Javascript : Pre-HTML</li>
                    <li>Internal Javascript : Pre-HTML</li>
                    <li>HTML</li>
                    <li>External Javascript : Post-HTML</li>
                    <li>Internal Javascript : Post-HTML</li>
                </ol>
            </div>
            <div class="form-group">
                <label for="show-intro-checkbox-${id}" class="col-sm-4 control-label">Show this 'Intro' tab first when in 'Preferences' mode</label>
                <div class="col-sm-3">
                    <input type="checkbox" name="show-intro-checkbox-${id}" id="show-intro-checkbox-${id}" class="form-control" <c:if test="${showIntro == 'ON' || showIntro == 'on'}">checked="checked"</c:if>/>
                </div>
            </div>
        </section>  <!-- /.intro-section-${id} -->

        <section class="css-section-${id}">
            <div class="alert alert-info">
                <ul>
                    <li>Inline CSS - The <code>&lt;style&gt;&lt;/style&gt;</code> tags are not needed.</li>
                    <li>The CSS in this section can be used in other portlets and page-specific HTML as well.</li>
                    <li>You can alter the CSS of this portlet itself by defining attributes for "#contentAdvancedContainer" and add the "Instance ID" from above.</li>
                    <li>For example:
                        <code>
                            #contentAdvancedContainer${id} {
                                padding: 50px;
                            }
                        </code>
                    </li>
                </ul>
            </div>
            <div id="editor_cssSection${id}" name="editor_cssSection${id}" class="css${id}_InfoBlock"></div>
        </section> <!-- /.css-section-${id} -->

        <section class="html-section-${id}">
            <div class="alert alert-info">
                <ul>
                    <li>HTML only.</li>
                    <li>Javascript and CSS should be entered in their respective editors.</li>
                </ul>
            </div>
            <div id="editor_htmlSection${id}" name="editor_htmlSection${id}" class="html${id}_InfoBlock"></div>
        </section> <!-- /.html-section-${id} -->

        <section class="js-internal-pre-section-${id}">
            <div class="alert alert-info">
                <ul>
                    <li>Inline JS - The <code>&lt;style&gt;&lt;/style&gt;</code> tags are not needed.</li>
                    <li>This javascript will render <em>before</em> the HTML.</li>
                </ul>
            </div>
            <div id="editor_jsSectionInternalPre${id}" name="editor_jsSectionInternalPre${id}" class="intPre${id}_InfoBlock"></div>
        </section> <!-- /.js-internal-pre-section-${id} -->

        <section class="js-internal-post-section-${id}">
            <div class="alert alert-info">
                <ul>
                    <li>Inline JS - The <code>&lt;style&gt;&lt;/style&gt;</code> tags are not needed.</li>
                    <li>This javascript will render <em>after</em> the HTML.</li>
                </ul>
            </div>
            <div id="editor_jsSectionInternalPost${id}" name="editor_jsSectionInternalPost${id}" class="intPost${id}_InfoBlock"></div>
        </section> <!-- /.js-internal-post-section-${id} -->

        <section class="js-external-section-${id}">
            <div class="alert alert-info">
                <ul>
                    <li>External JS libraries.</li>
                    <li>For example, you can enter an entire <code>&lt;script&gt;...&lt;/script&gt;</code> line:
                        <code>&lt;script type="text/javascript" src="http://ajax.booya.com/libs/jQbooya/1.7/jQbooya.min.js"&gt;&lt;/script&gt;</code>
                    </li>
                </ul>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-5">PRE : Loaded <em>before</em> the Internal JS (Pre), HTML, and Internal JS (Post):</label>
                <div class="col-sm-6">
                    <textarea rows="5" id="js-block-external-pre-${id}" name="js-block-external-pre-${id}" class="form-control">${jsBlockExternalPre}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-5">POST : Loaded <em>after</em> the Internal JS (Pre) and HTML, but <b>before</b> the Internal JS (Post):</label>
                <div class="col-sm-6">
                    <textarea rows="5" id="js-block-external-post-${id}" name="js-block-external-post-${id}" class="form-control">${jsBlockExternalPost}</textarea>
                </div>
            </div>
        </section> <!-- /.js-external-section-${id} -->

        <section class="portlet-section-${id}">
            <hr />
            <div class="form-group">
                <label class="col-sm-2 control-label" for="portlet-border-pixel-top-${id}">Top Border <span class="popover-help" data-container="body" data-toggle="popover" data-placement="top" data-content="<ul><li>Set the pixel value to '0' (zero) for no border.</li><li>Pixel value is usually 1 or 0.</li></ul>">?</span></label>
                <div class="col-sm-2">
                    <div class="input-group">
                        <input type="text" class="form-control" id="portlet-border-pixel-top-${id}" name="portlet-border-pixel-top-${id}" value="${portletBorderPixelTop}" size="1"/>
                        <span class="input-group-addon">px</span>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="input-group">
                        <span class="input-group-addon">#</span>
                        <input class="form-control color {onImmediateChange:'updatePortletBorderColor${id}(this,0);'}" type="text" id="portlet-border-color-top-${id}" name="portlet-border-color-top-${id}" value="${portletBorderColorTop}" size="5"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label" for="portlet-border-pixel-bottom-${id}">Bottom Border  <span class="popover-help" data-container="body" data-toggle="popover" data-placement="top" data-content="<ul><li>Set the pixel value to '0' (zero) for no border.</li><li>Pixel value is usually 1 or 0.</li></ul>">?</span></label>
                <div class="col-sm-2">
                    <div class="input-group">
                        <input type="text" class="form-control" id="portlet-border-pixel-bottom-${id}" name="portlet-border-pixel-bottom-${id}" value="${portletBorderPixelBottom}" size="1"/>
                        <span class="input-group-addon">px</span>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="input-group">
                        <span class="input-group-addon">#</span>
                        <input class="form-control color {onImmediateChange:'updatePortletBorderColor${id}(this,2);'}" type="text" id="portlet-border-color-bottom-${id}" name="portlet-border-color-bottom-${id}" value="${portletBorderColorBottom}" size="5"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label" for="portlet-border-pixel-left-${id}">Left Border  <span class="popover-help" data-container="body" data-toggle="popover" data-placement="top" data-content="<ul><li>Set the pixel value to '0' (zero) for no border.</li><li>Pixel value is usually 1 or 0.</li></ul>">?</span></label>
                <div class="col-sm-2">
                    <div class="input-group">
                        <input type="text" class="form-control" id="portlet-border-pixel-left-${id}" name="portlet-border-pixel-left-${id}" value="${portletBorderPixelLeft}" size="1"/>
                        <span class="input-group-addon">px</span>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="input-group">
                        <span class="input-group-addon">#</span>
                        <input class="form-control color {onImmediateChange:'updatePortletBorderColor${id}(this,3);'}" type="text" id="portlet-border-color-left-${id}" name="portlet-border-color-left-${id}" value="${portletBorderColorLeft}" size="5"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label" for="portlet-border-pixel-right-${id}">Right Border  <span class="popover-help" data-container="body" data-toggle="popover" data-placement="top" data-content="<ul><li>Set the pixel value to '0' (zero) for no border.</li><li>Pixel value is usually 1 or 0.</li></ul>">?</span></label>
                <div class="col-sm-2">
                    <div class="input-group">
                        <input type="text" class="form-control" id="portlet-border-pixel-right-${id}" name="portlet-border-pixel-right-${id}" value="${portletBorderPixelRight}" size="1"/>
                        <span class="input-group-addon">px</span>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="input-group">
                        <span class="input-group-addon">#</span>
                        <input class="form-control color {onImmediateChange:'updatePortletBorderColor${id}(this,1);'}" type="text" id="portlet-border-color-right-${id}" name="portlet-border-color-right-${id}" value="${portletBorderColorRight}" size="5"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">Corner Radius  <span class="popover-help" data-container="body" data-toggle="popover" data-placement="top" data-content="<ul><li>Set the Portlet Corner Radius values</li><li>Integer value, in pixels</li></ul>">?</span></label>
                <div class="col-sm-3" id="portlet-corner-radii-${id}">
                    <label class="control-label col-sm-2" for="portlet-top-left-radius-${id}">Top Left</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="portlet-top-left-radius-${id}" name="portletTopLeftRadius${id}" value="${portletTopLeftRadius}" size="1"/>
                    </div>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="portlet-top-right-radius-${id}" name="portlet-top-right-radius-${id}" value="${portletTopRightRadius}" size="1"/>
                    </div>
                    <label class="control-label col-sm-2" for="portlet-top-right-radius-${id}">Top Right</label>
                    <div class="clearfix"></div>
                    <label class="control-label col-sm-2" for="portlet-bottom-left-radius-${id}">Bottom Left</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="portlet-bottom-left-radius-${id}" name="portlet-bottom-left-radius-${id}" value="${portletBottomLeftRadius}" size="1"/>
                    </div>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="portlet-bottom-right-radius-${id}" name="portlet-bottom-right-radius-${id}" value="${portletBottomRightRadius}" size="1"/>
                    </div>
                    <label class="control-label col-sm-2" for="portlet-bottom-right-radius-${id}">Bottom Left</label>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="portlet-background-color-${id}">Background Color</label>
                <div class="col-sm-2">
                    <div class="input-group">
                        <span class="input-group-addon">#</span>
                        <input class="form-control color {onImmediateChange:'updatePortletBackgroundColor${id}(this);'}" type="text" id="portlet-background-color-${id}" name="portlet-background-color-${id}" value="${portletBackgroundColor}" size="5"/>
                    </div>
                </div>
            </div>
        </section> <!-- /.portlet-section-${id} -->
    </div> <!-- /end sections -->

    <div class="container">
           <!-- Alerts -->
            <div id="alert-main-success" class="alert alert-success">
                <strong>&#10004;</strong> Your portlet was updated.
            </div>
          <div id="alert-main-danger" class="alert alert-danger">
            <a href="#" class="close">&times;</a>
            <strong>&#9888;</strong> There seems to be a problem:
            <span id="error-message"></span>
          </div>
          <!-- End Alerts -->
        <a href="#" class="btn btn-default btn-cancel-${id}">Cancel</a>
        <button class="btn btn-primary" id="btn-save-${id}">Save</button>
    </div>
</form>
<div id="viewSourceModal" style="display: none; overflow: auto;"><textarea></textarea></div>

<div id="view-source-modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="view-source-modal" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">Source</h4>
        </div>
        <div class="modal-body">
            <textarea id="souce-code-${id}" class="form-control" rows="20"></textarea>
        </div>
    </div>
  </div>
</div>

<c:if test="${fallbackJS}">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
</c:if>

<script src="/ieeecs-CSContentAdvanced-portlet/js/jscolor/jscolor.js" type="text/javascript"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/ace/1.1.3/ace.js" type="text/javascript" charset="utf-8"></script>

<!-- NOTE: Don't put the type in the script tag b/c Liferay will mess up during the minifier process -->
<script>
	function updatePortletBackgroundColor${id}(color) {
		$("#portlet-corner-radii-${id}").css({"background-color":"#" + color});
	}

	function updatePortletBorderColor${id}(color, border) {
		if ( border == 0 ) {
			$("#portlet-corner-radii-${id}").css({"border-top-color":"#" + color});
		} else if ( border == 1 ) {
			$("#portlet-corner-radii-${id}").css({"border-right-color":"#" + color});
		} else if ( border == 2 ) {
			$("#portlet-corner-radii-${id}").css({"border-bottom-color":"#" + color});
		} else if ( border == 3 ) {
			$("#portlet-corner-radii-${id}").css({"border-left-color":"#" + color});
		}
	}

    $(document).ready(function() {
        // enable popovers
        $('.popover-help').popover({'html':true});

        var codeFolding = "markbegin";
        var theme = "ace/theme/dreamweaver";
        var modeJavascript = "ace/mode/javascript";

        var editor_cssSection${id} = ace.edit("editor_cssSection${id}");
        editor_cssSection${id}.setTheme(theme);
        editor_cssSection${id}.getSession().setMode("ace/mode/css");
        editor_cssSection${id}.getSession().getDocument().insertLines(0, ${cssBlock});
        editor_cssSection${id}.getSession().setFoldStyle(codeFolding);

        var editor_htmlSection${id} = ace.edit("editor_htmlSection${id}");
        editor_htmlSection${id}.setTheme(theme);
        editor_htmlSection${id}.getSession().setMode("ace/mode/html");
        editor_htmlSection${id}.getSession().getDocument().insertLines(0, ${htmlBlock});
        editor_htmlSection${id}.getSession().setFoldStyle(codeFolding);

        var editor_jsSectionInternalPre${id} = ace.edit("editor_jsSectionInternalPre${id}");
        editor_jsSectionInternalPre${id}.setTheme(theme);
        editor_jsSectionInternalPre${id}.getSession().setMode(modeJavascript);
        editor_jsSectionInternalPre${id}.getSession().getDocument().insertLines(0, ${jsBlockInternalPre});
        editor_jsSectionInternalPre${id}.getSession().setFoldStyle(codeFolding);

        var editor_jsSectionInternalPost${id} = ace.edit("editor_jsSectionInternalPost${id}");
        editor_jsSectionInternalPost${id}.setTheme(theme);
        editor_jsSectionInternalPost${id}.getSession().setMode(modeJavascript);
        editor_jsSectionInternalPost${id}.getSession().getDocument().insertLines(0, ${jsBlockInternalPost});
        editor_jsSectionInternalPost${id}.getSession().setFoldStyle(codeFolding);

        $("#btn-save-${id}").click(function() {
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

            $("#css-block-${id}").val(cssBlockLines);
            $("#html-block-${id}").val(htmlBlockLines);
            $("#js-block-internal-pre-${id}").val(jsBlockInternalPreLines);
            $("#js-block-internal-post-${id}").val(jsBlockInternalPostLines);

            if ( $("#show-intro-checkbox-${id}").is(':checked') ) {
                $("#show-intro-${id}").val("ON");
            } else {
                $("#show-intro-${id}").val("OFF");
            }

            // TODO: reduce to one line, probably should serialize the form
            // build the post data and post to the portlet
            var postData = {};
            postData.requestType_${id} = 'SAVE_CONFIG';
            postData.portletMode_${id} = $("#portlet-mode-${id}").val();
            postData.showIntro_${id} = $("#show-intro-${id}").val();
            postData.cssBlock_${id} = $("#css-block-${id}").val();
            postData.htmlBlock_${id} = $("#html-block-${id}").val();
            postData.jsBlockInternalPre_${id} = $("#js-block-internal-pre-${id}").val();
            postData.jsBlockInternalPost_${id} = $("#js-block-internal-post-${id}").val();
            postData.jsBlockExternalPre_${id} = $("#js-block-external-pre-${id}").val();
            postData.jsBlockExternalPost_${id} = $("#js-block-external-post-${id}").val();
            postData.portletBorderColorTop_${id} = $('#portlet-border-color-top-${id}').val();
            postData.portletBorderColorRight_${id}   = $('#portlet-border-color-right-${id}').val();
            postData.portletBorderColorBottom_${id} = $('#portlet-border-color-bottom-${id}').val();
            postData.portletBorderColorLeft_${id}  = $('#portlet-border-color-left-${id}').val();
            postData.portletBorderPixelTop_${id}  = $('#portlet-border-pixel-top-${id}').val();
            postData.portletBorderPixelRight_${id}  = $('#portlet-border-pixel-right-${id}').val();
            postData.portletBorderPixelBottom_${id}  = $('#portlet-border-pixel-bottom-${id}').val();
            postData.portletBorderPixelLeft_${id}  = $('#portlet-border-pixel-left-${id}').val();
            postData.portletBackgroundColor_${id}  = $('#portlet-background-color-${id}').val();
            postData.portletTopLeftRadius_${id}  = $('#portlet-top-left-radius-${id}').val();
            postData.portletBottomLeftRadius_${id}  = $('#portlet-bottom-left-radius-${id}').val();
            postData.portletTopRightRadius_${id}  = $('#portlet-top-right-radius-${id}').val();
            postData.portletBottomRightRadius_${id} = $('#portlet-bottom-right-radius-${id}').val();


          $.ajax({
              type: "POST",
              data: postData,
              url: "${configureAjaxHandler}",
              beforeSend: function() {
                $("#btn-save-${id}").prop('disabled', true);
              },
              success: function (response) {
                if(response == 200) {
                    $("#alert-main-danger").fadeOut(0);
                     $("#alert-main-success").fadeIn(100);
                     $("#btn-save-${id}").prop('disabled', false);
                     setTimeout(function() {$("#alert-main-success").fadeOut(300);},3000);
                 } else {
                   console.error(data);
                   $("#alert-main-danger").fadeIn(100);
                   $("#error-message").html("There seems to be a problem with your request.  Please contact help@computer.org.");
                   $("#btn-save-${id}").prop('disabled', false);
                 }
              },
              error: function (data) {
                console.error(data);
                $("#alert-main-danger").fadeIn(100);
                $("#error-message").html(data.responseJSON.error);
                $("#btn-save-${id}").prop('disabled', false);
              }
          });

        });

        $("#configuration-options-${id}").on("change", function() {
            var thisSelected = $(this).val();
            if (thisSelected == "view-source-${id}") {
               $('#souce-code-${id}').val(getAllSnapshots());
               $('#view-source-modal-${id}').modal({});
            } else {
                $("section[class$='section-${id}']").hide();
                $("." + thisSelected).show();
                if ( thisSelected == "css-section-${id}" ) {
                    editor_cssSection${id}.focus();
                } else if ( thisSelected == "html-section-${id}" ) {
                    editor_htmlSection${id}.focus();
                } else if ( thisSelected == "js-internal-post-section-${id}" ) {
                    editor_jsSectionInternalPost${id}.focus();
                } else if ( thisSelected == "js-internal-pre-section-${id}" ) {
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
            return $("#js-block-external-pre-${id}").val();
        }

        function getExternalJSPostHTMLSnapshot() {
            return $("#js-block-external-post-${id}").val();
        }

         $("input[id$='radius-${id}']").keyup(function() {
            var pixelValue = $(this).val();
            var thisId = $(this).attr("id");
            var containerId = "#" + $(this).parent().parent().attr("id");

            if ( thisId.indexOf("top-left") > -1 ) {
                $(containerId).css({"border-top-left-radius":pixelValue + "px " + pixelValue + "px"});
                $(containerId).css({"-moz-border-top-left-radius":pixelValue + "px " + pixelValue + "px"});
            } else if ( thisId.indexOf("bottom-left") > -1 ) {
                $(containerId).css({"border-bottom-left-radius":pixelValue + "px " + pixelValue + "px"});
                $(containerId).css({"-moz-border-bottom-left-radius":pixelValue + "px " + pixelValue + "px"});
            } else if ( thisId.indexOf("top-right") > -1 ) {
                $(containerId).css({"border-top-right-radius":pixelValue + "px " + pixelValue + "px"});
                $(containerId).css({"-moz-border-top-right-radius":pixelValue + "px " + pixelValue + "px"});
            } else if ( thisId.indexOf("bottom-right") > -1 ) {
                $(containerId).css({"border-bottom-right-radius":pixelValue + "px " + pixelValue + "px"});
                $(containerId).css({"-moz-border-bottom-right-radius":pixelValue + "px " + pixelValue + "px"});
            }
        });

        $("#portlet-border-pixel-top-${id}").keyup(function() {
            var newPixel = $(this).val();
            $("#portlet-corner-radii-${id}").css({"border-top-width":newPixel+"px"});
        });

        $("#portlet-border-pixel-right-${id}").keyup(function() {
            var newPixel = $(this).val();
            $("#portlet-corner-radii-${id}").css({"border-right-width":newPixel+"px"});
        });

        $("#portlet-border-pixel-bottom-${id}").keyup(function() {
            var newPixel = $(this).val();
            $("#portlet-corner-radii-${id}").css({"border-bottom-width":newPixel+"px"});
        });

        $("#portlet-border-pixel-left-${id}").keyup(function() {
            var newPixel = $(this).val();
            $("#portlet-corner-radii-${id}").css({"border-left-width":newPixel+"px"});
        });

        $(".btn-cancel-${id}").click(function() {
            // go back to previous page
            window.location = "${viewAction}";
        });

        <c:if test="${showIntro == 'ON' || showIntro == 'on'}">
            $(".intro-section-${id}").show();
        </c:if>
        <c:if test="${showIntro == 'OFF' || showIntro == 'off'}">
            $("#configuration-options-${id}").val("css-section-${id}");
            $(".css-section-${id}").show();
        </c:if>
    });
</script>