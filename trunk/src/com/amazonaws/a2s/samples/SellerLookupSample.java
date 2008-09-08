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
 * Seller Lookup  Samples
 *
 *
 */
public class SellerLookupSample {

    /**
     * Just add few required parameters, and try the service
     * Seller Lookup functionality
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
         * sample for Seller Lookup Request
         ***********************************************************************/

         SellerLookupRequest request = new SellerLookupRequest();
        
         // @TODO: set request parameters here

         // invokeSellerLookup(service, request);

    }


                                                            
    /**
     * Seller Lookup Action Sample
     * 
     * <br><br>
     * The SellerLookup operation returns detailed information about sellers and,
     * in the US locale, merchants. To lookup a seller, you must use their seller ID.
     * The information returned includes the seller's name, location, average rating by
     * customers, and the first five customer feedback entries. SellerLookup will not,
     * however, return the seller's Email or business addresses.
     * <br><br>
     * <b>Available Response Groups</b>:
     * <ul>
     * <li>Request</li>
     * <li>Seller</li>
     * </ul>
     *   
     * @param service instance of AmazonA2S service
     * @param request  SellerLookup request
     */
    public static void invokeSellerLookup(AmazonA2S service, SellerLookupRequest... request) {
        try {

            SellerLookupResponse response = service.sellerLookup(request);

            
            System.out.println ("SellerLookup Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    SellerLookupResponse");
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
            java.util.List<Sellers> sellerssList = response.getSellers();
            for (Sellers sellers : sellerssList) {
                System.out.print("        Sellers");
                System.out.println();
                if (sellers.isSetTotalResults()) {
                    System.out.print("            TotalResults");
                    System.out.println();
                    System.out.print("                " + sellers.getTotalResults());
                    System.out.println();
                }
                if (sellers.isSetTotalPages()) {
                    System.out.print("            TotalPages");
                    System.out.println();
                    System.out.print("                " + sellers.getTotalPages());
                    System.out.println();
                }
                java.util.List<Seller> sellersList = sellers.getSeller();
                for (Seller seller : sellersList) {
                    System.out.print("            Seller");
                    System.out.println();
                    if (seller.isSetSellerId()) {
                        System.out.print("                SellerId");
                        System.out.println();
                        System.out.print("                    " + seller.getSellerId());
                        System.out.println();
                    }
                    if (seller.isSetSellerName()) {
                        System.out.print("                SellerName");
                        System.out.println();
                        System.out.print("                    " + seller.getSellerName());
                        System.out.println();
                    }
                    if (seller.isSetSellerLegalName()) {
                        System.out.print("                SellerLegalName");
                        System.out.println();
                        System.out.print("                    " + seller.getSellerLegalName());
                        System.out.println();
                    }
                    if (seller.isSetNickname()) {
                        System.out.print("                Nickname");
                        System.out.println();
                        System.out.print("                    " + seller.getNickname());
                        System.out.println();
                    }
                    if (seller.isSetGlancePage()) {
                        System.out.print("                GlancePage");
                        System.out.println();
                        System.out.print("                    " + seller.getGlancePage());
                        System.out.println();
                    }
                    if (seller.isSetAbout()) {
                        System.out.print("                About");
                        System.out.println();
                        System.out.print("                    " + seller.getAbout());
                        System.out.println();
                    }
                    if (seller.isSetMoreAbout()) {
                        System.out.print("                MoreAbout");
                        System.out.println();
                        System.out.print("                    " + seller.getMoreAbout());
                        System.out.println();
                    }
                    if (seller.isSetLocation()) {
                        System.out.print("                Location");
                        System.out.println();
                        SellerLocation  location = seller.getLocation();
                        if (location.isSetUserDefinedLocation()) {
                            System.out.print("                    UserDefinedLocation");
                            System.out.println();
                            System.out.print("                        " + location.getUserDefinedLocation());
                            System.out.println();
                        }
                        if (location.isSetCity()) {
                            System.out.print("                    City");
                            System.out.println();
                            System.out.print("                        " + location.getCity());
                            System.out.println();
                        }
                        if (location.isSetState()) {
                            System.out.print("                    State");
                            System.out.println();
                            System.out.print("                        " + location.getState());
                            System.out.println();
                        }
                        if (location.isSetCountry()) {
                            System.out.print("                    Country");
                            System.out.println();
                            System.out.print("                        " + location.getCountry());
                            System.out.println();
                        }
                    } 
                    if (seller.isSetAverageFeedbackRating()) {
                        System.out.print("                AverageFeedbackRating");
                        System.out.println();
                        System.out.print("                    " + seller.getAverageFeedbackRating());
                        System.out.println();
                    }
                    if (seller.isSetTotalFeedback()) {
                        System.out.print("                TotalFeedback");
                        System.out.println();
                        System.out.print("                    " + seller.getTotalFeedback());
                        System.out.println();
                    }
                    if (seller.isSetTotalFeedbackPages()) {
                        System.out.print("                TotalFeedbackPages");
                        System.out.println();
                        System.out.print("                    " + seller.getTotalFeedbackPages());
                        System.out.println();
                    }
                    if (seller.isSetSellerFeedbackSummary()) {
                        System.out.print("                SellerFeedbackSummary");
                        System.out.println();
                        SellerFeedbackSummary  sellerFeedbackSummary = seller.getSellerFeedbackSummary();
                        java.util.List<FeedbackDateRange> feedbackDateRangesList = sellerFeedbackSummary.getFeedbackDateRange();
                        for (FeedbackDateRange feedbackDateRange : feedbackDateRangesList) {
                            System.out.print("                    FeedbackDateRange");
                            System.out.println();
                            java.util.List<SellerFeedbackRating> sellerFeedbackRatingsList = feedbackDateRange.getSellerFeedbackRating();
                            for (SellerFeedbackRating sellerFeedbackRating : sellerFeedbackRatingsList) {
                                System.out.print("                        SellerFeedbackRating");
                                System.out.println();
                                if (sellerFeedbackRating.isSetCount()) {
                                    System.out.print("                            Count");
                                    System.out.println();
                                    System.out.print("                                " + sellerFeedbackRating.getCount());
                                    System.out.println();
                                }
                                if (sellerFeedbackRating.isSetPercentage()) {
                                    System.out.print("                            Percentage");
                                    System.out.println();
                                    System.out.print("                                " + sellerFeedbackRating.getPercentage());
                                    System.out.println();
                                }
                            }
                        }
                    } 
                    if (seller.isSetSellerFeedback()) {
                        System.out.print("                SellerFeedback");
                        System.out.println();
                        SellerFeedback  sellerFeedback = seller.getSellerFeedback();
                        java.util.List<Feedback> feedbacksList = sellerFeedback.getFeedback();
                        for (Feedback feedback : feedbacksList) {
                            System.out.print("                    Feedback");
                            System.out.println();
                            if (feedback.isSetRating()) {
                                System.out.print("                        Rating");
                                System.out.println();
                                System.out.print("                            " + feedback.getRating());
                                System.out.println();
                            }
                            if (feedback.isSetComment()) {
                                System.out.print("                        Comment");
                                System.out.println();
                                System.out.print("                            " + feedback.getComment());
                                System.out.println();
                            }
                            if (feedback.isSetDate()) {
                                System.out.print("                        Date");
                                System.out.println();
                                System.out.print("                            " + feedback.getDate());
                                System.out.println();
                            }
                            if (feedback.isSetRatedBy()) {
                                System.out.print("                        RatedBy");
                                System.out.println();
                                System.out.print("                            " + feedback.getRatedBy());
                                System.out.println();
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
