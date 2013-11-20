// Browser check [NEED TO HAVE BOWSER.JS ALREADY INCLUDED BEFORE THIS FILE IS INCLUDED]
/*
 if the browser fails of the following conditions,
 forward to the browser download page:
 1. Opera browser
 2. Chrome < 11
 3. Firefox < 6
 4. IE < 9
*/
if ((bowser.msie && bowser.version < 9)||
    (bowser.opera)||
    (bowser.mozilla && bowser.version < 6)||
    (bowser.chrome && bowser.version < 11)) {
    window.location = '/portal/web/myhome/browser';
}
// make sure console is available
if (!window.console) window.console = {};
if (!window.console.log) window.console.log = function () {};

//Height Define
jQuery(document).ready(function(){
var a = jQuery(window).height();
	jQuery("#home").height(a - 0)
});

//Scroll To
jQuery(".scroll").click(function(event){
	event.preventDefault();
	jQuery("html,body").animate({scrollTop:jQuery(this.hash).offset().top}, 400)
});

//Scroll Spy Refresh
jQuery('#navbar').scrollspy()

//Scroll To Top
jQuery(document).ready(function(){
	//Check to see if the window is top if not then display button
	jQuery(window).scroll(function(){
		if (jQuery(this).scrollTop() > 160) {
			jQuery('.scrollToTop').fadeIn();
		} else {
			jQuery('.scrollToTop').fadeOut();
		}
	});
	//Click event to scroll to top
	jQuery('.scrollToTop').click(function(){
		jQuery('html, body').animate({scrollTop : 0},800);
		return false;
	});
});

//Onload FadeIn
jQuery(document).ready(function(){
	jQuery(".fadeOnLoad").delay(1000).fadeIn(700);
});

//Div FadeOut On Scroll
jQuery(window).scroll(function() {
    if (jQuery(this).scrollTop() < 150) {
        jQuery(".hero-text:hidden").fadeIn(400);
    }
    else {
        jQuery(".hero-text:visible").fadeOut(400);
    }
});

//Onload FadeIn
jQuery(document).ready(function(){
	jQuery(".fadeOnLoad").delay(1000).fadeIn(700);
});

// Login Modal Click
jQuery("a.login").on("click", function() {
    $("#login-modal").modal("show");
});
