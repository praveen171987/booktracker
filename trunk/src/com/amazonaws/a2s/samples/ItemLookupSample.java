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
 * Item Lookup  Samples
 *
 *
 */
public class ItemLookupSample {

    /**
     * Just add few required parameters, and try the service
     * Item Lookup functionality
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
        //AmazonA2S service = new AmazonA2SClient(accessKeyId, associateTag);
        
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
         AmazonA2S service = new AmazonA2SMock();

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Item Lookup Request
         ***********************************************************************/

         ItemLookupRequest request = new ItemLookupRequest();
        
         // @TODO: set request parameters here

         // invokeItemLookup(service, request);

    }


                                
    /**
     * Item Lookup Action Sample
     * 
     * <br><br>
     * Given an Item identifier, the ItemLookup operation returns some or all
     * of the item attributes, depending on the response group specified in the request.
     * By default, ItemLookup returns an item's ASIN, DetailPageURL, Manufacturer,
     * ProductGroup, and Title of the item.
     * <br><br>
     * ItemLookup supports many response groups, so you can retrieve many different
     * kinds of product information, called item attributes, including product reviews,
     * variations, similar products, pricing, availability, images of products, accessories,
     * and other information.
     * <br><br>
     * To look up more than one item at a time, separate the item identifiers by commas.
     * <br><br>
     * <b>Available Response Groups</b>:
     * <ul>
     * <li>Request</li>
     * <li>Small</li>
     * <li>Accessories</li>
     * <li>BrowseNodes</li>
     * <li>EditorialReview</li>
     * <li>Images</li>
     * <li>ItemAttributes</li>
     * <li>ItemIds</li>
     * <li>Large</li>
     * <li>ListmaniaLists</li>
     * <li>Medium</li>
     * <li>MerchantItemAttributes</li>
     * <li>OfferFull</li>
     * <li>Offers</li>
     * <li>OfferSummary</li>
     * <li>Reviews</li>
     * <li>SalesRank</li>
     * <li>Similarities</li>
     * <li>Subjects</li>
     * <li>Tracks</li>
     * <li>TagsSummary</li>
     * <li>Tags</li>
     * <li>VariationImages</li>
     * <li>VariationMinimum</li>
     * <li>Variations</li>
     * <li>VariationSummary</li>
     * </ul>
     *   
     * @param service instance of AmazonA2S service
     * @param request  ItemLookup request
     */
    public static void invokeItemLookup(AmazonA2S service, ItemLookupRequest... request) {
        try {

            ItemLookupResponse response = service.itemLookup(request);

            
            System.out.println ("ItemLookup Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    ItemLookupResponse");
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
            java.util.List<Items> itemssList = response.getItems();
            for (Items items : itemssList) {
                System.out.print("        Items");
                System.out.println();
                if (items.isSetCorrectedQuery()) {
                    System.out.print("            CorrectedQuery");
                    System.out.println();
                    CorrectedQuery  correctedQuery = items.getCorrectedQuery();
                    if (correctedQuery.isSetKeywords()) {
                        System.out.print("                Keywords");
                        System.out.println();
                        System.out.print("                    " + correctedQuery.getKeywords());
                        System.out.println();
                    }
                    if (correctedQuery.isSetMessage()) {
                        System.out.print("                Message");
                        System.out.println();
                        System.out.print("                    " + correctedQuery.getMessage());
                        System.out.println();
                    }
                } 
                if (items.isSetQid()) {
                    System.out.print("            Qid");
                    System.out.println();
                    System.out.print("                " + items.getQid());
                    System.out.println();
                }
                if (items.isSetEngineQuery()) {
                    System.out.print("            EngineQuery");
                    System.out.println();
                    System.out.print("                " + items.getEngineQuery());
                    System.out.println();
                }
                if (items.isSetTotalResults()) {
                    System.out.print("            TotalResults");
                    System.out.println();
                    System.out.print("                " + items.getTotalResults());
                    System.out.println();
                }
                if (items.isSetTotalPages()) {
                    System.out.print("            TotalPages");
                    System.out.println();
                    System.out.print("                " + items.getTotalPages());
                    System.out.println();
                }
                if (items.isSetSearchResultsMap()) {
                    System.out.print("            SearchResultsMap");
                    System.out.println();
                    SearchResultsMap  searchResultsMap = items.getSearchResultsMap();
                    java.util.List<SearchResultsMapSearchIndex> searchIndexsList = searchResultsMap.getSearchIndex();
                    for (SearchResultsMapSearchIndex searchIndex : searchIndexsList) {
                        System.out.print("                SearchIndex");
                        System.out.println();
                        if (searchIndex.isSetIndexName()) {
                            System.out.print("                    IndexName");
                            System.out.println();
                            System.out.print("                        " + searchIndex.getIndexName());
                            System.out.println();
                        }
                        if (searchIndex.isSetResults()) {
                            System.out.print("                    Results");
                            System.out.println();
                            System.out.print("                        " + searchIndex.getResults());
                            System.out.println();
                        }
                        if (searchIndex.isSetPages()) {
                            System.out.print("                    Pages");
                            System.out.println();
                            System.out.print("                        " + searchIndex.getPages());
                            System.out.println();
                        }
                        if (searchIndex.isSetCorrectedQuery()) {
                            System.out.print("                    CorrectedQuery");
                            System.out.println();
                            CorrectedQuery  correctedQuery = searchIndex.getCorrectedQuery();
                            if (correctedQuery.isSetKeywords()) {
                                System.out.print("                        Keywords");
                                System.out.println();
                                System.out.print("                            " + correctedQuery.getKeywords());
                                System.out.println();
                            }
                            if (correctedQuery.isSetMessage()) {
                                System.out.print("                        Message");
                                System.out.println();
                                System.out.print("                            " + correctedQuery.getMessage());
                                System.out.println();
                            }
                        } 
                        if (searchIndex.isSetRelevanceRank()) {
                            System.out.print("                    RelevanceRank");
                            System.out.println();
                            System.out.print("                        " + searchIndex.getRelevanceRank());
                            System.out.println();
                        }
                        java.util.List<String> ASINsList  =  searchIndex.getASIN();
                        for (String ASIN : ASINsList) { 
                            System.out.print("                    ASIN");
                                System.out.println();
                            System.out.print("                        " + ASIN);
                        }	
                    }
                } 
                java.util.List<Item> itemsList = items.getItem();
                for (Item item : itemsList) {
                    System.out.print("            Item");
                    System.out.println();
                    if (item.isSetAlternateVersions()) {
                        System.out.print("                AlternateVersions");
                        System.out.println();
                        AlternateVersions  alternateVersions = item.getAlternateVersions();
                        java.util.List<AlternateVersion> alternateVersionsList = alternateVersions.getAlternateVersion();
                        for (AlternateVersion alternateVersion : alternateVersionsList) {
                            System.out.print("                    AlternateVersion");
                            System.out.println();
                            if (alternateVersion.isSetASIN()) {
                                System.out.print("                        ASIN");
                                System.out.println();
                                System.out.print("                            " + alternateVersion.getASIN());
                                System.out.println();
                            }
                            if (alternateVersion.isSetTitle()) {
                                System.out.print("                        Title");
                                System.out.println();
                                System.out.print("                            " + alternateVersion.getTitle());
                                System.out.println();
                            }
                            if (alternateVersion.isSetBinding()) {
                                System.out.print("                        Binding");
                                System.out.println();
                                System.out.print("                            " + alternateVersion.getBinding());
                                System.out.println();
                            }
                        }
                    } 
                    if (item.isSetASIN()) {
                        System.out.print("                ASIN");
                        System.out.println();
                        System.out.print("                    " + item.getASIN());
                        System.out.println();
                    }
                    if (item.isSetParentASIN()) {
                        System.out.print("                ParentASIN");
                        System.out.println();
                        System.out.print("                    " + item.getParentASIN());
                        System.out.println();
                    }
                    if (item.isSetDetailPageURL()) {
                        System.out.print("                DetailPageURL");
                        System.out.println();
                        System.out.print("                    " + item.getDetailPageURL());
                        System.out.println();
                    }
                    if (item.isSetSalesRank()) {
                        System.out.print("                SalesRank");
                        System.out.println();
                        System.out.print("                    " + item.getSalesRank());
                        System.out.println();
                    }
                    if (item.isSetSmallImage()) {
                        System.out.print("                SmallImage");
                        System.out.println();
                        Image  smallImage = item.getSmallImage();
                        if (smallImage.isSetURL()) {
                            System.out.print("                    URL");
                            System.out.println();
                            System.out.print("                        " + smallImage.getURL());
                            System.out.println();
                        }
                        if (smallImage.isSetHeight()) {
                            System.out.print("                    Height");
                            System.out.println();
                            DecimalWithUnits  height = smallImage.getHeight();
                        } 
                        if (smallImage.isSetWidth()) {
                            System.out.print("                    Width");
                            System.out.println();
                            DecimalWithUnits  width = smallImage.getWidth();
                        } 
                        if (smallImage.isSetIsVerified()) {
                            System.out.print("                    IsVerified");
                            System.out.println();
                            System.out.print("                        " + smallImage.getIsVerified());
                            System.out.println();
                        }
                    } 
                    if (item.isSetMediumImage()) {
                        System.out.print("                MediumImage");
                        System.out.println();
                        Image  mediumImage = item.getMediumImage();
                        if (mediumImage.isSetURL()) {
                            System.out.print("                    URL");
                            System.out.println();
                            System.out.print("                        " + mediumImage.getURL());
                            System.out.println();
                        }
                        if (mediumImage.isSetHeight()) {
                            System.out.print("                    Height");
                            System.out.println();
                            DecimalWithUnits  height = mediumImage.getHeight();
                        } 
                        if (mediumImage.isSetWidth()) {
                            System.out.print("                    Width");
                            System.out.println();
                            DecimalWithUnits  width = mediumImage.getWidth();
                        } 
                        if (mediumImage.isSetIsVerified()) {
                            System.out.print("                    IsVerified");
                            System.out.println();
                            System.out.print("                        " + mediumImage.getIsVerified());
                            System.out.println();
                        }
                    } 
                    if (item.isSetLargeImage()) {
                        System.out.print("                LargeImage");
                        System.out.println();
                        Image  largeImage = item.getLargeImage();
                        if (largeImage.isSetURL()) {
                            System.out.print("                    URL");
                            System.out.println();
                            System.out.print("                        " + largeImage.getURL());
                            System.out.println();
                        }
                        if (largeImage.isSetHeight()) {
                            System.out.print("                    Height");
                            System.out.println();
                            DecimalWithUnits  height = largeImage.getHeight();
                        } 
                        if (largeImage.isSetWidth()) {
                            System.out.print("                    Width");
                            System.out.println();
                            DecimalWithUnits  width = largeImage.getWidth();
                        } 
                        if (largeImage.isSetIsVerified()) {
                            System.out.print("                    IsVerified");
                            System.out.println();
                            System.out.print("                        " + largeImage.getIsVerified());
                            System.out.println();
                        }
                    } 
                    java.util.List<ImageSets> imageSetssList = item.getImageSets();
                    for (ImageSets imageSets : imageSetssList) {
                        System.out.print("                ImageSets");
                        System.out.println();
                        if (imageSets.isSetMerchantId()) {
                            System.out.print("                    MerchantId");
                            System.out.println();
                            System.out.print("                        " + imageSets.getMerchantId());
                            System.out.println();
                        }
                        java.util.List<ImageSet> imageSetsList = imageSets.getImageSet();
                        for (ImageSet imageSet : imageSetsList) {
                            System.out.print("                    ImageSet");
                            System.out.println();
                            if (imageSet.isSetSwatchImage()) {
                                System.out.print("                        SwatchImage");
                                System.out.println();
                                Image  swatchImage = imageSet.getSwatchImage();
                                if (swatchImage.isSetURL()) {
                                    System.out.print("                            URL");
                                    System.out.println();
                                    System.out.print("                                " + swatchImage.getURL());
                                    System.out.println();
                                }
                                if (swatchImage.isSetHeight()) {
                                    System.out.print("                            Height");
                                    System.out.println();
                                    DecimalWithUnits  height = swatchImage.getHeight();
                                } 
                                if (swatchImage.isSetWidth()) {
                                    System.out.print("                            Width");
                                    System.out.println();
                                    DecimalWithUnits  width = swatchImage.getWidth();
                                } 
                                if (swatchImage.isSetIsVerified()) {
                                    System.out.print("                            IsVerified");
                                    System.out.println();
                                    System.out.print("                                " + swatchImage.getIsVerified());
                                    System.out.println();
                                }
                            } 
                            if (imageSet.isSetSmallImage()) {
                                System.out.print("                        SmallImage");
                                System.out.println();
                                Image  smallImage = imageSet.getSmallImage();
                                if (smallImage.isSetURL()) {
                                    System.out.print("                            URL");
                                    System.out.println();
                                    System.out.print("                                " + smallImage.getURL());
                                    System.out.println();
                                }
                                if (smallImage.isSetHeight()) {
                                    System.out.print("                            Height");
                                    System.out.println();
                                    DecimalWithUnits  height = smallImage.getHeight();
                                } 
                                if (smallImage.isSetWidth()) {
                                    System.out.print("                            Width");
                                    System.out.println();
                                    DecimalWithUnits  width = smallImage.getWidth();
                                } 
                                if (smallImage.isSetIsVerified()) {
                                    System.out.print("                            IsVerified");
                                    System.out.println();
                                    System.out.print("                                " + smallImage.getIsVerified());
                                    System.out.println();
                                }
                            } 
                            if (imageSet.isSetThumbnailImage()) {
                                System.out.print("                        ThumbnailImage");
                                System.out.println();
                                Image  thumbnailImage = imageSet.getThumbnailImage();
                                if (thumbnailImage.isSetURL()) {
                                    System.out.print("                            URL");
                                    System.out.println();
                                    System.out.print("                                " + thumbnailImage.getURL());
                                    System.out.println();
                                }
                                if (thumbnailImage.isSetHeight()) {
                                    System.out.print("                            Height");
                                    System.out.println();
                                    DecimalWithUnits  height = thumbnailImage.getHeight();
                                } 
                                if (thumbnailImage.isSetWidth()) {
                                    System.out.print("                            Width");
                                    System.out.println();
                                    DecimalWithUnits  width = thumbnailImage.getWidth();
                                } 
                                if (thumbnailImage.isSetIsVerified()) {
                                    System.out.print("                            IsVerified");
                                    System.out.println();
                                    System.out.print("                                " + thumbnailImage.getIsVerified());
                                    System.out.println();
                                }
                            } 
                            if (imageSet.isSetTinyImage()) {
                                System.out.print("                        TinyImage");
                                System.out.println();
                                Image  tinyImage = imageSet.getTinyImage();
                                if (tinyImage.isSetURL()) {
                                    System.out.print("                            URL");
                                    System.out.println();
                                    System.out.print("                                " + tinyImage.getURL());
                                    System.out.println();
                                }
                                if (tinyImage.isSetHeight()) {
                                    System.out.print("                            Height");
                                    System.out.println();
                                    DecimalWithUnits  height = tinyImage.getHeight();
                                } 
                                if (tinyImage.isSetWidth()) {
                                    System.out.print("                            Width");
                                    System.out.println();
                                    DecimalWithUnits  width = tinyImage.getWidth();
                                } 
                                if (tinyImage.isSetIsVerified()) {
                                    System.out.print("                            IsVerified");
                                    System.out.println();
                                    System.out.print("                                " + tinyImage.getIsVerified());
                                    System.out.println();
                                }
                            } 
                            if (imageSet.isSetMediumImage()) {
                                System.out.print("                        MediumImage");
                                System.out.println();
                                Image  mediumImage = imageSet.getMediumImage();
                                if (mediumImage.isSetURL()) {
                                    System.out.print("                            URL");
                                    System.out.println();
                                    System.out.print("                                " + mediumImage.getURL());
                                    System.out.println();
                                }
                                if (mediumImage.isSetHeight()) {
                                    System.out.print("                            Height");
                                    System.out.println();
                                    DecimalWithUnits  height = mediumImage.getHeight();
                                } 
                                if (mediumImage.isSetWidth()) {
                                    System.out.print("                            Width");
                                    System.out.println();
                                    DecimalWithUnits  width = mediumImage.getWidth();
                                } 
                                if (mediumImage.isSetIsVerified()) {
                                    System.out.print("                            IsVerified");
                                    System.out.println();
                                    System.out.print("                                " + mediumImage.getIsVerified());
                                    System.out.println();
                                }
                            } 
                            if (imageSet.isSetLargeImage()) {
                                System.out.print("                        LargeImage");
                                System.out.println();
                                Image  largeImage = imageSet.getLargeImage();
                                if (largeImage.isSetURL()) {
                                    System.out.print("                            URL");
                                    System.out.println();
                                    System.out.print("                                " + largeImage.getURL());
                                    System.out.println();
                                }
                                if (largeImage.isSetHeight()) {
                                    System.out.print("                            Height");
                                    System.out.println();
                                    DecimalWithUnits  height = largeImage.getHeight();
                                } 
                                if (largeImage.isSetWidth()) {
                                    System.out.print("                            Width");
                                    System.out.println();
                                    DecimalWithUnits  width = largeImage.getWidth();
                                } 
                                if (largeImage.isSetIsVerified()) {
                                    System.out.print("                            IsVerified");
                                    System.out.println();
                                    System.out.print("                                " + largeImage.getIsVerified());
                                    System.out.println();
                                }
                            } 
                        }
                    }
                    if (item.isSetItemAttributes()) {
                        System.out.print("                ItemAttributes");
                        System.out.println();
                        ItemAttributes  itemAttributes = item.getItemAttributes();
                        java.util.List<String> actorsList  =  itemAttributes.getActor();
                        for (String actor : actorsList) { 
                            System.out.print("                    Actor");
                                System.out.println();
                            System.out.print("                        " + actor);
                        }	
                        if (itemAttributes.isSetAddress()) {
                            System.out.print("                    Address");
                            System.out.println();
                            Address  address = itemAttributes.getAddress();
                            if (address.isSetName()) {
                                System.out.print("                        Name");
                                System.out.println();
                                System.out.print("                            " + address.getName());
                                System.out.println();
                            }
                            if (address.isSetAddress1()) {
                                System.out.print("                        Address1");
                                System.out.println();
                                System.out.print("                            " + address.getAddress1());
                                System.out.println();
                            }
                            if (address.isSetAddress2()) {
                                System.out.print("                        Address2");
                                System.out.println();
                                System.out.print("                            " + address.getAddress2());
                                System.out.println();
                            }
                            if (address.isSetAddress3()) {
                                System.out.print("                        Address3");
                                System.out.println();
                                System.out.print("                            " + address.getAddress3());
                                System.out.println();
                            }
                            if (address.isSetCity()) {
                                System.out.print("                        City");
                                System.out.println();
                                System.out.print("                            " + address.getCity());
                                System.out.println();
                            }
                            if (address.isSetState()) {
                                System.out.print("                        State");
                                System.out.println();
                                System.out.print("                            " + address.getState());
                                System.out.println();
                            }
                            if (address.isSetPostalCode()) {
                                System.out.print("                        PostalCode");
                                System.out.println();
                                System.out.print("                            " + address.getPostalCode());
                                System.out.println();
                            }
                            if (address.isSetCountry()) {
                                System.out.print("                        Country");
                                System.out.println();
                                System.out.print("                            " + address.getCountry());
                                System.out.println();
                            }
                        } 
                        java.util.List<String> agesList  =  itemAttributes.getAge();
                        for (String age : agesList) { 
                            System.out.print("                    Age");
                                System.out.println();
                            System.out.print("                        " + age);
                        }	
                        if (itemAttributes.isSetAmazonMaximumAge()) {
                            System.out.print("                    AmazonMaximumAge");
                            System.out.println();
                            DecimalWithUnits  amazonMaximumAge = itemAttributes.getAmazonMaximumAge();
                        } 
                        if (itemAttributes.isSetAmazonMinimumAge()) {
                            System.out.print("                    AmazonMinimumAge");
                            System.out.println();
                            DecimalWithUnits  amazonMinimumAge = itemAttributes.getAmazonMinimumAge();
                        } 
                        if (itemAttributes.isSetAnalogVideoFormat()) {
                            System.out.print("                    AnalogVideoFormat");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getAnalogVideoFormat());
                            System.out.println();
                        }
                        if (itemAttributes.isSetApertureModes()) {
                            System.out.print("                    ApertureModes");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getApertureModes());
                            System.out.println();
                        }
                        java.util.List<String> artistsList  =  itemAttributes.getArtist();
                        for (String artist : artistsList) { 
                            System.out.print("                    Artist");
                                System.out.println();
                            System.out.print("                        " + artist);
                        }	
                        if (itemAttributes.isSetAspectRatio()) {
                            System.out.print("                    AspectRatio");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getAspectRatio());
                            System.out.println();
                        }
                        if (itemAttributes.isSetAssemblyInstructions()) {
                            System.out.print("                    AssemblyInstructions");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getAssemblyInstructions());
                            System.out.println();
                        }
                        if (itemAttributes.isSetAssemblyRequired()) {
                            System.out.print("                    AssemblyRequired");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getAssemblyRequired());
                            System.out.println();
                        }
                        if (itemAttributes.isSetAudienceRating()) {
                            System.out.print("                    AudienceRating");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getAudienceRating());
                            System.out.println();
                        }
                        java.util.List<String> audioFormatsList  =  itemAttributes.getAudioFormat();
                        for (String audioFormat : audioFormatsList) { 
                            System.out.print("                    AudioFormat");
                                System.out.println();
                            System.out.print("                        " + audioFormat);
                        }	
                        java.util.List<String> authorsList  =  itemAttributes.getAuthor();
                        for (String author : authorsList) { 
                            System.out.print("                    Author");
                                System.out.println();
                            System.out.print("                        " + author);
                        }	
                        if (itemAttributes.isSetBackFinding()) {
                            System.out.print("                    BackFinding");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBackFinding());
                            System.out.println();
                        }
                        if (itemAttributes.isSetBandMaterialType()) {
                            System.out.print("                    BandMaterialType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBandMaterialType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetBatteriesIncluded()) {
                            System.out.print("                    BatteriesIncluded");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBatteriesIncluded());
                            System.out.println();
                        }
                        if (itemAttributes.isSetBatteriesRequired()) {
                            System.out.print("                    BatteriesRequired");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBatteriesRequired());
                            System.out.println();
                        }
                        if (itemAttributes.isSetBatteries()) {
                            System.out.print("                    Batteries");
                            System.out.println();
                            NonNegativeIntegerWithUnits  batteries = itemAttributes.getBatteries();
                        } 
                        if (itemAttributes.isSetBatteryDescription()) {
                            System.out.print("                    BatteryDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBatteryDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetBatteryType()) {
                            System.out.print("                    BatteryType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBatteryType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetBezelMaterialType()) {
                            System.out.print("                    BezelMaterialType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBezelMaterialType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetBinding()) {
                            System.out.print("                    Binding");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBinding());
                            System.out.println();
                        }
                        if (itemAttributes.isSetBrand()) {
                            System.out.print("                    Brand");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getBrand());
                            System.out.println();
                        }
                        if (itemAttributes.isSetCalendarType()) {
                            System.out.print("                    CalendarType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCalendarType());
                            System.out.println();
                        }
                        java.util.List<String> cameraManualFeaturessList  =  itemAttributes.getCameraManualFeatures();
                        for (String cameraManualFeatures : cameraManualFeaturessList) { 
                            System.out.print("                    CameraManualFeatures");
                                System.out.println();
                            System.out.print("                        " + cameraManualFeatures);
                        }	
                        if (itemAttributes.isSetCaseDiameter()) {
                            System.out.print("                    CaseDiameter");
                            System.out.println();
                            DecimalWithUnits  caseDiameter = itemAttributes.getCaseDiameter();
                        } 
                        if (itemAttributes.isSetCaseMaterialType()) {
                            System.out.print("                    CaseMaterialType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCaseMaterialType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetCaseThickness()) {
                            System.out.print("                    CaseThickness");
                            System.out.println();
                            DecimalWithUnits  caseThickness = itemAttributes.getCaseThickness();
                        } 
                        if (itemAttributes.isSetCaseType()) {
                            System.out.print("                    CaseType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCaseType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetCatalogNumber()) {
                            System.out.print("                    CatalogNumber");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCatalogNumber());
                            System.out.println();
                        }
                        java.util.List<String> categorysList  =  itemAttributes.getCategory();
                        for (String category : categorysList) { 
                            System.out.print("                    Category");
                                System.out.println();
                            System.out.print("                        " + category);
                        }	
                        java.util.List<String> categoryBinsList  =  itemAttributes.getCategoryBin();
                        for (String categoryBin : categoryBinsList) { 
                            System.out.print("                    CategoryBin");
                                System.out.println();
                            System.out.print("                        " + categoryBin);
                        }	
                        if (itemAttributes.isSetCDRWDescription()) {
                            System.out.print("                    CDRWDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCDRWDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetChainType()) {
                            System.out.print("                    ChainType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getChainType());
                            System.out.println();
                        }
                        java.util.List<String> charactersList  =  itemAttributes.getCharacter();
                        for (String character : charactersList) { 
                            System.out.print("                    Character");
                                System.out.println();
                            System.out.print("                        " + character);
                        }	
                        if (itemAttributes.isSetCEROAgeRating()) {
                            System.out.print("                    CEROAgeRating");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCEROAgeRating());
                            System.out.println();
                        }
                        if (itemAttributes.isSetClaspType()) {
                            System.out.print("                    ClaspType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getClaspType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetClothingSize()) {
                            System.out.print("                    ClothingSize");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getClothingSize());
                            System.out.println();
                        }
                        if (itemAttributes.isSetClubType()) {
                            System.out.print("                    ClubType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getClubType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetColor()) {
                            System.out.print("                    Color");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getColor());
                            System.out.println();
                        }
                        if (itemAttributes.isSetCompatibility()) {
                            System.out.print("                    Compatibility");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCompatibility());
                            System.out.println();
                        }
                        java.util.List<String> compatibleDevicessList  =  itemAttributes.getCompatibleDevices();
                        for (String compatibleDevices : compatibleDevicessList) { 
                            System.out.print("                    CompatibleDevices");
                                System.out.println();
                            System.out.print("                        " + compatibleDevices);
                        }	
                        if (itemAttributes.isSetComputerHardwareType()) {
                            System.out.print("                    ComputerHardwareType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getComputerHardwareType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetComputerPlatform()) {
                            System.out.print("                    ComputerPlatform");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getComputerPlatform());
                            System.out.println();
                        }
                        if (itemAttributes.isSetConnectivity()) {
                            System.out.print("                    Connectivity");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getConnectivity());
                            System.out.println();
                        }
                        if (itemAttributes.isSetContinuousShootingSpeed()) {
                            System.out.print("                    ContinuousShootingSpeed");
                            System.out.println();
                            DecimalWithUnits  continuousShootingSpeed = itemAttributes.getContinuousShootingSpeed();
                        } 
                        if (itemAttributes.isSetCountry()) {
                            System.out.print("                    Country");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCountry());
                            System.out.println();
                        }
                        if (itemAttributes.isSetCPUManufacturer()) {
                            System.out.print("                    CPUManufacturer");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCPUManufacturer());
                            System.out.println();
                        }
                        if (itemAttributes.isSetCPUSpeed()) {
                            System.out.print("                    CPUSpeed");
                            System.out.println();
                            DecimalWithUnits  CPUSpeed = itemAttributes.getCPUSpeed();
                        } 
                        if (itemAttributes.isSetCPUType()) {
                            System.out.print("                    CPUType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCPUType());
                            System.out.println();
                        }
                        java.util.List<Creator> creatorsList = itemAttributes.getCreator();
                        for (Creator creator : creatorsList) {
                            System.out.print("                    Creator");
                            System.out.println();
                        }
                        if (itemAttributes.isSetCuisine()) {
                            System.out.print("                    Cuisine");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getCuisine());
                            System.out.println();
                        }
                        java.util.List<String> dataLinkProtocolsList  =  itemAttributes.getDataLinkProtocol();
                        for (String dataLinkProtocol : dataLinkProtocolsList) { 
                            System.out.print("                    DataLinkProtocol");
                                System.out.println();
                            System.out.print("                        " + dataLinkProtocol);
                        }	
                        if (itemAttributes.isSetDeliveryOption()) {
                            System.out.print("                    DeliveryOption");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDeliveryOption());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDelayBetweenShots()) {
                            System.out.print("                    DelayBetweenShots");
                            System.out.println();
                            DecimalWithUnits  delayBetweenShots = itemAttributes.getDelayBetweenShots();
                        } 
                        if (itemAttributes.isSetDepartment()) {
                            System.out.print("                    Department");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDepartment());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDeweyDecimalNumber()) {
                            System.out.print("                    DeweyDecimalNumber");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDeweyDecimalNumber());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDialColor()) {
                            System.out.print("                    DialColor");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDialColor());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDialWindowMaterialType()) {
                            System.out.print("                    DialWindowMaterialType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDialWindowMaterialType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDigitalZoom()) {
                            System.out.print("                    DigitalZoom");
                            System.out.println();
                            DecimalWithUnits  digitalZoom = itemAttributes.getDigitalZoom();
                        } 
                        java.util.List<String> directorsList  =  itemAttributes.getDirector();
                        for (String director : directorsList) { 
                            System.out.print("                    Director");
                                System.out.println();
                            System.out.print("                        " + director);
                        }	
                        if (itemAttributes.isSetDisplayColorSupport()) {
                            System.out.print("                    DisplayColorSupport");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDisplayColorSupport());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDisplaySize()) {
                            System.out.print("                    DisplaySize");
                            System.out.println();
                            DecimalWithUnits  displaySize = itemAttributes.getDisplaySize();
                        } 
                        if (itemAttributes.isSetDrumSetPieceQuantity()) {
                            System.out.print("                    DrumSetPieceQuantity");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDrumSetPieceQuantity());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDVDLayers()) {
                            System.out.print("                    DVDLayers");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDVDLayers());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDVDRWDescription()) {
                            System.out.print("                    DVDRWDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDVDRWDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDVDSides()) {
                            System.out.print("                    DVDSides");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDVDSides());
                            System.out.println();
                        }
                        if (itemAttributes.isSetDPCI()) {
                            System.out.print("                    DPCI");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getDPCI());
                            System.out.println();
                        }
                        if (itemAttributes.isSetEAN()) {
                            System.out.print("                    EAN");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getEAN());
                            System.out.println();
                        }
                        if (itemAttributes.isSetEdition()) {
                            System.out.print("                    Edition");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getEdition());
                            System.out.println();
                        }
                        java.util.List<String> educationalFocussList  =  itemAttributes.getEducationalFocus();
                        for (String educationalFocus : educationalFocussList) { 
                            System.out.print("                    EducationalFocus");
                                System.out.println();
                            System.out.print("                        " + educationalFocus);
                        }	
                        java.util.List<String> ethnicitysList  =  itemAttributes.getEthnicity();
                        for (String ethnicity : ethnicitysList) { 
                            System.out.print("                    Ethnicity");
                                System.out.println();
                            System.out.print("                        " + ethnicity);
                        }	
                        if (itemAttributes.isSetESRBAgeRating()) {
                            System.out.print("                    ESRBAgeRating");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getESRBAgeRating());
                            System.out.println();
                        }
                        if (itemAttributes.isSetExternalDisplaySupportDescription()) {
                            System.out.print("                    ExternalDisplaySupportDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getExternalDisplaySupportDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetFabricType()) {
                            System.out.print("                    FabricType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getFabricType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetFaxNumber()) {
                            System.out.print("                    FaxNumber");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getFaxNumber());
                            System.out.println();
                        }
                        java.util.List<String> featuresList  =  itemAttributes.getFeature();
                        for (String feature : featuresList) { 
                            System.out.print("                    Feature");
                                System.out.println();
                            System.out.print("                        " + feature);
                        }	
                        if (itemAttributes.isSetFilmColorType()) {
                            System.out.print("                    FilmColorType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getFilmColorType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetFirstIssueLeadTime()) {
                            System.out.print("                    FirstIssueLeadTime");
                            System.out.println();
                            StringWithUnits  firstIssueLeadTime = itemAttributes.getFirstIssueLeadTime();
                        } 
                        if (itemAttributes.isSetFlavorName()) {
                            System.out.print("                    FlavorName");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getFlavorName());
                            System.out.println();
                        }
                        if (itemAttributes.isSetFloppyDiskDriveDescription()) {
                            System.out.print("                    FloppyDiskDriveDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getFloppyDiskDriveDescription());
                            System.out.println();
                        }
                        java.util.List<String> formatsList  =  itemAttributes.getFormat();
                        for (String format : formatsList) { 
                            System.out.print("                    Format");
                                System.out.println();
                            System.out.print("                        " + format);
                        }	
                        java.util.List<String> formFactorsList  =  itemAttributes.getFormFactor();
                        for (String formFactor : formFactorsList) { 
                            System.out.print("                    FormFactor");
                                System.out.println();
                            System.out.print("                        " + formFactor);
                        }	
                        if (itemAttributes.isSetGemType()) {
                            System.out.print("                    GemType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGemType());
                            System.out.println();
                        }
                        java.util.List<String> gemTypeSetElementsList  =  itemAttributes.getGemTypeSetElement();
                        for (String gemTypeSetElement : gemTypeSetElementsList) { 
                            System.out.print("                    GemTypeSetElement");
                                System.out.println();
                            System.out.print("                        " + gemTypeSetElement);
                        }	
                        java.util.List<String> gendersList  =  itemAttributes.getGender();
                        for (String gender : gendersList) { 
                            System.out.print("                    Gender");
                                System.out.println();
                            System.out.print("                        " + gender);
                        }	
                        if (itemAttributes.isSetGenre()) {
                            System.out.print("                    Genre");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGenre());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGLProductGroup()) {
                            System.out.print("                    GLProductGroup");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGLProductGroup());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGolfClubFlex()) {
                            System.out.print("                    GolfClubFlex");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGolfClubFlex());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGolfClubLoft()) {
                            System.out.print("                    GolfClubLoft");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGolfClubLoft());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGraphicsCardInterface()) {
                            System.out.print("                    GraphicsCardInterface");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGraphicsCardInterface());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGraphicsDescription()) {
                            System.out.print("                    GraphicsDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGraphicsDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGraphicsMemorySize()) {
                            System.out.print("                    GraphicsMemorySize");
                            System.out.println();
                            DecimalWithUnits  graphicsMemorySize = itemAttributes.getGraphicsMemorySize();
                        } 
                        if (itemAttributes.isSetGuitarAttribute()) {
                            System.out.print("                    GuitarAttribute");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGuitarAttribute());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGuitarBridgeSystem()) {
                            System.out.print("                    GuitarBridgeSystem");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGuitarBridgeSystem());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGuitarPickThickness()) {
                            System.out.print("                    GuitarPickThickness");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGuitarPickThickness());
                            System.out.println();
                        }
                        if (itemAttributes.isSetGuitarPickupConfiguration()) {
                            System.out.print("                    GuitarPickupConfiguration");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getGuitarPickupConfiguration());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHandOrientation()) {
                            System.out.print("                    HandOrientation");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getHandOrientation());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHardDiskCount()) {
                            System.out.print("                    HardDiskCount");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getHardDiskCount());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHardDiskSize()) {
                            System.out.print("                    HardDiskSize");
                            System.out.println();
                            DecimalWithUnits  hardDiskSize = itemAttributes.getHardDiskSize();
                        } 
                        if (itemAttributes.isSetHardDiskInterface()) {
                            System.out.print("                    HardDiskInterface");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getHardDiskInterface());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHardwarePlatform()) {
                            System.out.print("                    HardwarePlatform");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getHardwarePlatform());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHasAutoFocus()) {
                            System.out.print("                    HasAutoFocus");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isHasAutoFocus());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHasBurstMode()) {
                            System.out.print("                    HasBurstMode");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isHasBurstMode());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHasInCameraEditing()) {
                            System.out.print("                    HasInCameraEditing");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isHasInCameraEditing());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHasRedEyeReduction()) {
                            System.out.print("                    HasRedEyeReduction");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isHasRedEyeReduction());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHasSelfTimer()) {
                            System.out.print("                    HasSelfTimer");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isHasSelfTimer());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHasTripodMount()) {
                            System.out.print("                    HasTripodMount");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isHasTripodMount());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHasVideoOut()) {
                            System.out.print("                    HasVideoOut");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isHasVideoOut());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHasViewfinder()) {
                            System.out.print("                    HasViewfinder");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isHasViewfinder());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHazardousMaterialType()) {
                            System.out.print("                    HazardousMaterialType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getHazardousMaterialType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetHoursOfOperation()) {
                            System.out.print("                    HoursOfOperation");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getHoursOfOperation());
                            System.out.println();
                        }
                        if (itemAttributes.isSetIncludedSoftware()) {
                            System.out.print("                    IncludedSoftware");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getIncludedSoftware());
                            System.out.println();
                        }
                        if (itemAttributes.isSetIncludesMp3Player()) {
                            System.out.print("                    IncludesMp3Player");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isIncludesMp3Player());
                            System.out.println();
                        }
                        if (itemAttributes.isSetIngredients()) {
                            System.out.print("                    Ingredients");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getIngredients());
                            System.out.println();
                        }
                        java.util.List<String> ingredientsSetElementsList  =  itemAttributes.getIngredientsSetElement();
                        for (String ingredientsSetElement : ingredientsSetElementsList) { 
                            System.out.print("                    IngredientsSetElement");
                                System.out.println();
                            System.out.print("                        " + ingredientsSetElement);
                        }	
                        if (itemAttributes.isSetInstrumentKey()) {
                            System.out.print("                    InstrumentKey");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getInstrumentKey());
                            System.out.println();
                        }
                        java.util.List<String> interestsList  =  itemAttributes.getInterest();
                        for (String interest : interestsList) { 
                            System.out.print("                    Interest");
                                System.out.println();
                            System.out.print("                        " + interest);
                        }	
                        if (itemAttributes.isSetIsAdultProduct()) {
                            System.out.print("                    IsAdultProduct");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isIsAdultProduct());
                            System.out.println();
                        }
                        if (itemAttributes.isSetIsAutographed()) {
                            System.out.print("                    IsAutographed");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isIsAutographed());
                            System.out.println();
                        }
                        if (itemAttributes.isSetISBN()) {
                            System.out.print("                    ISBN");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getISBN());
                            System.out.println();
                        }
                        if (itemAttributes.isSetIsFragile()) {
                            System.out.print("                    IsFragile");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isIsFragile());
                            System.out.println();
                        }
                        if (itemAttributes.isSetIsLabCreated()) {
                            System.out.print("                    IsLabCreated");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isIsLabCreated());
                            System.out.println();
                        }
                        if (itemAttributes.isSetIsMemorabilia()) {
                            System.out.print("                    IsMemorabilia");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isIsMemorabilia());
                            System.out.println();
                        }
                        if (itemAttributes.isSetISOEquivalent()) {
                            System.out.print("                    ISOEquivalent");
                            System.out.println();
                            NonNegativeIntegerWithUnits  ISOEquivalent = itemAttributes.getISOEquivalent();
                        } 
                        if (itemAttributes.isSetIsPreannounce()) {
                            System.out.print("                    IsPreannounce");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.isIsPreannounce());
                            System.out.println();
                        }
                        if (itemAttributes.isSetIssuesPerYear()) {
                            System.out.print("                    IssuesPerYear");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getIssuesPerYear());
                            System.out.println();
                        }
                        if (itemAttributes.isSetItemDimensions()) {
                            System.out.print("                    ItemDimensions");
                            System.out.println();
                            ItemDimensions  itemDimensions = itemAttributes.getItemDimensions();
                            if (itemDimensions.isSetHeight()) {
                                System.out.print("                        Height");
                                System.out.println();
                                DecimalWithUnits  height = itemDimensions.getHeight();
                            } 
                            if (itemDimensions.isSetLength()) {
                                System.out.print("                        Length");
                                System.out.println();
                                DecimalWithUnits  length = itemDimensions.getLength();
                            } 
                            if (itemDimensions.isSetWeight()) {
                                System.out.print("                        Weight");
                                System.out.println();
                                DecimalWithUnits  weight = itemDimensions.getWeight();
                            } 
                            if (itemDimensions.isSetWidth()) {
                                System.out.print("                        Width");
                                System.out.println();
                                DecimalWithUnits  width = itemDimensions.getWidth();
                            } 
                        } 
                        if (itemAttributes.isSetKeyboardDescription()) {
                            System.out.print("                    KeyboardDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getKeyboardDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetLabel()) {
                            System.out.print("                    Label");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getLabel());
                            System.out.println();
                        }
                        java.util.List<String> languageNamesList  =  itemAttributes.getLanguageName();
                        for (String languageName : languageNamesList) { 
                            System.out.print("                    LanguageName");
                                System.out.println();
                            System.out.print("                        " + languageName);
                        }	
                        if (itemAttributes.isSetLanguages()) {
                            System.out.print("                    Languages");
                            System.out.println();
                            Languages  languages = itemAttributes.getLanguages();
                            java.util.List<Language> languagesList = languages.getLanguage();
                            for (Language language : languagesList) {
                                System.out.print("                        Language");
                                System.out.println();
                                if (language.isSetName()) {
                                    System.out.print("                            Name");
                                    System.out.println();
                                    System.out.print("                                " + language.getName());
                                    System.out.println();
                                }
                                if (language.isSetType()) {
                                    System.out.print("                            Type");
                                    System.out.println();
                                    System.out.print("                                " + language.getType());
                                    System.out.println();
                                }
                                if (language.isSetAudioFormat()) {
                                    System.out.print("                            AudioFormat");
                                    System.out.println();
                                    System.out.print("                                " + language.getAudioFormat());
                                    System.out.println();
                                }
                            }
                        } 
                        if (itemAttributes.isSetLegalDisclaimer()) {
                            System.out.print("                    LegalDisclaimer");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getLegalDisclaimer());
                            System.out.println();
                        }
                        if (itemAttributes.isSetLensType()) {
                            System.out.print("                    LensType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getLensType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetLineVoltage()) {
                            System.out.print("                    LineVoltage");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getLineVoltage());
                            System.out.println();
                        }
                        if (itemAttributes.isSetListPrice()) {
                            System.out.print("                    ListPrice");
                            System.out.println();
                            Price  listPrice = itemAttributes.getListPrice();
                            if (listPrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + listPrice.getAmount());
                                System.out.println();
                            }
                            if (listPrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + listPrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (listPrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + listPrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (itemAttributes.isSetMacroFocusRange()) {
                            System.out.print("                    MacroFocusRange");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMacroFocusRange());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMagazineType()) {
                            System.out.print("                    MagazineType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMagazineType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMalletHardness()) {
                            System.out.print("                    MalletHardness");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMalletHardness());
                            System.out.println();
                        }
                        if (itemAttributes.isSetManufacturer()) {
                            System.out.print("                    Manufacturer");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getManufacturer());
                            System.out.println();
                        }
                        if (itemAttributes.isSetManufacturerLaborWarrantyDescription()) {
                            System.out.print("                    ManufacturerLaborWarrantyDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getManufacturerLaborWarrantyDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetManufacturerMaximumAge()) {
                            System.out.print("                    ManufacturerMaximumAge");
                            System.out.println();
                            DecimalWithUnits  manufacturerMaximumAge = itemAttributes.getManufacturerMaximumAge();
                        } 
                        if (itemAttributes.isSetManufacturerMinimumAge()) {
                            System.out.print("                    ManufacturerMinimumAge");
                            System.out.println();
                            DecimalWithUnits  manufacturerMinimumAge = itemAttributes.getManufacturerMinimumAge();
                        } 
                        if (itemAttributes.isSetManufacturerPartsWarrantyDescription()) {
                            System.out.print("                    ManufacturerPartsWarrantyDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getManufacturerPartsWarrantyDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMaterialType()) {
                            System.out.print("                    MaterialType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMaterialType());
                            System.out.println();
                        }
                        java.util.List<String> materialTypeSetElementsList  =  itemAttributes.getMaterialTypeSetElement();
                        for (String materialTypeSetElement : materialTypeSetElementsList) { 
                            System.out.print("                    MaterialTypeSetElement");
                                System.out.println();
                            System.out.print("                        " + materialTypeSetElement);
                        }	
                        if (itemAttributes.isSetMaximumAperture()) {
                            System.out.print("                    MaximumAperture");
                            System.out.println();
                            DecimalWithUnits  maximumAperture = itemAttributes.getMaximumAperture();
                        } 
                        if (itemAttributes.isSetMaximumColorDepth()) {
                            System.out.print("                    MaximumColorDepth");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMaximumColorDepth());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMaximumFocalLength()) {
                            System.out.print("                    MaximumFocalLength");
                            System.out.println();
                            DecimalWithUnits  maximumFocalLength = itemAttributes.getMaximumFocalLength();
                        } 
                        if (itemAttributes.isSetMaximumHighResolutionImages()) {
                            System.out.print("                    MaximumHighResolutionImages");
                            System.out.println();
                            NonNegativeIntegerWithUnits  maximumHighResolutionImages = itemAttributes.getMaximumHighResolutionImages();
                        } 
                        if (itemAttributes.isSetMaximumHorizontalResolution()) {
                            System.out.print("                    MaximumHorizontalResolution");
                            System.out.println();
                            NonNegativeIntegerWithUnits  maximumHorizontalResolution = itemAttributes.getMaximumHorizontalResolution();
                        } 
                        if (itemAttributes.isSetMaximumLowResolutionImages()) {
                            System.out.print("                    MaximumLowResolutionImages");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMaximumLowResolutionImages());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMaximumResolution()) {
                            System.out.print("                    MaximumResolution");
                            System.out.println();
                            DecimalWithUnits  maximumResolution = itemAttributes.getMaximumResolution();
                        } 
                        if (itemAttributes.isSetMaximumShutterSpeed()) {
                            System.out.print("                    MaximumShutterSpeed");
                            System.out.println();
                            DecimalWithUnits  maximumShutterSpeed = itemAttributes.getMaximumShutterSpeed();
                        } 
                        if (itemAttributes.isSetMaximumVerticalResolution()) {
                            System.out.print("                    MaximumVerticalResolution");
                            System.out.println();
                            NonNegativeIntegerWithUnits  maximumVerticalResolution = itemAttributes.getMaximumVerticalResolution();
                        } 
                        if (itemAttributes.isSetMaximumWeightRecommendation()) {
                            System.out.print("                    MaximumWeightRecommendation");
                            System.out.println();
                            DecimalWithUnits  maximumWeightRecommendation = itemAttributes.getMaximumWeightRecommendation();
                        } 
                        if (itemAttributes.isSetMediaType()) {
                            System.out.print("                    MediaType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMediaType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMemorySlotsAvailable()) {
                            System.out.print("                    MemorySlotsAvailable");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMemorySlotsAvailable());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMetalStamp()) {
                            System.out.print("                    MetalStamp");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMetalStamp());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMetalType()) {
                            System.out.print("                    MetalType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMetalType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMiniMovieDescription()) {
                            System.out.print("                    MiniMovieDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMiniMovieDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMinimumFocalLength()) {
                            System.out.print("                    MinimumFocalLength");
                            System.out.println();
                            DecimalWithUnits  minimumFocalLength = itemAttributes.getMinimumFocalLength();
                        } 
                        if (itemAttributes.isSetMinimumShutterSpeed()) {
                            System.out.print("                    MinimumShutterSpeed");
                            System.out.println();
                            DecimalWithUnits  minimumShutterSpeed = itemAttributes.getMinimumShutterSpeed();
                        } 
                        if (itemAttributes.isSetModel()) {
                            System.out.print("                    Model");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getModel());
                            System.out.println();
                        }
                        if (itemAttributes.isSetModelYear()) {
                            System.out.print("                    ModelYear");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getModelYear());
                            System.out.println();
                        }
                        if (itemAttributes.isSetModemDescription()) {
                            System.out.print("                    ModemDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getModemDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMonitorSize()) {
                            System.out.print("                    MonitorSize");
                            System.out.println();
                            DecimalWithUnits  monitorSize = itemAttributes.getMonitorSize();
                        } 
                        if (itemAttributes.isSetMonitorViewableDiagonalSize()) {
                            System.out.print("                    MonitorViewableDiagonalSize");
                            System.out.println();
                            DecimalWithUnits  monitorViewableDiagonalSize = itemAttributes.getMonitorViewableDiagonalSize();
                        } 
                        if (itemAttributes.isSetMouseDescription()) {
                            System.out.print("                    MouseDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMouseDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMPN()) {
                            System.out.print("                    MPN");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMPN());
                            System.out.println();
                        }
                        if (itemAttributes.isSetMusicalStyle()) {
                            System.out.print("                    MusicalStyle");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getMusicalStyle());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNativeResolution()) {
                            System.out.print("                    NativeResolution");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNativeResolution());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNeighborhood()) {
                            System.out.print("                    Neighborhood");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNeighborhood());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNetworkInterfaceDescription()) {
                            System.out.print("                    NetworkInterfaceDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNetworkInterfaceDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNotebookDisplayTechnology()) {
                            System.out.print("                    NotebookDisplayTechnology");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNotebookDisplayTechnology());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNotebookPointingDeviceDescription()) {
                            System.out.print("                    NotebookPointingDeviceDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNotebookPointingDeviceDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfDiscs()) {
                            System.out.print("                    NumberOfDiscs");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfDiscs());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfIssues()) {
                            System.out.print("                    NumberOfIssues");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfIssues());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfItems()) {
                            System.out.print("                    NumberOfItems");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfItems());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfKeys()) {
                            System.out.print("                    NumberOfKeys");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfKeys());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfPages()) {
                            System.out.print("                    NumberOfPages");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfPages());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfPearls()) {
                            System.out.print("                    NumberOfPearls");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfPearls());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfRapidFireShots()) {
                            System.out.print("                    NumberOfRapidFireShots");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfRapidFireShots());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfStones()) {
                            System.out.print("                    NumberOfStones");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfStones());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfStrings()) {
                            System.out.print("                    NumberOfStrings");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfStrings());
                            System.out.println();
                        }
                        if (itemAttributes.isSetNumberOfTracks()) {
                            System.out.print("                    NumberOfTracks");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getNumberOfTracks());
                            System.out.println();
                        }
                        if (itemAttributes.isSetOperatingSystem()) {
                            System.out.print("                    OperatingSystem");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getOperatingSystem());
                            System.out.println();
                        }
                        if (itemAttributes.isSetOpticalSensorResolution()) {
                            System.out.print("                    OpticalSensorResolution");
                            System.out.println();
                            DecimalWithUnits  opticalSensorResolution = itemAttributes.getOpticalSensorResolution();
                        } 
                        if (itemAttributes.isSetOpticalZoom()) {
                            System.out.print("                    OpticalZoom");
                            System.out.println();
                            DecimalWithUnits  opticalZoom = itemAttributes.getOpticalZoom();
                        } 
                        if (itemAttributes.isSetOriginalReleaseDate()) {
                            System.out.print("                    OriginalReleaseDate");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getOriginalReleaseDate());
                            System.out.println();
                        }
                        if (itemAttributes.isSetOutputWattage()) {
                            System.out.print("                    OutputWattage");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getOutputWattage());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPackageDimensions()) {
                            System.out.print("                    PackageDimensions");
                            System.out.println();
                            PackageDimensions  packageDimensions = itemAttributes.getPackageDimensions();
                            if (packageDimensions.isSetHeight()) {
                                System.out.print("                        Height");
                                System.out.println();
                                DecimalWithUnits  height = packageDimensions.getHeight();
                            } 
                            if (packageDimensions.isSetLength()) {
                                System.out.print("                        Length");
                                System.out.println();
                                DecimalWithUnits  length = packageDimensions.getLength();
                            } 
                            if (packageDimensions.isSetWeight()) {
                                System.out.print("                        Weight");
                                System.out.println();
                                DecimalWithUnits  weight = packageDimensions.getWeight();
                            } 
                            if (packageDimensions.isSetWidth()) {
                                System.out.print("                        Width");
                                System.out.println();
                                DecimalWithUnits  width = packageDimensions.getWidth();
                            } 
                        } 
                        if (itemAttributes.isSetPackageQuantity()) {
                            System.out.print("                    PackageQuantity");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPackageQuantity());
                            System.out.println();
                        }
                        java.util.List<String> pantLengthsList  =  itemAttributes.getPantLength();
                        for (String pantLength : pantLengthsList) { 
                            System.out.print("                    PantLength");
                                System.out.println();
                            System.out.print("                        " + pantLength);
                        }	
                        java.util.List<String> pantSizesList  =  itemAttributes.getPantSize();
                        for (String pantSize : pantSizesList) { 
                            System.out.print("                    PantSize");
                                System.out.println();
                            System.out.print("                        " + pantSize);
                        }	
                        if (itemAttributes.isSetPearlLustre()) {
                            System.out.print("                    PearlLustre");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPearlLustre());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPearlMinimumColor()) {
                            System.out.print("                    PearlMinimumColor");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPearlMinimumColor());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPearlShape()) {
                            System.out.print("                    PearlShape");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPearlShape());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPearlStringingMethod()) {
                            System.out.print("                    PearlStringingMethod");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPearlStringingMethod());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPearlSurfaceBlemishes()) {
                            System.out.print("                    PearlSurfaceBlemishes");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPearlSurfaceBlemishes());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPearlType()) {
                            System.out.print("                    PearlType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPearlType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPearlUniformity()) {
                            System.out.print("                    PearlUniformity");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPearlUniformity());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPhoneNumber()) {
                            System.out.print("                    PhoneNumber");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPhoneNumber());
                            System.out.println();
                        }
                        java.util.List<String> photoFlashTypesList  =  itemAttributes.getPhotoFlashType();
                        for (String photoFlashType : photoFlashTypesList) { 
                            System.out.print("                    PhotoFlashType");
                                System.out.println();
                            System.out.print("                        " + photoFlashType);
                        }	
                        java.util.List<String> pictureFormatsList  =  itemAttributes.getPictureFormat();
                        for (String pictureFormat : pictureFormatsList) { 
                            System.out.print("                    PictureFormat");
                                System.out.println();
                            System.out.print("                        " + pictureFormat);
                        }	
                        java.util.List<String> platformsList  =  itemAttributes.getPlatform();
                        for (String platform : platformsList) { 
                            System.out.print("                    Platform");
                                System.out.println();
                            System.out.print("                        " + platform);
                        }	
                        if (itemAttributes.isSetPriceRating()) {
                            System.out.print("                    PriceRating");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPriceRating());
                            System.out.println();
                        }
                        java.util.List<String> primaryColorsList  =  itemAttributes.getPrimaryColor();
                        for (String primaryColor : primaryColorsList) { 
                            System.out.print("                    PrimaryColor");
                                System.out.println();
                            System.out.print("                        " + primaryColor);
                        }	
                        if (itemAttributes.isSetProcessorCount()) {
                            System.out.print("                    ProcessorCount");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getProcessorCount());
                            System.out.println();
                        }
                        if (itemAttributes.isSetProductGroup()) {
                            System.out.print("                    ProductGroup");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getProductGroup());
                            System.out.println();
                        }
                        if (itemAttributes.isSetProductSiteLaunchDate()) {
                            System.out.print("                    ProductSiteLaunchDate");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getProductSiteLaunchDate());
                            System.out.println();
                        }
                        if (itemAttributes.isSetProductTypeName()) {
                            System.out.print("                    ProductTypeName");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getProductTypeName());
                            System.out.println();
                        }
                        if (itemAttributes.isSetProductTypeSubcategory()) {
                            System.out.print("                    ProductTypeSubcategory");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getProductTypeSubcategory());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPromotionalTag()) {
                            System.out.print("                    PromotionalTag");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPromotionalTag());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPublicationDate()) {
                            System.out.print("                    PublicationDate");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPublicationDate());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPublisher()) {
                            System.out.print("                    Publisher");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPublisher());
                            System.out.println();
                        }
                        if (itemAttributes.isSetPOBoxShippingExcluded()) {
                            System.out.print("                    POBoxShippingExcluded");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getPOBoxShippingExcluded());
                            System.out.println();
                        }
                        if (itemAttributes.isSetReadingLevel()) {
                            System.out.print("                    ReadingLevel");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getReadingLevel());
                            System.out.println();
                        }
                        java.util.List<String> returnMethodsList  =  itemAttributes.getReturnMethod();
                        for (String returnMethod : returnMethodsList) { 
                            System.out.print("                    ReturnMethod");
                                System.out.println();
                            System.out.print("                        " + returnMethod);
                        }	
                        if (itemAttributes.isSetRecorderTrackCount()) {
                            System.out.print("                    RecorderTrackCount");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getRecorderTrackCount());
                            System.out.println();
                        }
                        if (itemAttributes.isSetRegionCode()) {
                            System.out.print("                    RegionCode");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getRegionCode());
                            System.out.println();
                        }
                        if (itemAttributes.isSetRegionOfOrigin()) {
                            System.out.print("                    RegionOfOrigin");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getRegionOfOrigin());
                            System.out.println();
                        }
                        if (itemAttributes.isSetReturnPolicy()) {
                            System.out.print("                    ReturnPolicy");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getReturnPolicy());
                            System.out.println();
                        }
                        if (itemAttributes.isSetReleaseDate()) {
                            System.out.print("                    ReleaseDate");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getReleaseDate());
                            System.out.println();
                        }
                        if (itemAttributes.isSetRemovableMemory()) {
                            System.out.print("                    RemovableMemory");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getRemovableMemory());
                            System.out.println();
                        }
                        if (itemAttributes.isSetRemovableStorage()) {
                            System.out.print("                    RemovableStorage");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getRemovableStorage());
                            System.out.println();
                        }
                        if (itemAttributes.isSetRequiredVoltageRange()) {
                            System.out.print("                    RequiredVoltageRange");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getRequiredVoltageRange());
                            System.out.println();
                        }
                        if (itemAttributes.isSetResolutionModes()) {
                            System.out.print("                    ResolutionModes");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getResolutionModes());
                            System.out.println();
                        }
                        if (itemAttributes.isSetRingSize()) {
                            System.out.print("                    RingSize");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getRingSize());
                            System.out.println();
                        }
                        if (itemAttributes.isSetRunningTime()) {
                            System.out.print("                    RunningTime");
                            System.out.println();
                            DecimalWithUnits  runningTime = itemAttributes.getRunningTime();
                        } 
                        if (itemAttributes.isSetScentName()) {
                            System.out.print("                    ScentName");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getScentName());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSecondaryCacheSize()) {
                            System.out.print("                    SecondaryCacheSize");
                            System.out.println();
                            NonNegativeIntegerWithUnits  secondaryCacheSize = itemAttributes.getSecondaryCacheSize();
                        } 
                        if (itemAttributes.isSetSettingType()) {
                            System.out.print("                    SettingType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSettingType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetShaftMaterialType()) {
                            System.out.print("                    ShaftMaterialType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getShaftMaterialType());
                            System.out.println();
                        }
                        java.util.List<String> shoeSizesList  =  itemAttributes.getShoeSize();
                        for (String shoeSize : shoeSizesList) { 
                            System.out.print("                    ShoeSize");
                                System.out.println();
                            System.out.print("                        " + shoeSize);
                        }	
                        if (itemAttributes.isSetSize()) {
                            System.out.print("                    Size");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSize());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSizePerPearl()) {
                            System.out.print("                    SizePerPearl");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSizePerPearl());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSkillLevel()) {
                            System.out.print("                    SkillLevel");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSkillLevel());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSKU()) {
                            System.out.print("                    SKU");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSKU());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSoldInStores()) {
                            System.out.print("                    SoldInStores");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSoldInStores());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSoundCardDescription()) {
                            System.out.print("                    SoundCardDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSoundCardDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSpeakerCount()) {
                            System.out.print("                    SpeakerCount");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSpeakerCount());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSpeakerDescription()) {
                            System.out.print("                    SpeakerDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSpeakerDescription());
                            System.out.println();
                        }
                        java.util.List<String> specialFeaturessList  =  itemAttributes.getSpecialFeatures();
                        for (String specialFeatures : specialFeaturessList) { 
                            System.out.print("                    SpecialFeatures");
                                System.out.println();
                            System.out.print("                        " + specialFeatures);
                        }	
                        if (itemAttributes.isSetStoneClarity()) {
                            System.out.print("                    StoneClarity");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getStoneClarity());
                            System.out.println();
                        }
                        if (itemAttributes.isSetStoneColor()) {
                            System.out.print("                    StoneColor");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getStoneColor());
                            System.out.println();
                        }
                        if (itemAttributes.isSetStoneCut()) {
                            System.out.print("                    StoneCut");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getStoneCut());
                            System.out.println();
                        }
                        if (itemAttributes.isSetStoneShape()) {
                            System.out.print("                    StoneShape");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getStoneShape());
                            System.out.println();
                        }
                        if (itemAttributes.isSetStoneWeight()) {
                            System.out.print("                    StoneWeight");
                            System.out.println();
                            DecimalWithUnits  stoneWeight = itemAttributes.getStoneWeight();
                        } 
                        if (itemAttributes.isSetStudio()) {
                            System.out.print("                    Studio");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getStudio());
                            System.out.println();
                        }
                        if (itemAttributes.isSetStyle()) {
                            System.out.print("                    Style");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getStyle());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSubscriptionLength()) {
                            System.out.print("                    SubscriptionLength");
                            System.out.println();
                            NonNegativeIntegerWithUnits  subscriptionLength = itemAttributes.getSubscriptionLength();
                        } 
                        java.util.List<String> supportedImageTypesList  =  itemAttributes.getSupportedImageType();
                        for (String supportedImageType : supportedImageTypesList) { 
                            System.out.print("                    SupportedImageType");
                                System.out.println();
                            System.out.print("                        " + supportedImageType);
                        }	
                        if (itemAttributes.isSetSupportedMediaSize()) {
                            System.out.print("                    SupportedMediaSize");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSupportedMediaSize());
                            System.out.println();
                        }
                        if (itemAttributes.isSetSystemBusSpeed()) {
                            System.out.print("                    SystemBusSpeed");
                            System.out.println();
                            DecimalWithUnits  systemBusSpeed = itemAttributes.getSystemBusSpeed();
                        } 
                        if (itemAttributes.isSetSystemMemorySizeMax()) {
                            System.out.print("                    SystemMemorySizeMax");
                            System.out.println();
                            DecimalWithUnits  systemMemorySizeMax = itemAttributes.getSystemMemorySizeMax();
                        } 
                        if (itemAttributes.isSetSystemMemorySize()) {
                            System.out.print("                    SystemMemorySize");
                            System.out.println();
                            DecimalWithUnits  systemMemorySize = itemAttributes.getSystemMemorySize();
                        } 
                        if (itemAttributes.isSetSystemMemoryType()) {
                            System.out.print("                    SystemMemoryType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getSystemMemoryType());
                            System.out.println();
                        }
                        java.util.List<String> targetBrandsList  =  itemAttributes.getTargetBrand();
                        for (String targetBrand : targetBrandsList) { 
                            System.out.print("                    TargetBrand");
                                System.out.println();
                            System.out.print("                        " + targetBrand);
                        }	
                        if (itemAttributes.isSetTellingPageIndicator()) {
                            System.out.print("                    TellingPageIndicator");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTellingPageIndicator());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTheatricalReleaseDate()) {
                            System.out.print("                    TheatricalReleaseDate");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTheatricalReleaseDate());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTitle());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalDiamondWeight()) {
                            System.out.print("                    TotalDiamondWeight");
                            System.out.println();
                            DecimalWithUnits  totalDiamondWeight = itemAttributes.getTotalDiamondWeight();
                        } 
                        if (itemAttributes.isSetTotalExternalBaysFree()) {
                            System.out.print("                    TotalExternalBaysFree");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalExternalBaysFree());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalFirewirePorts()) {
                            System.out.print("                    TotalFirewirePorts");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalFirewirePorts());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalGemWeight()) {
                            System.out.print("                    TotalGemWeight");
                            System.out.println();
                            DecimalWithUnits  totalGemWeight = itemAttributes.getTotalGemWeight();
                        } 
                        if (itemAttributes.isSetTotalInternalBaysFree()) {
                            System.out.print("                    TotalInternalBaysFree");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalInternalBaysFree());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalMetalWeight()) {
                            System.out.print("                    TotalMetalWeight");
                            System.out.println();
                            DecimalWithUnits  totalMetalWeight = itemAttributes.getTotalMetalWeight();
                        } 
                        if (itemAttributes.isSetTotalNTSCPALPorts()) {
                            System.out.print("                    TotalNTSCPALPorts");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalNTSCPALPorts());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalParallelPorts()) {
                            System.out.print("                    TotalParallelPorts");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalParallelPorts());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalPCCardSlots()) {
                            System.out.print("                    TotalPCCardSlots");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalPCCardSlots());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalPCISlotsFree()) {
                            System.out.print("                    TotalPCISlotsFree");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalPCISlotsFree());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalSerialPorts()) {
                            System.out.print("                    TotalSerialPorts");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalSerialPorts());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalSVideoOutPorts()) {
                            System.out.print("                    TotalSVideoOutPorts");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalSVideoOutPorts());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalUSB2Ports()) {
                            System.out.print("                    TotalUSB2Ports");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalUSB2Ports());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalUSBPorts()) {
                            System.out.print("                    TotalUSBPorts");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalUSBPorts());
                            System.out.println();
                        }
                        if (itemAttributes.isSetTotalVGAOutPorts()) {
                            System.out.print("                    TotalVGAOutPorts");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getTotalVGAOutPorts());
                            System.out.println();
                        }
                        if (itemAttributes.isSetUPC()) {
                            System.out.print("                    UPC");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getUPC());
                            System.out.println();
                        }
                        if (itemAttributes.isSetVariationDenomination()) {
                            System.out.print("                    VariationDenomination");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getVariationDenomination());
                            System.out.println();
                        }
                        if (itemAttributes.isSetVariationDescription()) {
                            System.out.print("                    VariationDescription");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getVariationDescription());
                            System.out.println();
                        }
                        if (itemAttributes.isSetWarranty()) {
                            System.out.print("                    Warranty");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getWarranty());
                            System.out.println();
                        }
                        if (itemAttributes.isSetWatchMovementType()) {
                            System.out.print("                    WatchMovementType");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getWatchMovementType());
                            System.out.println();
                        }
                        if (itemAttributes.isSetWaterResistanceDepth()) {
                            System.out.print("                    WaterResistanceDepth");
                            System.out.println();
                            DecimalWithUnits  waterResistanceDepth = itemAttributes.getWaterResistanceDepth();
                        } 
                        if (itemAttributes.isSetWEEETaxValue()) {
                            System.out.print("                    WEEETaxValue");
                            System.out.println();
                            Price  WEEETaxValue = itemAttributes.getWEEETaxValue();
                            if (WEEETaxValue.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + WEEETaxValue.getAmount());
                                System.out.println();
                            }
                            if (WEEETaxValue.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + WEEETaxValue.getCurrencyCode());
                                System.out.println();
                            }
                            if (WEEETaxValue.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + WEEETaxValue.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (itemAttributes.isSetWirelessMicrophoneFrequency()) {
                            System.out.print("                    WirelessMicrophoneFrequency");
                            System.out.println();
                            System.out.print("                        " + itemAttributes.getWirelessMicrophoneFrequency());
                            System.out.println();
                        }
                    } 
                    if (item.isSetMerchantItemAttributes()) {
                        System.out.print("                MerchantItemAttributes");
                        System.out.println();
                        MerchantItemAttributes  merchantItemAttributes = item.getMerchantItemAttributes();
                        java.util.List<String> actorsList  =  merchantItemAttributes.getActor();
                        for (String actor : actorsList) { 
                            System.out.print("                    Actor");
                                System.out.println();
                            System.out.print("                        " + actor);
                        }	
                        if (merchantItemAttributes.isSetAddress()) {
                            System.out.print("                    Address");
                            System.out.println();
                            Address  address = merchantItemAttributes.getAddress();
                            if (address.isSetName()) {
                                System.out.print("                        Name");
                                System.out.println();
                                System.out.print("                            " + address.getName());
                                System.out.println();
                            }
                            if (address.isSetAddress1()) {
                                System.out.print("                        Address1");
                                System.out.println();
                                System.out.print("                            " + address.getAddress1());
                                System.out.println();
                            }
                            if (address.isSetAddress2()) {
                                System.out.print("                        Address2");
                                System.out.println();
                                System.out.print("                            " + address.getAddress2());
                                System.out.println();
                            }
                            if (address.isSetAddress3()) {
                                System.out.print("                        Address3");
                                System.out.println();
                                System.out.print("                            " + address.getAddress3());
                                System.out.println();
                            }
                            if (address.isSetCity()) {
                                System.out.print("                        City");
                                System.out.println();
                                System.out.print("                            " + address.getCity());
                                System.out.println();
                            }
                            if (address.isSetState()) {
                                System.out.print("                        State");
                                System.out.println();
                                System.out.print("                            " + address.getState());
                                System.out.println();
                            }
                            if (address.isSetPostalCode()) {
                                System.out.print("                        PostalCode");
                                System.out.println();
                                System.out.print("                            " + address.getPostalCode());
                                System.out.println();
                            }
                            if (address.isSetCountry()) {
                                System.out.print("                        Country");
                                System.out.println();
                                System.out.print("                            " + address.getCountry());
                                System.out.println();
                            }
                        } 
                        if (merchantItemAttributes.isSetAmazonMaximumAge()) {
                            System.out.print("                    AmazonMaximumAge");
                            System.out.println();
                            DecimalWithUnits  amazonMaximumAge = merchantItemAttributes.getAmazonMaximumAge();
                        } 
                        if (merchantItemAttributes.isSetAmazonMinimumAge()) {
                            System.out.print("                    AmazonMinimumAge");
                            System.out.println();
                            DecimalWithUnits  amazonMinimumAge = merchantItemAttributes.getAmazonMinimumAge();
                        } 
                        if (merchantItemAttributes.isSetApertureModes()) {
                            System.out.print("                    ApertureModes");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getApertureModes());
                            System.out.println();
                        }
                        java.util.List<String> artistsList  =  merchantItemAttributes.getArtist();
                        for (String artist : artistsList) { 
                            System.out.print("                    Artist");
                                System.out.println();
                            System.out.print("                        " + artist);
                        }	
                        if (merchantItemAttributes.isSetAspectRatio()) {
                            System.out.print("                    AspectRatio");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getAspectRatio());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetAssemblyInstructions()) {
                            System.out.print("                    AssemblyInstructions");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getAssemblyInstructions());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetAssemblyRequired()) {
                            System.out.print("                    AssemblyRequired");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getAssemblyRequired());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetAudienceRating()) {
                            System.out.print("                    AudienceRating");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getAudienceRating());
                            System.out.println();
                        }
                        java.util.List<String> audioFormatsList  =  merchantItemAttributes.getAudioFormat();
                        for (String audioFormat : audioFormatsList) { 
                            System.out.print("                    AudioFormat");
                                System.out.println();
                            System.out.print("                        " + audioFormat);
                        }	
                        java.util.List<String> authorsList  =  merchantItemAttributes.getAuthor();
                        for (String author : authorsList) { 
                            System.out.print("                    Author");
                                System.out.println();
                            System.out.print("                        " + author);
                        }	
                        if (merchantItemAttributes.isSetBackFinding()) {
                            System.out.print("                    BackFinding");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBackFinding());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetBandMaterialType()) {
                            System.out.print("                    BandMaterialType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBandMaterialType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetBatteriesIncluded()) {
                            System.out.print("                    BatteriesIncluded");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBatteriesIncluded());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetBatteriesRequired()) {
                            System.out.print("                    BatteriesRequired");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBatteriesRequired());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetBatteries()) {
                            System.out.print("                    Batteries");
                            System.out.println();
                            NonNegativeIntegerWithUnits  batteries = merchantItemAttributes.getBatteries();
                        } 
                        if (merchantItemAttributes.isSetBatteryDescription()) {
                            System.out.print("                    BatteryDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBatteryDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetBatteryType()) {
                            System.out.print("                    BatteryType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBatteryType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetBezelMaterialType()) {
                            System.out.print("                    BezelMaterialType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBezelMaterialType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetBinding()) {
                            System.out.print("                    Binding");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBinding());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetBrand()) {
                            System.out.print("                    Brand");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getBrand());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCalendarType()) {
                            System.out.print("                    CalendarType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCalendarType());
                            System.out.println();
                        }
                        java.util.List<String> cameraManualFeaturessList  =  merchantItemAttributes.getCameraManualFeatures();
                        for (String cameraManualFeatures : cameraManualFeaturessList) { 
                            System.out.print("                    CameraManualFeatures");
                                System.out.println();
                            System.out.print("                        " + cameraManualFeatures);
                        }	
                        if (merchantItemAttributes.isSetCaseDiameter()) {
                            System.out.print("                    CaseDiameter");
                            System.out.println();
                            DecimalWithUnits  caseDiameter = merchantItemAttributes.getCaseDiameter();
                        } 
                        if (merchantItemAttributes.isSetCaseMaterialType()) {
                            System.out.print("                    CaseMaterialType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCaseMaterialType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCaseThickness()) {
                            System.out.print("                    CaseThickness");
                            System.out.println();
                            DecimalWithUnits  caseThickness = merchantItemAttributes.getCaseThickness();
                        } 
                        if (merchantItemAttributes.isSetCaseType()) {
                            System.out.print("                    CaseType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCaseType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCatalogNumber()) {
                            System.out.print("                    CatalogNumber");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCatalogNumber());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCDRWDescription()) {
                            System.out.print("                    CDRWDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCDRWDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetChainType()) {
                            System.out.print("                    ChainType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getChainType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetClaspType()) {
                            System.out.print("                    ClaspType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getClaspType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetClothingSize()) {
                            System.out.print("                    ClothingSize");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getClothingSize());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetColor()) {
                            System.out.print("                    Color");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getColor());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCompatibility()) {
                            System.out.print("                    Compatibility");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCompatibility());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetComputerHardwareType()) {
                            System.out.print("                    ComputerHardwareType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getComputerHardwareType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetComputerPlatform()) {
                            System.out.print("                    ComputerPlatform");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getComputerPlatform());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetConnectivity()) {
                            System.out.print("                    Connectivity");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getConnectivity());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetContinuousShootingSpeed()) {
                            System.out.print("                    ContinuousShootingSpeed");
                            System.out.println();
                            DecimalWithUnits  continuousShootingSpeed = merchantItemAttributes.getContinuousShootingSpeed();
                        } 
                        if (merchantItemAttributes.isSetCountry()) {
                            System.out.print("                    Country");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCountry());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCountryOfOrigin()) {
                            System.out.print("                    CountryOfOrigin");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCountryOfOrigin());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCPUManufacturer()) {
                            System.out.print("                    CPUManufacturer");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCPUManufacturer());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCPUSpeed()) {
                            System.out.print("                    CPUSpeed");
                            System.out.println();
                            DecimalWithUnits  CPUSpeed = merchantItemAttributes.getCPUSpeed();
                        } 
                        if (merchantItemAttributes.isSetCPUType()) {
                            System.out.print("                    CPUType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCPUType());
                            System.out.println();
                        }
                        java.util.List<Creator> creatorsList = merchantItemAttributes.getCreator();
                        for (Creator creator : creatorsList) {
                            System.out.print("                    Creator");
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCuisine()) {
                            System.out.print("                    Cuisine");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCuisine());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetCustomizable()) {
                            System.out.print("                    Customizable");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getCustomizable());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDelayBetweenShots()) {
                            System.out.print("                    DelayBetweenShots");
                            System.out.println();
                            DecimalWithUnits  delayBetweenShots = merchantItemAttributes.getDelayBetweenShots();
                        } 
                        if (merchantItemAttributes.isSetDeliveryOption()) {
                            System.out.print("                    DeliveryOption");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDeliveryOption());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDepartment()) {
                            System.out.print("                    Department");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDepartment());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDescription()) {
                            System.out.print("                    Description");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDeweyDecimalNumber()) {
                            System.out.print("                    DeweyDecimalNumber");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDeweyDecimalNumber());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDialColor()) {
                            System.out.print("                    DialColor");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDialColor());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDialWindowMaterialType()) {
                            System.out.print("                    DialWindowMaterialType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDialWindowMaterialType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDigitalZoom()) {
                            System.out.print("                    DigitalZoom");
                            System.out.println();
                            DecimalWithUnits  digitalZoom = merchantItemAttributes.getDigitalZoom();
                        } 
                        java.util.List<String> directorsList  =  merchantItemAttributes.getDirector();
                        for (String director : directorsList) { 
                            System.out.print("                    Director");
                                System.out.println();
                            System.out.print("                        " + director);
                        }	
                        if (merchantItemAttributes.isSetDisplaySize()) {
                            System.out.print("                    DisplaySize");
                            System.out.println();
                            DecimalWithUnits  displaySize = merchantItemAttributes.getDisplaySize();
                        } 
                        if (merchantItemAttributes.isSetDrumSetPieceQuantity()) {
                            System.out.print("                    DrumSetPieceQuantity");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDrumSetPieceQuantity());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDVDLayers()) {
                            System.out.print("                    DVDLayers");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDVDLayers());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDVDRWDescription()) {
                            System.out.print("                    DVDRWDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDVDRWDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDVDSides()) {
                            System.out.print("                    DVDSides");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDVDSides());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetDPCI()) {
                            System.out.print("                    DPCI");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getDPCI());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetEAN()) {
                            System.out.print("                    EAN");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getEAN());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetEdition()) {
                            System.out.print("                    Edition");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getEdition());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetESRBAgeRating()) {
                            System.out.print("                    ESRBAgeRating");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getESRBAgeRating());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetExternalDisplaySupportDescription()) {
                            System.out.print("                    ExternalDisplaySupportDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getExternalDisplaySupportDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetFabricType()) {
                            System.out.print("                    FabricType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getFabricType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetFaxNumber()) {
                            System.out.print("                    FaxNumber");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getFaxNumber());
                            System.out.println();
                        }
                        java.util.List<String> featuresList  =  merchantItemAttributes.getFeature();
                        for (String feature : featuresList) { 
                            System.out.print("                    Feature");
                                System.out.println();
                            System.out.print("                        " + feature);
                        }	
                        if (merchantItemAttributes.isSetFirstIssueLeadTime()) {
                            System.out.print("                    FirstIssueLeadTime");
                            System.out.println();
                            StringWithUnits  firstIssueLeadTime = merchantItemAttributes.getFirstIssueLeadTime();
                        } 
                        if (merchantItemAttributes.isSetFloppyDiskDriveDescription()) {
                            System.out.print("                    FloppyDiskDriveDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getFloppyDiskDriveDescription());
                            System.out.println();
                        }
                        java.util.List<String> formatsList  =  merchantItemAttributes.getFormat();
                        for (String format : formatsList) { 
                            System.out.print("                    Format");
                                System.out.println();
                            System.out.print("                        " + format);
                        }	
                        if (merchantItemAttributes.isSetFixedShippingCharge()) {
                            System.out.print("                    FixedShippingCharge");
                            System.out.println();
                            Price  fixedShippingCharge = merchantItemAttributes.getFixedShippingCharge();
                            if (fixedShippingCharge.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + fixedShippingCharge.getAmount());
                                System.out.println();
                            }
                            if (fixedShippingCharge.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + fixedShippingCharge.getCurrencyCode());
                                System.out.println();
                            }
                            if (fixedShippingCharge.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + fixedShippingCharge.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (merchantItemAttributes.isSetGemType()) {
                            System.out.print("                    GemType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getGemType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetGraphicsCardInterface()) {
                            System.out.print("                    GraphicsCardInterface");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getGraphicsCardInterface());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetGraphicsDescription()) {
                            System.out.print("                    GraphicsDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getGraphicsDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetGraphicsMemorySize()) {
                            System.out.print("                    GraphicsMemorySize");
                            System.out.println();
                            DecimalWithUnits  graphicsMemorySize = merchantItemAttributes.getGraphicsMemorySize();
                        } 
                        if (merchantItemAttributes.isSetGuitarAttribute()) {
                            System.out.print("                    GuitarAttribute");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getGuitarAttribute());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetGuitarBridgeSystem()) {
                            System.out.print("                    GuitarBridgeSystem");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getGuitarBridgeSystem());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetGuitarPickThickness()) {
                            System.out.print("                    GuitarPickThickness");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getGuitarPickThickness());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetGuitarPickupConfiguration()) {
                            System.out.print("                    GuitarPickupConfiguration");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getGuitarPickupConfiguration());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHardDiskCount()) {
                            System.out.print("                    HardDiskCount");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getHardDiskCount());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHardDiskSize()) {
                            System.out.print("                    HardDiskSize");
                            System.out.println();
                            NonNegativeIntegerWithUnits  hardDiskSize = merchantItemAttributes.getHardDiskSize();
                        } 
                        if (merchantItemAttributes.isSetHasAutoFocus()) {
                            System.out.print("                    HasAutoFocus");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isHasAutoFocus());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHasBurstMode()) {
                            System.out.print("                    HasBurstMode");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isHasBurstMode());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHasInCameraEditing()) {
                            System.out.print("                    HasInCameraEditing");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isHasInCameraEditing());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHasRedEyeReduction()) {
                            System.out.print("                    HasRedEyeReduction");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isHasRedEyeReduction());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHasSelfTimer()) {
                            System.out.print("                    HasSelfTimer");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isHasSelfTimer());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHasTripodMount()) {
                            System.out.print("                    HasTripodMount");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isHasTripodMount());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHasVideoOut()) {
                            System.out.print("                    HasVideoOut");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isHasVideoOut());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHasViewfinder()) {
                            System.out.print("                    HasViewfinder");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isHasViewfinder());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHazardousMaterialType()) {
                            System.out.print("                    HazardousMaterialType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getHazardousMaterialType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetHoursOfOperation()) {
                            System.out.print("                    HoursOfOperation");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getHoursOfOperation());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIncludedSoftware()) {
                            System.out.print("                    IncludedSoftware");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getIncludedSoftware());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIncludesMp3Player()) {
                            System.out.print("                    IncludesMp3Player");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isIncludesMp3Player());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIndications()) {
                            System.out.print("                    Indications");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getIndications());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIngredients()) {
                            System.out.print("                    Ingredients");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getIngredients());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetInstrumentKey()) {
                            System.out.print("                    InstrumentKey");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getInstrumentKey());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIsAutographed()) {
                            System.out.print("                    IsAutographed");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isIsAutographed());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetISBN()) {
                            System.out.print("                    ISBN");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getISBN());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIsEmailNotifyAvailable()) {
                            System.out.print("                    IsEmailNotifyAvailable");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isIsEmailNotifyAvailable());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIsFragile()) {
                            System.out.print("                    IsFragile");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isIsFragile());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIsLabCreated()) {
                            System.out.print("                    IsLabCreated");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isIsLabCreated());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetIsMemorabilia()) {
                            System.out.print("                    IsMemorabilia");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.isIsMemorabilia());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetISOEquivalent()) {
                            System.out.print("                    ISOEquivalent");
                            System.out.println();
                            NonNegativeIntegerWithUnits  ISOEquivalent = merchantItemAttributes.getISOEquivalent();
                        } 
                        if (merchantItemAttributes.isSetIssuesPerYear()) {
                            System.out.print("                    IssuesPerYear");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getIssuesPerYear());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetItemDimensions()) {
                            System.out.print("                    ItemDimensions");
                            System.out.println();
                            ItemDimensions  itemDimensions = merchantItemAttributes.getItemDimensions();
                            if (itemDimensions.isSetHeight()) {
                                System.out.print("                        Height");
                                System.out.println();
                                DecimalWithUnits  height = itemDimensions.getHeight();
                            } 
                            if (itemDimensions.isSetLength()) {
                                System.out.print("                        Length");
                                System.out.println();
                                DecimalWithUnits  length = itemDimensions.getLength();
                            } 
                            if (itemDimensions.isSetWeight()) {
                                System.out.print("                        Weight");
                                System.out.println();
                                DecimalWithUnits  weight = itemDimensions.getWeight();
                            } 
                            if (itemDimensions.isSetWidth()) {
                                System.out.print("                        Width");
                                System.out.println();
                                DecimalWithUnits  width = itemDimensions.getWidth();
                            } 
                        } 
                        if (merchantItemAttributes.isSetKeyboardDescription()) {
                            System.out.print("                    KeyboardDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getKeyboardDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetLabel()) {
                            System.out.print("                    Label");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getLabel());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetLanguages()) {
                            System.out.print("                    Languages");
                            System.out.println();
                            Languages  languages = merchantItemAttributes.getLanguages();
                            java.util.List<Language> languagesList = languages.getLanguage();
                            for (Language language : languagesList) {
                                System.out.print("                        Language");
                                System.out.println();
                                if (language.isSetName()) {
                                    System.out.print("                            Name");
                                    System.out.println();
                                    System.out.print("                                " + language.getName());
                                    System.out.println();
                                }
                                if (language.isSetType()) {
                                    System.out.print("                            Type");
                                    System.out.println();
                                    System.out.print("                                " + language.getType());
                                    System.out.println();
                                }
                                if (language.isSetAudioFormat()) {
                                    System.out.print("                            AudioFormat");
                                    System.out.println();
                                    System.out.print("                                " + language.getAudioFormat());
                                    System.out.println();
                                }
                            }
                        } 
                        if (merchantItemAttributes.isSetLegalDisclaimer()) {
                            System.out.print("                    LegalDisclaimer");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getLegalDisclaimer());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetLineVoltage()) {
                            System.out.print("                    LineVoltage");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getLineVoltage());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetListPrice()) {
                            System.out.print("                    ListPrice");
                            System.out.println();
                            Price  listPrice = merchantItemAttributes.getListPrice();
                            if (listPrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + listPrice.getAmount());
                                System.out.println();
                            }
                            if (listPrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + listPrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (listPrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + listPrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (merchantItemAttributes.isSetMacroFocusRange()) {
                            System.out.print("                    MacroFocusRange");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMacroFocusRange());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMagazineType()) {
                            System.out.print("                    MagazineType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMagazineType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMalletHardness()) {
                            System.out.print("                    MalletHardness");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMalletHardness());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetManufacturer()) {
                            System.out.print("                    Manufacturer");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getManufacturer());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetManufacturerLaborWarrantyDescription()) {
                            System.out.print("                    ManufacturerLaborWarrantyDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getManufacturerLaborWarrantyDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetManufacturerMaximumAge()) {
                            System.out.print("                    ManufacturerMaximumAge");
                            System.out.println();
                            DecimalWithUnits  manufacturerMaximumAge = merchantItemAttributes.getManufacturerMaximumAge();
                        } 
                        if (merchantItemAttributes.isSetManufacturerMinimumAge()) {
                            System.out.print("                    ManufacturerMinimumAge");
                            System.out.println();
                            DecimalWithUnits  manufacturerMinimumAge = merchantItemAttributes.getManufacturerMinimumAge();
                        } 
                        if (merchantItemAttributes.isSetManufacturerPartsWarrantyDescription()) {
                            System.out.print("                    ManufacturerPartsWarrantyDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getManufacturerPartsWarrantyDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMaterialType()) {
                            System.out.print("                    MaterialType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMaterialType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMaximumAperture()) {
                            System.out.print("                    MaximumAperture");
                            System.out.println();
                            DecimalWithUnits  maximumAperture = merchantItemAttributes.getMaximumAperture();
                        } 
                        if (merchantItemAttributes.isSetMaximumColorDepth()) {
                            System.out.print("                    MaximumColorDepth");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMaximumColorDepth());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMaximumFocalLength()) {
                            System.out.print("                    MaximumFocalLength");
                            System.out.println();
                            DecimalWithUnits  maximumFocalLength = merchantItemAttributes.getMaximumFocalLength();
                        } 
                        if (merchantItemAttributes.isSetMaximumHighResolutionImages()) {
                            System.out.print("                    MaximumHighResolutionImages");
                            System.out.println();
                            NonNegativeIntegerWithUnits  maximumHighResolutionImages = merchantItemAttributes.getMaximumHighResolutionImages();
                        } 
                        if (merchantItemAttributes.isSetMaximumHorizontalResolution()) {
                            System.out.print("                    MaximumHorizontalResolution");
                            System.out.println();
                            NonNegativeIntegerWithUnits  maximumHorizontalResolution = merchantItemAttributes.getMaximumHorizontalResolution();
                        } 
                        if (merchantItemAttributes.isSetMaximumLowResolutionImages()) {
                            System.out.print("                    MaximumLowResolutionImages");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMaximumLowResolutionImages());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMaximumResolution()) {
                            System.out.print("                    MaximumResolution");
                            System.out.println();
                            DecimalWithUnits  maximumResolution = merchantItemAttributes.getMaximumResolution();
                        } 
                        if (merchantItemAttributes.isSetMaximumShutterSpeed()) {
                            System.out.print("                    MaximumShutterSpeed");
                            System.out.println();
                            DecimalWithUnits  maximumShutterSpeed = merchantItemAttributes.getMaximumShutterSpeed();
                        } 
                        if (merchantItemAttributes.isSetMaximumVerticalResolution()) {
                            System.out.print("                    MaximumVerticalResolution");
                            System.out.println();
                            NonNegativeIntegerWithUnits  maximumVerticalResolution = merchantItemAttributes.getMaximumVerticalResolution();
                        } 
                        if (merchantItemAttributes.isSetMaximumWeightRecommendation()) {
                            System.out.print("                    MaximumWeightRecommendation");
                            System.out.println();
                            DecimalWithUnits  maximumWeightRecommendation = merchantItemAttributes.getMaximumWeightRecommendation();
                        } 
                        if (merchantItemAttributes.isSetMemorySlotsAvailable()) {
                            System.out.print("                    MemorySlotsAvailable");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMemorySlotsAvailable());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMetalStamp()) {
                            System.out.print("                    MetalStamp");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMetalStamp());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMetalType()) {
                            System.out.print("                    MetalType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMetalType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMiniMovieDescription()) {
                            System.out.print("                    MiniMovieDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMiniMovieDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMinimumFocalLength()) {
                            System.out.print("                    MinimumFocalLength");
                            System.out.println();
                            DecimalWithUnits  minimumFocalLength = merchantItemAttributes.getMinimumFocalLength();
                        } 
                        if (merchantItemAttributes.isSetMinimumShutterSpeed()) {
                            System.out.print("                    MinimumShutterSpeed");
                            System.out.println();
                            DecimalWithUnits  minimumShutterSpeed = merchantItemAttributes.getMinimumShutterSpeed();
                        } 
                        if (merchantItemAttributes.isSetModel()) {
                            System.out.print("                    Model");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getModel());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetModelYear()) {
                            System.out.print("                    ModelYear");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getModelYear());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetModemDescription()) {
                            System.out.print("                    ModemDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getModemDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMonitorSize()) {
                            System.out.print("                    MonitorSize");
                            System.out.println();
                            DecimalWithUnits  monitorSize = merchantItemAttributes.getMonitorSize();
                        } 
                        if (merchantItemAttributes.isSetMonitorViewableDiagonalSize()) {
                            System.out.print("                    MonitorViewableDiagonalSize");
                            System.out.println();
                            DecimalWithUnits  monitorViewableDiagonalSize = merchantItemAttributes.getMonitorViewableDiagonalSize();
                        } 
                        if (merchantItemAttributes.isSetMouseDescription()) {
                            System.out.print("                    MouseDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMouseDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMPN()) {
                            System.out.print("                    MPN");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMPN());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetMusicalStyle()) {
                            System.out.print("                    MusicalStyle");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getMusicalStyle());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNativeResolution()) {
                            System.out.print("                    NativeResolution");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNativeResolution());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNeighborhood()) {
                            System.out.print("                    Neighborhood");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNeighborhood());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNetworkInterfaceDescription()) {
                            System.out.print("                    NetworkInterfaceDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNetworkInterfaceDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNotebookDisplayTechnology()) {
                            System.out.print("                    NotebookDisplayTechnology");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNotebookDisplayTechnology());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNotebookPointingDeviceDescription()) {
                            System.out.print("                    NotebookPointingDeviceDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNotebookPointingDeviceDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfDiscs()) {
                            System.out.print("                    NumberOfDiscs");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfDiscs());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfIssues()) {
                            System.out.print("                    NumberOfIssues");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfIssues());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfItems()) {
                            System.out.print("                    NumberOfItems");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfItems());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfKeys()) {
                            System.out.print("                    NumberOfKeys");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfKeys());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfPages()) {
                            System.out.print("                    NumberOfPages");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfPages());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfPearls()) {
                            System.out.print("                    NumberOfPearls");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfPearls());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfRapidFireShots()) {
                            System.out.print("                    NumberOfRapidFireShots");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfRapidFireShots());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfStones()) {
                            System.out.print("                    NumberOfStones");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfStones());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfStrings()) {
                            System.out.print("                    NumberOfStrings");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfStrings());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetNumberOfTracks()) {
                            System.out.print("                    NumberOfTracks");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getNumberOfTracks());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetOpticalZoom()) {
                            System.out.print("                    OpticalZoom");
                            System.out.println();
                            DecimalWithUnits  opticalZoom = merchantItemAttributes.getOpticalZoom();
                        } 
                        if (merchantItemAttributes.isSetOriginalReleaseDate()) {
                            System.out.print("                    OriginalReleaseDate");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getOriginalReleaseDate());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetOutputWattage()) {
                            System.out.print("                    OutputWattage");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getOutputWattage());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPackageDimensions()) {
                            System.out.print("                    PackageDimensions");
                            System.out.println();
                            PackageDimensions  packageDimensions = merchantItemAttributes.getPackageDimensions();
                            if (packageDimensions.isSetHeight()) {
                                System.out.print("                        Height");
                                System.out.println();
                                DecimalWithUnits  height = packageDimensions.getHeight();
                            } 
                            if (packageDimensions.isSetLength()) {
                                System.out.print("                        Length");
                                System.out.println();
                                DecimalWithUnits  length = packageDimensions.getLength();
                            } 
                            if (packageDimensions.isSetWeight()) {
                                System.out.print("                        Weight");
                                System.out.println();
                                DecimalWithUnits  weight = packageDimensions.getWeight();
                            } 
                            if (packageDimensions.isSetWidth()) {
                                System.out.print("                        Width");
                                System.out.println();
                                DecimalWithUnits  width = packageDimensions.getWidth();
                            } 
                        } 
                        if (merchantItemAttributes.isSetPearlLustre()) {
                            System.out.print("                    PearlLustre");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPearlLustre());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPearlMinimumColor()) {
                            System.out.print("                    PearlMinimumColor");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPearlMinimumColor());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPearlShape()) {
                            System.out.print("                    PearlShape");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPearlShape());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPearlStringingMethod()) {
                            System.out.print("                    PearlStringingMethod");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPearlStringingMethod());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPearlSurfaceBlemishes()) {
                            System.out.print("                    PearlSurfaceBlemishes");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPearlSurfaceBlemishes());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPearlType()) {
                            System.out.print("                    PearlType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPearlType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPearlUniformity()) {
                            System.out.print("                    PearlUniformity");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPearlUniformity());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPhoneNumber()) {
                            System.out.print("                    PhoneNumber");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPhoneNumber());
                            System.out.println();
                        }
                        java.util.List<String> photoFlashTypesList  =  merchantItemAttributes.getPhotoFlashType();
                        for (String photoFlashType : photoFlashTypesList) { 
                            System.out.print("                    PhotoFlashType");
                                System.out.println();
                            System.out.print("                        " + photoFlashType);
                        }	
                        java.util.List<String> pictureFormatsList  =  merchantItemAttributes.getPictureFormat();
                        for (String pictureFormat : pictureFormatsList) { 
                            System.out.print("                    PictureFormat");
                                System.out.println();
                            System.out.print("                        " + pictureFormat);
                        }	
                        java.util.List<String> platformsList  =  merchantItemAttributes.getPlatform();
                        for (String platform : platformsList) { 
                            System.out.print("                    Platform");
                                System.out.println();
                            System.out.print("                        " + platform);
                        }	
                        if (merchantItemAttributes.isSetPriceRating()) {
                            System.out.print("                    PriceRating");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPriceRating());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetProcessorCount()) {
                            System.out.print("                    ProcessorCount");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getProcessorCount());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetProductGroup()) {
                            System.out.print("                    ProductGroup");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getProductGroup());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPromotionalTag()) {
                            System.out.print("                    PromotionalTag");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPromotionalTag());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPOBoxShippingExcluded()) {
                            System.out.print("                    POBoxShippingExcluded");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPOBoxShippingExcluded());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPublicationDate()) {
                            System.out.print("                    PublicationDate");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPublicationDate());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetPublisher()) {
                            System.out.print("                    Publisher");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getPublisher());
                            System.out.println();
                        }
                        java.util.List<String> purchasingChannelsList  =  merchantItemAttributes.getPurchasingChannel();
                        for (String purchasingChannel : purchasingChannelsList) { 
                            System.out.print("                    PurchasingChannel");
                                System.out.println();
                            System.out.print("                        " + purchasingChannel);
                        }	
                        if (merchantItemAttributes.isSetReadingLevel()) {
                            System.out.print("                    ReadingLevel");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getReadingLevel());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetRecorderTrackCount()) {
                            System.out.print("                    RecorderTrackCount");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getRecorderTrackCount());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetRegionCode()) {
                            System.out.print("                    RegionCode");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getRegionCode());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetRegionOfOrigin()) {
                            System.out.print("                    RegionOfOrigin");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getRegionOfOrigin());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetReleaseDate()) {
                            System.out.print("                    ReleaseDate");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getReleaseDate());
                            System.out.println();
                        }
                        java.util.List<String> returnMethodsList  =  merchantItemAttributes.getReturnMethod();
                        for (String returnMethod : returnMethodsList) { 
                            System.out.print("                    ReturnMethod");
                                System.out.println();
                            System.out.print("                        " + returnMethod);
                        }	
                        if (merchantItemAttributes.isSetRemovableMemory()) {
                            System.out.print("                    RemovableMemory");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getRemovableMemory());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetResolutionModes()) {
                            System.out.print("                    ResolutionModes");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getResolutionModes());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetReturnPolicy()) {
                            System.out.print("                    ReturnPolicy");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getReturnPolicy());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetRingSize()) {
                            System.out.print("                    RingSize");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getRingSize());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSafetyWarning()) {
                            System.out.print("                    SafetyWarning");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSafetyWarning());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSalesRestriction()) {
                            System.out.print("                    SalesRestriction");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSalesRestriction());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSecondaryCacheSize()) {
                            System.out.print("                    SecondaryCacheSize");
                            System.out.println();
                            NonNegativeIntegerWithUnits  secondaryCacheSize = merchantItemAttributes.getSecondaryCacheSize();
                        } 
                        if (merchantItemAttributes.isSetSettingType()) {
                            System.out.print("                    SettingType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSettingType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSize()) {
                            System.out.print("                    Size");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSize());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSKU()) {
                            System.out.print("                    SKU");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSKU());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSoldInStores()) {
                            System.out.print("                    SoldInStores");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSoldInStores());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSizePerPearl()) {
                            System.out.print("                    SizePerPearl");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSizePerPearl());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSkillLevel()) {
                            System.out.print("                    SkillLevel");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSkillLevel());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSoundCardDescription()) {
                            System.out.print("                    SoundCardDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSoundCardDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSpeakerCount()) {
                            System.out.print("                    SpeakerCount");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSpeakerCount());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSpeakerDescription()) {
                            System.out.print("                    SpeakerDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSpeakerDescription());
                            System.out.println();
                        }
                        java.util.List<String> specialFeaturessList  =  merchantItemAttributes.getSpecialFeatures();
                        for (String specialFeatures : specialFeaturessList) { 
                            System.out.print("                    SpecialFeatures");
                                System.out.println();
                            System.out.print("                        " + specialFeatures);
                        }	
                        if (merchantItemAttributes.isSetStoneClarity()) {
                            System.out.print("                    StoneClarity");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getStoneClarity());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetStoneColor()) {
                            System.out.print("                    StoneColor");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getStoneColor());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetStoneCut()) {
                            System.out.print("                    StoneCut");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getStoneCut());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetStoneShape()) {
                            System.out.print("                    StoneShape");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getStoneShape());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetStoneWeight()) {
                            System.out.print("                    StoneWeight");
                            System.out.println();
                            DecimalWithUnits  stoneWeight = merchantItemAttributes.getStoneWeight();
                        } 
                        if (merchantItemAttributes.isSetStudio()) {
                            System.out.print("                    Studio");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getStudio());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetSubscriptionLength()) {
                            System.out.print("                    SubscriptionLength");
                            System.out.println();
                            NonNegativeIntegerWithUnits  subscriptionLength = merchantItemAttributes.getSubscriptionLength();
                        } 
                        java.util.List<String> supportedImageTypesList  =  merchantItemAttributes.getSupportedImageType();
                        for (String supportedImageType : supportedImageTypesList) { 
                            System.out.print("                    SupportedImageType");
                                System.out.println();
                            System.out.print("                        " + supportedImageType);
                        }	
                        if (merchantItemAttributes.isSetSystemBusSpeed()) {
                            System.out.print("                    SystemBusSpeed");
                            System.out.println();
                            DecimalWithUnits  systemBusSpeed = merchantItemAttributes.getSystemBusSpeed();
                        } 
                        if (merchantItemAttributes.isSetSystemMemorySizeMax()) {
                            System.out.print("                    SystemMemorySizeMax");
                            System.out.println();
                            DecimalWithUnits  systemMemorySizeMax = merchantItemAttributes.getSystemMemorySizeMax();
                        } 
                        if (merchantItemAttributes.isSetSystemMemorySize()) {
                            System.out.print("                    SystemMemorySize");
                            System.out.println();
                            DecimalWithUnits  systemMemorySize = merchantItemAttributes.getSystemMemorySize();
                        } 
                        if (merchantItemAttributes.isSetSystemMemoryType()) {
                            System.out.print("                    SystemMemoryType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getSystemMemoryType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTellingPageIndicator()) {
                            System.out.print("                    TellingPageIndicator");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTellingPageIndicator());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTheatricalReleaseDate()) {
                            System.out.print("                    TheatricalReleaseDate");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTheatricalReleaseDate());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTitle());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalDiamondWeight()) {
                            System.out.print("                    TotalDiamondWeight");
                            System.out.println();
                            DecimalWithUnits  totalDiamondWeight = merchantItemAttributes.getTotalDiamondWeight();
                        } 
                        if (merchantItemAttributes.isSetTotalExternalBaysFree()) {
                            System.out.print("                    TotalExternalBaysFree");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalExternalBaysFree());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalFirewirePorts()) {
                            System.out.print("                    TotalFirewirePorts");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalFirewirePorts());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalGemWeight()) {
                            System.out.print("                    TotalGemWeight");
                            System.out.println();
                            DecimalWithUnits  totalGemWeight = merchantItemAttributes.getTotalGemWeight();
                        } 
                        if (merchantItemAttributes.isSetTotalInternalBaysFree()) {
                            System.out.print("                    TotalInternalBaysFree");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalInternalBaysFree());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalMetalWeight()) {
                            System.out.print("                    TotalMetalWeight");
                            System.out.println();
                            DecimalWithUnits  totalMetalWeight = merchantItemAttributes.getTotalMetalWeight();
                        } 
                        if (merchantItemAttributes.isSetTotalNTSCPALPorts()) {
                            System.out.print("                    TotalNTSCPALPorts");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalNTSCPALPorts());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalParallelPorts()) {
                            System.out.print("                    TotalParallelPorts");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalParallelPorts());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalPCCardSlots()) {
                            System.out.print("                    TotalPCCardSlots");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalPCCardSlots());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalPCISlotsFree()) {
                            System.out.print("                    TotalPCISlotsFree");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalPCISlotsFree());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalSerialPorts()) {
                            System.out.print("                    TotalSerialPorts");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalSerialPorts());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalSVideoOutPorts()) {
                            System.out.print("                    TotalSVideoOutPorts");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalSVideoOutPorts());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalUSB2Ports()) {
                            System.out.print("                    TotalUSB2Ports");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalUSB2Ports());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalUSBPorts()) {
                            System.out.print("                    TotalUSBPorts");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalUSBPorts());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetTotalVGAOutPorts()) {
                            System.out.print("                    TotalVGAOutPorts");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getTotalVGAOutPorts());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetUPC()) {
                            System.out.print("                    UPC");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getUPC());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetVariationDenomination()) {
                            System.out.print("                    VariationDenomination");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getVariationDenomination());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetVariationDescription()) {
                            System.out.print("                    VariationDescription");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getVariationDescription());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetVendorRebate()) {
                            System.out.print("                    VendorRebate");
                            System.out.println();
                            VendorRebate  vendorRebate = merchantItemAttributes.getVendorRebate();
                            if (vendorRebate.isSetType()) {
                                System.out.print("                        Type");
                                System.out.println();
                                System.out.print("                            " + vendorRebate.getType());
                                System.out.println();
                            }
                            if (vendorRebate.isSetStartDate()) {
                                System.out.print("                        StartDate");
                                System.out.println();
                                System.out.print("                            " + vendorRebate.getStartDate());
                                System.out.println();
                            }
                            if (vendorRebate.isSetEndDate()) {
                                System.out.print("                        EndDate");
                                System.out.println();
                                System.out.print("                            " + vendorRebate.getEndDate());
                                System.out.println();
                            }
                        } 
                        if (merchantItemAttributes.isSetWarranty()) {
                            System.out.print("                    Warranty");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getWarranty());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetWatchMovementType()) {
                            System.out.print("                    WatchMovementType");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getWatchMovementType());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetWebsiteBuyability()) {
                            System.out.print("                    WebsiteBuyability");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getWebsiteBuyability());
                            System.out.println();
                        }
                        if (merchantItemAttributes.isSetWaterResistanceDepth()) {
                            System.out.print("                    WaterResistanceDepth");
                            System.out.println();
                            DecimalWithUnits  waterResistanceDepth = merchantItemAttributes.getWaterResistanceDepth();
                        } 
                        if (merchantItemAttributes.isSetWirelessMicrophoneFrequency()) {
                            System.out.print("                    WirelessMicrophoneFrequency");
                            System.out.println();
                            System.out.print("                        " + merchantItemAttributes.getWirelessMicrophoneFrequency());
                            System.out.println();
                        }
                    } 
                    if (item.isSetCollections()) {
                        System.out.print("                Collections");
                        System.out.println();
                        Collections  collections = item.getCollections();
                        java.util.List<Collection> collectionsList = collections.getCollection();
                        for (Collection collection : collectionsList) {
                            System.out.print("                    Collection");
                            System.out.println();
                            if (collection.isSetCollectionSummary()) {
                                System.out.print("                        CollectionSummary");
                                System.out.println();
                                CollectionSummary  collectionSummary = collection.getCollectionSummary();
                                if (collectionSummary.isSetLowestListPrice()) {
                                    System.out.print("                            LowestListPrice");
                                    System.out.println();
                                    Price  lowestListPrice = collectionSummary.getLowestListPrice();
                                    if (lowestListPrice.isSetAmount()) {
                                        System.out.print("                                Amount");
                                        System.out.println();
                                        System.out.print("                                    " + lowestListPrice.getAmount());
                                        System.out.println();
                                    }
                                    if (lowestListPrice.isSetCurrencyCode()) {
                                        System.out.print("                                CurrencyCode");
                                        System.out.println();
                                        System.out.print("                                    " + lowestListPrice.getCurrencyCode());
                                        System.out.println();
                                    }
                                    if (lowestListPrice.isSetFormattedPrice()) {
                                        System.out.print("                                FormattedPrice");
                                        System.out.println();
                                        System.out.print("                                    " + lowestListPrice.getFormattedPrice());
                                        System.out.println();
                                    }
                                } 
                                if (collectionSummary.isSetHighestListPrice()) {
                                    System.out.print("                            HighestListPrice");
                                    System.out.println();
                                    Price  highestListPrice = collectionSummary.getHighestListPrice();
                                    if (highestListPrice.isSetAmount()) {
                                        System.out.print("                                Amount");
                                        System.out.println();
                                        System.out.print("                                    " + highestListPrice.getAmount());
                                        System.out.println();
                                    }
                                    if (highestListPrice.isSetCurrencyCode()) {
                                        System.out.print("                                CurrencyCode");
                                        System.out.println();
                                        System.out.print("                                    " + highestListPrice.getCurrencyCode());
                                        System.out.println();
                                    }
                                    if (highestListPrice.isSetFormattedPrice()) {
                                        System.out.print("                                FormattedPrice");
                                        System.out.println();
                                        System.out.print("                                    " + highestListPrice.getFormattedPrice());
                                        System.out.println();
                                    }
                                } 
                                if (collectionSummary.isSetLowestSalePrice()) {
                                    System.out.print("                            LowestSalePrice");
                                    System.out.println();
                                    Price  lowestSalePrice = collectionSummary.getLowestSalePrice();
                                    if (lowestSalePrice.isSetAmount()) {
                                        System.out.print("                                Amount");
                                        System.out.println();
                                        System.out.print("                                    " + lowestSalePrice.getAmount());
                                        System.out.println();
                                    }
                                    if (lowestSalePrice.isSetCurrencyCode()) {
                                        System.out.print("                                CurrencyCode");
                                        System.out.println();
                                        System.out.print("                                    " + lowestSalePrice.getCurrencyCode());
                                        System.out.println();
                                    }
                                    if (lowestSalePrice.isSetFormattedPrice()) {
                                        System.out.print("                                FormattedPrice");
                                        System.out.println();
                                        System.out.print("                                    " + lowestSalePrice.getFormattedPrice());
                                        System.out.println();
                                    }
                                } 
                                if (collectionSummary.isSetHighestSalePrice()) {
                                    System.out.print("                            HighestSalePrice");
                                    System.out.println();
                                    Price  highestSalePrice = collectionSummary.getHighestSalePrice();
                                    if (highestSalePrice.isSetAmount()) {
                                        System.out.print("                                Amount");
                                        System.out.println();
                                        System.out.print("                                    " + highestSalePrice.getAmount());
                                        System.out.println();
                                    }
                                    if (highestSalePrice.isSetCurrencyCode()) {
                                        System.out.print("                                CurrencyCode");
                                        System.out.println();
                                        System.out.print("                                    " + highestSalePrice.getCurrencyCode());
                                        System.out.println();
                                    }
                                    if (highestSalePrice.isSetFormattedPrice()) {
                                        System.out.print("                                FormattedPrice");
                                        System.out.println();
                                        System.out.print("                                    " + highestSalePrice.getFormattedPrice());
                                        System.out.println();
                                    }
                                } 
                            } 
                            if (collection.isSetCollectionParent()) {
                                System.out.print("                        CollectionParent");
                                System.out.println();
                                CollectionParent  collectionParent = collection.getCollectionParent();
                                if (collectionParent.isSetASIN()) {
                                    System.out.print("                            ASIN");
                                    System.out.println();
                                    System.out.print("                                " + collectionParent.getASIN());
                                    System.out.println();
                                }
                                if (collectionParent.isSetTitle()) {
                                    System.out.print("                            Title");
                                    System.out.println();
                                    System.out.print("                                " + collectionParent.getTitle());
                                    System.out.println();
                                }
                            } 
                            java.util.List<CollectionItem> collectionItemsList = collection.getCollectionItem();
                            for (CollectionItem collectionItem : collectionItemsList) {
                                System.out.print("                        CollectionItem");
                                System.out.println();
                                if (collectionItem.isSetASIN()) {
                                    System.out.print("                            ASIN");
                                    System.out.println();
                                    System.out.print("                                " + collectionItem.getASIN());
                                    System.out.println();
                                }
                                if (collectionItem.isSetTitle()) {
                                    System.out.print("                            Title");
                                    System.out.println();
                                    System.out.print("                                " + collectionItem.getTitle());
                                    System.out.println();
                                }
                            }
                        }
                    } 
                    if (item.isSetSubjects()) {
                        System.out.print("                Subjects");
                        System.out.println();
                        Subjects  subjects = item.getSubjects();
                        java.util.List<String> subjectsList  =  subjects.getSubject();
                        for (String subject : subjectsList) { 
                            System.out.print("                    Subject");
                                System.out.println();
                            System.out.print("                        " + subject);
                        }	
                    } 
                    if (item.isSetOfferSummary()) {
                        System.out.print("                OfferSummary");
                        System.out.println();
                        OfferSummary  offerSummary = item.getOfferSummary();
                        if (offerSummary.isSetLowestNewPrice()) {
                            System.out.print("                    LowestNewPrice");
                            System.out.println();
                            Price  lowestNewPrice = offerSummary.getLowestNewPrice();
                            if (lowestNewPrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + lowestNewPrice.getAmount());
                                System.out.println();
                            }
                            if (lowestNewPrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + lowestNewPrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (lowestNewPrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + lowestNewPrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (offerSummary.isSetLowestUsedPrice()) {
                            System.out.print("                    LowestUsedPrice");
                            System.out.println();
                            Price  lowestUsedPrice = offerSummary.getLowestUsedPrice();
                            if (lowestUsedPrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + lowestUsedPrice.getAmount());
                                System.out.println();
                            }
                            if (lowestUsedPrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + lowestUsedPrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (lowestUsedPrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + lowestUsedPrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (offerSummary.isSetLowestCollectiblePrice()) {
                            System.out.print("                    LowestCollectiblePrice");
                            System.out.println();
                            Price  lowestCollectiblePrice = offerSummary.getLowestCollectiblePrice();
                            if (lowestCollectiblePrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + lowestCollectiblePrice.getAmount());
                                System.out.println();
                            }
                            if (lowestCollectiblePrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + lowestCollectiblePrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (lowestCollectiblePrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + lowestCollectiblePrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (offerSummary.isSetLowestRefurbishedPrice()) {
                            System.out.print("                    LowestRefurbishedPrice");
                            System.out.println();
                            Price  lowestRefurbishedPrice = offerSummary.getLowestRefurbishedPrice();
                            if (lowestRefurbishedPrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + lowestRefurbishedPrice.getAmount());
                                System.out.println();
                            }
                            if (lowestRefurbishedPrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + lowestRefurbishedPrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (lowestRefurbishedPrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + lowestRefurbishedPrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (offerSummary.isSetTotalNew()) {
                            System.out.print("                    TotalNew");
                            System.out.println();
                            System.out.print("                        " + offerSummary.getTotalNew());
                            System.out.println();
                        }
                        if (offerSummary.isSetTotalUsed()) {
                            System.out.print("                    TotalUsed");
                            System.out.println();
                            System.out.print("                        " + offerSummary.getTotalUsed());
                            System.out.println();
                        }
                        if (offerSummary.isSetTotalCollectible()) {
                            System.out.print("                    TotalCollectible");
                            System.out.println();
                            System.out.print("                        " + offerSummary.getTotalCollectible());
                            System.out.println();
                        }
                        if (offerSummary.isSetTotalRefurbished()) {
                            System.out.print("                    TotalRefurbished");
                            System.out.println();
                            System.out.print("                        " + offerSummary.getTotalRefurbished());
                            System.out.println();
                        }
                    } 
                    if (item.isSetOffers()) {
                        System.out.print("                Offers");
                        System.out.println();
                        Offers  offers = item.getOffers();
                        if (offers.isSetTotalOffers()) {
                            System.out.print("                    TotalOffers");
                            System.out.println();
                            System.out.print("                        " + offers.getTotalOffers());
                            System.out.println();
                        }
                        if (offers.isSetTotalOfferPages()) {
                            System.out.print("                    TotalOfferPages");
                            System.out.println();
                            System.out.print("                        " + offers.getTotalOfferPages());
                            System.out.println();
                        }
                        java.util.List<Offer> offersList = offers.getOffer();
                        for (Offer offer : offersList) {
                            System.out.print("                    Offer");
                            System.out.println();
                            if (offer.isSetMerchant()) {
                                System.out.print("                        Merchant");
                                System.out.println();
                                Merchant  merchant = offer.getMerchant();
                                if (merchant.isSetMerchantId()) {
                                    System.out.print("                            MerchantId");
                                    System.out.println();
                                    System.out.print("                                " + merchant.getMerchantId());
                                    System.out.println();
                                }
                                if (merchant.isSetName()) {
                                    System.out.print("                            Name");
                                    System.out.println();
                                    System.out.print("                                " + merchant.getName());
                                    System.out.println();
                                }
                                if (merchant.isSetGlancePage()) {
                                    System.out.print("                            GlancePage");
                                    System.out.println();
                                    System.out.print("                                " + merchant.getGlancePage());
                                    System.out.println();
                                }
                                if (merchant.isSetAverageFeedbackRating()) {
                                    System.out.print("                            AverageFeedbackRating");
                                    System.out.println();
                                    System.out.print("                                " + merchant.getAverageFeedbackRating());
                                    System.out.println();
                                }
                                if (merchant.isSetTotalFeedback()) {
                                    System.out.print("                            TotalFeedback");
                                    System.out.println();
                                    System.out.print("                                " + merchant.getTotalFeedback());
                                    System.out.println();
                                }
                                if (merchant.isSetTotalFeedbackPages()) {
                                    System.out.print("                            TotalFeedbackPages");
                                    System.out.println();
                                    System.out.print("                                " + merchant.getTotalFeedbackPages());
                                    System.out.println();
                                }
                            } 
                            if (offer.isSetSeller()) {
                                System.out.print("                        Seller");
                                System.out.println();
                                Seller  seller = offer.getSeller();
                                if (seller.isSetSellerId()) {
                                    System.out.print("                            SellerId");
                                    System.out.println();
                                    System.out.print("                                " + seller.getSellerId());
                                    System.out.println();
                                }
                                if (seller.isSetSellerName()) {
                                    System.out.print("                            SellerName");
                                    System.out.println();
                                    System.out.print("                                " + seller.getSellerName());
                                    System.out.println();
                                }
                                if (seller.isSetSellerLegalName()) {
                                    System.out.print("                            SellerLegalName");
                                    System.out.println();
                                    System.out.print("                                " + seller.getSellerLegalName());
                                    System.out.println();
                                }
                                if (seller.isSetNickname()) {
                                    System.out.print("                            Nickname");
                                    System.out.println();
                                    System.out.print("                                " + seller.getNickname());
                                    System.out.println();
                                }
                                if (seller.isSetGlancePage()) {
                                    System.out.print("                            GlancePage");
                                    System.out.println();
                                    System.out.print("                                " + seller.getGlancePage());
                                    System.out.println();
                                }
                                if (seller.isSetAbout()) {
                                    System.out.print("                            About");
                                    System.out.println();
                                    System.out.print("                                " + seller.getAbout());
                                    System.out.println();
                                }
                                if (seller.isSetMoreAbout()) {
                                    System.out.print("                            MoreAbout");
                                    System.out.println();
                                    System.out.print("                                " + seller.getMoreAbout());
                                    System.out.println();
                                }
                                if (seller.isSetLocation()) {
                                    System.out.print("                            Location");
                                    System.out.println();
                                    SellerLocation  location = seller.getLocation();
                                    if (location.isSetUserDefinedLocation()) {
                                        System.out.print("                                UserDefinedLocation");
                                        System.out.println();
                                        System.out.print("                                    " + location.getUserDefinedLocation());
                                        System.out.println();
                                    }
                                    if (location.isSetCity()) {
                                        System.out.print("                                City");
                                        System.out.println();
                                        System.out.print("                                    " + location.getCity());
                                        System.out.println();
                                    }
                                    if (location.isSetState()) {
                                        System.out.print("                                State");
                                        System.out.println();
                                        System.out.print("                                    " + location.getState());
                                        System.out.println();
                                    }
                                    if (location.isSetCountry()) {
                                        System.out.print("                                Country");
                                        System.out.println();
                                        System.out.print("                                    " + location.getCountry());
                                        System.out.println();
                                    }
                                } 
                                if (seller.isSetAverageFeedbackRating()) {
                                    System.out.print("                            AverageFeedbackRating");
                                    System.out.println();
                                    System.out.print("                                " + seller.getAverageFeedbackRating());
                                    System.out.println();
                                }
                                if (seller.isSetTotalFeedback()) {
                                    System.out.print("                            TotalFeedback");
                                    System.out.println();
                                    System.out.print("                                " + seller.getTotalFeedback());
                                    System.out.println();
                                }
                                if (seller.isSetTotalFeedbackPages()) {
                                    System.out.print("                            TotalFeedbackPages");
                                    System.out.println();
                                    System.out.print("                                " + seller.getTotalFeedbackPages());
                                    System.out.println();
                                }
                                if (seller.isSetSellerFeedbackSummary()) {
                                    System.out.print("                            SellerFeedbackSummary");
                                    System.out.println();
                                    SellerFeedbackSummary  sellerFeedbackSummary = seller.getSellerFeedbackSummary();
                                    java.util.List<FeedbackDateRange> feedbackDateRangesList = sellerFeedbackSummary.getFeedbackDateRange();
                                    for (FeedbackDateRange feedbackDateRange : feedbackDateRangesList) {
                                        System.out.print("                                FeedbackDateRange");
                                        System.out.println();
                                        java.util.List<SellerFeedbackRating> sellerFeedbackRatingsList = feedbackDateRange.getSellerFeedbackRating();
                                        for (SellerFeedbackRating sellerFeedbackRating : sellerFeedbackRatingsList) {
                                            System.out.print("                                    SellerFeedbackRating");
                                            System.out.println();
                                            if (sellerFeedbackRating.isSetCount()) {
                                                System.out.print("                                        Count");
                                                System.out.println();
                                                System.out.print("                                            " + sellerFeedbackRating.getCount());
                                                System.out.println();
                                            }
                                            if (sellerFeedbackRating.isSetPercentage()) {
                                                System.out.print("                                        Percentage");
                                                System.out.println();
                                                System.out.print("                                            " + sellerFeedbackRating.getPercentage());
                                                System.out.println();
                                            }
                                        }
                                    }
                                } 
                                if (seller.isSetSellerFeedback()) {
                                    System.out.print("                            SellerFeedback");
                                    System.out.println();
                                    SellerFeedback  sellerFeedback = seller.getSellerFeedback();
                                    java.util.List<Feedback> feedbacksList = sellerFeedback.getFeedback();
                                    for (Feedback feedback : feedbacksList) {
                                        System.out.print("                                Feedback");
                                        System.out.println();
                                        if (feedback.isSetRating()) {
                                            System.out.print("                                    Rating");
                                            System.out.println();
                                            System.out.print("                                        " + feedback.getRating());
                                            System.out.println();
                                        }
                                        if (feedback.isSetComment()) {
                                            System.out.print("                                    Comment");
                                            System.out.println();
                                            System.out.print("                                        " + feedback.getComment());
                                            System.out.println();
                                        }
                                        if (feedback.isSetDate()) {
                                            System.out.print("                                    Date");
                                            System.out.println();
                                            System.out.print("                                        " + feedback.getDate());
                                            System.out.println();
                                        }
                                        if (feedback.isSetRatedBy()) {
                                            System.out.print("                                    RatedBy");
                                            System.out.println();
                                            System.out.print("                                        " + feedback.getRatedBy());
                                            System.out.println();
                                        }
                                    }
                                } 
                            } 
                            if (offer.isSetOfferAttributes()) {
                                System.out.print("                        OfferAttributes");
                                System.out.println();
                                OfferAttributes  offerAttributes = offer.getOfferAttributes();
                                if (offerAttributes.isSetCondition()) {
                                    System.out.print("                            Condition");
                                    System.out.println();
                                    System.out.print("                                " + offerAttributes.getCondition());
                                    System.out.println();
                                }
                                if (offerAttributes.isSetSubCondition()) {
                                    System.out.print("                            SubCondition");
                                    System.out.println();
                                    System.out.print("                                " + offerAttributes.getSubCondition());
                                    System.out.println();
                                }
                                if (offerAttributes.isSetConditionNote()) {
                                    System.out.print("                            ConditionNote");
                                    System.out.println();
                                    System.out.print("                                " + offerAttributes.getConditionNote());
                                    System.out.println();
                                }
                                if (offerAttributes.isSetWillShipExpedited()) {
                                    System.out.print("                            WillShipExpedited");
                                    System.out.println();
                                    System.out.print("                                " + offerAttributes.isWillShipExpedited());
                                    System.out.println();
                                }
                                if (offerAttributes.isSetWillShipInternational()) {
                                    System.out.print("                            WillShipInternational");
                                    System.out.println();
                                    System.out.print("                                " + offerAttributes.isWillShipInternational());
                                    System.out.println();
                                }
                            } 
                            java.util.List<OfferListing> offerListingsList = offer.getOfferListing();
                            for (OfferListing offerListing : offerListingsList) {
                                System.out.print("                        OfferListing");
                                System.out.println();
                                if (offerListing.isSetOfferListingId()) {
                                    System.out.print("                            OfferListingId");
                                    System.out.println();
                                    System.out.print("                                " + offerListing.getOfferListingId());
                                    System.out.println();
                                }
                                if (offerListing.isSetExchangeId()) {
                                    System.out.print("                            ExchangeId");
                                    System.out.println();
                                    System.out.print("                                " + offerListing.getExchangeId());
                                    System.out.println();
                                }
                                if (offerListing.isSetPrice()) {
                                    System.out.print("                            Price");
                                    System.out.println();
                                    Price  price = offerListing.getPrice();
                                    if (price.isSetAmount()) {
                                        System.out.print("                                Amount");
                                        System.out.println();
                                        System.out.print("                                    " + price.getAmount());
                                        System.out.println();
                                    }
                                    if (price.isSetCurrencyCode()) {
                                        System.out.print("                                CurrencyCode");
                                        System.out.println();
                                        System.out.print("                                    " + price.getCurrencyCode());
                                        System.out.println();
                                    }
                                    if (price.isSetFormattedPrice()) {
                                        System.out.print("                                FormattedPrice");
                                        System.out.println();
                                        System.out.print("                                    " + price.getFormattedPrice());
                                        System.out.println();
                                    }
                                } 
                                if (offerListing.isSetSalePrice()) {
                                    System.out.print("                            SalePrice");
                                    System.out.println();
                                    Price  salePrice = offerListing.getSalePrice();
                                    if (salePrice.isSetAmount()) {
                                        System.out.print("                                Amount");
                                        System.out.println();
                                        System.out.print("                                    " + salePrice.getAmount());
                                        System.out.println();
                                    }
                                    if (salePrice.isSetCurrencyCode()) {
                                        System.out.print("                                CurrencyCode");
                                        System.out.println();
                                        System.out.print("                                    " + salePrice.getCurrencyCode());
                                        System.out.println();
                                    }
                                    if (salePrice.isSetFormattedPrice()) {
                                        System.out.print("                                FormattedPrice");
                                        System.out.println();
                                        System.out.print("                                    " + salePrice.getFormattedPrice());
                                        System.out.println();
                                    }
                                } 
                                if (offerListing.isSetAmountSaved()) {
                                    System.out.print("                            AmountSaved");
                                    System.out.println();
                                    Price  amountSaved = offerListing.getAmountSaved();
                                    if (amountSaved.isSetAmount()) {
                                        System.out.print("                                Amount");
                                        System.out.println();
                                        System.out.print("                                    " + amountSaved.getAmount());
                                        System.out.println();
                                    }
                                    if (amountSaved.isSetCurrencyCode()) {
                                        System.out.print("                                CurrencyCode");
                                        System.out.println();
                                        System.out.print("                                    " + amountSaved.getCurrencyCode());
                                        System.out.println();
                                    }
                                    if (amountSaved.isSetFormattedPrice()) {
                                        System.out.print("                                FormattedPrice");
                                        System.out.println();
                                        System.out.print("                                    " + amountSaved.getFormattedPrice());
                                        System.out.println();
                                    }
                                } 
                                if (offerListing.isSetPercentageSaved()) {
                                    System.out.print("                            PercentageSaved");
                                    System.out.println();
                                    System.out.print("                                " + offerListing.getPercentageSaved());
                                    System.out.println();
                                }
                                if (offerListing.isSetAvailability()) {
                                    System.out.print("                            Availability");
                                    System.out.println();
                                    System.out.print("                                " + offerListing.getAvailability());
                                    System.out.println();
                                }
                                if (offerListing.isSetAvailabilityAttributes()) {
                                    System.out.print("                            AvailabilityAttributes");
                                    System.out.println();
                                    AvailabilityAttributes  availabilityAttributes = offerListing.getAvailabilityAttributes();
                                    if (availabilityAttributes.isSetAvailabilityType()) {
                                        System.out.print("                                AvailabilityType");
                                        System.out.println();
                                        System.out.print("                                    " + availabilityAttributes.getAvailabilityType());
                                        System.out.println();
                                    }
                                    if (availabilityAttributes.isSetIsPreorder()) {
                                        System.out.print("                                IsPreorder");
                                        System.out.println();
                                        System.out.print("                                    " + availabilityAttributes.isIsPreorder());
                                        System.out.println();
                                    }
                                    if (availabilityAttributes.isSetMinimumHours()) {
                                        System.out.print("                                MinimumHours");
                                        System.out.println();
                                        System.out.print("                                    " + availabilityAttributes.getMinimumHours());
                                        System.out.println();
                                    }
                                    if (availabilityAttributes.isSetMaximumHours()) {
                                        System.out.print("                                MaximumHours");
                                        System.out.println();
                                        System.out.print("                                    " + availabilityAttributes.getMaximumHours());
                                        System.out.println();
                                    }
                                } 
                                if (offerListing.isSetQuantity()) {
                                    System.out.print("                            Quantity");
                                    System.out.println();
                                    System.out.print("                                " + offerListing.getQuantity());
                                    System.out.println();
                                }
                                if (offerListing.isSetISPUStoreAddress()) {
                                    System.out.print("                            ISPUStoreAddress");
                                    System.out.println();
                                    Address  ISPUStoreAddress = offerListing.getISPUStoreAddress();
                                    if (ISPUStoreAddress.isSetName()) {
                                        System.out.print("                                Name");
                                        System.out.println();
                                        System.out.print("                                    " + ISPUStoreAddress.getName());
                                        System.out.println();
                                    }
                                    if (ISPUStoreAddress.isSetAddress1()) {
                                        System.out.print("                                Address1");
                                        System.out.println();
                                        System.out.print("                                    " + ISPUStoreAddress.getAddress1());
                                        System.out.println();
                                    }
                                    if (ISPUStoreAddress.isSetAddress2()) {
                                        System.out.print("                                Address2");
                                        System.out.println();
                                        System.out.print("                                    " + ISPUStoreAddress.getAddress2());
                                        System.out.println();
                                    }
                                    if (ISPUStoreAddress.isSetAddress3()) {
                                        System.out.print("                                Address3");
                                        System.out.println();
                                        System.out.print("                                    " + ISPUStoreAddress.getAddress3());
                                        System.out.println();
                                    }
                                    if (ISPUStoreAddress.isSetCity()) {
                                        System.out.print("                                City");
                                        System.out.println();
                                        System.out.print("                                    " + ISPUStoreAddress.getCity());
                                        System.out.println();
                                    }
                                    if (ISPUStoreAddress.isSetState()) {
                                        System.out.print("                                State");
                                        System.out.println();
                                        System.out.print("                                    " + ISPUStoreAddress.getState());
                                        System.out.println();
                                    }
                                    if (ISPUStoreAddress.isSetPostalCode()) {
                                        System.out.print("                                PostalCode");
                                        System.out.println();
                                        System.out.print("                                    " + ISPUStoreAddress.getPostalCode());
                                        System.out.println();
                                    }
                                    if (ISPUStoreAddress.isSetCountry()) {
                                        System.out.print("                                Country");
                                        System.out.println();
                                        System.out.print("                                    " + ISPUStoreAddress.getCountry());
                                        System.out.println();
                                    }
                                } 
                                if (offerListing.isSetISPUStoreHours()) {
                                    System.out.print("                            ISPUStoreHours");
                                    System.out.println();
                                    System.out.print("                                " + offerListing.getISPUStoreHours());
                                    System.out.println();
                                }
                                if (offerListing.isSetIsEligibleForSuperSaverShipping()) {
                                    System.out.print("                            IsEligibleForSuperSaverShipping");
                                    System.out.println();
                                    System.out.print("                                " + offerListing.isIsEligibleForSuperSaverShipping());
                                    System.out.println();
                                }
                                if (offerListing.isSetSalesRestriction()) {
                                    System.out.print("                            SalesRestriction");
                                    System.out.println();
                                    System.out.print("                                " + offerListing.getSalesRestriction());
                                    System.out.println();
                                }
                                java.util.List<ShippingCharge> shippingChargesList = offerListing.getShippingCharge();
                                for (ShippingCharge shippingCharge : shippingChargesList) {
                                    System.out.print("                            ShippingCharge");
                                    System.out.println();
                                    if (shippingCharge.isSetShippingType()) {
                                        System.out.print("                                ShippingType");
                                        System.out.println();
                                        System.out.print("                                    " + shippingCharge.getShippingType());
                                        System.out.println();
                                    }
                                    if (shippingCharge.isSetIsRateTaxInclusive()) {
                                        System.out.print("                                IsRateTaxInclusive");
                                        System.out.println();
                                        System.out.print("                                    " + shippingCharge.isIsRateTaxInclusive());
                                        System.out.println();
                                    }
                                    if (shippingCharge.isSetShippingPrice()) {
                                        System.out.print("                                ShippingPrice");
                                        System.out.println();
                                        Price  shippingPrice = shippingCharge.getShippingPrice();
                                        if (shippingPrice.isSetAmount()) {
                                            System.out.print("                                    Amount");
                                            System.out.println();
                                            System.out.print("                                        " + shippingPrice.getAmount());
                                            System.out.println();
                                        }
                                        if (shippingPrice.isSetCurrencyCode()) {
                                            System.out.print("                                    CurrencyCode");
                                            System.out.println();
                                            System.out.print("                                        " + shippingPrice.getCurrencyCode());
                                            System.out.println();
                                        }
                                        if (shippingPrice.isSetFormattedPrice()) {
                                            System.out.print("                                    FormattedPrice");
                                            System.out.println();
                                            System.out.print("                                        " + shippingPrice.getFormattedPrice());
                                            System.out.println();
                                        }
                                    } 
                                }
                            }
                            if (offer.isSetLoyaltyPoints()) {
                                System.out.print("                        LoyaltyPoints");
                                System.out.println();
                                LoyaltyPoints  loyaltyPoints = offer.getLoyaltyPoints();
                                if (loyaltyPoints.isSetPoints()) {
                                    System.out.print("                            Points");
                                    System.out.println();
                                    System.out.print("                                " + loyaltyPoints.getPoints());
                                    System.out.println();
                                }
                                if (loyaltyPoints.isSetTypicalRedemptionValue()) {
                                    System.out.print("                            TypicalRedemptionValue");
                                    System.out.println();
                                    Price  typicalRedemptionValue = loyaltyPoints.getTypicalRedemptionValue();
                                    if (typicalRedemptionValue.isSetAmount()) {
                                        System.out.print("                                Amount");
                                        System.out.println();
                                        System.out.print("                                    " + typicalRedemptionValue.getAmount());
                                        System.out.println();
                                    }
                                    if (typicalRedemptionValue.isSetCurrencyCode()) {
                                        System.out.print("                                CurrencyCode");
                                        System.out.println();
                                        System.out.print("                                    " + typicalRedemptionValue.getCurrencyCode());
                                        System.out.println();
                                    }
                                    if (typicalRedemptionValue.isSetFormattedPrice()) {
                                        System.out.print("                                FormattedPrice");
                                        System.out.println();
                                        System.out.print("                                    " + typicalRedemptionValue.getFormattedPrice());
                                        System.out.println();
                                    }
                                } 
                            } 
                            if (offer.isSetPromotions()) {
                                System.out.print("                        Promotions");
                                System.out.println();
                                Promotions  promotions = offer.getPromotions();
                                java.util.List<Promotion> promotionsList = promotions.getPromotion();
                                for (Promotion promotion : promotionsList) {
                                    System.out.print("                            Promotion");
                                    System.out.println();
                                    if (promotion.isSetSummary()) {
                                        System.out.print("                                Summary");
                                        System.out.println();
                                        PromotionSummary  summary = promotion.getSummary();
                                        if (summary.isSetPromotionId()) {
                                            System.out.print("                                    PromotionId");
                                            System.out.println();
                                            System.out.print("                                        " + summary.getPromotionId());
                                            System.out.println();
                                        }
                                        if (summary.isSetCategory()) {
                                            System.out.print("                                    Category");
                                            System.out.println();
                                            System.out.print("                                        " + summary.getCategory());
                                            System.out.println();
                                        }
                                        if (summary.isSetStartDate()) {
                                            System.out.print("                                    StartDate");
                                            System.out.println();
                                            System.out.print("                                        " + summary.getStartDate());
                                            System.out.println();
                                        }
                                        if (summary.isSetEndDate()) {
                                            System.out.print("                                    EndDate");
                                            System.out.println();
                                            System.out.print("                                        " + summary.getEndDate());
                                            System.out.println();
                                        }
                                        if (summary.isSetEligibilityRequirementDescription()) {
                                            System.out.print("                                    EligibilityRequirementDescription");
                                            System.out.println();
                                            System.out.print("                                        " + summary.getEligibilityRequirementDescription());
                                            System.out.println();
                                        }
                                        if (summary.isSetBenefitDescription()) {
                                            System.out.print("                                    BenefitDescription");
                                            System.out.println();
                                            System.out.print("                                        " + summary.getBenefitDescription());
                                            System.out.println();
                                        }
                                        if (summary.isSetTermsAndConditions()) {
                                            System.out.print("                                    TermsAndConditions");
                                            System.out.println();
                                            System.out.print("                                        " + summary.getTermsAndConditions());
                                            System.out.println();
                                        }
                                    } 
                                    if (promotion.isSetDetails()) {
                                        System.out.print("                                Details");
                                        System.out.println();
                                        PromotionDetails  details = promotion.getDetails();
                                        if (details.isSetMerchantId()) {
                                            System.out.print("                                    MerchantId");
                                            System.out.println();
                                            System.out.print("                                        " + details.getMerchantId());
                                            System.out.println();
                                        }
                                        if (details.isSetOwningMerchantId()) {
                                            System.out.print("                                    OwningMerchantId");
                                            System.out.println();
                                            System.out.print("                                        " + details.getOwningMerchantId());
                                            System.out.println();
                                        }
                                        if (details.isSetPromotionId()) {
                                            System.out.print("                                    PromotionId");
                                            System.out.println();
                                            System.out.print("                                        " + details.getPromotionId());
                                            System.out.println();
                                        }
                                        if (details.isSetPromotionCategory()) {
                                            System.out.print("                                    PromotionCategory");
                                            System.out.println();
                                            System.out.print("                                        " + details.getPromotionCategory());
                                            System.out.println();
                                        }
                                        if (details.isSetMerchantPromotionId()) {
                                            System.out.print("                                    MerchantPromotionId");
                                            System.out.println();
                                            System.out.print("                                        " + details.getMerchantPromotionId());
                                            System.out.println();
                                        }
                                        if (details.isSetGroupClaimCode()) {
                                            System.out.print("                                    GroupClaimCode");
                                            System.out.println();
                                            System.out.print("                                        " + details.getGroupClaimCode());
                                            System.out.println();
                                        }
                                        if (details.isSetCouponCombinationType()) {
                                            System.out.print("                                    CouponCombinationType");
                                            System.out.println();
                                            System.out.print("                                        " + details.getCouponCombinationType());
                                            System.out.println();
                                        }
                                        if (details.isSetStartDate()) {
                                            System.out.print("                                    StartDate");
                                            System.out.println();
                                            System.out.print("                                        " + details.getStartDate());
                                            System.out.println();
                                        }
                                        if (details.isSetEndDate()) {
                                            System.out.print("                                    EndDate");
                                            System.out.println();
                                            System.out.print("                                        " + details.getEndDate());
                                            System.out.println();
                                        }
                                        if (details.isSetTermsAndConditions()) {
                                            System.out.print("                                    TermsAndConditions");
                                            System.out.println();
                                            System.out.print("                                        " + details.getTermsAndConditions());
                                            System.out.println();
                                        }
                                        if (details.isSetEligibilityRequirements()) {
                                            System.out.print("                                    EligibilityRequirements");
                                            System.out.println();
                                            PromotionEligibilityRequirements  eligibilityRequirements = details.getEligibilityRequirements();
                                            java.util.List<PromotionEligibilityRequirement> eligibilityRequirementsList = eligibilityRequirements.getEligibilityRequirement();
                                            for (PromotionEligibilityRequirement eligibilityRequirement : eligibilityRequirementsList) {
                                                System.out.print("                                        EligibilityRequirement");
                                                System.out.println();
                                                if (eligibilityRequirement.isSetEligibilityRequirementType()) {
                                                    System.out.print("                                            EligibilityRequirementType");
                                                    System.out.println();
                                                    System.out.print("                                                " + eligibilityRequirement.getEligibilityRequirementType());
                                                    System.out.println();
                                                }
                                                if (eligibilityRequirement.isSetQuantity()) {
                                                    System.out.print("                                            Quantity");
                                                    System.out.println();
                                                    System.out.print("                                                " + eligibilityRequirement.getQuantity());
                                                    System.out.println();
                                                }
                                                if (eligibilityRequirement.isSetCurrencyAmount()) {
                                                    System.out.print("                                            CurrencyAmount");
                                                    System.out.println();
                                                    Price  currencyAmount = eligibilityRequirement.getCurrencyAmount();
                                                    if (currencyAmount.isSetAmount()) {
                                                        System.out.print("                                                Amount");
                                                        System.out.println();
                                                        System.out.print("                                                    " + currencyAmount.getAmount());
                                                        System.out.println();
                                                    }
                                                    if (currencyAmount.isSetCurrencyCode()) {
                                                        System.out.print("                                                CurrencyCode");
                                                        System.out.println();
                                                        System.out.print("                                                    " + currencyAmount.getCurrencyCode());
                                                        System.out.println();
                                                    }
                                                    if (currencyAmount.isSetFormattedPrice()) {
                                                        System.out.print("                                                FormattedPrice");
                                                        System.out.println();
                                                        System.out.print("                                                    " + currencyAmount.getFormattedPrice());
                                                        System.out.println();
                                                    }
                                                } 
                                            }
                                        } 
                                        if (details.isSetBenefits()) {
                                            System.out.print("                                    Benefits");
                                            System.out.println();
                                            PromotionBenefits  benefits = details.getBenefits();
                                            java.util.List<PromotionBenefit> benefitsList = benefits.getBenefit();
                                            for (PromotionBenefit benefit : benefitsList) {
                                                System.out.print("                                        Benefit");
                                                System.out.println();
                                                if (benefit.isSetBenefitType()) {
                                                    System.out.print("                                            BenefitType");
                                                    System.out.println();
                                                    System.out.print("                                                " + benefit.getBenefitType());
                                                    System.out.println();
                                                }
                                                if (benefit.isSetComponentType()) {
                                                    System.out.print("                                            ComponentType");
                                                    System.out.println();
                                                    System.out.print("                                                " + benefit.getComponentType());
                                                    System.out.println();
                                                }
                                                if (benefit.isSetQuantity()) {
                                                    System.out.print("                                            Quantity");
                                                    System.out.println();
                                                    System.out.print("                                                " + benefit.getQuantity());
                                                    System.out.println();
                                                }
                                                if (benefit.isSetPercentOff()) {
                                                    System.out.print("                                            PercentOff");
                                                    System.out.println();
                                                    System.out.print("                                                " + benefit.getPercentOff());
                                                    System.out.println();
                                                }
                                                if (benefit.isSetFixedAmount()) {
                                                    System.out.print("                                            FixedAmount");
                                                    System.out.println();
                                                    Price  fixedAmount = benefit.getFixedAmount();
                                                    if (fixedAmount.isSetAmount()) {
                                                        System.out.print("                                                Amount");
                                                        System.out.println();
                                                        System.out.print("                                                    " + fixedAmount.getAmount());
                                                        System.out.println();
                                                    }
                                                    if (fixedAmount.isSetCurrencyCode()) {
                                                        System.out.print("                                                CurrencyCode");
                                                        System.out.println();
                                                        System.out.print("                                                    " + fixedAmount.getCurrencyCode());
                                                        System.out.println();
                                                    }
                                                    if (fixedAmount.isSetFormattedPrice()) {
                                                        System.out.print("                                                FormattedPrice");
                                                        System.out.println();
                                                        System.out.print("                                                    " + fixedAmount.getFormattedPrice());
                                                        System.out.println();
                                                    }
                                                } 
                                                if (benefit.isSetCeiling()) {
                                                    System.out.print("                                            Ceiling");
                                                    System.out.println();
                                                    Price  ceiling = benefit.getCeiling();
                                                    if (ceiling.isSetAmount()) {
                                                        System.out.print("                                                Amount");
                                                        System.out.println();
                                                        System.out.print("                                                    " + ceiling.getAmount());
                                                        System.out.println();
                                                    }
                                                    if (ceiling.isSetCurrencyCode()) {
                                                        System.out.print("                                                CurrencyCode");
                                                        System.out.println();
                                                        System.out.print("                                                    " + ceiling.getCurrencyCode());
                                                        System.out.println();
                                                    }
                                                    if (ceiling.isSetFormattedPrice()) {
                                                        System.out.print("                                                FormattedPrice");
                                                        System.out.println();
                                                        System.out.print("                                                    " + ceiling.getFormattedPrice());
                                                        System.out.println();
                                                    }
                                                } 
                                            }
                                        } 
                                        if (details.isSetItemApplicability()) {
                                            System.out.print("                                    ItemApplicability");
                                            System.out.println();
                                            PromotionItemApplicability  itemApplicability = details.getItemApplicability();
                                            if (itemApplicability.isSetASIN()) {
                                                System.out.print("                                        ASIN");
                                                System.out.println();
                                                System.out.print("                                            " + itemApplicability.getASIN());
                                                System.out.println();
                                            }
                                            if (itemApplicability.isSetIsInBenefitSet()) {
                                                System.out.print("                                        IsInBenefitSet");
                                                System.out.println();
                                                System.out.print("                                            " + itemApplicability.isIsInBenefitSet());
                                                System.out.println();
                                            }
                                            if (itemApplicability.isSetIsInEligibilityRequirementSet()) {
                                                System.out.print("                                        IsInEligibilityRequirementSet");
                                                System.out.println();
                                                System.out.print("                                            " + itemApplicability.isIsInEligibilityRequirementSet());
                                                System.out.println();
                                            }
                                        } 
                                        if (details.isSetMerchandisingMessage()) {
                                            System.out.print("                                    MerchandisingMessage");
                                            System.out.println();
                                            System.out.print("                                        " + details.getMerchandisingMessage());
                                            System.out.println();
                                        }
                                    } 
                                }
                            } 
                        }
                    } 
                    if (item.isSetVariationSummary()) {
                        System.out.print("                VariationSummary");
                        System.out.println();
                        VariationSummary  variationSummary = item.getVariationSummary();
                        if (variationSummary.isSetLowestPrice()) {
                            System.out.print("                    LowestPrice");
                            System.out.println();
                            Price  lowestPrice = variationSummary.getLowestPrice();
                            if (lowestPrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + lowestPrice.getAmount());
                                System.out.println();
                            }
                            if (lowestPrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + lowestPrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (lowestPrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + lowestPrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (variationSummary.isSetHighestPrice()) {
                            System.out.print("                    HighestPrice");
                            System.out.println();
                            Price  highestPrice = variationSummary.getHighestPrice();
                            if (highestPrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + highestPrice.getAmount());
                                System.out.println();
                            }
                            if (highestPrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + highestPrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (highestPrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + highestPrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (variationSummary.isSetLowestSalePrice()) {
                            System.out.print("                    LowestSalePrice");
                            System.out.println();
                            Price  lowestSalePrice = variationSummary.getLowestSalePrice();
                            if (lowestSalePrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + lowestSalePrice.getAmount());
                                System.out.println();
                            }
                            if (lowestSalePrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + lowestSalePrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (lowestSalePrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + lowestSalePrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (variationSummary.isSetHighestSalePrice()) {
                            System.out.print("                    HighestSalePrice");
                            System.out.println();
                            Price  highestSalePrice = variationSummary.getHighestSalePrice();
                            if (highestSalePrice.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + highestSalePrice.getAmount());
                                System.out.println();
                            }
                            if (highestSalePrice.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + highestSalePrice.getCurrencyCode());
                                System.out.println();
                            }
                            if (highestSalePrice.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + highestSalePrice.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (variationSummary.isSetSingleMerchantId()) {
                            System.out.print("                    SingleMerchantId");
                            System.out.println();
                            System.out.print("                        " + variationSummary.getSingleMerchantId());
                            System.out.println();
                        }
                    } 
                    if (item.isSetVariations()) {
                        System.out.print("                Variations");
                        System.out.println();
                        Variations  variations = item.getVariations();
                        if (variations.isSetTotalVariations()) {
                            System.out.print("                    TotalVariations");
                            System.out.println();
                            System.out.print("                        " + variations.getTotalVariations());
                            System.out.println();
                        }
                        if (variations.isSetTotalVariationPages()) {
                            System.out.print("                    TotalVariationPages");
                            System.out.println();
                            System.out.print("                        " + variations.getTotalVariationPages());
                            System.out.println();
                        }
                        if (variations.isSetVariationDimensions()) {
                            System.out.print("                    VariationDimensions");
                            System.out.println();
                            VariationDimensions  variationDimensions = variations.getVariationDimensions();
                            java.util.List<String> variationDimensionsList  =  variationDimensions.getVariationDimension();
                            for (String variationDimension : variationDimensionsList) { 
                                System.out.print("                        VariationDimension");
                                    System.out.println();
                                System.out.print("                            " + variationDimension);
                            }	
                        } 
                    } 
                    if (item.isSetCustomerReviews()) {
                        System.out.print("                CustomerReviews");
                        System.out.println();
                        CustomerReviews  customerReviews = item.getCustomerReviews();
                        if (customerReviews.isSetAverageRating()) {
                            System.out.print("                    AverageRating");
                            System.out.println();
                            System.out.print("                        " + customerReviews.getAverageRating());
                            System.out.println();
                        }
                        if (customerReviews.isSetTotalReviews()) {
                            System.out.print("                    TotalReviews");
                            System.out.println();
                            System.out.print("                        " + customerReviews.getTotalReviews());
                            System.out.println();
                        }
                        if (customerReviews.isSetTotalReviewPages()) {
                            System.out.print("                    TotalReviewPages");
                            System.out.println();
                            System.out.print("                        " + customerReviews.getTotalReviewPages());
                            System.out.println();
                        }
                        java.util.List<Review> reviewsList = customerReviews.getReview();
                        for (Review review : reviewsList) {
                            System.out.print("                    Review");
                            System.out.println();
                            if (review.isSetASIN()) {
                                System.out.print("                        ASIN");
                                System.out.println();
                                System.out.print("                            " + review.getASIN());
                                System.out.println();
                            }
                            if (review.isSetRating()) {
                                System.out.print("                        Rating");
                                System.out.println();
                                System.out.print("                            " + review.getRating());
                                System.out.println();
                            }
                            if (review.isSetHelpfulVotes()) {
                                System.out.print("                        HelpfulVotes");
                                System.out.println();
                                System.out.print("                            " + review.getHelpfulVotes());
                                System.out.println();
                            }
                            if (review.isSetCustomerId()) {
                                System.out.print("                        CustomerId");
                                System.out.println();
                                System.out.print("                            " + review.getCustomerId());
                                System.out.println();
                            }
                            if (review.isSetReviewer()) {
                                System.out.print("                        Reviewer");
                                System.out.println();
                                Reviewer  reviewer = review.getReviewer();
                                if (reviewer.isSetCustomerId()) {
                                    System.out.print("                            CustomerId");
                                    System.out.println();
                                    System.out.print("                                " + reviewer.getCustomerId());
                                    System.out.println();
                                }
                                if (reviewer.isSetName()) {
                                    System.out.print("                            Name");
                                    System.out.println();
                                    System.out.print("                                " + reviewer.getName());
                                    System.out.println();
                                }
                                if (reviewer.isSetNickname()) {
                                    System.out.print("                            Nickname");
                                    System.out.println();
                                    System.out.print("                                " + reviewer.getNickname());
                                    System.out.println();
                                }
                                if (reviewer.isSetLocation()) {
                                    System.out.print("                            Location");
                                    System.out.println();
                                    System.out.print("                                " + reviewer.getLocation());
                                    System.out.println();
                                }
                            } 
                            if (review.isSetTotalVotes()) {
                                System.out.print("                        TotalVotes");
                                System.out.println();
                                System.out.print("                            " + review.getTotalVotes());
                                System.out.println();
                            }
                            if (review.isSetDate()) {
                                System.out.print("                        Date");
                                System.out.println();
                                System.out.print("                            " + review.getDate());
                                System.out.println();
                            }
                            if (review.isSetSummary()) {
                                System.out.print("                        Summary");
                                System.out.println();
                                System.out.print("                            " + review.getSummary());
                                System.out.println();
                            }
                            if (review.isSetContent()) {
                                System.out.print("                        Content");
                                System.out.println();
                                System.out.print("                            " + review.getContent());
                                System.out.println();
                            }
                        }
                    } 
                    if (item.isSetEditorialReviews()) {
                        System.out.print("                EditorialReviews");
                        System.out.println();
                        EditorialReviews  editorialReviews = item.getEditorialReviews();
                        java.util.List<EditorialReview> editorialReviewsList = editorialReviews.getEditorialReview();
                        for (EditorialReview editorialReview : editorialReviewsList) {
                            System.out.print("                    EditorialReview");
                            System.out.println();
                            if (editorialReview.isSetSource()) {
                                System.out.print("                        Source");
                                System.out.println();
                                System.out.print("                            " + editorialReview.getSource());
                                System.out.println();
                            }
                            if (editorialReview.isSetContent()) {
                                System.out.print("                        Content");
                                System.out.println();
                                System.out.print("                            " + editorialReview.getContent());
                                System.out.println();
                            }
                            if (editorialReview.isSetIsLinkSuppressed()) {
                                System.out.print("                        IsLinkSuppressed");
                                System.out.println();
                                System.out.print("                            " + editorialReview.isIsLinkSuppressed());
                                System.out.println();
                            }
                        }
                    } 
                    if (item.isSetSimilarProducts()) {
                        System.out.print("                SimilarProducts");
                        System.out.println();
                        SimilarProducts  similarProducts = item.getSimilarProducts();
                        java.util.List<SimilarProduct> similarProductsList = similarProducts.getSimilarProduct();
                        for (SimilarProduct similarProduct : similarProductsList) {
                            System.out.print("                    SimilarProduct");
                            System.out.println();
                            if (similarProduct.isSetASIN()) {
                                System.out.print("                        ASIN");
                                System.out.println();
                                System.out.print("                            " + similarProduct.getASIN());
                                System.out.println();
                            }
                            if (similarProduct.isSetTitle()) {
                                System.out.print("                        Title");
                                System.out.println();
                                System.out.print("                            " + similarProduct.getTitle());
                                System.out.println();
                            }
                        }
                    } 
                    if (item.isSetAccessories()) {
                        System.out.print("                Accessories");
                        System.out.println();
                        Accessories  accessories = item.getAccessories();
                        java.util.List<Accessory> accessorysList = accessories.getAccessory();
                        for (Accessory accessory : accessorysList) {
                            System.out.print("                    Accessory");
                            System.out.println();
                            if (accessory.isSetASIN()) {
                                System.out.print("                        ASIN");
                                System.out.println();
                                System.out.print("                            " + accessory.getASIN());
                                System.out.println();
                            }
                            if (accessory.isSetTitle()) {
                                System.out.print("                        Title");
                                System.out.println();
                                System.out.print("                            " + accessory.getTitle());
                                System.out.println();
                            }
                        }
                    } 
                    if (item.isSetTracks()) {
                        System.out.print("                Tracks");
                        System.out.println();
                        Tracks  tracks = item.getTracks();
                        java.util.List<Disc> discsList = tracks.getDisc();
                        for (Disc disc : discsList) {
                            System.out.print("                    Disc");
                            System.out.println();
                            java.util.List<Track> tracksList = disc.getTrack();
                            for (Track track : tracksList) {
                                System.out.print("                        Track");
                                System.out.println();
                            }
                        }
                    } 
                    if (item.isSetBrowseNodes()) {
                        System.out.print("                BrowseNodes");
                        System.out.println();
                        BrowseNodes  browseNodes = item.getBrowseNodes();
                        java.util.List<BrowseNode> browseNodesList = browseNodes.getBrowseNode();
                        for (BrowseNode browseNode : browseNodesList) {
                            System.out.print("                    BrowseNode");
                            System.out.println();
                            if (browseNode.isSetBrowseNodeId()) {
                                System.out.print("                        BrowseNodeId");
                                System.out.println();
                                System.out.print("                            " + browseNode.getBrowseNodeId());
                                System.out.println();
                            }
                            if (browseNode.isSetName()) {
                                System.out.print("                        Name");
                                System.out.println();
                                System.out.print("                            " + browseNode.getName());
                                System.out.println();
                            }
                            if (browseNode.isSetIsCategoryRoot()) {
                                System.out.print("                        IsCategoryRoot");
                                System.out.println();
                                System.out.print("                            " + browseNode.isIsCategoryRoot());
                                System.out.println();
                            }
                            if (browseNode.isSetProperties()) {
                                System.out.print("                        Properties");
                                System.out.println();
                                BrowseNodeProperties  properties = browseNode.getProperties();
                                java.util.List<Property> propertysList = properties.getProperty();
                                for (Property property : propertysList) {
                                    System.out.print("                            Property");
                                    System.out.println();
                                    if (property.isSetName()) {
                                        System.out.print("                                Name");
                                        System.out.println();
                                        System.out.print("                                    " + property.getName());
                                        System.out.println();
                                    }
                                    if (property.isSetValue()) {
                                        System.out.print("                                Value");
                                        System.out.println();
                                        System.out.print("                                    " + property.getValue());
                                        System.out.println();
                                    }
                                }
                            } 
                            if (browseNode.isSetChildren()) {
                                System.out.print("                        Children");
                                System.out.println();
                                BrowseNodeChildren  children = browseNode.getChildren();
                            } 
                            if (browseNode.isSetAncestors()) {
                                System.out.print("                        Ancestors");
                                System.out.println();
                                BrowseNodeAncestors  ancestors = browseNode.getAncestors();
                            } 
                            if (browseNode.isSetTopSellers()) {
                                System.out.print("                        TopSellers");
                                System.out.println();
                                TopSellers  topSellers = browseNode.getTopSellers();
                                java.util.List<TopSeller> topSellersList = topSellers.getTopSeller();
                                for (TopSeller topSeller : topSellersList) {
                                    System.out.print("                            TopSeller");
                                    System.out.println();
                                    if (topSeller.isSetASIN()) {
                                        System.out.print("                                ASIN");
                                        System.out.println();
                                        System.out.print("                                    " + topSeller.getASIN());
                                        System.out.println();
                                    }
                                    if (topSeller.isSetTitle()) {
                                        System.out.print("                                Title");
                                        System.out.println();
                                        System.out.print("                                    " + topSeller.getTitle());
                                        System.out.println();
                                    }
                                }
                            } 
                            if (browseNode.isSetNewReleases()) {
                                System.out.print("                        NewReleases");
                                System.out.println();
                                NewReleases  newReleases = browseNode.getNewReleases();
                                java.util.List<NewRelease> newReleasesList = newReleases.getNewRelease();
                                for (NewRelease newRelease : newReleasesList) {
                                    System.out.print("                            NewRelease");
                                    System.out.println();
                                    if (newRelease.isSetASIN()) {
                                        System.out.print("                                ASIN");
                                        System.out.println();
                                        System.out.print("                                    " + newRelease.getASIN());
                                        System.out.println();
                                    }
                                    if (newRelease.isSetTitle()) {
                                        System.out.print("                                Title");
                                        System.out.println();
                                        System.out.print("                                    " + newRelease.getTitle());
                                        System.out.println();
                                    }
                                }
                            } 
                        }
                    } 
                    if (item.isSetSearchInside()) {
                        System.out.print("                SearchInside");
                        System.out.println();
                        SearchInside  searchInside = item.getSearchInside();
                        if (searchInside.isSetTotalExcerpts()) {
                            System.out.print("                    TotalExcerpts");
                            System.out.println();
                            System.out.print("                        " + searchInside.getTotalExcerpts());
                            System.out.println();
                        }
                        if (searchInside.isSetExcerpt()) {
                            System.out.print("                    Excerpt");
                            System.out.println();
                            SearchInsideExcerpt  excerpt = searchInside.getExcerpt();
                            if (excerpt.isSetChecksum()) {
                                System.out.print("                        Checksum");
                                System.out.println();
                                System.out.print("                            " + excerpt.getChecksum());
                                System.out.println();
                            }
                            if (excerpt.isSetPageType()) {
                                System.out.print("                        PageType");
                                System.out.println();
                                System.out.print("                            " + excerpt.getPageType());
                                System.out.println();
                            }
                            if (excerpt.isSetPageNumber()) {
                                System.out.print("                        PageNumber");
                                System.out.println();
                                System.out.print("                            " + excerpt.getPageNumber());
                                System.out.println();
                            }
                            if (excerpt.isSetSequenceNumber()) {
                                System.out.print("                        SequenceNumber");
                                System.out.println();
                                System.out.print("                            " + excerpt.getSequenceNumber());
                                System.out.println();
                            }
                            if (excerpt.isSetText()) {
                                System.out.print("                        Text");
                                System.out.println();
                                System.out.print("                            " + excerpt.getText());
                                System.out.println();
                            }
                        } 
                    } 
                    if (item.isSetListmaniaLists()) {
                        System.out.print("                ListmaniaLists");
                        System.out.println();
                        ListmaniaLists  listmaniaLists = item.getListmaniaLists();
                        java.util.List<ListmaniaList> listmaniaListsList = listmaniaLists.getListmaniaList();
                        for (ListmaniaList listmaniaList : listmaniaListsList) {
                            System.out.print("                    ListmaniaList");
                            System.out.println();
                            if (listmaniaList.isSetListId()) {
                                System.out.print("                        ListId");
                                System.out.println();
                                System.out.print("                            " + listmaniaList.getListId());
                                System.out.println();
                            }
                            if (listmaniaList.isSetListName()) {
                                System.out.print("                        ListName");
                                System.out.println();
                                System.out.print("                            " + listmaniaList.getListName());
                                System.out.println();
                            }
                        }
                    } 
                    if (item.isSetTags()) {
                        System.out.print("                Tags");
                        System.out.println();
                        Tags  tags = item.getTags();
                        if (tags.isSetDistinctTags()) {
                            System.out.print("                    DistinctTags");
                            System.out.println();
                            System.out.print("                        " + tags.getDistinctTags());
                            System.out.println();
                        }
                        if (tags.isSetDistinctItems()) {
                            System.out.print("                    DistinctItems");
                            System.out.println();
                            System.out.print("                        " + tags.getDistinctItems());
                            System.out.println();
                        }
                        if (tags.isSetDistinctUsers()) {
                            System.out.print("                    DistinctUsers");
                            System.out.println();
                            System.out.print("                        " + tags.getDistinctUsers());
                            System.out.println();
                        }
                        if (tags.isSetTotalUsages()) {
                            System.out.print("                    TotalUsages");
                            System.out.println();
                            System.out.print("                        " + tags.getTotalUsages());
                            System.out.println();
                        }
                        if (tags.isSetFirstTagging()) {
                            System.out.print("                    FirstTagging");
                            System.out.println();
                            Tagging  firstTagging = tags.getFirstTagging();
                            if (firstTagging.isSetName()) {
                                System.out.print("                        Name");
                                System.out.println();
                                System.out.print("                            " + firstTagging.getName());
                                System.out.println();
                            }
                            if (firstTagging.isSetEntityId()) {
                                System.out.print("                        EntityId");
                                System.out.println();
                                System.out.print("                            " + firstTagging.getEntityId());
                                System.out.println();
                            }
                            if (firstTagging.isSetUserId()) {
                                System.out.print("                        UserId");
                                System.out.println();
                                System.out.print("                            " + firstTagging.getUserId());
                                System.out.println();
                            }
                            if (firstTagging.isSetTime()) {
                                System.out.print("                        Time");
                                System.out.println();
                                System.out.print("                            " + firstTagging.getTime());
                                System.out.println();
                            }
                        } 
                        if (tags.isSetLastTagging()) {
                            System.out.print("                    LastTagging");
                            System.out.println();
                            Tagging  lastTagging = tags.getLastTagging();
                            if (lastTagging.isSetName()) {
                                System.out.print("                        Name");
                                System.out.println();
                                System.out.print("                            " + lastTagging.getName());
                                System.out.println();
                            }
                            if (lastTagging.isSetEntityId()) {
                                System.out.print("                        EntityId");
                                System.out.println();
                                System.out.print("                            " + lastTagging.getEntityId());
                                System.out.println();
                            }
                            if (lastTagging.isSetUserId()) {
                                System.out.print("                        UserId");
                                System.out.println();
                                System.out.print("                            " + lastTagging.getUserId());
                                System.out.println();
                            }
                            if (lastTagging.isSetTime()) {
                                System.out.print("                        Time");
                                System.out.println();
                                System.out.print("                            " + lastTagging.getTime());
                                System.out.println();
                            }
                        } 
                        java.util.List<Tag> tagsList = tags.getTag();
                        for (Tag tag : tagsList) {
                            System.out.print("                    Tag");
                            System.out.println();
                            if (tag.isSetName()) {
                                System.out.print("                        Name");
                                System.out.println();
                                System.out.print("                            " + tag.getName());
                                System.out.println();
                            }
                            if (tag.isSetTagType()) {
                                System.out.print("                        TagType");
                                System.out.println();
                                System.out.print("                            " + tag.getTagType());
                                System.out.println();
                            }
                            if (tag.isSetDistinctItems()) {
                                System.out.print("                        DistinctItems");
                                System.out.println();
                                System.out.print("                            " + tag.getDistinctItems());
                                System.out.println();
                            }
                            if (tag.isSetDistinctUsers()) {
                                System.out.print("                        DistinctUsers");
                                System.out.println();
                                System.out.print("                            " + tag.getDistinctUsers());
                                System.out.println();
                            }
                            if (tag.isSetTotalUsages()) {
                                System.out.print("                        TotalUsages");
                                System.out.println();
                                System.out.print("                            " + tag.getTotalUsages());
                                System.out.println();
                            }
                            if (tag.isSetFirstTagging()) {
                                System.out.print("                        FirstTagging");
                                System.out.println();
                                Tagging  firstTagging = tag.getFirstTagging();
                                if (firstTagging.isSetName()) {
                                    System.out.print("                            Name");
                                    System.out.println();
                                    System.out.print("                                " + firstTagging.getName());
                                    System.out.println();
                                }
                                if (firstTagging.isSetEntityId()) {
                                    System.out.print("                            EntityId");
                                    System.out.println();
                                    System.out.print("                                " + firstTagging.getEntityId());
                                    System.out.println();
                                }
                                if (firstTagging.isSetUserId()) {
                                    System.out.print("                            UserId");
                                    System.out.println();
                                    System.out.print("                                " + firstTagging.getUserId());
                                    System.out.println();
                                }
                                if (firstTagging.isSetTime()) {
                                    System.out.print("                            Time");
                                    System.out.println();
                                    System.out.print("                                " + firstTagging.getTime());
                                    System.out.println();
                                }
                            } 
                            if (tag.isSetLastTagging()) {
                                System.out.print("                        LastTagging");
                                System.out.println();
                                Tagging  lastTagging = tag.getLastTagging();
                                if (lastTagging.isSetName()) {
                                    System.out.print("                            Name");
                                    System.out.println();
                                    System.out.print("                                " + lastTagging.getName());
                                    System.out.println();
                                }
                                if (lastTagging.isSetEntityId()) {
                                    System.out.print("                            EntityId");
                                    System.out.println();
                                    System.out.print("                                " + lastTagging.getEntityId());
                                    System.out.println();
                                }
                                if (lastTagging.isSetUserId()) {
                                    System.out.print("                            UserId");
                                    System.out.println();
                                    System.out.print("                                " + lastTagging.getUserId());
                                    System.out.println();
                                }
                                if (lastTagging.isSetTime()) {
                                    System.out.print("                            Time");
                                    System.out.println();
                                    System.out.print("                                " + lastTagging.getTime());
                                    System.out.println();
                                }
                            } 
                            java.util.List<TaggedItems> taggedItemssList = tag.getTaggedItems();
                            for (TaggedItems taggedItems : taggedItemssList) {
                                System.out.print("                        TaggedItems");
                                System.out.println();
                                if (taggedItems.isSetDistinctUsers()) {
                                    System.out.print("                            DistinctUsers");
                                    System.out.println();
                                    System.out.print("                                " + taggedItems.getDistinctUsers());
                                    System.out.println();
                                }
                                if (taggedItems.isSetTotalUsages()) {
                                    System.out.print("                            TotalUsages");
                                    System.out.println();
                                    System.out.print("                                " + taggedItems.getTotalUsages());
                                    System.out.println();
                                }
                                if (taggedItems.isSetFirstTagging()) {
                                    System.out.print("                            FirstTagging");
                                    System.out.println();
                                    Tagging  firstTagging = taggedItems.getFirstTagging();
                                    if (firstTagging.isSetName()) {
                                        System.out.print("                                Name");
                                        System.out.println();
                                        System.out.print("                                    " + firstTagging.getName());
                                        System.out.println();
                                    }
                                    if (firstTagging.isSetEntityId()) {
                                        System.out.print("                                EntityId");
                                        System.out.println();
                                        System.out.print("                                    " + firstTagging.getEntityId());
                                        System.out.println();
                                    }
                                    if (firstTagging.isSetUserId()) {
                                        System.out.print("                                UserId");
                                        System.out.println();
                                        System.out.print("                                    " + firstTagging.getUserId());
                                        System.out.println();
                                    }
                                    if (firstTagging.isSetTime()) {
                                        System.out.print("                                Time");
                                        System.out.println();
                                        System.out.print("                                    " + firstTagging.getTime());
                                        System.out.println();
                                    }
                                } 
                                if (taggedItems.isSetLastTagging()) {
                                    System.out.print("                            LastTagging");
                                    System.out.println();
                                    Tagging  lastTagging = taggedItems.getLastTagging();
                                    if (lastTagging.isSetName()) {
                                        System.out.print("                                Name");
                                        System.out.println();
                                        System.out.print("                                    " + lastTagging.getName());
                                        System.out.println();
                                    }
                                    if (lastTagging.isSetEntityId()) {
                                        System.out.print("                                EntityId");
                                        System.out.println();
                                        System.out.print("                                    " + lastTagging.getEntityId());
                                        System.out.println();
                                    }
                                    if (lastTagging.isSetUserId()) {
                                        System.out.print("                                UserId");
                                        System.out.println();
                                        System.out.print("                                    " + lastTagging.getUserId());
                                        System.out.println();
                                    }
                                    if (lastTagging.isSetTime()) {
                                        System.out.print("                                Time");
                                        System.out.println();
                                        System.out.print("                                    " + lastTagging.getTime());
                                        System.out.println();
                                    }
                                } 
                            }
                            java.util.List<TaggedListmaniaLists> taggedListmaniaListssList = tag.getTaggedListmaniaLists();
                            for (TaggedListmaniaLists taggedListmaniaLists : taggedListmaniaListssList) {
                                System.out.print("                        TaggedListmaniaLists");
                                System.out.println();
                                if (taggedListmaniaLists.isSetList()) {
                                    System.out.print("                            List");
                                    System.out.println();
                                    List  list = taggedListmaniaLists.getList();
                                    if (list.isSetListId()) {
                                        System.out.print("                                ListId");
                                        System.out.println();
                                        System.out.print("                                    " + list.getListId());
                                        System.out.println();
                                    }
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
