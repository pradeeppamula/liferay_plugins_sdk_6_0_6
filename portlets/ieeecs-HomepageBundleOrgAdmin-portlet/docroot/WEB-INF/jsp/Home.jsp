<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='ajaxHandlerBundleOrgAdmin' id='ajaxHandlerBundleOrgAdmin' />
<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>
<%-- Only show portlet if user is signed in and has admin rights --%>
<c:if test="${!isAuthenticated}">
    <style type="text/css">
        #bundle-org-admin-splash-container {
              color: #ffffff;
              display: block;
              position: relative;
              height: 1000px;
        }

        #bundle-org-admin-splash-bg-container {
              background: url(/ieeecs-HomepageBundleOrgAdmin-portlet/images/admin-bg.jpg);
              opacity: 0.25;
              width: 100%;
              height: 100%;
              top: 0;
              left: 0;
              position: absolute;
         }

        #bundle-org-admin-hero-section {
            position: absolute;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            padding-top: 10%;
        }
         #bundle-org-admin-hero-section h1 {
            font-size: 55px;
            letter-spacing: 10px;
            line-height: 70px;
            margin-bottom: 30px;
            font-weight: 800;
            text-transform: uppercase;
        }
    </style>
    <div id="bundle-org-admin-splash-container" class="text-center col-md-12 col-sm-12 col-xs-12">
        <div id="bundle-org-admin-splash-bg-container"></div>
        <div id="bundle-org-admin-hero-section">
            <h1>Organization Administration.</h1>
            <p class="lead">Please login to manage your organizations.</p>
        </div>
    </div>
