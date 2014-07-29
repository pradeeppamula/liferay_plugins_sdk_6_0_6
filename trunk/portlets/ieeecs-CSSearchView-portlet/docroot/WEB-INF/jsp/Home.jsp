<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:actionURL var="configureAction" portletMode="view" windowState="normal"/>
<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>
<portlet:resourceURL var='cssearchViewAjaxHandler' id='cssearchViewAjaxHandler' />
<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<style type="text/css">
    #cs-search-list-container-${id} { position: relative; }
    #cs-search-view-form-${id} {
        padding: 20px 0;
    }
    #cs-search-view-form-${id} > div {
        padding: 5px 0;
    }
    .cs-search-view {
        padding-top: 20px;
    }

    #cs-search-field-${id} + span {
        background-color: #fff;
        border: none;
    }
    #cs-search-field-${id} + span:hover {
        cursor: pointer;
    }
    <%-- Move to theme --%>
    .cs-search-view-form {
        background: rgba(0, 0, 0, 0.1);
    }
    <%-- /.cs-search-view-form --%>

    .cs-search-view-dynamic:hover {
        cursor: pointer;
    }

    .cs-search-view-form.dynamic {
        display: none;
    }
    .cs-search-view-form.static {
        display: block;
    }
    .up-search-arrow { display: none; }

    <c:if test="${canInlineEdit}">
        #edit-search-view-${id} {
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

<div id="cs-search-list-container-${id}">
    <c:if test="${canInlineEdit}">
        <span id="edit-search-view-${id}" class="label label-danger"><a class="inline-edit" data-toggle="modal" data-target="#search-view-edit-modal-${id}">Edit</a></span>
        <div id="search-view-edit-modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="search-view-edit-modal-${id}" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Search View Administration</h4>
                </div>
                <div class="modal-body">
                   <form id="edit-search-view-form-${id}" role="form">
                     <div class="form-group">
                         <label for="display-type-select-${id}">Display Type</label>
                         <select id="display-type-select-${id}" class="form-control">
                            <option value="static">Static</option>
                            <option value="dynamic">Dynamic</option>
                         </select>
                         <p id="display-type-select-help-${id}" class="help-block"></p>
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
        </div> <!-- /#search-view-edit-modal-${id} -->

        <script>
            $(document).ready(function() {

                $("#btn-save-${id}").click(function() {
                    var postData = {};
                    postData.requestType_${id} = 'SAVE_CONFIG';
                    postData.displayMode_${id} = $('#display-type-select-${id}').val();

                    $.ajax({
                      type: "POST",
                      data: postData,
                      url: "${cssearchViewAjaxHandler}",
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

    <div class="cs-search-view">
        <c:if test="${displayMode == 'dynamic'}">
            <div class="cs-search-view-dynamic text-center">
                <div>---------SEARCH---------</div>
                <div class="down-search-arrow">&#8609;</div>
                <div class="up-search-arrow">&#8607;</div>
            </div>
        </c:if>

        <form id="cs-search-view-form-${id}" role="form" class="col-md-6 col-md-offset-3 cs-search-view-form ${displayMode}">
            <div class="input-group col-md-8 col-md-offset-2">
                <input type="text" class="form-control" id="cs-search-field-${id}" placeholder="Find your services and products">
                <span class="input-group-addon">&#x1f50d;</span>
            </div>
            <div class="col-md-8 col-md-offset-2">
                <label class="radio-inline col-sm-3">
                    <input type="radio" name="search-options" id="site-search" value="site"> Site
                </label>
                <label class="radio-inline col-sm-4">
                    <input type="radio" name="search-options" id="digital-library-search" value="dl"> Digital Library
                </label>
                <label class="radio-inline">
                    <a href="#">Advanced Search</a>
                </label>
            </div>
        </form>
    </div>

</div>
<script>
    var setDisplayHelp = function() {
        var showStaticHelp = "Search box will always been displayed.";
        var showDynamicHelp = "Users will be able to toggle the search box visibility.";
        var selectedDisplayType = $('#display-type-select-${id}').val();
        if(selectedDisplayType == 'static') {
            $('#display-type-select-help-${id}').html(showStaticHelp);
        } else {
            $('#display-type-select-help-${id}').html(showDynamicHelp);
        }
    }

   $(document).ready(function() {
       var displayMode = '${displayMode}';
       displayMode = displayMode == '' ? 'static' : displayMode;
       $('#display-type-select-${id} option[value="'+displayMode+'"]').attr("selected", "selected");

        setDisplayHelp();

        $('#display-type-select-${id}').change(function() {
             setDisplayHelp();
        });

       $('.cs-search-view-dynamic').click(function() {
            if($('.down-search-arrow').is(':visible')) {
                $('.down-search-arrow').hide();
                $('.up-search-arrow').show();
                $('#cs-search-view-form-${id}').slideDown(300);
            } else {
                $('.down-search-arrow').show();
                $('.up-search-arrow').hide();
                $('#cs-search-view-form-${id}').slideUp(300);
            }
       });

      $('#cs-search-field-${id} + span').click(function() {
        console.log("performing search on search page");
      });

   });
</script>