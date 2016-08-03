$.expr[':'].textEquals = function(a, i, m) {  
    return $(a).text().match("^" + m[3] + "$");  
};});
