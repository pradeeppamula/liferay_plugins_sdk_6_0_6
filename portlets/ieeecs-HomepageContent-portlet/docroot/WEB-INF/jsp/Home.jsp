<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='ajaxHandlerContent' id='ajaxHandlerContent' />
<style type="text/css">
	#homepage-content-container-${id} {
		font-size: 14px;
		min-height: 430px;
		background-color: #ffffff;
	}
	.content-date {
	    color: #ffffff;
           line-height: 20px;
           padding: 5px;
	}
	.content-body {
	    padding: 0 20px 30px 20px;
	    text-align: justify;
	}
	.header-info {
	    padding: 0px 20px;
	}
	.content-loading-container {
	    display: block;
           height: 150px;
           line-height: 150px;
           text-align: center;
           color: #2a72b2;
           font-weight: bolder;
           font-size: 2.5em;
	}
	.add-to-bundle-container { padding-bottom: 20px; }
	.webinar-flowplayer-container {text-align:center;}
	.webinar-flowplayer {display:none;}
         /* custom player skin */
    .flowplayer { width: 100%; background-color:#000; background-size: cover; max-width: 800px; }
    .flowplayer .fp-controls { background-color: rgba(17, 17, 17, 1)}
    .flowplayer .fp-timeline { background-color: rgba(204, 204, 204, 1)}
    .flowplayer .fp-progress { background-color: rgba(0, 167, 200, 1)}
    .flowplayer .fp-buffer { background-color: rgba(249, 249, 249, 1)}
    .flowplayer .fp-fullscreen {
        background-color: #aaa;
        padding-right: 38px;
    }

    @media (max-width: 767px) {
      .content-date {
         padding: 5px 5px 5px 20px;
      }
    }


    /********************************/
    /**     Full Content Styles     */
    /********************************/
    .abs-content-large { margin-bottom: 10px; }
    .section-title {
        font-weight: bold;
        margin: 20px 0 10px 0;
        font-size: 1.4em;
        text-decoration: underline;
        color: #2a72b2;
    }
    .ss1-heading {
        margin: 15px 0px 5px 0px;
        font-weight: bold;
    }
    .captionText {
        font-style: italic;
        font-size: 0.9em;
    }
    #maincontent img { max-width: 100%; }

</style>
<div id="homepage-content-container-${id}" class="col-md-12 col-sm-12">
    <div class="webinar-flowplayer"></div>
</div> <!-- /#homepage-content-container-${id} -->
<!--<script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>-->
<script>
    // compile the handlebar templates to be used by Ember
    Ember.TEMPLATES['article'] = Ember.Handlebars.compile('<div> <div class="row"> <h4 class="col-md-3 col-sm-3 col-xs-9 content-date label-info">{{publicationDateFormatted}}</h4> </div> <div class="row header-info"> <h1>{{title}}</h1> <h3>{{publisher}}</h3> {{#if authors}} <h6>by {{authors}}</h6> {{/if}} </div> </div> <!-- /.container --> <hr> <div class="content-body"> <div class="alerts-container"> <div class="alert-success-container alert alert-block alert-success fade hide success"> <button type="button" class="close" aria-hidden="true" {{ action "hideAddBundleSuccess" }}>x</button> <h4><i class="icon-check-sign icon-3x icon-fixed-width"></i>Added To Bundle!</h4> </div> <!-- /.success --> <div class="alert-danger-container alert alert-block alert-danger fade hide error"> <button type="button" class="close" aria-hidden="true" {{ action "hideAddBundleError" }}>x</button> {{#if bundleLimitError}} <p> <i class="icon-warning-sign icon-3x icon-fixed-width"></i> Your bundle is at its limit, would you like to increase the size? <a class="btn btn-default" href="#" {{ action "goToBundlePurchase" }}>Make it happen</a> </p> {{else}} <h4><i class="icon-exclamation-sign icon-3x icon-fixed-width"></i>There was a problem adding this item to your bundle, please try again or contact help@computer.org.</h4> {{/if}} </div> <!-- /.error --> <div class="alert-warning-container alert alert-block alert-warning fade hide warning"> <button type="button" class="close" aria-hidden="true"  {{ action "hideAddBundleConfirm" }}>x</button> <p> <i class="icon-warning-sign icon-3x icon-fixed-width"></i> Are you sure you would like to add this item to your bundle? <button {{bindAttr class=":btn :btn-default isSavingToBundle:disabled"}} {{ action "addItemToBundle" }}> {{#unless isSavingToBundle }} Of Course {{else}} Please Wait {{/unless}} </button> </p> </div> <!-- /.warning --> </div> <!-- /.alerts-container --> {{#if hasFullAccess}} {{{summary}}} {{else}} <div class="add-to-bundle-container"> <a href="#" class="btn btn-info btn-xs"  {{ action "showAddBundleConfirm" }}>Add To Bundle</a> </div> {{{summary}}} <br /><br /> <a href="/portal/web/myhome/article-bundle" class="btn btn-medium btn-block btn-primary">Learn More About Our Article Bundles</a> {{/if}} </div> <!-- /.content-body -->');
    Ember.TEMPLATES['content'] = Ember.Handlebars.compile('{{#if isLoading}} <div class="content-loading-container"> <i class="icon-spinner icon-spin icon-large"></i> Loading </div> {{else}} {{#if isArticle}} {{view ContentApp.ArticleView}} {{else}} {{#if isWebinar}} {{view ContentApp.WebinarView}} {{else}} <div class="alert-warning-container alert alert-block alert-warning fade in warning"> <h2> <i class="icon-warning-sign icon-2x icon-fixed-width"></i> Content Not Found. </h2> <p>Please verify that you selected the correct item.  If you are still having trouble contact <strong>help@computer.org</strong> for help.</p> </div> <!-- /.warning --> {{/if}} {{/if}} {{/if}}');
    Ember.TEMPLATES['webinar'] = Ember.Handlebars.compile('<div> <div class="row"> <h4 class="col-md-3 col-sm-3 col-xs-9 content-date label-info">{{publicationDateFormatted}}</h4> </div> <div class="row header-info"> <h2>{{title}}</h2> <h3>{{publisher}}</h3> {{#if authorList}} <h6>by {{authors}}</h6> {{/if}} </div> </div> <!-- /.container --> <hr> <div class="content-body"> {{#if hasFullAccess}} <p> {{{summary}}} </p> <br /> <div class="webinar-flowplayer-container"></div> <div id="webinar-not-found-${id}" class="alert-warning-container alert alert-block alert-warning fade hide warning"> <h2> <i class="icon-warning-sign icon-2x icon-fixed-width"></i> Webinar Not Found. </h2> <p>Please verify that you selected the correct item.  If you are still having trouble contact <strong>help@computer.org</strong> for help.</p> </div> <!-- /.warning --> {{else}} <p> {{{summary}}} </p> <br /><br /> <a href="/portal/web/myhome/webinar-bundle" class="btn btn-medium btn-block btn-primary">Learn More About Our Webinars</a> {{/if}} </div><!-- /.content-body -->');

	// define the Content Ember App
	ContentApp = Ember.Application.create({
		 rootElement: '#homepage-content-container-${id}'
	});

    // have the root serve up the content template
	ContentApp.Router.map(function() {
		this.route("content", { path: "/" });
	});

    // This is the Content Model for the template
	ContentApp.Content = Ember.Object.extend({
	    isSavingToBundle: false,
	    doi: '',
	    sku: '',
	    cid: '',
	    hasFullAccess: false,
		publisher: '',
	  	summary: '',
	  	publicationDate: '',
		title: '',
		contentType: '',
		authorList: null,
        isAuthenticated: ${isAuthenticated},
        videoSourcePath: '',
		authors: function() {
		    var retVal = null;
		    var authorList = this.get('authorList');
		    if(authorList != undefined && authorList != '' && authorList.length > 0) {
		       retVal = '';
                  var idx=0;
                  for(idx;idx<authorList.length;idx++) {
                   if(idx>0) {
                       retVal += ', ';
                   }
                   retVal += authorList[idx].givenname+' '+authorList[idx].surname;
                  }
		    }
		    return retVal;
		}.property('authorList'),
		publicationDateFormatted: function() {
   			var publicationDate = new Date(this.get('publicationDate'));
   			var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
		    return months[publicationDate.getMonth()] + ' ' + publicationDate.getDate() + ', ' + publicationDate.getFullYear();
		}.property('publicationDate')
	});

    // The custom views for articles and webinars
   ContentApp.ArticleView = Ember.View.extend({
       templateName: 'article'
   });
   ContentApp.WebinarView = Ember.View.extend({
       templateName: 'webinar'
   });
	
	// create Ember.ArrayController
	ContentApp.ContentController = Ember.ObjectController.extend({
	  userPurchaseData: '${userPurchaseData}',
	  type: '${type}',
	  cid: '${cid}',
	  isLoading: true,
	  bundleLimitError: true,
	  isArticle: false,
	  isWebinar: false,

	  /**
       * This function will use the user's purchase information to determine
       * what webinar skus are expired from within their bundle.  This is needed
       * so that user's do not have access to bundle items that are expired.
       * @param Object userPurchaseData
       * @return Array list of expired skus
       */
	  getExpiredSkus: function(userPurchaseData) {
	     var retVal = [];
	     var today = new Date();
            var xx=0;

            // iterate over the webinar bundles for the user
            for(xx=0;xx<userPurchaseData.bundle.webinar.length;xx++) {
                // get the expired date
                var expirationDate = new Date(userPurchaseData.bundle.webinar[xx].expiration_date);
                // check the webinars object for bundles with an expiration date before today
                if(today.getTime() > expirationDate.getTime()) {
                    // if there is a webinars bundle with an expiration before today, build a list of its skus
                    var yy = 0;
                    for(yy=0;yy<userPurchaseData.bundle.webinar[xx].selected_items.length;yy++){
                        retVal.push(userPurchaseData.bundle.webinar[xx].selected_items[yy].sku);
                    }
                }
            }
	    return retVal;
	  },

	  /**
       * This function will user the user's purchase information to determine
       * if they have any free slots in their specific bundle.  Usually this
       * function will be called when a user is adding items to their bundle.
       * @param String type -  the type of bundle
       * @return boolean
       */
	  bundleHasSpace: function() {
	      var userPurchaseData = _self.get('userPurchaseData');
             userPurchaseData = (userPurchaseData != '') ? $.parseJSON(userPurchaseData) : '';

             /*
              * First check to see if the user has purchase information through
              * their organization, if they don't, return false so that they will
              * have to request a quote.
              */
              if(userPurchaseData.bundle == undefined ||
               userPurchaseData.bundle.csdl_article == undefined) {
               return false;
              }
             var type = _self.get('type');
             var userContentCount;
             var bundleType;
             // get the number of content items the user has for the type
             if(type == 'article') {
                userContentCount = userPurchaseData.units.csdl_article.length;
                bundleType =  userPurchaseData.bundle.csdl_article;
             }
             /*
              * now add up the number of items in the bundle, and check that against
              * the number of items that the user has to see if they have available space.
              */
              var totalBundleCount = 0;
              var idx = 0;
              for(idx;idx<bundleType.length;idx++) {
                  totalBundleCount += bundleType[idx].number_of_items;
              }
              return userContentCount < totalBundleCount;
	  },
	  /**
       * This is the required actions object that Ember wants you put
       * your functions that handle actions from Handlebars templates.
       */
	  actions: {
	    /**
	     * This function will load the full article based on the
	     * passed in abstract
	     * @param Object abstract
	     */
	    loadFullArticle: function(abstract) {
	      _self = this;
          // First refresh the user purchase data
          var data = {};
          data.requestType_${id} = 'LOAD_ARTICLE_CONTENT';
          // TODO: build dynamic path later
          data.contentPath_${id} = 'mags/co/2013/12/mco2013120068.xml';

            // post to portlet to retrieve the  user purchase data
            $.post("${ajaxHandlerContent}", data)
                .done(function(response) {
                   /*
                    * If the data is found, we can set that it is in fact an article, otherwise
                    * the "no content found" view will be displayed
                    */
                    _self.set('isArticle', true);

                    // TODO: set the full article content on the UI
                    console.log(response);
                    _self.set("summary", response);
                })
               .fail(function(error) {
                 var eMsg = "Content - Error loading the content at path: " + data.contentPath_${id} + ".  Error: " + error;
                 Ember.Logger.error(eMsg);
                 var logData = {};
                 logData.message = eMsg;
                 Log.error(logData);
               })
               .always(function() {
                   // the the UI that loading is complete
                   _self.set('isLoading', false);
               });
	    },

	      /**
	       * This function will reset some of the controller properties to their
	       * default state.
	       * @param Object data - the user purchase data
	       */
	      resetState: function(data) {
	          // reset the controller to its default values
	          this.set('isLoading', true);
                 this.set('bundleLimitError', true);
	          // set the updated userPurchase Data
                 this.set('userPurchaseData', data);
	          // now re-initialize the page
	          this.send('init', '');
	      },

	      /**
	       * This overriden Ember initialization function will retrieve the content
	       * based on the content type and the id.  It will then set the retrieved
	       * content data on the controller to be viewed by the user.
	       * @param Object payload
	       */
	      init: function(payload) {
	        _self = this;

	        // grab the user's purchase data from the controller
	        var userPurchaseData = _self.get('userPurchaseData');
	        userPurchaseData = (userPurchaseData != '') ? $.parseJSON(userPurchaseData) : '';
	        var type = _self.get('type');
	        var contentId = _self.get('cid');

	        // if the content is an article
	        if(type == 'article') {
                var _self = this;

                // build the json data for the search
                var data = {
                       "query" : {
                           "multi_match" : {
                               "fields" : ["doi"],
                               "query" : contentId,
                               "type" : "prefix"
                           }
                       }
                }
                // POST on the ES REST API endpoint to load the article from Elastic search
                $.ajax({
                    type: 'post',
                    contentType: 'application/json',
                    url: '${elasticSearchURL}' + '/content/_search',
                    data: JSON.stringify(data),
                    success: function(response) {
                        if(response != undefined && response.hits.total > 0) {
                            // set the content model data
                            _self.set('publisher', response.hits.hits[0]._source.publisher);
                            _self.set('publicationDate', response.hits.hits[0]._source.publicationdate);
                            _self.set('summary', response.hits.hits[0]._source.summary);
                            _self.set('title', response.hits.hits[0]._source.title);
                            _self.set('contentType', 'article');
                            _self.set('cid', contentId);
                            _self.set('authorList', response.hits.hits[0]._source.articleMetadata.article.articleinfo.authorgroup.author);
                            _self.set('doi', contentId);

                            // now check the purchase data to see if the user has access
                            if(userPurchaseData !== undefined && userPurchaseData != '' && userPurchaseData.units !== undefined) {
                                // iterate over the article dois that the user currently is subscribed to
                                var idx=0;
                                for(idx=0;idx<userPurchaseData.units.csdl_article.length;idx++) {
                                    if(contentId == userPurchaseData.units.csdl_article[idx]) {
                                        _self.set('hasFullAccess', true);
                                        break;
                                    }
                                }
                            }
                        }

                         // if they are NOT authenticated, remove their full access
                        <c:if test="${!isAuthenticated}">
                            _self.set('hasFullAccess', false);
                        </c:if>

                       // if they have full access, load the article content
                       if(_self.get('hasFullAccess')) {
                         _self.send('loadFullArticle', response.hits.hits[0]._source);
                       } else {
                           /*
                            * If the data is found, we can set that it is in fact an article, otherwise
                            * the "no content found" view will be displayed
                            */
                            _self.set('isArticle', true);
                           // the the UI that loading is complete
                           _self.set('isLoading', false);
                       }
                    },
                    error: function(error) {
                     var eMsg = "Content - Error loading the "+ contentType +" content: " + error.message;
                     Ember.Logger.error(eMsg);
                     var logData = {};
                     logData.message = eMsg;
                     Log.error(logData);
                     // the the UI that loading is complete
                     _self.set('isLoading', false);
                 },
                 dataType: 'json'
             });
	        } else if (type=='webinar') { // else we are retrieving webinar data
	            var _self = this;

               // build the json data for the search
                var data = {
                      "query" : {
                          "multi_match" : {
                              "fields" : ["sku"],
                              "query" : contentId,
                              "type" : "prefix"
                          }
                      }
                  }

                    // POST on the ES REST API endpoint to load the webinar from Elastic search
                    $.ajax({
                        type: 'post',
                        contentType: 'application/json',
                        url: '${elasticSearchURL}' + '/content/_search',
                        data: JSON.stringify(data),
                        success: function(response) {
                            if(response != undefined && response.hits.total > 0) {
                                /*
                                 * If the data is found, we can set that it is in fact a webinar, otherwise
                                 * the "no content found" view will be displayed
                                 */
                               _self.set('isWebinar', true);

                               // set the content model data
                                _self.set('publisher', response.hits.hits[0]._source.publisher);
                                _self.set('publicationDate', response.hits.hits[0]._source.publicationdate);
                                _self.set('summary', response.hits.hits[0]._source.summary);
                                _self.set('title', response.hits.hits[0]._source.title);
                                _self.set('contentType', 'webinar');
                                _self.set('cid', contentId);
                                _self.set('authorList', response.hits.hits[0]._source.creatorlist.creator);
                                _self.set('sku', contentId);
                                _self.set('videoSourcePath', response.hits.hits[0]._source.filePath);

                               /*
                                * update the social sharing meta information
                                */
                              /* $("meta[property='og\\:title']").attr("content", _self.get('title'));
                               var summary = _self.get('summary');
                               var summary =  (summary.length > 140) ? summary.substring(0,140) + '...' : summary;
                               $("meta[property='og\\:description']").attr("content", summary);
                               var url = 'http://www.computer.org/portal/web/myhome/content?type='  + _self.get('contentType') + '&cid=' + _self.get('cid');
                               $("meta[property='og\\:url']").attr("content", url);
                               $("meta[itemprop='name']").attr("content", _self.get('title'));
                               $("meta[itemprop='description']").attr("content", summary);

                               // reparse the twitter and facebook buttons?
                               FB.XFBML.parse();
                               twttr.widgets.load();  */

                               /*
                                * Verify that they have full access to the webinar
                                * based on their user purchase data
                                */
                                if(userPurchaseData !== undefined &&
                                   userPurchaseData != '' &&
                                   userPurchaseData.units !== undefined) {

                                    // get the list of expired webinars
                                    var expiredWebinars =  _self.getExpiredSkus(userPurchaseData);

                                    // iterate over the webinar dois that the user currently is subscribed to
                                    var idx=0;
                                    for(idx=0;idx<userPurchaseData.units.webinars.length;idx++) {
                                        if(contentId == userPurchaseData.units.webinars[idx]) {
                                            // only set full access of the current sku is not in an expired webinar bundle
                                            if($.inArray(contentId, expiredWebinars) < 0) {
                                               _self.set('hasFullAccess', true);
                                            }
                                            break;
                                        }
                                    }
                                } // end if
                            } // end if

                            // if they are NOT authenticated, remove their full access
                            <c:if test="${!isAuthenticated}">
                                _self.set('hasFullAccess', false);
                            </c:if>

                             // the the UI that loading is complete
                             _self.set('isLoading', false);

                             // get the video source path off the controller
                             var sourcePath = _self.get('videoSourcePath');

                             // if there is a video source path load the video, else show error after a second
                             if(sourcePath == '' || sourcePath == undefined) {
                                 setTimeout(function() {
                                     $("#webinar-not-found-${id}").removeClass("hide");
                                     $("#webinar-not-found-${id}").addClass("in");
                                 }, 1000);
                             } else {
                                 // since there is a source path for the video, we can attempt to load the flowplayer
                                 var flowplayerOptions =  {
                                    playlist: [
                                       // a list of type-url mappings in picking order
                                       [
                                          { mp4: sourcePath }
                                       ]
                                    ],
                                    ratio: 9/16,
                                    swf: "/ieeecs-HomePage-theme/images/flowplayer.swf",
                                    splash: true,
                                    embed: false,
                                    engine: "flash"
                                 };

                                 /*
                                  * If the user has full access keep the full clip,
                                  * otherwise just show a short description
                                  */
                                 if(!_self.get('hasFullAccess')) {
                                    // TODO: Phase2? set 30secs clip? How?
                                 } else {
                                     $(function () {
                                         // this will install flowplayer into the element(s) with class="webinar-flowplayer"
                                         $(".webinar-flowplayer").flowplayer(flowplayerOptions);
                                     });

                                    /*
                                     * NOTE: Due to handlebars loading the dom later, we needed to have the
                                     * flowplayer outside of the handlebars template so it could be initialized
                                     * during the pageload.  Once the dom is loaded, we give it a bit then move
                                     * the flowplayer into the rendered handlebars template.
                                     */
                                    setTimeout(function() {
                                       $(".webinar-flowplayer").appendTo($( ".webinar-flowplayer-container"));
                                       $(".webinar-flowplayer").removeClass("is-mouseout");
                                       $(".webinar-flowplayer").addClass("is-mouseover");
                                       $(".webinar-flowplayer").addClass("fixed-controls");
                                       $(".webinar-flowplayer").show();
                                    }, 1000);
                                }
                             }
                        },
                        error: function(error) {
                            var eMsg = "Content - An error occurred when searching for webinar content: " + error.message;
                             Ember.Logger.error(eMsg);
                             var logData = {};
                             logData.message = eMsg;
                             Log.error(logData);
                            // the the UI that loading is complete
                            _self.set('isLoading', false);
                        },
                        dataType: 'json'
                    });
	        }  else {
	            // stop the loading and show the content not found view
	            _self.set('isLoading', false);
	        }
	      },

	       /**
            * This function will serve as the process to display to the
            * user that their selected item was in fact successfully/unsuccessfully
            * added to their bundle.
            * @param Object payload
            */
	       addItemToBundleConfirmation: function(payload) {
                var _self = this;

                var item = payload.content;
                // set the item to be in the "isSavingToBundle" state
                this.set('isSavingToBundle', false);
                // check to see if there was success adding the item to the bundle or not
                if(payload.result == 200) {
                    // set that the user now has full access to this item
                    this.set('hasFullAccess', item.get('hasFullAccess'));
                    // hide any error/warning alerts
                    this.send('hideAddBundleConfirm');
                    this.send('hideAddBundleError');

                    // else show success message
                    this.send('showAddBundleSuccess');

                    // hide the warning alert
                    $('.alert-warning-container').next().addClass('hide');

                    // clear out the success after a few seconds
                    setTimeout(function() {
                        _self.send('hideAddBundleSuccess');
                    }, 3000);
                } else {
                    // if the response was 500, we know it's a system error, else it's a bundle limit error
                    this.set('bundleLimitError', (payload.result != 500));
                    // display error to the client
                    this.send('showAddBundleError');
                    this.send('hideAddBundleConfirm');
                }
            },

            /**
             * This function will add an item to the user's bundle, depending on if they
             * have the space available to do that.
             */
            addItemToBundle: function() {
                 _self = this;
                 // check to see if bundle has space, if so we can add it to their bundle
                 if(_self.bundleHasSpace()) {

                     // set the item to be in the "isSavingToBundle" state
                     this.set('isSavingToBundle', true);

                     // build up the payload to send to the account portlet
                     var payload = {};
                     payload.content = this;
                     payload.sender = 'ContentApp';

                     // add the item to the bundle, by sending the item data to the account portlet
                     Ember.Instrumentation.instrument('AccountApp.addContentToBundle', payload);
                 } else {
                    /*
                     * if not, show error and ask user if they would like to increase their bundle size
                     * which will have a button for navigating to the purchase page
                     */
                    this.send('showAddBundleError');
                    this.send('hideAddBundleConfirm');
                 }
             },

             /**
              * This function will either notify the authenticated user that they need confirm
              * that they want to add the item to the bundle.  If they are not authenticated,
              * the app will just forward the user to the bundle purchase page.
              */
             showAddBundleConfirm: function() {
                  // if the user is not authenticated, navigate to the bundle purchase page
                  var isAuthenticated = this.get('isAuthenticated');
                  if(isAuthenticated) {
                       $('.alert-warning-container').addClass("in");
                       $('.alert-warning-container').removeClass("hide");
                  } else {
                       this.send('goToBundlePurchase');
                  }
             },
             hideAddBundleConfirm: function() {
                 $('.alert-warning-container').addClass('hide');
                 $('.alert-warning-container').removeClass('in');
             },
             showAddBundleError: function() {
                 $('.alert-danger-container').addClass("in");
                 $('.alert-danger-container').removeClass("hide");
             },
             hideAddBundleError: function() {
                 $('.alert-danger-container').addClass('hide');
                 $('.alert-danger-container').removeClass('in');
             },
             showAddBundleSuccess: function() {
                 $('.alert-success-container').addClass("in");
                 $('.alert-success-container').removeClass("hide");
             },
             hideAddBundleSuccess: function() {
                 $('.alert-success-container').addClass('hide');
                 $('.alert-success-container').removeClass('in');
             },

             /**
              * This function will forward the user to the bundle purchase page for
              * the specific content type.
              */
             goToBundlePurchase: function() {
                   var type = this.get('type');
                   if(type == 'article') {
                       // navigate to the bundle purchase article page
                       window.location = '/portal/web/myhome/purchase-bundle?t=a';
                   } else if (type == 'webinar') {
                       // navigate to the bundle purchase webinar page
                       window.location = '/portal/web/myhome/purchase-bundle?t=w';
                   }
             }
	  }
	});

    // This is the Route handler for the Content App
	ContentApp.ContentRoute = Ember.Route.extend({

	 /**
      * This is a standard function of Ember to initialize the controller.
      * Here we are creating the Ember subscriptions to certain channels.
      * @param Ember.Controller controller
      */
	  setupController: function(controller) {
	    controller.set('content', ContentApp.Content.create({}));
	    Ember.Instrumentation.subscribe("ContentApp.init", {
	      before: function (name, timestamp, payload) {
	       controller.send('init', payload);
	      },
	      after: function () { }
	    });
	     Ember.Instrumentation.subscribe("ContentApp.addItemToBundleConfirmation", {
               before: function (name, timestamp, payload) {
                   controller.send('addItemToBundleConfirmation', payload);
               },
               after: function () { }
           });
           Ember.Instrumentation.subscribe("ContentApp.resetState", {
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
		// Finally call the controller function to perform the initial load
		Ember.Instrumentation.instrument('ContentApp.init', '');
	});
</script>
