 // global variables 
var col = 0;
var parent = null; 
var items = new Array();
var N = 0; 
var author = true;

function get(i) {
	var node = items[i].getElementsByTagName("TD")[col];    
	if(node.childNodes.length == 0) return "";
	var retval = node.firstChild.firstChild.nodeValue;
	if(author){
		var comma = retval.indexOf(",");
		if(comma != -1)
			retval = retval.substring(0,comma);
		var space = retval.lastIndexOf(" ");
		if(space != -1)
			retval = retval.substring(space+1);
	}
	if(retval.substring(0,4) == "The ")
		retval = retval.substring(4);
	
	if(parseInt(retval) == retval) 
		return parseInt(retval);
	
	return retval;
}

function compare(val1, val2, asc){ 
	return (asc) ? val1 < val2 : val1 > val2;
}


function isort(m, k, asc) {
	for(var j=m+k; j < N; j+= k) { 
		for(var i=j; i >= k && compare(get(i), get(i-k), asc); i-= k) { 
			exchange(i, i-k); 
		} 
	} 
} 

function sortColumn(tableid, n, asc) { 
	parent = document.getElementById(tableid); 
	col = n; 
	if(parent.nodeName != "TBODY") 
		parent = parent.getElementsByTagName("TBODY")[0]; 
	if(parent.nodeName != "TBODY") 
		return false; 
	items = parent.getElementsByTagName("TR"); 
	N = items.length; 
	
	// shell sort 
	if((k = Math.floor(N/5)) > 7) { 
		for(var m=0; m < k; m++) 
			isort(m, k, asc); 
	} 
	if((k = Math.floor(N/7)) > 7) { 
		for(var m=0; m < k; m++) 
			isort(m, k, asc); 
	} 
	for(k=7; k > 0; k -= 2) { 
		for(var m=0; m < k; m++) 
			isort(m, k, asc); 
		} 
}
function doSort(tableid, col, isAuthor){
	var parent = document.getElementById(tableid);
	if(isAuthor){
		author = true;
	}else{
		author = false;
	}
	if(!parent || !parent.col){
		parent.col = true;
	} else {
		parent.col = false;
	}
	sortColumn(tableid, col, parent.col);
}

function exchange(i, j) {
	if(i == j+1) { 
		parent.insertBefore(items[i], items[j]); 
	} else if(j == i+1) { 
		parent.insertBefore(items[j], items[i]); 
	} else { 
		var tmpNode = parent.replaceChild(items[i], items[j]); 
		if(typeof(items[i]) == "undefined") { 
			parent.appendChild(tmpNode); 
		} else { 
			parent.insertBefore(tmpNode, items[i]); 
		} 
	} 
}