function getHTTPObject() { 
	if (typeof XMLHttpRequest != 'undefined') { 
		return new XMLHttpRequest(); 
	}
	try { 
		return new ActiveXObject("Msxml2.XMLHTTP"); 
	} catch (e) { 
		try { 
			return new ActiveXObject("Microsoft.XMLHTTP"); 
		} catch (e) {} 
	} 
	return false; 
}

function tryRequest() {
	var http = getHTTPObject(); 
	http.open("GET", "/BookTracker/HandleData?playlist=read", true); 
	http.onreadystatechange = function() { 
		if (http.readyState == 4) {
			eval(http.responseText);
			var i=0;
			while(json.data && json.data[i]){
				alert(json.data[i].title);
				i++;
			}
		} 
	} 
	http.send(null);
}