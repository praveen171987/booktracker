<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BookTracker - Table View</title>
		<link rel="stylesheet" href="scripts/twoColumn.css">
		<link rel="stylesheet" href="scripts/tagCloud.css">
		<link rel="stylesheet" href="scripts/matttable.css">
		<link rel="stylesheet" href="scripts/bookInfo.css">
		<!--<link rel="stylesheet" href="scripts/mootable.css">-->
		<link rel="stylesheet" href="scripts/amazonResults.css">
		<script src="scripts/mootools-core.js"></script>
		<script src="scripts/mootools-more.js"></script>
		<script src="scripts/matttable.js"></script>
		<!--<script src="scripts/mootable.js"></script>-->
		<!--<script src="scripts/sorting_table.js"></script>-->
		<script src="pieces/tagCloud.js"></script>
		<script src="pieces/amazonResults.js"></script>
		<script src="pieces/bookInfo.js"></script>
	
		<script type="text/javascript">
			var dataTable;
			var tagPane;
			var resultsPane;
			var bookInfoPane;
			var navPane;
			var sidebar = true;
			var curWidth;
			var tabs = ['tags','results','bookInfo'];
			window.addEvent('domready', function(){
				tagPane = new TagCloud('tags',{});
				resultsPane = new AmazonResult('results',{});
				bookInfoPane = new BookInfo('bookInfo',{});
				navPane = new NavPlaylists();
				dataTable = new MooTable('dataTable',
					{width: [17,202,192,100,108,33], 
					rowDef: ['   ','title','author','pub_date','isbn','pages'],
					contHeight: 500,
					contWidth: 1000,
					reservedPlaylistNames: ['Library','Unreleased', 'New Playlist']
					});
				$('dataTable').makeResizable({
					handle: $('divider'),
					modifiers: {x: 'width', y: false}
				});
				<%if(session.getAttribute("username") != null){%>
					getData(null,null);
				<%}%>
				window.fireEvent('resize');
			});
			window.addEvent('resize', function(){
				$('main').setStyle('height', window.innerHeight-($('banner').clientHeight+$('footer').clientHeight));
				dataTable.setHeight($('main').getStyle('height'));
				$('right').setStyle('height',$('main').getStyle('height'));
				if(sidebar){
					if(curWidth)
						$('dataTable').setStyle('width', curWidth)
					else
						curWidth = $('content').getStyle('width').toInt()-480
						$('dataTable').setStyle('width',curWidth);
				} else {
					curWidth = $('dataTable').getStyle('width');
					$('dataTable').setStyle('width','100%');
				}
			});

			var taglimits;
			var playlistName = "";
			function getData(playlist, tags) {
				taglimits = tags;
				playlistName = playlist;

				if(playlist == null) playlist = "";
				if(tags == null) tags = "";
				var jsonRequest = new Request({url: "/BookTracker/HandleData",  onSuccess: function(text){
					var json = eval("("+text+")");
					if(json && json.data) dataTable.loadData(json.data);
					if(json && json.tags) tagPane.loadTags(json.tags);
				}}).get({'playlist': playlist, 'tags':tags});

			}
			function getQuery(keywords, newPage) {
				var jsonRequest = new Request({url: "/BookTracker/AmazonQuery",  onSuccess: function(text){
						var json = eval("("+text+")");
						if(json) resultsPane.loadQuery(json);
						showTab(1, true);
						$('amazon').scrollTop = 0;
				}});
				if(keywords) {
					jsonRequest.get({'keyword': keywords});
				}
				else if(newPage == 1){
					jsonRequest.get({'nextPage': 'true'});
				}
				else if(newPage == -1){
					jsonRequest.get({'prevPage': 'true'});
				}
			}
			function submitRequest(data, playlist) {
				var request = new Request({url: "/BookTracker/AddBook",  onSuccess: function(text){
					data.origOrder = dataTable.rowData.length;
					dataTable.rowData[dataTable.rowData.length] = data;
					dataTable._addRow(data);
				}});
				if(playlistName && playlistName != "")
					request.get({'playlist': playlistName, 'isbn':data.isbn});
				else request.get({'isbn':data.isbn});
			}
			function addToPlaylist(isbn, playlist) {
				var request = new Request({url: "/BookTracker/AddBook",  onSuccess: function(text){
					alert('success!');
				}});
				if(playlist && playlist != "")
					request.get({'playlist': playlist, 'isbn':isbn});
			}			
			function showTab(j, really) {
				var i=0;
				sidebar = true;
				while(tabs[i]){
					if(i!=j) $(tabs[i]).setStyle('display','none');
					else if(really) {
						$(tabs[i]).setStyle('display','block');
					}else{
						if($(tabs[i]).getStyle('display')=='block') {
							sidebar = false;
							$(tabs[i]).setStyle('display','none')
						}
						else
							$(tabs[i]).setStyle('display','block');
					}
					i++;
				}
				window.fireEvent('resize');
			}
		</script>
	</head>
	<body>
		<div id="banner">
			<%@ include file="pieces/header.jsp"%>
		</div>
		<div id="main">
			<div id="nav">
				<%@ include file="pieces/leftNav.jsp"%>
			</div>
			<div id="content">
				<table id="dataTable">
					<thead>
						<tr>
							<td class="lock">&nbsp;</td>
							<td>Title</td>
							<td>Author</td>
							<td>Publication Date</td>
							<td>ISBN</td>
							<td>Num Pages</td>
						</tr>
					</thead>
					<tbody/>
				</table>
				<div id="divider"></div>
				<div id="right">
					<div id="tags"></div>
					<div id="results"></div>
					<div id="bookInfo"></div>
				</div>
			</div>

		</div>
		<div id="footer">
			<%@ include file="pieces/footer.jsp"%>
		</div>
	</body>
</html>