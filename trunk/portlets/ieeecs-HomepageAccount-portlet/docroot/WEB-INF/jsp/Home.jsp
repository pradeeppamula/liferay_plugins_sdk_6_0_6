<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='accountAjaxHandler' id='accountAjaxHandler' />
<style type="text/css">

    #homepage-account-inner-container-${id} {
        margin: ${innerMargins};
    }

    #homepage-account-container-${id} {
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
        position: relative;
        background-color: #ffffff;
        padding: 20px;
    }

    #homepage-account-container-${id} {
        font-size: 12px;
    }
    #homepage-account-container-${id} h5 {
        color: #2a72b2;
        text-align: center;
    }

    #homepage-account-container-${id} ul {
        -webkit-margin-before: 1em;
        -webkit-margin-after: 1em;
        -webkit-margin-start: 0;
        -webkit-margin-end: 0;
        -webkit-padding-start: 40px;
        overflow: hidden;
        padding: 10px;
        margin: 0;
        background-color: #f9f9f9;
    }

     /* Small devices (tablets, 992px and down) */
    @media (max-width: 992px) {
        .header-text-${id} {
           padding: 7px 0px 0 5px;
           color: #999;
           font-size: 11px;
           line-height: 20px;
           text-transform: uppercase;
           display: inline;
       }
    }
      /* Large devices (tablets, desktops, 993px and up) */
     @media (min-width: 993px) {
        .header-text-${id} {
            padding: 7px 0px 0 5px;
            color: #999;
            font-size: 11px;
            line-height: 42px;
            text-transform: uppercase;
            display: inline;
        }
     }

    .header-number-${id} {
        color: #2a72b2;
        font-size: 30px;
        font-weight: bold;
        text-align: right;
        line-height: 30px;
    }

    .purchase-button-container { margin-top: 10px;}
</style>
<%-- Only show this section if user is NOT signed in --%>
<c:if test="${!isSignedIn}">
     <div id="homepage-account-container-${id}">
        <div id="homepage-account-inner-container-${id}">
         ${accountContent}
        </div> <!-- /#homepage-account-inner-container-${id} -->
    </div>  <!-- /#homepage-account-container-${id} -->
</c:if>

