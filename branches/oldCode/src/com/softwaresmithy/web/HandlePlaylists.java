package com.softwaresmithy.web;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlePlaylists extends HttpServlet{
	private static final long serialVersionUID = -7498283855369633299L;
	Connection con = null;
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/booktracker", "root",
					"mdl3128");
			JSONObject json = new JSONObject("json");
			
			Statement getPls = con.createStatement();
			getPls.execute("select distinct playlist_name from playlist_entry where username = '"+req.getSession().getAttribute("username")+"'");
			json.addAttribute(getPls.getResultSet(), "playlists");
	
	
			resp.getWriter().write(json.getJSONObject());
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

}
