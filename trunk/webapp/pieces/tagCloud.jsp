<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<html>
	<head>
		<style type="text/css">
			.tag0{
				font-size: .58em;
			}
			.tag1{
				font-size: 1.2em;
			}
			.tag2{
				font-size: 1.7em;
			}
			.tag3{
				font-size: 2.3em;
			}
			.tag4{
				font-size: 2.9em;
			}
			.tag5{
				font-size: 3.5em;
			}
			.tag6{
				font-size: 4.0em
			}
		</style>
	</head>
	<body>

<%
	Connection con = null;
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booktracker","root", "mdl3128");
		Statement query = con.createStatement();
		query.execute("select tag, count(*) as num from booktracker.tags group by tag");
		
		int max=0;
		while(query.getResultSet().next()){
			max = (max>query.getResultSet().getInt("num"))?max:query.getResultSet().getInt("num");
		}
		query.getResultSet().beforeFirst();
		
		while(query.getResultSet().next()){
			double classNum = Math.floor(query.getResultSet().getInt("num")*6/max);
		%>
			
			
			<span style="margin-left:2px; margin-right:2px;" class="tag<%=(int)classNum%>"><%=query.getResultSet().getString("tag")%></span>
		<%}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	
	%>
	</body>
</html>