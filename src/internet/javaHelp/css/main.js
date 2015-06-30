$(document).ready(function(){
	var altura = $('.menu').offset().top;
	/*alert(altura);*/
	$(window).on('scroll',function(){
		if ($(window).scrollTop() > altura ) {
			$('.menu').addClass('menu-fixed');
		}else{
			$('.menu').removeClass('menu-fixed');
		}
	});
});