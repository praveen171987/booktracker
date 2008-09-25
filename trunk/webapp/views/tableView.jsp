<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<script type="text/javascript">
	function loadData(responseText){
		eval(responseText);
		var i = 0;
		while(json.data && json.data[i]){
			var row = document.getElementById("sizable").tBodies[0].insertRow(-1);
				row.insertCell(-1).innerHTML = "&nbsp;";
				row.insertCell(-1).innerHTML = "<div>"+json.data[i].title+"</div>";
				row.insertCell(-1).innerHTML = "<div>"+json.data[i].author+"</div>";
				row.insertCell(-1).innerHTML = "<div>"+json.data[i].pub_date+"</div>";
				row.insertCell(-1).innerHTML = "<div>"+json.data[i].isbn+"</div>";
				row.insertCell(-1).innerHTML = "<div>"+json.data[i].pages+"</div>";
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
	</tbody>
</table>
</div>