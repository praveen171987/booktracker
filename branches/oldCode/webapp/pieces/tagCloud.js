var TagCloud = new Class({	
	Implements: [Options, Events],
	options: {
	},
	initialize: function(el, options){
		this.setOptions(options);
		this.limits = new Element('div').set('id','limits');
		this.tagCloud = new Element('div').set('id','tagCloud');
		$(el).appendChild(this.limits);
		$(el).appendChild(this.tagCloud);
		
		window.addEvent('resize', function() {
			this.tagCloud.setStyle('height',this.tagCloud.getParent().getParent().getStyle('height').toInt()-this.limits.clientHeight-2);
		}.bind(this));
	},
	
	loadTags: function(data){
		var i=0;
		this.limits.set('html',"");
		if(taglimits){
			var limit = taglimits.split(",");
			while(limit[i]){
				var a = new Element("a").set('html',limit[i]).set('ind',i);
				a.addEvent('click', function() {
					var newlimit = "";
					var i=0;
					while(limit[i]){
						if(i != this.get('ind')){
							newlimit += limit[i]+",";
						}
						i++;
					}
					newlimit = newlimit.substring(0,newlimit.length-1);
					getData(playlistName,newlimit, false);
				});
				this.limits.appendChild(a);
				i++;
			}
		}
		
		i=0;
		var max=0;
		var min=1;
		var spread = 6;
		while(data && data[i]){
			max = (max>data[i].num.toInt())?max:data[i].num.toInt();
			min = (min<data[i].num.toInt())?min:data[i].num.toInt();
			i++;
		}
		spread = (spread>(max-min))?(max-min):spread;
		if(spread==0)spread = 1;
		i=0;
		this.tagCloud.set('html',"");
		while(data && data[i]){
			var classNum = Math.floor(data[i].num*spread/(max-(min-1)));
			var span = new Element("span").addClass("tag"+classNum).set('html',data[i].tag);
			span.addEvent('click',function() {
				var i=0;
				if(taglimits) {
					var limits = taglimits.split(",");
					while(limits[i]){
						if(this.textContent == limits[i]) return;
						i++;
					}
					getData(playlistName,taglimits+","+this.textContent, false);
				}
				else getData(playlistName,this.textContent, false);
			});
			this.tagCloud.appendChild(span);
			i++;
		}
		window.fireEvent('resize');
	}
});