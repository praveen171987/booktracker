document.onmousemove = mouseMove;
document.onmouseup   = mouseUp;

var MIN_WIDTH = 50;
var dragObject  = null;
var mouseOffset = null;
var divs = new Array();
var previousScroll = 0;
var height;

function start(containerId){
	var i=0;
	height = document.getElementById(containerId).clientHeight;
	while(document.getElementById("cell_0_"+i)){
		var div = document.getElementById("cell_0_"+i);
		var grabber = document.createElement("div");
		grabber.className = "columnGrabber";
		//alert(getElementOffset(div).x);
		grabber.xPos = parseInt(getElementOffset(div).x)+parseInt(div.style.width)+6;//
		grabber.xOffset = parseInt(getElementOffset(div).x);
		//alert(parseInt(getElementOffset(div).x)+" + "+parseInt(div.style.width));
		grabber.style.left = grabber.xPos;
		grabber.style.top = getElementOffset(document.getElementById(containerId)).y;
		grabber.style.height = height;
		grabber.index = i;
		divs[i] = grabber;
		makeDraggable(grabber);
		document.getElementById(containerId).appendChild(grabber);
		
		i++
	}
}

function makeDraggable(item){
	if(!item) return;
	item.onmousedown = function(ev){
		this.origX = this.xPos;
		if(this.index == 0){
			this.minX = this.xOffset+MIN_WIDTH;
		}
		else {
			this.minX = divs[this.index-1].xPos+MIN_WIDTH;
		}
		dragObject  = this;
		mouseOffset = getMouseOffset(this, ev);
		return false;
	}
}
function mouseMove(ev){
	ev           = ev || window.event;
	var mousePos = mouseCoords(ev);

	if(dragObject){

		var newPos = mousePos.x - mouseOffset.x
		//dragObject.style.left = newPos;
		dragObject.xPos = (newPos>dragObject.minX)?newPos:dragObject.minX;
		dragObject.style.left = dragObject.xPos;
		return false;
	}
	var i=0;
	if(previousScroll != parseInt(document.body.scrollLeft)){
		while(divs[i]){
			divs[i].xPos += previousScroll-parseInt(document.body.scrollLeft);
			divs[i].style.left = divs[i].xPos;
			i++
		}
		previousScroll = document.body.scrollLeft;
	}
}
function mouseUp(ev){
	if(dragObject){
		var stringWidth = document.getElementById("cell_0_"+dragObject.index).style.width;
		var width = parseInt(stringWidth.substring(0,stringWidth.length-2));
		var i=0;
		while(document.getElementById("cell_"+i+"_"+dragObject.index)){
			document.getElementById("cell_"+i+"_"+dragObject.index).style.width = width+dragObject.xPos-dragObject.origX;
			i++;
		}
		var i = dragObject.index+1;
		while(divs[i]){
			divs[i].xPos += dragObject.xPos-dragObject.origX;
			divs[i].style.left = divs[i].xPos;
			i++;
		}
	}
	dragObject = null;
}

function mouseCoords(ev){
	if(ev.pageX || ev.pageY){
		return {x:ev.pageX, y:ev.pageY};
	}
	return {
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y:ev.clientY + document.body.scrollTop  - document.body.clientTop
	};
}
function getMouseOffset(target, ev){
	ev = ev || window.event;

	var docPos    = getPosition(target);
	var mousePos  = mouseCoords(ev);
	return {x:mousePos.x - docPos.x, y:mousePos.y - docPos.y};
}
function getPosition(e){
	var left = 0;
	var top  = 0;

	while (e.offsetParent){
		left += e.offsetLeft;
		e     = e.offsetParent;
	}

	left += e.offsetLeft;
	top  += e.offsetTop;

	return {x:left, y:top};
}
function getElementOffset(obj){
	var curleft = curtop = 0;
	if (obj.offsetParent) {
		do {
			curleft += obj.offsetLeft;
			curtop += obj.offsetTop;
		} while (obj = obj.offsetParent);
	}
	return {
		x:curleft,
		y:curtop
		};
}

		
	