/*
 * directional v1.0.0
 * up? down? left? right?
 * MIT License
 */

(function($) {
  $.fn.directional = function( options ) {
    var lastScrollX = 0;
    var lastScrollY = 0;
    var direction = null;
    $(window).on('scroll', function(event) {  
      var scrollX = window.pageXOffset;
      var scrollY = window.pageYOffset;
      if (scrollY > lastScrollY) {
        $(window).trigger('down', [scrollY, lastScrollY]);
        window.direction = 'down';
      } else if (scrollY < lastScrollY) {
        $(window).trigger('up');
        window.direction = 'up';
      } else if (scrollX > lastScrollX) {
        $(window).trigger('right');
        window.direction = 'right';
      } else if (scrollX < lastScrollX) {
        $(window).trigger('left');
        window.direction = 'left';
      } else {
        window.direction = null;
      }
      lastScrollX = scrollX;
      lastScrollY = scrollY;
    });
  }
}(jQuery));