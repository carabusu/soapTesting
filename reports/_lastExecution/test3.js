jQuery(function($) {
    
	$.extend($.expr[':'],{
   		containsPartialText:  function(a, i, m) {
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
    }
	});
	
	
	$.extend($.expr[':'],{
   		containsFullText:  function(a, i, m) {
	          textSearch = m[3];
			  textSearch = textSearch.replace(/\\/g, "");
			  elemText = $(a).html().trim();
			  elemText = elemText.replace(/&nbsp;/g, " ");
			  console.log("element: " + elemText);
			  //console.log("text:    " + textSearch);
			  if (elemText===textSearch) {
			    //console.log("---" + elemText);
			    return $(a).text();
		  	}
    }
	});
	
});
