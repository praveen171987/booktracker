package com.softwaresmithy.amazon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.a2s.AmazonA2S;
import com.amazonaws.a2s.AmazonA2SClient;

import com.amazonaws.a2s.model.Item;
import com.amazonaws.a2s.model.ItemSearchRequest;
import com.amazonaws.a2s.model.ItemSearchResponse;
import com.amazonaws.a2s.model.Items;

import com.softwaresmithy.acornweb.AcornWebQueryEngine;

public class AmazonQuery extends HttpServlet {
	/**
	 * 
	 */
	/************************************************************************
     * Access Key ID obtained from:
     * http://aws.amazon.com
     ***********************************************************************/
    String accessKeyId = "1JE5WNWJ29J99WBX06G2";
    String associateTag = "scumbkt19";
    ItemSearchRequest request;
    AmazonA2S service;
	public static void main(String... args) {
        
    }
	
	public void init(){
	     /***********************************************************************
         * Instantiate Client Implementation of Amazon A2S 
         ***********************************************************************/
        service = new AmazonA2SClient(accessKeyId, associateTag);
        
        request = new ItemSearchRequest();
        
        request.setSearchIndex("Books");
                 
        //Valid values for ResponseGroup include Accessories, AlternateVersions, BrowseNodes, Collections, 
        //EditorialReview, Images, ItemAttributes, ItemIds, Large, ListmaniaLists, Medium, 
        //MerchantItemAttributes, OfferFull, OfferListings, OfferSummary, Offers, PromotionDetails, 
        //PromotionSummary, PromotionalTag, RelatedItems, Request, Reviews, SalesRank, SearchBins, 
        //SearchInside, Similarities, Small, Subjects, Tags, TagsSummary, Tracks, VariationMatrix, 
        //VariationMinimum, VariationOffers, VariationSummary, Variations.
        java.util.List<String> responseGroup = new java.util.ArrayList<String>();
	        responseGroup.add("Small");
	        responseGroup.add("AlternateVersions");
	        responseGroup.add("Images");
	        responseGroup.add("Tags");
	        responseGroup.add("Reviews");
	        //request.setResponseGroup(responseGroup);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String keyword = (String) req.getParameter("keyword");
		String dummy = (String) req.getParameter("dummy");
		if(dummy.equals("true")){
			for(int i=0;i<3;i++){
				try {
					resp.getWriter().write(dummyResponse());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		}
		if(keyword != null){
			request.setKeywords("Hood Stephen Lawhead");
			try{
	             //AcornWebQueryEngine query = new AcornWebQueryEngine();
	             
				
	             ItemSearchResponse response = service.itemSearch(request);

	             java.util.List<Items> items = response.getItems();
	             System.out.println("List<Items> size: "+items.size());
	             List<Item> item = items.get(0).getItem();
	             for(int j=0;j<item.size();j++){
	                 AmazonResult temp = new AmazonResult(item.get(j));
	                 req.getSession().setAttribute(temp.getISBN(), temp);
	                 resp.getWriter().write(formatHttpResult(temp));
	                 //System.out.println(temp.getTitle()+": "+temp.getISBN());
	                 //System.out.println("at library: "+query.atLibrary(isbns));
	             }
	             
	         }catch(Exception e){
	             e.printStackTrace();
	         }
		}
		else{
			try{
				resp.getWriter().write("error!");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private String formatHttpResult(AmazonResult book){
		String temp = "";
		temp += "<div class='round'>"+
		"<img class='cover' src='"+book.getSmallImageUrl()+"'></img>"+
		"<div class='info'>"+
			"<div class='bio'>"+
				"<div class='title'>"+book.getTitle()+"</div>"+
				"<div class='author'>by "+book.getAuthor().get(0)+"</div>"+ // TODO Handle multiple authors 
				"<div class='tags'><%= 'apple, banana, cranberry, author, title'%></div>"+
			"</div>"+
			"<div class='right'>"+
				"<div class='rating'>* * * * *</div>"+
				"<div class='links'>"+
					"<div class='library'>Reserve from Library</div>"+
					"<div class='amazon'>Buy at Amazon</div>"+
				"</div>"+
			"</div>"+
		"</div>"+
	"</div>";
		
		return temp;
	}
	private String dummyResponse(){
		String temp = "";
		temp += "<div class='round'>"+
		"<img class='cover' src='"+"http://ecx.images-amazon.com/images/I/51yYy6tjdlL._SL75_.jpg"+"'></img>"+
		"<div class='info'>"+
			"<div class='bio'>"+
				"<div class='title'>"+"Scarlet"+"</div>"+
				"<div class='author'>by Stephen Lawhead</div><!--Handle multiple authors!-->"+
				"<div class='tags'><%= 'apple, banana, cranberry, author, title'%></div>"+
			"</div>"+
			"<div class='right'>"+
				"<div class='rating'>* * * * *</div>"+
				"<div class='links'>"+
					"<div class='library'>Reserve from Library</div>"+
					"<div class='amazon'>Buy at Amazon</div>"+
				"</div>"+
			"</div>"+
		"</div>"+
	"</div>";
		return temp;
	}
}
