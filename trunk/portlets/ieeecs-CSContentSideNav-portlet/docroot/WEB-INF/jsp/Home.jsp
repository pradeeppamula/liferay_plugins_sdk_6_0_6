<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:actionURL var="configureAction" portletMode="view" windowState="normal"/>
<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>
<portlet:resourceURL var='contentSideNavAjaxHandler' id='contentSideNavAjaxHandler' />
<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">
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

<div id="content-side-nav-container-${id}">
    <c:if test="${canInlineEdit}">
        <span id="edit-content-advanced-${id}" class="label label-danger"><a class="inline-edit" data-toggle="modal" data-target="#ca-edit-modal-${id}">Edit</a></span>
        <div id="ca-edit-modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ca-edit-modal" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Content Side Navigation Administration</h4>
                </div>
                <div class="modal-body">
                   Modal Body
                </div> <!-- /.modal-body -->
                 <div class="modal-footer">
                     <!-- Alerts -->
                        <div id="alert-main-success" class="alert alert-success text-left">
                            <strong>&#10004;</strong> Your portlet was updated.
                        </div>
                      <div id="alert-main-danger" class="alert alert-danger text-left">
                        <a href="#" class="close">&times;</a>
                        <strong>&#9888;</strong> There seems to be a problem:
                        <span id="error-message"></span>
                      </div>
                      <!-- End Alerts -->
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button class="btn btn-primary" id="btn-save-${id}">Save</button>
                 </div>
            </div>
          </div>
        </div> <!-- /#ca-edit-modal-${id} -->

        <script type="application/javascript">
            $(document).ready(function() {
                $("#btn-save-${id}").click(function() {
                    var postData = {};
                    postData.requestType_${id} = 'SAVE_CONFIG';

                    $.ajax({
                      type: "POST",
                      data: postData,
                      url: "${contentSideNavAjaxHandler}",
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
            });
       </script>
    </c:if>

    <ul>
    <li>Link 1</li>
    <li>Link 2</li>

    </ul>
</div>
</c:if>