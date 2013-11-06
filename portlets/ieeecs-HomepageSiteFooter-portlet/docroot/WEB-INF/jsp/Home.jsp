<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>

	<style type="text/css">
		#homepage-site-footer-container-${id} {
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
			background-color: #ffffff;	
			padding: 20px 25px;		
			position: relative;
		}	
		.footer-social-links > a:hover {
			text-decoration: none;
		}
		.footer-links,.footer-social-links {
			line-height: 40px;
		}
	</style>

	<div id="homepage-site-footer-container-${id}">
        <div class="row footer-links">
          <a href="/portal/web/myhome/article-bundle">CSDL</a>
          <a href="/portal/web/myhome/webinar-bundle">Webinars</a>
          <a href="http://www.ieee.org/site_terms_conditions.html?WT.mc_id=hpf_terms">Terms</a>
          <a href="http://www.computer.org/portal/web/guest/privacy">Privacy</a>
        </div>
        <div class="row footer-social-links">
          <a href="mailto:help@computer.org"><i class="icon-envelope icon-2x"></i></a>
          <a href="https://twitter.com/computersociety" target="_blank"><i class="icon-twitter icon-2x"></i></a>
          <a href="https://www.facebook.com/ieeecomputersociety?v=wall" target="_blank"><i class="icon-facebook icon-2x"></i></a>
          <a href="https://plus.google.com/b/106653716856205029801/#106653716856205029801/posts" target="_blank"><i class="icon-google-plus icon-2x"></i></a>
          <a href="http://www.linkedin.com/groups?home=&amp;gid=52513&amp;trk=anet_ug_hm" target="_blank"><i class="icon-linkedin icon-2x"></i></a>
          <a href="http://www.youtube.com/user/ieeeComputerSociety" target="_blank"><i class="icon-youtube icon-2x"></i></a>
          <a href="http://www.computer.org/portal/web/csdl/dlrss" target="_blank"><i class="icon-rss icon-2x"></i></a>
        </div>
	    <div class="row">
	        <div class="col-md-9 col-sm-9">
	        	<span class="text-muted copy-section"></span>
	        </div>
	        <div class="col-md-3 col-sm-3"><i class="icon-keyboard"></i> in LA</div>
	    </div>
	</div> <!-- /#homepage-site-footer-container-${id} -->

	<script>
		$(document).ready(function() {
			var now = new Date();
			var currYear = now.getFullYear();
			$('.copy-section').html('&copy; ' + currYear + ' IEEE. All Rights Reserved.');
		});	
	</script>
