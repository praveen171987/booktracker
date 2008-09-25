<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.CallableStatement"%>

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


<%
	//Connection con = null;
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booktracker","root", "mdl3128");
		CallableStatement query2 = con.prepareCall("{call getTags(?,?,?)}");
			query2.setString(1,(String)request.getSession().getAttribute("username"));
			query2.setNull(2,java.sql.Types.VARCHAR);
			query2.setNull(3, java.sql.Types.VARCHAR);
		query2.execute();
		
		int max=0;
		int min=1;
		int spread = 6;
		while(query2.getResultSet().next()){
			max = (max>query2.getResultSet().getInt("num"))?max:query2.getResultSet().getInt("num");
			min = (min<query2.getResultSet().getInt("num"))?min:query2.getResultSet().getInt("num");
		}
		query2.getResultSet().beforeFirst();
		
		spread = (spread>(max-min))?(max-min):spread;
		
		while(query2.getResultSet().next()){
			double classNum = Math.floor(query2.getResultSet().getInt("num")*spread/(max-(min-1)));
		%>
			
			
			<span style="margin-left:2px; margin-right:2px;" class="tag<%=(int)classNum%>"><%=query2.getResultSet().getString("tag")%></span>
		<%}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	
	%>
