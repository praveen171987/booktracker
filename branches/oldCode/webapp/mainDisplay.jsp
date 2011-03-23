<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BookTracker - Table View</title>
		<link rel="stylesheet" href="scripts/twoColumn.css">
		<link rel="stylesheet" href="scripts/tagCloud.css">
		<link rel="stylesheet" href="scripts/matttable.css">
		<link rel="stylesheet" href="scripts/bookInfo.css">
		<link rel="stylesheet" href="scripts/amazonResults.css">
		
		<script src="scripts/mootools-core.js"></script>
		<script src="scripts/mootools-more.js"></script>
		<script src="scripts/matttable.js"></script>
		<script src="pieces/tagCloud.js"></script>
		<script src="pieces/amazonResults.js"></script>
		<script src="pieces/bookInfo.js"></script>
	
		<!-- Calendar Dependencies-->
		<!-- Loading Theme file(s) -->
	    <link rel="stylesheet" href="scripts/win2k.css" />
		<!-- Loading Calendar JavaScript files -->
	    <script type="text/javascript" src="scripts/zapatec.js"></script>
	    <script type="text/javascript" src="scripts/calendar.js"></script>
		<!-- Loading language definition file -->
	    <script type="text/javascript" src="scripts/calendar-en.js"></script>
		<script type="text/javascript">
			var dataTable;
			var tagPane;
			var resultsPane;
			var bookInfoPane;
			var navPane;
			var sidebar = true;
			var curWidth;
			var tabs = ['tags','results','bookInfo'];
			var taglimits;
			var playlistName = "";
			var usingFilter = true; //false means using playlist
			window.addEvent('domready', function(){
				tagPane = new TagCloud('tags',{});
				resultsPane = new AmazonResult('results',{});
				bookInfoPane = new BookInfo('bookInfo',{});
				navPane = new NavPlaylists();
				//debugger;
				dataTable = new MooTable('dataTable',
					{width: [17,291,192,100,108,33], 
					rowDef: ['   ','title','author','pub_date','isbn','pages'],
					contHeight: 500,
					contWidth: 1000,
					reservedPlaylistNames: ['Library','Unreleased', 'New Playlist']
					});
				$('dataTable').makeResizable({
					handle: $('divider'),
					modifiers: {x: 'width', y: false},
					onComplete: function() {
						curWidth = $('dataTable').getStyle('width').toInt();
					}
				});
				<%if(session.getAttribute("username") != null){%>
					getData(null,null, true);
				<%}%>
				
			    Zapatec.Calendar.setup({
			        weekNumbers       : false,
			        showOthers        : false,
			        step              : 1,
			        electric          : false,
			        inputField        : "calendar",
			        button            : "trigger",
			        ifFormat          : "%Y-%m-%d",
			        daFormat          : "%Y/%m/%d"
			    });
				
				window.fireEvent('resize');
			});
			
			window.addEvent('resize', function(){
				$('main').setStyle('height', window.innerHeight-($('banner').clientHeight+$('footer').clientHeight));
				if(dataTable)
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
			
			function getData(playlist, tags, getPlaylistNames) {
				taglimits = tags;
				playlistName = playlist;

				if(playlist == null) playlist = "";
				if(tags == null) tags = "";
				
				var src = "bt";
				if(getPlaylistNames) src += 'p';
				
				var jsonRequest = new Request({url: "/BookTracker/HandleData",  onSuccess: function(text){
					var json = eval("("+text+")");
					if(!playlist){
						if(json && json.data) dataTable.loadData(json.data);
					}
					else dataTable.loadPlaylist(json.data);
					if(json && json.tags) tagPane.loadTags(json.tags);
					if(json && json.playlists) navPane.loadPlaylists(json.playlists);
				}}).get({'src': src,'plname': playlist, 'reqtags':tags});

			}
			function getQuery(keywords, newPage) {
				//TODO: Refactor this code into the amazonResults class, pass in a text field and trigger button that are set in the init
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
			function submitRequest(method, isbns, params) {
				var request = new Request({url: "/BookTracker/HandleData",  onSuccess: function(text){
					alert('success: '+text);
					if(params.amazonData){
						params.amazonData.lib_id = text;
						params.amazonData.ind = dataTable.rowData.length;
						dataTable.rowData[dataTable.rowData.length] = params.amazonData;
					}
					if(params.rdDate){
						dataTable.rowData.each(function(cell, ind, arr) {
							if(isbns.contains(cell.isbn)) {
								cell.date_finished = params.rdDate;
								if(paramds.rdDate == '9999-12-31')
									cell.date_started = params.rdDate;
							}
						},this);
					}
					dataTable.sort();
				}, onFailure: function(xhr) {
					$('error').setStyle('margin-top',23);
					var myFx = new Fx.Tween('error');
					var count = xhr.statusText.split("\|").length;
					$('error').set('html',xhr.statusText.replace(/\|/g, "<br/>"));
					for(var i=0; i<count+1; i++) {
						(function(ind) {
							myFx.start('margin-top',23*(ind-1)*(-1), 23*ind*(-1));
							}).pass(i).delay((2000*i));
					}
					
				}});	
				var paramObject = {'meth': method, 'isbns': isbns};
				if(method.contains('pl'))
					paramObject.plname = params.plname;
				if(method.contains('rd'))
					paramObject.rddate = params.rddate;
				if(method.contains('st'))
					paramObject.stdate = params.stdate;
				request.post(paramObject);
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