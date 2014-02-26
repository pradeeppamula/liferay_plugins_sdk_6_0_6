<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='ajaxHandlerPurchase' id='ajaxHandlerPurchase' />

<style type="text/css">
    #homepage-purchase-container-${id} {
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
    }
    .footer-social-links > a:hover {
        text-decoration: none;
    }
    .footer-links,.footer-social-links {
        line-height: 40px;
        text-align:center;
    }
    .footer-links > a, .footer-social-links > a {
        color: #ffffff;
    }
    .copy-section { padding: 0 5px;}
    .footer-copy-section {
        text-align: center;
    }
    .purchase-bundle-form-container {
        padding: 0 25px 20px 25px;
    }
    .purchase-form {
        padding: 30px 22%;
        background-color: #ffffff;
    }
    .purchase-bundle-header-container {
        color: #ffffff;
    }

    input .flat-gray {
        background-color: #eee !important;
        border: 0px solid #eee !important;
    }
</style>

<div id="homepage-purchase-container-${id}">
</div> <!-- /#homepage-purchase-container-${id} -->

<script>

    // compile the handlebar templates to be used by Ember
    Ember.TEMPLATES['purchase'] = Ember.Handlebars.compile('<div class="purchase-bundle-form-container col-xs-12"> <div class="purchase-bundle-header-container"> <h3>Request A Quote</h3> <p> <i class="icon-info-sign icon-fixed-width"></i> <small>Please complete the form below to request a bundle.</small> </p> </div> <div id="success-alert-${id}" class="alert alert-block alert-success fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeSuccessAlert" }}>x</button> <h4><i class="icon-check-sign icon-3x icon-fixed-width"></i>Thank you for requesting a bundle.  A representative will be contacting you shortly to discuss your bundle details.</h4> </div> <!-- /#success-alert-${id} --> <div id="val-error-alert-${id}" class="alert alert-block alert-danger fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeValErrorAlert" }}>x</button> <h4><i class="icon-exclamation-sign icon-3x icon-fixed-width"></i><span id="error-message-${id}"></span></h4> </div> <!-- /#val-error-alert-${id} --> <div class="purchase-form well"> <form role="form"> {{#unless hasType}} <div class="form-group item-content-type-${id}"> <label class="control-label" for="item-content-type-${id}"> Bundle Type </label> {{view Ember.Select contentBinding="contentTypes"valueBinding="type"classNames="form-control"prompt="select a type"}} </div> {{/unless}} <div class="form-group name-${id}"> <label class="control-label" for="name-${id}">Name</label> {{view Ember.TextField valueBinding="name" classNames="form-control flat-gray" placeholder="Name"}} </div> <div class="form-group email-${id}"> <label class="control-label" for="company-${id}">Email</label> {{view Ember.TextField valueBinding="email" classNames="form-control flat-gray" placeholder="Email"}} </div> <div class="form-group company-${id}"> <label class="control-label" for="company-${id}">Company</label> {{view Ember.TextField valueBinding="company" classNames="form-control flat-gray" placeholder="Company"}} </div> <div class="form-group title-${id}"> <label class="control-label" for="title-${id}">Title</label> {{view Ember.TextField valueBinding="title" classNames="form-control flat-gray" placeholder="Title"}} <p class="help-block">Not Required</p> </div> <div class="form-group phone-${id}"> <label class="control-label" for="phone-${id}">Phone</label> {{view Ember.TextField valueBinding="phone" classNames="form-control flat-gray" placeholder="Phone Number"}} </div> </form> <div> <button type="button" {{bindAttr class=":btn :btn-primary :btn-block isSaving:disabled"}} {{ action "submitForm" }}> {{#if isSaving}} Please Wait... {{else}} Request Quote {{/if}} </button> </div> </div><!-- /.purchase-form --> </div> <!-- /.purchase-bundle-form-container --> <div class="row footer-links"> <a href="/portal/web/myhome/home">Home</a> <a href="/portal/web/myhome/article-bundle">CSDL</a> <a href="/portal/web/myhome/webinar-bundle">Webinars</a> <a href="http://www.ieee.org/site_terms_conditions.html?WT.mc_id=hpf_terms">Terms</a> <a href="http://www.computer.org/portal/web/guest/privacy">Privacy</a> <a href="#top">Top</a> </div> <div class="row footer-social-links"> <a href="mailto:help@computer.org"><i class="icon-envelope icon-2x"></i></a> <a class="metrics-capture" href="https://twitter.com/computersociety" target="_blank"><i class="icon-twitter icon-2x"></i></a> <a class="metrics-capture" href="https://www.facebook.com/ieeecomputersociety?v=wall" target="_blank"><i class="icon-facebook icon-2x"></i></a> <a class="metrics-capture" href="https://plus.google.com/b/106653716856205029801/#106653716856205029801/posts" target="_blank"><i class="icon-google-plus icon-2x"></i></a> <a class="metrics-capture" href="http://www.linkedin.com/groups?home=&amp;gid=52513&amp;trk=anet_ug_hm" target="_blank"><i class="icon-linkedin icon-2x"></i></a> <a class="metrics-capture" href="http://www.youtube.com/user/ieeeComputerSociety" target="_blank"><i class="icon-youtube icon-2x"></i></a> <a class="metrics-capture" href="http://www.computer.org/portal/web/csdl/dlrss" target="_blank"><i class="icon-rss icon-2x"></i></a> </div> <div class="row footer-copy-section"> <p class="text-muted"> <span class="text-primary copy-section"></span> <i class="icon-keyboard"></i> in LA </p> </div>');

    // initialize the purchase Ember App
    PurchaseApp = Ember.Application.create({
         rootElement: '#homepage-purchase-container-${id}',
    });

    // define the featured content model
    PurchaseApp.Data = Ember.Object.extend({
        name: '',
        email: '',
        company: '',
        phone: '',
        title: '',
    });

    // define the purchase controller
    PurchaseApp.PurchaseController = Ember.ObjectController.extend({
        type: '${type}',
        hasType: false,
        contentTypes: ['article', 'webinar'],
        isSaving: false,
        validationMessage: 'Please enter all required information.',
        errorMessage: 'There was a problem submitting your bundle request.  Please try again or contact help@computer.org.',

          /**
           * This helper function will clear out all the
           * error alerts by removing the bootstrap "has-error"
           * class where necessary.
           */
         clearValidationErrors: function() {
            // remove the error classes
            $('.name-${id}').removeClass("has-error");
            $('.company-${id}').removeClass("has-error");
            $('.email-${id}').removeClass("has-error");
            $('.phone-${id}').removeClass("has-error");
            $('.item-content-type-${id}').removeClass("has-error");
            // remove the error alert
            this.send('closeValErrorAlert');
          },

          /**
           * This helper function will validate the form based on the user's input.
           * @return boolean
           */
          isValidForm: function() {
            // first clear any old validation errors
            this.clearValidationErrors();
            var retVal = true;
            var name = this.get('name');
            var company = this.get('company');
            var email = this.get('email');
            var phone = this.get('phone');

            // if there isn't a type already specified, then we should validate that
            // the user has selected a type
            if(!this.get('hasType')) {
                var type = this.get('type');
                 if (type == undefined || type.trim() === "") {
                    // add "has-error" class to the type container
                    $('.item-content-type-${id}').addClass("has-error");
                    retVal = false;
                }
            }

            // name is required
            if (name === undefined || name.trim() === "") {
                // add "has-error" class to the name container
                $('.name-${id}').addClass("has-error");
                retVal = false;
            }
            // their company is required
            if (company === undefined || company.trim() === "") {
                // add "has-error" class to the company container
                $('.company-${id}').addClass("has-error");
                retVal = false;
            }
            // an email is required
            if (email === undefined || email.trim() === "") {
               // add "has-error" class to the email container
               $('.email-${id}').addClass("has-error");
               retVal = false;
           }
           // a phone number is required
            if (phone === undefined || phone.trim() === "") {
               // add "has-error" class to the phone container
               $('.phone-${id}').addClass("has-error");
               retVal = false;
           }
            return retVal;
          },

        /**
         * This is the required actions object that Ember wants you put
         * your functions that handle actions from Handlebars templates.
         */
        actions: {

           /**
            * Helper function that will set the type of bundle on the controller
            * that the user is requesting for purchase.
            * @param String payload - the bundle type
            */
           setHasType: function(payload) {
             this.set('hasType', payload);
           },

           /**
            * This function will submit the form to the server with the user's purchase information
            * for sales to view via an email that is sent out.
            */
           submitForm: function() {
             //  validate the form
             if(this.isValidForm()) {
                // clear out any validation errors
                this.clearValidationErrors();

                // set the save button to be in the loading state
                this.set('isSaving', true);

                  // finally post to the server
                 var postData = {};
                 postData.requestType_${id} = 'PURCHASE_BUNDLE';
                 postData.name_${id} = this.get('name');
                 postData.title_${id} = this.get('title');
                 postData.company_${id} = this.get('company');
                 postData.email_${id} = this.get('email');
                 postData.phone_${id} = this.get('phone');
                 postData.bundleType_${id} = this.get('type');

                 // post to portlet to send the bundle request to sales
                 $.post("${ajaxHandlerPurchase}", postData).then(function(response) {
                     // stop the loading state of the button
                     this.set('isSaving', false);

                     if(response == 200) {
                         // show successful save alert
                         $('#success-alert-${id}').addClass("in");
                         $('#success-alert-${id}').removeClass("hide");
                     } else {
                        this.send('showErrorMessage');
                     }
                 }.bind(this), function() {
                   this.set("isSaving", false);
                   this.send('showErrorMessage');
                 }.bind(this));
             } // end if
             else { // show validation alert
                 this.send('showValidationErrorMessage');
             }
           },
           showErrorMessage: function() {
                $('#error-message-${id}').html(this.get('errorMessage'));
                 $('#val-error-alert-${id}').addClass("in");
                 $('#val-error-alert-${id}').removeClass("hide");
           },
           showValidationErrorMessage: function() {
                $('#error-message-${id}').html(this.get('validationMessage'));
                  $('#val-error-alert-${id}').addClass("in");
                  $('#val-error-alert-${id}').removeClass("hide");
           },
           closeSuccessAlert: function() {
               $("#success-alert-${id}").addClass('hide');
               $("#success-alert-${id}").removeClass('in');
           },
           closeValErrorAlert: function() {
               $('#val-error-alert-${id}').addClass('hide');
               $('#val-error-alert-${id}').removeClass('in');
           }
        } // end actions
    });

    // Define the router root path for this application
    PurchaseApp.Router.reopen({
      rootURL: '/purchase/'
    });

    // define the app routes
    PurchaseApp.Router.map(function() {
        this.route("purchase", { path: "/" });
    });

    // Handle the index route, and set up the subscribers for the App
    PurchaseApp.PurchaseRoute = Ember.Route.extend({
      setupController: function(controller) {
        controller.set('content', PurchaseApp.Data.create({}));
         // register the set has type listener
         Ember.Instrumentation.subscribe("PurchaseApp.setHasType", {
          before: function (name, timestamp, payload) {
           controller.send('setHasType', payload);
          },
          after: function () { }
        });
      }
    });
</script>
<script>
    $(document).ready(function() {
        var type = '${type}';
        Ember.Instrumentation.instrument('PurchaseApp.setHasType', (type != ''));
        var now = new Date();
        var currYear = now.getFullYear();
        $('.copy-section').html('&copy; ' + currYear + ' IEEE. All Rights Reserved.');
        // hide the search bar
        try {
            // publish the message out to the SearchApp
            Ember.Instrumentation.instrument('SearchApp.hideSearchApp', '');
        } catch(error) {}
    });
</script>

