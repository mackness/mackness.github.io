
  $(window).load(function() {
  	
  	//homepage sliders  
    $('.homepage_slider .flexslider').flexslider();
    
    $('.carousel .flexslider').flexslider({
    	animation: "slide"
    });

	setTimeout(function() {
	    $('footer').append('this');
	}, 4000)
});