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
</style>
<div id="homepage-content-container-${id}" class="col-md-12 col-sm-12">
    <div class="webinar-flowplayer"></div>
</div> <!-- /#homepage-content-container-${id} -->
<!--<script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>-->
<script>
    Ember.TEMPLATES['article'] = Ember.Handlebars.compile('<div> <div class="row"> <h4 class="col-md-3 col-sm-3 content-date label-info">{{publicationDateFormatted}}</h4> </div> <div class="row header-info"> <h1>{{title}}</h1> <h3>{{publisher}}</h3> {{#if authors}} <h6>by {{authors}}</h6> {{/if}} </div> </div> <!-- /.container --> <hr> <div class="content-body"> <div class="alerts-container"> <div class="alert-success-container alert alert-block alert-success fade hide success"> <button type="button" class="close" aria-hidden="true" {{ action "hideAddBundleSuccess" }}>x</button> <h4><i class="icon-check-sign icon-3x icon-fixed-width"></i>Added To Bundle!</h4> </div> <!-- /.success --> <div class="alert-danger-container alert alert-block alert-danger fade hide error"> <button type="button" class="close" aria-hidden="true" {{ action "hideAddBundleError" }}>x</button> {{#if bundleLimitError}} <p> <i class="icon-warning-sign icon-3x icon-fixed-width"></i> Your bundle is at its limit, would you like to increase the size? <a class="btn btn-default" href="#" {{ action "goToBundlePurchase" }}>Make it happen</a> </p> {{else}} <h4><i class="icon-exclamation-sign icon-3x icon-fixed-width"></i>There was a problem adding this item to your bundle, please try again or contact help@computer.org.</h4> {{/if}} </div> <!-- /.error --> <div class="alert-warning-container alert alert-block alert-warning fade hide warning"> <button type="button" class="close" aria-hidden="true"  {{ action "hideAddBundleConfirm" }}>x</button> <p> <i class="icon-warning-sign icon-3x icon-fixed-width"></i> Are you sure you would like to add this item to your bundle? <button {{bindAttr class=":btn :btn-default isSavingToBundle:disabled"}} {{ action "addItemToBundle" }}> {{#unless isSavingToBundle }} Of Course {{else}} Please Wait {{/unless}} </button> </p> </div> <!-- /.warning --> </div> <!-- /.alerts-container --> {{#if hasFullAccess}} {{{summary}}} {{else}} <div class="add-to-bundle-container"> <a href="#" class="btn btn-info btn-xs"  {{ action "showAddBundleConfirm" }}>Add To Bundle</a> </div> {{{summary}}} <br /><br /> <a href="/portal/web/myhome/article-bundle" class="btn btn-medium btn-block btn-primary">Learn More About Our Article Bundles</a> {{/if}} </div> <!-- /.content-body -->');
    Ember.TEMPLATES['content'] = Ember.Handlebars.compile('{{#if isLoading}} <div class="content-loading-container"> <i class="icon-spinner icon-spin icon-large"></i> Loading </div> {{else}} {{#if isArticle}} {{view ContentApp.ArticleView}} {{else}} {{#if isWebinar}} {{view ContentApp.WebinarView}} {{else}} <div class="alert-warning-container alert alert-block alert-warning fade in warning"> <h2> <i class="icon-warning-sign icon-2x icon-fixed-width"></i> Content Not Found. </h2> <p>Please verify that you selected the correct item.  If you are still having trouble contact <strong>help@computer.org</strong> for help.</p> </div> <!-- /.warning --> {{/if}} {{/if}} {{/if}}');
	Ember.TEMPLATES['webinar'] = Ember.Handlebars.compile('<div> <div class="row"> <h4 class="col-md-3 col-sm-3 content-date label-info">{{publicationDateFormatted}}</h4> </div> <div class="row header-info"> <h2>{{title}}</h2> <h3>{{publisher}}</h3> {{#if authorList}} <h6>by {{authors}}</h6> {{/if}} </div> </div> <!-- /.container --> <hr> <div class="content-body"> {{#if hasFullAccess}} <p> {{{summary}}} </p> <br /> <div class="webinar-flowplayer-container"></div> <div id="webinar-not-found-${id}" class="alert-warning-container alert alert-block alert-warning fade hide warning"> <h2> <i class="icon-warning-sign icon-2x icon-fixed-width"></i> Webinar Not Found. </h2> <p>Please verify that you selected the correct item.  If you are still having trouble contact <strong>help@computer.org</strong> for help.</p> </div> <!-- /.warning --> {{else}} <p> {{{summary}}} </p> <br /><br /> <a href="/portal/web/myhome/webinar-bundle" class="btn btn-medium btn-block btn-primary">Learn More About Our Webinars</a> {{/if}} </div><!-- /.content-body -->');
	ContentApp = Ember.Application.create({
		 rootElement: '#homepage-content-container-${id}'
	});

	ContentApp.Router.map(function() {
		this.route("content", { path: "/" });
	});

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
	  getExpiredSkus: function(userPurchaseData) {
	     var retVal = [];
	     var today = new Date();
            var xx=0;
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
	  actions: {
	      resetState: function(data) {
	          // reset the controller to its default values
	          this.set('isLoading', true);
                 this.set('bundleLimitError', true);
	          // set the updated userPurchase Data
                 this.set('userPurchaseData', data);
	          // now re-initialize the page
	          this.send('init', '');
	      },
	      init: function(data) {
	        _self = this;
	        var userPurchaseData = _self.get('userPurchaseData');
	        userPurchaseData = (userPurchaseData != '') ? $.parseJSON(userPurchaseData) : '';
	        var type = _self.get('type');
	        var contentId = _self.get('cid');
	        if(type == 'article') {
	            // finally post to the server to retrieve the data
                   var postData = {};
                   postData.requestType_${id} = 'LOAD_ARTICLE_CONTENT';
                   postData.cid_${id} = contentId;

                   // post to portlet to retrieve the content
                   $.post("${ajaxHandlerContent}", postData)
                       .done(function(response) {
                           if(response != undefined && response.csdlresponse != undefined &&
                              response.csdlresponse.contentlist != undefined) {
                               /*
                                * If the data is found, we can set that it is in fact a webinar, otherwise
                                * the "no content found" view will be displayed
                                */
                                _self.set('isArticle', true);

                                var item = response.csdlresponse.contentlist;
                                var plist = item.packagelist;
                                var pack = plist['package'];

                                // set the content model data
                                _self.set('publisher', pack.publisher);
                                _self.set('publicationDate', pack.publicationdate);
                                _self.set('summary', item.summary);
                                _self.set('title', item.title);
                                _self.set('contentType', 'article');
                                _self.set('cid', contentId);
                                _self.set('authorList', item.creatorlist.creator);
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
                           // the the UI that loading is complete
                           _self.set('isLoading', false);
                       }).fail(function(error) {
                           console.log("Error loading the "+ contentType +" content:" + error);
                            // the the UI that loading is complete
                            _self.set('isLoading', false);
                        })
                       .always(function() {});
	        } else if (type=='webinar') {
	            var _self = this;

	            // load the webinar from Elastic search
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

                    // POST on the ES REST API endpoint
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

                             // the the UI that loading is complete
                             _self.set('isLoading', false);
                             var sourcePath = _self.get('videoSourcePath');
                             // if there is a video source path load the video, else show error
                             if(sourcePath == '' || sourcePath == undefined) {
                                 setTimeout(function() {
                                     $("#webinar-not-found-${id}").removeClass("hide");
                                     $("#webinar-not-found-${id}").addClass("in");
                                 }, 1000);
                             } else {
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
                                  * otherwise have the flowplayer only show a 30 seconds
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
                            console.log("An error occurred when searching for webinar content:" + error);
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
             goToBundlePurchase: function() {
                   var type = this.get('type');
                   if(type == 'article') {
                       // navigate to the bundle purchase article page
                       window.location = '/portal/web/myhome/purchase-bundle?=t=a';
                   } else if (type == 'webinar') {
                       // navigate to the bundle purchase webinar page
                       window.location = '/portal/web/myhome/purchase-bundle?t=w';
                   }
             }
	  }
	});

	ContentApp.ContentRoute = Ember.Route.extend({
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
