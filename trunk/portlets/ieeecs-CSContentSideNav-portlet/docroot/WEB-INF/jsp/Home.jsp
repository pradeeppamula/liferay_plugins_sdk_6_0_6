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
<style type="text/css">
    .cs-content-side-nav-links {
        padding-top: 20px;
    }

    <c:if test="${canInlineEdit}">
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
        #alert-main-success-${id}, #alert-main-danger-${id} {
            display: none;
        }
    </c:if>
</style>

<div id="content-side-nav-container-${id}">
    <c:if test="${canInlineEdit}">
        <span id="edit-content-side-nav-${id}" class="label label-danger"><a class="inline-edit" data-toggle="modal" data-target="#csn-edit-modal-${id}">Edit</a></span>
        <div id="csn-edit-modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="csn-edit-modal" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Content Side Navigation Administration</h4>
                </div>
                <div class="modal-body">
                   <form id="edit-content-side-nav-form-${id}" role="form">
                    <div class="form-group">
                         <label for="show-links-select-${id}">Display Type</label>
                         <select id="show-links-select-${id}" class="form-control">
                            <option value="YES">Show All Community Links</option>
                            <option value="NO">Show Only Page Links</option>
                         </select>
                         <p id="show-links-select-help-${id}" class="help-block"></p>
                    </div>
                    <div class="form-group">
                         <label for="show-child-links-select-${id}">Show Sub Page Links</label>
                         <select id="show-child-links-select-${id}" class="form-control">
                            <option value="YES">YES</option>
                            <option value="NO">NO</option>
                         </select>
                         <p id="show-child-links-select-help-${id}" class="help-block"></p>
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
        </div> <!-- /#ca-edit-modal-${id} -->

        <script>
            var setDisplayHelp = function() {
                var showAllHelp = "All sublinks of the community will be displayed.";
                var showPageHelp = "Only sublinks of the community page will be displayed.";
                var selectedDisplayType = $('#show-links-select-${id}').val();
                if(selectedDisplayType == 'YES') {
                    $('#show-links-select-help-${id}').html(showAllHelp);
                } else {
                    $('#show-links-select-help-${id}').html(showPageHelp);
                }
            }

            $(document).ready(function() {
                var showAllCommunityLinks = '${showAllCommunityLinks}';
                showAllCommunityLinks = showAllCommunityLinks == '' ? 'YES' : showAllCommunityLinks;
                $('#show-links-select-${id} option[value="'+showAllCommunityLinks+'"]').attr("selected", "selected");

                var showChildLinks = '${showChildLinks}';
                showChildLinks = showChildLinks == '' ? 'YES' : showChildLinks;
                $('#show-child-links-select-${id} option[value="'+showChildLinks+'"]').attr("selected", "selected");

                setDisplayHelp();

                $('#show-links-select-${id}').change(function() {
                     setDisplayHelp();
                 });

                $("#btn-save-${id}").click(function() {
                    var postData = {};
                    postData.requestType_${id} = 'SAVE_CONFIG';
                    postData.showAllCommunityLinks_${id} = $('#show-links-select-${id}').val();
                    postData.showChildLinks_${id} = $('#show-child-links-select-${id}').val();

                    $.ajax({
                      type: "POST",
                      data: postData,
                      url: "${contentSideNavAjaxHandler}",
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
    <ul id="cs-content-side-nav-links-${id}" class="cs-content-side-nav-links"></ul>
</div>
<script>
   $(document).ready(function() {
    var links = $.parseJSON('${links}');
    $.each(links, function(i, link) {
        var html = link.active ? '<li class="active ${community}"><a' : '<li class="${community}"><a';
        html +=' href="'+link.url+'">'+link.title+'</a>';
        if(link.childLinks != undefined && link.childLinks.length > 0) {
            html += '<ul>';
            for(var i=0;i<link.childLinks.length;i++) {
                html += link.childLinks[i].active ? '<li class="active ${community}"><a' : '<li class="${community}"><a';
                html +=' href="'+link.childLinks[i].url+'">'+link.childLinks[i].title+'</a></li>';
            }
            html += '</ul>';
        }
        html += '</li>';
        $('#cs-content-side-nav-links-${id}').append(html);
    });
   });
</script>