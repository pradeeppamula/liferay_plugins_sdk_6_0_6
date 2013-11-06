<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='featuredContentAjaxHandler' id='featuredContentAjaxHandler' />
<%-- Only show portlet if user is signed in --%>
<c:if test="${isSignedIn}">

	<style type="text/css">
		#homepage-featured-content-container-${id} {
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
			height: 350px;
			background-color: #000;	
			position: relative;
		}	
		.layout-1-hero-${id} {
			height: 250px;
			position: relative;
			font-size: 1.1em;
			cursor:pointer;
		}
		.layout-1-hero-${id} > img,.layout-1-featured-1-${id} > img,.layout-1-featured-2-${id} > img {
			width: 100%;
			height: 100%;
		}
		
		.title-container-bg-${id} {
			opacity: 0.6;
  			filter: alpha(opacity=60);
  			background-color: #000000;
  			position: absolute;
			bottom: 0;
			z-index: 1;
			height: 20%;
			width: 100%;
		}
		.featured-content-title-${id} {
			color: #fff;
			text-align: left;
			padding: 0px 5px;
			position: absolute;
			bottom: 0;
			z-index: 2;
            left: 0;
			height: 20%;
			font-size: inherit;
		}
		.layout-1-featured-1-${id},.layout-1-featured-2-${id} {
			height: 175px;
			position: relative;
			font-size: 0.9em;
			cursor: pointer;
		}
		#homepage-featured-content-container-${id}:hover #edit-featured-content-${id} {
			display: block;
		}
		#edit-featured-content-${id} {
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
		.content-edit-nav-section {
			padding: 20px;
			background-color: #efefef;
		}
		.layout-1-hero-item-list {
			height: 100px;
			position: absolute;
			bottom: 0;
			left: 0;
		}
		.layout-1-hero-more-links {
			height: 35px;
			position: absolute;
			bottom: 0;
			left: 0;
			background-color: #333;
			z-index: 1;
			line-height: 35px;
			text-align: center;
		}
		.layout-1-hero-item-1 > img,.layout-1-hero-item-2 > img,.layout-1-hero-item-3 > img,.layout-1-hero-item-4 > img {
			height: 100%;
			width: 100%;
		}
		.layout-1-hero-item-1,.layout-1-hero-item-2,.layout-1-hero-item-3,.layout-1-hero-item-4 {
			/* change back when we add more influncer -> height: 65px; */
			height: 100px;
		}
		.layout-1-hero-item-1:hover,.layout-1-hero-item-2:hover,.layout-1-hero-item-3:hover,.layout-1-hero-item-4:hover {
			border: solid 1px #00ccff;
			cursor: pointer;
		}

		.header-container {
            position: absolute;
            top: 0;
            right: 0;
            background-color: #000;
            padding: 10px;
            text-align: center;
            color: #fff;
        }

		<c:if test="${canInlineEdit}">	
		/* INLINE EDIT STYLING */
		.highlight {
			border: solid 2px #00ccff;
		}
		.layout-1-hero-item-1-edit:hover,.layout-1-hero-item-2-edit:hover,.layout-1-hero-item-3-edit:hover,.layout-1-hero-item-4-edit:hover,.layout-1-featured-1-edit-${id}:hover,.layout-1-featured-2-edit-${id}:hover {
			border: solid 2px #00ccff;
		}
		.layout-1-hero-edit-${id} {
			height: 225px;
			position: relative;
			font-size: 1.0em;
		}
		.layout-1-featured-1-edit-${id},.layout-1-featured-2-edit-${id} {
			height: 150px;
			position: relative;
			font-size: 0.8em;
		}
		.layout-1-hero-edit-${id} > img,.layout-1-featured-1-edit-${id} > img,.layout-1-featured-2-edit-${id} > img {
			width: 100%;
			height: 100%;
		}
		.layout-1-hero-item-1-edit > img,.layout-1-hero-item-2-edit > img,.layout-1-hero-item-3-edit > img,.layout-1-hero-item-4-edit > img {
			height: 100%;
			width: 100%;
		}
		.layout-1-hero-item-1-edit,.layout-1-hero-item-2-edit,.layout-1-hero-item-3-edit,.layout-1-hero-item-4-edit {
			/*  Phase 2 add this back in height: 50px;  */
			height: 75px;
		}
		.edit-item-layout-container {
			position: relative;
			height: 300px;
		}
		.edit-layout-1-container {
			position: absolute;
			bottom: 0;
			height: 300px;
		}
		#layouts-tab-content-${id} { 
			position: relative;
			height: 400px;
		}
		
		.edit-item-form-container {
			position: relative;
			margin-top: 15px;
		}
		.layout-1-hero-item-list-edit {
			height: 75px;
			position: absolute;
			bottom: 0;
			left: 0;
		}
		.layout-1-hero-more-links-edit {
			height: 25px;
			position: absolute;
			bottom: 0;
			left: 0;
			background-color: #333;
			z-index: 1;
			line-height: 25px;
			text-align: center;
		}
		/* END INLINE EDIT STYLING */
		</c:if>
	</style>

	<div id="homepage-featured-content-container-${id}">
		<script type="text/x-handlebars" data-template-name="featured">
		<!-- Show the inline edit button only if the user has those privileges -->
		<c:if test="${canInlineEdit}">	
			<span id="edit-featured-content-${id}" class="label label-default"><a class="inline-edit" {{action 'editFeaturedContent'}}><i class="icon-edit-sign"></i>&nbsp;Edit</a></span>
		</c:if>	
		{{#if isLayoutType1}}
			<div class="layout-1-hero-${id} col-md-8 col-sm-8" {{action 'goToContent' heroItem}}>
				<img {{bindAttr src="heroItem.imageURL"}} />
				{{#if heroItem.header}}
				    <div class="header-container col-md-5 col-sm-5">{{{heroItem.header}}}</div>
				{{/if}}
				<div class="title-container-bg-${id}"></div>
				<span class="featured-content-title-${id}">{{{heroItem.shortTitle}}}</span>
			</div>
			<div class="col-md-4 col-sm-4">
				<div class="layout-1-featured-1-${id}"  {{action 'goToContent' featuredPosition1}}>
					<img {{bindAttr src="featuredPosition1.imageURL"}} />
					{{#if featuredPosition1.header}}
                        <div class="header-container col-md-5 col-sm-5">{{{featuredPosition1.header}}}</div>
                    {{/if}}
					<div class="title-container-bg-${id}"></div>
					<span class="featured-content-title-${id}">{{{featuredPosition1.shortTitle}}}</span>
				</div>
				<div class="layout-1-featured-2-${id}"  {{action 'goToContent' featuredPosition2}}>
					<img {{bindAttr src="featuredPosition2.imageURL"}} />
					{{#if featuredPosition2.header}}
                        <div class="header-container col-md-5 col-sm-5">{{{featuredPosition2.header}}}</div>
                    {{/if}}
					<div class="title-container-bg-${id}"></div>
					<span class="featured-content-title-${id}">{{{featuredPosition2.shortTitle}}}</span>
				</div>
			</div>
			<div class="layout-1-hero-item-list col-md-8 col-sm-8">
				<div {{action "selectHero" heroItem1}} class="layout-1-hero-item-1 col-md-3 col-sm-3" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Hero item 1">
					<img {{bindAttr src="heroItem1.imageURL"}} />
				</div>
				<div {{action "selectHero" heroItem2}} class="layout-1-hero-item-2 col-md-3 col-sm-3">
					<img {{bindAttr src="heroItem2.imageURL"}} />
				</div>
				<div {{action "selectHero" heroItem3}} class="layout-1-hero-item-3 col-md-3 col-sm-3">
					<img {{bindAttr src="heroItem3.imageURL"}} />
				</div>
				<div {{action "selectHero" heroItem4}} class="layout-1-hero-item-4 col-md-3 col-sm-3">
					<img {{bindAttr src="heroItem4.imageURL"}} />
				</div>
			</div>
			<!-- NOTE: Phase 2 add this back in
			<div class="layout-1-hero-more-links col-md-8 col-sm-8">
				<a hrer="#">More Influencer Content <i class="icon-double-angle-right"></i> </a>
			</div>-->
		{{/if}}


		<!-- INLINE EDIT MODAL -->
		<div class="modal fade" id="featured-content-edit-${id}" tabindex="-1" role="dialog" aria-labelledby="featuredContentPortalAdmin" aria-hidden="true">
			<div class="modal-dialog">
			  <div class="modal-content">
			    <div class="modal-header">
			      <button type="button" class="close" {{action closeFeaturedContent}} aria-hidden="true">&times;</button>
			      <h4 class="modal-title">Featured Content Administration</h4>
			    </div>
			    <div class="content-edit-nav-section">
			    	<ul class="nav nav-pills nav-justified">
					  <li class="active"><a data-toggle="pill" href="#layouts-section-${id}">Layouts</a></li>
					  <li><a data-toggle="pill" href="#items-section-${id}">Content Items</a></li>
					</ul>
				</div>
			    <div class="modal-body">
				    <div class="tab-content">
				    	<!-- LAYOUTS TAB PANE -->
					  <div class="tab-pane active" id="layouts-section-${id}">
					  	<div class="container">
							<div id="layouts-tab-content-${id}" class="tab-content">
								<div class="col-md-12 col-sm-12">
									<h5>Active Layout <span class="label label-default">{{#if isLayoutType1 }} Layout 1 {{else}} No Layout Selected {{/if}}</span></h5>
						  			<div class="btn-group">
								        <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">Select Layout <span class="caret"></span></button>
								        <ul class="dropdown-menu">
							            	<li {{bindAttr class="isLayoutType1:active"}}><a href="#layout-1-${id}" data-toggle="tab">Layout 1</a></li>
							          	</ul>
								    </div>
								</div> <!-- /.col-md-12 .col-sm-12-->
							    <div {{bindAttr class=":tab-pane :fade :in isLayoutType1:active"}} id="layout-1-${id}">
							      	<div class="edit-layout-1-container">
									  	<div class="layout-1-hero-edit-${id} col-md-8 col-sm-8">
											<img src="//placehold.it/500x500&text=Hero+Section" />
											<div class="title-container-bg-${id}"></div>
											<span class="featured-content-title-${id}">Title of content</span>
										</div>
										<div class="col-md-4 col-sm-4">
											<div class="layout-1-featured-1-edit-${id}">
												<img src="//placehold.it/500x500&text=Featured+Section+1" />
												<div class="title-container-bg-${id}"></div>
												<span class="featured-content-title-${id}">Title of content</span>
											</div>
											<div class="layout-1-featured-2-edit-${id}">
												<img src="//placehold.it/500x500&text=Featured+Section+2" />
												<div class="title-container-bg-${id}"></div>
												<span class="featured-content-title-${id}">Title of content</span>
											</div>
										</div>
										<div class="layout-1-hero-item-list-edit col-md-8 col-sm-8">
											<div class="layout-1-hero-item-1-edit col-md-3 col-sm-3">
												<img src="//placehold.it/500x500&text=Hero+Item+1" />
											</div>
											<div class="layout-1-hero-item-2-edit col-md-3 col-sm-3">
												<img src="//placehold.it/500x500&text=Hero+Item+2" />
											</div>
											<div class="layout-1-hero-item-3-edit col-md-3 col-sm-3">
												<img src="//placehold.it/500x500&text=Hero+Item+3" />
											</div>
											<div class="layout-1-hero-item-4-edit col-md-3 col-sm-3">
												<img src="//placehold.it/500x500&text=Hero+Item+4" />
											</div>
										</div>
										<!-- NOTE: Phase 2 add this back in
										<div class="layout-1-hero-more-links-edit col-md-8 col-sm-8">
											<a href="#">More Influencer Content <i class="icon-double-angle-right"></i> </a>
										</div>
										-->
									</div> <!-- /.edit-layout-1-container -->
							    </div> <!-- /#layout1 -->
							</div> <!-- /#layouts-tab-content -->
					  	</div> <!-- /.container -->
					  </div> <!-- /#layouts-section-${id} -->

					  <!-- ITEMS TAB PANE -->
					  <div class="tab-pane" id="items-section-${id}">
					  	<h5>Click on the item that you would like to change.</h5>
					  	<div class="edit-item-layout-container">
						  	<div class="layout-1-hero-edit-${id} col-md-8 col-sm-8">
								<img {{bindAttr src="heroItem.imageURL"}} />
								{{#if heroItem.header}}
                                    <div class="header-container col-md-5 col-sm-5">{{{heroItem.header}}}</div>
                                {{/if}}
								<div class="title-container-bg-${id}"></div>
								<span class="featured-content-title-${id}">{{{heroItem.shortTitle}}}</span>
							</div>
							<div class="col-md-4 col-sm-4">
								<div id="layout-1-featured-1-edit-${id}" {{action "selectItemForEdit" featuredPosition1}} class="layout-1-featured-1-edit-${id}">
									<img {{bindAttr src="featuredPosition1.imageURL"}} />
									{{#if featuredPosition1.header}}
                                        <div class="header-container col-md-5 col-sm-5">{{{featuredPosition1.header}}}</div>
                                    {{/if}}
									<div class="title-container-bg-${id}"></div>
									<span class="featured-content-title-${id}">{{{featuredPosition1.shortTitle}}}</span>
								</div>
								<div id="layout-1-featured-2-edit-${id}" {{action "selectItemForEdit" featuredPosition2}} class="layout-1-featured-2-edit-${id}">
									<img {{bindAttr src="featuredPosition2.imageURL"}} />
									{{#if featuredPosition2.header}}
                                        <div class="header-container col-md-5 col-sm-5">{{{featuredPosition2.header}}}</div>
                                    {{/if}}
									<div class="title-container-bg-${id}"></div>
									<span class="featured-content-title-${id}">{{{featuredPosition2.shortTitle}}}</span>
								</div>
							</div>
							<div class="layout-1-hero-item-list-edit col-md-8 col-sm-8">
								<div id="layout-1-hero-item-1-edit-${id}" {{action "selectItemForEdit" heroItem1}} class="layout-1-hero-item-1-edit col-md-3 col-sm-3">
									<img {{bindAttr src="heroItem1.imageURL"}} />
								</div>
								<div id="layout-1-hero-item-2-edit-${id}" {{action "selectItemForEdit" heroItem2}} class="layout-1-hero-item-2-edit col-md-3 col-sm-3">
									<img {{bindAttr src="heroItem2.imageURL"}} />
								</div>
								<div id="layout-1-hero-item-3-edit-${id}" {{action "selectItemForEdit" heroItem3}} class="layout-1-hero-item-3-edit col-md-3 col-sm-3">
									<img {{bindAttr src="heroItem3.imageURL"}} />
								</div>
								<div id="layout-1-hero-item-4-edit-${id}" {{action "selectItemForEdit" heroItem4}} class="layout-1-hero-item-4-edit col-md-3 col-sm-3">
									<img {{bindAttr src="heroItem4.imageURL"}} />
								</div>
							</div>
							<!-- NOTE: Removing more influential until Phase 2 <div class="layout-1-hero-more-links-edit col-md-8 col-sm-8"><a href="#">More Influencer Content <i class="icon-double-angle-right"></i> </a></div>-->
						</div><!--/.edit-item-layout-container -->

						<div class="edit-item-form-container">
							<div id="changed-item-alert-${id}" class="alert alert-block alert-warning fade hide">
						        <button type="button" class="close" aria-hidden="true" {{ action 'closeChangeAlert' }}>x</button>
						        <h4><i class="icon-warning-sign icon-3x icon-fixed-width"></i>Whoa!</h4>
						        <p>You will need to click the <strong>"Save Changes"</strong> button in order for your changes to 
						        	be saved.</p>
						        <a class="btn btn-default" href="#" {{ action 'discardChanges'}}>Discard Changes</a>
						    </div> <!-- /#changed-item-alert-${id} -->
						    <div id="saved-item-alert-${id}" class="alert alert-block alert-success fade hide">
						        <button type="button" class="close" aria-hidden="true" {{ action 'closeSavedAlert' }}>x</button>
						        <h4><i class="icon-check-sign icon-3x icon-fixed-width"></i>Changes Saved!</h4>
						    </div><!-- /#saved-item-alert-${id} -->
						    <div id="val-error-item-alert-${id}" class="alert alert-block alert-danger fade hide">
							        <button type="button" class="close" aria-hidden="true" {{ action 'closeValErrorAlert' }}>x</button>
							        <h4><i class="icon-exclamation-sign icon-3x icon-fixed-width"></i>Please enter all required information.</h4>
							</div> <!-- /#val-error-item-alert-${id} -->
							<form role="form">
                                <div class="form-group item-content-type-${id}">
                                 <label class="control-label" for="item-content-type-${id}">Content Type
                                    {{#if activeEditItem.type}}
                                         <span class="label label-info">{{activeEditItem.type}}</span>
                                    {{/if}}
                                 </label>
                                 {{view Ember.Select
                                        contentBinding="contentTypes"
                                        valueBinding="activeEditItem.contentType"
                                        classNames="form-control"}}
                               </div>
							  <div class="form-group item-header-${id}">
                                <label class="control-label" for="item-header-${id}">Header</label>
                                {{view Ember.TextField valueBinding='activeEditItem.header' classNames="form-control" placeholder="header"}}
                                <p class="help-block">You can use html for the header.</p>
                              </div>
							  <div class="form-group item-title-${id}">
							    <label class="control-label" for="item-title-${id}">Title</label>
							    {{view Ember.TextField valueBinding='activeEditItem.title' classNames="form-control" placeholder="title"}}
							  </div>
			                  <div class="form-group item-cid-${id}">
                                  <label class="control-label" for="item-cid-${id}">Content Id
                                    {{#if activeEditItem.idType}}
                                         <span class="label label-default"> {{activeEditItem.idType}}</span>
                                    {{/if}}
                                  </label>
                                  {{view Ember.TextField valueBinding='activeEditItem.cid' classNames="form-control" placeholder="doi or sku"}}
                              </div>
							  <div class="form-group item-imageURL-${id}">
							    <label class="control-label" for="item-imageURL-${id}">Image URL</label>
							    {{view Ember.TextField valueBinding='activeEditItem.imageURL' classNames="form-control" placeholder="url"}}
							    <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
							    <!-- NOTE: Phase 2 add this back in
							    <p class="help-block">Or, you can also simply upload an image.</p>
							    -->
							  </div>
							  <!-- NOTE: Phase 2 add this back in
							  <div class="control-label" class="form-group">
							    <label for="item-file-${id}">File input</label>
							    <input type="file" id="item-file-${id}">
							  </div>
							  -->
							</form>
						</div> <!-- .edit-item-form-container -->
					  </div> <!-- /#items-section-${id} -->
					</div> <!-- /.tab-content -->
				</div> <!-- /.modal-body -->
			    <div class="modal-footer">
			      <button type="button" class="btn btn-default" {{action 'closeFeaturedContent'}}>Close</button>
			      <button type="button" {{bindAttr class=":btn :btn-primary activeEditItem.isDirty::disabled isSaving:disabled"}} {{ action 'saveContent' }}>
			      		{{#if isSaving}}
						    Saving...
						{{else}}
						    Save changes
						{{/if}}
			      </button>
			    </div>
			  </div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<!-- /INLINE EDIT MODAL -->
		</script>
	</div>
	<script>
		// initialize the featured content Ember App
		FeaturedContentApp = Ember.Application.create({
			 rootElement: '#homepage-featured-content-container-${id}'
		});

		// force the root path to utilize the "featured" template 
		FeaturedContentApp.Router.map(function() {
		  this.route("featured", { path: "/" });
		});

		// define the featured content model
		FeaturedContentApp.Content = Ember.Object.extend({
		    cid: null,
		    header: null,
            contentType: "select one",
		    isDirty: false,
		    baseContentURL: "/portal/web/myhome/content?type=",
		  	position: null,
		  	imageURL: "//placehold.it/500x500&text=No+Image",
		  	title: null,
		  	type: null, 
		  	name: null,
		  	shortTitle: function() {
    			var title = this.get('title');
    			var type = this.get('type');
    			var maxLength = (type == "hero") ? 110 : 58;
			    return (title.length > maxLength) ? title.substring(0,maxLength) + '...' : title;
			}.property('title'),
			contentURL : function() {
			    return this.get('baseContentURL') + this.get('contentType') + "&cid=" + this.get('cid');
			}.property('baseContentURL', 'cid', 'contentType'),
			idType: function() {
			    var retVal = null;
			    var type = this.get('contentType');
			    if(type == 'article') {
			        retVal = 'doi';
			    } else if (type == 'webinar') {
			        retVal = 'sku';
			    }
			    return retVal;
			}.property('contentType')
		});

		FeaturedContentApp.Content.reopen({
		  titleChanged: Ember.observer(function() {
		    this.set('isDirty', true);
		  }, 'title','imageURL', 'contentType', 'header', 'cid')
		});

		// create Ember.ArrayController for managing the Featured content models/views
		FeaturedContentApp.FeaturedController = Ember.ArrayController.extend({
		  contentTypes: ['select one', 'article', 'webinar'],
		  content: [],
		  isLayoutType1: false,
		  isLayoutType2: false,
		  isLoadingPosition1: true,
		  isLoadingPosition2: true,
		  isSaving: false,
		  heroItem: null,
		  featuredPosition1: null,
		  featuredPosition2: null,
		  heroItem1: null, 
		  heroItem2: null, 
		  heroItem3: null, 
		  heroItem4: null, 
		  activeEditItem: null,
		  preActiveEditItem: null,
		  originalData: null,
		  currentHeroPosition: 1,
		  autoScrollIntervalId: 0,
		  clearValidationErrors: function() {
		  	// remove the error classes 
		  	$('.item-title-${id}').removeClass("has-error");
		    $('.item-content-type-${id}').removeClass("has-error");
		    $('.item-cid-${id}').removeClass("has-error");
		  	// remove the error alert
		  	$('#val-error-item-alert-${id}').addClass('hide');
	        $('#val-error-item-alert-${id}').removeClass('in');
		  },
		  isValidForm: function() {
		  	var retVal = true;
		    var content = this.get('activeEditItem');
		    // validate the title
		    if (content.title === undefined || content.title.trim() === "") {
		    	// add "has-error" class to the content item title container
		    	$('.item-title-${id}').addClass("has-error");
		    	retVal = false;
		    }

            // validate the content id
		    if (content.cid === undefined || content.cid.trim() === "") {
                // add "has-error" class to the content item cid container
                $('.item-cid-${id}').addClass("has-error");
                retVal = false;
            }

            // validate the content type
            if (content.contentType === undefined || content.contentType.trim() === ""
                || content.contentType.trim() == "select one") {
                // add "has-error" class to the content item contentType container
                $('.item-content-type-${id}').addClass("has-error");
                retVal = false;
            }

		    return retVal;
		  },
		  actions: {
		      setAutoScrollIntervalId: function(payload) {
		         this.set('autoScrollIntervalId', payload);
		      },
		      autoScrollHero: function(payload) {
		          // get the position of the current hero item
		          var position = this.get('currentHeroPosition');
		          // now iterate the position and grab the next item based on the layout type
		          position++;
		          if(this.get('isLayoutType1')) {
		             position = (position == 5) ? 1 : position;
		          } else if(this.get('isLayoutType2')) {

		          }
		          // get the item based on the position
                  var nextItem = this.get('heroItem'+position);
                  // now set it as the hero
                  this.set('heroItem', nextItem);
                  // set the new position back on the controller
                  this.set('currentHeroPosition',position);
		      },
		      goToContent: function(item) {
		          // navigate to the item's page
		          if(item != undefined) {
		            window.location = item.get('contentURL');
		          }
		      },
              selectItemForEdit: function(item) {
                var currentEditItem = this.get('activeEditItem');
                if(currentEditItem != undefined && currentEditItem.isDirty == true) {
                    $('#changed-item-alert-${id}').addClass("in");
                    $('#changed-item-alert-${id}').removeClass("hide");
                } else {
                    // remove all highlighted classes on any of the item containers
                    $( "div[id$='edit-${id}']" ).removeClass('highlight');

                    // then add the highlight class to the current item container depending on the item type
                    if(item.type == "hero") {
                        $('#layout-1-hero-item-'+item.position+'-edit-${id}').addClass('highlight');
                    } else {
                        $('#layout-1-featured-'+item.position+'-edit-${id}').addClass('highlight');
                    }

                    // remove any old validation errors
                    this.clearValidationErrors();
                    // set the new item for editing
                    this.set('activeEditItem', item);

                    // set the "pre" active edit item just in case the user resets the form
                    var preEditItem = {};
                    preEditItem.title = item.title;
                    preEditItem.imageURL = item.imageURL;
                    preEditItem.cid = item.cid;
                    preEditItem.header = item.header;
                    preEditItem.contentType = item.contentType;
                    this.set('preActiveEditItem', preEditItem);
                }
              },
              selectHero: function(item) {
                this.set('heroItem', item);
                // reset the hero position based on the item (so auto scroll can iterate to the next item)
                this.set('currentHeroPosition', item.position);
              },
              editFeaturedContent: function(){
                $("#featured-content-edit-${id}").modal("show");
                // get the interval id
                var intervalId = this.get('autoScrollIntervalId');
                // clear the auto scrolling
                window.clearInterval(intervalId);
              },
              closeFeaturedContent: function() {
                // check to see if there are changes, if so, show this alert
                var currentEditItem = this.get('activeEditItem');
                if(currentEditItem != undefined && currentEditItem.isDirty == true) {
                    $('#changed-item-alert-${id}').addClass("in");
                    $('#changed-item-alert-${id}').removeClass("hide");
                } else {
                    $("#featured-content-edit-${id}").modal("hide");
                }
              },
              setFeaturedContent : function(data) {
                var _self = this;
                _self.set('originalData', data);
                if(data.layoutType == 1) {
                    _self.set('isLayoutType1', true);
                } else if(data.layoutType == 2) {
                    _self.set('isLayoutType2', true);
                }
                data.contentItems.forEach(function(item) {
                    var contentItem = FeaturedContentApp.Content.create({
                        position: item.position,
                        imageURL: item.imageURL,
                        title: item.title,
                        contentType: item.contentType,
                        cid: item.cid,
                        header: item.header,
                        type: item.type // [hero|featured]
                    });
                    if(contentItem.type == "hero") {
                        switch(contentItem.position) {
                            case 1:
                                    contentItem.name = 'heroItem1';
                                    _self.set(contentItem.name, contentItem);
                                    _self.set('heroItem', contentItem);
                                break;
                            case 2:
                                contentItem.name = 'heroItem2';
                                _self.set(contentItem.name, contentItem);
                                break;
                            case 3:
                                contentItem.name = 'heroItem3';
                                _self.set(contentItem.name, contentItem);
                                break;
                            case 4:
                                contentItem.name = 'heroItem4';
                                _self.set(contentItem.name, contentItem);
                                break;
                        }
                    } else { // it is a featured item
                        switch(contentItem.position) {
                            case 1:
                                contentItem.name = 'featuredPosition1';
                                _self.set(contentItem.name, contentItem);
                                _self.set('isLoadingPosition1', false);
                                break;
                            case 2:
                                contentItem.name = 'featuredPosition2';
                                _self.set(contentItem.name, contentItem);
                                _self.set('isLoadingPosition2', false);
                                break;
                        }
                    }
                });
              },
              saveContent: function() {
                    if(this.isValidForm()) {
                        // clear out any validation errors
                        this.clearValidationErrors();
                        // set the save button to be in the loading state
                        this.set('isSaving', true);
                        // build the JSON, save it to the server
                        // grab the original data
                        var data = this.get('originalData');
                        // set the content layout type
                        if(this.get('isLayoutType1') == 1) {
                            data.layoutType = 1;
                        } else if(this.get('isLayoutType2')) {
                            data.layoutType = 2;
                        }
                        // clear the old content items, and set the new ones
                        data.contentItems = [];
                        // remove any unnecessary properties
                        delete(data.exception);
                        var currentItem = this.get('heroItem1');
                        if(currentItem != undefined && currentItem.title != null) {
                            var item = buildBaseItem(currentItem)
                            data.contentItems.pushObject(item);
                        }
                        var currentItem = this.get('heroItem2');
                        if(currentItem != undefined && currentItem.title != null) {
                            var item = buildBaseItem(currentItem)
                            data.contentItems.pushObject(item);
                        }
                        var currentItem = this.get('heroItem3');
                        if(currentItem != undefined && currentItem.title != null) {
                            var item = buildBaseItem(currentItem)
                            data.contentItems.pushObject(item);
                        }
                        var currentItem = this.get('heroItem4');
                        if(currentItem != undefined && currentItem.title != null) {
                            var item = buildBaseItem(currentItem)
                            data.contentItems.pushObject(item);
                        }
                        var currentItem = this.get('featuredPosition1');
                        if(currentItem != undefined && currentItem.title != null) {
                            var item = buildBaseItem(currentItem)
                            data.contentItems.pushObject(item);
                        }
                        var currentItem = this.get('featuredPosition2');
                        if(currentItem != undefined && currentItem.title != null) {
                            var item = buildBaseItem(currentItem)
                            data.contentItems.pushObject(item);
                        }

                        // finally post to the server
                        var postData = {};
                        postData.requestType_${id} = 'saveContent';
                        postData.meta_${id} = JSON.stringify(data);

                        // post to portlet to retreive the featured content
                        $.post("${featuredContentAjaxHandler}", postData).then(function() {
                            // stop the loading state of the button
                            this.set('isSaving', false);

                            // reset the all items to be in the clean state
                            this.send('cleanModels');

                            // show successful save alert
                            $('#saved-item-alert-${id}').addClass("in");
                            $('#saved-item-alert-${id}').removeClass("hide");

                            // set the originalData to the newly updated data
                            this.set('originalData', data);

                            // clear out the alerts after a few seconds
                            setTimeout(function() {
                                $("#saved-item-alert-${id}").addClass('hide');
                                $("#saved-item-alert-${id}").removeClass('in');
                                $('#changed-item-alert-${id}').addClass('hide');
                                $('#changed-item-alert-${id}').removeClass('in');
                            }, 3000);
                        }.bind(this), function() {
                          this.set("isSaving", false);
                          // TODO show error
                        }.bind(this));
                    }// end if
                    else { // show error alert
                        $('#val-error-item-alert-${id}').addClass("in");
                        $('#val-error-item-alert-${id}').removeClass("hide");
                    }
                },
                cleanModels: function() {
                    // clean the item currently being edited if any
                    this.get('activeEditItem').set('isDirty',false);
                    this.get('heroItem1').set('isDirty',false);
                    this.get('heroItem2').set('isDirty',false);
                    this.get('heroItem3').set('isDirty',false);
                    this.get('heroItem4').set('isDirty',false);
                    this.get('featuredPosition1').set('isDirty',false);
                    this.get('featuredPosition2').set('isDirty',false);
                },
                closeSavedAlert: function() {
                    $("#saved-item-alert-${id}").addClass('hide');
                    $("#saved-item-alert-${id}").removeClass('in');
                },
                closeChangeAlert: function() {
                    $('#changed-item-alert-${id}').addClass('hide');
                    $('#changed-item-alert-${id}').removeClass('in');
                },
                closeValErrorAlert: function() {
                    $('#val-error-item-alert-${id}').addClass('hide');
                    $('#val-error-item-alert-${id}').removeClass('in');
                },
                discardChanges: function() {
                    // first reset the current changes on the active item
                    var preEditItem = this.get('preActiveEditItem');
                    this.get('activeEditItem').set('title',preEditItem.title);
                    this.get('activeEditItem').set('imageURL',preEditItem.imageURL);
                    this.get('activeEditItem').set('cid',preEditItem.cid);
                    this.get('activeEditItem').set('header',preEditItem.header);
                    this.get('activeEditItem').set('contentType',preEditItem.contentType);
                    this.send('cleanModels');
                    this.send('closeChangeAlert');
                    // remove any old validation errors
                    this.clearValidationErrors();
                    this.send('closeValErrorAlert');
                }
        	}
		});

		// for the initial index view
		FeaturedContentApp.FeaturedRoute = Ember.Route.extend({
		  setupController: function(controller) {
		    Ember.Instrumentation.subscribe("FeaturedContentApp.setFeaturedContent", {
		      before: function (name, timestamp, payload) {
		       controller.send('setFeaturedContent', payload);
		      },
		      after: function () { }
		    });
		     // register the auto scroll listener
		     Ember.Instrumentation.subscribe("FeaturedContentApp.autoScrollHero", {
              before: function (name, timestamp, payload) {
               controller.send('autoScrollHero', payload);
              },
              after: function () { }
            });
            // register the auto scroll listener
             Ember.Instrumentation.subscribe("FeaturedContentApp.setAutoScrollIntervalId", {
              before: function (name, timestamp, payload) {
               controller.send('setAutoScrollIntervalId', payload);
              },
              after: function () { }
            });
		  }
		});

		function buildBaseItem(item) {
			var retVal = {};
			retVal.imageURL = item.imageURL;
	  		retVal.title = item.title;
	  		retVal.position = item.position;
	  		retVal.type = item.type;
	  		retVal.header = item.header;
	  		retVal.cid = item.cid;
	  		retVal.contentType = item.contentType;
			return retVal;
		}
	</script>
	<script>
		$(document).ready(function() {
			var data = {};
			data.requestType_${id} = 'loadContent';
			
			// post to portlet to retreive the featured content
			var jqxhr = $.post("${featuredContentAjaxHandler}", data)
			    .done(function(response) { 
			    	// send the featured content list to the Ember controller to be displayed and managed
					Ember.Instrumentation.instrument('FeaturedContentApp.setFeaturedContent', response);
					// set the auto scroll function to fire every 10 seconds
                    var id = setInterval(function() {
                        Ember.Instrumentation.instrument('FeaturedContentApp.autoScrollHero', '');
                    }, 10000);
                    Ember.Instrumentation.instrument('FeaturedContentApp.setAutoScrollIntervalId', id);
			    })
			    .fail(function(error) { console.log("Error loading the featured content:" + error); })
			    .always(function() {});
		});	
	</script>
</c:if>