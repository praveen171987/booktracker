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
 * Cart Clear  Samples
 *
 *
 */
public class CartClearSample {

    /**
     * Just add few required parameters, and try the service
     * Cart Clear functionality
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
         * sample for Cart Clear Request
         ***********************************************************************/

         CartClearRequest request = new CartClearRequest();
        
         // @TODO: set request parameters here

         // invokeCartClear(service, request);

    }


                                                                                
    /**
     * Cart Clear Action Sample
     * 
     * <br><br>
     * The CartClear operation enables you to remove all of the items in a remote shopping cart, including
     * SavedForLater items. To remove only some of the items in a cart or to reduce the quantity
     * of one or more items, use  CartModify  .
     * <br><br>
     * To delete all of the items from a remote shopping cart, you must specify the cart using the
     * CartId and HMAC values, which are returned by the CartCreate operation. A value similar
     * to the HMAC, URLEncodedHMAC, is also returned. This value is the URL encoded version
     * of the HMAC. This encoding is necessary because some characters, such as + and /,
     * cannot be included in a URL. Rather than encoding the HMAC yourself, use the
     * URLEncodedHMAC value for the HMAC parameter.
     * <br><br>
     * CartClear does not work after the customer has used the PurchaseURL to either purchase the
     * items or merge them with the items in their Amazon cart.
     * <br><br>
     * Carts exist even though they have been emptied. The lifespan of a cart is 7 days since the
     * last time it was acted upon. For example, if a cart created 6 days ago is modified,
     * the cart lifespan is reset to 7 days.
     * <br><br>
     * <b>Available Response Groups</b>:
     * <ul>
     * <li>Request</li>
     * <li>Cart</li>
     * </ul>
     *   
     * @param service instance of AmazonA2S service
     * @param request  CartClear request
     */
    public static void invokeCartClear(AmazonA2S service, CartClearRequest... request) {
        try {

            CartClearResponse response = service.cartClear(request);

            
            System.out.println ("CartClear Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    CartClearResponse");
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
            java.util.List<Cart> cartsList = response.getCart();
            for (Cart cart : cartsList) {
                System.out.print("        Cart");
                System.out.println();
                if (cart.isSetCartId()) {
                    System.out.print("            CartId");
                    System.out.println();
                    System.out.print("                " + cart.getCartId());
                    System.out.println();
                }
                if (cart.isSetHMAC()) {
                    System.out.print("            HMAC");
                    System.out.println();
                    System.out.print("                " + cart.getHMAC());
                    System.out.println();
                }
                if (cart.isSetURLEncodedHMAC()) {
                    System.out.print("            URLEncodedHMAC");
                    System.out.println();
                    System.out.print("                " + cart.getURLEncodedHMAC());
                    System.out.println();
                }
                if (cart.isSetPurchaseURL()) {
                    System.out.print("            PurchaseURL");
                    System.out.println();
                    System.out.print("                " + cart.getPurchaseURL());
                    System.out.println();
                }
                if (cart.isSetSubTotal()) {
                    System.out.print("            SubTotal");
                    System.out.println();
                    Price  subTotal = cart.getSubTotal();
                    if (subTotal.isSetAmount()) {
                        System.out.print("                Amount");
                        System.out.println();
                        System.out.print("                    " + subTotal.getAmount());
                        System.out.println();
                    }
                    if (subTotal.isSetCurrencyCode()) {
                        System.out.print("                CurrencyCode");
                        System.out.println();
                        System.out.print("                    " + subTotal.getCurrencyCode());
                        System.out.println();
                    }
                    if (subTotal.isSetFormattedPrice()) {
                        System.out.print("                FormattedPrice");
                        System.out.println();
                        System.out.print("                    " + subTotal.getFormattedPrice());
                        System.out.println();
                    }
                } 
                if (cart.isSetCartItems()) {
                    System.out.print("            CartItems");
                    System.out.println();
                    CartItems  cartItems = cart.getCartItems();
                    if (cartItems.isSetSubTotal()) {
                        System.out.print("                SubTotal");
                        System.out.println();
                        Price  subTotal = cartItems.getSubTotal();
                        if (subTotal.isSetAmount()) {
                            System.out.print("                    Amount");
                            System.out.println();
                            System.out.print("                        " + subTotal.getAmount());
                            System.out.println();
                        }
                        if (subTotal.isSetCurrencyCode()) {
                            System.out.print("                    CurrencyCode");
                            System.out.println();
                            System.out.print("                        " + subTotal.getCurrencyCode());
                            System.out.println();
                        }
                        if (subTotal.isSetFormattedPrice()) {
                            System.out.print("                    FormattedPrice");
                            System.out.println();
                            System.out.print("                        " + subTotal.getFormattedPrice());
                            System.out.println();
                        }
                    } 
                    java.util.List<CartItem> cartItemsList = cartItems.getCartItem();
                    for (CartItem cartItem : cartItemsList) {
                        System.out.print("                CartItem");
                        System.out.println();
                        if (cartItem.isSetCartItemId()) {
                            System.out.print("                    CartItemId");
                            System.out.println();
                            System.out.print("                        " + cartItem.getCartItemId());
                            System.out.println();
                        }
                        if (cartItem.isSetASIN()) {
                            System.out.print("                    ASIN");
                            System.out.println();
                            System.out.print("                        " + cartItem.getASIN());
                            System.out.println();
                        }
                        if (cartItem.isSetExchangeId()) {
                            System.out.print("                    ExchangeId");
                            System.out.println();
                            System.out.print("                        " + cartItem.getExchangeId());
                            System.out.println();
                        }
                        if (cartItem.isSetMerchantId()) {
                            System.out.print("                    MerchantId");
                            System.out.println();
                            System.out.print("                        " + cartItem.getMerchantId());
                            System.out.println();
                        }
                        if (cartItem.isSetSellerId()) {
                            System.out.print("                    SellerId");
                            System.out.println();
                            System.out.print("                        " + cartItem.getSellerId());
                            System.out.println();
                        }
                        if (cartItem.isSetSellerNickname()) {
                            System.out.print("                    SellerNickname");
                            System.out.println();
                            System.out.print("                        " + cartItem.getSellerNickname());
                            System.out.println();
                        }
                        if (cartItem.isSetQuantity()) {
                            System.out.print("                    Quantity");
                            System.out.println();
                            System.out.print("                        " + cartItem.getQuantity());
                            System.out.println();
                        }
                        if (cartItem.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + cartItem.getTitle());
                            System.out.println();
                        }
                        if (cartItem.isSetProductGroup()) {
                            System.out.print("                    ProductGroup");
                            System.out.println();
                            System.out.print("                        " + cartItem.getProductGroup());
                            System.out.println();
                        }
                        if (cartItem.isSetListOwner()) {
                            System.out.print("                    ListOwner");
                            System.out.println();
                            System.out.print("                        " + cartItem.getListOwner());
                            System.out.println();
                        }
                        if (cartItem.isSetListType()) {
                            System.out.print("                    ListType");
                            System.out.println();
                            System.out.print("                        " + cartItem.getListType());
                            System.out.println();
                        }
                        if (cartItem.isSetMetaData()) {
                            System.out.print("                    MetaData");
                            System.out.println();
                            CartItemMetaData  metaData = cartItem.getMetaData();
                            java.util.List<MetaData> keyValuePairsList = metaData.getKeyValuePair();
                            for (MetaData keyValuePair : keyValuePairsList) {
                                System.out.print("                        KeyValuePair");
                                System.out.println();
                                if (keyValuePair.isSetKey()) {
                                    System.out.print("                            Key");
                                    System.out.println();
                                    System.out.print("                                " + keyValuePair.getKey());
                                    System.out.println();
                                }
                                if (keyValuePair.isSetValue()) {
                                    System.out.print("                            Value");
                                    System.out.println();
                                    System.out.print("                                " + keyValuePair.getValue());
                                    System.out.println();
                                }
                            }
                        } 
                        if (cartItem.isSetPrice()) {
                            System.out.print("                    Price");
                            System.out.println();
                            Price  price = cartItem.getPrice();
                            if (price.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + price.getAmount());
                                System.out.println();
                            }
                            if (price.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + price.getCurrencyCode());
                                System.out.println();
                            }
                            if (price.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + price.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (cartItem.isSetItemTotal()) {
                            System.out.print("                    ItemTotal");
                            System.out.println();
                            Price  itemTotal = cartItem.getItemTotal();
                            if (itemTotal.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + itemTotal.getAmount());
                                System.out.println();
                            }
                            if (itemTotal.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + itemTotal.getCurrencyCode());
                                System.out.println();
                            }
                            if (itemTotal.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + itemTotal.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                    }
                } 
                if (cart.isSetSavedForLaterItems()) {
                    System.out.print("            SavedForLaterItems");
                    System.out.println();
                    SavedForLaterItems  savedForLaterItems = cart.getSavedForLaterItems();
                    if (savedForLaterItems.isSetSubTotal()) {
                        System.out.print("                SubTotal");
                        System.out.println();
                        Price  subTotal = savedForLaterItems.getSubTotal();
                        if (subTotal.isSetAmount()) {
                            System.out.print("                    Amount");
                            System.out.println();
                            System.out.print("                        " + subTotal.getAmount());
                            System.out.println();
                        }
                        if (subTotal.isSetCurrencyCode()) {
                            System.out.print("                    CurrencyCode");
                            System.out.println();
                            System.out.print("                        " + subTotal.getCurrencyCode());
                            System.out.println();
                        }
                        if (subTotal.isSetFormattedPrice()) {
                            System.out.print("                    FormattedPrice");
                            System.out.println();
                            System.out.print("                        " + subTotal.getFormattedPrice());
                            System.out.println();
                        }
                    } 
                    java.util.List<CartItem> savedForLaterItemsList = savedForLaterItems.getSavedForLaterItem();
                    for (CartItem savedForLaterItem : savedForLaterItemsList) {
                        System.out.print("                SavedForLaterItem");
                        System.out.println();
                        if (savedForLaterItem.isSetCartItemId()) {
                            System.out.print("                    CartItemId");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getCartItemId());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetASIN()) {
                            System.out.print("                    ASIN");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getASIN());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetExchangeId()) {
                            System.out.print("                    ExchangeId");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getExchangeId());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetMerchantId()) {
                            System.out.print("                    MerchantId");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getMerchantId());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetSellerId()) {
                            System.out.print("                    SellerId");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getSellerId());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetSellerNickname()) {
                            System.out.print("                    SellerNickname");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getSellerNickname());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetQuantity()) {
                            System.out.print("                    Quantity");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getQuantity());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getTitle());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetProductGroup()) {
                            System.out.print("                    ProductGroup");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getProductGroup());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetListOwner()) {
                            System.out.print("                    ListOwner");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getListOwner());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetListType()) {
                            System.out.print("                    ListType");
                            System.out.println();
                            System.out.print("                        " + savedForLaterItem.getListType());
                            System.out.println();
                        }
                        if (savedForLaterItem.isSetMetaData()) {
                            System.out.print("                    MetaData");
                            System.out.println();
                            CartItemMetaData  metaData = savedForLaterItem.getMetaData();
                            java.util.List<MetaData> keyValuePairsList = metaData.getKeyValuePair();
                            for (MetaData keyValuePair : keyValuePairsList) {
                                System.out.print("                        KeyValuePair");
                                System.out.println();
                                if (keyValuePair.isSetKey()) {
                                    System.out.print("                            Key");
                                    System.out.println();
                                    System.out.print("                                " + keyValuePair.getKey());
                                    System.out.println();
                                }
                                if (keyValuePair.isSetValue()) {
                                    System.out.print("                            Value");
                                    System.out.println();
                                    System.out.print("                                " + keyValuePair.getValue());
                                    System.out.println();
                                }
                            }
                        } 
                        if (savedForLaterItem.isSetPrice()) {
                            System.out.print("                    Price");
                            System.out.println();
                            Price  price = savedForLaterItem.getPrice();
                            if (price.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + price.getAmount());
                                System.out.println();
                            }
                            if (price.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + price.getCurrencyCode());
                                System.out.println();
                            }
                            if (price.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + price.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (savedForLaterItem.isSetItemTotal()) {
                            System.out.print("                    ItemTotal");
                            System.out.println();
                            Price  itemTotal = savedForLaterItem.getItemTotal();
                            if (itemTotal.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + itemTotal.getAmount());
                                System.out.println();
                            }
                            if (itemTotal.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + itemTotal.getCurrencyCode());
                                System.out.println();
                            }
                            if (itemTotal.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + itemTotal.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                    }
                } 
                if (cart.isSetSimilarProducts()) {
                    System.out.print("            SimilarProducts");
                    System.out.println();
                    SimilarProducts  similarProducts = cart.getSimilarProducts();
                    java.util.List<SimilarProduct> similarProductsList = similarProducts.getSimilarProduct();
                    for (SimilarProduct similarProduct : similarProductsList) {
                        System.out.print("                SimilarProduct");
                        System.out.println();
                        if (similarProduct.isSetASIN()) {
                            System.out.print("                    ASIN");
                            System.out.println();
                            System.out.print("                        " + similarProduct.getASIN());
                            System.out.println();
                        }
                        if (similarProduct.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + similarProduct.getTitle());
                            System.out.println();
                        }
                    }
                } 
                if (cart.isSetTopSellers()) {
                    System.out.print("            TopSellers");
                    System.out.println();
                    TopSellers  topSellers = cart.getTopSellers();
                    java.util.List<TopSeller> topSellersList = topSellers.getTopSeller();
                    for (TopSeller topSeller : topSellersList) {
                        System.out.print("                TopSeller");
                        System.out.println();
                        if (topSeller.isSetASIN()) {
                            System.out.print("                    ASIN");
                            System.out.println();
                            System.out.print("                        " + topSeller.getASIN());
                            System.out.println();
                        }
                        if (topSeller.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + topSeller.getTitle());
                            System.out.println();
                        }
                    }
                } 
                if (cart.isSetNewReleases()) {
                    System.out.print("            NewReleases");
                    System.out.println();
                    NewReleases  newReleases = cart.getNewReleases();
                    java.util.List<NewRelease> newReleasesList = newReleases.getNewRelease();
                    for (NewRelease newRelease : newReleasesList) {
                        System.out.print("                NewRelease");
                        System.out.println();
                        if (newRelease.isSetASIN()) {
                            System.out.print("                    ASIN");
                            System.out.println();
                            System.out.print("                        " + newRelease.getASIN());
                            System.out.println();
                        }
                        if (newRelease.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + newRelease.getTitle());
                            System.out.println();
                        }
                    }
                } 
                if (cart.isSetSimilarViewedProducts()) {
                    System.out.print("            SimilarViewedProducts");
                    System.out.println();
                    SimilarViewedProducts  similarViewedProducts = cart.getSimilarViewedProducts();
                    java.util.List<SimilarViewedProduct> similarViewedProductsList = similarViewedProducts.getSimilarViewedProduct();
                    for (SimilarViewedProduct similarViewedProduct : similarViewedProductsList) {
                        System.out.print("                SimilarViewedProduct");
                        System.out.println();
                        if (similarViewedProduct.isSetASIN()) {
                            System.out.print("                    ASIN");
                            System.out.println();
                            System.out.print("                        " + similarViewedProduct.getASIN());
                            System.out.println();
                        }
                        if (similarViewedProduct.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + similarViewedProduct.getTitle());
                            System.out.println();
                        }
                    }
                } 
                if (cart.isSetOtherCategoriesSimilarProducts()) {
                    System.out.print("            OtherCategoriesSimilarProducts");
                    System.out.println();
                    OtherCategoriesSimilarProducts  otherCategoriesSimilarProducts = cart.getOtherCategoriesSimilarProducts();
                    java.util.List<OtherCategoriesSimilarProduct> otherCategoriesSimilarProductsList = otherCategoriesSimilarProducts.getOtherCategoriesSimilarProduct();
                    for (OtherCategoriesSimilarProduct otherCategoriesSimilarProduct : otherCategoriesSimilarProductsList) {
                        System.out.print("                OtherCategoriesSimilarProduct");
                        System.out.println();
                        if (otherCategoriesSimilarProduct.isSetASIN()) {
                            System.out.print("                    ASIN");
                            System.out.println();
                            System.out.print("                        " + otherCategoriesSimilarProduct.getASIN());
                            System.out.println();
                        }
                        if (otherCategoriesSimilarProduct.isSetTitle()) {
                            System.out.print("                    Title");
                            System.out.println();
                            System.out.print("                        " + otherCategoriesSimilarProduct.getTitle());
                            System.out.println();
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
