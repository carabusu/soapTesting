jQuery(function($) {
    
	$.extend($.expr[':'],{
   		containsPartialText:  function(a, i, m) {
        textSearch = m[3];
		textSearch = textSearch.replace(/\\/g, "");
		  elemText = $(a).html().trim();
		  elemText = elemText.replace(/&nbsp;/g, " ");
		  console.log("element: " + elemText);
		  //console.log("text:    " + textSearch);
		  if (elemText.includes(textSearch) && textSearch.includes(elemText)) {
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
			  console.log("text:    " + textSearch);
			  alert("text:    " + textSearch);
			   if (elemText.includes(textSearch)) {
			    console.log("---" + elemText);
			    return $(a).text();
		  	}
    }
	});
	

	jQuery.fn.extend({
	    getMandatoryFields: function (a,i,m) {
			var returnStepsCodeShort=".step()\n";
			var returnStepsCodeLong="";
			$(this).each(function (index, value){

				var splitStr = $(this).text().split(' ');
				for (var i = 0; i < splitStr.length; i++) {
				   splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);     
			   }
			   var newLabelValue=splitStr.join(' ').trim().replace(/[^a-zA-Z^0-9.]+/g, ''); 
			   //console.log(">" + newLabelValue);

			   //console.log($(this).parent().find($("[role*='combobox']:visible")).length);
			   if ($(this).parent().find($("[role*='combobox']:visible")).length>0){
				   returnStepsCodeShort=returnStepsCodeShort + "	.combo(\"myValue\", Fields." + newLabelValue + ")\n";
				   returnStepsCodeLong=returnStepsCodeLong + "pgmp.createRequest.combo.selectValue(selectValue, Fields." + newLabelValue + ".val()); \n"
				   //console.log(".combo(\"myValue\", Fields." + newLabelValue + ")" );
			   }
			   if ($(this).parent().find($(" input:visible:visible")).length>0){
				   var attrElementID=$(this).parent().find($(" input:visible:visible")).attr("id");
				   if(attrElementID.includes('DateTimePicker')){
					   returnStepsCodeShort=returnStepsCodeShort + "	.input(DateTime.addToCurrentDate(noOfDays), Fields." + newLabelValue + ")\n";
					   returnStepsCodeLong=returnStepsCodeLong + "pgmp.createRequest.input.selectDate(DateTime.addToCurrentDate(noOfDays), Fields." + newLabelValue + ".val()); \n"
					   //console.log(".input(DateTime.addToCurrentDate(noOfDays), Fields." + newLabelValue + ")");
				   }
				   if(attrElementID.includes('BluePagesLookupField')){
					   returnStepsCodeShort=returnStepsCodeShort + "	.input(\"BULHAC, MIHAI (Mihai)\, Fields." + newLabelValue + ")\n";
					   returnStepsCodeLong=returnStepsCodeLong + "pgmp.createRequest.input.selectBluePageName(\"BULHAC, MIHAI (Mihai)\", Fields." + newLabelValue + ".val()); \n"
					   //console.log(".input(\"BULHAC, MIHAI (Mihai)\", Fields." + newLabelValue + ")");
				   }
				   if(attrElementID.includes('InputField')){
					   returnStepsCodeShort=returnStepsCodeShort + "	.input(\"test\", Fields." + newLabelValue + ")\n";
					   returnStepsCodeLong=returnStepsCodeLong + "pgmp.createRequest.input.type(\"test\", Fields." + newLabelValue + ".val()); \n"
					   //console.log(".input(\"test\", Fields." + newLabelValue + ")");
				   }
			   }
			   if ($(this).parent().find($(" textarea:visible")).length>0){
				   returnStepsCodeShort=returnStepsCodeShort + "	.textArea(\"test\", Fields." + newLabelValue + ")\n";
				   returnStepsCodeLong=returnStepsCodeLong + "pgmp.createRequest.textArea.type(\"test\", Fields." + newLabelValue + ".val()); \n"
				   //console.log(".textArea(\"test\", Fields." + newLabelValue + ")" );
			   }
			   if ($(this).parent().find($(" select:visible")).length>0){
				   returnStepsCodeShort=returnStepsCodeShort + "	.multiSelect(multiSelectValue, Fields." + newLabelValue + ")\n";
				   returnStepsCodeLong=returnStepsCodeLong + "pgmp.createRequest.combo.selectValue(comboValue, Fields." + newLabelValue + ".val()); \n"
				   //console.log(".multiSelect(multiSelectValue, Fields." + newLabelValue + ")" );
			   }
			   returnStepsCodeShort=returnStepsCodeShort+ "	.submit(\"Submit\",submitValue);\n"
			});

			return  returnStepsCodeShort + "\n\n\n" + returnStepsCodeLong;

	    }
	});
	
});
