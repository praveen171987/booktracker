package com.softwaresmithy.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.*;

public class AddBook {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;

	    try {
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booktracker",
	        "root", "mdl3128");
	      
	      Statement statement = con.createStatement();
			statement.execute("SELECT count(isbn) FROM alt_vers WHERE alt_ver = '0123456789'");
			System.out.println(statement.getResultSet());
	      ResultSet results = statement.getResultSet();
	      results.first();
	      System.out.println(results.getInt(1));
	      
	      while(results.next()){
	    	  
	      }
	      
	      if(!con.isClosed())
	        System.out.println("Successfully connected to " +
	          "MySQL server using TCP/IP...");

	    } catch(Exception e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if(con != null)
	          con.close();
	      } catch(SQLException e) {}
	    }

	}

}
