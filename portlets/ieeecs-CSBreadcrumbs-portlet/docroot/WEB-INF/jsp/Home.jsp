<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:actionURL var="configureAction" portletMode="view" windowState="normal"/>
<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>
<portlet:resourceURL var='csbreadcrumbsAjaxHandler' id='csbreadcrumbsAjaxHandler' />
<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<style type="text/css">
    .cs-breadcrumbs {
        padding-top: 20px;
    }

    ul.cs-breadcrumbs li {
        display:inline;
        margin-left:10px;
    }

    <c:if test="${canInlineEdit}">
        #edit-breadcrumbs-${id} {
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
        #alert-main-success-${id}, #alert-main-danger-${id} {
            display: none;
        }
    </c:if>
</style>

<div id="cs-breadcrumbs-container-${id}">
    <c:if test="${canInlineEdit}">
        <span id="edit-breadcrumbs-${id}" class="label label-danger"><a class="inline-edit" data-toggle="modal" data-target="#breadcrumbs-edit-modal-${id}">Edit</a></span>
        <div id="breadcrumbs-edit-modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="breadcrumbs-edit-modal-${id}" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Breadcrumbs Administration</h4>
                </div>
                <div class="modal-body">
                   <form id="edit-breadcrumbs-form-${id}" role="form">
                    <div class="form-group">
                    </div>
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
        </div> <!-- /#breadcrumbs-edit-modal-${id} -->

        <script>
            $(document).ready(function() {
                $("#btn-save-${id}").click(function() {
                    var postData = {};
                    postData.requestType_${id} = 'SAVE_CONFIG';

                    $.ajax({
                      type: "POST",
                      data: postData,
                      url: "${csbreadcrumbsAjaxHandler}",
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
    <ul id="cs-breadcrumbs-${id}" class="cs-breadcrumbs"></ul>
</div>
<script>
   $(document).ready(function() {
    var breadcrumbs = $.parseJSON('${breadcrumbs}');
    breadcrumbs = breadcrumbs.reverse();
    $.each(breadcrumbs, function(i, breadcrumb) {
        var html = breadcrumb.active ? '<li class="active ${community}"><a' : '<li class="${community}"><a';
        html +=' href="'+breadcrumb.url+'">'+breadcrumb.title+'</a></li>';
        $('#cs-breadcrumbs-${id}').append(html);
    });
   });
</script>