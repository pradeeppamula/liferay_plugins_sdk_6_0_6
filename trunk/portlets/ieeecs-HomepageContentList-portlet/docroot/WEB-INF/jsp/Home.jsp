<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='ajaxHandlerContentList' id='ajaxHandlerContentList' />
<%-- Only show portlet if user is signed in --%>
<c:if test="${isSignedIn}">

	<style type="text/css">
		#homepage-content-list-container-${id} {
			font-size: 12px;
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
		}

		.content-list {
			width: 100%;
		}

		.content-list-item-media-container {
			height: 110px;
			width: 110px;
		}
		.content-list-item-media-container > img {
		    width: 100%;
		}

		.content-list-item-type {
			text-transform: lowercase;
			color: #ffffff;
			padding: 0 5px;
			width: 70px;
			text-align: center;
			font-size: 12px;
			position: absolute;
			top: 0;
			right: 0;
		}

		.content-list-item {
			background-color: #ffffff;
			position: relative;
			border-bottom: 1px solid #eeeeee;
		}
		
		.content-list-item-details:hover {
			cursor: pointer;		
		}

		.content-list-item-contracted:hover {
			background-color: #e7e7e7;
		}
		
		.content-list-item-footer {
			text-align: center;
			margin: 0 auto;
			color: #000000;
			line-height: 30px;
		}

		 .content-list-search-bar {
            background-color: #ffffff;
            height: 50px;
            padding: 7px 15px;
            color: #2a72b2;
            line-height: 40px;
            border-bottom: 1px solid #eeeeee;
            font-weight: 500;
            font-size: 1.3em;
        }

        /* Small devices (tablets, 992px and down) */
        @media (max-width: 992px) {
           .content-list-search-bar {
           			background-color: #ffffff;
           			height: 50px;
           			padding: 7px 15px;
           			color: #2a72b2;
           			border-bottom: 1px solid #eeeeee;
           			font-weight: 500;
                    font-size: 1.3em;
           		}
        }
        /* Large devices (tablets, desktops, 993px and up) */
         @media (min-width: 993px) {
            .content-list-search-bar {
                background-color: #ffffff;
                height: 50px;
                padding: 7px 15px;
                color: #2a72b2;
                line-height: 40px;
                border-bottom: 1px solid #eeeeee;
                font-weight: 500;
                font-size: 1.3em;
            }
        }
		.content-list-item .content-done-column, .content-list-item .content-loading-column{
			display: none;
		}

		.content-list-item:hover {
            background-color: #e7e7e7;
        }

		.content-list-item-done {
			display: block;
			background-color: #eeeeee;
			height: 100px;
			line-height: 115px;
			text-align: center;
			color: #000000;
		}
		.content-list-item-loading {
			display: block;
			height: 50px;
			line-height: 50px;
			text-align: center;
			color: #2a72b2;
			font-weight: bolder;
			background-color: #ffffff;
		}

		.content-list-item-done .content-item-column, 
		.content-list-item-done .content-loading-column {
			display: none;
		}

		.content-list-item-loading .content-item-column, 
		.content-list-item-loading .content-done-column {
			display: none;
		}

		.package-title {
			padding: 10px 60px 0px 10px;
			color: #2a72b2;
			font-size: 14px;
			font-weight: 500;
			line-height: 1.1;
		}

		.package-title-sm-spacer {height:10px;}
		.join-button-container {
            padding-left: 28px;
		}
        .ie-reset-button-align {
          top: -4px;
        }
	</style>

  	<div id="homepage-content-list-container-${id}" class="col-md-12 col-sm-12">
	</div>

	<script>
	    // compile the handlebar templates to be used by Ember
	    Ember.TEMPLATES['contentList'] = Ember.Handlebars.compile('<div class="content-list-search-bar"> <form class="col-md-4 col-sm-4 pull-right"> <div class="input-group"> {{view Ember.TextField valueBinding="filter" classNames="form-control" placeholder="Filter your content"}} <span class="input-group-btn"> <button class="btn btn-primary myhome-tooltip" type="button" {{action "clearFilter"}} data-toggle="tooltip" data-placement="top" data-original-title="Reset Filter"> <i class="icon-remove-sign"></i> </button> </span> </div><!-- /.input-group --> </form> <div class="col-md-8 col-sm-8"> <span>Suggested Content</span> </div> </div> <div class="content-list"> {{#each item in filteredContent}} <div {{bindAttr class=":col-md-12 :col-sm-12 item.contentTypeCSSClass"}} > {{#if item.isGroup}} <div {{ bindAttr class=":alert :alert-block :alert-success :fade :hide :success item.cid" }}> <button type="button" class="close" aria-hidden="true" {{ action "hideJoinSuccess" item}}>x</button> <h4><i class="icon-check-sign icon-3x icon-fixed-width"></i>Community joined!</h4> </div> <!-- /.success --> <div {{ bindAttr class=":alert :alert-block :alert-danger :fade :hide :error item.cid" }}> <button type="button" class="close" aria-hidden="true" {{ action "hideJoinError" item}}>x</button> <h4><i class="icon-exclamation-sign icon-3x icon-fixed-width"></i>There was a problem joining this community, please try again or contact help@computer.org.</h4> </div> <!-- /.error --> <div {{ bindAttr class=":alert :alert-block :alert-warning :fade :hide :warning item.cid" }}> <button type="button" class="close" aria-hidden="true"  {{ action "hideJoinConfirm" item}}>x</button> <p> <i class="icon-warning-sign icon-3x icon-fixed-width"></i> Are you sure you would like to join community <strong>{{ item.title }}</strong>? <a class="btn btn-default" href="#" {{ action "joinGroup" }} >Yes</a> </p> </div> <!-- /.warning --> {{/if}} <div class="content-item-column"> <div class="content-list-item-details" {{ action "itemClick" item }}> <div {{bindAttr class=":content-list-item-type item.contentType"}}> {{item.contentType}} </div> <!-- this is a spacer that is visible only for the small (tablet) sized devices --> <div class="clearfix package-title-sm-spacer visible-sm"></div> <p class="package-title"></p> <div class="media"> <div {{bindAttr class=":pull-left :media-object :content-list-item-media-container"}}> {{#if item.isGroup}} <div class="join-button-container"> <button class="btn btn-primary" {{action "joinGroupConfirm" item}}> <i class="icon-plus-sign icon-fixed-width"></i>Join </button> </div> {{else}} <img src="/ieeecs-HomepageContentList-portlet/images/default-content.jpg"/> {{/if}} </div> <div class="media-body"> <h4 class="media-heading">{{item.title}}</h4> {{#unless item.isGroup}} <span class="text-muted">{{item.publisher}} {{#if item.shortSummary}}&middot;{{/if}}</span> {{/unless}} {{item.shortSummary}} </div> </div> <!-- /.media --> </div> <!-- /.content-list-item-details --> </div> <!-- /.content-item-column --> <div class="content-done-column"><i class="icon-check-sign icon-3x"></i></div> <div class="content-loading-column"><i class="icon-spinner icon-spin icon-large"></i> Loading</div> </div> <!-- /.contentTypeCSSClass --> {{/each}} </div><!-- /.content-list -->');

		// define the Content List Ember App
		ContentListApp = Ember.Application.create({
			 rootElement: '#homepage-content-list-container-${id}'
		});

        // have the root serve up the content list template
		ContentListApp.Router.map(function() {
			this.route("contentList", { path: "/" });
		});

         // This is the Content List Model for the template
		ContentListApp.Content = Ember.Object.extend({
		    isGroup: false,
		    itemId: "",
		    cid: "",
			publisher: "",
		  	summary: "",
		  	publicationDate: null,
			title: "",
			contentType: "",
			contentTypeCSSClass: 'content-list-item',
			shortSummary: function() {
    			var summary = this.get('summary');
			    return (summary.length > 450) ? summary.substring(0,450) + '...' : summary;
			}.property('summary'), 
			publicationDateFormatted: function() {
    			var publicationDate = new Date(this.get('publicationDate'));
    			var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
			    return months[publicationDate.getMonth()] + ' ' + publicationDate.getDate() + ', ' + publicationDate.getFullYear();
			}.property('publicationDate')
		});
		
		// create Ember.ArrayController to handle the content list actions
		ContentListApp.ContentListController = Ember.ArrayController.extend({
		  content: [], 
		  userPurchaseData: null,
		  selectedGroup: null,
		  filter: '',
		  type: '',
		  keywords: '',
		  isLoading: false,
		  isDoneLoadingAllContent: false,
		  isFilterOn: false,
		  currentUnitIndex: 0,
		  maxContentLimit: 50,
		  groupSize: 10,
		  totalHits: 0,

		  /**
           * This is the required actions object that Ember wants you put
           * your functions that handle actions from Handlebars templates.
           */
		  actions: {

		      /**
		       * Helper function to clear out the content list filter so
		       * that all the items will be visible.
		       */
		      clearFilter: function() {
                   this.set('isFilterOn', false);
                   this.set('filter', "");
              },

              /**
               * This function will be executed once an item is clicked.
               * Depending on the item, it will navigate to the content page
               * to display the content item to the user.
               * @param Object item - the item to be displayed
               */
		      itemClick: function(item) {
		        if(item != undefined) {
                    var type = this.get('type');
                    if(type == 'article') {
                        // navigate to the article page
                        window.location = '/portal/web/myhome/content?type=article&cid='+item.cid;
                    }
                }
		      },

		      /**
		       * This helper function will set the controller properties that are
		       * necessary for loading the content.  The data that comes in as a parameter
		       * will come from another Ember App, depending on the content type.
		       * @param Array data - the payload containing data
		       */
		      setLoadingMetaData: function(data) {
		        this.set('type', data[0].contentType);
		        this.set('keywords', data[0].keywords);
		      },

		      /**
		       * This function will load up the content list based on the content type and
		       * display it to the user
		       * @param Object initialLoad
		       */
              loadContent: function(initialLoad) {
                var _self = this;
                var isLoading = _self.get('isLoading');
                var isFilterOn = _self.get('isFilterOn');

                // if the controller is not already loading data, and the filter is not activated
                // then we can proceed with loading more data
                if(!isLoading && !isFilterOn) {
                    // create the temp model that will represent the "loading" view
                    var loadingContentItem = ContentListApp.Content.create({
                          contentTypeCSSClass: "content-list-item-loading"
                    });
                    // add the "loading" object to the content for the client to see that loading is happening
                    _self.pushObject(loadingContentItem);
                    _self.set('isLoading', true);

                    // grab any necessary properties for the load
                    var currentUnitIndex = _self.get('currentUnitIndex');
                    var groupSize = _self.get('groupSize');
                    var contentList = _self.get('contentList');
                    var type = _self.get('type');
                    var keywords = _self.get('keywords');
                    var maxContentLimit = _self.get('maxContentLimit');
                    var totalHits = _self.get('totalHits');
                    var count=0;
                    var url = '';

                    // create the data object for the load
                    var data = {};
                    // check to see if we are on the last set of results
                    if(totalHits > 0 && ((totalHits - currentUnitIndex) < groupSize)) {
                        groupSize = totalHits - currentUnitIndex;
                    }

                    // if the content type is an article we build the request data for article types
                    if(type == 'article') {
                        url = '${$elasticSearchURL}/content/_search';
                        // build the  json data for the search
                        data = {
                            "query" : {
                                "multi_match" : {
                                    "fields" : ["keywords"],
                                    "query" : keywords,
                                    "type" : "prefix"
                                }
                            },
                            "from" : currentUnitIndex,
                            "size" : groupSize,
                        }

                         data.filter = {"bool":{"must":[]}};
                         var terms = { "terms" : { "contentType": [] } };
                         // add the article filter
                         terms.terms.contentType.pushObject("article");
                         data.filter.bool.must.pushObject(terms);

                         // stringify the data for ES
                         data = JSON.stringify(data);
                    }
                    // if we content type is group we set the request URL for group
                    else if (type == 'group') {
                        url =  "${ajaxHandlerContentList}"
                        data.requestType_${id} = 'LOAD_SUGGESTED_GROUP_DATA';
                        data.limit_${id} = maxContentLimit;
                    }

                    // load the data based on the type from the data source
                    jQuery.ajax({
                        type: 'post',
                        url: url,
                        data: data,
                        success: function(response) {
                          // loading is over, so we can remove the "loading" item
                          _self.removeObject(loadingContentItem);
                          // update the total number of results if necessary
                          if(type == 'article') {
                            totalHits = (totalHits == 0) ? response.hits.total : totalHits;
                            response = response.hits.hits;
                          } else if(type == 'group') {
                            totalHits = (totalHits == 0) ? response.length : totalHits;
                          }
                          _self.set('totalHits', totalHits);
                          var count = 0;
                          response.forEach(function(item) {
                               count++;
                               currentUnitIndex++;

                               var contentItem = ContentListApp.Content.create({
                                   itemId: ''+count+''
                               });

                               // build the content model object for the type
                                if(type == 'article') {
                                   contentItem = _self.hydrateArticleItem(contentItem, item);
                                } else if(type == 'group') {
                                   contentItem = _self.hydrateGroupItem(contentItem, item);
                                }

                                // now add the content item to the controller
                                _self.pushObject(contentItem);
                            });
                            _self.set('currentUnitIndex', currentUnitIndex);
                            _self.set('isLoading', false);

                            // If we have reached the limit of desired number of content items,
                            // destroy the infinite scroll
                            if (currentUnitIndex == maxContentLimit || currentUnitIndex == totalHits) {
                                // if we are at the last item, destroy the plugin instance
                                $('#homepage-content-list-container-${id} div.content-list')
                                    .infiniteScrollHelper('destroy');
                                    _self.set('isDoneLoadingAllContent', true);
                                    var doneContentItem = ContentListApp.Content.create({
                                          contentTypeCSSClass: "content-list-item-done"
                                    });
                                    _self.pushObject(doneContentItem);
                            }
                         },
                      error: function(error) {
                          var eMsg = "ContentList - An error occurred when loading the content: " + error.message;
                          Ember.Logger.error(eMsg);
                          var logData = {};
                          logData.message = eMsg;
                          Log.error(logData);
                      },
                      dataType: 'json'
                     });
                }
              },
               showJoinConfirm: function(group) {
                    $('.warning.'+group.cid).addClass("in");
                    $('.warning.'+group.cid).removeClass("hide");
              },
              hideJoinConfirm: function(group) {
                    $('.warning.'+group.cid).addClass('hide');
                    $('.warning.'+group.cid).removeClass('in');
              },
               showJoinSuccess: function(group) {
                   $('.success.'+group.cid).addClass("in");
                   $('.success.'+group.cid).removeClass("hide");
              },
              hideJoinSuccess: function(group) {
                   $('.success.'+group.cid).addClass('hide');
                   $('.success.'+group.cid).removeClass('in');
              },
           showJoinError: function(group) {
                 $('.error.'+group.cid).addClass("in");
                 $('.error.'+group.cid).removeClass("hide");
            },
            hideJoinError: function(group) {
                 $('.error.'+group.cid).addClass('hide');
                 $('.error.'+group.cid).removeClass('in');
            },
            joinGroupConfirm: function(group) {
                 this.set('selectedGroup', group);
                 this.send('showJoinConfirm', group);
            },

            /**
             * This function will let the user join a group that they have selected.
             */
            joinGroup: function() {
              _self = this;
              // grab the selected group to be joined
              var selectedGroup = this.get('selectedGroup');
              // first hide the confirmation alert
              this.send('hideJoinConfirm', selectedGroup);

               // build the post data for the server to add the user to the group
               var postData = {};
                postData.requestType_${id} = 'JOIN_GROUP';
                postData.groupId_${id} = selectedGroup.cid;
                 // post to portlet to join the group
                $.post("${ajaxHandlerContentList}", postData).then(function(response) {
                   // if the user was able to succesfully join the group
                   if(response == 200) {
                        // show the success message
                        _self.send('showJoinSuccess', selectedGroup);

                        // clear out the success after a few seconds
                        setTimeout(function() {
                            _self.send('hideJoinSuccess',selectedGroup);
                             // remove the joined group from the list
                             _self.removeObject(selectedGroup);
                            // clear the selected group
                            _self.set('selectedGroup', {});
                        }, 3000);
                   } else {
                       this.send('showJoinError',selectedGroup);
                   }
                }.bind(this), function() {
                  this.send('showJoinError',selectedGroup);
                }.bind(this));
            }
		  },

		  /**
		   * This helper function will set the necessary properties on the
		   * article content item from what was retrieved from the search.
		   * @param Object contentItem - the object to set the data on
		   * @param Object sourchData - the object to pull the data from
		   * @return Object contentItem
		   */
		  hydrateArticleItem: function(contentItem, sourceData) {
                contentItem.publisher = sourceData._source.publisher;
                contentItem.summary =  sourceData._source.summary;
                contentItem.publicationDate = sourceData._source.publicationdate;
                contentItem.title =  sourceData._source.title;
                contentItem.contentType = sourceData._source.contentType;
                contentItem.cid = (sourceData._source.contentType == 'article') ? sourceData._source.doi : '';
                return contentItem;
		  },

		  /**
           * This helper function will set the necessary properties on the
           * group content item from what was retrieved from the search.
           * @param Object contentItem - the object to set the data on
           * @param Object sourchData - the object to pull the data from
           * @return Object contentItem
           */
		  hydrateGroupItem: function(contentItem, sourceData) {
		      contentItem.isGroup = true;
              contentItem.summary = sourceData.description;
              contentItem.title =  sourceData.name;
              contentItem.contentType = 'group';
              contentItem.cid = sourceData.groupId;
              return contentItem;
		  },

		  /**
		   * This is the controller computed property that will
		   * serve as the "filtered" data.  It contains the necessary
		   * filter functions that will the content set on the controller
		   * when the user types in the filter input text box.
		   * @return Array - the filtered content data
		   */
		  filteredContent: function() {
		  	var _self = this;
		    var filter = _self.get('filter');
		    _self.set('isFilterOn', (filter.length > 0));
		    return _self.get('content').filter(function(item, index, enumerable){
		      return item.get('title').toLowerCase().match(filter.toLowerCase()) ||
		      		item.get('summary').toLowerCase().match(filter.toLowerCase())
		    });
		  }.property('filter', 'content.@each').cacheable()
		});

        // This is the Route handler for the Content List App
		ContentListApp.ContentListRoute = Ember.Route.extend({

		 /**
          * This is a standard function of Ember to initialize the controller.
          * Here we are creating the Ember subscriptions to certain channels.
          * @param Ember.Controller controller
          */
		  setupController: function(controller) {
		    Ember.Instrumentation.subscribe("ContentListApp.loadContent", {
		      before: function (name, timestamp, payload) {
		       controller.send('loadContent', payload);
		      },
		      after: function () { }
		    });
		    Ember.Instrumentation.subscribe("ContentListApp.setLoadingMetaData", {
		      before: function (name, timestamp, payload) {
		       controller.send('setLoadingMetaData', payload);
		      },
		      after: function () { }
		    });
		  }
		});

	</script>

	<script>
		$(document).ready(function() {
		    // ie hack to position filter reset button properly
		    if (bowser.msie) {
		        $('.content-list-search-bar form div span.input-group-btn').addClass('ie-reset-button-align');
		    }
		    // initialize any tooltips
            $('.myhome-tooltip').tooltip();
			// send the users content list to the Ember controller
			Ember.Instrumentation.instrument('ContentListApp.setLoadingMetaData', ${loadingMetaData});

			// initialize the content list for infinite scrolling
			$('#homepage-content-list-container-${id} div.content-list').
			 infiniteScrollHelper({
			    loadMore: function(page) {
			    	Ember.Instrumentation.instrument('ContentListApp.loadContent', false);
			    },
			    doneLoading: function() {
			       return true;
			    }
			});

			// Finally call the controller function to perform the initial load
			Ember.Instrumentation.instrument('ContentListApp.loadContent', true);
		});	
	</script>
</c:if>
