<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.CallableStatement"%>
<script type="text/javascript">
	function loadTags(data){
		var i=0;
		
		document.getElementById("limits").innerHTML = "";
		if(taglimits){
			var limits = taglimits.split(",");
			while(limits[i]){
				var a = document.createElement("a");
				a.innerHTML = limits[i];
				a.index = i;
				a.onclick = function() {
					var newLimits = "";
					var i=0;
					while(limits[i]){
						if(i != this.index){
							newLimits += limits[i]+",";
						}
						i++;
					}
					newLimits = newLimits.substring(0,newLimits.length-1);
					getData(playlistName,newLimits);
				};
				document.getElementById("limits").appendChild(a);
				i++;
			}
		}
		
		i=0;
		var max=0;
		var min=1;
		var spread = 6;
		while(data && data[i]){
			max = (max>data[i].num)?max:data[i].num;
			min = (min<data[i].num)?min:data[i].num;
			i++;
		}
		spread = (spread>(max-min))?(max-min):spread;
		if(spread==0)spread = 1;
		i=0;
		document.getElementById("tagCloud").innerHTML = "";
		while(data && data[i]){
			var classNum = Math.floor(data[i].num*spread/(max-(min-1)));
			var span = document.createElement("span");
			span.className = "tag"+classNum;
			span.innerHTML = data[i].tag;
			span.onclick = function() {
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
			};
			document.getElementById("tagCloud").appendChild(span);
			i++;
		}
	}
</script>
<div id="limits"></div>
<div id="tagCloud"></div>