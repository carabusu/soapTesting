//This selector will find exact text without case sensitivity
	$.expr[':'].exactTextCaseInsensitive = function(a, i, m) {
		var pattern = new RegExp("^" + m[3] + "$", "i");
		return $(a).text().match(pattern);
	};
	
	//This selector will find exact text with case sensitivity
	$.expr[':'].exactTextCaseSensitive = function(a, i, m) {
		var pattern = new RegExp("^" + m[3] + "$");
		return $(a).text().match(pattern);
	};
	
	//This selector will find all texts starting with a particular text
	$.expr[':'].textBeginningWithString = function(a, i, m) {
		var pattern = new RegExp("^" + m[3]);
		return $(a).text().match(pattern);
	};
	
	//This selector will find all texts ending with a particular text
	$.expr[':'].textEndingWithString = function(a, i, m) {
		var pattern = new RegExp(m[3] + "$");
		return $(a).text().match(pattern);
	};
	
	//This selector will find all strings across multiple lines
	$.expr[':'].textInMultipleLines = function(a, i, m) {
		var pattern = new RegExp("^" + m[3], "m");
		return $(a).text().match(pattern);
	};
	
	//mine
	
	$.expr[':'].textEquals = function(a, i, m) {  
    return $(a).text().match("^" + m[3] + "$");  
};});
