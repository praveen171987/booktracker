<%@page import="org.apache.lucene.index.*"%>
<%@page import="org.apache.lucene.search.*"%>
<%@page import="org.apache.lucene.queryParser.*"%>
<%@page import="org.apache.lucene.analysis.*"%>
<%@page import="org.apache.lucene.analysis.standard.StandardAnalyzer"%>

<html>
	<head>
		<title>BookTracker - Table View</title>
		<link rel="stylesheet" href="scripts/twoColumn.css">
		<script type="text/javascript" src="scripts/sizableColumns.js"></script>
		<style type="text/css">
			body
			{
				margin: 0;
				padding: 0;
			}

			.columnGrabber
			{
				position: fixed;
				position: expression("absolute");
				top: 0;
				top: expression(eval(document.body.scrollTop));
				width: 8px;
				height: 100%;
				margin-left: -4px;
				cursor: col-resize;
				
				background-color: #000000;
			}
			div
			{
				overflow: hidden;
				white-space: nowrap;
			}
			td
			{
				padding-left: 4px;
				padding-right: 5px;
			}
		</style>
	</head>
	<body onLoad="start('sizable');">
		<div id="container">
			<div id="banner">
				<h1>Site name</h1>

			</div>
			<div id="nav">
				<p>
					Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod.
				</p>
			</div>
			<div id="content">
				<h2>
					Page heading
				</h2>

				<p>
					<table id="sizable">
						<tr>
							<td><div  style="width:50px;" id="cell_0_0">Title</div></td>
							<td><div  style="width:50px;" id="cell_0_1">Author</div></td>
							<td><div  style="width:50px;" id="cell_0_2">Publication Date</div></td>
							<td><div  style="width:50px;" id="cell_0_3">ISBN</div></td>
							<td><div  style="width:50px;" id="cell_0_4">Num Pages</div></td>
						</tr>
						<%
						String[] isbns = {"0765319209","1416555870","1416555919","159102594X","0765315459","159554089X"};
						IndexReader reader = IndexReader.open("D:\\Documents and Settings\\szy4zq\\My Documents\\BookTracker\\src\\index");
						IndexSearcher searcher = new IndexSearcher(reader);
						
						String field = "ISBN";
						Analyzer analyzer = new StandardAnalyzer();
						QueryParser parser = new QueryParser(field, analyzer);
						int val = 1;
						for(int i=0;i<isbns.length;i++){
							Hits hits = searcher.search(parser.parse(isbns[i]));
							if(hits.length()>0){%>
								<tr>
									<td><div id="cell_<%=val%>_0" style="width:50px;" ><%=hits.doc(0).getFields("title")[0].stringValue()%></div></td>
									<td><div id="cell_<%=val%>_1" style="width:50px;" ><%=hits.doc(0).getFields("author")[0].stringValue()%></div></td>
									<td><div id="cell_<%=val%>_2" style="width:50px;" ><%=hits.doc(0).getFields("pubDate")[0].stringValue()%></div></td>
									<td><div id="cell_<%=val%>_3" style="width:50px;" ><%=hits.doc(0).getFields("ISBN")[0].stringValue()%></div></td>
									<td><div id="cell_<%=val%>_4" style="width:50px;" ><%=hits.doc(0).getFields("pages")[0].stringValue()%></div></td>
								</tr>
							<%
							val++;
							}
						}

						
						%>
					</table>
				</p>
				<p class="nextbutton">
					<a href="JavaScript:history.back()">&lt; Back to tutorial</a>
				</p>
			</div>
			<div id="footer">

				Footer stuff here
			</div>
		</div>
	</body>
</html>