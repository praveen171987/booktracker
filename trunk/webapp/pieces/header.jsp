<h1>Book Tracker</h1>
<div class="login">
	<%
		if(session.getAttribute("username") == null)
		{
	%>
			<!--<div class="register"><a href="newuser.jsp">register a new user</a></div>-->
			<form class="login" method="post" action="HandleUser">
				username? <input type="text" name="username">
				password? <input type="password" name="password">
				<input type="hidden" name="link" value="<%=request.getRequestURI() %>">
				<input type="submit" value="=">
			</form>
	<%
		}
		else
		{
	%>
		<span>
		Hello, <%=session.getAttribute("username")%>, <a href="HandleUser">logout</a>?
		</span>
	<%
		}
	%>
</div>
<div id="amazSearch">
<input id="text" type="text">
<input id="button" style="float:right;" type="button" value="search" onClick="getQuery($('text').value);">
</div>
<input id="debug">