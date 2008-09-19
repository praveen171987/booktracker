package com.softwaresmithy.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class HandleUser extends HttpServlet {

	Connection con = null;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/booktracker", "root",
					"mdl3128");
		} catch (Exception e) {
			try {
				resp.getWriter().write("Unable to connect to database");
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			if (req.getParameter("logout") != null && req.getParameter("logout").equals("true")) {
				req.getSession().invalidate();

				resp.getWriter().write("You've been logged out!");

				return;
			}
			if (req.getParameter("newUser") != null && req.getParameter("newUser").equals("true")) {// Create a user
				Statement newUser = con.createStatement();
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String email = req.getParameter("email");
				String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				try{
					newUser.execute("INSERT INTO user VALUES("+
						"'"+username+"',"+
						"'"+password+"',"+
						"'"+email+"',"+
						"'"+date+"')");
				}catch(MySQLIntegrityConstraintViolationException e){
					resp.getWriter().write("user already exists, please use a different name");
				}
			} else {// Login
				Statement login = con.createStatement();
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				login.execute("SELECT password FROM user WHERE username = '"+username+"'");
				login.getResultSet().first();
				if(login.getResultSet().getString(1).equals(password)){
					req.getSession().setAttribute("username", username);
					req.getSession().setAttribute("playlist", "library");
					
					resp.sendRedirect(req.getParameter("link"));
				}else {
					resp.getWriter().write("username and password do not match");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.getSession().invalidate();
			resp.sendRedirect("/BookTracker/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
