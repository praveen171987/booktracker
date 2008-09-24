<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<script type="text/javascript">
	function loadData(responseText){
		eval(responseText);
		var i = 0;
		while(json.data && json.data[i]){
			var row = document.createElement("tr");
			i++;
		}
	}
</script>
<div id="tableDiv">
<table id="sizable">
	<thead>
		<tr NoDrag = "true" NoDrop="true">
			<td>&nbsp;</td>
			<td><div  style="width:330px;" onClick="doSort('sizable',1);">Title</div></td>
			<td><div  style="width:192px;" onClick="doSort('sizable',2, true);">Author</div></a></td>
			<td><div  style="width:134px;" onClick="doSort('sizable',3);">Publication Date</div></a></td>
			<td><div  style="width:96px;" onClick="doSort('sizable',4);">ISBN</div></a></td>
			<td><div  style="width:157px;" onClick="doSort('sizable',5);">Num Pages</div></a></td>
		</tr>
	</thead>
	<tbody>
	<%
	Connection con = null;
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booktracker","root", "mdl3128");
//		Statement query = con.createStatement();
//		query.execute("select title, (select GROUP_CONCAT(author SEPARATOR ', ') "+
//			"from booktracker.authors where authors.isbn=book.isbn) as author, pub_date, isbn, pages from booktracker.book where isbn in "+
//				"(select isbn from lib_entry where username = '"+request.getSession().getAttribute("username")+"')");
		
		CallableStatement query = con.prepareCall("{call retrievePlaylist(?,?)}");
			query.setString(1,(String)request.getSession().getAttribute("username"));
			query.setString(2,"read");
		query.execute();
		
		int val=1;
		while(query.getResultSet().next()){%>
				<tr>
					<td>&nbsp;</td>
					<td><div style="width:330px;" ><%=query.getResultSet().getString("title")%></div></td>
					<td><div style="width:192px;" ><%=query.getResultSet().getString("author")%></div></td>
					<td><div style="width:134px;" ><%=query.getResultSet().getString("pub_date")%></div></td>
					<td><div style="width:96px;" ><%=query.getResultSet().getString("isbn")%></div></td>
					<td><div style="width:157px;" ><%=query.getResultSet().getInt("pages")%></div></td>
				</tr>
			<%
			val++;
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		con.close();
	}

	
	%>
	</tbody>
</table>
</div>