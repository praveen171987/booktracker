package com.softwaresmithy.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.amazonaws.a2s.AmazonA2S;
import com.amazonaws.a2s.AmazonA2SClient;
import com.amazonaws.a2s.model.ItemLookupRequest;
import com.amazonaws.a2s.model.ItemLookupResponse;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.softwaresmithy.acornweb.AcornWebQueryEngine;
import com.softwaresmithy.amazon.AmazonResult;

public class Test {

	/**
	 * @param args
	 */
	static final File INDEX_DIR = new File("index");
        //static final File INDEX_DIR = new File("F:\\Users\\Matt\\BookTracker\\src\\index");
	public static void main(String[] args) {
		Connection con = null;
		try{
	        /************************************************************************
	         * Access Key ID obtained from:
	         * http://aws.amazon.com
	         ***********************************************************************/
	        String accessKeyId = "1JE5WNWJ29J99WBX06G2";
	        String associateTag = "scumbkt19";
	        
	        /************************************************************************
	         * Instantiate Client Implementation of Amazon A2S 
	         ***********************************************************************/
	        AmazonA2S service = new AmazonA2SClient(accessKeyId, associateTag);

	        boolean stop = false;

	        AcornWebQueryEngine library = new AcornWebQueryEngine();
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booktracker",
	    		"root", "mdl3128");
	        
	        while(!stop){
	        	System.out.print("query isbn: ");
	        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        	String isbn = br.readLine();
	        	if(isbn==null || isbn.equals("")) break;
		        ItemLookupRequest request = new ItemLookupRequest();
		         java.util.List<String> responseGroup = new java.util.ArrayList<String>();
			        responseGroup.add("Small");
			        responseGroup.add("AlternateVersions");
			        responseGroup.add("Images");
			        responseGroup.add("ItemAttributes");
			        responseGroup.add("Tags");
			        responseGroup.add("Reviews");
			        request.setResponseGroup(responseGroup);
		        
		        java.util.List<String> itemId = new ArrayList<String>();
		        	itemId.add(isbn);
		        request.setItemId(itemId);
                        if(isbn.length()==10){
                            //Standard ISBN
                        }
                        else if(isbn.length()==13){
                            //ISBN-13
                            request.setIdType("EAN");
                            request.setSearchIndex("Books");    
                        }
                        else{
                            //Not a valid number
                            System.out.println("Not a valid ISBN");
                            continue;
                        }
		        ItemLookupResponse response = service.itemLookup(request);
		        
		        if(response.getItems().size()>0){
		        	System.out.println(response.getItems().get(0).getItem().get(0).getItemAttributes().getTitle());
		        	
					AmazonResult result = new AmazonResult(response.getItems().get(0).getItem().get(0));
					//String bookId = library.atLibrary(result);
					Statement statement = con.createStatement();
					statement.execute("SELECT count(isbn) FROM alt_vers WHERE alt_ver = '"+result.getISBN()+"'");
					statement.getResultSet().first();
					//if(bookId != null){}
						//doc.add(new Field("bookId",bookId,Field.Store.YES,Field.Index.NO));
					if(statement.getResultSet().getInt(1)>0){//if(select count(isbn) in alt_vers where alt_ver = result.getISBN();
						System.out.println("Exists as AltVersion");
					}else {
						CallableStatement cs = con.prepareCall("{call insertBook(?,?,?,?,?,?,?,?,?,?,?)}");
						cs = result.prepareCS(cs);
						System.out.println(cs.toString());
						try{
							cs.execute();
							System.out.println("Successfully added");

						}catch(MySQLIntegrityConstraintViolationException e){
							System.out.println("Already in the database");
						}
						Statement addToUser = con.createStatement();
						String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
						String query = "INSERT INTO lib_entry VALUES(null,'apple','"+result.getISBN()+"',null,'"+date+"',"+
							"null,null)";
						addToUser.execute(query);
						System.out.println("added book to user library");
					}
		        }
		        else System.out.println("no results");
	        }
		}catch(Exception e){
			e.printStackTrace();

		}finally{
			try{
				con.close();
			}catch(Exception e){}
		}
	}
}
