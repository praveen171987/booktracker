// ===================================================================
//	Author: Matt LeVan
//	
//	My own resizable column implemenation cludged together with Denis
//	Howlett's row reordering code.
//// ===================================================================
//// Author: Denis Howlett <feedback@isocra.com>
//// WWW: http://www.isocra.com/
////
//// NOTICE: You may use this code for any purpose, commercial or
//// private, without any further permission from the author. You may
//// remove this notice from your final code if you wish, however we
//// would appreciate it if at least the web site address is kept.
////
//// You may *NOT* re-distribute this code in any way except through its
//// use. That means, you can include it in your product, or your web
//// site, or any other form where the code is actually being used. You
//// may not put the plain javascript up on your site for download or
//// include it in your javascript libraries for download.
//// If you wish to share this code with others, please just point them
//// to the URL instead.
////
//// Please DO NOT link directly to this .js files from your site. Copy
//// the files to your server and use them there. Thank you.
//// ===================================================================
document.onmousemove = mouseMove;
document.onmouseup   = mouseUp;

var MIN_WIDTH = 50;
var dragObject  = null;
var mouseOffset = null;
var divs = new Array();
var previousScroll = 0;
var height;

var currenttable = null;

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
	
	if (currenttable && currenttable.dragObject) {
        ev   = ev || window.event;
        var mousePos = currenttable.mouseCoords(ev);
        var y = mousePos.y - currenttable.mouseOffset.y;
        if (y != currenttable.oldY) {
            // work out if we're going up or down...
            var movingDown = y > currenttable.oldY;
            // update the old value
            currenttable.oldY = y;
            // update the style to show we're dragging
            currenttable.dragObject.style.backgroundColor = "#eee";
            // If we're over a row then move the dragged row to there so that the user sees the
            // effect dynamically
            var currentRow = currenttable.findDropTargetRow(y);
            if (currentRow) {
                if (movingDown && currenttable.dragObject != currentRow) {
                    currenttable.dragObject.parentNode.insertBefore(currenttable.dragObject, currentRow.nextSibling);
                } else if (! movingDown && currenttable.dragObject != currentRow) {
                    currenttable.dragObject.parentNode.insertBefore(currenttable.dragObject, currentRow);
                }
            }
        }
        return false;
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
	
	if (currenttable && currenttable.dragObject) {
        var droppedRow = currenttable.dragObject;
        // If we have a dragObject, then we need to release it,
        // The row will already have been moved to the right place so we just reset stuff
        droppedRow.style.backgroundColor = 'transparent';
        currenttable.dragObject   = null;
        // And then call the onDrop method in case anyone wants to do any post processing
        currenttable.onDrop(currenttable.table, droppedRow);
        currenttable = null; // let go of the table too
    }
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

function getEventSource(evt) {
    if (window.event) {
        evt = window.event; // For IE
        return evt.srcElement;
    } else {
        return evt.target; // For Firefox
    }
}

function TableDnD() {
    /** Keep hold of the current drag object if any */
    this.dragObject = null;
    /** The current mouse offset */
    this.mouseOffset = null;
    /** The current table */
    this.table = null;
    /** Remember the old value of Y so that we don't do too much processing */
    this.oldY = 0;

    /** Initialise the drag and drop by capturing mouse move events */
    this.init = function(table) {
        this.table = table;
        var rows = table.tBodies[0].rows; //getElementsByTagName("tr")
        for (var i=0; i<rows.length; i++) {
			// John Tarr: added to ignore rows that I've added the NoDnD attribute to (Category and Header rows)
			var nodrag = rows[i].getAttribute("NoDrag")
			alert(nodrag);
			if (nodrag == null || nodrag == "undefined") { //There is no NoDnD attribute on rows I want to drag
				this.makeDraggable(rows[i]);
			}
        }
    }

    /** This function is called when you drop a row, so redefine it in your code
        to do whatever you want, for example use Ajax to update the server */
    this.onDrop = function(table, droppedRow) {
        // Do nothing for now
    }

	/** Get the position of an element by going up the DOM tree and adding up all the offsets */
    this.getPosition = function(e){
        var left = 0;
        var top  = 0;
		/** Safari fix -- thanks to Luis Chato for this! */
		if (e.offsetHeight == 0) {
			/** Safari 2 doesn't correctly grab the offsetTop of a table row
			    this is detailed here:
			    http://jacob.peargrove.com/blog/2006/technical/table-row-offsettop-bug-in-safari/
			    the solution is likewise noted there, grab the offset of a table cell in the row - the firstChild.
			    note that firefox will return a text node as a first child, so designing a more thorough
			    solution may need to take that into account, for now this seems to work in firefox, safari, ie */
			e = e.firstChild; // a table cell
		}

        while (e.offsetParent){
            left += e.offsetLeft;
            top  += e.offsetTop;
            e     = e.offsetParent;
        }

        left += e.offsetLeft;
        top  += e.offsetTop;

        return {x:left, y:top};
    }

	/** Get the mouse coordinates from the event (allowing for browser differences) */
    this.mouseCoords = function(ev){
        if(ev.pageX || ev.pageY){
            return {x:ev.pageX, y:ev.pageY};
        }
        return {
            x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
            y:ev.clientY + document.body.scrollTop  - document.body.clientTop
        };
    }

	/** Given a target element and a mouse event, get the mouse offset from that element.
		To do this we need the element's position and the mouse position */
    this.getMouseOffset = function(target, ev){
        ev = ev || window.event;

        var docPos    = this.getPosition(target);
        var mousePos  = this.mouseCoords(ev);
        return {x:mousePos.x - docPos.x, y:mousePos.y - docPos.y};
    }

	/** Take an item and add an onmousedown method so that we can make it draggable */
    this.makeDraggable = function(item) {
        if(!item) return;
        var self = this; // Keep the context of the TableDnd inside the function
        item.cells[0].onmousedown = function(ev) {
            // Need to check to see if we are an input or not, if we are an input, then
            // return true to allow normal processing
            var target = getEventSource(ev);
            if (target.tagName == 'INPUT' || target.tagName == 'SELECT') return true;
            currenttable = self;
            self.dragObject  = this.parentNode;
            self.mouseOffset = self.getMouseOffset(this.parentNode, ev);
            return false;
        }
        item.cells[0].style.cursor = "move";
    }

    /** We're only worried about the y position really, because we can only move rows up and down */
    this.findDropTargetRow = function(y) {
        var rows = this.table.tBodies[0].rows;
		for (var i=0; i<rows.length; i++) {
			var row = rows[i];
			// John Tarr added to ignore rows that I've added the NoDnD attribute to (Header rows)
			var nodrop = row.getAttribute("NoDrop");
			if (nodrop == null || nodrop == "undefined") {  //There is no NoDnD attribute on rows I want to drag
				var rowY    = this.getPosition(row).y;
				var rowHeight = parseInt(row.offsetHeight)/2;
				if (row.offsetHeight == 0) {
					rowY = this.getPosition(row.firstChild).y;
					rowHeight = parseInt(row.firstChild.offsetHeight)/2;
				}
				// Because we always have to insert before, we need to offset the height a bit
				if ((y > rowY - rowHeight) && (y < (rowY + rowHeight))) {
					// that's the row we're over
					return row;
				}
			}
		}
		return null;
	}
}
	