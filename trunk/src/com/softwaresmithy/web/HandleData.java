package com.softwaresmithy.web;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softwaresmithy.amazon.AmazonResult;

public class HandleData extends HttpServlet{
	private static final long serialVersionUID = 2941698075480033286L;
	Connection con = null;
	
	public void init(){
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		String username = (String)req.getSession().getAttribute("username");
		if(username == null || username.equals("")){
			try{
				resp.sendError(300, "You must be logged in to use this service");
			}catch(Exception e){}
			return;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/booktracker", "root",
					"mdl3128");
			JSONObject json = new JSONObject("json");
			CallableStatement bookData = con.prepareCall("{call getData(?,?,?)}");
			
			String src = req.getParameter("src");
			String plName = req.getParameter("plname");
			String reqTags = req.getParameter("reqtags");
			
			
			bookData = setParams(bookData, username, plName, reqTags);
			bookData.execute();
			if(src.contains("b")) {//Book Data Requested
				json.addAttribute(bookData.getResultSet(), "data");
			}
			if(src.contains("t")) {//Tag Data Requested
				if(bookData.getMoreResults()){
					json.addAttribute(bookData.getResultSet(),"tags");
				}
			}
			if(src.contains("p")) {//Playlist Data Requested
				Statement getPls = con.createStatement();
				getPls.execute("select distinct playlist_name from playlist_entry where username = '"+req.getSession().getAttribute("username")+"'");
				json.addAttribute(getPls.getResultSet(), "playlists");
			}

			resp.getWriter().write(json.getJSONObject());
			
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
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		Boolean nonFatalError = false;
		String errString = "";
		String username = (String)req.getSession().getAttribute("username");
		if(username == null || username.equals("")){
			try{
				resp.sendError(300, "You must be logged in to use this service");
			}catch(Exception e){}
			return;
		}
		Connection con = null;
		
		try {
			System.out.println("in post");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/booktracker", "root", "mdl3128");
			
			String method = (String) req.getParameter("meth");
			String[] isbns = ((String) req.getParameter("isbns")).split(",");
			String plName = (String) req.getParameter("plname");
			String rdDate = (String) req.getParameter("rddate");
			String stDate = (String) req.getParameter("stdate");
			
			if(isbns == null) {
				resp.sendError(300, "You must send at least one isbn");
				return;
			}
			for(String isbn : isbns){
				System.out.println("starting isbn");
				if(method.contains("bk")){ //Add item to Library
					System.out.println("adding book to lib");
					AmazonResult match = (AmazonResult) req.getSession().getAttribute("isbn:"+isbn);
					if (match != null) { // Information from current search, ie, add to library
						try{
							CallableStatement addBook = prepareAddBook(con, match);
							addBook.execute();
						}catch(com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException e){
							//book info exists already, user doesn't care (neither do i)
						}
						// Add book to user's library
						try {
							CallableStatement addToLib = prepareAddToLib(con, username,match.getISBN());
							addToLib.execute();
						}catch(com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException e){
							nonFatalError = true;
							errString += match.getTitle()+" is already in your library|";
						}
					}
					else {
						nonFatalError = true;
						errString += "unidentified isbn: "+isbn;
					}
				}
				if(method.contains("pl")){ //Add item to Playlist
					if(plName != null && !plName.equals("")) {
						CallableStatement addToPlaylist = prepareAddToPlay(con, 
								username,
								isbn,plName);
						addToPlaylist.execute();
					}
				}
				if(method.contains("rd")){ //Add item's Read Date
					
				}
				if(method.contains("st")){ //Add item's Start Date
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}
	private CallableStatement setParams(CallableStatement cs, String username, String playlist, String tags){
		try{
			cs.setString(1, username);
			
			if(playlist != null && !playlist.equals("")){
				cs.setString(2, playlist);
			}else cs.setNull(2,java.sql.Types.VARCHAR);
			
			if(tags != null && !tags.equals("")){
				cs.setString(3, tags);
			}else cs.setNull(3,java.sql.Types.VARCHAR);
		}catch(Exception e){
		}
		return cs;
	}
	/*
	CREATE DEFINER=`root`@`localhost` PROCEDURE `addBook`(
	        isbn VARCHAR(10), title TEXT, amaz_rating DECIMAL(4,3), pub_date VARCHAR(10),
	        pages INTEGER, small_url TEXT, medium_url TEXT, large_url TEXT,
	        authors TEXT, altVers TEXT, tags TEXT)
	*/
	private CallableStatement prepareAddBook(Connection con, AmazonResult book) throws SQLException{
		CallableStatement addBook = con.prepareCall("{call addBook(?,?,?,?,?,?,?,?,?,?,?)}");
		addBook.setString(1, book.getISBN());
		addBook.setString(2, book.getTitle());
		addBook.setDouble(3, book.getRating());
		addBook.setString(4, book.getPubDate());
		addBook.setInt(5, book.getPages());
		addBook.setString(6, book.getSmallImageUrl());
		addBook.setString(7, book.getMediumImageUrl());
		addBook.setString(8, book.getLargeImageUrl());
		addBook.setString(9, book.getAuthorAsString());
		addBook.setString(10, book.getAlternateVersionsAsString());
		addBook.setString(11, book.getTagsAsString());
		return addBook;
	}
	/*
	 CREATE DEFINER=`root`@`localhost` PROCEDURE `addToLibrary`(username VARCHAR(30), isbn VARCHAR(10))
	 */
	private CallableStatement prepareAddToLib(Connection con, String username, String isbn) throws SQLException{
		CallableStatement cs = con.prepareCall("{call addToLibrary(?,?)}");
		cs.setString(1,username);
		cs.setString(2, isbn);
		return cs;
	}
	/*
	 CREATE DEFINER=`root`@`localhost` PROCEDURE `addToPlaylist`(qUsername VARCHAR(30), qIsbn VARCHAR(10), playlistName VARCHAR(30))
	 */
	private CallableStatement prepareAddToPlay(Connection con, String username, String isbn, String playlist) throws SQLException{
		CallableStatement cs = con.prepareCall("{call addToPlaylist(?,?,?)}");
		cs.setString(1, username);
		cs.setString(2, isbn);
		cs.setString(3, playlist);
		return cs;
	}
}
