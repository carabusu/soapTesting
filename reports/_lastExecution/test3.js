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
	    getMandatoryFields: function (extractionType, fieldsToExtract) {
			var returnStepsCodeShortFull=".step()\n";
			var returnStepsCodeShortEmpty=".step()\n";
			var returnStepsCodeLongFull="";
			var returnStepsCodeLongEmpty="";
			$(this).each(function (index, value){

				var splitStr = $(this).text().split(' ');
				for (var i = 0; i < splitStr.length; i++) {
				   splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);     
			   }
			   var newLabelValue=splitStr.join(' ').trim().replace(/[^a-zA-Z^0-9.]+/g, ''); 
			   //console.log(">" + newLabelValue);

			   //console.log($(this).parent().find($("[role*='combobox']:visible")).length);
			   if ($(this).parent().find($("[id*='SelectField']:visible")).length>0){
				   returnStepsCodeShortFull=returnStepsCodeShortFull + "	.combo(\"myValue\", Fields." + newLabelValue + ")\n";
				   returnStepsCodeLongFull=returnStepsCodeLongFull + "pgmp.createRequest.combo.selectValue(selectValue, Fields." + newLabelValue + ".val()); \n"
				   var newElementValue=$(this).parent().find($("[role*='combobox']:visible")).text().trim().replace(/[^a-zA-Z^0-9.]+/g, ''); 
				   if(newElementValue.length<1) {
						returnStepsCodeShortEmpty=returnStepsCodeShortEmpty + "	.combo(\"myValue\", Fields." + newLabelValue + ")\n";
						returnStepsCodeLongEmpty=returnStepsCodeLongEmpty + "pgmp.createRequest.combo.selectValue(selectValue, Fields." + newLabelValue + ".val()); \n"
				   }
				   //console.log(".combo(\"myValue\", Fields." + newLabelValue + ")" );
			   }
			   if ($(this).parent().find($(" input:visible")).length>0){
				   var attrElementID=$(this).parent().find($(" input:visible")).attr("id");
				   if(attrElementID.includes('DateTimePicker')){
					   returnStepsCodeShortFull=returnStepsCodeShortFull + "	.input(DateTime.addToCurrentDate(noOfDays), Fields." + newLabelValue + ")\n";
					   returnStepsCodeLongFull=returnStepsCodeLongFull + "pgmp.createRequest.input.selectDate(DateTime.addToCurrentDate(noOfDays), Fields." + newLabelValue + ".val()); \n";
					   var newElementValue=$(this).parent().find($(" input:visible")).attr("aria-valuenow").trim().replace(/[^a-zA-Z^0-9.]+/g, ''); 
					   if(newElementValue.includes("null")) {
							returnStepsCodeShortEmpty=returnStepsCodeShortEmpty  + "	.input(DateTime.addToCurrentDate(noOfDays), Fields." + newLabelValue + ")\n";
							returnStepsCodeLongEmpty=returnStepsCodeLongEmpty  + "pgmp.createRequest.input.selectDate(DateTime.addToCurrentDate(noOfDays), Fields." + newLabelValue + ".val()); \n";
					   }
					   //console.log(".input(DateTime.addToCurrentDate(noOfDays), Fields." + newLabelValue + ")");
				   }
				   if(attrElementID.includes('BluePagesLookupField')){
					   returnStepsCodeShortFull=returnStepsCodeShortFull + "	.input(\"BULHAC, MIHAI (Mihai)\, Fields." + newLabelValue + ")\n";
					   returnStepsCodeLongFull=returnStepsCodeLongFull + "pgmp.createRequest.input.selectBluePageName(\"BULHAC, MIHAI (Mihai)\", Fields." + newLabelValue + ".val()); \n";
					    var newElementValue=$(this).parent().find($(" input:visible")).attr("value").trim().replace(/[^a-zA-Z^0-9.]+/g, ''); 
						console.log("length: " + newElementValue + "-" + newElementValue.length)
					   if(newElementValue.length<1) {
							returnStepsCodeShortEmpty=returnStepsCodeShortEmpty   + "	.input(\"BULHAC, MIHAI (Mihai)\, Fields." + newLabelValue + ")\n";
							returnStepsCodeLongEmpty=returnStepsCodeLongEmpty  + "pgmp.createRequest.input.selectBluePageName(\"BULHAC, MIHAI (Mihai)\", Fields." + newLabelValue + ".val()); \n";
					   }
					   //console.log(".input(\"BULHAC, MIHAI (Mihai)\", Fields." + newLabelValue + ")");
				   }
				   if(attrElementID.includes('InputField')){
					   returnStepsCodeShortFull=returnStepsCodeShortFull + "	.input(\"test\", Fields." + newLabelValue + ")\n";
					   returnStepsCodeLongFull=returnStepsCodeLongFull + "pgmp.createRequest.input.type(\"test\", Fields." + newLabelValue + ".val()); \n"
					    var newElementValue=$(this).parent().find($(" input:visible")).attr("value").trim().replace(/[^a-zA-Z^0-9.]+/g, ''); 
					   if(newElementValue.length<1) {
							returnStepsCodeShortEmpty=returnStepsCodeShortEmpty   + "	.input(\"test\", Fields." + newLabelValue + ")\n";
							returnStepsCodeLongEmpty=returnStepsCodeLongEmpty  + "pgmp.createRequest.input.type(\"test\", Fields." + newLabelValue + ".val()); \n";
					   }
					   //console.log(".input(\"test\", Fields." + newLabelValue + ")");
				   }
			   }
			   if ($(this).parent().find($(" textarea:visible")).length>0){
				   returnStepsCodeShortFull=returnStepsCodeShortFull + "	.textArea(\"test\", Fields." + newLabelValue + ")\n";
				   returnStepsCodeLongFull=returnStepsCodeLongFull + "pgmp.createRequest.textArea.type(\"test\", Fields." + newLabelValue + ".val()); \n";
				   var newElementValue=$(this).parent().find($(" textarea:visible")).text().trim().replace(/[^a-zA-Z^0-9.]+/g, ''); 
				   if(newElementValue.length<1) {
							returnStepsCodeShortEmpty=returnStepsCodeShortEmpty  + "	.textArea(\"test\", Fields." + newLabelValue + ")\n";
							returnStepsCodeLongEmpty=returnStepsCodeLongEmpty  + "pgmp.createRequest.textArea.type(\"test\", Fields." + newLabelValue + ".val()); \n";
					   }
				   //console.log(".textArea(\"test\", Fields." + newLabelValue + ")" );
			   }
			   if ($(this).parent().find($(" select:visible")).length>0){
				   returnStepsCodeShortFull=returnStepsCodeShortFull + "	.multiSelect(multiSelectValue, Fields." + newLabelValue + ")\n";
				   returnStepsCodeLongFull=returnStepsCodeLongFull + "pgmp.createRequest.combo.selectValue(comboValue, Fields." + newLabelValue + ".val()); \n";
				   var newElementValue=$(this).parent().find($(" select:visible")).text().trim().replace(/[^a-zA-Z^0-9.]+/g, ''); 
				   if(newElementValue.length<1) {
							returnStepsCodeShortEmpty=returnStepsCodeShortEmpty  + "	.multiSelect(multiSelectValue, Fields." + newLabelValue + ")\n";
							returnStepsCodeLongEmpty=returnStepsCodeLongEmpty  + "pgmp.createRequest.combo.selectValue(comboValue, Fields." + newLabelValue + ".val()); \n";
					}
				   //console.log(".multiSelect(multiSelectValue, Fields." + newLabelValue + ")" );
			   }

			});
			returnStepsCodeShortFull=returnStepsCodeShortFull+ "	.submit(\"Submit\",submitValue);\n";
			returnStepsCodeShortEmpty=returnStepsCodeShortEmpty+ "	.submit(\"Submit\",submitValue);\n";
			if (extractionType.includes("short")){
				if(fieldsToExtract.includes("full"))
					return returnStepsCodeShortFull;
				if(fieldsToExtract.includes("empty"))
					return returnStepsCodeShortEmpty;
			}	
			if (extractionType.includes("long")){
				if(fieldsToExtract.includes("full"))
					return returnStepsCodeLongFull;
				if(fieldsToExtract.includes("empty"))
					return returnStepsCodeLongEmpty;
			}

			return  0;

	    }
	});	
	
	
});
