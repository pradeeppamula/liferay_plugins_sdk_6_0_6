<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${isAuthenticated && hasAccess}">

	    <div class="navbar navbar-midnight navbar-static-top" role="navigation">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="/">CSTheme Administration</a>
            </div>
            <div class="navbar-collapse collapse">
              <form class="navbar-form navbar-right" role="form">
                <select id="community-list" name="community-list" class="selectpicker">
                  <option value="-1" selected="selected">Select community</option>
                </select>
                <a class="btn btn-primary" data-toggle="modal" data-target=".add-community-modal"><i class="fa fa-plus"></i></a>
              </form>
            </div>
          </div>
        </div>

        <div class="container-fluid">

            <!-- Alerts -->
            <div id="alert-main-success" class="alert alert-success">
                <strong><i class="fa fa-check-circle"></i></strong> Your community was updated.
            </div>
            <div id="alert-remove-success" class="alert alert-success">
              <strong><i class="fa fa-check-circle"></i></strong> Your community was removed.
            </div>

            <div id="alert-confirm-remove" class="alert alert-warning">
             <a href="#" class="close">&times;</a>
             <strong><i class="fa fa-exclamation-triangle"></i> </strong> Are you sure you would like to remove this community? (Note: this will affect the styling for all the community's sub pages) <a id="confirm-remove-btn" href="#" class="btn btn-warning">Yes</a>
            </div>
          <div id="alert-main-danger" class="alert alert-danger">
            <a href="#" class="close">&times;</a>
            <strong><i class="fa fa-exclamation-circle"></i></strong> There seems to be a problem:
            <span id="error-message"></span>
          </div>
          <!-- End Alerts -->

          <div class="row sub-header-section">
            <div class="col-md-2 col-sm-2 col-xs-12">
              <select id="theme-segments" name="theme-segments" class="selectpicker">
                <option value="-1" selected="selected">Select segment</option>
                <option value="0">libraries</option>
                <option value="1">header</option>
                <option value="2">preBody</option>
                <option value="3">navigation</option>
                <option value="4">footer</option>
                <option value="5">postBody</option>
              </select>
            </div>
            <div id="segment-info" class="col-md-8 col-sm-8 col-xs-12">
            </div>
            <div class="pull-right">
              <button id="save-btn" class="btn btn-primary">Save</button>
              <button id="remove-btn" class="btn btn-danger">Remove</button>
            </div>
          </div>
        </div>

        <!-- Add Community Modal -->
        <div class="modal fade add-community-modal" tabindex="-1" role="dialog" aria-labelledby="add-community-modal" aria-hidden="true">
          <div class="modal-dialog modal-sm">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Add Community</h4>
              </div>
              <div class="modal-body">
                <div id="alert-new-community-success" class="alert alert-success">
                  <strong><i class="fa fa-check-circle"></i> </strong> Saved.
                </div>
                <div id="alert-new-community-danger" class="alert alert-danger alert-dismissable">
                  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                  <strong><i class="fa fa-info-circle"></i></strong> There seems to be a problem:
                  <span id="error-message-nc"></span>
                </div>
                <p class="text-primary"><i class="fa fa-exclamation-circle"></i> Please do not include white spaces, or they will be removed.</p>
                <form class="form-inline" role="form">
                  <div class="form-group">
                    <input type="text" class="form-control" id="community-name" placeholder="Community Name">
                  </div>
                </form>
              </div>
              <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal">Close</a>
                <a id="add-community-button" href="#" class="btn btn-success">Add</a>
              </div>
            </div>
          </div>
        </div>

        <!-- Ace Editor -->
        <div id="ace-ide-container">
          <div id="editor"><div>
        </div>

    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.5.4/bootstrap-select.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/ace/1.1.3/ace.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">

      // global URL variable
      // TODO: make port number a configurable property
      var baseURL = 'http://' + window.location.hostname + ':8880';
      $( document ).ready(function() {
        // perform any initializations
        initApp();

        // Handle any alert closings
        $("a.close").on("click", function() {
           $(this).parent().fadeOut(1);
        });

        // Handle adding community clicks
        $('#add-community-button').on("click", function() {
          // first validate
          var name = $('#community-name').val();
          if(name == undefined || name.trim() == '') {
            $("#community-name").parent().addClass("has-error");
            return;
          }
          $("#community-name").parent().removeClass("has-error");
          postCommunity(name.replace(/\s+/g, '').toLowerCase());
        });

        // Handle save button clicks
        $("#save-btn").on("click", function() {
          var selectedCommunity = $('#community-list').find(":selected").val();
          $.ajax({
              type: "PUT",
              data: Community,
              url: baseURL+'/coms/'+selectedCommunity,
              beforeSend: function() {
                $("#save-btn").prop('disabled', true);
              },
              success: function (data) {
                $("#alert-main-danger").fadeOut(0);
                $("#alert-main-success").fadeIn(100);
                $("#save-btn").prop('disabled', false);
                setTimeout(function() {$("#alert-main-success").fadeOut(300);},3000);
              },
              error: function (data) {
                console.error(data);
                $("#alert-main-danger").fadeIn(100);
                $("#error-message").html(data.responseJSON.error);
                $("#save-btn").prop('disabled', false);
              }
          });
        });

        // Handle remove button clicks
        $("#remove-btn").on("click", function() {
          // first show the confirm
          $("#alert-confirm-remove").fadeIn(0);
        });

        // Handle the remove confirmation click
        $("#confirm-remove-btn").on("click", function() {
           $("#alert-confirm-remove").fadeOut(0);
           removeCommunity();
        });

        // Handle community change events
        $("#community-list").change(function() {
          resetState();
          var selectedCommunity = $('#community-list').find(":selected").val();

          if(selectedCommunity == "-1") {
            $("#save-btn").prop('disabled', true);
            $("#remove-btn").prop('disabled', true);
            return;
          }

          $.ajax({
              type: "GET",
              url: baseURL + '/coms/'+selectedCommunity,
              success: function (community) {
                $("#alert-main-danger").fadeOut(0);
                window.Community = community;
                $("#remove-btn").prop('disabled', false);
                $("#save-btn").prop('disabled', true);
              },
              error: function (data) {
                console.error(data);
                $("#alert-main-danger").fadeIn(100);
                $("#error-message").html("The community was not retrieved properly.");
                resetState();
              }
          });
        });

        AceEditor.getSession().on('change', function(e) {
          if(window.Community === undefined) return;
          var selectedSegment = $('#theme-segments').find(":selected").val();
          switch(parseInt(selectedSegment)) {
            case 0: Community.libraries = AceEditor.getValue();
              break;
            case 1: Community.header = AceEditor.getValue();
              break;
            case 2: Community.preBody = AceEditor.getValue();
              break;
            case 3: Community.navigation = AceEditor.getValue();
              break;
            case 4: Community.footer = AceEditor.getValue();
              break;
            case 5: Community.postBody = AceEditor.getValue();
              break;
          }
        });

        // handle the theme segment changes
        $("#theme-segments").change(function() {
          if(window.Community === undefined) return;
          var selectedSegment = $('#theme-segments').find(":selected").val();
          var selectedCommunity = $('#community-list').find(":selected").val();
          $("#save-btn").prop('disabled', (selectedSegment == "-1") && (selectedCommunity == "-1"));
          var icon = "<i class=\"fa fa-info-circle text-primary\"></i>";
          var message = "";
          switch(parseInt(selectedSegment)) {
            case -1: AceEditor.setValue("");
                $("#segment-info").html("");
              break;
            case 0: AceEditor.setValue(Community.libraries);
              message = " Any css, js, or link HTML tags that will be placed in the head tag.";
              break;
            case 1: AceEditor.setValue(Community.header);
              message =" HTML designated to the header of the theme, located right below the body HTML tag.";
              break;
            case 2: AceEditor.setValue(Community.preBody);
              message =" Any HTML that will be placed above the main page content.";
              break;
            case 3: AceEditor.setValue(Community.navigation);
              message =" Any HTML that is for site navigation, located below the header section.";
              break;
            case 4: AceEditor.setValue(Community.footer);
              message =" Any HTML that will serve as the site footer (including JS libs).";
              break;
            case 5: AceEditor.setValue(Community.postBody);
              message =" Any HTML that will be placed below the main page content.";
              break;
          }

          if(message != "") $("#segment-info").html(icon + message);
        });
      });


      /**
       * This function will create a new community based on
       * the passed in community name and post it to the
       * /coms endpoint.
       * @param String communityName
       */
      var postCommunity = function(communityName) {
        var community = {
           "community" : communityName,
           "url" : "/portal/web/" + communityName,
           "type" : "community",
           "showInfo" : "1",
           "libraries" : [""],
           "header": [""],
           "footer" : [""],
           "preBody" : [""],
           "navigation" : [""],
           "postBody" : [""]
        };

        // post the community to the server
        $.ajax({
            type: "POST",
            url: baseURL+'/coms',
            data: community,
            beforeSend: function() {
              $("#add-community-button").prop('disabled', true);
            },
            success: function (data) {
              $("#alert-new-community-danger").fadeOut(0);
              $("#alert-new-community-success").fadeIn(100);
              $("#add-community-button").prop('disabled', false);
              resetState();
              loadCommunities();
              setTimeout(function() {$("#alert-new-community-success").fadeOut(300);},3000);
            },
            error: function (data) {
              console.error(data);
              $("#alert-new-community-danger").fadeIn(100);
              $("#error-message-nc").html("Your community was not added.");
              $("#add-community-button").prop('disabled', false);
            }
        });
      }

      /**
       * This function will load all the communities
       * into the select drop down list.
       */
      var loadCommunities = function() {
        $.ajax({
            type: "GET",
            url: baseURL+'/coms',
            success: function (communities) {
              $("#alert-main-danger").fadeOut(0);
              $('#community-list').html('<option value="-1" selected="selected">Select community</option>');
              $.each(communities, function( index, value ) {
                $("#community-list").append("<option>"+value+"</option>");
              });
              $("select[id='community-list']").selectpicker({style: 'btn-normal', size: false, width: 'auto'});
              $("select[id='community-list']").selectpicker('refresh');
              $('#community-name').val('');
              $("#save-btn").prop('disabled', true);
              $("#remove-btn").prop('disabled', true);
            },
            error: function (data) {
              console.error(data);
              $("#alert-main-danger").fadeIn(100);
              $("#error-message").html("The communities were not retrieved properly.");
            }
        });
      }

      /**
       * This function will remove the community
       * based on the community name.
       */
      var removeCommunity = function() {
          var selectedCommunity = $('#community-list').find(":selected").val();
          $.ajax({
              type: "DELETE",
              data: Community,
              url: baseURL+'/coms/'+selectedCommunity,
              success: function (data) {
                resetState();
                // reload the communities
                loadCommunities();
                $("#save-btn").prop('disabled', true);
                $("#remove-btn").prop('disabled', true);
                $("#alert-main-danger").fadeOut(0);
                $("#alert-remove-success").fadeIn(100);
                setTimeout(function() {$("#alert-remove-success").fadeOut(300);},3000);
              },
              error: function (data) {
                console.error(data);
                $("#alert-main-danger").fadeIn(100);
                $("#error-message").html("The community was not removed properly.");
              }
          });
      }

      /**
       * This function will perform any necessary initializations
       * for the application.
       */
      var initApp = function() {
        $("select[id='theme-segments']").selectpicker({style: 'btn-success', size: false, width: 'auto'});

        // initially disable the save and remove buttons
        $("#save-btn").prop('disabled', true);
        $("#remove-btn").prop('disabled', true);

        // initially load the communities after page load
        loadCommunities();
      }

      /**
       * This function will reset the state of the
       * application.
       */
      function resetState() {
         $('#theme-segments').selectpicker('val', '-1');
         AceEditor.setValue("");
         window.Community = undefined;
      }

      var editor = ace.edit("editor");
      editor.setValue("");
      editor.setTheme("ace/theme/monokai");
      editor.getSession().setMode("ace/mode/html");
      editor.getSession().setTabSize(4);
      document.getElementById('editor').style.fontSize='12px';
      editor.getSession().setUseWrapMode(true);
      window.AceEditor = editor;
    </script>
</c:if>