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
window.onresize = resizeContainer;

var MIN_WIDTH = 50;
var dragColumn  = null;
var mouseOffset = null;
var divs = new Array();
var height;

var currenttable = null;

function start(containerId){
	var i=0;
	height = document.getElementById(containerId).clientHeight-2;
	while(document.getElementById("sizable").rows[0].cells[i+1]){
		var div = document.getElementById("sizable").rows[0].cells[i+1].firstChild;
		var colDivider = document.createElement("div");
			colDivider.className = "columnDivider";
			colDivider.xPos = parseInt(getElementOffset(div).x)+parseInt(div.style.width)+6;//
			colDivider.xOffset = parseInt(getElementOffset(div).x);
			colDivider.style.left = colDivider.xPos;
			colDivider.style.top = getElementOffset(document.getElementById(containerId)).y+1;
			colDivider.style.height = height;
			colDivider.index = i;
		divs[i] = colDivider;
		makeDraggable(colDivider);
		document.getElementById(containerId).appendChild(colDivider);
		
		i++
	}
	resizeContainer();
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
		dragColumn  = this;
		mouseOffset = getMouseOffset(this, ev);
		return false;
	}
}
function mouseMove(ev){
	ev           = ev || window.event;
	var mousePos = mouseCoords(ev);

	if(dragColumn){
		var newPos = mousePos.x - mouseOffset.x
		dragColumn.xPos = (newPos>dragColumn.minX)?newPos:dragColumn.minX;
		dragColumn.style.left = dragColumn.xPos;
		return false;
	}
	
	if (currenttable && currenttable.dragRow) {

        var y = mousePos.y - currenttable.mouseOffset.y;
		var x = mousePos.x - currenttable.mouseOffset.x;
//		if (x != currenttable.oldX) {
//			if(!currenttable.playlistDragRow){
//				var obj = document.createElement("div");
//				var text = document.createElement("p");
//				text.innerHTML = "Matt was here"; 
//				obj.appendChild(text);
//				obj.style.position = "absolute";
	//			obj.style.display = "block";
	//			currenttable.playlistDragRow = obj;
	//			document.body.appendChild(obj);
	//		}
//
//			currenttable.playlistDragRow.style.top = mousePos.y;
//			currenttable.playlistDragRow.style.left = mousePos.x;
//
//			currenttable.oldX = x;
//			currenttable.dragToPlaylist = x < 170;
//		}
		//VERTICAL DRAGGING
        if (y != currenttable.oldY && !currenttable.dragToPlaylist) {
            // work out if we're going up or down...
            var movingDown = y > currenttable.oldY;
            // update the old value
            currenttable.oldY = y;
            // update the style to show we're dragging
            currenttable.dragRow.style.backgroundColor = "#eee";
            // If we're over a row then move the dragged row to there so that the user sees the
            // effect dynamically
            var currentRow = currenttable.findDropTargetRow(y);
            if (currentRow) {
                if (movingDown && currenttable.dragRow != currentRow) {
                    currenttable.dragRow.parentNode.insertBefore(currenttable.dragRow, currentRow.nextSibling);
                } else if (! movingDown && currenttable.dragRow != currentRow) {
                    currenttable.dragRow.parentNode.insertBefore(currenttable.dragRow, currentRow);
                }
            }
        }
        return false;
    }
}
function mouseUp(ev){
	if(dragColumn){
		var stringWidth = document.getElementById("sizable").rows[0].cells[dragColumn.index+1].firstChild.style.width;
		var width = parseInt(stringWidth.substring(0,stringWidth.length-2));
		var i=0;
		while(document.getElementById("sizable").rows[i]){
			document.getElementById("sizable").rows[i].cells[dragColumn.index+1].firstChild.style.width = width+dragColumn.xPos-dragColumn.origX;
			i++;
		}
		var i = dragColumn.index+1;
		while(divs[i]){
			divs[i].xPos += dragColumn.xPos-dragColumn.origX;
			divs[i].style.left = divs[i].xPos;
			i++;
		}
	}
	dragColumn = null;
//	if(currenttable && currenttable.playlistDragRow) {
//		ev   = ev || window.event;
//        var mousePos = currenttable.mouseCoords(ev);
//		document.body.removeChild(currenttable.playlistDragRow);
//		currenttable.playlistDragRow = null;
//		var playlists = document.getElementById("playlists");
//		if(playlists){
//			for(var i=0;i<playlists.rows.length;i++){
//				var rowPos = getElementOffset(playlists.rows[i]);
//				if(mousePos.x > rowPos.x && mousePos.x < rowPos.x+playlists.rows[i].clientWidth){
//					if(mousePos.y > rowPos.y && mousePos.y < rowPos.y+playlists.rows[i].clientHeight){
//						alert("added to "+playlists.rows[i].innerHTML);
//					}
//				}
//			}
//		}
//	}
	if (currenttable && currenttable.dragRow) {
        var droppedRow = currenttable.dragRow;
        // If we have a dragColumn, then we need to release it,
        // The row will already have been moved to the right place so we just reset stuff
        droppedRow.style.backgroundColor = 'transparent';
        currenttable.dragRow   = null;
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

function resizeContainer() {
	var winW;
	var winH;
	 if (navigator.appName=="Netscape") {
	  winW = window.innerWidth;
	  winH = window.innerHeight;
	 }
	 if (navigator.appName.indexOf("Microsoft")!=-1) {
	  winW = document.body.offsetWidth;
	  winH = document.body.offsetHeight;
	 }
	 document.getElementById("container").style.height = winH-147;
	 var i=0;
	 while(divs[i]){
		divs[i].style.height = winH-147;
		i++;
	 }
}

function TableDnD() {
    /** Keep hold of the current drag object if any */
    this.dragRow = null;
    /** The current mouse offset */
    this.mouseOffset = null;
    /** The current table */
    this.table = null;
    /** Remember the old value of Y so that we don't do too much processing */
    this.oldY = 0;
	this.oldX = 0;
	this.dragToPlaylist = false;
	
    /** Initialise the drag and drop by capturing mouse move events */
    this.init = function(table) {
        this.table = table;
        var rows = table.tBodies[0].rows; //getElementsByTagName("tr")
        for (var i=0; i<rows.length; i++) {
			// John Tarr: added to ignore rows that I've added the NoDnD attribute to (Category and Header rows)
			var nodrag = rows[i].getAttribute("NoDrag")
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
            self.dragRow  = this.parentNode;
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
	