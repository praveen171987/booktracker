var MooTable = new Class({
	Implements: [Options, Events],
	options: {
		width: 100,
		rowDef: false,		//Row object  definition (array)
		contHeight: false,
		contWidth: false,
		reservedPlaylistNames: []
	},
	//class variables;
	sortIndex: 0,
	rowData: [],
	divHeaders: [],
	sortOrder: null,
	playlistSort: false,
	selectedRow: null,
	playlists: [],
	filter: $lambda(true),
	filteredIsbns: '', //Used for tag queries
	initialize: function(el, options){
		this.setOptions(options);
		this.element = $(el);	//The table
		this.divTHead;
		
		this.mooTable = new Element('div').set('class','mooTable');
		this.divTBody = new Element('div').set('class','tBody');
		this.divTBody.addEvent('scroll', function(ev) {
			this.divTHead.setStyle('left', this.divTBody.scrollLeft*-1);
		}.bind(this));
		window.addEvent('keydown', function(ev) {
			if(this.selectedRow) {
				if(ev.key == "up"){
					if(this.selectedRow.previousSibling)
						this.selectedRow.previousSibling.fireEvent('click');
				}else if(ev.key == "down"){
					if(this.selectedRow.nextSibling)
						this.selectedRow.nextSibling.fireEvent('click');
				}
			}
		}.bind(this));
		this.divTBody.wraps(this.element);
		
		this.numColumns = $(this.element.tHead).getElements('td').length;
		
		this._createHeader($(this.element.tHead));
		this.mooTable.wraps(this.divTBody);
		this.divTHead.inject(this.divTBody,'before');
		
		this.plButton = new Element('input', {type: 'button', value: 'Add to Playlist'}).addEvent('click', function(ev) {
			if(!this.checkSelected()) return;
			this.plDiv.setStyle('display','block');
		}.bind(this));
		
		this.readButton = new Element('input',{type: 'button', value: 'Mark as Read'}).addEvent('click', function(ev) {
			if(!this.checkSelected()) return;
			this.readDiv.setStyle('display','block');
		}.bind(this));
		
		this.buttonDiv = new Element('div').grab(this.readButton).grab(this.plButton).inject(this.divTHead, 'before');
		
		this.plDiv = new Element('div',{'class':'plDiv'}).setStyles({display:'none',
			top:this.plButton.getPosition().y+this.plButton.clientHeight, left: this.plButton.getPosition().x});
		this.plDiv.addEvent('mouseleave', function(ev) {
			this.isbn = null;
			this.setStyle('display','none');
		});
		this.plDiv.inject(document.body, 'top');
		
		this.readDiv = new Element('div', {'class':'plDiv'}).setStyles({display:'none',
			top:this.readButton.getPosition().y+this.readButton.clientHeight, left: this.readButton.getPosition().x});
		this.readDiv.grab(new Element('div').set('html', 'Today').addEvent('click', function(ev) {
			submitRequest('rd',this.selectedIsbns(),{rddate: this.formatCurrentDate()});
		}.bind(this)));
		this.readDiv.grab(new Element('div').set('html', 'The Distant Past').addEvent('click', function(ev) {
			submitRequest('rd',this.selectedIsbns(),{rddate: '9999-12-31'});
		}.bind(this)));
		this.readDiv.grab(new Element('div', {id: 'trigger'}).set('html', 'Other'));
		this.readDiv.addEvent('mouseleave', function(ev) {
			this.setStyle('display','none');
		});
		this.readDiv.inject(document.body,'top');
		
		this.divMooCont = new Element('div').set('class','mootableContainer').set('id',el).wraps(this.mooTable);
		if(this.options.contHeight) this.setHeight(this.options.contHeight);
		if(this.options.contWidth) this.divMooCont.setStyle('width',this.options.contWidth);
		
		this.element.getElements('tr').each( function(row){
			row.getElements('td').each(function(cell, ind){
				var html = $(cell).innerHTML;
				$(cell).innerHTML = '';
				new Element('div').setStyle('width',($type(this.options.width) == 'numeric'?this.options.width:this.options.width[ind])).set('html',html).inject($(cell), 'bottom');
			},this);
		},this);
		this.element.removeProperty('id');
		
	},
	_createHeader: function(tHead) {
		var divTHead = new Element('div');
		var headerTr = new Element('div');
		headerTr.set('class','tr');
		divTHead.set('class','tHead');
		resizeLeft = null;
		tHead.getElements('td').each( function(cell, ind, arr){
			var divCell = new Element('div').set('class','cell').set('html',cell.innerHTML);
			var divResizeLeft = new Element('div').set('class','resize');
			divResizeLeft.addEvent('click',function(el) { return false;}); //Stops a resize click from sorting the column
			var divResizeRight = new Element('div').set('class','resize');
			divResizeRight.addEvent('click',function(el) { return false;}); //Stops a resize click from sorting the column
			
			var headerTd = new Element('div').set('class','td').set('col',ind).setStyle('width',($type(this.options.width) == 'numeric'?this.options.width:this.options.width[ind]));
			
			if(!cell.hasClass('lock')){
				headerTd.addEvent('click', function(ev) {
					if(this.sortIndex != ind) {
						if(this.sortOrder) this.divHeaders[this.sortIndex].removeClass('sort_'+this.sortOrder);
						this.sortOrder = null; //reset sort upon clicking a new column
						
					}
					this.sortIndex = ind;

					if(headerTd.hasClass('sort_asc')) {
						headerTd.removeClass('sort_asc').addClass('sort_desc');
						this.sortOrder = 'desc';
					}else if(headerTd.hasClass('sort_desc')) {
						headerTd.removeClass('sort_desc');
						this.sortOrder = null;
					}else {
						headerTd.addClass('sort_asc');
						this.sortOrder = 'asc';
					}
					this.sort();
				}.bind(this));
			}
			this.divHeaders[ind] = headerTd;

			
			new Drag(headerTd,{
				handle: divResizeRight,
				limit: {y:false},
				modifiers: {x:'width', y:false},
				onComplete: function(el){
					this.element.getElements('tr').each( function(row,ind){
						row.cells[headerTd.get('col')].getElement('div').setStyle('width',headerTd.getStyle('width'));
					});
				}.bind(this),
				onDrag: function(el){
					//headerTr.width
				}
			});
			
			if(resizeLeft){//The previous header is resizable
				headerTd.appendChild(divResizeLeft.setStyle('left','0px'));
				new Drag(resizeLeft,{
				handle: divResizeLeft,
				limit: {y:false},
				modifiers: {x:'width', y:false},
				onComplete: function(el){
					this.element.getElements('tr').each( function(row,ind){
						row.cells[headerTd.get('col')-1].getElement('div').setStyle('width',headerTd.previousSibling.getStyle('width'));
					});
				}.bind(this),
				onDrag: function(el){
					headerTr.width
				}
			});
			}
			resizeLeft = headerTd;

			headerTd.appendChild(divCell);
			if(cell.hasClass('lock')){
				resizeLeft = null;
			}else if(ind == arr.length-1)//Last Element or locked
				headerTd.appendChild(divResizeRight.setStyle('width','4px'));
			else
				headerTd.appendChild(divResizeRight);
				
			headerTr.appendChild(headerTd);
			
		},this);
		divTHead.appendChild(headerTr);
		tHead.destroy();
		this.divTHead = divTHead;
	},
	loadData: function(data){
		this.rowData.empty();
		if(this.sortOrder) this.divHeaders[this.sortIndex].removeClass('sort_'+this.sortOrder); //remove ordering
		this.sortOrder = null;
		var i = 0;
		this.element.tBodies[0].set('html','');
		while(data && data[i]){
			this.rowData[i] = data[i];
			i++;
		}
		this.newFilter($lambda(true));//show everything
	},
	loadPlaylist: function(data) {
		usingFilter = false;
		if(this.sortOrder) this.divHeaders[this.sortIndex].removeClass('sort_'+this.sortOrder); //remove ordering
		this.rowData.each(function(obj, ind, arr) {
			obj.playlistOrder = null;
		}, this);
		this.sortOrder = null
		var i=0;
		var j=0;
		while(data && data[i]) {
			j=0;
			while (this.rowData[j]) {
				if(this.rowData[j].lib_id == data[i].entry_id) {
					this.rowData[j].playlistOrder = i;
				}
				j++;
			}
			i++;
		}
		this.rowData.each(function(row, ind, arr) {
			if(this.rowData[ind].playlistOrder == null) {
				this.rowData[ind].playlistOrder = -1;
			}
		}, this);
		this.playlistSort = true;
		this.filter = function(obj) { return obj.playlistOrder >= 0};
		this.sort();
	},
	loadPlaylists: function(plsts) {
		var newPlaylist = new Element('div').grab(
			new Element('input', {type: 'text', value: 'New Playlist', size: '15', maxlength: '30'}).addEvents({
				'click': function(ev) {
					this.setStyle('color','black');
					this.select();
				},
				'keydown': function(ev) {
					if(ev.key == 'enter') {
						if(this.options.reservedPlaylistNames.extend(this.playlists).indexOf(ev.target.value) != -1){
							var plName = ev.target.value;
							submitRequest("pl",this.selectedIsbns(),{plname:ev.target.value});
							navPane.addPlaylist(plName);
							this._addPlaylist(plName);
							this.plDiv.fireEvent('mouseleave');
						}
						else alert('must be a unique name');
					}
					
				}.bind(this),
				'blur': function(ev) {
					this.set('value','New Playlist');
					this.setStyle('color','');
				}
			})
		).inject(this.plDiv, 'bottom');
		
		this.playlists = plsts;
		plsts.each(function(obj) {
			this._addPlaylist(obj.playlist_name);
		}, this);
	},
	_addPlaylist: function(name) {
		new Element('div').set('html',name).inject(this.plDiv.lastChild, 'before').addEvent('click', function(ev) {
			submitRequest("pl",this.selectedIsbns(),{plname:ev.target.get('html')});
		}.bind(this));
	},
	_addRow: function(rowObj, location) {
		var tbody = this.element.tBodies[0];
		var tr = new Element('tr').addEvent('click', function() {
			if(this.selectedRow && this.selectedRow == tr) {
				if(tr.hasClass('selected')) this.unselectRow(tr);
				else {
					this.selectRow(tr);
					bookInfoPane.showInfo(rowObj);
					showTab(2, true);
				}
			}else {
				if(this.selectedRow) this.unselectRow(this.selectedRow);
				this.selectRow(tr);
				this.selectedRow = tr;
				bookInfoPane.showInfo(rowObj);
				showTab(2, true);
			}
		}.bind(this));
		tr.ind = rowObj.ind;
		
		var i=0;
		while(this.options.rowDef[i]){
			if(i==0){
				new Element('td').setStyle('width',this.divHeaders[i].getStyle('width')).grab(
					new Element('input', {type: 'checkbox'}).addEvent('click', function(ev) {
						ev.stopPropagation();
						if(ev.target.checked) {
							this.selectRow(ev.target.parentNode.parentNode);
						}
						else {
							this.unselectRow(ev.target.parentNode.parentNode);
						}
					}.bind(this))
				).inject(tr,'bottom');
			}else
				new Element('td').set('html', '<div style="width:'+this.divHeaders[i].getStyle('width')+'">'+rowObj[this.options.rowDef[i]]+'</div>').inject(tr,'bottom');
			i++;
		}
		if(location == null)
			tbody.appendChild(tr);
		else {
			tr.inject(tbody.rows[location], 'before');
		}
	},
	newFilter: function(newFilter){
		usingFilter = true;
		this.playlistSort = false;
		if(this.sortOrder) this.divHeaders[this.sortIndex].removeClass('sort_'+this.sortOrder); //remove ordering
		
		this.filter = newFilter;
		this.sort();
	},	
	showRows: function() {
		$(this.element.tBodies[0]).set('html','');
		this.filteredIsbns = '';
		var i = 0;
		while(this.rowData[i]){
			if(this.filter(this.rowData[i])) {
				this.rowData[i].ind = i;
				this.filteredIsbns+= this.rowData[i].isbn+',';
				this._addRow(this.rowData[i]);
			}
			i++;
		}
		if(this.filteredIsbns.length >0) this.filteredIsbns = this.filteredIsbns.substring(0,this.filteredIsbns.length-1);
	},
	setHeight: function(contHeight) {
		this.divMooCont.setStyle('height',contHeight);
		this.divTBody.setStyle('height',contHeight.toInt()-this.divTBody.getStyle('top').toInt());
	},

	sort: function() {
		this.msort(this.rowData, 0, this.rowData.length);
		this.showRows();
	},
	msort: function(arr, begin, end){
		var size = end - begin;
		if(size<2) return;
		var beginRight = begin+Math.floor(size/2);
		this.msort(arr, begin, beginRight);
		this.msort(arr, beginRight, end);
		this.merge(arr, begin, beginRight, end);
	},
	merge: function(arr, begin, beginRight, end) {
		for(;begin<beginRight;++begin) {
			if(this.compare(arr[begin],arr[beginRight])) {
				var temp = arr[begin];
				arr[begin] = arr[beginRight];
				this.insert(arr, beginRight, end, temp);
			}
		}
	},
	insert: function(arr, begin, end, temp){
		while(begin+1 < end && this.compare(temp, arr[begin+1])) {
			this.swap(arr, begin, begin+1);
			begin++;
		}
		arr[begin] = temp;
	},
	swap: function(arr, a,b){
		var temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	},
	compare: function(a, b){
		if(this.sortOrder){
			var valA = a[this.options.rowDef[this.sortIndex]];
			if(valA.indexOf('The ') == 0) valA = valA.substring(4);
			else if(valA.indexOf('A ') == 0) valA = valA.substring(2);
			var valB = b[this.options.rowDef[this.sortIndex]];
			if(valB.indexOf('The ') == 0) valB = valB.substring(4);
			else if(valB.indexOf('A ') == 0) valB = valB.substring(2);
		
			if(this.sortOrder == "asc"){
				return valA > valB;
			}else {
				return valA < valB;
			}
		}else {
			if(this.playlistSort)
					return a.playlistOrder.toInt() > b.playlistOrder.toInt();
			return a.lib_id.toInt() > b.lib_id.toInt();
				
		}
	},
	checkSelected: function() {
		if( !$A(this.element.rows).some( function(item, index, array) {
			return item.cells[0].firstChild.checked;
		})) {
			alert('You must select at least one book.');
			return false;
		}
		return true;
	},
	selectRow: function(tr) {
		tr.addClass('selected');
		tr.cells[0].firstChild.checked = true;
	},
	unselectRow: function(tr) {
		tr.removeClass('selected');
		tr.cells[0].firstChild.checked = false;
	},
	selectedIsbns: function() {
		var isbns = "";
		$A(this.element.rows).each( function(row, ind, array) {
			if(row.cells[0].firstChild.checked) {
				isbns+=this.rowData[row.ind].isbn+",";
				this.unselectRow(row);
			}
		}, this);
		if(isbns.charAt(isbns.length-1) == ",") isbns = isbns.substring(0,isbns.length-1);
		return isbns;
	},
	formatCurrentDate: function() {
		var date = new Date();
		return date.getFullYear()+"-"+(date.getMonth().toInt()+1)+"-"+date.getDate();
	}
  });
