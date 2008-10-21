package com.softwaresmithy.amazon;

import java.io.IOException;
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
        if (dummy != null && dummy.equals("true")) {
            for (int i = 0; i < 3; i++) {
                try {
                    resp.getWriter().write(dummyResponse());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                resp.getWriter().write("json = {data:[");	//Start JSON array
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
			"'medium_url': '"+book.getMediumImageUrl()+"',"+
			"'title': '"+book.getTitle()+"',"+
			"'author': '"+book.getAuthorAsString()+"',"+
			"'amaz_rating': '"+book.getRating()+"',"+
			"'tags': '"+book.getTagsAsString()+"'";
		return temp+"}";
    }
    private String formatHttpResult(AmazonResult book) {
        String temp = "";
        temp += "<div class='round'>" +
                "<img class='cover' src='" + book.getSmallImageUrl() + "'></img>" +
                "<div class='info'>" +
                "<div class='bio'>" +
                "<div class='title'>" + book.getTitle() + "</div>" +
                "<div class='author'>by " + book.getAuthor().get(0) + "</div>" + // TODO Handle multiple authors 
                "<div class='tags'><%= 'apple, banana, cranberry, author, title'%></div>" +
                "</div>" +
                "<div class='right'>" +
                "<div class='rating'>* * * * *</div>" +
                "<div class='links'>" +
                "<div class='library'>Reserve from Library</div>" +
                "<div class='amazon'>Buy at Amazon</div>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</div>";

        return temp;
    }

    private String dummyResponse() {
        String temp = "";
        temp += "<div class='round'>" +
                "<img class='cover' src='" + "http://ecx.images-amazon.com/images/I/51yYy6tjdlL._SL75_.jpg" + "'></img>" +
                "<div class='info'>" +
                "<div class='bio'>" +
                "<div class='title'>" + "Scarlet" + "</div>" +
                "<div class='author'>by Stephen Lawhead</div><!--Handle multiple authors!-->" +
                "<div class='tags'><%= 'apple, banana, cranberry, author, title'%></div>" +
                "</div>" +
                "<div class='right'>" +
                "<div class='rating'>* * * * *</div>" +
                "<div class='links'>" +
                "<div class='library'>Reserve from Library</div>" +
                "<div class='amazon'>Buy at Amazon</div>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</div>";
        return temp;
    }
}
