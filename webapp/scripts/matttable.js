var MooTable = new Class({
	Implements: [Options, Events],
	options: {
		width: 100,
		rowDef: false,		//Row object  definition (array)
		contHeight: false,
		contWidth: false,
		rowClick: $lambda(false)
	},
	//class variables;
	sortIndex: 0,
	rowData: [],
	divHeaders: [],
	sortOrder: null,
	selectedRow: null,
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
		
		this.numColumns = this.element.tHead.getElements('td').length;
		
		this._createHeader($(this.element.tHead));
		this.mooTable.wraps(this.divTBody);
		this.divTHead.inject(this.divTBody,'before');
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
		
		this.sortables = null;
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
			if(ind == arr.length-1)//Last Element
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
			this.rowData[i].origOrder = i;
			this._addRow(data[i]);
			i++;
		}
	},
	_addRow: function(rowObj) {
		var tbody = this.element.tBodies[0];
		var tr = new Element('tr').addEvent('click', function() {
			if(this.selectedRow && this.selectedRow == tr) {
				if(tr.toggleClass('selected').hasClass('selected')) bookInfoPane.showInfo(rowObj);;
			}else {
				if(this.selectedRow) this.selectedRow.removeClass('selected');
				tr.addClass('selected');
				this.selectedRow = tr;
				bookInfoPane.showInfo(rowObj);
				showTab(2, true);
			}
		}.bind(this));
		var i=0;
		while(this.options.rowDef[i]){
			new Element('td').set('html', '<div style="width:'+this.divHeaders[i].getStyle('width')+'">'+rowObj[this.options.rowDef[i]]+'</div>').inject(tr,'bottom');
			i++;
		}
		tbody.appendChild(tr);
		if(!this.sortables) {
			this.sortables = new Sortables(tbody, {clone: true});
			this.sortables.addEvent('complete',function(elem) {
				alert('hi');
			});
		}
		else this.sortables.addItems(tr);
	},
	showRows: function() {
		this.element.tBodies[0].set('html','');
		var i = 0;
		while(this.rowData[i]){
			this._addRow(this.rowData[i]);
			i++;
		}
	},
	setHeight: function(contHeight) {
		this.divMooCont.setStyle('height',contHeight);
		this.divTBody.setStyle('height',contHeight.toInt()-24);
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
		var valA = a[this.options.rowDef[this.sortIndex]];
		if(valA.indexOf('The ') == 0) valA = valA.substring(4);
		else if(valA.indexOf('A ') == 0) valA = valA.substring(2);
		var valB = b[this.options.rowDef[this.sortIndex]];
		if(valB.indexOf('The ') == 0) valB = valB.substring(4);
		else if(valB.indexOf('A ') == 0) valB = valB.substring(2);
		if(this.sortOrder){
			if(this.sortOrder == "asc"){
				return valA > valB;
			}else {
				return valA < valB;
			}
		}else {
			return a.origOrder > b.origOrder;
		}
	}
  });
