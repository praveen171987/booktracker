package com.softwaresmithy.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.amazonaws.a2s.AmazonA2S;
import com.amazonaws.a2s.AmazonA2SClient;
import com.amazonaws.a2s.model.ItemLookupRequest;
import com.amazonaws.a2s.model.ItemLookupResponse;
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
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/booktracker", "root",
					"mdl3128");
			Statement update = con.createStatement();
			update.execute("SELECT * FROM `booktracker`.`book` WHERE not DATE_SUB(CURDATE(),INTERVAL 30 DAY) <= last_updated;");
			ResultSet rs = update.getResultSet();
			while(rs.next()){
		         java.util.List<String> itemId = new ArrayList<String>();
		         	itemId.add(rs.getString("isbn"));
		         request.setItemId(itemId);
		         ItemLookupResponse response = service.itemLookup(request);
		         AmazonResult book = new AmazonResult(response.getItems().get(0).getItem().get(0));
		         System.out.println(rs.getString("title"));
		         if(book.getRating() != rs.getDouble("amaz_rating"))
		        	 System.out.println("\tnew rating: "+book.getRating()+" "+rs.getDouble("amaz_rating"));
		         if(!book.getPubDate().equals(rs.getString("pub_date")))
		        	 System.out.println("\tnew pub_date "+book.getPubDate()+" "+rs.getString("pub_date"));
		         if(book.getPages() != rs.getInt("pages"))
		        	 System.out.println("\tnew num pages "+book.getPages() +" "+ rs.getInt("pages"));
		         if(!book.getSmallImageUrl().equals(rs.getString("small_url")))
		        	 System.out.println("\tnew small image "+book.getSmallImageUrl() +" "+ rs.getString("small_url"));
		         if(!book.getMediumImageUrl().equals(rs.getString("medium_url")))
		        	 System.out.println("\tnew medium image "+book.getMediumImageUrl() +" "+ rs.getString("medium_url"));
		         if(!book.getLargeImageUrl().equals(rs.getString("large_url")))
		        	 System.out.println("\tnew large image "+book.getLargeImageUrl() +" "+ rs.getString("large_url"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		new UpdateData();
	}
}