<%-- Only show this section if user is signed in --%>
<c:if test="${isSignedIn}">
    <div id="homepage-account-container-${id}">
        <div id="homepage-account-inner-container-${id}">
        </div> <!-- /#homepage-account-inner-container-${id} -->
    </div>  <!-- /#homepage-account-container-${id} -->
    <script>
            Ember.TEMPLATES['account'] = Ember.Handlebars.compile('<h5>Welcome Back, ${firstName}.</h5> <div class="row-fluid"> <c:if test="${hasArticleBundle}"> <div class="row"> <div id="used-article-count-${id}" class="header-number-${id} col-md-3 col-sm-3 col-xs-3"> {{numberOfArticles}} </div> <div class="header-text-${id}"> articles in your library </div> </div> <div class="row"> <div id="available-count-${id}" class="header-number-${id} col-md-3 col-sm-3 col-xs-3"> {{remainingBundleSpace}} </div> <div class="header-text-${id}"> left in your article bundle </div> </div> </c:if> <c:if test="${hasWebinarBundle}"> <div class="row"> <div id="used-webinar-count-${id}" class="header-number-${id} col-md-3 col-sm-3 col-xs-3"> {{numberOfWebinars}} </div> <div class="header-text-${id}"> webinars in your library </div> </div> </c:if> <!--NOTE: 11.13.13 Hiding the article count for now <div class="row"> <div id="article-timeperiod-count-${id}" class="header-number-${id} col-md-3 col-sm-3 col-xs-3"> <i class="icon-spinner icon-spin"></i> </div> <div class="header-text-${id}"> articles entered <span id="article-count-month-${id}">this month</span> </div> </div> --> </div> <div class="purchase-button-container"> <button class="btn btn-medium btn-block btn-primary" {{ action "goToPurchaseBundle" }}>REQUEST A QUOTE</button> </div>');
            // initialize the account Ember App
            AccountApp = Ember.Application.create({
                 rootElement: '#homepage-account-inner-container-${id}'
            });

            // define the homepage account model
            AccountApp.Data = Ember.Object.extend({});

            // create Ember.ArrayController for managing the homepage account
            AccountApp.AccountController = Ember.ObjectController.extend({
              numberOfArticles: '-',
              numberOfWebinars: '-',
              remainingBundleSpace: '${availableArticles}',
              userPurchaseData: null,

              /**
               * This function will user the user's purchase information to determine
               * if they have any free slots in their specific bundle.  Usually this
               * function will be called when a user is adding items to their bundle.
               * @param String type -  the type of bundle
               * @param Object userPurchaseData - the user's purchase information
               * @return boolean
               */
              bundleHasSpace: function(type, userPurchaseData) {
                  var userContentCount;
                  var bundleType;

                  /*
                   * First check to see if the user has purchase information through
                   * their organization, if they don't, return false so that they will
                   * have to request a quote.
                   */
                   if(userPurchaseData.bundle == undefined ||
                    userPurchaseData.bundle.csdl_article == undefined) {
                    return false;
                   }
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
               * Ember actions object.  Where you need to store all the action
               * handlers.
               */
              actions: {
                  /**
                   * This function will set the user's purchase information on the
                   * controller to be used by this Account App.
                   * @param data
                   */
                  setUserPurchaseData : function(data) {
                    this.set('userPurchaseData', data);
                    this.set('numberOfArticles', data.units.csdl_article.length);
                    this.set('numberOfWebinars', data.units.webinars.length);
                    //  now determine how much they have left for their article bundles
                    var userPurchaseData = this.get('userPurchaseData');
                    var userContentCount;
                    var bundleType;
                    // get the number of content items the user has for the type
                    userContentCount = userPurchaseData.units.csdl_article.length;
                    bundleType =  userPurchaseData.bundle.csdl_article;
                    var totalBundleCount = 0;
                    var idx = 0;
                    for(idx;idx<bundleType.length;idx++) {
                        totalBundleCount += parseInt(bundleType[idx].number_of_items);
                    }
                    // now set the number remaining for the user on the UI
                    this.set('remainingBundleSpace',totalBundleCount-userContentCount);
                  },

                  /**
                   * This function will navigate the user to the purchase bundle page
                   */
                  goToPurchaseBundle: function() {
                      // navigate to the purchase bundle page
                      window.location = '/portal/web/myhome/purchase-bundle';
                  },

                  /**
                   * This function is responsible for accepting a payload which contains
                   * the content item and adding it to the user's bundle if possible.  It will
                   * first load the user's purchase data, then verify if the user has enough
                   * free space in their bundle, and finally update it if so.  We then use
                   * Ember Instruments to publish the result of this action to the subscribers, which
                   * will then display the result to the user in the UI.
                   * @param Object payload
                   */
                  addContentToBundle: function(payload) {

                      var _self = this;
                      var item = payload.content;

                      // First refresh the user purchase data
                      var data = {};
                      data.requestType_${id} = 'LOAD_USER_PURCHASE_DATA';

                        // post to portlet to retreive the  user purchase data
                        $.post("${accountAjaxHandler}", data)
                        .done(function(response) {

                            // first update this portlet's UI
                            _self.send('setUserPurchaseData', response);
                            var userPurchaseData = _self.get('userPurchaseData');

                             // check to see if there is space in the bundle for this item
                             if(_self.bundleHasSpace(item.get('contentType'), userPurchaseData)) {

                                  // add the item to the bundle
                                  if(item.get('contentType') == 'article') {
                                      item.set('hasFullAccess', true);
                                      userPurchaseData.units.csdl_article.pushObject(item.get('doi'));
                                  }

                                  // now build the data object for the next request to update the purchase data
                                  var data = {};
                                  data.requestType_${id} = 'UPDATE_USER_PURCHASE_DATA';

                                  // update the purchase data's modified date time
                                  userPurchaseData.modified_date = new Date().toISOString();
                                  data.purchaseData_${id} = JSON.stringify(userPurchaseData);

                                  // finally update the userPurchaseData in the datasource
                                  $.post("${accountAjaxHandler}", data)
                                      .done(function(response) {
                                       if(response == 200) {
                                            _self.send('setUserPurchaseData', userPurchaseData);

                                            // if successful, send success result back to the sender
                                            payload.result = 200;
                                            payload.content = item;
                                            // send back the item to the sender with success code
                                            Ember.Instrumentation.instrument(''+payload.sender+'.addItemToBundleConfirmation', payload);

                                            //  update all portlets that need access to the the user purchase data
                                            try {
                                                Ember.Instrumentation.instrument('SearchApp.setUserPurchaseData', userPurchaseData);
                                            } catch (error) {} // digest just in case app is not available
                                            try {
                                                Ember.Instrumentation.instrument('SubscriptionContent.resetState', userPurchaseData.units);
                                            } catch (error) {} // digest just in case app is not available
                                            try {
                                                Ember.Instrumentation.instrument('ContentApp.resetState', JSON.stringify(userPurchaseData));
                                            } catch (error) {} // digest just in case app is not available
                                        } else {
                                            payload.result = 500;
                                            // send back the item to the sender with error code
                                            Ember.Instrumentation.instrument(''+payload.sender+'.addItemToBundleConfirmation', payload);
                                        }
                                    })
                                   .fail(function(error) {
                                       var eMsg = "Account - Error loading this purchase data: " + error.message;
                                        Ember.Logger.error(eMsg);
                                         var logData = {};
                                         logData.message = eMsg;
                                         Log.error(logData);
                                       payload.result = 500;
                                       // send back the item to the sender with error code
                                       Ember.Instrumentation.instrument(''+payload.sender+'.addItemToBundleConfirmation', payload);
                                   })
                                   .always(function() {});
                             } else {
                                 payload.result = 422;
                                // send back the item to the sender with error code
                                Ember.Instrumentation.instrument(''+payload.sender+'.addItemToBundleConfirmation', payload);
                             }
                        })
                        .fail(function(error) {
                          var eMsg = "Account - Error loading the purchase data: " + error.message;
                          Ember.Logger.error(eMsg);
                             var logData = {};
                             logData.message = eMsg;
                             Log.error(logData);
                            payload.result = 500;
                            // send back the item to the sender with error code
                            Ember.Instrumentation.instrument(''+payload.sender+'.addItemToBundleConfirmation', payload);
                        })
                        .always(function() {});
                  }
              }
            });

            // force the root path to utilize the "account" template
            AccountApp.Router.map(function() {
              this.route("account", { path: "/" });
            });

            // for the initial index view
            AccountApp.AccountRoute = Ember.Route.extend({
              setupController: function(controller) {
                Ember.Instrumentation.subscribe("AccountApp.setUserPurchaseData", {
                  before: function (name, timestamp, payload) {
                    controller.send('setUserPurchaseData', payload);
                  },
                  after: function () { }
                });
                Ember.Instrumentation.subscribe("AccountApp.addContentToBundle", {
                  before: function (name, timestamp, payload) {
                    controller.send('addContentToBundle', payload);
                  },
                  after: function () { }
                });
              }
            });
    </script>
    <script>
        $(document).ready(function() {
            var monthShort = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
            var monthFull = [ "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December" ];
            var numOfDaysInMonth = ["31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"];
            var now = new Date();
            var currMonth = monthShort[now.getMonth()];
            var currYear = now.getFullYear();
            var currMonthNumOfDays = numOfDaysInMonth[now.getMonth()];

            var currMonthFull = monthFull[now.getMonth()];
            $('#article-count-month-${id}').html('in ' + currMonthFull);

            var data = {};
            data.startDate_${id} = ''+currMonth+' 01, '+currYear+'';
            data.endDate_${id} =  ''+currMonth+' '+currMonthNumOfDays+', '+currYear+'';
            data.requestType_${id} = 'numOfArticles';

            /* NOTE - 11.13.13 - we are commenting this portion out for now
            // post to portlet to retreive the number of articles for month
            // NOTE: 02.21.14 - This can hit the new CSDL datasource?
            var jqxhr = $.post("${accountAjaxHandler}", data)
                .done(function(response) {
                    $('#article-timeperiod-count-${id}').show();
                    $('#article-timeperiod-count-${id}').html(response.numOfArticles);
                })
                .fail(function(error) {
                    var eMsg = "Account - Error loading article count: " + error.message;
                    Ember.Logger.error(eMsg);
                     var logData = {};
                     logData.message = eMsg;
                     Log.error(logData);
                })
                .always(function() {});
            */

            var pData = {};
            pData.startDate_${id} = ''+currMonth+' 01, '+currYear+'';
            pData.endDate_${id} =  ''+currMonth+' '+currMonthNumOfDays+', '+currYear+'';
            pData.requestType_${id} = 'LOAD_USER_PURCHASE_DATA';
            /*
             * finally post again to the portlet to get the purchase data and
             * send this data out to the necessary Ember apps.
             */
             $.post("${accountAjaxHandler}", pData)
                .done(function(response) {
                    Ember.Instrumentation.instrument('AccountApp.setUserPurchaseData', response);
                    Ember.Instrumentation.instrument('SearchApp.setUserPurchaseData', response);
                })
                .fail(function(error) {
                    var eMsg = "Account  - Error on initial load of user purchase data: " + error.message;
                     Ember.Logger.error(eMsg);
                     var logData = {};
                     logData.message = eMsg;
                     Log.error(logData);
                })
                .always(function() {});

        });
    </script>
</c:if>