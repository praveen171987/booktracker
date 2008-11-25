package com.softwaresmithy.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.a2s.AmazonA2S;
import com.amazonaws.a2s.AmazonA2SClient;
import com.amazonaws.a2s.model.ItemLookupRequest;
import com.amazonaws.a2s.model.ItemLookupResponse;
import com.softwaresmithy.acornweb.AcornWebQueryEngine;
import com.softwaresmithy.amazon.AmazonResult;

public class UpdateData {
	Connection con;
	UpdateData(){
		try {
			String accessKeyId = "1JE5WNWJ29J99WBX06G2";
		    String associateTag = "scumbkt19";
		    ItemLookupRequest request;
		    AmazonA2S service;

	        /***********************************************************************
	         * Instantiate Client Implementation of Amazon A2S 
	         ***********************************************************************/
	        service = new AmazonA2SClient(accessKeyId, associateTag);

	        request = new ItemLookupRequest();

	        //Valid values for ResponseGroup include Accessories, AlternateVersions, BrowseNodes, Collections, 
	        //EditorialReview, Images, ItemAttributes, ItemIds, Large, ListmaniaLists, Medium, 
	        //MerchantItemAttributes, OfferFull, OfferListings, OfferSummary, Offers, PromotionDetails, 
	        //PromotionSummary, PromotionalTag, RelatedItems, Request, Reviews, SalesRank, SearchBins, 
	        //SearchInside, Similarities, Small, Subjects, Tags, TagsSummary, Tracks, VariationMatrix, 
	        //VariationMinimum, VariationOffers, VariationSummary, Variations.
	        java.util.List<String> responseGroup = new java.util.ArrayList<String>();
	        responseGroup.add("ItemAttributes");
	        responseGroup.add("Small");
	        responseGroup.add("AlternateVersions");
	        responseGroup.add("Images");
	        responseGroup.add("Tags");
	        responseGroup.add("Reviews");
		    request.setResponseGroup(responseGroup);
		    
		    String updateStr = "";
		    
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/booktracker", "root",
					"mdl3128");
			
			AcornWebQueryEngine web = new AcornWebQueryEngine();
			
			Statement update = con.createStatement();
			//update.execute("SELECT a.isbn, amaz_rating, pub_date, pages, small_url, medium_url, large_url, (select GROUP_CONCAT(alt_ver SEPARATOR ', ') from booktracker.alt_vers where alt_vers.isbn=a.isbn) as alt_vers FROM book a WHERE not DATE_SUB(CURDATE(),INTERVAL 30 DAY) <= last_updated;");
			update.execute("SELECT a.isbn, amaz_rating, pub_date, pages, small_url, medium_url, large_url, (select GROUP_CONCAT(alt_ver SEPARATOR ', ') from booktracker.alt_vers where alt_vers.isbn=a.isbn) as alt_vers FROM book a;");
			ResultSet rs = update.getResultSet();
			while(rs.next()){
				updateStr = "";
		         java.util.List<String> itemId = new ArrayList<String>();
		         	itemId.add(rs.getString("isbn"));
		         request.setItemId(itemId);
		         ItemLookupResponse response = service.itemLookup(request);
		         AmazonResult book = new AmazonResult(response.getItems().get(0).getItem().get(0));
		         if(book.getRating() != rs.getDouble("amaz_rating")&&
		        	  !(Double.isNaN(book.getRating()) && rs.wasNull())){
		        	 updateStr += "amaz_rating= '"+book.getRating()+"',";
		         }
		         if(!book.getPubDate().equals(rs.getString("pub_date")) &&
		        		 !(book.getPubDate().equals("") && rs.wasNull()))
		        	 updateStr += "pub_date= '"+book.getPubDate()+"',";
		         if(book.getPages() != rs.getInt("pages") &&
		        		 !(book.getPages() == -1 && rs.wasNull()))
		        	 updateStr += "pages= '"+book.getPages()+"',";
		         if(!book.getSmallImageUrl().equals(rs.getString("small_url")) &&
		        		 !(book.getSmallImageUrl().equals("") && rs.wasNull()))
		        	 updateStr += "small_url= '"+book.getSmallImageUrl()+"',";
		         if(!book.getMediumImageUrl().equals(rs.getString("medium_url")) &&
		        		 !(book.getMediumImageUrl().equals("") && rs.wasNull()))
		        	 updateStr += "medium_url= '"+book.getMediumImageUrl()+"',";
		         if(!book.getLargeImageUrl().equals(rs.getString("large_url")) &&
		        		 !(book.getLargeImageUrl().equals("") && rs.wasNull()))
		        	 updateStr += "large_url= '"+book.getLargeImageUrl()+"',";
		         
		         if(updateStr.length() > 0){ 
			         updateStr = "UPDATE book SET "+updateStr+"last_updated= CURDATE() WHERE isbn= '"+rs.getString("isbn")+"'";
			         
			         try{
			        	 //con.createStatement().execute(updateStr);
			        	 System.out.println("success!");
			         }catch(Exception e){
			        	 System.out.println("isbn: "+book.getISBN()+" failed");
			        	 System.out.println(updateStr);
			        	 e.printStackTrace();
			         }
		         }
		         else {
		        	 try {
		        		 //con.createStatement().execute("UPDATE book SET last_updated= CURDATE() WHERE isbn= '"+rs.getString("isbn")+"'");
		        		 System.out.println("no updates found");
		        	 }catch(Exception e){
		        		 e.printStackTrace();
		        	 }
		        	 
		         }
		         List<String> altVers = new ArrayList<String>();
		         if(rs.getString("alt_vers") != null)
		        	 altVers.addAll(Arrays.asList(rs.getString("alt_vers").split(", ")));
		         altVers.add(book.getISBN());
		         String carlId = web.atLibrary(altVers); 
		         if( carlId != null){
		        	 System.out.println("at library: "+carlId);
		        	 try{
		        		 con.createStatement().execute("insert into carlweb values('"+book.getISBN()+"', '"+carlId+"')");
		        	 }catch(Exception e) {
		        		 System.out.println("\tupdate failed");
		        		 e.printStackTrace();
		        	 }
		         }
		         else {
		        	 System.out.println("not at library");
		         }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		new UpdateData();
	}
}
