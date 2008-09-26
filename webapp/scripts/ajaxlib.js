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

var taglimits;
var playlistName = "";
function getData(playlist, tags) {
	taglimits = tags;
	playlistName = playlist;
	
	var http = getHTTPObject();
	if(playlist == null) playlist = "";
	if(tags == null) tags = "";
	http.open("GET", "/BookTracker/HandleData?playlist="+playlist+"&tags="+tags, true); 
	http.onreadystatechange = function() {
		if (http.readyState == 4) {
			eval(http.responseText);
			if(json && json.data){
				loadData(json.data);
			}
			if(json && json.tags){
				loadTags(json.tags);
			}
		} 
	} 
	http.send(null);
}