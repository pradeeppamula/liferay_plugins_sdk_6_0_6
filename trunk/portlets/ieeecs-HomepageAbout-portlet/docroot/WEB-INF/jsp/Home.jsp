<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='aboutAjaxHandler' id='aboutAjaxHandler' />

	<style type="text/css">
		#homepage-about-container-${id} {
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
		.about-footer-social-links > a:hover {
			text-decoration: none;
		}
		.about-footer-links,.about-footer-social-links {
			line-height: 40px;
			text-align:center;
		}
		.about-footer-links > a, .about-footer-social-links > a {
		    color: #ffffff;
		}
		.copy-section { padding: 0 5px;}
		.footer-copy-section {
		    text-align: center;
		}
		.about-mid-container { background-color: #ffffff; }
		.about-mid-container > ul {
		    padding:0 10% 10px 10%;
		}
		.about-mid-container > ul > li {
		    margin-bottom: 10px;
		}
		.about-top-container {
		    background-color: #004d85;
		    color: #ffffff;
		    padding-bottom: 40px;
	    }
	    .page-header { text-align: center; }
	    .about-top-content {
	        padding: 0 13%;
	    }
	     .about-top-content > ul {
            padding: 0 40px;
        }
	    .about-top-content > p {
	        text-align: justify;
	    }

	     ul.browser-list {width: 90%; margin: 0 auto; list-style: none; list-style-type:none; }
	     ul ul { list-style-type:none; }
         ul.browser-list > li {float: left; padding: 30px; width: 25%; }
         ul.browser-list > li > ul {display: block; width: 100%;list-style-type: none; }
         ul.browser-list > li > ul > li {display: block; float: none; }
         ul.browser-list > li > a { color: #ffffff; }
        .supported-browsers-container {
            color: #fff;
        }
	</style>

	<div id="homepage-about-container-${id}">

        <c:if test="${!isSignedIn}">
            <%@ include file="/WEB-INF/templates/about.home.hbs" %>
        </c:if>

         <c:if test="${!isSignedIn}">
              <%@ include file="/WEB-INF/templates/about.index.hbs" %>
        </c:if>


         <!-- SUPPORTED BROWSERS TEMPLATE -->


	</div> <!-- /#homepage-about-container-${id} -->

    <script>
        Ember.TEMPLATES['about/article'] = Ember.Handlebars.compile('<div class="about-top-container"> <div class="page-header"> <h1><i class="icon-file-text icon-fixed-width"></i>Computer Society Digital Library</h1> </div> <div class="about-top-content"> <p class="lead"> Access to only as many articles as you need</p> <p> The Computer Society Digital Library is well known for being the most comprehensive computing resource in the world. But sometimes, you might not need access to the entire computing universe. </p> <div class="col-md-7 col-sm-7 pull-right"> <img src="/ieeecs-HomepageAbout-portlet/images/bundlearticles-image-3.jpg" alt="bundle-articles" class="img-rounded pull-right col-md-11 col-sm-11"> </div> <p> That is why IEEE Computer Society is pleased to introduce CSDL Bundled Article Sales. Now, your company or organization can purchase access to only as many articles as you need-whether that is 25, 50, 100, 250, or 500. <br /><br /> And you can select just the topics you are interested in including areas such as cloud, big data, mobile, IoT (Internet of Things), embedded systems. <br /><br /> CSDL Bundled Articles offer a cost-effective way of accessing one of the world greatest computing resources. And if you find that your allotment is running out-do not worry; we will remind you when it is time to reorder. <br /><br /> </p> <div> <button {{ action "purchaseBundle" }} class="btn btn-block btn-primary btn-lg">Request A Quote</button> </div> </div><!- /.about-top-content --> </div> <div class="about-mid-container well"> <blockquote><h3>The CSDL provides:</h3></blockquote> <ul> <li>Over 500,000 individual documents each presenting the latest thinking in computer science and technology </li> <li> The vital contents of the world\s leading peer-reviewed computing publications including Computer, IEEE Software, IEEE Computer Graphics and Applications, IEEE Security and Privacy, IEEE Internet Computing, and many more </li> <li> Authoritative transactions including Pattern Analysis and Machine Intelligence, Mobile Computing, Parallel and Distributed Processing, Computer Architecture, and others </li> <li> The cream of over 4,100 conferences giving you access to abstracts and complete papers on the most advanced and useful topics - with new material added daily. </li> </ul> <div class="alert alert-info"> <p> <i class="icon-info-sign icon-fixed-width icon-2x"></i> <a href="#" {{action "purchaseBundle"}}><strong>Talk</strong></a> to an IEEE Computer Society representative today about this unique opportunity to purchase CSDL article bundles in sizes that meet your organizations needs. </p> </div> </div> <div class="row about-footer-links"> <a href="/portal/web/myhome/home">Home</a> <a href="/portal/web/myhome/article-bundle">CSDL</a> <a href="/portal/web/myhome/webinar-bundle">Webinars</a> <a href="http://www.ieee.org/site_terms_conditions.html?WT.mc_id=hpf_terms">Terms</a> <a href="http://www.computer.org/portal/web/guest/privacy">Privacy</a> <a href="#top">Top</a> </div> <div class="row about-footer-social-links"> <a href="mailto:help@computer.org"><i class="icon-envelope icon-2x"></i></a> <a href="https://twitter.com/computersociety" target="_blank"><i class="icon-twitter icon-2x"></i></a> <a href="https://www.facebook.com/ieeecomputersociety?v=wall" target="_blank"><i class="icon-facebook icon-2x"></i></a> <a href="https://plus.google.com/b/106653716856205029801/#106653716856205029801/posts" target="_blank"><i class="icon-google-plus icon-2x"></i></a> <a href="http://www.linkedin.com/groups?home=&amp;gid=52513&amp;trk=anet_ug_hm" target="_blank"><i class="icon-linkedin icon-2x"></i></a> <a href="http://www.youtube.com/user/ieeeComputerSociety" target="_blank"><i class="icon-youtube icon-2x"></i></a> <a href="http://www.computer.org/portal/web/csdl/dlrss" target="_blank"><i class="icon-rss icon-2x"></i></a> </div> <div class="row footer-copy-section"> <p class="text-muted"> <span class="text-primary copy-section"></span> <i class="icon-keyboard"></i> in LA </p> </div>');
        Ember.TEMPLATES['about/browser'] = Ember.Handlebars.compile('<div class="supported-browsers-container"> <h1 class="text-center">Supported Browsers</h2> <p class="lead">In order to have the best experience we recommend that you use one of the following browsers (with their modern versions) listed below.</p> <div class="text-center"> <ul class="browser-list"> <li> <a href="https://www.google.com/intl/en/chrome/browser/"> <img alt="chrome" src="/ieeecs-HomePage-theme/images/chrome-icon.png"/> <ul> <li>Version >= 11</li> </ul> </a> </li> <li> <a href="http://www.mozilla.org/en-US/firefox/new/"> <img alt="firefox" src="/ieeecs-HomePage-theme/images/firefox-icon.png"/> <ul> <li>Version >= 6</li> </ul> </a> </li> <li> <a href="http://www.apple.com/safari/"> <img alt="safari" src="/ieeecs-HomePage-theme/images/safari-icon.png"/> <ul> <li>All Versions</li> </ul> </a> </li> <li> <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie"> <img alt="ie" src="/ieeecs-HomePage-theme/images/ie-icon.png"/> <ul> <li>Version >= 9</li> </ul> </a> </li> </ul> </div> </div>');

        // initialize the article Ember App
        AboutApp = Ember.Application.create({
             rootElement: '#homepage-about-container-${id}',
             LOG_TRANSITIONS: true
        });

        // define the about controllers
        AboutApp.AboutArticleController = Ember.ObjectController.extend({
            actions: {
               purchaseBundle: function() {
                    // navigate to the purchase bundle page
                    window.location =  '/portal/web/myhome/purchase-bundle?t=a';
               }
            }
        });
        // webinar controller
        AboutApp.AboutWebinarController = Ember.ObjectController.extend({
            catelogIsVisible: false,
            actions: {
                purchaseBundle: function() {
                    // navigate to the purchase bundle page
                    window.location = '/portal/web/myhome/purchase-bundle?t=w';
               },
                toggleCatelog: function() {
                    this.toggleProperty('catelogIsVisible');
                }
            }
        });    
        AboutApp.AboutHomeController = Ember.ObjectController.extend({
            actions: {
                purchaseWebinarBundle: function() {
                    // navigate to the purchase bundle page
                    window.location = '/portal/web/myhome/purchase-bundle?t=w';
               },
               purchaseArticleBundle: function() {
                   // navigate to the purchase bundle page
                   window.location = '/portal/web/myhome/purchase-bundle?t=a';
              }
            }
        });           

        /* THIS IS THE TEMP HACK FOR I.E. 8/9 WHEN USING LOCATION
         * HISTORY IN THE ROUTER.  IT HAS BEEN MERGED INTO EMBER CORE,
         * BUT HASN'T BEEN RELEASED YET.
         */
        if (!history.pushState) {
          Ember.LinkView.reopen({
            click: function() {
              window.location.path.replace(this.get('href'));
            }
          });

          Ember.HistoryLocation.reopen({
            replaceState: function() {}
          });
        }


         AboutApp.Router.reopen({
           location: 'history',
           rootURL: '/portal/web/myhome/'
         });

        // define the app routes
        AboutApp.Router.map(function() {
            this.resource('about', {path: '/'}, function() {
                this.route('article', {path: '/article-bundle'});
                this.route('webinar', {path: '/webinar-bundle'});
                this.route('home', {path: '/home'});
                this.route("browser", {path: '/br'});
            });
        });

        // for the initial home view
        AboutApp.AboutRoute = Ember.Route.extend();
        AboutApp.ArticleRoute = Ember.Route.extend();
</script>
 <c:if test="${isSignedIn}">
<script>
  $(document).ready(function() {
        // if the user is signed in, we need to load their purchase data so we can search content
        var data = {};
         data.requestType_${id} = 'LOAD_USER_PURCHASE_DATA';
          /*
           * finally post again to the portlet to get the purchase data and
           * send this data out to the necessary Ember apps.
           */
       $.post("${aboutAjaxHandler}", data)
          .done(function(response) {
              Ember.Instrumentation.instrument('SearchApp.setUserPurchaseData', response);
          })
          .fail(function(error) { console.log("error loading purchase data:" + error); })
          .always(function() {});
    });
</script>
</c:if>
<script>
    $(document).ready(function() {
        var now = new Date();
        var currYear = now.getFullYear();
        $('.copy-section').html('&copy; ' + currYear + ' IEEE. All Rights Reserved.');
    });
</script>
