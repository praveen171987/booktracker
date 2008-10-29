<script type="text/javascript">
var TagCloud = new Class({	
	Implements: [Options, Events],
	options: {
		width: 100,
		rowDef: false,		//Row object  definition (array)
		contHeight: false,
		contWidth: false,
		rowClick: $lambda(false)
	},
	initialize: function(el, options){
		this.setOptions(options);
		var limits = new Element('div');
		var tagCloud = new Element('div');
		el.appendChild(limits).appendChild(tagCloud);
		
		window.addEvent('resize', function() {
			$('tagCloud').setStyle('height',$('tagCloud').getParent().getParent().getStyle('height').toInt()-$('limits').clientHeight);
		});
	},
	
	function loadTags(data){
		var i=0;
		
		$("limits").innerHTML = "";
		if(taglimits){
			var limits = taglimits.split(",");
			while(limits[i]){
				var a = new Element("a").set('html',limits[i]).set('ind',i);
				a.addEvent('click', function() {
					var newLimits = "";
					var i=0;
					while(limits[i]){
						if(i != this.get('ind')){
							newLimits += limits[i]+",";
						}
						i++;
					}
					newLimits = newLimits.substring(0,newLimits.length-1);
					getData(playlistName,newLimits);
				});
				$("limits").appendChild(a);
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
		$("tagCloud").innerHTML = "";
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
					getData(playlistName,taglimits+","+this.textContent);
				}
				else getData(playlistName,this.textContent);
			});
			$("tagCloud").appendChild(span);
			i++;
		}
		window.fireEvent('resize');
	}
});
</script>
<!--
<div id="limits"></div>
<div id="tagCloud"></div>
-->