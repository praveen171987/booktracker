package com.softwaresmithy.web;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleData extends HttpServlet{
	private static final long serialVersionUID = 2941698075480033286L;
	Connection con = null;
	
	public void init(){
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/booktracker", "root",
					"mdl3128");
			JSONObject json = new JSONObject("json");
			CallableStatement bookData = con.prepareCall("{call getData(?,?,?)}");
			bookData = setParams(bookData, req);
			bookData.execute();
			json.addAttribute(bookData.getResultSet(), "data");
			if(bookData.getMoreResults()){
				json.addAttribute(bookData.getResultSet(),"tags");
			}


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

}
