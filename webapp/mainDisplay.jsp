<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BookTracker - Table View</title>
		<link rel="stylesheet" href="scripts/twoColumn.css">
		<link rel="stylesheet" href="scripts/tagCloud.css">
		<link rel="stylesheet" href="scripts/matttable.css">
		<link rel="stylesheet" href="scripts/amazonResults.css">
		<script src="scripts/mootools-core.js"></script>
		<script src="scripts/mootools-more.js"></script>
		<script src="scripts/matttable.js"></script>
		<script src="pieces/tagCloud.js"></script>
		<script src="pieces/amazonResults.js"></script>
		<script src="pieces/bookInfo.js"></script>
	
		<script type="text/javascript">
			var dataTable;
			var tagPane;
			var resultsPane;
			var bookInfoPane;
			var sidebar = true;
			var tabs = ['tags','results','bookInfo'];
			window.addEvent('domready', function(){
				tagPane = new TagCloud('tags',{});
				resultsPane = new AmazonResult('results',{});
				bookInfoPane = new BookInfo('bookInfo',{});
				dataTable = new MooTable('dataTable',
					{width: [202,192,134,96,157], 
					rowDef: ['title','author','pub_date','isbn','pages'],
					contHeight: 500,
					contWidth: 1000,
					rowClick: function() {
						alert(this.data.isbn);
					}
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
					$$('.mootableContainer').setStyle('width',$('content').getStyle('width').toInt()-480);
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
						showSearch();
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
					dataTable._addRow(data);
				}});
				if(playlistName && playlistName != "")
					request.get({'playlist': playlistName, 'isbn':data.isbn});
				else request.get({'isbn':data.isbn});
			}
			function showTab(j) {
				var i=0;
				while(tabs[i]){
					if(i!=j) $(tabs[i]).setStyle('display','none');
					else $(tabs[i]).setStyle('display','block');
					i++;
				}
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
							<td>Title</td>
							<td>Author</td>
							<td>Publication Date</td>
							<td>ISBN</td>
							<td>Num Pages</td>
						</tr>
					</thead>
					<tbody/>
				</table>
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