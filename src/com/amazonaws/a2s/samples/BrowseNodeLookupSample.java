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
 * Browse Node Lookup  Samples
 *
 *
 */
public class BrowseNodeLookupSample {

    /**
     * Just add few required parameters, and try the service
     * Browse Node Lookup functionality
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
         * sample for Browse Node Lookup Request
         ***********************************************************************/

         BrowseNodeLookupRequest request = new BrowseNodeLookupRequest();
        
         // @TODO: set request parameters here

         // invokeBrowseNodeLookup(service, request);

    }


                                    
    /**
     * Browse Node Lookup Action Sample
     * 
     * <br><br>
     * Given a browse node ID, BrowseNodeLookup returns the specified browse node's name, children, and ancestors.
     * The names and browse node IDs of the children and ancestor browse nodes are also returned.
     * BrowseNodeLookup enables you to traverse the browse node hierarchy to find a browse node.
     * As you traverse down the hierarchy, you refine your search and limit the number of items returned.
     * For example, you might traverse the following hierarchy: DVD Used DVDs Kids and Family,
     * to select out of all the DVDs offered by Amazon only those that are appropriate for family viewing.
     * Returning the items associated with Kids and Family produces a much more targeted result than a search
     * based at the level of Used DVDs.
     * <br><br>
     * Alternatively, by traversing up the browse node tree, you can determine the root category of an item.
     * You might do that, for example, to return the top seller of the root product category using the
     * TopSeller response group in an ItemSearch request.
     * <br><br>
     * You can use BrowseNodeLookup iteratively to navigate through the browse node hierarchy to
     * reach the node that most appropriately suits your search. Then you can use the browse node ID in
     * an ItemSearch request. This response would be far more targeted than, for example,
     * searching through all of the browse nodes in a search index.
     * <br><br>
     * <b>Available Response Groups</b>:
     * <ul>
     * <li>Request</li>
     * <li>BrowseNodeInfo</li>
     * </ul>
     *   
     * @param service instance of AmazonA2S service
     * @param request  BrowseNodeLookup request
     */
    public static void invokeBrowseNodeLookup(AmazonA2S service, BrowseNodeLookupRequest... request) {
        try {

            BrowseNodeLookupResponse response = service.browseNodeLookup(request);

            
            System.out.println ("BrowseNodeLookup Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    BrowseNodeLookupResponse");
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
            java.util.List<BrowseNodes> browseNodessList = response.getBrowseNodes();
            for (BrowseNodes browseNodes : browseNodessList) {
                System.out.print("        BrowseNodes");
                System.out.println();
                java.util.List<BrowseNode> browseNodesList = browseNodes.getBrowseNode();
                for (BrowseNode browseNode : browseNodesList) {
                    System.out.print("            BrowseNode");
                    System.out.println();
                    if (browseNode.isSetBrowseNodeId()) {
                        System.out.print("                BrowseNodeId");
                        System.out.println();
                        System.out.print("                    " + browseNode.getBrowseNodeId());
                        System.out.println();
                    }
                    if (browseNode.isSetName()) {
                        System.out.print("                Name");
                        System.out.println();
                        System.out.print("                    " + browseNode.getName());
                        System.out.println();
                    }
                    if (browseNode.isSetIsCategoryRoot()) {
                        System.out.print("                IsCategoryRoot");
                        System.out.println();
                        System.out.print("                    " + browseNode.isIsCategoryRoot());
                        System.out.println();
                    }
                    if (browseNode.isSetProperties()) {
                        System.out.print("                Properties");
                        System.out.println();
                        BrowseNodeProperties  properties = browseNode.getProperties();
                        java.util.List<Property> propertysList = properties.getProperty();
                        for (Property property : propertysList) {
                            System.out.print("                    Property");
                            System.out.println();
                            if (property.isSetName()) {
                                System.out.print("                        Name");
                                System.out.println();
                                System.out.print("                            " + property.getName());
                                System.out.println();
                            }
                            if (property.isSetValue()) {
                                System.out.print("                        Value");
                                System.out.println();
                                System.out.print("                            " + property.getValue());
                                System.out.println();
                            }
                        }
                    } 
                    if (browseNode.isSetChildren()) {
                        System.out.print("                Children");
                        System.out.println();
                        BrowseNodeChildren  children = browseNode.getChildren();
                    } 
                    if (browseNode.isSetAncestors()) {
                        System.out.print("                Ancestors");
                        System.out.println();
                        BrowseNodeAncestors  ancestors = browseNode.getAncestors();
                    } 
                    if (browseNode.isSetTopSellers()) {
                        System.out.print("                TopSellers");
                        System.out.println();
                        TopSellers  topSellers = browseNode.getTopSellers();
                        java.util.List<TopSeller> topSellersList = topSellers.getTopSeller();
                        for (TopSeller topSeller : topSellersList) {
                            System.out.print("                    TopSeller");
                            System.out.println();
                            if (topSeller.isSetASIN()) {
                                System.out.print("                        ASIN");
                                System.out.println();
                                System.out.print("                            " + topSeller.getASIN());
                                System.out.println();
                            }
                            if (topSeller.isSetTitle()) {
                                System.out.print("                        Title");
                                System.out.println();
                                System.out.print("                            " + topSeller.getTitle());
                                System.out.println();
                            }
                        }
                    } 
                    if (browseNode.isSetNewReleases()) {
                        System.out.print("                NewReleases");
                        System.out.println();
                        NewReleases  newReleases = browseNode.getNewReleases();
                        java.util.List<NewRelease> newReleasesList = newReleases.getNewRelease();
                        for (NewRelease newRelease : newReleasesList) {
                            System.out.print("                    NewRelease");
                            System.out.println();
                            if (newRelease.isSetASIN()) {
                                System.out.print("                        ASIN");
                                System.out.println();
                                System.out.print("                            " + newRelease.getASIN());
                                System.out.println();
                            }
                            if (newRelease.isSetTitle()) {
                                System.out.print("                        Title");
                                System.out.println();
                                System.out.print("                            " + newRelease.getTitle());
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
