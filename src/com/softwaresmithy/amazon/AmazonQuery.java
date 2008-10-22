package com.softwaresmithy.amazon;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.a2s.AmazonA2S;
import com.amazonaws.a2s.AmazonA2SClient;

import com.amazonaws.a2s.model.*;

import com.softwaresmithy.acornweb.AcornWebQueryEngine;

public class AmazonQuery extends HttpServlet {

	private static final long serialVersionUID = -7890657707280443585L;
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

    @Override
    public void init() {
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
        responseGroup.add("ItemAttributes");
        responseGroup.add("Small");
        responseGroup.add("AlternateVersions");
        responseGroup.add("Images");
        responseGroup.add("Tags");
        responseGroup.add("Reviews");
    request.setResponseGroup(responseGroup);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String keyword = (String) req.getParameter("keyword");
        String dummy = (String) req.getParameter("dummy");
        AmazonResult book = new AmazonResult();
        List<String> auth = new ArrayList<String>();
        auth.add("Terry Pratchett");
        book.setAuthor(auth);
        book.setDetailUrl("#");
        book.setTitle("Nation");
        book.setSmallImageUrl("");
        if (dummy != null && dummy.equals("true")) {
            try {
                resp.getWriter().write("{data:["+formatJsonResult(book)+"]}");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if (keyword != null && !keyword.equals("")) {
            request.setKeywords(keyword);
            try {
                //AcornWebQueryEngine query = new AcornWebQueryEngine();

                ItemSearchResponse response = service.itemSearch(request);

                java.util.List<Items> items = response.getItems();
                List<Item> item = items.get(0).getItem();
//                for(int i=0;i<item.get(0).getEditorialReviews().getEditorialReview().size();i++) {
//                	System.out.println(item.get(0).getEditorialReviews().getEditorialReview().get(i).getContent());
//                }
                resp.getWriter().write("{data:[");	//Start JSON array
                for (int j = 0; j < item.size(); j++) {
                    AmazonResult temp = new AmazonResult(item.get(j));
                    req.getSession().setAttribute(temp.getISBN(), temp);
                    resp.getWriter().write(formatJsonResult(temp));
                    if(j<item.size()-1) resp.getWriter().write(",");
                //System.out.println(temp.getTitle()+": "+temp.getISBN());
                //System.out.println("at library: "+query.atLibrary(isbns));
                }
                resp.getWriter().write("]}");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                resp.getWriter().write("error!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private String formatJsonResult(AmazonResult book) {
    	String temp = "{"+
			"'small_url': '"+(book.getSmallImageUrl().equals("")?"images/blank_medium.jpg":book.getSmallImageUrl())+"',"+
			"'title': '"+book.getTitle()+"',"+
			"'author': '"+book.getAuthorAsString()+"',"+
			"'amaz_rating': '"+book.getRating()+"',"+
			"'tags': '"+book.getTagsAsString()+"',"+
			"'detailUrl': '"+book.getDetailUrl()+"'";
    		
		return temp+"}";
    }
}
