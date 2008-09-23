package com.softwaresmithy.web;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleData extends HttpServlet{
	Connection con = null;
	public void init(){
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/booktracker", "root",
					"mdl3128");
			
			CallableStatement bookData = con.prepareCall("{call getData(?,?,?)}");
			bookData = setParams(bookData, req);
			bookData.execute();
			resp.getWriter().write(resultSetToJson(bookData.getResultSet()));
			//resp.getWriter().write("json = { data: [{'firstName' : 'John','lastName'  : 'Doe','age'       : 23 }]}");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	private CallableStatement setParams(CallableStatement cs, HttpServletRequest req){
		try{
			//cs.setString(1, (String)req.getSession().getAttribute("username"));
			cs.setString(1, "apple");
			if(req.getParameter("playlist")!= null && !req.getParameter("playlist").equals("")){
				cs.setString(2, req.getParameter("playlist"));
			}
			else cs.setNull(2,java.sql.Types.VARCHAR);
			if(req.getParameter("tags")!= null && !req.getParameter("tags").equals("")){
				cs.setString(3, req.getParameter("tags"));
			}
			else cs.setNull(3,java.sql.Types.VARCHAR);
		}catch(Exception e){
		}
		return cs;
	}
	private String resultSetToJson(ResultSet rs){
		String json = "json = {";
		try{
			json += "'data': ["; //Array Start
			while(rs.next()){
				json += "{"; //Object Start
				int i=0;
				for(i=0;i<rs.getMetaData().getColumnCount()-1;i++){
					json += "'"+rs.getMetaData().getColumnName(i+1)+"'"+" : "+"'"+rs.getString(i+1)+"',";
				}
				json += "'"+rs.getMetaData().getColumnName(i+1)+"'"+" : "+"'"+rs.getString(i+1)+"'";
				json += "},"; //Ojbect End
			}
			json = json.substring(0,json.length()-1);//Subtract last comma
			json += "]"; //Array End
		}catch(Exception e){
			e.printStackTrace();
			json += "}";
			return json;
		}
		json += "}";
		return json;
	}
}
