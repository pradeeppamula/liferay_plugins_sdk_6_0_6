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
	</style>

	<div id="homepage-about-container-${id}">
	</div> <!-- /#homepage-about-container-${id} -->

    <script>
        Ember.TEMPLATES['about/webinar'] = Ember.Handlebars.compile('<div class="about-top-container col-xs-12"> <div class="page-header"> <h1><i class="icon-youtube-play icon-fixed-width"></i>Webinars</h1> </div> <div class="about-top-content"> <p class="lead"> Keep your staff up to date with expert instruction </p> <p> IEEE Computer Society bundled webinars are now available to let your staff receive perpetual access to our collection of more than 100 essential technical webinars developed by leaders in the field. </p> <em><h3>Bundled webinars let you:</h3></em> <ul> <li> Purchase one-time or unlimited access for all employees or individual groups </li> <li> Track employee viewing habits </li> <li> Come up with a customizable program that works for you. </li> </ul> <p><button {{action "purchaseBundle" }} class="btn btn-block btn-primary btn-lg">Try out a webinar now.</button></p> </div> <!-- /.about-top-content --> </div> <div class="about-mid-container well col-xs-12"> <div class="col-md-7 col-sm-7 pull-right"> <img src="/ieeecs-HomepageAbout-portlet/images/bundlewebinars-image-1.jpg" alt="bundle-webinars" class="img-rounded pull-right col-md-11 col-sm-11 col-xs-12"> </div> {{#if catelogIsVisible}} <a href="#" {{ action "toggleCatelog"}}> <h4><i class="icon-info-sign icon-fixed-width"></i>Hide the catalogue <i class="icon-caret-up icon-fixed-width"></i></h4> </a> <h3>The IEEE Computer Society Technical Webinar Collection meets the needs of your technical staff, providing a direct view into basic skills as well as leading-edge technologies.  The webinars in the collection include: </h3> <ul> <li>10 Deadly Sins of Software Estimation</li> <li>A View from the C-Suite: The Future of Information Security</li> <li>Active Defense</li> <li>Advances in Requirements Engineering</li> <li>Affordability Engineering for Requirements Engineers</li> <li>Affordability-Based Engineering</li> <li>Affordable Software Architecture</li> <li>Against the Odds: Managing the Unmanageable in a Time of Crisis</li> <li>Agile Leadership and Contracting Models</li> <li>Agile Model-Driven Development</li> <li>Agile Software Development in the Computer Science Handbook</li> <li>Architecture as Language: Using DSLs to describe Software Architecture</li> <li>Automation Everywhere--Industrial Control Systems in the Cross Hairs</li> <li>Balancing the Tension between Lean and Agile</li> <li>Best Practices for Transforming and Evolving a Legacy System</li> <li>Best Practices in Software Architecture</li> <li>Building Software for Affordability</li> <li>Business Analytics and Optimization</li> <li>Clinical Virtual Reality: A Brief Review of the Future</li> <li>Cloud Computing--An Overview and Introduction from an Enterprise Perspective</li> <li>Cloud Security Challenges</li> <li>Combinatorial Test Design Methodology</li> <li>Complexity Made Simple: Tackling Complexity in Software-Intensive Systems</li> <li>Computer-Assisted Approaches for Identifying and Tracking Technical Debt</li> <li>Concepts and Methods in Systems Architecting</li> <li>Counterinsurgency in Context</li> <li>Deception and Estimation: How We Fool Ourselves</li> <li>Discovery Driven Growth</li> <li>Dynamic Construction - UnCovered</li> <li>Emergent Design: The Evolutionary Nature of Professional Software Development</li> <li>Fifteen Years of Design Patterns</li> <li>Form and Content: Why They Both Matter</li> <li>Four Strategies for Responsive Design</li> <li>From Vision to Execution</li> <li>Future of Surgery</li> <li>Gray, the new Black: Gray-Box Vulnerability Testing</li> <li>How to Reduce Costs Throughout the Project Lifecycle</li> <li>Human Social Culture Behavior Modeling</li> <li>Immerse Yourself - A Discussion on Virtual World Technologies</li> <li>Improving Software Economics, Achieving Agility at Scale</li> <li>Industrial Strength Software Measurement</li> <li>Innovation Tournaments</li> <li>Introduction to Lean Software Development</li> <li>Ladar: A Look Forward</li> <li>Lean Engineering</li> <li>Leveraging a Service Oriented and Model Driven Approach to Architecting Your Enterprise</li> <li>Leveraging Social Media for Early Indications and Warnings of Instability and Unrest</li> <li>Low Temperature, All Copper Solder-Free Electronic Assembly and Interconnect Technology</li> <li>Manage your Technical Debt</li> <li>Managing Complexity: A Panel Discussion Exploring the Challenges of Architecting Complex Systems</li> <li>Medical Device Interoperability</li> <li>Model-Based Engineering of Real-Time and Embedded Systems</li> <li>Modeling and Simulation-Driven Development of Complex, Software-Intensive Systems</li> <li>Modeling the Life-cycles of Systems of Systems</li> <li>Optimizing Optimism: Why Engineers Should Be More Like Las Vegas Bookies</li> <li>Pattern-Oriented Software Architecture: A Pattern Language for Distributed Computing</li> <li>Pattern-Oriented Software Architecture:XA Pattern Language for High Quality and Affordable Distributed Computing Systems</li> <li>Preventing Defects With Test Driven Development</li> <li>Principles, Patterns, Practices, Professionalism</li> <li>Reconciling Strategy, Hardware, and Software Through Architecture: Lessons -- Old and New</li> <li>Requirements Triage</li> <li>Right-Sizing Agile Development</li> <li>Risk-Based Testing</li> <li>Scaling with Design Constraints -- Predicting the Future of Big Chips</li> <li>Secure Programming with Static Analysis</li> <li>Service Orientation and Systems of Systems</li> <li>Seven Unbreakable Rules of Software Leadership</li> <li>Seven Unbreakable Rules of Software Leadership</li> <li>Software Architecture Using Model-Based Approaches</li> <li>Software Product Lines: What Got Us Here, Won’t Get Us There</li> <li>Software Testing as a Quality-Improvement Activity</li> <li>Standards & Practices for Software and System Engineers / Professionals</li> <li>Strategy is Innovation</li> <li>Systems Engineering as a Literate Behavior</li> <li>Systems Engineering Cost Estimation</li> <li>Systems Engineering Economics-- Using Requirements to do Early Estimation and Prioritization</li> <li>Systems of Systems and Large-Scale Systems Assurance</li> <li>Systems-of-Systems Analysis of Ballistic Missile Defense Architecture Effectiveness through Surrogate Modeling and Simulation</li> <li>Technical Institutions and Technical Autonomy</li> <li>Technology Trends and Innovation Insights</li> <li>Test Driven Development in the Field</li> <li>The Collaborative Effort of Software and Hardware Teams in Agile Development</li> <li>The Coming of Age of Platforms</li> <li>The Data-Context-Interaction (DCI) Architecture -- Realizing the Object Vision</li> <li>The Economics of Agility</li> <li>The Economics of Reuse</li> <li>The Future of Software V&V</li> <li>The Innovator&rsquo;s Prescription</li> <li>The Real Skills of An Automated Test Professional</li> <li>The Role of the Software Architect</li> <li>The Science (and Art) of Security Fragility</li> <li>The Software Challenge of Robots</li> <li>Universal Systems Language (USL) and its Automation, the 001 Tool Suite, for Designing and Building Systems and Software</li> <li>Using Measures and Risk Indicators for Early Insight Into Software Product Characteristics such as Software Safety</li> <li>Utilizing Design of Experiments to Reduce Software and System Testing Cost</li> <li>What is in Your Program? Application Security Maturity in 2012</li> <li>Why We Need Architects (and Architecture) on Agile Projects</li> <li>Wired for War: Everything You Wanted to Know About Robots and War</li> <li>Your Requirements for Managing Requirements</li> </ul> {{else}} <a href="#" {{ action "toggleCatelog"}}> <h4><i class="icon-info-sign icon-fixed-width"></i>View the full catalogue <i class="icon-caret-down icon-fixed-width"></i></h4> </a> {{/if}} <h3> Keep your technical staff up to date on the latest techniques and trends with engaging instruction from the leaders in the field. The IEEE Computer Society Technical Webinar Collection includes exciting and essential topics like: </h3> <div> <blockquote> <p>What Every Engineer Should Know About Systems Thinking</p> <small>by Ricardo Valerdi</small> </blockquote> <blockquote> <p> Emergent Design: The Evolutionary Nature of Professional Software Development</p> <small> by Scott Bain</small> </blockquote> <blockquote> <p>Balancing the Tension Between Lean and Agile</p> <small>by Jim Coplien</small> </blockquote> <blockquote> <p> How to Reduce Costs Throughout the Project Lifecycle</p> <small>by Kent Beck</small> </blockquote> <blockquote> <p>The Coming of Age of Platforms</p> <small>by Mary Poppendieck</small> </blockquote> <blockquote> <p>Technology Trends and Innovation Insights</p> <small> by Josh Wolfe</small> </blockquote> </div> <p> And 67 more fascinating titles, including the newest technologies in cloud computing, agile development, nano architecture, green computing, and much more. </p> <p> The Technical Webinar Collection covers a wide range of computing and information processing technologies. These dynamic webinars train your staff in both business and highly technical skills applicable to all phases of project design and development, IT, and engineering. </p> <p> Presented by leading professionals, the IEEE Computer Society Technical Webinar Collection will make your staff members feel as if they are on the front lines of innovation, and at the same time, they perfect their basic skills. Exciting presenters stimulate questions and prompt research to help solve day-to-day problems. </p> <div class="alert alert-info"> <p> <i class="icon-info-sign icon-fixed-width icon-2x"></i> <a href="#" {{action "purchaseBundle"}}><strong>Talk</strong></a>  to an IEEE Computer Society representative today about this unique opportunity to receive perpetual access to this exciting collection for a single, affordable price. </p> </div> </div> <div class="row about-footer-links"> <a href="/portal/web/myhome/home">Home</a> <a href="/portal/web/myhome/article-bundle">CSDL</a> <a href="/portal/web/myhome/webinar-bundle">Webinars</a> <a href="http://www.ieee.org/site_terms_conditions.html?WT.mc_id=hpf_terms">Terms</a> <a href="http://www.computer.org/portal/web/guest/privacy">Privacy</a> <a href="#top">Top</a> </div> <div class="row about-footer-social-links"> <a href="mailto:help@computer.org"><i class="icon-envelope icon-2x"></i></a> <a href="https://twitter.com/computersociety" target="_blank"><i class="icon-twitter icon-2x"></i></a> <a href="https://www.facebook.com/ieeecomputersociety?v=wall" target="_blank"><i class="icon-facebook icon-2x"></i></a> <a href="https://plus.google.com/b/106653716856205029801/#106653716856205029801/posts" target="_blank"><i class="icon-google-plus icon-2x"></i></a> <a href="http://www.linkedin.com/groups?home=&amp;gid=52513&amp;trk=anet_ug_hm" target="_blank"><i class="icon-linkedin icon-2x"></i></a> <a href="http://www.youtube.com/user/ieeeComputerSociety" target="_blank"><i class="icon-youtube icon-2x"></i></a> <a href="http://www.computer.org/portal/web/csdl/dlrss" target="_blank"><i class="icon-rss icon-2x"></i></a> </div> <div class="row footer-copy-section"> <p class="text-muted"> <span class="text-primary copy-section"></span> <i class="icon-keyboard"></i> in LA </p> </div>');
        Ember.TEMPLATES['about/article'] = Ember.Handlebars.compile('<div class="about-top-container col-xs-12"> <div class="page-header"> <h1><i class="icon-file-text icon-fixed-width"></i>Computer Society Digital Library</h1> </div> <div class="about-top-content"> <p class="lead"> Access to only as many articles as you need</p> <p> The Computer Society Digital Library is well known for being the most comprehensive computing resource in the world. But sometimes, you might not need access to the entire computing universe. </p> <div class="col-md-7 col-sm-7 col-xs-12 pull-right"> <img src="/ieeecs-HomepageAbout-portlet/images/bundlearticles-image-3.jpg" alt="bundle-articles" class="img-rounded pull-right col-md-11 col-sm-11 col-xs-12"> </div> <p> That&rsquo;s why IEEE Computer Society is pleased to introduce CSDL Bundled Article Sales. Now, your company or organization can purchase access to only as many articles as you need-whether that&rsquo;s 25, 50, 100, 250, or 500. <br /><br /> And you can select just the topics you&rsquo;re interested in including areas such as cloud, big data, mobile, IoT (Internet of Things), embedded systems. <br /><br /> CSDL Bundled Articles offer a cost-effective way of accessing one of the world&rsquo;s greatest computing resources. And if you find that your allotment is running out-don&rsquo;t worry; we will remind you when it&rsquo;s time to reorder. <br /><br /> </p> <div> <button {{ action "purchaseArticleBundle" }} class="btn btn-block btn-primary btn-lg">Request A Quote</button> </div> </div><!- /.about-top-content --> </div> <div class="about-top-container col-xs-12"> <div class="page-header"> <h1><i class="icon-youtube-play icon-fixed-width"></i>Webinars</h1> </div> <div class="about-top-content"> <p class="lead"> Keep your staff up to date with expert instruction </p> <p> IEEE Computer Society bundled webinars are now available to let your staff receive perpetual access to our collection of more than 100 essential technical webinars developed by leaders in the field. </p> <em><h3>Bundled webinars let you:</h3></em> <ul> <li> Purchase one-time or unlimited access for all employees or individual groups </li> <li> Track employee viewing habits </li> <li> Come up with a customizable program that works for you. </li> </ul> <p><button {{action "purchaseWebinarBundle" }} class="btn btn-block btn-primary btn-lg">Try out a webinar now.</button></p> </div> <!-- /.about-top-content --> </div>');
        <c:if test="${!isSignedIn}">
            Ember.TEMPLATES['about/home'] = Ember.Handlebars.compile('<div class="about-top-container col-xs-12"> <div class="page-header"> <h1><i class="icon-file-text icon-fixed-width"></i>Computer Society Digital Library</h1> </div> <div class="about-top-content"> <p class="lead"> Access to only as many articles as you need</p> <p> The Computer Society Digital Library is well known for being the most comprehensive computing resource in the world. But sometimes, you might not need access to the entire computing universe. </p> <div class="col-md-7 col-sm-7 col-xs-12 pull-right"> <img src="/ieeecs-HomepageAbout-portlet/images/bundlearticles-image-3.jpg" alt="bundle-articles" class="img-rounded pull-right col-md-11 col-sm-11 col-xs-12"> </div> <p> That&rsquo;s why IEEE Computer Society is pleased to introduce CSDL Bundled Article Sales. Now, your company or organization can purchase access to only as many articles as you need-whether that&rsquo;s 25, 50, 100, 250, or 500. <br /><br /> And you can select just the topics you&rsquo;re interested in including areas such as cloud, big data, mobile, IoT (Internet of Things), embedded systems. <br /><br /> CSDL Bundled Articles offer a cost-effective way of accessing one of the world&rsquo;s greatest computing resources. And if you find that your allotment is running out-don&rsquo;t worry; we will remind you when it&rsquo;s time to reorder. <br /><br /> </p> <div> <button {{ action "purchaseArticleBundle" }} class="btn btn-block btn-primary btn-lg">Request A Quote</button> </div> </div><!- /.about-top-content --> </div> <div class="about-top-container col-xs-12"> <div class="page-header"> <h1><i class="icon-youtube-play icon-fixed-width"></i>Webinars</h1> </div> <div class="about-top-content"> <p class="lead"> Keep your staff up to date with expert instruction </p> <p> IEEE Computer Society bundled webinars are now available to let your staff receive perpetual access to our collection of more than 100 essential technical webinars developed by leaders in the field. </p> <em><h3>Bundled webinars let you:</h3></em> <ul> <li> Purchase one-time or unlimited access for all employees or individual groups </li> <li> Track employee viewing habits </li> <li> Come up with a customizable program that works for you. </li> </ul> <p><button {{action "purchaseWebinarBundle" }} class="btn btn-block btn-primary btn-lg">Try out a webinar now.</button></p> </div> <!-- /.about-top-content --> </div>');
            Ember.TEMPLATES['about/index'] = Ember.Handlebars.compile('<div class="about-top-container col-xs-12"> <div class="page-header"> <h1><i class="icon-file-text icon-fixed-width"></i>Computer Society Digital Library</h1> </div> <div class="about-top-content"> <p class="lead"> Access to only as many articles as you need</p> <p> The Computer Society Digital Library is well known for being the most comprehensive computing resource in the world. But sometimes, you might not need access to the entire computing universe. </p> <div class="col-md-7 col-sm-7 col-xs-12 pull-right"> <img src="/ieeecs-HomepageAbout-portlet/images/bundlearticles-image-3.jpg" alt="bundle-articles" class="img-rounded pull-right col-md-11 col-sm-11 col-xs-12"> </div> <p> That&rsquo;s why IEEE Computer Society is pleased to introduce CSDL Bundled Article Sales. Now, your company or organization can purchase access to only as many articles as you need-whether that&rsquo;s 25, 50, 100, 250, or 500. <br /><br /> And you can select just the topics you&rsquo;re interested in including areas such as cloud, big data, mobile, IoT (Internet of Things), embedded systems. <br /><br /> CSDL Bundled Articles offer a cost-effective way of accessing one of the world&rsquo;s greatest computing resources. And if you find that your allotment is running out-don&rsquo;t worry; we will remind you when it&rsquo;s time to reorder. <br /><br /> </p> <div> <button {{ action "purchaseArticleBundle" }} class="btn btn-block btn-primary btn-lg">Request A Quote</button> </div> </div><!- /.about-top-content --> </div> <div class="about-top-container col-xs-12"> <div class="page-header"> <h1><i class="icon-youtube-play icon-fixed-width"></i>Webinars</h1> </div> <div class="about-top-content"> <p class="lead"> Keep your staff up to date with expert instruction </p> <p> IEEE Computer Society bundled webinars are now available to let your staff receive perpetual access to our collection of more than 100 essential technical webinars developed by leaders in the field. </p> <em><h3>Bundled webinars let you:</h3></em> <ul> <li> Purchase one-time or unlimited access for all employees or individual groups </li> <li> Track employee viewing habits </li> <li> Come up with a customizable program that works for you. </li> </ul> <p><button {{action "purchaseWebinarBundle" }} class="btn btn-block btn-primary btn-lg">Try out a webinar now.</button></p> </div> <!-- /.about-top-content --> </div>');
        </c:if>
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
