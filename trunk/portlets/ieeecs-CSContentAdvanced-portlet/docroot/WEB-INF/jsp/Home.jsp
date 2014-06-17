<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='contentAdvancedAjaxHandler' id='contentAdvancedAjaxHandler' />
<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">
	#content-advanced-container-${id} {
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

	section[class$="section-${id}"] {
        height: 300px;
        display: none;
    }

    .js-external-section-${id} {  height: 300px !important; }

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

    <c:if test="${canInlineEdit}">
        /* INLINE EDIT STYLES */
        #edit-content-advanced-${id} {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 99;
        }
        a.inline-edit {
            opacity: 0.6;
            filter: alpha(opacity=60);
            transition: 0.1s linear;
            -moz-transition: 0.1s linear;
            -webkit-transition: 0.1s linear;
            color: #ffffff;
            font-size: 14px;
            line-height: 14px;
        }
        a.inline-edit:hover {
            text-decoration: none;
            opacity: 1.0;
            filter: alpha(opacity=100);
            cursor: pointer;
        }
        /* END INLINE EDIT STYLES */
    </c:if>
</style>

<c:if test="${fallbackJS}">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
</c:if>

<div id="content-advanced-container-${id}">
    <c:if test="${canInlineEdit}">
        <span id="edit-content-advanced-${id}" class="label label-danger"><a class="inline-edit" data-toggle="modal" data-target="#ca-edit-modal-${id}">Edit</a></span>
        <div id="ca-edit-modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ca-edit-modal" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Content Advanced Administration</h4>
                </div>
                <div class="modal-body">
                    <form id="edit-form-${id}" name="edit-form-${id}" role="form" class="form-horizontal">
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
                            </select>
                        </div>

                          <section class="css-section-${id}">
                            <div class="alert alert-info">
                                <ul>
                                    <li>Inline CSS - The <code>&lt;style&gt;&lt;/style&gt;</code> tags are not needed.</li>
                                    <li>The CSS in this section can be used in other portlets and page-specific HTML as well.</li>
                                    <li>You can alter the CSS of this portlet itself by defining attributes for "#contentAdvancedContainer" and add the "Instance ID" from above.</li>
                                    <li>For example:
                                        <code>
                                            #content-advanced-container-${id} {
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
                    </form>
                </div>
            </div>
          </div>
        </div>
    </c:if>

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

             $.ajax({
                 type: "POST",
                 data: postData,
                 url: "${contentAdvancedAjaxHandler}",
                 beforeSend: function() {
                   $("#btn-save-${id}").prop('disabled', true);
                 },
                 success: function (response) { console.log(response);
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
           });
       });
	</script>
</div>
</c:if>