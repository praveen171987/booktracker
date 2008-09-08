/******************************************************************************* 
 *  Copyright 2007 Amazon Technologies, Inc.  
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  
 *  You may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 *  CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 *  specific language governing permissions and limitations under the License.
 * ***************************************************************************** 
 *    __  _    _  ___ 
 *   (  )( \/\/ )/ __)
 *   /__\ \    / \__ \
 *  (_)(_) \/\/  (___/
 * 
 *  Amazon A2S Java Library
 *  API Version: 2007-10-29
 *  Generated: Thu Jan 10 05:27:32 PST 2008 
 * 
 */



package com.amazonaws.a2s.samples;

import com.amazonaws.a2s.AmazonA2S;
import com.amazonaws.a2s.AmazonA2SClient;
import com.amazonaws.a2s.model.AlternateVersion;
import com.amazonaws.a2s.model.CollectionItem;
import com.amazonaws.a2s.model.Image;
import com.amazonaws.a2s.model.Item;
import com.amazonaws.a2s.model.ItemSearchRequest;
import com.amazonaws.a2s.model.ItemSearchResponse;
import com.amazonaws.a2s.model.Items;
import com.amazonaws.a2s.model.Tag;

import java.util.*;
import com.softwaresmithy.acornweb.AcornWebQueryEngine;
/**
 *
 * Item Search  Samples
 *
 *
 */
public class ItemSearchSample {

    /**
     * Just add few required parameters, and try the service
     * Item Search functionality
     *
     * @param args unused
     */
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
        
        /************************************************************************
         * Uncomment to invoke library on different Locale. Available Locales:
         *  - US
         *  - UK
         *  - FR
         *  - JP
         *  - CA
         ***********************************************************************/
        // AmazonA2S service = new AmazonA2SClient(accessKeyId, associateTag, AmazonA2SLocale.US);

        /************************************************************************
         * Uncomment to try out Mock Service that simulates Amazon A2S 
         * responses without calling Amazon A2S  service.
         *
         * Responses are loaded from local XML files. You can tweak XML files to
         * experiment with various outputs during development
         *
         * XML files available under com/amazonaws/a2s/mock tree
         *
         ***********************************************************************/
        // AmazonA2S service = new AmazonA2SMock();

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Item Search Request
         ***********************************************************************/

         ItemSearchRequest request = new ItemSearchRequest();
        
         // @TODO: set request parameters here

         // invokeItemSearch(service, request);
         request.setSearchIndex("Books");
         
         request.setKeywords("Hood Stephen Lawhead");
         java.util.List<String> responseGroup = new java.util.ArrayList();
         
         //Valid values for ResponseGroup include Accessories, AlternateVersions, BrowseNodes, Collections, 
         //EditorialReview, Images, ItemAttributes, ItemIds, Large, ListmaniaLists, Medium, 
         //MerchantItemAttributes, OfferFull, OfferListings, OfferSummary, Offers, PromotionDetails, 
         //PromotionSummary, PromotionalTag, RelatedItems, Request, Reviews, SalesRank, SearchBins, 
         //SearchInside, Similarities, Small, Subjects, Tags, TagsSummary, Tracks, VariationMatrix, 
         //VariationMinimum, VariationOffers, VariationSummary, Variations.

         responseGroup.add("Small");
         responseGroup.add("AlternateVersions");
         responseGroup.add("Images");
         responseGroup.add("Tags");
         responseGroup.add("Reviews");
         request.setResponseGroup(responseGroup);
         //request.setAuthor("Stephen R. Lawhead");
         
         try{
             AcornWebQueryEngine query = new AcornWebQueryEngine();
             
             ItemSearchResponse response = service.itemSearch(request);

             java.util.List<Items> items = response.getItems();
             System.out.println("List<Items> size: "+items.size());
             List<Item> item = items.get(0).getItem();
             for(int j=0;j<item.size();j++){
                 java.util.List<String> isbns = new ArrayList();         
                 
                 if(item.get(j).isSetImageSets()){
                	 Image medium = item.get(j).getSmallImage();
                	 System.out.println(medium.getURL());
                 }
                 item.get(j).getItemAttributes().getAuthor().get(0);
                 if(item.get(j).isSetTags()){
                	 System.out.println("Printing Tags");
                	 List<Tag> tags = item.get(j).getTags().getTag();
                	 System.out.println("DistinctTags: "+item.get(j).getTags().getTotalUsages());
                	 for(int k=0;k<tags.size();k++){
                		 System.out.println(tags.get(k).getName()+": "+tags.get(k).getTotalUsages());
                	 }
                 }
                 if(item.get(j).isSetCustomerReviews()){
                	 System.out.println(item.get(j).getCustomerReviews().getAverageRating());
                	 System.out.println(item.get(j).getCustomerReviews().getTotalReviews());
                 }
                 
                 
                 isbns.add(item.get(j).getASIN());
                 System.out.println(item.get(j).getASIN());
                 if(item.get(j).isSetAlternateVersions()){
                    java.util.List<AlternateVersion> av = item.get(j).getAlternateVersions().getAlternateVersion();
                    System.out.println("Printing Alternate Versions");
                    for(int k=0;k<av.size();k++){
                        isbns.add(av.get(k).getASIN());
                        System.out.println("\t"+av.get(k).getASIN()+": "+av.get(k).getTitle());
                    }
                 }
                 //System.out.println("at library: "+query.atLibrary(isbns));
             }
             //invokeItemSearch(service, request);
         }catch(Exception e){
             e.printStackTrace();
         }
    }
}