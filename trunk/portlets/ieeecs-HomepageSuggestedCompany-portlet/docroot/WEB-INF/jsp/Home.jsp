<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='suggestedCompaniesAjaxHandler' id='suggestedCompaniesAjaxHandler' />
<%-- Only show portlet if user is signed in --%>
<c:if test="${isSignedIn}">

	<style type="text/css">
		#homepage-suggested-company-container-${id} {
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
			padding: 20px 0px;		
			position: relative;
		}	
		#homepage-suggested-company-container-${id}:hover #edit-suggested-companies-${id} {
			display: block;
		}

		#homepage-suggested-company-container-${id} .header {
			color: #999;
			font-size: 16px;
			line-height: 16px;
			font-weight: 500;
		}
		.suggested-company-img-container-${id} > img {
			width: 200px;
			height: 200px;
		}
		.suggested-company-header, .suggested-company-footer {
			padding: 0 20px;
		}
		.company img {
			width: 100%;
		}
		.company {
			line-height: 80px;
			height: 80px;
			filter: alpha(opacity=40);
  			opacity: 0.4;
			-webkit-transition: opacity 0.1s ease-in-out;
			-moz-transition: opacity 0.1s ease-in-out;
			-ms-transition: opacity 0.1s ease-in-out;
			-o-transition: opacity 0.1s ease-in-out;
			transition: opacity 0.1s ease-in-out;
			overflow: hidden;
			border: solid 1px #cccccc;
			margin: 3px;
			-webkit-border-radius: 5px;
			-moz-border-radius: 5px;
			border-radius: 5px;
		}
		.company:hover {
			filter: alpha(opacity=100);
  			opacity: 1.0;
  			cursor: pointer;
		}
		<c:if test="${canInlineEdit}">
		/* INLINE EDIT STYLES */
		.full-opacity {
			filter: alpha(opacity=100);
  			opacity: 1.0;
		}
		.suggested-companies-layout-container {
			position: relative;
			height: 260;
		}
		.edit-company-form-container {
			position: relative;
			margin-top: 15px;
		}
		#edit-suggested-companies-${id} {
			display: none;
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
		/* END INLINE EDIT STYLES */
		</c:if>
	</style>

	<div id="homepage-suggested-company-container-${id}">
	</div> <!-- /#homepage-suggested-company-container-${id} -->

	<script>
	    Ember.TEMPLATES['companies'] = Ember.Handlebars.compile('<c:if test="${canInlineEdit}"> <span id="edit-suggested-companies-${id}" class="label label-default"><a class="inline-edit" {{action "editSugggestedCompanies"}}><i class="icon-edit-sign"></i>&nbsp;Edit</a></span> </c:if> <div class="suggested-company-header"> <a class="text-muted pull-right hide" href="#">View all</a> <p class="header">Suggested companies</p> </div> <div class="col-md-offset-2 col-sm-offset-1 col-xs-offset-2 row"> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company1}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company1.name"}}> <img {{bindAttr src="company1.imageURL"}} /> </div> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company2}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company2.name"}}> <img {{bindAttr src="company2.imageURL"}} /> </div> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company3}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company3.name"}}> <img {{bindAttr src="company3.imageURL"}} /> </div> </div> <div class="col-md-offset-2 col-sm-offset-1 col-xs-offset-2 row"> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company4}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company4.name"}}> <img {{bindAttr src="company4.imageURL"}} /> </div> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company5}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company5.name"}}> <img {{bindAttr src="company5.imageURL"}} /> </div> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company6}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company6.name"}}> <img {{bindAttr src="company6.imageURL"}} /> </div> </div> <div class="col-md-offset-2 col-sm-offset-1 col-xs-offset-2 row"> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company7}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company7.name"}}> <img {{bindAttr src="company7.imageURL"}} /> </div> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company8}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company8.name"}}> <img {{bindAttr src="company8.imageURL"}} /> </div> <div class="company col-md-3 col-sm-3 col-xs-3" {{action "goToURL" company9}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company9.name"}}> <img {{bindAttr src="company9.imageURL"}} /> </div> </div> <div class="text-right suggested-company-footer"> <!-- TODO: Create feedback module Phase 2/3? <a href="#"><i class="icon-comment icon-fixed-width"></i>Feedback</a> --> </div> <!-- INLINE EDIT MODAL --> <div class="modal fade" id="suggested-companies-edit-modal-${id}" tabindex="-1" role="dialog" aria-labelledby="suggestedCompaniesPortletAdmin" aria-hidden="true"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" {{action "closeSuggestedCompaniesModal"}} aria-hidden="true">&times;</button> <h4 class="modal-title">Suggested Companies Administration</h4> </div> <!-- /.modal-header --> <div class="modal-body"> <h5><i class="icon-info-sign icon-fixed-width"></i>Click on the item that you would like to change.</h5> <div class="suggested-companies-layout-container"> <div class="col-md-offset-2 col-sm-offset-2 row"> <div id="edit-company-${id}-1" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company1}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company1.name"}}> <img {{bindAttr src="company1.imageURL"}} /> </div> <div id="edit-company-${id}-2" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company2}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company2.name"}}> <img {{bindAttr src="company2.imageURL"}} /> </div> <div id="edit-company-${id}-3" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company3}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company3.name"}}> <img {{bindAttr src="company3.imageURL"}} /> </div> </div> <div class="col-md-offset-2 col-sm-offset-2 row"> <div id="edit-company-${id}-4" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company4}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company4.name"}}> <img {{bindAttr src="company4.imageURL"}} /> </div> <div id="edit-company-${id}-5" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company5}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company5.name"}}> <img {{bindAttr src="company5.imageURL"}} /> </div> <div id="edit-company-${id}-6" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company6}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company6.name"}}> <img {{bindAttr src="company6.imageURL"}} /> </div> </div> <div class="col-md-offset-2 col-sm-offset-2 row"> <div id="edit-company-${id}-7" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company7}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company7.name"}}> <img {{bindAttr src="company7.imageURL"}} /> </div> <div id="edit-company-${id}-8" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company8}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company8.name"}}> <img {{bindAttr src="company8.imageURL"}} /> </div> <div id="edit-company-${id}-9" class="company col-md-3 col-sm-3" {{action "selectCompanyForEdit" company9}} data-toggle="tooltip" data-placement="top" {{bindAttr data-original-title="company9.name"}}> <img {{bindAttr src="company9.imageURL"}} /> </div> </div> </div> <!-- ./suggested-companies-layout-container --> <div class="edit-company-form-container"> <div id="changed-company-alert-${id}" class="alert alert-block alert-warning fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeChangeAlert" }}>x</button> <h4><i class="icon-warning-sign icon-3x icon-fixed-width"></i>Whoa!</h4> <p>You will need to click the <strong>"Save Changes"</strong> button in order for your changes to be saved.</p> <a class="btn btn-default" href="#" {{ action "discardChanges"}}>Discard Changes</a> </div> <!-- /#changed-company-alert-${id} --> <div id="saved-company-alert-${id}" class="alert alert-block alert-success fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeSavedAlert" }}>x</button> <h4><i class="icon-check-sign icon-3x icon-fixed-width"></i>Changes Saved!</h4> </div> <!-- /#saved-company-alert-${id} --> <div id="val-error-company-alert-${id}" class="alert alert-block alert-danger fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeValErrorAlert" }}>x</button> <h4><i class="icon-exclamation-sign icon-3x icon-fixed-width"></i>Please enter all required information.</h4> </div> <!-- /#val-error-company-alert-${id} --> <form role="form"> <div class="form-group company-name-${id}"> <label class="control-label" for="company-name-${id}">Name</label> {{view Ember.TextField valueBinding="activeEditCompany.name" classNames="form-control" placeholder="name"}} </div> <div class="form-group company-url-${id}"> <label class="control-label" for="company-url-${id}">Website URL</label> {{view Ember.TextField valueBinding="activeEditCompany.url" classNames="form-control" placeholder="url"}} </div> <div class="form-group company-imageURL-${id}"> <label class="control-label" for="company-imageURL-${id}">Image URL</label> {{view Ember.TextField valueBinding="activeEditCompany.imageURL" classNames="form-control" placeholder="url"}} <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p> <!-- NOTE: add back in for phase 2 <p class="help-block">Or, you can also simply upload an image.</p> --> </div> <!-- NOTE: add back in for phase 2 <div class="form-group company-file-${id}"> <label class="control-label" for="company-file-${id}">File input</label> <input type="file" id="company-file-${id}"> </div> --> </form> </div> <!-- .edit-company-form-container --> </div> <!-- /.modal-body --> <div class="modal-footer"> <button type="button" class="btn btn-default" {{action "closeSuggestedCompaniesModal"}}>Close</button> <button type="button" {{bindAttr class=":btn :btn-primary activeEditCompany.isDirty::disabled isSaving:disabled"}} {{ action "saveCompanies" }}> {{#if isSaving}} Saving... {{else}} Save changes {{/if}} </button> </div> <!-- /.modal-footer --> </div><!-- /.modal-content --> </div><!-- /.modal-dialog --> </div><!-- /.modal --> <!-- /INLINE EDIT MODAL -->');
		// initialize the suggested company Ember App
		SuggestedCompanyApp = Ember.Application.create({
			 rootElement: '#homepage-suggested-company-container-${id}'
		});

		// define the suggested company model
		SuggestedCompanyApp.Company = Ember.Object.extend({
		  	position: null,
		  	imageURL: "//placehold.it/500x500&text=No+Image",
		  	name: null,
		  	url: null,
			isDirty: false
		});

		SuggestedCompanyApp.Company.reopen({
		  titleChanged: Ember.observer(function() {
		    this.set('isDirty', true);
		  }, 'name','imageURL', 'url', 'position')
		});

		// create Ember.ArrayController for managing the suggested companies models/views
		SuggestedCompanyApp.CompaniesController = Ember.ArrayController.extend({
		  content: [],
		  company1: null,
		  company2: null,
		  company3: null,
		  company4: null,
		  company5: null,
		  company6: null,
		  company7: null,
		  company8: null,
		  company9: null,
		  activeEditCompany: null,
		  preActiveEditCompany: null,
		  isSaving: false,
		  originalData: null,
		  actions: {
		      selectCompanyForEdit: function(company) {
                var currentEditItem = this.get('activeEditCompany');
                if(currentEditItem != undefined && currentEditItem.isDirty == true) {
                    $('#changed-company-alert-${id}').addClass("in");
                    $('#changed-company-alert-${id}').removeClass("hide");
                } else {
                    // remove all full-opacity classes on any of the company containers
                    $( "div[id^='edit-company-${id}-']" ).removeClass('full-opacity');
                    // then add the full-opacity class to the current company container
                    $('#edit-company-${id}-'+company.position).addClass('full-opacity');

                    // remove any old validation errors
                    this.clearValidationErrors();
                    this.set('activeEditCompany', company);

                    // set the "pre" active edit company just in case the user resets the form
                    var preCompany = {};
                    preCompany.name = company.name;
                    preCompany.imageURL = company.imageURL;
                    preCompany.url = company.url;
                    this.set('preActiveEditCompany', preCompany);
                }
              },
              editSugggestedCompanies: function(){
                $("#suggested-companies-edit-modal-${id}").modal("show");
              },
              closeSuggestedCompaniesModal: function() {
                // check to see if there are changes, if so, show this alert
                var currentEditItem = this.get('activeEditCompany');
                if(currentEditItem != undefined && currentEditItem.isDirty == true) {
                    $('#changed-company-alert-${id}').addClass("in");
                    $('#changed-company-alert-${id}').removeClass("hide");
                } else {
                    $("#suggested-companies-edit-modal-${id}").modal("hide");
                }
              },
              setCompanyData : function(data) {
                // set the initial data returned from the server
                this.set('originalData', data);
                // iterate over the companies setting them on the controller
                var idx = 0;
                for (idx; idx < data.companies.length; idx++) {
                    var contentItem = SuggestedCompanyApp.Company.create({
                        position: data.companies[idx].position,
                        imageURL: data.companies[idx].imageURL,
                        name: data.companies[idx].name,
                        url: data.companies[idx].url
                    });
                    this.set('company'+contentItem.position, contentItem);
                };
              },
              saveCompanies: function() {
                if(this.isValidForm()) {
                    // clear out any validation errors
                    this.clearValidationErrors();

                    // set the save button to be in the loading state
                    this.set('isSaving', true);
                    // build the JSON, save it to the server
                    // grab the original data
                    var data = this.get('originalData');
                    var companiesCount = data.companies.length;
                    // clear the old companies, and set the new ones
                    data.companies = [];
                    // remove any unnecessary properties
                    delete(data.exception);
                    var idx = 1;
                    // iterate over the companies
                    for(idx; idx<=companiesCount; idx++) {
                        var currentItem = this.get('company'+idx);
                        if(currentItem != undefined && currentItem.name != null) {
                            var item = buildBaseCompany(currentItem)
                            data.companies.pushObject(item);
                        }
                    }

                    // finally post to the server
                    var postData = {};
                    postData.requestType_${id} = 'saveCompanyData';
                    postData.meta_${id} = JSON.stringify(data);

                    // post to portlet to retreive the featured content
                    $.post("${suggestedCompaniesAjaxHandler}", postData).then(function() {
                        // stop the loading state of the button
                        this.set('isSaving', false);
                        // reset the all items to be in the clean state
                        this.send('cleanModels');
                        // show successful save alert
                        $('#saved-company-alert-${id}').addClass("in");
                        $('#saved-company-alert-${id}').removeClass("hide");

                        // set the originalData to the newly updated data
                        this.set('originalData', data);

                        // clear out the alerts after a few seconds
                        setTimeout(function() {
                            $("#saved-company-alert-${id}").addClass('hide');
                            $("#saved-company-alert-${id}").removeClass('in');
                            $('#changed-company-alert-${id}').addClass('hide');
                            $('#changed-company-alert-${id}').removeClass('in');
                        }, 3000);
                        }.bind(this), function() {
                          this.set("isSaving", false);
                          // TODO: show error
                        }.bind(this));
                    } // end if
                    else { // show error alert
                        $('#val-error-company-alert-${id}').addClass("in");
                        $('#val-error-company-alert-${id}').removeClass("hide");
                    }
                },
                goToURL: function(company) {
                    // if this is a valid company, navigate to the companie's page
                    if(company != undefined && company.url != undefined) {
                       // capture the metrics on this external link click
                       var captureData = {};
                       captureData.url = document.URL;
                       captureData.link = company.url;
                       Metrics.capture("ExternalLinkClick", captureData);

                       window.open(company.url,'_blank');
                    }
                },
                discardChanges: function() {
                    // reset the activeEditCompany to its initial state before editing
                    var preEditCompany = this.get('preActiveEditCompany');
                    this.get('activeEditCompany').set('name',preEditCompany.name);
                    this.get('activeEditCompany').set('url',preEditCompany.url);
                    this.get('activeEditCompany').set('imageURL',preEditCompany.imageURL);

                    // clean all the models
                    this.send('cleanModels');
                    this.send('closeChangeAlert');
                    // remove any old validation errors
                    this.clearValidationErrors();
                    this.send('closeValErrorAlert');
                },
                cleanModels: function() {
                    // clean the company currently being edited if any
                    this.get('activeEditCompany').set('isDirty',false);

                    // iterate over the companies, setting them to be not dirty
                    var idx=1;
                    for(idx; idx<10; idx++) {
                        var currentItem = this.get('company'+idx);
                        if(currentItem != undefined && currentItem.name != null) {
                            currentItem.set('isDirty',false);
                        }
                    }
                },
                closeSavedAlert: function() {
                    $("#saved-company-alert-${id}").addClass('hide');
                    $("#saved-company-alert-${id}").removeClass('in');
                },
                closeChangeAlert: function() {
                    $('#changed-company-alert-${id}').addClass('hide');
                    $('#changed-company-alert-${id}').removeClass('in');
                },
                closeValErrorAlert: function() {
                    $('#val-error-company-alert-${id}').addClass('hide');
                    $('#val-error-company-alert-${id}').removeClass('in');
                }
		  },
		  clearValidationErrors: function() {
		  	// remove the error classes 
		  	$('.company-name-${id}').removeClass("has-error");
		  	$('.company-url-${id}').removeClass("has-error");
		  	// remove the error alert
		  	$('#val-error-company-alert-${id}').addClass('hide');
	        $('#val-error-company-alert-${id}').removeClass('in');
		  },
		  isValidForm: function() {
		  	var retVal = true;
		    var company = this.get('activeEditCompany');
		    if (company.name === undefined || company.name.trim() === "") {
		    	// add "has-error" class to the company name container
		    	$('.company-name-${id}').addClass("has-error");
		    	retVal = false;
		    }
		    if (company.url === undefined || company.url.trim() === "") {
		    	// add "has-error" class to the company url container
		    	$('.company-url-${id}').addClass("has-error");
		    	retVal = false;
		    }
		    // TODO: Image validation!!
		    return retVal;
		  }
		});

		// force the root path to utilize the "companies" template 
		SuggestedCompanyApp.Router.map(function() {
		  this.route("companies", { path: "/" });
		});

		// for the initial index view
		SuggestedCompanyApp.CompaniesRoute = Ember.Route.extend({
		  setupController: function(controller) {
		    Ember.Instrumentation.subscribe("SuggestedCompanyApp.setCompanyData", {
		      before: function (name, timestamp, payload) {
		      	controller.send('setCompanyData', payload);
		      },
		      after: function () { }
		    });
		  }
		});

		/*
		 * Helper function that will build a clean company model 
		 */
		function buildBaseCompany(item) {
			var retVal = {};
			retVal.imageURL = item.imageURL;
	  		retVal.name = item.name;
	  		retVal.position = item.position;
	  		retVal.url = item.url;
			return retVal;
		}
	</script>
	<script>
		$(document).ready(function() {
			var data = {};
			data.requestType_${id} = 'loadCompanyData';
			
			// post to portlet to retreive the companies 
			var jqxhr = $.post("${suggestedCompaniesAjaxHandler}", data)
			    .done(function(response) { 
			    	// send the suggested companies data list to the Ember controller to be displayed and managed
					Ember.Instrumentation.instrument('SuggestedCompanyApp.setCompanyData', response);
			    })
			    .fail(function(error) {
			        var eMsg = "SuggestedCompany - Error loading the suggested companies: " + error.message;
			        Ember.Logger.error(eMsg);
                    var logData = {};
                    logData.message = eMsg;
                    Log.error(logData);
			    })
			    .always(function() {});
			// enable tooltips for the companies
			$('.company').tooltip();
		});	
	</script>
</c:if>
