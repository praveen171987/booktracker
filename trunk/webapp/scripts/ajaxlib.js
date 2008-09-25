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

function getData(playlist, tags) {
	var http = getHTTPObject();
	if(playlist == null) playlist = "";
	if(tags == null) tags = "";
	http.open("GET", "/BookTracker/HandleData?playlist="+playlist+"&tags="+tags, true); 
	http.onreadystatechange = function() { 
		if (http.readyState == 4) {
			loadData(http.responseText);
		} 
	} 
	http.send(null);
}