</c:if>
<c:if test="${isAuthenticated}">
<c:if test="${canInlineEdit}">

	<style type="text/css">
		#homepage-bundle-org-admin-container-${id} {
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
	    .bundle-org-admin-bundle-form-container {
            padding: 20px 25px;
        }
        .bundle-org-admin-form {
            padding: 30px 22%;
            background-color: #ffffff;
        }
        .bundle-org-admin-bundle-header-container {
            color: #ffffff;
        }

        .admin-content-container {
            min-height: 600px;
            background-color: #ffffff;
        }

        .admin-new-org-container { padding: 10px; }
        .admin-content {
            padding: 0 20px;
            min-height:inherit;
            background-color: #c2c6c9;
        }

        .admin-breadcrumbs-container {
            padding-bottom: 20px;
            color: #ffffff;
        }

        .admin-breadcrumbs-container > a {
            color: #fff;
        }

        .admin-breadcrumbs-container > a:hover {
            color: #aaa;
        }

        .admin-loading-container {
            display: block;
            height: 50px;
            line-height: 50px;
            text-align: center;
            color: #ffffff;
            font-weight: bolder;
        }

        .admin-side-nav.nav>li>a {
            padding: 0 !important;
            line-height: 40px;
            border-radius: 0px;
        }
        .admin-side-nav.nav-pills>li.active>a {
            backgroud-color: #333;
        }
        .admin-side-nav.nav>li>a>span {
            padding-left:5px;
        }
        .admin-side-nav.nav-stacked>li+li {
          margin-top: 0px;
        }

        .admin-side-nav-icon {
            text-align: center;
            font-size: 1.7em;
            color: #fff;
        }
        .organization-edit-container {
            margin-top: 10px;
        }
        .org { background-color: #dfa962; }
        .users { background-color: #5bb38b; }
        .bundles { background-color: #787be7; }
        .num-of-users-badge {
            margin-top: 12px;
            margin-right: 2px;
        }

        .add-article-form-container, .bundle-alerts-container, .change-expiration-container {
            padding: 10px 0;
        }

        input .flat-gray {
            background-color: #eee !important;
            border: 0px solid #eee !important;
        }

         /* Small devices (tablets, 992px and down) */
                @media (max-width: 992px) {
                   .organization-header-bar, .user-header-bar {
                   			height: 50px;
                   			padding: 7px 15px;
                   			color: #2a72b2;
                   			font-weight: 500;
                            font-size: 1.3em;
                   		}
                }
                /* Large devices (tablets, desktops, 993px and up) */
                 @media (min-width: 993px) {
                    .organization-header-bar, .user-header-bar {
                        height: 50px;
                        padding: 7px 15px;
                        color: #2a72b2;
                        line-height: 40px;
                        font-weight: 500;
                        font-size: 1.3em;
                    }
                }
	</style>

	<div id="homepage-bundle-org-admin-container-${id}">
	</div> <!-- /#homepage-bundle-org-admin-container-${id} -->

    <script>
        // compile the handlebar templates to be used by Ember
        Ember.TEMPLATES['admin'] = Ember.Handlebars.compile('<div class="bundle-org-admin-bundle-form-container"> <div class="bundle-org-admin-bundle-header-container"> <h3>Bundle Organization Administration</h3> </div> {{outlet}} </div> <!-- /.bundle-org-admin-bundle-form-container --> {{outlet footer}}');
        Ember.TEMPLATES['admin/index'] = Ember.Handlebars.compile('<div class="admin-content-container"> <div class="organization-header-bar"> <form class="col-md-3 col-sm-4 pull-right"> {{view Ember.TextField valueBinding="filter" classNames="form-control" placeholder="Filter"}} </form> <div class="col-md-8 col-sm-8"> <span>Organizations</span> {{#link-to "admin.new" classNames="btn btn-primary"}}<i class="icon-plus icon-fixed-width"></i> Add{{/link-to}} </div> </div> <!-- /.organization-header-bar --> <table class="table table-striped table-hover"> <thead> <tr> <th>Organization</th> <th>Created</th> <th>Actions</th> </tr> </thead> <tbody> {{#each organization in filteredContent}} <tr> <td>{{organization.name}}</td> <td>{{formattedDate organization.createdDate "LL"}}</td> <td>{{#link-to "organization" organization}}Manage{{/link-to}}</td> </tr> {{/each}} </tbody> </table> </div><!-- /.admin-content-container -->');
        Ember.TEMPLATES['admin/new'] = Ember.Handlebars.compile('<div class="admin-breadcrumbs-container"> {{#link-to "admin"}}<i class="icon-chevron-sign-left"></i> Back To Organizations{{/link-to}} </div> <div class="admin-content-container"> <div class="admin-new-org-container"> <c:if test="${hasAddOrgSuccess}"> <div id="add-organization-success-alert-${id}" class="alert alert-block alert-success fade in"> <button type="button" class="close" aria-hidden="true" {{ action "closeAlert" "add-organization-success-alert-${id}"}}>x</button> <p><i class="icon-check-sign icon-2x icon-fixed-width"></i>Organization Added!</p> </div><!-- /#add-organization-success-alert-${id} --> </c:if> <c:if test="${hasAddOrgErrors}"> <div id="add-organization-error-alert-${id}" class="alert alert-block alert-danger fade in"> <button type="button" class="close" aria-hidden="true" {{ action "closeAlert" "add-organization-error-alert-${id}"}}>x</button> <i class="icon-exclamation-sign icon-2x icon-fixed-width"></i> The following error(s) occurred when adding the new organization: <p><c:out value="${addOrganzationErrors}" escapeXml="false"/></p> </div> <!-- /#add-organization-error-alert-${id} --> </c:if> <div class="alert alert-info"> <i class="icon-info-sign icon-2x icon-fixed-width"></i>Please use a <strong>csv</strong> or <strong>txt</strong> file with the specified format(per line): <strong><em>GroupName,UserFirstName,UserLastName,UserEmail</em></strong></a> </div> <form id="new-org-form-${id}" name="new-org-form-${id}" role="form" method="POST" enctype="multipart/form-data" action="${viewAction}"> <input type="hidden" name="requestType_${id}" id="requestType_${id}" value="ADD_ORGANIZATION" /> <div class="form-group orgFile_${id}"> <label class="control-label" for="orgFile_${id}">Select Your File</label> <input type="file" id="orgFile_${id}" name="orgFile_${id}" size="75"> </div> <button {{bindAttr class=":btn :btn-primary isSaving:disabled"}} {{action "addOrganization"}}> {{#if isSaving}} Please Wait... {{else}} Add Organization {{/if}} </button> </form> </div><!-- /.admin-new-org-container --> </div><!-- /.admin-content-container -->');
        Ember.TEMPLATES['articlebundleform'] = Ember.Handlebars.compile('<form role="form"> <div class="form-group article-bundle-size-${id} col-md-6 col-sm-6"> <label class="control-label" for="article-bundle-size-${id}">Bundle Size</label> {{view Ember.TextField valueBinding="controller.newArticleBundleSize" classNames="form-control flat-gray" placeholder="size"}} </div> </form> <div class="pull-right col-md-12 col-sm-12"> <a href="#" {{action "hideArticleBundleForm"}} {{bindAttr class=":btn :btn-default"}}>Cancel</a> <button {{bindAttr class=":btn :btn-primary"}} {{action "addArticleBundle"}}> Add </button> </div>');
        Ember.TEMPLATES['footer'] = Ember.Handlebars.compile('<div class="row footer-links"> <a href="/portal/web/myhome/home">Home</a> <a href="/portal/web/myhome/article-bundle">CSDL</a> <a href="/portal/web/myhome/webinar-bundle">Webinars</a> <a href="http://www.ieee.org/site_terms_conditions.html?WT.mc_id=hpf_terms">Terms</a> <a href="http://www.computer.org/portal/web/guest/privacy">Privacy</a> </div> <div class="row footer-social-links"> <a href="mailto:help@computer.org"><i class="icon-envelope icon-2x"></i></a> <a href="https://twitter.com/computersociety" target="_blank"><i class="icon-twitter icon-2x"></i></a> <a href="https://www.facebook.com/ieeecomputersociety?v=wall" target="_blank"><i class="icon-facebook icon-2x"></i></a> <a href="https://plus.google.com/b/106653716856205029801/#106653716856205029801/posts" target="_blank"><i class="icon-google-plus icon-2x"></i></a> <a href="http://www.linkedin.com/groups?home=&amp;gid=52513&amp;trk=anet_ug_hm" target="_blank"><i class="icon-linkedin icon-2x"></i></a> <a href="http://www.youtube.com/user/ieeeComputerSociety" target="_blank"><i class="icon-youtube icon-2x"></i></a> <a href="http://www.computer.org/portal/web/csdl/dlrss" target="_blank"><i class="icon-rss icon-2x"></i></a> </div> <div class="row footer-copy-section"> <p class="text-muted"> <span class="text-primary copy-section"></span> <i class="icon-keyboard"></i> in LA </p> </div>');
        Ember.TEMPLATES['loading'] = Ember.Handlebars.compile('<div class="admin-loading-container"><i class="icon-spinner icon-spin icon-large"></i> Loading</div>');
        Ember.TEMPLATES['organization/edit'] = Ember.Handlebars.compile('<div class="organization-edit-container"> <div id="saved-organization-alert-${id}" class="alert alert-block alert-success fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeSavedAlert" }}>x</button> <p><i class="icon-check-sign icon-2x icon-fixed-width"></i>Changes Saved!</p> </div><!-- /#saved-user-alert-${id} --> <div id="error-organization-alert-${id}" class="alert alert-block alert-danger fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeErrorAlert" }}>x</button> <p><i class="icon-exclamation-sign icon-2x icon-fixed-width"></i><span id="error-organization-message-${id}"></span></p> </div> <!-- /#error-organization-alert-${id} --> <div id="validation-organization-alert-${id}" class="alert alert-block alert-danger fade hide validation-organization-alert-${id}"> <button type="button" class="close" aria-hidden="true" {{ action "closeValidationErrorAlert" "validation-organization-alert-${id}" }}>x</button> <p><i class="icon-exclamation-sign icon-2x icon-fixed-width"></i><span id="validation-organization-message-${id}"></span></p> </div> <!-- /#validattion-organization-alert-${id} --> <form role="form"> <div class="form-group name-${id} col-md-6 col-sm-6"> <label class="control-label" for="name-${id}">Name</label> {{view Ember.TextField valueBinding="name" classNames="form-control flat-gray" placeholder="name"}} </div> <div class="form-group description-${id} col-md-8 col-sm-8"> <label class="control-label" for="company-${id}">Description</label> {{view Ember.TextArea valueBinding="description" classNames="form-control flat-gray" placeholder="description"}} </div> </form> <div class="pull-right col-md-12 col-sm-12"> {{#link-to "admin.index" classNames="btn btn-default"}}Cancel{{/link-to}} <button {{bindAttr class=":btn :btn-primary isSaving:disabled"}} {{action "saveOrganization"}}> {{#if isSaving}} Please Wait... {{else}} Save Changes {{/if}} </button> </div> </div><!-- /.organization-edit-container -->');
        Ember.TEMPLATES['organization'] = Ember.Handlebars.compile('<div class="admin-breadcrumbs-container"> {{#link-to "admin"}}<i class="icon-chevron-sign-left"></i> Back To Organizations{{/link-to}} </div> <div class="admin-content-container"> <div class="admin-side-nav col-md-3 col-sm-3"> <ul class="admin-side-nav nav nav-pills nav-stacked"> <li id="admin-org-nav-${id}" class="active"> <a href="#" {{action "showOrganization"}}> <div class="col-md-2 col-sm-2 admin-side-nav-icon org"> <i class="icon-building"></i> </div> <span>{{name}}</span> </a> </li> <li id="admin-user-nav-${id}"> <a href="#" {{action "showUsers"}}> <div class="col-md-2 col-sm-2 admin-side-nav-icon users"> <i class="icon-group"></i> </div> <span>Users</span> <!-- NOTE: Add back in on Phase 2 once we get the count working   --> <span class="num-of-users-badge badge pull-right"> {{numberOfUsers}} </span> </a> </li> <li id="admin-bundle-nav-${id}"> <a href="#" {{action "showBundle"}}> <div class="col-md-2 col-sm-2 admin-side-nav-icon bundles"> <i class="icon-archive"></i> </div> <span>Bundles</span> </a> </li> </ul> </div> <div class="admin-content col-md-9 col-sm-9"> {{outlet content}} </div> </div> <!-- /.admin-content-container -->');
        Ember.TEMPLATES['organization/bundles'] = Ember.Handlebars.compile('<ul class="nav nav-tabs"> <li class="active"><a data-toggle="tab" href="#article-section-${id}">CSDL Articles</a></li> <li><a data-toggle="tab" href="#webinar-section-${id}">Webinars</a></li> </ul> <div class="tab-content"> <div class="bundle-alerts-container"> <div id="saved-bundle-alert-${id}" class="alert alert-block alert-success fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeSavedAlert" "#saved-bundle-alert-${id}"}}>x</button> <p><i class="icon-check-sign icon-2x icon-fixed-width"></i>Changes Saved!</p> </div><!-- /#saved-bundle-alert-${id} --> <div id="error-bundle-alert-${id}" class="alert alert-block alert-danger fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeErrorAlert" "#error-bundle-alert-${id}" }}>x</button> <p><i class="icon-exclamation-sign icon-2x icon-fixed-width"></i><span id="error-bundle-message-${id}"></span></p> </div> <!-- /#error-bundle-alert-${id} --> <div id="validation-bundle-alert-${id}" class="alert alert-block alert-danger fade hide validation-bundle-alert-${id}"> <button type="button" class="close" aria-hidden="true" {{ action "closeValidationErrorAlert" "validation-bundle-alert-${id}" }}>x</button> <p><i class="icon-exclamation-sign icon-2x icon-fixed-width"></i><span id="validation-bundle-message-${id}"></span></p> </div> <!-- /#validation-bundle-alert-${id} --> <div id="info-bundle-alert-${id}" class="alert alert-block alert-info fade hide info-bundle-alert-${id}"> <button type="button" class="close" aria-hidden="true" {{ action "closeAlert" "#info-bundle-alert-${id}" }}>x</button> <p><i class="icon-info-sign icon-2x icon-fixed-width"></i>Please click the <strong>Save Changes</strong> button in order for your changes to take affect.</p> </div> <!-- /#info-bundle-alert-${id} --> </div> <div class="tab-pane active" id="article-section-${id}"> <div id="warning-article-alert-${id}" class="alert alert-block alert-warning fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeWarningAlert" "#warning-article-alert-${id}" }}>x</button> <i class="icon-warning-sign icon-2x icon-fixed-width"></i> Are you sure you would like to remove this bundle? <a class="btn btn-default" href="#" {{action "removeBundle" "article"}}>Yes, Remove Bundle</a></p> </div> <!-- /#warning-article-alert-${id} --> <div class="add-article-form-container"> {{#if inAddArticleMode}} {{view BundleOrgAdminApp.AddArticleBundleForm}} {{else}} <a class="btn btn-primary" href="#" {{action "showArticleBundleForm"}}><i class="icon-plus icon-fixed-width"></i>Add Bundle</a> {{/if}} </div> <table class="table table-striped table-hover"> <thead> <tr> <th>Size</th> <th>Created</th> <th>Actions</th> </tr> </thead> <tbody> {{#each csdl_article}} <tr> <td>{{number_of_items}}</td> <td>{{formattedDate created_date "LL"}}</td> <td><a href="#" class="btn btn-danger" {{action "showArticleWarningAlert" this}}>Remove</a></td> </tr> {{/each}} </tbody> </table> </div> <!-- /#article-section-${id} --> <div class="tab-pane" id="webinar-section-${id}"> <div id="warning-webinar-alert-${id}" class="alert alert-block alert-warning fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeWarningAlert" "#warning-webinar-alert-${id}" }}>x</button> <i class="icon-warning-sign icon-2x icon-fixed-width"></i> Are you sure you would like to remove this webinar? <a class="btn btn-default" href="#" {{action "removeBundle" "webinar"}}>Yes, Remove Webinar</a></p> </div> <!-- /#warning-webinar-alert-${id} --> <div> {{#if inAddWebinarMode}} {{view BundleOrgAdminApp.AddWebinarBundleForm}} {{else}} <a class="btn btn-primary" href="#" {{action "showWebinarBundleForm"}}><i class="icon-plus icon-fixed-width"></i>Add Webinar</a> {{/if}} </div> <div class="col-md-10 change-expiration-container"> {{#if inChangeExpirationMode}} <div id="webinar-expiration-date-${id}" class="form-group col-md-5 col-sm-5"> <label class="control-label" for="webinar-expiration-date-${id}">Webinar Expiration</label> <div class="input-group"> {{view Ember.TextField id="webinar-expiration-field-${id}" valueBinding="webinarExpirationDate" classNames="form-control datepicker input-group" placeholder="YYYY-MM-DD"}} <span class="input-group-addon"><i class="icon-calendar"></i></span> </div> </div> <div class="pull-right col-md-12 col-sm-12"> <a href="#" {{action "hideChangeExpirationForm"}} {{bindAttr class=":btn :btn-default"}}>Cancel</a> <button {{bindAttr class=":btn :btn-primary"}} {{action "changeExpirationDate"}}> Done </button> </div> {{else}} <br /> <a class="btn btn-primary" href="#" {{action "showChangeExpirationForm"}}><i class="icon-calendar icon-fixed-width"></i>Change Expiration</a> {{/if}} </div> <table class="table table-striped table-hover"> <thead> <tr> <th>Name</th> <th>Expiration</th> <th>Actions</th> </tr> </thead> <tbody> {{#each web in webinar}} {{#each web.selected_items}} <tr> <td>{{name}}</td> <td>{{formattedDate web.expiration_date "LL"}}</td> <td><a href="#" class="btn btn-danger" {{action "showWebinarWarningAlert" this}}>Remove</a></td> </tr> {{/each}} {{/each}} </tbody> </table> </div> <!-- /#webinar-section-${id} --> <div class="pull-right col-md-12 col-sm-12"> <button {{bindAttr class=":btn :btn-primary isSaving:disabled"}} {{action "saveBundles"}}> {{#if isSaving}} Please Wait... {{else}} Save Changes {{/if}} </button> </div> </div> <!-- /.tab-content -->');
        Ember.TEMPLATES['users'] = Ember.Handlebars.compile('<div class="user-header-bar"> <!-- NOTE: Enable filter for later in Phase 2 <form class="col-md-3 col-sm-4 pull-right"> {{view Ember.TextField valueBinding="filter" classNames="form-control" placeholder="Filter"}} </form> --> </div> <!-- /.user-header-bar --> <div id="warning-user-alert-${id}" class="alert alert-block alert-warning fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeWarningAlert" }}>x</button> <p><i class="icon-warning-sign icon-2x icon-fixed-width"></i> Are you sure you would like to remove <strong><span id="warning-user-name-${id}"></span></strong>? <a class="btn btn-default" href="#" {{ action "removeUser"}}>Yes, Remove User</a></p> </div> <!-- /#changed-user-alert-${id} --> <div id="saved-user-alert-${id}" class="alert alert-block alert-success fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeSavedAlert" }}>x</button> <p><i class="icon-check-sign icon-2x icon-fixed-width"></i>User Removed!</p> </div><!-- /#saved-user-alert-${id} --> <div id="error-user-alert-${id}" class="alert alert-block alert-danger fade hide"> <button type="button" class="close" aria-hidden="true" {{ action "closeErrorAlert" }}>x</button> <p><i class="icon-exclamation-sign icon-2x icon-fixed-width"></i><span id="error-user-message-${id}"></span></p> </div> <!-- /#val-error-user-alert-${id} --> <table class="table table-striped table-hover"> <thead> <tr> <th>Name</th> <th># of Articles</th> <th># of Webinars</th> <th>Actions</th> </tr> </thead> <tbody> {{#each users}} <tr> <td>{{name}}</td> <td>{{numberOfArticles}}</td> <td>{{numberOfWebinars}}</td> <td><button class="btn btn-danger" {{action "removeUserConfirm" this}}>Remove</button></td> </tr> {{/each}} </tbody> </table>');
        Ember.TEMPLATES['webinarbundleform'] = Ember.Handlebars.compile('<form role="form"> <div class="form-group expires-${id} col-md-10 col-sm-10"> <label class="control-label" for="webinar-bundle-name-${id}">Webinar</label> <div class="input-group"> {{view Ember.Select contentBinding="controller.webinars"optionValuePath="content.sku"optionLabelPath="content.name"valueBinding="controller.newWebinarSku"classNames="form-control"}} </div> </div> </form> <div class="pull-right col-md-12 col-sm-12"> <a href="#" {{action "hideWebinarBundleForm"}} {{bindAttr class=":btn :btn-default"}}>Cancel</a> <button {{bindAttr class=":btn :btn-primary"}} {{action "addWebinarBundle"}}> Add </button> </div>');

        // initialize the bundle-org-admin Ember App
        BundleOrgAdminApp = Ember.Application.create({
             rootElement: '#homepage-bundle-org-admin-container-${id}',
             LOG_TRANSITIONS: true
        });

        // define any models for this app
        BundleOrgAdminApp.Organization = Ember.Object.extend();
        BundleOrgAdminApp.User = Ember.Object.extend();

        // register a helper for formatting dates
        Ember.Handlebars.registerBoundHelper('formattedDate', function(date, format) {
          return moment.utc(new Date(date)).format(format);
        });

        // override the find method for the Organization model
        BundleOrgAdminApp.Organization.reopenClass({

          /**
           * This function will hit the server to find all the organizations, or a specific organization by id.
           * @param String id - an organization id
           * @return Object|Array
           */
          find: function(id) {
            if(id !== undefined) {
                // TODO: (Phase 2) load single organization by id
                return  BundleOrgAdminApp.Organization.create({id: id, name: "Test Organization", description: "This is a test."});
            } else {
                var organizations = [];
                var data = {};
                data.requestType_${id} = 'LOAD_ORGANIZATIONS';
                // post to the portlet
                $.post("${ajaxHandlerBundleOrgAdmin}", data)
                 .done(function(response) {
                     response.forEach(function (organization) {
                       var _id = organization._id;
                       organization.id = (_id != undefined) ? _id.$oid : 37;
                       organizations.pushObject(BundleOrgAdminApp.Organization.create(organization));
                     });
                  })
                 .fail(function(error) {
                    var eMsg = "BundleOrgAdmin - Error loading organizations: " + error.message;
                    Ember.Logger.error(eMsg);
                    var logData = {};
                    logData.message = eMsg;
                    Log.error(logData);
                 });
                 return organizations;
             }
          }
        });

        // override the find method for the User model
         BundleOrgAdminApp.User.reopenClass({

          /**
            * This function will hit the server to find all the users of a specific organization.
            * @param String id - an organization id
            * @return Array users
            */
          find: function(id) {
            var users = [];
            // if there was an org id passed in, we can search
            if(id !== undefined) {

                // build the post data object
                var data = {};
                data.requestType_${id} = 'LOAD_USERS';
                data.orgId_${id} = id;

                // post to the portlet
                $.post("${ajaxHandlerBundleOrgAdmin}", data)
                 .done(function(response) {

                     // iterate over the list of returned users build
                     response.forEach(function (user) {
                       // set the number of articles that the user has
                       user.numberOfArticles = user.units.csdl_article.length;
                       // set the number of webinars that the user has
                       user.numberOfWebinars = user.units.webinars.length;
                       // add the user to the list of users
                       users.pushObject(BundleOrgAdminApp.User.create(user));
                     });
                     // send out message to Organization Controller to update users/bundles
                     Ember.Instrumentation.instrument('BundleOrgAdminApp.updateOrganizationSubData', response);
                  })
                 .fail(function(error) {
                    var eMsg = "BundleOrgAdmin - Error loading users: " + error.message;
                    Ember.Logger.error(eMsg);
                    var logData = {};
                    logData.message = eMsg;
                    Log.error(logData);
                 });
                 return users;
             } else {
                return users;
             }
          }
        });

        /**
         * Define the admin new controller for handling the AdminNew route
         */
        BundleOrgAdminApp.AdminNewController = Ember.ArrayController.extend({
            isSaving: false,

            /**
             * This is the required actions object that Ember wants you put
             * your functions that handle actions from Handlebars templates.
             */
            actions: {

                /**
                 * This function will submit the form that has the new admin
                 * information.
                 */
                addOrganization: function() {
                    this.set('isSaving', true);
                    $('#new-org-form-${id}').submit();
                },
                closeAlert: function(elementId) {
                    $('#'+elementId).addClass('hide');
                    $('#'+elementId).removeClass('in');
                }
            }
        });

        /**
         * Define the admin index controller that will handle the AdminIndex
         */
        BundleOrgAdminApp.AdminIndexController = Ember.ArrayController.extend({
            filter: '',
            /**
             * This is the required actions object that Ember wants you put
             * your functions that handle actions from Handlebars templates.
             */
            actions: {
                /**
                 * This function will navigate the user to the create organization page
                 */
                goToAddOrganization: function() {
                    window.location = "/portal/web/guest/admin#/new";
                }
            },

            /**
               * This is the controller computed property that will
               * serve as the "filtered" data.  It contains the necessary
               * filter functions that will the content set on the controller
               * when the user types in the filter input text box.
               * @return Array - the filtered content data
               */
            filteredContent: function() {
                // get the organizations that are on the controller
                var organizations = this.get('content');
                // get the filter text value that the user has typed in
                var filter = this.get('filter').toLowerCase();
                if(filter == '') {
                   return Ember.ArrayProxy.create({ content: Ember.A(organizations) });
                } else {
                    return organizations.filter(function(org, index, enumerable){
                      return org.get('name').toLowerCase().match(filter.toLowerCase()) ||
                            org.get('description').toLowerCase().match(filter.toLowerCase());
                    });
                }
            }.property('filter', 'content.@each').cacheable()
        });


        /**
         * Define the users controller to handle the user route
         */
        BundleOrgAdminApp.UsersController = Ember.ArrayController.extend({
            users: [],
            userToBeRemoved: null,
            filter: '',

            /**
             * This is the required actions object that Ember wants you put
             * your functions that handle actions from Handlebars templates.
             */
            actions: {
               removeUserConfirm: function(user) {
                    this.send('showWarningAlert', user);
               },
               closeSavedAlert: function() {
                    $("#saved-user-alert-${id}").addClass('hide');
                    $("#saved-user-alert-${id}").removeClass('in');
                },
                closeWarningAlert: function() {
                    $('#warning-user-alert-${id}').addClass('hide');
                    $('#warning-user-alert-${id}').removeClass('in');
                },
                closeErrorAlert: function() {
                    $('#error-user-alert-${id}').addClass('hide');
                    $('#error-user-alert-${id}').removeClass('in');
                },
                showWarningAlert: function(user) {
                    $('#warning-user-alert-${id}').addClass("in");
                    $('#warning-user-alert-${id}').removeClass("hide");
                    if(user !== undefined) {
                        $('#warning-user-name-${id}').html(user.name);
                        this.set('userToBeRemoved', user);
                    } else {
                       $('#warning-user-name-${id}').html("that user");
                    }
                },
                showSavedAlert: function() {
                    $('#saved-user-alert-${id}').addClass("in");
                    $('#saved-user-alert-${id}').removeClass("hide");
                },
                showErrorAlert: function(message) {
                    $('#error-user-alert-${id}').addClass("in");
                    $('#error-user-alert-${id}').removeClass("hide");
                    $('#error-user-message-${id}').html(message);
                },

                /**
                 * This function will remove the User from the organization that the
                 * user is associated to, and then will update all the necessary
                 * Ember Apps that are associated with knowing how many users
                 * are in an organization.
                 */
                removeUser: function() {
                    var _self = this;

                    // build the data object to post to the server
                    var data = {};
                    data.requestType_${id} = 'REMOVE_USER';
                    var user = _self.get('userToBeRemoved')
                    data.userId_${id} = user.get('user_id');
                    data.userEmail_${id} = user.get('email');
                    data.userOrgId_${id} = user.get('organization_id').$oid;

                    // close the warning alert
                    _self.send('closeWarningAlert');

                    // post to the portlet
                    $.post("${ajaxHandlerBundleOrgAdmin}", data)
                     .done(function(response) {
                         if(response == 200) {
                            // remove the user from the list of users
                            _self.get('users').removeObject(user);

                            // send out message to Organization Controller to update users/bundles
                            Ember.Instrumentation.instrument('BundleOrgAdminApp.setNumberOfUsers',  _self.get('users').get('length'));

                            // clear out the user that is listed to be removed
                            _self.set('userToBeRemoved', null);
                            _self.send('showSavedAlert');
                            setTimeout(function() {
                                _self.send('closeSavedAlert');
                            }, 3000);
                         } else {
                            // show error
                            _self.send('showErrorAlert', "There was a problem removing this user, please try again or contact help@computer.org");
                         }
                      })
                     .fail(function(error) {
                        _self.send('showErrorAlert', "There was a problem removing this user, please try again or contact help@computer.org");
                        var eMsg = "BundleOrgAdmin - Error loading users: " + error.message;
                        Ember.Logger.error(eMsg);
                        var logData = {};
                        logData.message = eMsg;
                        Log.error(logData);
                     });
                }
            }
        });

        // add article bundle view
        BundleOrgAdminApp.AddArticleBundleForm = Ember.View.extend({
            templateName: 'articlebundleform'
        });

        // add webinar bundle view
        BundleOrgAdminApp.AddWebinarBundleForm = Ember.View.extend({
            templateName: 'webinarbundleform'
        });

        /**
         * Define the organization controller to handle the organization route
         */
        BundleOrgAdminApp.OrganizationController = Ember.ObjectController.extend({
            webinars: ${webinars},
            webinarExpirationDate: null,
            isSaving: false,
            needs: ['users'],
            numberOfUsers: '-',
            bundleToBeRemoved: null,
            newArticleBundleSize: null,
            newWebinarSku: null,
            inAddArticleMode: false,
            inAddWebinarMode: false,
            inChangeExpirationMode: false,

            /**
             * This function will create the default expiration date for webinars, and update
             * the first (and only) webinar with the new expiration date.  It will then
             * remove the old version of that webinar from the controller list, and add back
             * in the same updated webinar.  We needed to do a removal/add instead of an update
             * because the update didn't stick for some reason when the model was in a list.
             */
            setDefaultWebinarExpirationDate: function() {
                  // set the default expiration date on the webinar model
                // grab the webinars off the model
                var web = this.get('model').get('webinar').get('firstObject');
                // create another webinar object
                var webinar = Ember.Object.create(web);
                // set the new expiration date on the webinar
                webinar.set('expiration_date', this.get('webinarExpirationDate'));
                this.get('model').get('webinar').removeAt(0);
                this.get('model').get('webinar').pushObject(webinar);
            },

            /**
             * This function will clear out any validation errors that are currently displayed to the user.
             * It takes in the element id of the specific error alert in question, and removes that element.
             * @param String elementId
             */
             clearValidationErrors: function(elementId) {
                // remove the error classes
                $('.name-${id}').removeClass("has-error");
                $('.description-${id}').removeClass("has-error");
                 $('.article-bundle-size-${id}').removeClass("has-error");

                // remove the all validation alert
                this.send('closeValidationErrorAlert', elementId);
           },

           /**
              * This is the required actions object that Ember wants you put
              * your functions that handle actions from Handlebars templates.
              */
            actions: {

               /**
                * This function will update the expiration data on the webinar that is actively in context.
                */
               changeExpirationDate: function() {
                    // TODO: Validate the date
                    // grab the webinars off the model
                    var web = this.get('model').get('webinar').get('firstObject');
                    // create a new webinar object
                    var webinar = Ember.Object.create(web);
                    // set the new expiration date on the webinar
                    webinar.set('expiration_date', this.get('webinarExpirationDate'));
                    this.get('model').get('webinar').removeAt(0);
                    this.get('model').get('webinar').pushObject(webinar);
                    this.send('hideChangeExpirationForm');
                    // show the note to save the changes in order for changes to take affect
                    this.send('showInfoBundleAlert');
               },

               /**
                * This function will add a specific webinar to a bundle
                * only if they user does not already have the same webinar already
                * in their bundle.  It will make the expiration date of the newly added
                * webinar the same as the other webinars within the bundle it is being added to.
                */
               addWebinarBundle: function() {
                  // first check to see if a expiration date is set on the webinars, if not set a default one year from today
                  // get the webinar expiration date off the model
                    var webinars = this.get('model').get('webinar');
                    var currentExpirationDate = webinars.get('firstObject').expiration_date;

                    // update to the new expiration date if the current one is not null
                    if(currentExpirationDate == undefined) {
                         this.setDefaultWebinarExpirationDate();
                    }

                  // get the sku of the selected webinar
                  var sku = this.get('newWebinarSku');
                  // now find the actual webinar that matches the sku
                  var webinarList = this.get('webinars');


                  var newWebinar = webinarList.findProperty('sku', sku);
                  // grab the webinars off the model
                  var webinars = this.get('model').get('webinar');
                  // get the list of selected items from the FIRST webinar list
                  var selectedItems = webinars.get('firstObject').selected_items;
                  // add the webinar from the list if it is not already there
                  if(selectedItems.findProperty('sku', sku) === undefined) {
                    selectedItems.pushObject(newWebinar);
                    // show the note to save the changes in order for changes to take affect
                    this.send('showInfoBundleAlert');
                  }
               },

               /**
                * This function will add an article to the user's bundle, only if the user
                * has available space in their bundle.
                */
               addArticleBundle: function() {
                  // first clear any old validation messages
                  this.clearValidationErrors('validation-bundle-alert-${id}');

                  // grab the article bundle size
                  var size = this.get('newArticleBundleSize');

                  //  validate the size field
                  var isValidSize = true;

                  try {
                      if (size === undefined || size.trim() === "" || isNaN(size)) {
                         // add "has-error" class to the name container
                         $('.article-bundle-size-${id}').addClass("has-error");
                         isValidSize = false;
                      }
                  } catch(error) {
                      // add "has-error" class to the name container
                       $('.article-bundle-size-${id}').addClass("has-error");
                       isValidSize = false;
                  }

                  // if this size is valid, we can add the bundle
                  if(isValidSize) {
                      // create the new bundle object and add it to the list of article bundles
                      var newBundle = {number_of_items: parseInt(size), created_date: new Date().toISOString()};
                      // get the organization model
                      var org = this.get('model');
                      // add the new article bundle to the list of article bundles
                      org.get('csdl_article').pushObject(newBundle);

                      // show the note to save the changes in order for changes to take affect
                      this.send('showInfoBundleAlert');
                  } else {
                    this.send('showValidationErrorMessage', "validation-bundle-alert-${id}", "validation-bundle-message-${id}", "Please enter a valid bundle size.");
                  }
                },
                hideChangeExpirationForm: function() {
                   this.set('inChangeExpirationMode', false);
                },

                showChangeExpirationForm: function() {
                    this.set('inAddWebinarMode', false);
                    this.set('inChangeExpirationMode', true);
                    // get the webinar expiration date off the model
                    var webinars = this.get('model').get('webinar');
                    var currentExpirationDate = webinars.get('firstObject').expiration_date;

                    // update to the new expiration date if the current one is not null
                    if(currentExpirationDate != undefined) {
                        this.set('webinarExpirationDate', currentExpirationDate);
                    } else {
                        // set the default expiration date on the webinar model
                        this.setDefaultWebinarExpirationDate();
                    }
                },
                hideArticleBundleForm: function() {
                   // clear any old validation messages
                   this.clearValidationErrors('validation-bundle-alert-${id}');
                   this.set('inAddArticleMode', false);
                },
                showArticleBundleForm: function() {
                    this.set('inAddArticleMode', true);
                },
                hideWebinarBundleForm: function() {
                   // clear any old validation messages
                   this.clearValidationErrors('validation-bundle-alert-${id}');
                   this.set('inAddWebinarMode', false);
                },
                showWebinarBundleForm: function() {
                    this.set('inAddWebinarMode', true);
                    this.set('inChangeExpirationMode', false);
                },
                showArticleWarningAlert: function(bundle) {
                    $('#warning-article-alert-${id}').addClass("in");
                    $('#warning-article-alert-${id}').removeClass("hide");
                    if(bundle !== undefined) {
                        this.set('bundleToBeRemoved', bundle);
                    }
                },
                closeWarningAlert: function(elementId) {
                      $(elementId).addClass('hide');
                      $(elementId).removeClass('in');
                },
                showWebinarWarningAlert: function(bundle) {
                    $('#warning-webinar-alert-${id}').addClass("in");
                    $('#warning-webinar-alert-${id}').removeClass("hide");
                    if(bundle !== undefined) {
                        this.set('bundleToBeRemoved', bundle);
                    }
                },

                /**
                 * This helper function will set the number of users, from the payload
                 * that is received from an Ember.Instruments message.
                 * @param String payload - the number of users
                 */
                setNumberOfUsers: function(payload) {
                    if(payload != undefined) {
                        this.set('numberOfUsers', payload);
                    }
                },

                /**
                 * This function will update an organizations's bundle information from the
                 * payload parameter.  This is called whenever an organization's bundle
                 * information changes.
                 * @param Array payload - the users list
                 */
                updateOrganizationSubData: function(payload) {
                    // NOTE: payload should be the Users array
                    if(payload != undefined) {
                        // grab the model off the
                        var org = this.get('model');

                        // update the number of users
                        this.set('numberOfUsers', payload.get('length'));

                        // if bundle info is present, update that on the organization model
                        org.set('csdl_article',payload[0].bundle.csdl_article);
                        org.set('webinar',payload[0].bundle.webinar);

                        // set the updated organization back on the controller
                        this.set('model', org);
                    }
                },
                closeSavedAlert: function(elementId) {
                    if(elementId != undefined) {
                       $(elementId).addClass('hide');
                       $(elementId).removeClass('in');
                    } else {
                        $("#saved-organization-alert-${id}").addClass('hide');
                        $("#saved-organization-alert-${id}").removeClass('in');
                    }
                },
                closeErrorAlert: function(elementId) {
                    if(elementId != undefined) {
                        $(elementId).addClass('hide');
                        $(elementId).removeClass('in');
                    } else {
                        $("#error-organization-alert-${id}").addClass('hide');
                        $("#error-organization-alert-${id}").removeClass('in');
                    }
                },
                closeAlert: function(elementId) {
                    if(elementId != undefined) {
                        $(elementId).addClass('hide');
                        $(elementId).removeClass('in');
                    }
                },
                showSavedAlert: function(elementId) {
                    if(elementId != undefined) {
                        $(elementId).addClass('in');
                        $(elementId).removeClass('hide');
                    } else {
                        $('#saved-organization-alert-${id}').addClass("in");
                        $('#saved-organization-alert-${id}').removeClass("hide");
                    }
                },
                showErrorAlert: function(message, elementId) {
                    if(elementId != undefined) {
                        $(elementId).addClass('in');
                        $(elementId).removeClass('hide');
                        $('#error-bundle-message-${id}').html(message);
                    } else {
                        $('#error-organization-alert-${id}').addClass("in");
                        $('#error-organization-alert-${id}').removeClass("hide");
                        $('#error-organization-message-${id}').html(message);
                    }
                },

                /**
                 * This function will remove a bundle from the organization that is
                 * in context.
                 * @param String type - the bundle type
                 */
                removeBundle: function(type) {
                    // grab the bundle to be removed from the controller
                    var bundle = this.get('bundleToBeRemoved');

                    // grab the organization model
                    var org = this.get('model');

                    // remove based on the bundle type
                    if(type == 'article') {
                        // remove the article from the list
                        org.get('csdl_article').removeObject(bundle);
                        // close the warning alert
                        this.send('closeWarningAlert','#warning-article-alert-${id}');
                    } else if(type == 'webinar') {
                        /* NOTE: as of now we are only using the first webinar
                         * in the list of webinars */
                        var webinars = org.get('webinar');
                        var selectedItems = webinars.get('firstObject').selected_items;
                        // remove the webinar from the list
                        selectedItems.removeObject(bundle);

                        // close the warning alert
                        this.send('closeWarningAlert','#warning-webinar-alert-${id}');
                    }
                    // set the updated org back on the controller and save the org bundles
                    this.set('model', org);
                    // reset the bundle to be removed
                    this.set('bundleToBeRemoved', null);
                    // show the save changes notification
                    this.send('showInfoBundleAlert');
                },

                /**
                 * This function will an organization's bundle information to the datastore.
                 */
                saveBundles: function() {
                    var _self = this;
                    // set saving mode to true
                    _self.set('isSaving', true);

                    // get the organization model from the controller
                    var org = this.get('model');

                    // save to server, the server will update all users with this org
                    var data = {};
                    data.requestType_${id} = 'SAVE_ORGANIZATION_BUNDLES';
                    data.orgId_${id} = org.get('_id').$oid;
                    data.articleBundleData_${id} = JSON.stringify(org.get('csdl_article'));
                    data.webinarBundleData_${id} = JSON.stringify(org.get('webinar'));

                    // post the data to the server
                    $.post("${ajaxHandlerBundleOrgAdmin}", data)
                    .done(function(response) {
                        // set saving mode to false
                        _self.set('isSaving', false);
                        if(response == 200) {
                           _self.send('showSavedAlert', '#saved-bundle-alert-${id}');
                           setTimeout(function() {
                               _self.send('closeSavedAlert', '#saved-bundle-alert-${id}');
                           }, 3000);
                        } else {
                           // show error
                           _self.send('showErrorAlert', "There was a problem saving the bundle information, please try again or contact help@computer.org",  '#error-bundle-alert-${id}');
                        }
                     })
                    .fail(function(error) {
                        // set saving mode to false
                        _self.set('isSaving', false);
                       _self.send('showErrorAlert', "There was a problem saving the bundle information, please try again or contact help@computer.org",  '#error-bundle-alert-${id}');
                       var eMsg = "BundleOrgAdmin - Error saving bundle for the organization: " + error.message;
                       Ember.Logger.error(eMsg);
                       var logData = {};
                       logData.message = eMsg;
                       Log.error(logData);
                    });
                },
               showValidationErrorMessage: function(alertElementId, messageElementId, message) {
                     message = (message != undefined) ? message : 'Please enter all required fields';
                     $('#'+messageElementId).html(message);
                     $('#'+alertElementId).addClass("in");
                     $('#'+alertElementId).removeClass("hide");
               },
                closeValidationErrorAlert: function(elementId) {
                   if(elementId != undefined) {
                       $('#'+elementId).addClass('hide');
                       $('#'+elementId).removeClass('in');
                   } else {  // close them all since we don't have an element id
                       $("div[class^='validation']").addClass('hide');
                       $("div[class^='validation']").removeClass('in');
                   }
                },
                showInfoBundleAlert: function() {
                    $('#info-bundle-alert-${id}').addClass("in");
                    $('#info-bundle-alert-${id}').removeClass("hide");
                },


                /**
                 * This function will save the organization to the datastore.
                 */
                saveOrganization: function() {
                    var _self = this;

                    // get the organization model from the controller
                    var org = this.get('model');

                    // validate the form
                    var isValidOrganization = true;

                    // first clear any old validation errors
                    this.clearValidationErrors('validation-organization-alert-${id}');
                    var name = org.get('name');
                    var description = org.get('description');

                    if (name === undefined || name.trim() === "") {
                        // add "has-error" class to the name container
                        $('.name-${id}').addClass("has-error");
                        isValidOrganization = false;
                    }
                    if (description === undefined || description.trim() === "") {
                        // add "has-error" class to the description container
                        $('.description-${id}').addClass("has-error");
                        isValidOrganization = false;
                     }

                    // if the form is valid save the org, else show the validation errors
                    if(isValidOrganization) {
                        // set saving mode to true
                         _self.set('isSaving', true);

                        // create the post data
                        var data = {};
                        data.requestType_${id} = 'UPDATE_ORGANIZATION';
                        data.orgId_${id} = org.get('_id').$oid;
                        // update the organization data's modified date time
                        org.modifiedDate = new Date().toISOString();
                        // remove the id from the org json, we'll be passing it in as a request parameter
                        delete org.id;
                        data.orgData_${id} = JSON.stringify(org);

                        // post the data to the server
                        $.post("${ajaxHandlerBundleOrgAdmin}", data)
                        .done(function(response) {
                            // set saving mode to false
                            _self.set('isSaving', false);
                            if(response == 200) {
                               _self.send('showSavedAlert');
                               setTimeout(function() {
                                   _self.send('closeSavedAlert');
                               }, 3000);
                            } else {
                               // show error
                               _self.send('showErrorAlert', "There was a problem saving this organization, please try again or contact help@computer.org");
                            }
                         })
                        .fail(function(error) {
                            // set saving mode to false
                            _self.set('isSaving', false);
                           _self.send('showErrorAlert', "There was a problem saving this organization, please try again or contact help@computer.org");
                           var eMsg = "BundleOrgAdmin - Error saving organization: " + error.message;
                           Ember.Logger.error(eMsg);
                           var logData = {};
                           logData.message = eMsg;
                           Log.error(logData);
                        });

                    } else {
                         this.send('showValidationErrorMessage', "validation-organization-alert-${id}", "validation-organization-message-${id}");
                    }
                }
            }
        });

        // define the app routes for the BundleOrgAdmin Application
        BundleOrgAdminApp.Router.map(function() {
            this.resource('admin', {path: "/"}, function() {
                this.route("new");
                this.resource("organization", { path: "/:org_id" }, function() {
                       this.route("edit");
                       this.route("bundles");
                       this.resource("users", { path: "/users" }, function() {
                           this.route("edit", { path: "/edit" });
                           this.route("new", { path: "/new" });
                       });
                });
            });
        });

        // define the loading route
        BundleOrgAdminApp.LoadingRoute = Ember.Route.extend({});

        /**
         * Define the admin route. Render the admin template,
         * and tell Ember to render the footer template into the
         * admin template's outlet named "footer".
         */
        BundleOrgAdminApp.AdminRoute = Ember.Route.extend({
           renderTemplate: function() {
             this.render();
             this.render('footer', { into: 'admin', outlet: 'footer'} );
           }
        });

        /**
         * Define the admin index route.  The functions are called
         * in the following order:
         * 1. model
         * 2. setupController
         */
        BundleOrgAdminApp.AdminIndexRoute = Ember.Route.extend({
              model: function() {
                return BundleOrgAdminApp.Organization.find();
              },
              setupController: function(controller, model) {
                  controller.set('content', model);
              }
        });

        // define the organization route
        BundleOrgAdminApp.OrganizationRoute = Ember.Route.extend({

            /**
              * This is a standard function of Ember to initialize the controller.
              * Here we are creating the Ember subscriptions to certain channels, setting
              * a default webinar expiration date, and loading the organization in context's users.
              * @param Ember.Controller controller
              */
            setupController: function (controller, model) {
                // first set the organization model on the organization controller
                controller.set("model", model);

                // set the default webinar expiration date to one year from today
                var webinarExpirationDate = new Date();
                webinarExpirationDate.setDate(webinarExpirationDate.getDate() + 365);
                controller.set('webinarExpirationDate', webinarExpirationDate);

                // load the users for this organization
                var users = BundleOrgAdminApp.User.find(model.id);

                // set the users on the user controller
                this.controllerFor('users').set('users', users);

                /*
                 * create listener that will update any organization sub data,
                 * i.e. the number of users, bundle info, etc.
                 */
                Ember.Instrumentation.subscribe("BundleOrgAdminApp.updateOrganizationSubData", {
                  before: function (name, timestamp, payload) {
                    controller.send('updateOrganizationSubData', payload);
                  },
                  after: function () { }
                });
                Ember.Instrumentation.subscribe("BundleOrgAdminApp.setNumberOfUsers", {
                  before: function (name, timestamp, payload) {
                    controller.send('setNumberOfUsers', payload);
                  },
                  after: function () { }
                });
            },
            renderTemplate: function() {
                this.render('organization');
                $('#admin-bundle-nav-${id}').removeClass('active');
                $('#admin-org-nav-${id}').addClass('active');
                $('#admin-user-nav-${id}').removeClass('active');
                this.render('organization.edit', {into: 'organization', outlet: 'content'});
            },

            /**
              * This is the required actions object that Ember wants you put
              * your functions that handle actions from Handlebars templates.  I typically like
              * to keep view related navigation actions in the Route actions object not the
              * Controller actions object as you see here.
              */
            actions: {

                /**
                 * This function will show the "users" template in the organization template's
                 * content outlet.
                 */
                showUsers: function(){
                    // remove the active class from all other nav links except this user link
                    $('#admin-bundle-nav-${id}').removeClass('active');
                    $('#admin-org-nav-${id}').removeClass('active');
                    $('#admin-user-nav-${id}').addClass('active');
                    this.render('users', {into: 'organization', outlet: 'content'});
                },

                /**
                 * This function will show the "organization.bundles" template in the organization template's
                 * content outlet.
                 */
                showBundle: function(){
                    $('#admin-bundle-nav-${id}').addClass('active');
                    $('#admin-org-nav-${id}').removeClass('active');
                    $('#admin-user-nav-${id}').removeClass('active');
                    this.render('organization.bundles', {into: 'organization', outlet: 'content'});
                    $('#webinar-expiration-field-${id}').datepicker({
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        todayHighlight: true
                    });
                },

                /**
                 * This function will show the "organization.edit" template in the organization template's
                 * content outlet.
                 */
                showOrganization: function(){
                    $('#admin-bundle-nav-${id}').removeClass('active');
                    $('#admin-org-nav-${id}').addClass('active');
                    $('#admin-user-nav-${id}').removeClass('active');
                    this.render('organization.edit', {into: 'organization', outlet: 'content'});
                }
            }
        });
    </script>
    <script>
        $(document).ready(function() {
            var now = new Date();
            var currYear = now.getFullYear();
            $('.copy-section').html('&copy; ' + currYear + ' IEEE. All Rights Reserved.');
            // hide the search bar
            try {
                Ember.Instrumentation.instrument('SearchApp.hideSearchApp', '');
            } catch(error) {}
        });
    </script>
</c:if>
</c:if>

