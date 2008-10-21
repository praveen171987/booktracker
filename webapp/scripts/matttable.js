var MooTable = new Class({
	Implements: [Options, Events],
	options: {
		width: 100,
		rowDef: false,		//Row object  definition (array)
		contHeight: false,
		contWidth: false
	},
	//class variables;
	divHeaders: [],
	initialize: function(el, options){
		this.setOptions(options);
		this.element = $(el);	//The table
		this.divTHead;
		
		this.mooTable = new Element('div').set('class','mooTable');
		this.divTBody = new Element('div').set('class','tBody');
		this.divTBody.addEvent('scroll', function(ev) {
			this.divTHead.setStyle('left', this.divTBody.scrollLeft*-1);
		}.bind(this));
		this.divTBody.wraps(this.element);
		
		this.numColumns = this.element.tHead.getElements('td').length;
		
		this._createHeader($(this.element.tHead));
		this.mooTable.wraps(this.divTBody);
		this.divTHead.inject(this.divTBody,'before');
		this.divMooCont = new Element('div').set('class','mootableContainer').wraps(this.mooTable);
		if(this.options.contHeight) this.setHeight(this.options.contHeight);
		if(this.options.contWidth) this.divMooCont.setStyle('width',this.options.contWidth);
		
		this.element.getElements('tr').each( function(row){
			row.getElements('td').each(function(cell, ind){
				var html = $(cell).innerHTML;
				$(cell).innerHTML = '';
				new Element('div').setStyle('width',($type(this.options.width) == 'numeric'?this.options.width:this.options.width[ind])).set('html',html).inject($(cell), 'bottom');
			},this);
		},this);
		
		
	},
	_createHeader: function(tHead) {
		var divTHead = new Element('div');
		var headerTr = new Element('div');
		headerTr.set('class','tr');
		divTHead.set('class','tHead');
		resizeLeft = null;
		tHead.getElements('td').each( function(cell, ind, arr){
			var headerTd = new Element('div').set('class','td').set('col',ind).setStyle('width',($type(this.options.width) == 'numeric'?this.options.width:this.options.width[ind]));
			this.divHeaders[ind] = headerTd;
			var divCell = new Element('div').set('class','cell').set('html',cell.innerHTML);
			var divResizeLeft = new Element('div').set('class','resize');
			var divResizeRight = new Element('div').set('class','resize');
			
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
		var i = 0;
		this.element.tBodies[0].set('html','');
		while(data && data[i]){
			this._addRow(data[i]);
			i++;
		}
	},
	_addRow: function(rowObj) {
		var tbody = this.element.tBodies[0];
		var tr = new Element('tr');
		var i=0;
		while(this.options.rowDef[i]){
			new Element('td').set('html', '<div style="width:'+this.divHeaders[i].getStyle('width')+'">'+rowObj[this.options.rowDef[i]]+'</div>').inject(tr,'bottom');
			i++;
		}
		tbody.appendChild(tr);
	},
	setHeight: function(contHeight) {
		this.divMooCont.setStyle('height',contHeight);
		this.divTBody.setStyle('height',contHeight.toInt()-24);
	}
  });
