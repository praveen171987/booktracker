package com.softwaresmithy.amazon;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        String keyword = req.getParameter("keyword");
        boolean nextPage = false, prevPage = false; 
        if(req.getParameter("nextPage")!= null && req.getParameter("nextPage").equals("true"))
        	nextPage = true; 
        if(req.getParameter("prevPage")!= null && req.getParameter("prevPage").equals("true"))
        	prevPage = true; 
        
        HttpSession sess = req.getSession();

        if (keyword != null && !keyword.equals("")) {
            request.setKeywords(keyword);
            sess.setAttribute("keywords", keyword);
            sess.setAttribute("amazCurrPage", 1);
            //remove existing session isbns
            Enumeration<String> names = sess.getAttributeNames();
            Stack<String> deleteMe = new Stack<String>();
            String temp = "";
            while(names.hasMoreElements()){
            	temp = names.nextElement();
            	if(temp.startsWith("isbn:")){
            		deleteMe.push(temp);
            	}
            }
            while(!deleteMe.empty()){
            	sess.removeAttribute(deleteMe.pop());
            }

        } else if(keyword == null && nextPage){
        	int currPage = Integer.parseInt(sess.getAttribute("amazCurrPage").toString());
        	request.setKeywords((String)sess.getAttribute("keywords"));
        	request.setItemPage(new BigInteger(Integer.toString(currPage+1)));
        	sess.setAttribute("amazCurrPage", currPage+1);
        } else if(keyword == null && prevPage){
        	int currPage = Integer.parseInt(sess.getAttribute("amazCurrPage").toString());
        	request.setKeywords((String)sess.getAttribute("keywords"));
        	request.setItemPage(new BigInteger(Integer.toString(currPage-1)));
        	sess.setAttribute("amazCurrPage", currPage-1);
        }else {
            try {
                resp.getWriter().write("error!");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            //AcornWebQueryEngine query = new AcornWebQueryEngine();

            ItemSearchResponse response = service.itemSearch(request);
            BigInteger totPages = response.getItems().get(0).getTotalPages();
            java.util.List<Items> items = response.getItems();
            List<Item> item = items.get(0).getItem();
//            for(int i=0;i<item.get(0).getEditorialReviews().getEditorialReview().size();i++) {
//            	System.out.println(item.get(0).getEditorialReviews().getEditorialReview().get(i).getContent());
//            }
            resp.getWriter().write("{data:[");	//Start JSON array
            for (int j = 0; j < item.size(); j++) {
                AmazonResult temp = new AmazonResult(item.get(j));
                sess.setAttribute("isbn:"+temp.getISBN(), temp);
                resp.getWriter().write(formatJsonResult(temp));
                if(j<item.size()-1) resp.getWriter().write(",");
            //System.out.println(temp.getTitle()+": "+temp.getISBN());
            //System.out.println("at library: "+query.atLibrary(isbns));
            }
            resp.getWriter().write("],");
            resp.getWriter().write("totPages: "+totPages.intValue()+"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private String formatJsonResult(AmazonResult book) {
    	String temp = "{"+
    		"'isbn': '"+book.getISBN()+"',"+
    		"'title': '"+book.getTitle()+"',"+
			"'author': '"+book.getAuthorAsString()+"',"+
			"'amaz_rating': '"+book.getRating()+"',"+
			"'pub_date': '"+book.getPubDate()+"',"+
			"'pages': '"+book.getPages()+"',"+
			"'small_url': '"+(book.getSmallImageUrl().equals("")?"images/blank_small.jpg":book.getSmallImageUrl())+"',"+
			"'medium_url': '"+(book.getMediumImageUrl().equals("")?"images/blank_medium.jpg":book.getMediumImageUrl())+"',"+
			"'large_url': '"+(book.getLargeImageUrl().equals("")?"images/blank_large.jpg":book.getLargeImageUrl())+"',"+
			"'tags': '"+book.getTagsAsString()+"',"+
			"'detailUrl': '"+book.getDetailUrl()+"'";
    		
		return temp+"}";
    }
}
