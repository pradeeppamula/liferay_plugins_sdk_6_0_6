<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:actionURL var="configureAction" portletMode="view" windowState="normal"/>
<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>
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

	<c:if test="${cssBlockToRender != ''}">
	    ${cssBlockToRender}
	</c:if>

	section[class$="section-${id}"] {
        height: 300px;
    }

    .js-external-section-${id} {  height: 400px !important; }

    .css${id}_InfoBlock,
    .html${id}_InfoBlock,
    .intPre${id}_InfoBlock,
    .intPost${id}_InfoBlock {
        height: 650px;
    }

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
          #alert-main-success-${id},#alert-main-danger-${id} { display: none; }
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
                        <ul id="ca-edit-tabs-${id}" class="nav nav-tabs">
                            <li class="active"><a href="#css-section-${id}" data-toggle="tab">CSS</a></li>
                            <li><a href="#html-section-${id}" data-toggle="tab">HTML</a></li>
                            <li><a href="#js-internal-pre-section-${id}" data-toggle="tab">JS - Pre</a></li>
                            <li><a href="#js-internal-post-section-${id}" data-toggle="tab">JS - Post</a></li>
                            <li><a href="#js-external-section-${id}" data-toggle="tab">JS - Ext</a></li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content">
                            <section id="css-section-${id}" class="css-section-${id} tab-pane active">
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

                           <section id="html-section-${id}" class="html-section-${id} tab-pane">
                                <div class="alert alert-info">
                                    <ul>
                                        <li>HTML only.</li>
                                        <li>Javascript and CSS should be entered in their respective editors.</li>
                                    </ul>
                                </div>
                                <div id="editor_htmlSection${id}" name="editor_htmlSection${id}" class="html${id}_InfoBlock"></div>
                            </section> <!-- /.html-section-${id} -->

                             <section id="js-internal-pre-section-${id}" class="js-internal-pre-section-${id} tab-pane">
                                <div class="alert alert-info">
                                    <ul>
                                        <li>Inline JS - The <code>&lt;style&gt;&lt;/style&gt;</code> tags are not needed.</li>
                                        <li>This javascript will render <em>before</em> the HTML.</li>
                                    </ul>
                                </div>
                                <div id="editor_jsSectionInternalPre${id}" name="editor_jsSectionInternalPre${id}" class="intPre${id}_InfoBlock"></div>
                            </section> <!-- /.js-internal-pre-section-${id} -->

                            <section id="js-internal-post-section-${id}" class="js-internal-post-section-${id} tab-pane">
                                <div class="alert alert-info">
                                    <ul>
                                        <li>Inline JS - The <code>&lt;style&gt;&lt;/style&gt;</code> tags are not needed.</li>
                                        <li>This javascript will render <em>after</em> the HTML.</li>
                                    </ul>
                                </div>
                                <div id="editor_jsSectionInternalPost${id}" name="editor_jsSectionInternalPost${id}" class="intPost${id}_InfoBlock"></div>
                            </section> <!-- /.js-internal-post-section-${id} -->

                            <section id="js-external-section-${id}" class="js-external-section-${id} tab-pane">
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
                        </div> <!-- /.tab-content -->
                    </form>
                </div> <!-- /.modal-body -->
                 <div class="modal-footer">
                     <!-- Alerts -->
                        <div id="alert-main-success-${id}" class="alert alert-success text-left">
                            <strong>&#10004;</strong> Your portlet was updated.
                        </div>
                      <div id="alert-main-danger-${id}" class="alert alert-danger text-left">
                        <a href="#" class="close">&times;</a>
                        <strong>&#9888;</strong> There seems to be a problem:
                        <span id="error-message-${id}"></span>
                      </div>
                      <!-- End Alerts -->
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button class="btn btn-primary" id="btn-save-${id}">Save</button>
                 </div>
            </div>
          </div>
        </div>


    <script src="//cdnjs.cloudflare.com/ajax/libs/ace/1.1.3/ace.js" type="text/javascript" charset="utf-8"></script>
    <script type="application/javascript">

        $(document).ready(function() {
            var codeFolding = "markbegin";
            var theme = "ace/theme/dreamweaver";
            var modeJavascript = "ace/mode/javascript";
            var editor_cssSection${id} = ace.edit("editor_cssSection${id}");
            editor_cssSection${id}.setTheme(theme);
            editor_cssSection${id}.getSession().setMode("ace/mode/css");
            editor_cssSection${id}.getSession().getDocument().insertLines(0, ${cssBlock});
            editor_cssSection${id}.getSession().setFoldStyle(codeFolding);
            editor_cssSection${id}.focus();

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

            $('#ca-edit-tabs-${id} > li > a').click(function (e) {
                var id = $(this).attr("href");
                if(id == "#css-section-${id}") editor_cssSection${id}.focus();
                else if(id == "#html-section-${id}") editor_htmlSection${id}.focus();
                else if(id == "#js-internal-pre-section-${id}") editor_jsSectionInternalPre${id}.focus();
                else if(id == "#js-internal-post-section-${id}") editor_jsSectionInternalPost${id}.focus();
            });

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

                var postData = {};
                postData.requestType_${id} = 'SAVE_CONFIG';
                postData.cssBlock_${id} = JSON.stringify(cssBlockLines, null, 10);;
                postData.htmlBlock_${id} = JSON.stringify(htmlBlockLines, null, 10);;
                postData.jsBlockInternalPre_${id} = JSON.stringify(jsBlockInternalPreLines, null, 10);
                postData.jsBlockInternalPost_${id} = JSON.stringify(jsBlockInternalPostLines, null, 10);
                postData.jsBlockExternalPre_${id} = $("#js-block-external-pre-${id}").val();
                postData.jsBlockExternalPost_${id} = $("#js-block-external-post-${id}").val();

                $.ajax({
                  type: "POST",
                  data: postData,
                  url: "${contentAdvancedAjaxHandler}",
                  beforeSend: function() {
                    $("#btn-save-${id}").prop('disabled', true);
                  },
                  success: function (response) {
                    if(response == 200) {
                        $("#alert-main-danger-${id}").fadeOut(0);
                         $("#alert-main-success-${id}").fadeIn(100);
                         $("#btn-save-${id}").prop('disabled', false);
                         setTimeout(function() {$("#alert-main-success-${id}").fadeOut(300);},3000);
                     } else {
                       console.error(data);
                       $("#alert-main-danger-${id}").fadeIn(100);
                       $("#error-message-${id}").html("There seems to be a problem with your request.  Please contact help@computer.org.");
                       $("#btn-save-${id}").prop('disabled', false);
                     }
                  },
                  error: function (data) {
                    console.error(data);
                    $("#alert-main-danger-${id}").fadeIn(100);
                    $("#error-message-${id}").html(data.responseJSON.error);
                    $("#btn-save-${id}").prop('disabled', false);
                  }
              });
           });
       });
       </script>
    </c:if>

	<c:if test="${jsBlockExternalPre != ''}">
	    ${jsBlockExternalPre}
	</c:if>
	
	<c:if test="${jsBlockInternalPreToRender != ''}">
        <script language="Javascript">
            ${jsBlockInternalPreToRender}
        </script>
	</c:if>	
	
	<c:if test="${htmlBlockToRender != ''}">
	    ${htmlBlockToRender}
	</c:if>
	
	<c:if test="${jsBlockExternalPost != ''}">
	    ${jsBlockExternalPost}
	</c:if>
			
	<c:if test="${jsBlockInternalPostToRender != ''}">
        <script language="Javascript">
            ${jsBlockInternalPostToRender}
        </script>
	</c:if>
</div>
</c:if>