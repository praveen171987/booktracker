package com.softwaresmithy.amazon;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.a2s.AmazonA2S;
import com.amazonaws.a2s.AmazonA2SClient;
import com.amazonaws.a2s.model.AlternateVersion;
import com.amazonaws.a2s.model.Image;
import com.amazonaws.a2s.model.Item;
import com.amazonaws.a2s.model.ItemSearchRequest;
import com.amazonaws.a2s.model.ItemSearchResponse;
import com.amazonaws.a2s.model.Items;
import com.amazonaws.a2s.model.Tag;
import com.softwaresmithy.acornweb.AcornWebQueryEngine;

public class AmazonQuery {
	public static void main(String... args) {
        
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
        
         ItemSearchRequest request = new ItemSearchRequest();
        
         request.setSearchIndex("Books");
         request.setKeywords("Hood Stephen Lawhead");
         
         //Valid values for ResponseGroup include Accessories, AlternateVersions, BrowseNodes, Collections, 
         //EditorialReview, Images, ItemAttributes, ItemIds, Large, ListmaniaLists, Medium, 
         //MerchantItemAttributes, OfferFull, OfferListings, OfferSummary, Offers, PromotionDetails, 
         //PromotionSummary, PromotionalTag, RelatedItems, Request, Reviews, SalesRank, SearchBins, 
         //SearchInside, Similarities, Small, Subjects, Tags, TagsSummary, Tracks, VariationMatrix, 
         //VariationMinimum, VariationOffers, VariationSummary, Variations.
         java.util.List<String> responseGroup = new java.util.ArrayList();
	         responseGroup.add("Small");
	         responseGroup.add("AlternateVersions");
	         responseGroup.add("Images");
	         responseGroup.add("Tags");
	         responseGroup.add("Reviews");
	         request.setResponseGroup(responseGroup);
         
         try{
             //AcornWebQueryEngine query = new AcornWebQueryEngine();
             
             ItemSearchResponse response = service.itemSearch(request);

             java.util.List<Items> items = response.getItems();
             System.out.println("List<Items> size: "+items.size());
             List<Item> item = items.get(0).getItem();
             for(int j=0;j<item.size();j++){
                 AmazonResult temp = new AmazonResult(item.get(j));
                 System.out.println(temp.getTitle()+": "+temp.getISBN());
                 //System.out.println("at library: "+query.atLibrary(isbns));
             }
             //invokeItemSearch(service, request);
         }catch(Exception e){
             e.printStackTrace();
         }
    }
}
