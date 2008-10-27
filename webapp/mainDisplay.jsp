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
	
		<script type="text/javascript">
			var dataTable;
			var sidebar = true;
			window.addEvent('domready', function(){
				dataTable = new MooTable('dataTable',
					{width: [202,192,134,96,157], 
					rowDef: ['title','author','pub_date','isbn','pages'],
					contHeight: 500,
					contWidth: 1000
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
					if(json && json.tags) loadTags(json.tags);
				}}).get({'playlist': playlist, 'tags':tags});

			}
			function getQuery(keywords, newPage) {
				if(keywords) {
					var jsonRequest = new Request({url: "/BookTracker/AmazonQuery",  onSuccess: function(text){
						var json = eval("("+text+")");
						if(json) loadQuery(json);
					}}).get({'keyword': keywords});
				}
				else if(newPage == 1){
					var jsonRequest = new Request({url: "/BookTracker/AmazonQuery",  onSuccess: function(text){
						var json = eval("("+text+")");
						if(json) loadQuery(json);
					}}).get({'nextPage': 'true'});
				}
				else if(newPage == -1){
					var jsonRequest = new Request({url: "/BookTracker/AmazonQuery",  onSuccess: function(text){
						var json = eval("("+text+")");
						if(json) loadQuery(json);
					}}).get({'prevPage': 'true'});
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
					<div id="tags">
						<%@ include file="pieces/tagCloud.jsp"%>
					</div>
					<div id="results">
						<%@ include file="pieces/amazonResults.html"%>
					</div>
				</div>
			</div>

		</div>
		<div id="footer">
			<%@ include file="pieces/footer.jsp"%>
		</div>
	</body>
</html>