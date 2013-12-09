<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='ajaxHandlerSubscriptionList' id='ajaxHandlerSubscriptionList' />
<%-- Only show portlet if user is signed in --%>
<c:if test="${isSignedIn}">
<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">
	<style type="text/css">
		#homepage-subscription-list-container-${id} {
			font-size: 12px;
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
		}

		.subscription-content-list {
			width: 100%;
			/*overflow-y: auto;
			height: 600px;*/
		}

		.content-list-item-media-container {
			height: 130px;
			width: 130px;
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

		.content-item-expanded {
			padding: 10px 20px;
			background-color: #f6f6f6;
			border-bottom: 1px solid #eeeeee;
		}

		.package-title {
			padding: 10px 60px 0px 10px;
			color: #2a72b2;
			font-size: 14px;
			font-weight: 500;
			line-height: 1.1;
		}

		.package-title-sm-spacer {height:10px}
		
		/* SUBOPTIONS STYLES */
		.content-list-item-suboptions {
			color: #888;
			font-size: 12px;
			height: 40px;
			border-bottom: 1px solid #eeeeee;
		}

		.suboptions-links ul {
			list-style-type: none;
			margin-bottom: 0px;
		}
		.suboptions-links ul > li {
			line-height: 30px;
			display: inline;
			padding: 0px 10px;
		}
		.suboptions-social { 
			padding-top: 3px;
			margin: 0 0 0 10px;
			display: none; /* NOTE: HIDING FOR NOW UNTIL SOCIAL METATAGS */
		}
		.suboptions-expand-control {
			line-height: 30px;
		}

		.suboptions-social > a:hover,.suboptions-links ul > li > a:hover {
			text-decoration: none;
			opacity: 0.7;
            -moz-opacity: 0.7;
            -webkit-opacity: 0.7;
            -khtml-opacity: 0.7;
		}
		.suboptions-time {
			line-height: 30px;
			text-align: right;
			padding-right: 7px;
		}
		/* COMMENT STYLES */
		.comment-list-container {padding: 10px 0px;}
		.ie-reset-button-align {
          top: -4px;
        }
	</style>

  	<div id="homepage-subscription-list-container-${id}" class="col-md-12 col-sm-12">
	</div>

	<script>
	    Ember.TEMPLATES['list'] = Ember.Handlebars.compile('<div class="content-list-search-bar"> <form class="col-md-4 col-sm-4 pull-right"> <div class="input-group"> {{view Ember.TextField valueBinding="filter" classNames="form-control" placeholder="Filter your content"}} <span class="input-group-btn"> <button class="btn btn-primary myhome-tooltip" type="button" {{action "clearFilter"}} data-toggle="tooltip" data-placement="top" data-original-title="Reset Filter"> <i class="icon-remove-sign"></i> </button> </span> </div><!-- /.input-group --> </form> <div class="col-md-8 col-sm-8"> <span>Your Library Content</span> </div> </div> <div class="subscription-content-list"> {{#each item in filteredContent}} <div {{bindAttr class=":col-md-12 :col-sm-12 item.contentTypeCSSClass"}} > <div class="content-list-item-contracted"> <div class="content-item-column"> <div class="content-list-item-details" {{ action "goToItem" item }}> <div {{bindAttr class=":content-list-item-type item.contentType"}}> {{item.contentType}} </div> <!-- this is a spacer that is visible only for the small (tablet) sized devices --> <div class="clearfix package-title-sm-spacer visible-sm"></div> <p class="package-title">{{item.packageTitle}}</p> <div class="media"> <img src="/ieeecs-HomepageSubscriptionContentList-portlet/images/default-content.jpg" class="pull-left media-object content-list-item-media-container" /> <div class="media-body"> <h4 class="media-heading">{{item.title}}</h4> <span class="text-muted">{{item.publisher}} {{#if item.shortSummary}}&middot;{{/if}}</span> {{item.shortSummary}} </div> </div> </div> <!-- /.content-list-item-details --> <div class="content-list-item-suboptions"> <div class="suboptions-links col-md-4 col-sm-5 hide"> <ul> <li> <a href="#"> Like <!-- TODO: if there are likes, show the amount here --> <span class="badge">3</span> </a> </li> <li> <a href="#" {{ action "expandItem" item}}> Comments <!-- TODO: if there are comments, show the amount here --> <span class="badge">26</span> </a> </li> </ul> </div> <div class="suboptions-social col-md-2 col-sm-2"> <a {{bindAttr href="item.shareTwitterLink"}} target="_blank"><i class="icon-twitter icon-fixed-width icon-2x"></i></a> <a {{bindAttr href="item.shareFacebookLink"}} target="_blank"><i class="icon-facebook icon-fixed-width icon-2x"></i></a> </div> <div class="suboptions-time col-md-1 col-sm-2 pull-right"> <!-- TODO: have time this was added to bundle? 1d ago --> </div> <div class="pull-right col-md-1 col-sm-2 suboptions-expand-control hide"> {{#if item.isExpanded}} <a href="#" {{ action "toggleItemExpand" item }}>Collapse</a> {{else}} <a href="#" {{ action "toggleItemExpand" item }}>Expand</a> {{/if}} </div> </div> <!-- /.content-list-item-suboptions --> </div> <!-- /.content-list-item-contracted --> {{#if item.isExpanded}} <div class="content-item-expanded"> <div class="comment-list-container"> {{#if item.isCommentsVisible}} <a href="#" {{ action "toggleComments" item}}> Hide comments <i class="icon-caret-up icon-fixed-width"></i> </a> <ul class="list-unstyled"> <li class="col-md-offset-1 col-sm-offset-1 col-md-11 col-md-11"> <div class="col-md-1 col-sm-2 profile-img-container"> <img src="http://i.annihil.us/u/prod/marvel//universe3zx/images/3/33/Gambit442.jpg" class="profile-img-small"/> </div> <div class="col-md-10 col-sm-10"> <h6>Christoper Jones&nbsp;<span class="text-muted">12:04 PM</span></h6> <p>This is the best article ever.  I really enjoy reading from the greats.</p> </div> </li> <li class="col-md-offset-1 col-sm-offset-1 col-md-11 col-sm-11"> <div class="col-md-1 col-sm-2 profile-img-container"> <img src="http://1.images.comedycentral.com/images/show_images/600x400_chappellesshow.jpg?width=600&height=400&crop=true" class="profile-img-small"/> </div> <div class="col-md-10 col-sm-10"> <h6>Ricky Hatton&nbsp;<span class="text-muted">12:04 PM</span></h6> <p>Aoccdrnig to a rscheearch at Cmabrigde Uinervtisy, it deosnt mttaer in waht oredr the ltteers in a wrod are, the olny iprmoatnt tihng is taht the frist and lsat ltteers be at the rghit pclae. The rset can be a toatl mses and you can sitll raed it wouthit porbelm. Tihs is bcuseae the huamn mnid deos not raed ervey lteter by istlef, but the wrod as a wlohe.</p> </div> </li> </ul> {{else}} <a href="#" {{ action "toggleComments" item}}> 26 comments <i class="icon-caret-down icon-fixed-width"></i> </a> {{/if}} </div> {{#if item.isInPreCommentMode}} <div class="pre-add-comment-form-${id}"> <input type="text" class="form-control" placeholder="Add a comment..." {{action "showAddCommentForm" item}} /> </div> {{else}} <div class="add-comment-form-container"> <div class="add-comment-form"> <form class="form-horizontal" role="form"> <div class="form-group"> <div class="col-md-1 col-sm-1 profile-img-container"> <img class="profile-img-small" alt="$!{user_name}" src="/portal/image/user_male_portrait?img_id=$user.portraitId&t=1375672866971"/> </div> <div class="col-md-10 col-sm-10"> <textarea class="form-control" rows="3"></textarea> </div> </div> <div class="form-group"> <div class="col-md-offset-1 col-sm-offset-1 col-md-10 col-sm-10"> <button type="submit" class="btn btn-primary" disabled>Post Comment</button> <button class="btn btn-default" {{action "hideAddCommentForm" item}}>Cancel</button> </div> </div> </form> </div> <!-- /.add-comment-form --> </div> <!-- /.add-comment-form-container --> {{/if}} </div> <!-- /.content-item-expanded --> {{/if}} </div> <!-- /.content-item-column --> <div class="content-done-column"><i class="icon-check-sign icon-3x"></i></div> <div class="content-loading-column"><i class="icon-spinner icon-spin icon-large"></i> Loading</div> </div> <!-- /.contentTypeCSSClass --> {{/each}} </div><!-- /.subscription-content-list -->');
		SubscriptionContent = Ember.Application.create({
			 rootElement: '#homepage-subscription-list-container-${id}'
		});

		SubscriptionContent.Router.map(function() {
			this.route("list", { path: "/" });
		});

		SubscriptionContent.Content = Ember.Object.extend({
		    cid: "",
			publisher: "",
			packageTitle: "",
		  	summary: "",
		  	publicationDate: null,
			title: "",
			contentType: "",
			contentTypeCSSClass: 'content-list-item',
			isExpanded: false,
			isInPreCommentMode: true,
			isCommentsVisible: false,
			shortSummary: function() {
    			var summary = this.get('summary');
			    return (summary.length > 450) ? summary.substring(0,450) + '...' : summary;
			}.property('summary'), 
			publicationDateFormatted: function() {
    			var publicationDate = new Date(this.get('publicationDate'));
    			var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
			    return months[publicationDate.getMonth()] + ' ' + publicationDate.getDate() + ', ' + publicationDate.getFullYear();
			}.property('publicationDate'),
            shareFacebookLink: function() {
                var baseURL = 'https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Fwww.computer.org%2Fportal%2Fweb%2Fmyhome%2Fcontent?type=';
                return baseURL + this.get('contentType') + '%26cid=' + this.get('cid');
            }.property('contentType', 'cid'),
            shareTwitterLink : function() {
               var baseURL = 'https://twitter.com/share?url=http%3A%2F%2Fwww.computer.org%2Fportal%2Fweb%2Fmyhome%2Fcontent?type=';
               return baseURL + this.get('contentType') + '%26cid=' + this.get('cid');
            }.property('contentType', 'cid')
		});
		
		// create Ember.ArrayController
		SubscriptionContent.ListController = Ember.ArrayController.extend({
		  content: [], 
		  subscriptionList : null,
		  filter: "",
		  isLoading: false,
		  isDoneLoadingAllContent: false,
		  isFilterOn: false,
		  totalSubscriptionContentCount: 0,
		  currentUnitIndex: 0,
		  groupSize: 10,
		  actions: {
		      clearFilter: function() {
		           this.set('isFilterOn', false);
		           this.set('filter', "");
		      },
		      resetState: function(units) {
		         // reset all of the controller properties, this is for when we need to reload the subscription list
		         this.set([]);
                 this.set('subscriptionList',null);
                 this.set('filter', "");
                 this.set('isLoading', false);
                 this.set('isDoneLoadingAllContent', false);
                 this.set('isFilterOn', false);
                 this.set('totalSubscriptionContentCount', 0);
                 this.set('currentUnitIndex', 0);

                 if(units != undefined) {
                    var unitsCollection = [];
                    for(var i in units){
                        if (units.hasOwnProperty(i)) {
                            v = units[i];
                            if (v === 'toString') {
                                continue;
                            } // ignore useless items
                            if (Ember.typeOf(v) === 'function') {
                                continue;
                            }
                            v.forEach(function(unitId) {
                               unitsCollection.pushObject({type: i, contentId: unitId});
                            });
                        }
                    }

                    // randomize the list
                    unitsCollection.sort(function() {return 0.5 - Math.random()});

                    // now set the updated subscription list
                    this.send('setSubscriptionList',unitsCollection);

                    // reload the content based on the updated subscription list
                    this.send('loadMoreContent', 'reloading suscription list.');
                 }
		      },
		      goToItem: function(item) {
		          //  View the item based on the type
                  if(item != undefined) {
                      if(item.contentType == 'article') {
                         window.location = '/portal/web/myhome/content?type=article&cid='+item.cid;
                      } else if (item.contentType == 'webinar') {
                         window.location = '/portal/web/myhome/content?type=webinar&cid='+item.cid;
                      }
                  }
		      },
              toggleComments: function(item) {
                item.set('isCommentsVisible', !item.isCommentsVisible);
              },
              hideAddCommentForm: function(item) {
                item.set('isInPreCommentMode', true);
              },
              showAddCommentForm: function(item) {
                item.set('isInPreCommentMode', false);
              },
              expandItem: function(item) {
                item.set('isExpanded', true);
              },
              toggleItemExpand: function(item) {
                item.set('isExpanded', !item.isExpanded);
              },
              setSubscriptionList : function(data) {
                var _self = this;
                _self.set('subscriptionList', data);
                _self.set('totalSubscriptionContentCount', data.length);
              },
              loadMoreContent: function(args) {
                var _self = this;
                var isLoading = _self.get('isLoading');
                var isFilterOn = _self.get('isFilterOn');
                if(!isLoading && !isFilterOn) {
                    // create the temp model that will represent the "loading" view
                    var loadingContentItem = SubscriptionContent.Content.create({
                          contentTypeCSSClass: "content-list-item-loading"
                    });
                    // add it to the content for the client to see
                    _self.pushObject(loadingContentItem);
                    _self.set('isLoading', true);
                    var currentUnitIndex = _self.get('currentUnitIndex');
                    var totalSubscriptionContentCount = _self.get('totalSubscriptionContentCount');
                    var groupSize = _self.get('groupSize');
                    var subscriptionList = _self.get('subscriptionList');
                    // build the data list and
                    var data = {};
                    data.units_${id} = [];
                    var count=0;
                    // iterate over the next set of content items to pass to the server for retrieval
                    for(var idx = currentUnitIndex; (currentUnitIndex < totalSubscriptionContentCount && count < groupSize); currentUnitIndex++){
                        data.units_${id}.pushObject(subscriptionList[currentUnitIndex]);
                        count++;
                    }
                    _self.set('currentUnitIndex', currentUnitIndex);
                    data.requestType_${id} = 'USER_SUBSCRIPTION_CONTENT';
                    data.units_${id} = JSON.stringify(data.units_${id});

                    // post to the portlet
                    var jxhr = $.post("${ajaxHandlerSubscriptionList}", data)
                        .done(function(response) {
                          // loading is over, so we can remove the "loading" item
                          _self.removeObject(loadingContentItem);
                          // if there are items add them to the list
                           if(response != undefined) {
                              response.forEach(function(item) {
                                    // build the content based on the type
                                    if(item.contentType == 'article') {
                                        var pack = item.content.packagelist['package'];
                                        var contentItem = SubscriptionContent.Content.create({
                                            publisher: pack.publisher,
                                            packageTitle: pack.title,
                                            summary: item.content.summary,
                                            publicationDate: pack.publicationdate,
                                            title: item.content.title,
                                            contentType: item.contentType,
                                            cid: item.content.doi
                                        });
                                    } else if(item.contentType == 'webinar') {
                                        var contentItem = SubscriptionContent.Content.create({
                                            publisher: item.content.hits.hits[0]._source.publisher,
                                            packageTitle: item.content.hits.hits[0]._source.title,
                                            summary: item.content.hits.hits[0]._source.summary,
                                            publicationDate: item.content.hits.hits[0]._source.publicationdate,
                                            title: item.content.hits.hits[0]._source.title,
                                            contentType: item.contentType,
                                            cid: item.content.hits.hits[0]._source.sku
                                        });
                                    }
                                    _self.pushObject(contentItem);
                                });
                            }
                            _self.set('isLoading', false);

                            if (currentUnitIndex == totalSubscriptionContentCount) {
                                // if we are at the last item, destroy the plugin instance
                                $('#homepage-subscription-list-container-${id} div.subscription-content-list')
                                .infiniteScrollHelper('destroy');
                                _self.set('isDoneLoadingAllContent', true);
                                var doneContentItem = SubscriptionContent.Content.create({
                                      contentTypeCSSClass: "content-list-item-done"
                                });
                                _self.pushObject(doneContentItem);
                            }
                         })
                        .fail(function(error) { console.log("error loading article count:" + error); });
                }
              }
		  },
		  filteredContent: function() {
		  	var _self = this;
		    var filter = _self.get('filter');
		    _self.set('isFilterOn', (filter.length > 0));
		    return _self.get('content').filter(function(item, index, enumerable){
		      return item.get('title').toLowerCase().match(filter.toLowerCase()) ||
		      		(item.get('packageTitle') != undefined && item.get('packageTitle').toLowerCase().match(filter.toLowerCase())) ||
		      		(item.get('publisher') != undefined && item.get('publisher').toLowerCase().match(filter.toLowerCase())) ||
		      		item.get('contentType').toLowerCase().match(filter.toLowerCase());
		    });
		  }.property('filter', 'content.@each').cacheable()
		});

		SubscriptionContent.ListRoute = Ember.Route.extend({
		  setupController: function(controller) {
		    Ember.Instrumentation.subscribe("SubscriptionContent.loadMoreContent", {
		      before: function (name, timestamp, payload) {
		       controller.send('loadMoreContent', payload);
		      },
		      after: function () { }
		    });
		    Ember.Instrumentation.subscribe("SubscriptionContent.setSubscriptionList", {
		      before: function (name, timestamp, payload) {
		       controller.send('setSubscriptionList', payload);
		      },
		      after: function () { }
		    });
		     Ember.Instrumentation.subscribe("SubscriptionContent.resetState", {
              before: function (name, timestamp, payload) {
               controller.send('resetState', payload);
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
			// parse entire units list, and build into one JSON array
			var units = {};
			units = jQuery.parseJSON('${units}');
			var unitsCollection = [];

			for(var i in units){
				if (units.hasOwnProperty(i)) {
	                v = units[i];
	                if (v === 'toString') {
	                    continue;
	                } // ignore useless items
	                if (Ember.typeOf(v) === 'function') {
	                    continue;
	                }
	                if(v.length > 0) {
                        v.forEach(function(unitId) {
                           unitsCollection.pushObject({type: i, contentId: unitId});
                        });
		        	}
	            }
			}
			
			// randomize the list
			unitsCollection.sort(function() {return 0.5 - Math.random()});

			// send the user's subscription list to the Ember controller
			Ember.Instrumentation.instrument('SubscriptionContent.setSubscriptionList', unitsCollection);
			
			// initialize the subscription list for infinite scrolling
			$('#homepage-subscription-list-container-${id} div.subscription-content-list').
			 infiniteScrollHelper({
			    loadMore: function(page) {
			    	Ember.Instrumentation.instrument('SubscriptionContent.loadMoreContent', 'loading more..');
			    }, 
			    doneLoading: function() {
			       return true;
			    }
			});
			
			// Finally call the controller function to load more data
			Ember.Instrumentation.instrument('SubscriptionContent.loadMoreContent', 'initial loading');
		});	
	</script>
</c:if>
</c:if>