<%@page import="org.apache.lucene.index.*"%>
<%@page import="org.apache.lucene.search.*"%>
<%@page import="org.apache.lucene.queryParser.*"%>
<%@page import="org.apache.lucene.analysis.*"%>
<%@page import="org.apache.lucene.analysis.standard.StandardAnalyzer"%>
<table id="sizable">
	<tr NoDrag = "true" NoDrop="true">
		<td>&nbsp;</td>
		<td><div  style="width:330px;" id="cell_0_0">Title</div></td>
		<td><div  style="width:192px;" id="cell_0_1">Author</div></td>
		<td><div  style="width:134px;" id="cell_0_2">Publication Date</div></td>
		<td><div  style="width:96px;" id="cell_0_3">ISBN</div></td>
		<td><div  style="width:157px;" id="cell_0_4">Num Pages</div></td>
	</tr>
	<%
	out.println("DATASOURCE: "+request.getParameter("playlist"));
	String[] isbns = {"0765319209","1416555870","1416555919","159102594X","0765315459","159554089X"};
	//IndexReader reader = IndexReader.open("D:\\Documents and Settings\\szy4zq\\My Documents\\BookTracker\\src\\index");
	IndexReader reader = IndexReader.open("F:\\Users\\Matt\\BookTracker\\src\\index");
	IndexSearcher searcher = new IndexSearcher(reader);
	
	String field = "ISBN";
	Analyzer analyzer = new StandardAnalyzer();
	
	QueryParser parser = new QueryParser(field, analyzer);
	int val = 1;
	for(int i=0;i<isbns.length;i++){
		Term term = new Term("ISBN",isbns[i]);
		TermQuery tq = new TermQuery(term);
		
		Hits hits = searcher.search(tq);
		if(hits.length()>0){%>
			<tr>
				<td>&nbsp;</td>
				<td><div id="cell_<%=val%>_0" style="width:330px;" ><%=hits.doc(0).getFields("title")[0].stringValue()%></div></td>
				<td><div id="cell_<%=val%>_1" style="width:192px;" ><%=hits.doc(0).getFields("author")[0].stringValue()%></div></td>
				<td><div id="cell_<%=val%>_2" style="width:134px;" ><%=hits.doc(0).getFields("pubDate")[0].stringValue()%></div></td>
				<td><div id="cell_<%=val%>_3" style="width:96px;" ><%=hits.doc(0).getFields("ISBN")[0].stringValue()%></div></td>
				<td><div id="cell_<%=val%>_4" style="width:157px;" ><%=hits.doc(0).getFields("pages")[0].stringValue()%></div></td>
			</tr>
		<%
		val++;
		}
	}

	
	%>
</table>