//This selector will find exact text without case sensitivity
	
	$.expr[':'].textEquals = function(a, i, m) {  
		 return $(a).html().trim().match("^" + m[3] + "$");  
	};
	
	$.expr[':'].containsPartialText = function(a, i, m) {
		  textSearch = m[3];
		  textSearch = textSearch.replace(/\\/g, "");
		  elemText = $(a).html().trim();
		  elemText = elemText.replace(/&nbsp;/g, " ");
		  console.log("element: " + elemText);
		  //console.log("text:    " + textSearch);
		  if (elemText.includes(textSearch)) {
		    //console.log("---" + elemText);
		    return $(a).text();
		  }

	};
	
	$.expr[':'].mazga= function(a, i, m) {
		   textSearch = m[3];
		  textSearch = textSearch.replace(/\\/g, "");
		  elemText = $(a).html().trim();
		  elemText = elemText.replace(/&nbsp;/g, " ");
		  console.log("element: " + elemText);
		  //console.log("text:    " + textSearch);
		  if (elemText.includes(textSearch)) {
		    //console.log("---" + elemText);
		    return $(a).text();
		  }
	};
