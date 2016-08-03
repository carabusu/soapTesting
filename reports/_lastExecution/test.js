(function($) {
	//https://github.com/keithclark/JQuery-Extended-Selectors/blob/master/jquery-extra-selectors.js
	 function textEquals(m) {  
        return this.text().match("^" + m[3] + "$");  
	 }

	
	
	$.extend($.expr[':'], textEquals);
}(jQuery));
