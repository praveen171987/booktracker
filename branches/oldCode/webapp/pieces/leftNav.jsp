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
	},
	addPlaylist: function(name) {
		$('plNav').grab(new Element('li').set('html',name).addClass('playlist').addEvent('click', function() {
			getData(name, null, false);
		}), 'bottom');
	},
	loadPlaylists: function(playlists) {
		//TODO: remove explicit reference to MattTable instance (datatable)
		dataTable.loadPlaylists(playlists);
		var i = 0;
		while(playlists[i]) {
			var temp = playlists[i].playlist_name;
			this.addPlaylist(temp);
			i++;
		}
	}
});		
</script>
<ul id="plNav">
	<li class="playlist" onClick="dataTable.newFilter($lambda(true))"><img src="images/lib.gif">Library</li>
	<li class="playlist" onClick="dataTable.newFilter(function(obj) {return obj.pub_date > '2008-11-13'  })"><img src="images/lib.gif">Unreleased</li>
	<li class="playlist" onClick="dataTable.newFilter(function(obj) {return (obj.date_finished == '')})"><img src="images/lib.gif">Unread</li>
	<li class="playlist" onClick="dataTable.newFilter(function(obj) {return obj.date_started != '' && obj.date_finished == ''  })"><img src="images/lib.gif">In Progress</li>
</ul>