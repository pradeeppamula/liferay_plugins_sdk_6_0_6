<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='suggestedGroupsAjaxHandler' id='suggestedGroupsAjaxHandler' />
<%-- Only show portlet if user is signed in --%>
<c:if test="${isSignedIn}">

	<style type="text/css">		
		#homepage-suggested-group-container-${id} {	
			border-top: ${portletBorderPixelTop}px solid #${portletBorderColorTop};
			border-right: ${portletBorderPixelRight}px solid #${portletBorderColorRight};
			border-bottom: ${portletBorderPixelBottom}px solid #${portletBorderColorBottom};
			border-left: ${portletBorderPixelLeft}px solid #${portletBorderColorLeft};		
			border-top-left-radius: ${portletTopLeftRadius}px ${portletTopLeftRadius}px;
			-moz-border-top-left-radius: ${portletTopLeftRadius}px ${portletTopLeftRadius}px;
			border-top-right-radius: ${portletTopRightRadius}px ${portletTopRightRadius}px;		
			-moz-border-top-right-radius: ${portletTopRightRadius}px ${portletTopRightRadius}px;		
			border-bottom-left-radius: ${portletBottomLeftRadius}px ${portletBottomLeftRadius}px;		
			-moz-border-bottom-left-radius: ${portletBottomLeftRadius}px ${portletBottomLeftRadius}px;
			border-bottom-right-radius: ${portletBottomRightRadius}px ${portletBottomRightRadius}px;
			-moz-border-bottom-right-radius: ${portletBottomRightRadius}px ${portletBottomRightRadius}px;	
			margin: ${innerMargins};
			background-color: #ffffff;	
			padding: 20px 20px;
		}

		#homepage-suggested-group-container-${id} .header {
			color: #999;
			font-size: 16px;
			line-height: 16px;
			font-weight: 500;
		}
		
		.suggested-group-img-container-${id} > img {
			-webkit-border-radius: 5px;
			-moz-border-radius: 5px;
			border-radius: 5px;
			width: 35px;
			height: 35px;
		}

		.suggested-group-img-container-${id} {
			margin-right: 5px;
		}

		.suggested-group-header {
			max-height: 40px;
		}

		.close-button {
			padding: 5px;
			filter: alpha(opacity=40);
  			opacity: 0.4;
			-webkit-transition: opacity 0.1s ease-in-out;
			-moz-transition: opacity 0.1s ease-in-out;
			-ms-transition: opacity 0.1s ease-in-out;
			-o-transition: opacity 0.1s ease-in-out;
			transition: opacity 0.1s ease-in-out;
		}
		.suggested-group-container:hover div div > a.close-button {
			filter: alpha(opacity=100);
  			opacity: 1.0;
		}
		/* RESPONSIVE STYLES */

         /* Small devices (tablets, 992px and down) */
        @media (max-width: 992px) {
                .suggested-group-container {
                    padding: 10px 0;
                    height: 150px;
                }
        }
         /* Large devices (tablets, desktops, 993px and up) */
         @media (min-width: 993px) {
                .suggested-group-container {
                    padding: 10px 0;
                    height: 110px;
                }
         }
	</style>

	<div id="homepage-suggested-group-container-${id}">
	</div> <!-- /#homepage-suggested-group-container-${id} -->

	<script>

	    Ember.TEMPLATES['groups'] = Ember.Handlebars.compile('<a class="text-muted pull-right" href="/portal/web/myhome/suggested-content/?type=group">View all</a> <p class="header">Suggested communities</p> <div id="join-group-success-${id}" {{ bindAttr class=":alert :alert-block :alert-success :fade :hide :success" }}> <button type="button" class="close" aria-hidden="true" {{ action "hideJoinSuccess" }}>x</button> <h4><i class="icon-check-sign icon-3x icon-fixed-width"></i>Community joined!</h4> </div> <!-- /#join-group-success-${id} --> <div  id="join-group-error-${id}" {{ bindAttr class=":alert :alert-block :alert-danger :fade :hide  :error" }}> <button type="button" class="close" aria-hidden="true" {{ action "hideJoinError"}}>x</button> <h4><i class="icon-exclamation-sign icon-3x icon-fixed-width"></i>There was a problem joining this community, please try again or contact help@computer.org.</h4> </div> <!-- /#join-group-error-${id} --> <div id="join-group-confirm-${id}" {{ bindAttr class=":alert :alert-block :alert-warning :fade :hide :warning" }}> <button type="button" class="close" aria-hidden="true"  {{ action "hideJoinConfirm" }}>x</button> <p> <i class="icon-warning-sign icon-3x icon-fixed-width"></i> Are you sure you would like to join community <strong>{{ selectedGroup.name }}</strong>? <a class="btn btn-default" href="#" {{ action "joinGroup" }} >Yes</a> </p> </div> <!-- /#join-group-confirm-${id} --> <div class="suggested-group-container"> <div id="suggested-group-${id}-0" class="media"> <div class="media-body"> <h5 class="media-heading suggested-group-header">{{group0.shortName}}</h5> <a class="btn btn-default btn-sm pull-right" href="#"  {{ action "joinGroupConfirm" group0}}>Join</a> <a class="close-button pull-right" href="#" {{action "removeGroup" group0}}><i class="icon-remove-sign"></i></a> {{group0.shortDescription}} </div> </div> <!-- /.media --> </div><!-- /.suggested-group-container --> <div class="suggested-group-container"> <div id="suggested-group-${id}-1" class="media"> <div class="media-body"> <h5 class="media-heading suggested-group-header">{{group1.shortName}}</h5> <a class="btn btn-default btn-sm pull-right" href="#" {{action "joinGroupConfirm" group1}}>Join</a> <a class="close-button pull-right" href="#" {{action "removeGroup" group1}}><i class="icon-remove-sign"></i></a> {{group1.shortDescription}} </div> </div> <!-- /.media --> </div><!-- /.suggested-group-container --> <div class="suggested-group-container"> <div id="suggested-group-${id}-2" class="media"> <div class="media-body"> <h5 class="media-heading suggested-group-header">{{group2.shortName}}</h5> <a class="btn btn-default btn-sm pull-right" href="#"  {{ action "joinGroupConfirm" group2}}>Join</a> <a class="close-button pull-right" href="#" {{action "removeGroup" group2}}><i class="icon-remove-sign"></i></a> {{group2.shortDescription}} </div> </div> <!-- /.media --> </div><!-- /.suggested-group-container --> <div class="text-right"> <!-- TODO: Create feedback module Phase 2/3? <a href="#"><i class="icon-comment icon-fixed-width"></i>Feedback</a> --> </div>');
		// initialize the suggested group Ember App
		SuggestedGroupApp = Ember.Application.create({
			 rootElement: '#homepage-suggested-group-container-${id}'
		});

		// define the suggested group model
		SuggestedGroupApp.Group = Ember.Object.extend({
		    groupId: null,
		  	name: null,
		  	description: null,
		  	position: null, 
		  	shortName: function() {
    			var name = this.get('name');
			    return (name.length > 40) ? name.substring(0,40) + '...' : name;
			}.property('name'),
		  	shortDescription: function() {
    			var description = this.get('description');
			    return (description.length > 80) ? description.substring(0,80) + '...' : description;
			}.property('description')
		});

		// create Ember.ArrayController for managing the suggested groups models/views
		SuggestedGroupApp.GroupsController = Ember.ArrayController.extend({
		  content: [],
		  selectedGroup: null,
		  group0: null,
		  group1: null,
		  group2: null,
		  actions: {
              showJoinConfirm: function() {
                    $('#join-group-confirm-${id}').addClass("in");
                    $('#join-group-confirm-${id}').removeClass("hide");
              },
              hideJoinConfirm: function() {
                    $('#join-group-confirm-${id}').addClass('hide');
                    $('#join-group-confirm-${id}').removeClass('in');
              },
               showJoinSuccess: function(confirmId) {
                   $('#join-group-success-${id}').addClass("in");
                   $('#join-group-success-${id}').removeClass("hide");
              },
              hideJoinSuccess: function(confirmId) {
                   $('#join-group-success-${id}').addClass('hide');
                   $('#join-group-success-${id}').removeClass('in');
              },
               showJoinError: function(confirmId) {
                     $('#join-group-error-${id}').addClass("in");
                     $('#join-group-error-${id}').removeClass("hide");
                },
                hideJoinError: function(confirmId) {
                     $('#join-group-error-${id}').addClass('hide');
                     $('#join-group-error-${id}').removeClass('in');
                },
		      joinGroupConfirm: function(group) {
                 this.set('selectedGroup', group);
                 this.send('showJoinConfirm');
		      },
		      joinGroup: function() {
		        // first hide the confirmation alert
		        this.send('hideJoinConfirm');
		        // grab the group that the user wanted to join
		        var selectedGroup = this.get('selectedGroup');
		        // build the post data for the server
		        var postData = {};
                postData.requestType_${id} = 'JOIN_GROUP';
                postData.groupId_${id} = selectedGroup.groupId;
                 // post to portlet to join the group
                $.post("${suggestedGroupsAjaxHandler}", postData).then(function(response) {
                   if(response == 200) {
                        // show the success message
                        this.send('showJoinSuccess');
                        // remove the joined group from the list
                        this.send('removeGroup', selectedGroup);
                        this.removeObject(selectedGroup);

                        // clear the selected group
                        this.set('selectedGroup', {});
                        _self = this;
                        // clear out the success after a few seconds
                        setTimeout(function() {
                            _self.send('hideJoinSuccess');
                        }, 5000);
                   } else {
                       this.send('showJoinError');
                   }
                }.bind(this), function() {
                  this.send('showJoinError');
                }.bind(this));
		      },
              setGroupData : function(data) {
                // iterate over the groups setting them on the controller
                var idx = 0;
                for (idx; idx < data.length; idx++) {
                    var item = SuggestedGroupApp.Group.create({
                        groupId: data[idx].groupId,
                        name: data[idx].name,
                        description: data[idx].description,
                        position: idx
                    });
                    if(idx <3) {
                        this.set('group'+idx, item);
                    } else {
                        // just add the rest to the controller list
                        this.pushObject(item);
                    }
                };
              },
              removeGroup: function(group) {
                var position = group.position;
                // fade out the current container for the group
                $("#suggested-group-${id}-"+position).hide();

                // finally post to the server to retrieve the data
                var postData = {};
                postData.requestType_${id} = 'LOAD_SUGGESTED_GROUP_DATA';
                postData.limit_${id} = 5;
                // if there are any extra items in the content list, use one of those
                if(this.get('length') > 0) {
                    // first grab the first item off the list
                    var item = this.get('content')[0];
                    // then remove the item
                    this.get('content').removeAt(0);
                    item.position = position;
                    this.set('group'+position, item);
                    setTimeout(function() {
                        // fade in the current container for the group
                        $("#suggested-group-${id}-"+position).fadeIn();
                    }, 1000);
                } else {
                    // else load up more items for the content list
                    // post to portlet to retreive the suggested group
                    $.post("${suggestedGroupsAjaxHandler}", postData).then(function(response) {
                        var idx = 0;
                        for (idx; idx < response.length; idx++) {
                            var item = SuggestedGroupApp.Group.create({
                                groupId: response[idx].groupId,
                                name: response[idx].name,
                                description: response[idx].description
                            });
                            if(idx==0) {
                                item.position = position;
                                this.set('group'+position, item);
                            } else {
                                // just add the rest to the controller list
                                this.pushObject(item);
                            }
                        };
                        // fade in the current container for the group
                        $("#suggested-group-${id}-"+position).fadeIn();
                    }.bind(this), function() {
                      // TODO: show error
                    }.bind(this));
                }
	          }
	      }
		});

		// force the root path to utilize the "groups" template 
		SuggestedGroupApp.Router.map(function() {
		  this.route("groups", { path: "/" });
		});

		// for the initial index view
		SuggestedGroupApp.GroupsRoute = Ember.Route.extend({
		  setupController: function(controller) {
		    Ember.Instrumentation.subscribe("SuggestedGroupApp.setGroupData", {
		      before: function (name, timestamp, payload) {
		      	controller.send('setGroupData', payload);
		      },
		      after: function () { }
		    });
		  }
		});
	</script>
	<script>
		$(document).ready(function() {
			// finally post to the server to retrieve the data
		  	var postData = {};
			postData.requestType_${id} = 'LOAD_SUGGESTED_GROUP_DATA';
			postData.limit_${id} = 5;

			// post to portlet to retreive the suggested groups 
			var jqxhr = $.post("${suggestedGroupsAjaxHandler}", postData)
			    .done(function(response) {
			        // if there are no groups, hide this portlet
			        if(response === undefined || response.length == 0) {
			           $('#homepage-suggested-group-container-${id}').hide();
			        }
			    	// send the suggested groups data list to the Ember controller to be displayed and managed
					Ember.Instrumentation.instrument('SuggestedGroupApp.setGroupData', response);
			    })
			    .fail(function(error) {
			        var eMsg = "SuggestedGroup - Error loading the suggested groups: " + error.message;
			        Ember.Logger.error(eMsg);
                    var logData = {};
                    logData.message = eMsg;
                    Log.error(logData);
			     })
			    .always(function() {});
		});	
	</script>
</c:if>
