<html>
	<head>
		<title>BookTracker - Table View</title>
		<link rel="stylesheet" href="scripts/twoColumn.css">
		<script type="text/javascript" src="scripts/sizableColumns.js"></script>
		<script type="text/javascript" src="scripts/tableSort.js"></script>

		<script type="text/javascript">
			function load(){
				start('container');
				var table = document.getElementById("sizable");
				var tableDnD = new TableDnD();
				tableDnD.init(table);
			}
		</script>
	</head>
	<body onLoad="load();">
		<div id="banner">
			<%@ include file="pieces/header.jsp"%>
		</div>
		<div>
			<div id="container">
				<div id="nav">
					<%@ include file="pieces/leftNav.jsp"%>
				</div>
				<jsp:include page="views/tableView.jsp" >
					<jsp:param name="playlist" value="library" />
				</jsp:include>
			</div>
			<div id="right">Matt was Here</div>
		</div>
		<div id="footer">
			<%@ include file="pieces/footer.jsp"%>
		</div>
	</body>
</html>