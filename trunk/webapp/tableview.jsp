<%@page import="org.apache.lucene.index.*"%>
<%@page import="org.apache.lucene.search.*"%>
<%@page import="org.apache.lucene.queryParser.*"%>
<%@page import="org.apache.lucene.analysis.*"%>
<%@page import="org.apache.lucene.analysis.standard.StandardAnalyzer"%>

<html>
	<head>
		<title>BookTracker - Table View</title>
		<link rel="stylesheet" href="scripts/twoColumn.css">
		
	</head>
	<body>
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
					<table>
						<tr>
							<td>Title</td><td>Author</td><td>Publication Date</td><td>ISBN</td><td>Num Pages</td>
						</tr>
						<%
						String[] isbns = {"0765319209","1416555870","1416555919","159102594X","0765315459"};
						IndexReader reader = IndexReader.open("D:\\Documents and Settings\\szy4zq\\My Documents\\BookTracker\\src\\index");
						IndexSearcher searcher = new IndexSearcher(reader);
						
						String field = "ISBN";
						Analyzer analyzer = new StandardAnalyzer();
						QueryParser parser = new QueryParser(field, analyzer);
						for(int i=0;i<isbns.length;i++){
							Hits hits = searcher.search(parser.parse(isbns[i]));
							if(hits.length()>0){%>
								<tr>
									<td><%=hits.doc(0).getFields("title")[0].stringValue()%></td>
									<td><%=hits.doc(0).getFields("author")[0].stringValue()%></td>
									<td><%=hits.doc(0).getFields("pubDate")[0].stringValue()%></td>
									<td><%=hits.doc(0).getFields("ISBN")[0].stringValue()%></td>
									<td><%=hits.doc(0).getFields("pages")[0].stringValue()%></td>
								</tr>
							<%}
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