<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%
	Connection con = null;
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booktracker","root", "mdl3128");
		Statement query = con.createStatement();
		query.execute("select distinct playlist_name from playlist_entry where username = '"+request.getSession().getAttribute("username")+"'");

%>
<table id="playlists">
	<tr><td>Library</td></tr><%
	while(query.getResultSet().next()){%>
		<tr><td><%=query.getResultSet().getString(1)%></td></tr>
	<%}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		con.close();
	}
	
	%>
</table>