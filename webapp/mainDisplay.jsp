<html>
	<head>
		<title>BookTracker - Table View</title>
		<link rel="stylesheet" href="scripts/twoColumn.css">
		<link rel="stylesheet" href="scripts/tagCloud.css">
		<link rel="stylesheet" href="scripts/matttable.css">
		<script src="scripts/mootools-core.js"></script>
		<script src="scripts/mootools-more.js"></script>
		<script src="scripts/matttable.js"></script>
	
		<script type="text/javascript">
			var dataTable;
			window.addEvent('domready', function(){
				dataTable = new MooTable('dataTable',{width: [202,192,134,96,157], rowDef: ['title','author','pub_date','isbn','pages']});
				<%if(session.getAttribute("username") != null){%>
					getData(null,null);
				<%}%>
			});
			function getHTTPObject() { 
				if (typeof XMLHttpRequest != 'undefined') { 
					return new XMLHttpRequest(); 
				}
				try { 
					return new ActiveXObject("Msxml2.XMLHTTP"); 
				} catch (e) { 
					try { 
						return new ActiveXObject("Microsoft.XMLHTTP"); 
					} catch (e) {} 
				} 
				return false; 
			}

			var taglimits;
			var playlistName = "";
			function getData(playlist, tags) {
				taglimits = tags;
				playlistName = playlist;
				
				var http = getHTTPObject();
				if(playlist == null) playlist = "";
				if(tags == null) tags = "";
				http.open("GET", "/BookTracker/HandleData?playlist="+playlist+"&tags="+tags, true); 
				http.onreadystatechange = function() {
					if (http.readyState == 4) {
						eval(http.responseText);
						if(json && json.data){
							dataTable.loadData(json.data);
						}
						if(json && json.tags){
							loadTags(json.tags);
						}
					} 
				} 
				http.send(null);
}
		</script>
	</head>
	<body>
		<div id="banner">
			<%@ include file="pieces/header.jsp"%>
		</div>
		<div>
			<div id="container">
				<div id="nav">
					<%@ include file="pieces/leftNav.jsp"%>
				</div>
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
			</div>
			<div id="right">
				<%@ include file="pieces/tagCloud.jsp"%>
			</div>
		</div>
		<div id="footer">
			<%@ include file="pieces/footer.jsp"%>
		</div>
	</body>
</html>