<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='suggestedArticlesAjaxHandler' id='suggestedArticlesAjaxHandler' />
<%-- Only show portlet if user is signed in --%>
<c:if test="${isSignedIn}">

	<style type="text/css">
		#homepage-suggested-article-container-${id} {
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

		#homepage-suggested-article-container-${id} .header {
			color: #999;
			font-size: 16px;
			line-height: 16px;
			font-weight: 500;
		}
		
		.suggested-article-img-container-${id} > img {
			-webkit-border-radius: 5px;
			-moz-border-radius: 5px;
			border-radius: 5px;
			width: 35px;
			height: 35px;
		}

		.suggested-article-img-container-${id} {
			margin-right: 5px;
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
		.suggested-article-container:hover div div > a.close-button {
			filter: alpha(opacity=100);
  			opacity: 1.0;
		}

		/* RESPONSIVE STYLES */

		 /* Small devices (tablets, 992px and down) */
        @media (max-width: 992px) {
              .suggested-article-container {
                        padding: 10px 0;
                        height: 110px;
                    }
        }
         /* Large devices (tablets, desktops, 993px and up) */
         @media (min-width: 993px) {
         .suggested-article-container {
         			padding: 10px 0;
         			height: 80px;
         		}
         }
	</style>

	<div id="homepage-suggested-article-container-${id}">
	</div> <!-- /#homepage-suggested-article-container-${id} -->

	<script>
	    // compile the handlebar templates to be used by Ember
	    Ember.TEMPLATES['articles'] = Ember.Handlebars.compile('<a class="text-muted pull-right" href="/portal/web/myhome/suggested-content/?type=article&keywords=software%20engineering">View all</a> <p class="header">Suggested articles</p> <div class="suggested-article-container"> <div id="suggested-article-${id}-0" class="media"> <a class="pull-left suggested-article-img-container-${id}" href="#"> <img {{bindAttr src="article0.imageURL"}}/> </a> <div class="media-body"> <a class="btn btn-default btn-sm pull-right" href="#"  {{action "goToArticle" article0}}>View</a> <a class="close-button pull-right" href="#" {{action "removeArticle" article0}}><i class="icon-remove-sign"></i></a> {{article0.shortTitle}} </div> </div> <!-- /.media --> </div><!-- /.suggested-article-container --> <div class="suggested-article-container"> <div id="suggested-article-${id}-1" class="media"> <a class="pull-left suggested-article-img-container-${id}" href="#"> <img {{bindAttr src="article1.imageURL"}}/> </a> <div class="media-body"> <a class="btn btn-default btn-sm pull-right" href="#" {{action "goToArticle" article1}}>View</a> <a class="close-button pull-right" href="#" {{action "removeArticle" article1}}><i class="icon-remove-sign"></i></a> {{article1.shortTitle}} </div> </div> <!-- /.media --> </div><!-- /.suggested-article-container --> <div class="suggested-article-container"> <div id="suggested-article-${id}-2" class="media"> <a class="pull-left suggested-article-img-container-${id}" href="#"> <img {{bindAttr src="article2.imageURL"}}/> </a> <div class="media-body"> <a class="btn btn-default btn-sm pull-right" href="#" {{action "goToArticle" article2}}>View</a> <a class="close-button pull-right" href="#" {{action "removeArticle" article2}}><i class="icon-remove-sign"></i></a> {{article2.shortTitle}} </div> </div> <!-- /.media --> </div><!-- /.suggested-article-container --> <div class="text-right"> <!-- TODO: Create feedback module Phase 2/3? <a href="#"><i class="icon-comment icon-fixed-width"></i>Feedback</a> --> </div>');

		// initialize the suggested article Ember App
		SuggestedArticleApp = Ember.Application.create({
			 rootElement: '#homepage-suggested-article-container-${id}'
		});

		// define the suggested article model
		SuggestedArticleApp.Article = Ember.Object.extend({
		    cid: "",
		  	imageURL: "/ieeecs-HomePage-theme/images/CSLogo_dark.png",
		  	title: null, 
		  	position: null, 
		  	shortTitle: function() {
    			var title = this.get('title');
			    return (title.length > 80) ? title.substring(0,80) + '...' : title;
			}.property('title')
		});

		// create Ember.ArrayController for managing the suggested articles models/views
		SuggestedArticleApp.ArticlesController = Ember.ArrayController.extend({
		  content: [],
		  article0: null,
		  article1: null,
		  article2: null,
		  keywords: null,
		  currentArticleIndex: 0,
		  totalHits: 0,
		  maxArticleLimit: 50,
		  searchURL: '${elasticSearchURL}' + '/content/_search',

		  /**
           * This is the required actions object that Ember wants you put
           * your functions that handle actions from Handlebars templates.
           */
		  actions: {
		      /**
		       * This function will navigate to the content page to show the article
		       * @param Object item
		       */
		      goToArticle: function(item) {
                  //  View the item based on the type
                 if(item != undefined) {
                    window.location = '/portal/web/myhome/content?type=article&cid='+item.cid;
                 }
              },
              /**
               * This function will set the keywords on the controller, that will serve as what
               * the controller uses to search against in the Elasticsearch database.
               * @param String data - the keywords
               */
		      setKeywords: function(data) {
		         Ember.Logger.info('setKeywords: ' + data);

		        var keywords = this.get('keywords');

		        // if there are keywords already, do nothing, else load the articles
		        if(keywords !== undefined) {
                   this.set('keywords', data);
                   // now load the article data based on the keywords
                   this.send('loadArticleData', '');
		        }
		      },

		      /**
		       * This function will load up the articles from the ES server.
		       * @param Object data - not used as of now
		       */
		      loadArticleData: function(data) {
		        _self = this;
                 // build the  json data for the search
                 var data = {
                     "query" : {
                         "multi_match" : {
                             "fields" : ["keywords", "title"],
                             "query" : this.get('keywords'),
                             "type" : "prefix"
                         }
                     },
                     "from" : 0,
                     "size" : this.get('maxArticleLimit')
                 }

                  data.filter = {"bool":{"must":[]}};
                  var terms = { "terms" : { "contentType": [] } };
                  // add the article filter
                  terms.terms.contentType.pushObject("article");
                  data.filter.bool.must.pushObject(terms);

                   // load the data based on the type from the data source
                  jQuery.ajax({
                    type: 'post',
                    contentType: 'application/json',
                    url: this.get('searchURL'),
                    data: JSON.stringify(data),
                    success: function(response) {
                        var totalHits = _self.get('totalHits');
                           // update the total number of results if necessary
                           totalHits = (totalHits == 0) ? response.hits.total : totalHits;
                           _self.set('totalHits', totalHits);
                           // set the article data
                           _self.send('setArticleData', response.hits.hits);
                    },
                    error: function(error) {
                        var eMsg = "SuggestedArticle - An error occurred when searching for suggested articles: " + error.message;
                         Ember.Logger.error(eMsg);
                        var logData = {};
                        logData.message = eMsg;
                        Log.error(logData);
                    },
                        dataType: 'json'
                  });
		      },

		      /**
		       * This function will build up and set the articles on the controller
		       * that will be displayed in the portlet.
		       * @param Array data - the articles
		       */
              setArticleData : function(data) {
                var currentArticleIndex = this.get('currentArticleIndex');
                // iterate over the articles setting them on the controller
                var idx = 0;
                // if there is data, reset the content list to add the new articles
                if(data.length > 0) {
                    this.set('content', []);
                }

                // iterate over the article data, creating article models to be displayed
                for (idx; idx < data.length; idx++) {
                    var item = SuggestedArticleApp.Article.create({
                      //  imageURL: data[idx].imageURL,
                        title: data[idx]._source.title,
                        cid: data[idx]._source.doi,
                        position: idx
                    });
                    if(idx <3) {
                        currentArticleIndex++;
                        this.set('article'+idx, item);
                    }

                    // now add the item to the controller list
                    this.pushObject(item);
                };
                 _self.set('currentArticleIndex', currentArticleIndex);
              },

              /**
               * This function will remove the passed in article from the list
               * of articles in the portlet.
               * @param Object article
               */
              removeArticle: function(article) {
                    var currentArticleIndex = this.get('currentArticleIndex');
                    var totalHits = this.get('totalHits');
                    var position = article.position;

                    // fade out the current container for the article
                    $("#suggested-article-${id}-"+position).hide();

                    /*
                     *  Use the next item in the list of articles,
                     *  if we have reached the end of the list, go
                     *  back to the beginning.
                     */
                    currentArticleIndex = (currentArticleIndex == totalHits) ? 0 : currentArticleIndex;
                    var item = this.get('content')[currentArticleIndex];
                    // now increment the current article index so we can get the next article next time
                    currentArticleIndex++;
                     _self.set('currentArticleIndex', currentArticleIndex);

                    item.position = position;
                    this.set('article'+position, item);
                    setTimeout(function() {
                        // fade in the current container for the article
                        $("#suggested-article-${id}-"+position).fadeIn();
                    }, 1000);
              }
	      }
		});

		// force the root path to utilize the "articles" template 
		SuggestedArticleApp.Router.map(function() {
		  this.route("articles", { path: "/" });
		});

		// for the initial index view
		SuggestedArticleApp.ArticlesRoute = Ember.Route.extend({

		 /**
          * This is a standard function of Ember to initialize the controller.
          * Here we are creating the Ember subscriptions to certain channels.
          * @param Ember.Controller controller
          */
		  setupController: function(controller) {
		    Ember.Instrumentation.subscribe("SuggestedArticleApp.loadArticleData", {
		      before: function (name, timestamp, payload) {
		      	controller.send('loadArticleData', payload);
		      },
		      after: function () { }
		    });
		     Ember.Instrumentation.subscribe("SuggestedArticleApp.setKeywords", {
              before: function (name, timestamp, payload) {
                controller.send('setKeywords', payload);
              },
              after: function () { }
            });
		  }
		});
	</script>
	<script>
		$(document).ready(function() {
		    // wait about 1.5 seconds before calling set keywords
		    setTimeout(function() {
                 // send the suggested articles data list to the Ember controller to be displayed and managed
                 Ember.Instrumentation.instrument('SuggestedArticleApp.setKeywords', 'software');
            }, 1500);
		});
	</script>
</c:if>
