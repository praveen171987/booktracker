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

import com.amazonaws.a2s.model.*;
import com.amazonaws.a2s.*;
import com.amazonaws.a2s.AmazonA2SClient;
import com.amazonaws.a2s.mock.AmazonA2SMock;

/**
 *
 * Seller Listing Search  Samples
 *
 *
 */
public class SellerListingSearchSample {

    /**
     * Just add few required parameters, and try the service
     * Seller Listing Search functionality
     *
     * @param args unused
     */
    public static void main(String... args) {
        
        /************************************************************************
         * Access Key ID obtained from:
         * http://aws.amazon.com
         ***********************************************************************/
        String accessKeyId = "<Your Access Key ID>";
        String associateTag = "<Your Associate Tag>";
        
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
         * sample for Seller Listing Search Request
         ***********************************************************************/

         SellerListingSearchRequest request = new SellerListingSearchRequest();
        
         // @TODO: set request parameters here

         // invokeSellerListingSearch(service, request);

    }


                                                                                        
    /**
     * Seller Listing Search Action Sample
     * 
     * <br><br>
     * The SellerListingSearch operation enables you to search for items offered
     * by specific sellers. You cannot use SellerListingSearch to look up items sold by merchants.
     * To look up an item sold by a merchant, use  ItemLookup   or  ItemSearch
     * along with the MerchantId parameter.
     * <br><br>
     * SellerListingSearch returns the listing ID or exchange ID of an item.
     * Typically, you use those values with SellerListingLookup to find out more about those items.
     * <br><br>
     * Each SellerListingSearch request returns up to ten items. By default, the first ten
     * items are returned. You can use the ListingPage parameter to retrieve additional pages
     * of (up to) ten listings.
     * <br><br>
     * To use A2S, sellers must have less than 100,000 items for sale. Sellers that have more
     * items for sale should use, instead of A2S, other seller APIs, including the Amazon
     * Inventory Management System, and the Merchant@ API.
     * <br><br>
     * <b>Available Response Groups</b>:
     * <ul>
     * <li>Request</li>
     * <li>SellerListing</li>
     * </ul>
     *   
     * @param service instance of AmazonA2S service
     * @param request  SellerListingSearch request
     */
    public static void invokeSellerListingSearch(AmazonA2S service, SellerListingSearchRequest... request) {
        try {

            SellerListingSearchResponse response = service.sellerListingSearch(request);

            
            System.out.println ("SellerListingSearch Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    SellerListingSearchResponse");
            System.out.println();
            if (response.isSetOperationRequest()) {
                System.out.print("        OperationRequest");
                System.out.println();
                OperationRequest  operationRequest = response.getOperationRequest();
                if (operationRequest.isSetHTTPHeaders()) {
                    System.out.print("            HTTPHeaders");
                    System.out.println();
                    HTTPHeaders  HTTPHeaders = operationRequest.getHTTPHeaders();
                } 
                if (operationRequest.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + operationRequest.getRequestId());
                    System.out.println();
                }
                if (operationRequest.isSetArguments()) {
                    System.out.print("            Arguments");
                    System.out.println();
                    Arguments  arguments = operationRequest.getArguments();
                } 
                if (operationRequest.isSetRequestProcessingTime()) {
                    System.out.print("            RequestProcessingTime");
                    System.out.println();
                    System.out.print("                " + operationRequest.getRequestProcessingTime());
                    System.out.println();
                }
            } 
            java.util.List<SellerListings> sellerListingssList = response.getSellerListings();
            for (SellerListings sellerListings : sellerListingssList) {
                System.out.print("        SellerListings");
                System.out.println();
                if (sellerListings.isSetTotalResults()) {
                    System.out.print("            TotalResults");
                    System.out.println();
                    System.out.print("                " + sellerListings.getTotalResults());
                    System.out.println();
                }
                if (sellerListings.isSetTotalPages()) {
                    System.out.print("            TotalPages");
                    System.out.println();
                    System.out.print("                " + sellerListings.getTotalPages());
                    System.out.println();
                }
                java.util.List<SellerListing> sellerListingsList = sellerListings.getSellerListing();
                for (SellerListing sellerListing : sellerListingsList) {
                    System.out.print("            SellerListing");
                    System.out.println();
                    if (sellerListing.isSetExchangeId()) {
                        System.out.print("                ExchangeId");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getExchangeId());
                        System.out.println();
                    }
                    if (sellerListing.isSetListingId()) {
                        System.out.print("                ListingId");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getListingId());
                        System.out.println();
                    }
                    if (sellerListing.isSetASIN()) {
                        System.out.print("                ASIN");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getASIN());
                        System.out.println();
                    }
                    if (sellerListing.isSetSKU()) {
                        System.out.print("                SKU");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getSKU());
                        System.out.println();
                    }
                    if (sellerListing.isSetUPC()) {
                        System.out.print("                UPC");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getUPC());
                        System.out.println();
                    }
                    if (sellerListing.isSetEAN()) {
                        System.out.print("                EAN");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getEAN());
                        System.out.println();
                    }
                    if (sellerListing.isSetWillShipExpedited()) {
                        System.out.print("                WillShipExpedited");
                        System.out.println();
                        System.out.print("                    " + sellerListing.isWillShipExpedited());
                        System.out.println();
                    }
                    if (sellerListing.isSetWillShipInternational()) {
                        System.out.print("                WillShipInternational");
                        System.out.println();
                        System.out.print("                    " + sellerListing.isWillShipInternational());
                        System.out.println();
                    }
                    if (sellerListing.isSetTitle()) {
                        System.out.print("                Title");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getTitle());
                        System.out.println();
                    }
                    if (sellerListing.isSetPrice()) {
                        System.out.print("                Price");
                        System.out.println();
                        Price  price = sellerListing.getPrice();
                        if (price.isSetAmount()) {
                            System.out.print("                    Amount");
                            System.out.println();
                            System.out.print("                        " + price.getAmount());
                            System.out.println();
                        }
                        if (price.isSetCurrencyCode()) {
                            System.out.print("                    CurrencyCode");
                            System.out.println();
                            System.out.print("                        " + price.getCurrencyCode());
                            System.out.println();
                        }
                        if (price.isSetFormattedPrice()) {
                            System.out.print("                    FormattedPrice");
                            System.out.println();
                            System.out.print("                        " + price.getFormattedPrice());
                            System.out.println();
                        }
                    } 
                    if (sellerListing.isSetStartDate()) {
                        System.out.print("                StartDate");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getStartDate());
                        System.out.println();
                    }
                    if (sellerListing.isSetEndDate()) {
                        System.out.print("                EndDate");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getEndDate());
                        System.out.println();
                    }
                    if (sellerListing.isSetStatus()) {
                        System.out.print("                Status");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getStatus());
                        System.out.println();
                    }
                    if (sellerListing.isSetQuantity()) {
                        System.out.print("                Quantity");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getQuantity());
                        System.out.println();
                    }
                    if (sellerListing.isSetCondition()) {
                        System.out.print("                Condition");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getCondition());
                        System.out.println();
                    }
                    if (sellerListing.isSetSubCondition()) {
                        System.out.print("                SubCondition");
                        System.out.println();
                        System.out.print("                    " + sellerListing.getSubCondition());
                        System.out.println();
                    }
                    if (sellerListing.isSetSeller()) {
                        System.out.print("                Seller");
                        System.out.println();
                        Seller  seller = sellerListing.getSeller();
                        if (seller.isSetSellerId()) {
                            System.out.print("                    SellerId");
                            System.out.println();
                            System.out.print("                        " + seller.getSellerId());
                            System.out.println();
                        }
                        if (seller.isSetSellerName()) {
                            System.out.print("                    SellerName");
                            System.out.println();
                            System.out.print("                        " + seller.getSellerName());
                            System.out.println();
                        }
                        if (seller.isSetSellerLegalName()) {
                            System.out.print("                    SellerLegalName");
                            System.out.println();
                            System.out.print("                        " + seller.getSellerLegalName());
                            System.out.println();
                        }
                        if (seller.isSetNickname()) {
                            System.out.print("                    Nickname");
                            System.out.println();
                            System.out.print("                        " + seller.getNickname());
                            System.out.println();
                        }
                        if (seller.isSetGlancePage()) {
                            System.out.print("                    GlancePage");
                            System.out.println();
                            System.out.print("                        " + seller.getGlancePage());
                            System.out.println();
                        }
                        if (seller.isSetAbout()) {
                            System.out.print("                    About");
                            System.out.println();
                            System.out.print("                        " + seller.getAbout());
                            System.out.println();
                        }
                        if (seller.isSetMoreAbout()) {
                            System.out.print("                    MoreAbout");
                            System.out.println();
                            System.out.print("                        " + seller.getMoreAbout());
                            System.out.println();
                        }
                        if (seller.isSetLocation()) {
                            System.out.print("                    Location");
                            System.out.println();
                            SellerLocation  location = seller.getLocation();
                            if (location.isSetUserDefinedLocation()) {
                                System.out.print("                        UserDefinedLocation");
                                System.out.println();
                                System.out.print("                            " + location.getUserDefinedLocation());
                                System.out.println();
                            }
                            if (location.isSetCity()) {
                                System.out.print("                        City");
                                System.out.println();
                                System.out.print("                            " + location.getCity());
                                System.out.println();
                            }
                            if (location.isSetState()) {
                                System.out.print("                        State");
                                System.out.println();
                                System.out.print("                            " + location.getState());
                                System.out.println();
                            }
                            if (location.isSetCountry()) {
                                System.out.print("                        Country");
                                System.out.println();
                                System.out.print("                            " + location.getCountry());
                                System.out.println();
                            }
                        } 
                        if (seller.isSetAverageFeedbackRating()) {
                            System.out.print("                    AverageFeedbackRating");
                            System.out.println();
                            System.out.print("                        " + seller.getAverageFeedbackRating());
                            System.out.println();
                        }
                        if (seller.isSetTotalFeedback()) {
                            System.out.print("                    TotalFeedback");
                            System.out.println();
                            System.out.print("                        " + seller.getTotalFeedback());
                            System.out.println();
                        }
                        if (seller.isSetTotalFeedbackPages()) {
                            System.out.print("                    TotalFeedbackPages");
                            System.out.println();
                            System.out.print("                        " + seller.getTotalFeedbackPages());
                            System.out.println();
                        }
                        if (seller.isSetSellerFeedbackSummary()) {
                            System.out.print("                    SellerFeedbackSummary");
                            System.out.println();
                            SellerFeedbackSummary  sellerFeedbackSummary = seller.getSellerFeedbackSummary();
                            java.util.List<FeedbackDateRange> feedbackDateRangesList = sellerFeedbackSummary.getFeedbackDateRange();
                            for (FeedbackDateRange feedbackDateRange : feedbackDateRangesList) {
                                System.out.print("                        FeedbackDateRange");
                                System.out.println();
                                java.util.List<SellerFeedbackRating> sellerFeedbackRatingsList = feedbackDateRange.getSellerFeedbackRating();
                                for (SellerFeedbackRating sellerFeedbackRating : sellerFeedbackRatingsList) {
                                    System.out.print("                            SellerFeedbackRating");
                                    System.out.println();
                                    if (sellerFeedbackRating.isSetCount()) {
                                        System.out.print("                                Count");
                                        System.out.println();
                                        System.out.print("                                    " + sellerFeedbackRating.getCount());
                                        System.out.println();
                                    }
                                    if (sellerFeedbackRating.isSetPercentage()) {
                                        System.out.print("                                Percentage");
                                        System.out.println();
                                        System.out.print("                                    " + sellerFeedbackRating.getPercentage());
                                        System.out.println();
                                    }
                                }
                            }
                        } 
                        if (seller.isSetSellerFeedback()) {
                            System.out.print("                    SellerFeedback");
                            System.out.println();
                            SellerFeedback  sellerFeedback = seller.getSellerFeedback();
                            java.util.List<Feedback> feedbacksList = sellerFeedback.getFeedback();
                            for (Feedback feedback : feedbacksList) {
                                System.out.print("                        Feedback");
                                System.out.println();
                                if (feedback.isSetRating()) {
                                    System.out.print("                            Rating");
                                    System.out.println();
                                    System.out.print("                                " + feedback.getRating());
                                    System.out.println();
                                }
                                if (feedback.isSetComment()) {
                                    System.out.print("                            Comment");
                                    System.out.println();
                                    System.out.print("                                " + feedback.getComment());
                                    System.out.println();
                                }
                                if (feedback.isSetDate()) {
                                    System.out.print("                            Date");
                                    System.out.println();
                                    System.out.print("                                " + feedback.getDate());
                                    System.out.println();
                                }
                                if (feedback.isSetRatedBy()) {
                                    System.out.print("                            RatedBy");
                                    System.out.println();
                                    System.out.print("                                " + feedback.getRatedBy());
                                    System.out.println();
                                }
                            }
                        } 
                    } 
                }
            }
            System.out.println();

           
        } catch (AmazonA2SException ex) {
            
            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
        }
    }
            
}
