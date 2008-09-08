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
 * Help  Samples
 *
 *
 */
public class HelpSample {

    /**
     * Just add few required parameters, and try the service
     * Help functionality
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
         * sample for Help Request
         ***********************************************************************/

         HelpRequest request = new HelpRequest();
        
         // @TODO: set request parameters here

         // invokeHelp(service, request);

    }


                        
    /**
     * Help Action Sample
     * 
     * <br><br>
     * The Help operation provides information about A2S operations and
     * response groups. For operations, Help lists required and optional
     * request parameters, as well as default and optional response groups the
     * operation can use. For response groups, Help lists the operations that can
     * use the response group as well as the response tags returned by the
     * response group in the XML response.
     * <br><br>
     * The Help operation is not often used in customer applications. It can, however, be
     * used to help the developer in the following ways:
     * <br><br>
     * <ul>
     * <li>Provide contextual help in an interactive development environment (IDE) for developerst</li>
     * <li>Automate documentation creation as part of a developer's toolkit. </li>
     * </ul>
     * <br><br>
     * <b>Available Response Groups</b>:
     * <ul>
     * <li>Request</li>
     * <li>Help</li>
     * </ul>
     *   
     * @param service instance of AmazonA2S service
     * @param request  Help request
     */
    public static void invokeHelp(AmazonA2S service, HelpRequest... request) {
        try {

            HelpResponse response = service.help(request);

            
            System.out.println ("Help Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    HelpResponse");
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
            java.util.List<Information> informationsList = response.getInformation();
            for (Information information : informationsList) {
                System.out.print("        Information");
                System.out.println();
                java.util.List<OperationInformation> operationInformationsList = information.getOperationInformation();
                for (OperationInformation operationInformation : operationInformationsList) {
                    System.out.print("            OperationInformation");
                    System.out.println();
                    if (operationInformation.isSetName()) {
                        System.out.print("                Name");
                        System.out.println();
                        System.out.print("                    " + operationInformation.getName());
                        System.out.println();
                    }
                    if (operationInformation.isSetDescription()) {
                        System.out.print("                Description");
                        System.out.println();
                        System.out.print("                    " + operationInformation.getDescription());
                        System.out.println();
                    }
                    if (operationInformation.isSetRequiredParameters()) {
                        System.out.print("                RequiredParameters");
                        System.out.println();
                        RequiredParameters  requiredParameters = operationInformation.getRequiredParameters();
                        java.util.List<String> parametersList  =  requiredParameters.getParameter();
                        for (String parameter : parametersList) { 
                            System.out.print("                    Parameter");
                                System.out.println();
                            System.out.print("                        " + parameter);
                        }	
                    } 
                    if (operationInformation.isSetAvailableParameters()) {
                        System.out.print("                AvailableParameters");
                        System.out.println();
                        AvailableParameters  availableParameters = operationInformation.getAvailableParameters();
                        java.util.List<String> parametersList  =  availableParameters.getParameter();
                        for (String parameter : parametersList) { 
                            System.out.print("                    Parameter");
                                System.out.println();
                            System.out.print("                        " + parameter);
                        }	
                    } 
                    if (operationInformation.isSetDefaultResponseGroups()) {
                        System.out.print("                DefaultResponseGroups");
                        System.out.println();
                        DefaultResponseGroups  defaultResponseGroups = operationInformation.getDefaultResponseGroups();
                        java.util.List<String> responseGroupsList  =  defaultResponseGroups.getResponseGroup();
                        for (String responseGroup : responseGroupsList) { 
                            System.out.print("                    ResponseGroup");
                                System.out.println();
                            System.out.print("                        " + responseGroup);
                        }	
                    } 
                    if (operationInformation.isSetAvailableResponseGroups()) {
                        System.out.print("                AvailableResponseGroups");
                        System.out.println();
                        AvailableResponseGroups  availableResponseGroups = operationInformation.getAvailableResponseGroups();
                        java.util.List<String> responseGroupsList  =  availableResponseGroups.getResponseGroup();
                        for (String responseGroup : responseGroupsList) { 
                            System.out.print("                    ResponseGroup");
                                System.out.println();
                            System.out.print("                        " + responseGroup);
                        }	
                    } 
                }
                java.util.List<ResponseGroupInformation> responseGroupInformationsList = information.getResponseGroupInformation();
                for (ResponseGroupInformation responseGroupInformation : responseGroupInformationsList) {
                    System.out.print("            ResponseGroupInformation");
                    System.out.println();
                    if (responseGroupInformation.isSetName()) {
                        System.out.print("                Name");
                        System.out.println();
                        System.out.print("                    " + responseGroupInformation.getName());
                        System.out.println();
                    }
                    if (responseGroupInformation.isSetCreationDate()) {
                        System.out.print("                CreationDate");
                        System.out.println();
                        System.out.print("                    " + responseGroupInformation.getCreationDate());
                        System.out.println();
                    }
                    if (responseGroupInformation.isSetValidOperations()) {
                        System.out.print("                ValidOperations");
                        System.out.println();
                        ResponseGroupValidOperations  validOperations = responseGroupInformation.getValidOperations();
                        java.util.List<String> operationsList  =  validOperations.getOperation();
                        for (String operation : operationsList) { 
                            System.out.print("                    Operation");
                                System.out.println();
                            System.out.print("                        " + operation);
                        }	
                    } 
                    if (responseGroupInformation.isSetElements()) {
                        System.out.print("                Elements");
                        System.out.println();
                        ResponseGroupElements  elements = responseGroupInformation.getElements();
                        java.util.List<String> elementsList  =  elements.getElement();
                        for (String element : elementsList) { 
                            System.out.print("                    Element");
                                System.out.println();
                            System.out.print("                        " + element);
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
