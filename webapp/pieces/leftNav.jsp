<script type="text/javascript">
var playlists = [];
var NavPlaylists = new Class({
	Implements: [Options, Events],
	options: {
	},
	//class variables;
	initialize: function(el, options){
		var i = 0;
		var navDrag;
		var jsonRequest = new Request({url: "/BookTracker/HandlePlaylists",  onSuccess: function(text){
			var json = eval("("+text+")");
			//alert('success');
			if(json && json.playlists) {
				dataTable.loadPlaylists(json.playlists);
				while(json.playlists[i]) {
					var temp = json.playlists[i].playlist_name;
					this.addPlaylist(temp);
					i++;
				}
			}
		}.bind(this)}).get();
	},
	addPlaylist: function(name) {
		$('plNav').grab(new Element('li').set('html',name).addClass('playlist').addEvent('click', function() {
			getData(name, null);
		}), 'bottom');
	}
});		
</script>
<ul id="plNav">
	<li class="playlist" onClick="getData(null,null)"><img src="images/lib.gif">Library</li>
	<li class="playlist" onClick="getData('unreleased',null)"><img src="images/lib.gif">Unreleased</li>
</ul>