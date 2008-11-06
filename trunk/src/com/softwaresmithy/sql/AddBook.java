package com.softwaresmithy.sql;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.softwaresmithy.amazon.AmazonResult;

public class AddBook extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession sess = req.getSession();
		if(req.getParameter("isbn") == null) {
			try {
				resp.sendError(300);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		String addIsbn = req.getParameter("isbn").toString();
		String targetPlaylist = (req.getParameter("playlist") != null)?req.getParameter("playlist").toString():null;
		
		if (addIsbn != null && !addIsbn.equals("")) {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/booktracker", "root", "mdl3128");
					
				AmazonResult match = (AmazonResult) sess.getAttribute("isbn:"+addIsbn);
				if (match != null) { // Information from current search, ie, add to library (~and playlist)	
					// Add book info to database
					try{
						CallableStatement addBook = prepareAddBook(con, match);
						addBook.execute();
					}catch(com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException e){
						//swallow duplicate entry
					}
					// Add book to user's library
					try {
					CallableStatement addToLib = prepareAddToLib(con, sess.getAttribute("username").toString(),match.getISBN());
					addToLib.execute();
					}catch(com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException e){
						//swallow duplicate entry
					}
					// (optional) add book to specified playlist
					if(targetPlaylist != null && !targetPlaylist.equals("")) {
						CallableStatement addToPlaylist = prepareAddToPlay(con, 
								sess.getAttribute("username").toString(),
								addIsbn,targetPlaylist);
						addToPlaylist.execute();
					}
				} else { // For now, assume it's assignment to a playlist
					System.out.println("adding to playlist?");
					if(targetPlaylist != null && !targetPlaylist.equals("")) {
						System.out.println("adding to playlist! "+targetPlaylist+", "+addIsbn);
						try{
							CallableStatement addToPlaylist = prepareAddToPlay(con, 
									sess.getAttribute("username").toString(),
									addIsbn,targetPlaylist);
							addToPlaylist.execute();
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	public static void main(String[] args) {
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
