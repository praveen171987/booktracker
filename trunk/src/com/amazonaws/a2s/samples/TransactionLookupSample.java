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
 * Transaction Lookup  Samples
 *
 *
 */
public class TransactionLookupSample {

    /**
     * Just add few required parameters, and try the service
     * Transaction Lookup functionality
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
         * sample for Transaction Lookup Request
         ***********************************************************************/

         TransactionLookupRequest request = new TransactionLookupRequest();
        
         // @TODO: set request parameters here

         // invokeTransactionLookup(service, request);

    }


                                                                                    
    /**
     * Transaction Lookup Action Sample
     * 
     * <br><br>
     * The TransactionLookup operation returns information about up to ten purchases
     * that have already taken place. Transaction IDs are created whenever a purchase
     * request is made by a customer.
     * <br><br>
     * For a specified transaction ID, TransactionLookup returns:
     * <ul>
     * <li>Price details</li>
     * <li>Sale date</li>
     * <li>Shipping details</li>
     * <li>Seller details</li>
     * <li>Item's condition </li>
     * </ul>
     * <br><br>
     * For privacy reasons, this operation does not return information about the customer
     * who purchased the items.
     * <br><br>
     * <b>Available Response Groups</b>:
     * <ul>
     * <li>Request</li>
     * <li>TransactionDetails</li>
     * </ul>
     *   
     * @param service instance of AmazonA2S service
     * @param request  TransactionLookup request
     */
    public static void invokeTransactionLookup(AmazonA2S service, TransactionLookupRequest... request) {
        try {

            TransactionLookupResponse response = service.transactionLookup(request);

            
            System.out.println ("TransactionLookup Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    TransactionLookupResponse");
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
            java.util.List<Transactions> transactionssList = response.getTransactions();
            for (Transactions transactions : transactionssList) {
                System.out.print("        Transactions");
                System.out.println();
                if (transactions.isSetTotalResults()) {
                    System.out.print("            TotalResults");
                    System.out.println();
                    System.out.print("                " + transactions.getTotalResults());
                    System.out.println();
                }
                if (transactions.isSetTotalPages()) {
                    System.out.print("            TotalPages");
                    System.out.println();
                    System.out.print("                " + transactions.getTotalPages());
                    System.out.println();
                }
                java.util.List<Transaction> transactionsList = transactions.getTransaction();
                for (Transaction transaction : transactionsList) {
                    System.out.print("            Transaction");
                    System.out.println();
                    if (transaction.isSetTransactionId()) {
                        System.out.print("                TransactionId");
                        System.out.println();
                        System.out.print("                    " + transaction.getTransactionId());
                        System.out.println();
                    }
                    if (transaction.isSetSellerId()) {
                        System.out.print("                SellerId");
                        System.out.println();
                        System.out.print("                    " + transaction.getSellerId());
                        System.out.println();
                    }
                    if (transaction.isSetCondition()) {
                        System.out.print("                Condition");
                        System.out.println();
                        System.out.print("                    " + transaction.getCondition());
                        System.out.println();
                    }
                    if (transaction.isSetTransactionDate()) {
                        System.out.print("                TransactionDate");
                        System.out.println();
                        System.out.print("                    " + transaction.getTransactionDate());
                        System.out.println();
                    }
                    if (transaction.isSetTransactionDateEpoch()) {
                        System.out.print("                TransactionDateEpoch");
                        System.out.println();
                        System.out.print("                    " + transaction.getTransactionDateEpoch());
                        System.out.println();
                    }
                    if (transaction.isSetSellerName()) {
                        System.out.print("                SellerName");
                        System.out.println();
                        System.out.print("                    " + transaction.getSellerName());
                        System.out.println();
                    }
                    if (transaction.isSetPayingCustomerId()) {
                        System.out.print("                PayingCustomerId");
                        System.out.println();
                        System.out.print("                    " + transaction.getPayingCustomerId());
                        System.out.println();
                    }
                    if (transaction.isSetOrderingCustomerId()) {
                        System.out.print("                OrderingCustomerId");
                        System.out.println();
                        System.out.print("                    " + transaction.getOrderingCustomerId());
                        System.out.println();
                    }
                    if (transaction.isSetTotals()) {
                        System.out.print("                Totals");
                        System.out.println();
                        TransactionTotals  totals = transaction.getTotals();
                        if (totals.isSetTotal()) {
                            System.out.print("                    Total");
                            System.out.println();
                            Price  total = totals.getTotal();
                            if (total.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + total.getAmount());
                                System.out.println();
                            }
                            if (total.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + total.getCurrencyCode());
                                System.out.println();
                            }
                            if (total.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + total.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (totals.isSetSubtotal()) {
                            System.out.print("                    Subtotal");
                            System.out.println();
                            Price  subtotal = totals.getSubtotal();
                            if (subtotal.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + subtotal.getAmount());
                                System.out.println();
                            }
                            if (subtotal.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + subtotal.getCurrencyCode());
                                System.out.println();
                            }
                            if (subtotal.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + subtotal.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (totals.isSetTax()) {
                            System.out.print("                    Tax");
                            System.out.println();
                            Price  tax = totals.getTax();
                            if (tax.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + tax.getAmount());
                                System.out.println();
                            }
                            if (tax.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + tax.getCurrencyCode());
                                System.out.println();
                            }
                            if (tax.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + tax.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (totals.isSetShippingCharge()) {
                            System.out.print("                    ShippingCharge");
                            System.out.println();
                            Price  shippingCharge = totals.getShippingCharge();
                            if (shippingCharge.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + shippingCharge.getAmount());
                                System.out.println();
                            }
                            if (shippingCharge.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + shippingCharge.getCurrencyCode());
                                System.out.println();
                            }
                            if (shippingCharge.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + shippingCharge.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                        if (totals.isSetPromotion()) {
                            System.out.print("                    Promotion");
                            System.out.println();
                            Price  promotion = totals.getPromotion();
                            if (promotion.isSetAmount()) {
                                System.out.print("                        Amount");
                                System.out.println();
                                System.out.print("                            " + promotion.getAmount());
                                System.out.println();
                            }
                            if (promotion.isSetCurrencyCode()) {
                                System.out.print("                        CurrencyCode");
                                System.out.println();
                                System.out.print("                            " + promotion.getCurrencyCode());
                                System.out.println();
                            }
                            if (promotion.isSetFormattedPrice()) {
                                System.out.print("                        FormattedPrice");
                                System.out.println();
                                System.out.print("                            " + promotion.getFormattedPrice());
                                System.out.println();
                            }
                        } 
                    } 
                    if (transaction.isSetTransactionItems()) {
                        System.out.print("                TransactionItems");
                        System.out.println();
                        TransactionItems  transactionItems = transaction.getTransactionItems();
                        java.util.List<TransactionItem> transactionItemsList = transactionItems.getTransactionItem();
                        for (TransactionItem transactionItem : transactionItemsList) {
                            System.out.print("                    TransactionItem");
                            System.out.println();
                            if (transactionItem.isSetTransactionItemId()) {
                                System.out.print("                        TransactionItemId");
                                System.out.println();
                                System.out.print("                            " + transactionItem.getTransactionItemId());
                                System.out.println();
                            }
                            if (transactionItem.isSetQuantity()) {
                                System.out.print("                        Quantity");
                                System.out.println();
                                System.out.print("                            " + transactionItem.getQuantity());
                                System.out.println();
                            }
                            if (transactionItem.isSetUnitPrice()) {
                                System.out.print("                        UnitPrice");
                                System.out.println();
                                Price  unitPrice = transactionItem.getUnitPrice();
                                if (unitPrice.isSetAmount()) {
                                    System.out.print("                            Amount");
                                    System.out.println();
                                    System.out.print("                                " + unitPrice.getAmount());
                                    System.out.println();
                                }
                                if (unitPrice.isSetCurrencyCode()) {
                                    System.out.print("                            CurrencyCode");
                                    System.out.println();
                                    System.out.print("                                " + unitPrice.getCurrencyCode());
                                    System.out.println();
                                }
                                if (unitPrice.isSetFormattedPrice()) {
                                    System.out.print("                            FormattedPrice");
                                    System.out.println();
                                    System.out.print("                                " + unitPrice.getFormattedPrice());
                                    System.out.println();
                                }
                            } 
                            if (transactionItem.isSetTotalPrice()) {
                                System.out.print("                        TotalPrice");
                                System.out.println();
                                Price  totalPrice = transactionItem.getTotalPrice();
                                if (totalPrice.isSetAmount()) {
                                    System.out.print("                            Amount");
                                    System.out.println();
                                    System.out.print("                                " + totalPrice.getAmount());
                                    System.out.println();
                                }
                                if (totalPrice.isSetCurrencyCode()) {
                                    System.out.print("                            CurrencyCode");
                                    System.out.println();
                                    System.out.print("                                " + totalPrice.getCurrencyCode());
                                    System.out.println();
                                }
                                if (totalPrice.isSetFormattedPrice()) {
                                    System.out.print("                            FormattedPrice");
                                    System.out.println();
                                    System.out.print("                                " + totalPrice.getFormattedPrice());
                                    System.out.println();
                                }
                            } 
                            if (transactionItem.isSetASIN()) {
                                System.out.print("                        ASIN");
                                System.out.println();
                                System.out.print("                            " + transactionItem.getASIN());
                                System.out.println();
                            }
                            if (transactionItem.isSetChildTransactionItems()) {
                                System.out.print("                        ChildTransactionItems");
                                System.out.println();
                                ChildTransactionItems  childTransactionItems = transactionItem.getChildTransactionItems();
                            } 
                        }
                    } 
                    if (transaction.isSetShipments()) {
                        System.out.print("                Shipments");
                        System.out.println();
                        TransactionShipments  shipments = transaction.getShipments();
                        java.util.List<Shipment> shipmentsList = shipments.getShipment();
                        for (Shipment shipment : shipmentsList) {
                            System.out.print("                    Shipment");
                            System.out.println();
                            if (shipment.isSetCondition()) {
                                System.out.print("                        Condition");
                                System.out.println();
                                System.out.print("                            " + shipment.getCondition());
                                System.out.println();
                            }
                            if (shipment.isSetDeliveryMethod()) {
                                System.out.print("                        DeliveryMethod");
                                System.out.println();
                                System.out.print("                            " + shipment.getDeliveryMethod());
                                System.out.println();
                            }
                            if (shipment.isSetShipmentItems()) {
                                System.out.print("                        ShipmentItems");
                                System.out.println();
                                ShipmentItems  shipmentItems = shipment.getShipmentItems();
                                java.util.List<String> transactionItemIdsList  =  shipmentItems.getTransactionItemId();
                                for (String transactionItemId : transactionItemIdsList) { 
                                    System.out.print("                            TransactionItemId");
                                        System.out.println();
                                    System.out.print("                                " + transactionItemId);
                                }	
                            } 
                            if (shipment.isSetPackages()) {
                                System.out.print("                        Packages");
                                System.out.println();
                                ShipmentPackages  packages = shipment.getPackages();
                                java.util.List<ShipmentPackage> pkgsList = packages.getPackage();
                                for (ShipmentPackage pkg : pkgsList) {
                                    System.out.print("                            Package");
                                    System.out.println();
                                    if (pkg.isSetTrackingNumber()) {
                                        System.out.print("                                TrackingNumber");
                                        System.out.println();
                                        System.out.print("                                    " + pkg.getTrackingNumber());
                                        System.out.println();
                                    }
                                    if (pkg.isSetCarrierName()) {
                                        System.out.print("                                CarrierName");
                                        System.out.println();
                                        System.out.print("                                    " + pkg.getCarrierName());
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
