package com.sirsidynix.horizon.library;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.softwaresmithy.httpclient.HttpClientTool;
import com.softwaresmithy.xpath.util.XPathUtil;

/**
 * 
 * @author Jesse Hess
 * TODO: externalize XPath strings
 */
public class HorizonTools {
	
	public static HttpClientTool httpClientTool;
	
	public HorizonTools() {
		httpClientTool = new HttpClientTool();
	}
	
	/**
	 * for using via a proxy
	 * @param d
	 * @param port
	 */
	public HorizonTools(String proxyHostName, int proxyPort) {
		httpClientTool = new HttpClientTool(proxyHostName, proxyPort);
	}

	/**
	 * 
	 * @param url the HTTP URL to the Horizon catalog
	 * @param isbnIndex the code used to 
	 * @param isbn
	 */
	public String searchIsbnForStatus(String url, String isbnIndex, String isbn) {
		// find the "index" keyword for the library, if not provided
		if (isEmpty(isbnIndex)) {
			isbnIndex = findIsbnSearchIndex(url);
		}
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		// index, code that determines the search type that will be executed
		parameters.add(new BasicNameValuePair("index", isbnIndex));
		// term, the search value
		parameters.add(new BasicNameValuePair("term", isbn));
		// items per page, effects the number of searchresults/results/row items shown on a page, set to a perceived large enough number that all entries will be on a single page
		parameters.add(new BasicNameValuePair("ipp", "100"));
		// GetXML, the key to getting valid XML in the response instead of HTML
		parameters.add(new BasicNameValuePair("GetXML", "true"));
				
		String xmlPost = httpClientTool.httpPost(url, parameters);
		//System.out.println("response xml=" + xmlPost.trim()); // for debugging
		Element element = XPathUtil.getElement(xmlPost);
		
		// TODO: handle case when more than 1 search result happens
		NodeList nodeList = XPathUtil.getNodeListFromXPath(element,"/searchresponse/items/searchresults/results/row");
		String requestsColumn = XPathUtil.getStringFromXPath(element, "count(/searchresponse/fullnonmarc/searchresults/header/col[label='Requests']/preceding-sibling::*)");
		String waitStatus = "ERROR";
		int requests = 0;
		int copies = nodeList.getLength();
		try {
			int columnNum = Integer.parseInt(requestsColumn);
			// XPath is 1 based, and if it returns a 0, then it did not find the correct item
			if (columnNum > 0) {
				columnNum++;
				String requestsStr = XPathUtil.getStringFromXPath(element, "/searchresponse/fullnonmarc/searchresults/results/row/cell["+columnNum+"]/data/text");
				requests = Integer.parseInt(requestsStr);
			}
		} catch (NumberFormatException e) {
			// either the 'columnNum' or the 'requests' had invalid numbers, so move on with the default value
		}
		if (requests == 0 && copies == 0) {
			waitStatus = "NO_COPIES"; // an entry exists, but no copies exist
		} else if (requests <= 0 && copies > 0) {
			waitStatus = "AVAILABLE";
		} else if (requests > 0 && requests < copies) {
			waitStatus = "SHORT_WAIT";
		} else if (requests > 0 && copies <= requests && requests <= 2*copies) {
			waitStatus = "WAIT";
		} else if (requests >0 && requests > 2*copies) {
			waitStatus = "LONG_WAIT";
		}

		NodeList errorList = XPathUtil.getNodeListFromXPath(element, "//error");
		for (int i = 0; i < errorList.getLength(); i++) {
			Node error = errorList.item(i);
			String errorType = XPathUtil.getStringFromXPath(error,"type");
			String errorSubject = XPathUtil.getStringFromXPath(error,"subject");
			String errorReason = XPathUtil.getStringFromXPath(error,"reason");
			String errorMessage = XPathUtil.getStringFromXPath(error,"message");			

			StringBuilder b = new StringBuilder().append(errorType).append(errorSubject).append(errorReason).append(errorMessage);
			if (!isEmpty(b.toString())) {
				waitStatus = "NO_MATCH";
				//System.out.println(b.toString()); // for debugging
			}
		}
		//System.out.println("requests="+requests+" copies="+copies+" wait status: "+waitStatus); // for debugging
		return waitStatus;
	}
	
	/**
	 *  
	 * @param url
	 * @return
	 */
	private String findIsbnSearchIndex(String url) {
		// get the search menu
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("auth", "true"));
		parameters.add(new BasicNameValuePair("menu", "search"));
		parameters.add(new BasicNameValuePair("GetXML", "true"));
		String xmlGet = httpClientTool.httpGet(url, parameters);
		Element element = XPathUtil.getElement(xmlGet);
		// look for ISBN search index in default tab
		String shortcutXPath = "/searchresponse/aspectlist/aspectentry[contains(aspectlabel,'ISBN')]/shortcut";
		String shortcut = XPathUtil.getStringFromXPath(element, shortcutXPath);
		if (isEmpty(shortcut)) {
			// if did not exist in default tab, look in other subtabs, get the list of non-active subtabs
			NodeList nodeList = XPathUtil.getNodeListFromXPath(element, "/searchresponse/toolbar/submenu");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String submenu = XPathUtil.getStringFromXPath(node,"value");
				String encodedSubmenu = submenu; // if it fails to encode, then use original
				try {
					encodedSubmenu = URLEncoder.encode(submenu, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// ignore
				}
				parameters = new ArrayList<NameValuePair>();
				parameters.add(new BasicNameValuePair("auth", "true"));
				parameters.add(new BasicNameValuePair("menu", "search"));
				parameters.add(new BasicNameValuePair("submenu", encodedSubmenu));
				parameters.add(new BasicNameValuePair("GetXML", "true"));
				xmlGet = httpClientTool.httpGet(url, parameters);
				try {
					element = XPathUtil.getElement(xmlGet);
					shortcut = XPathUtil.getStringFromXPath(element, shortcutXPath);					
				} catch(Exception e) {
					// System.out.println("Error occurred for submenu="+submenu);
				}
				if (!isEmpty(shortcut)) {
					break;
				}
			}
		}
		// System.out.println("shortcut="+shortcut); // for debugging
		return shortcut;
	}

	/**
	 * Check if a String is null or empty
	 * @param s
	 * @return
	 */
	private boolean isEmpty(String s) {
		boolean value = false;
		if (s == null || "".equals(s)) {
			value = true;
		}
		return value;
	}

	/*****************************************************************************
	 * Future functionality, needs more testing
	 * ***************************************************************************/
	
	/**
	 * NOTE: not fit for consumption yet
	 * TODO: this method may be obsolete if the changes to requestLogin works
	 */
	public void gatherLibraryAccountInfo(String url, String username, String password) {
		String sessionId = requestSessionId(url, username, password);

		boolean loggedIn = requestLogin(url, username, password, sessionId);
		
		if (loggedIn) {
			gatherAccountInfo(url, sessionId);
		}
	}

	/**
	 * NOTE: not fit for consumption yet
	 * @param url
	 * @param sessionId
	 */
	public void gatherAccountInfo(String url, String sessionId) {
		//String[] parameters = new String[] {"session="+account.getSessionId(), "menu=account", "submenu=itemsout", "GetXML=true"};
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("session", sessionId));
		parameters.add(new BasicNameValuePair("menu", "account"));
		parameters.add(new BasicNameValuePair("submenu", "itemsout"));
		parameters.add(new BasicNameValuePair("GetXML", "true"));
		// itemsout
		String xmlGet = httpClientTool.httpGet(url, parameters);
		System.out.println("response: xml="+xmlGet.trim());
		Element element = XPathUtil.getElement(xmlGet);
		showInfo(element);
		showGroupPools(element);
		showSecurity(element);
		showItemsOut(element);
		showError(element);
		// info
		parameters.set(2, new BasicNameValuePair("submenu", "info"));
		xmlGet = httpClientTool.httpGet(url, parameters);
		System.out.println("response: xml="+xmlGet.trim());
		element = XPathUtil.getElement(xmlGet);
		showInfo(element);
		showGroupPools(element);
		showSecurity(element);
		showPatronInfo(element);
		showError(element);
		// blocks
		parameters.set(2, new BasicNameValuePair("submenu", "blocks"));
		xmlGet = httpClientTool.httpGet(url, parameters);
		System.out.println("response: xml="+xmlGet.trim());
		element = XPathUtil.getElement(xmlGet);
		showInfo(element);
		showGroupPools(element);
		showSecurity(element);
		showBlocks(element);
		showError(element);
		// holds
		parameters.set(2, new BasicNameValuePair("submenu", "holds"));
		xmlGet = httpClientTool.httpGet(url, parameters);
		System.out.println("response: xml="+xmlGet.trim());
		element = XPathUtil.getElement(xmlGet);
		showInfo(element);
		showGroupPools(element);
		showSecurity(element);
		showHolds(element);
		showError(element);		
	}

	/**
	 * NOTE: not fit for consumption yet
	 * The key to getting a sessionId that will allow logging in is the 'auth' parameter
	 */
	public String requestSessionId(String url, String username, String password) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("auth", "true"));
		parameters.add(new BasicNameValuePair("GetXML", "true"));
		String xmlGet = httpClientTool.httpGet(url, parameters);
		System.out.println("response: xml="+xmlGet.trim());
		Element element = XPathUtil.getElement(xmlGet);
		String sessionId = showInfo(element);
		showGroupPools(element);
		showSecurity(element);
		showError(element);
		return sessionId;
	}
	
	/**
	 * NOTE: not fit for consumption yet
	 * TODO: check if adding parameter auth=true to login request makes passing the sessionId not required
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean requestLogin(String url, String username, String password, String sessionId) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("sec1", username));
		parameters.add(new BasicNameValuePair("sec2", password));
		parameters.add(new BasicNameValuePair("session", sessionId));
		parameters.add(new BasicNameValuePair("menu", "account"));
		parameters.add(new BasicNameValuePair("submenu", "overview"));
		parameters.add(new BasicNameValuePair("GetXML", "true"));

		String xmlPost = httpClientTool.httpPost(url, parameters);
		Element element = XPathUtil.getElement(xmlPost);
		showInfo(element);
		boolean loggedIn = showSecurity(element);
		showGroupPools(element);
		showOverview(element);
		showError(element);

		System.out.println("response xml=" + xmlPost);
		return loggedIn;
	}
	
	/**
	 * NOTE: not fit for consumption yet
	 * @param element
	 */
	public String showInfo(Element element) {
		String sessionId = XPathUtil.getStringFromXPath(element, "/searchresponse/session | /patronpersonalresponse/session");
		String version = XPathUtil.getStringFromXPath(element, "/searchresponse/version | /patronpersonalresponse/version");
		String lang = XPathUtil.getStringFromXPath(element, "/searchresponse/lang | /patronpersonalresponse/lang");
		String expirepage = XPathUtil.getStringFromXPath(element, "/searchresponse/expirepage | /patronpersonalresponse/expirepage");
		String profile = XPathUtil.getStringFromXPath(element, "/searchresponse/profile | /patronpersonalresponse/profile");

		System.out.println("response: sessionId=" + sessionId);
		System.out.println("response: version=" + version);
		System.out.println("response: lang=" + lang);
		System.out.println("response: expirepage=" + expirepage);
		System.out.println("response: profile=" + profile);
		return sessionId;
	}

	/**
	 * NOTE: not fit for consumption yet 
	 * @param element
	 */
	public void showGroupPools(Element element) {
		// TODO: possible multiple datapool entries
		// TODO: don't show if the pool does not exist
		String dataPoolEmailReplyTo = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/emailreplyto");
		String dataPoolEmailPostmaster = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/emailpostmaster");
		String dataPoolSourceLabel = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/source/sourcelabel");
		String dataPoolSourceId = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/source/id");
		String dataPoolSourceType = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/source/type");
		String dataPoolSourceRequestType = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/source/requesttype");
		String dataPoolSourceLoginRequired = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/source/loginrequired");
		String dataPoolSourceMaxHitLimit = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/source/maxhitlimit");
		String dataPoolSourceSortViewNum = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/source/sortviewnum");
		String dataPoolSourceNum = XPathUtil.getStringFromXPath(element, "//group/pools/datapool/source/sourcenum");

		System.out.println("response: dataPoolEmailReplyTo="+ dataPoolEmailReplyTo);
		System.out.println("response: dataPoolEmailPostmaster="	+ dataPoolEmailPostmaster);
		System.out.println("response: dataPoolSourceLabel="	+ dataPoolSourceLabel);
		System.out.println("response: dataPoolSourceId=" + dataPoolSourceId);
		System.out.println("response: dataPoolSourceType=" + dataPoolSourceType);
		System.out.println("response: dataPoolSourceRequestType="+ dataPoolSourceRequestType);
		System.out.println("response: dataPoolSourceLoginRequired="	+ dataPoolSourceLoginRequired);
		System.out.println("response: dataPoolSourceMaxHitLimit="+ dataPoolSourceMaxHitLimit);
		System.out.println("response: dataPoolSourceSortViewNum="+ dataPoolSourceSortViewNum);
		System.out.println("response: dataPoolSourceNum=" + dataPoolSourceNum);
	}

	/**
	 * NOTE: not fit for consumption yet
	 * @param element
	 * @return
	 */
	public boolean showSecurity(Element element) {
		String securityAuth = XPathUtil.getStringFromXPath(element, "/searchresponse/security/auth | /patronpersonalresponse/security/auth");
		String securityName = XPathUtil.getStringFromXPath(element, "/searchresponse/security/name | /patronpersonalresponse/security/name");
		String securityPatronId = XPathUtil.getStringFromXPath(element, "/searchresponse/security/patronid | /patronpersonalresponse/security/patronid");
		String securityAddress = XPathUtil.getStringFromXPath(element, "/searchresponse/security/address | /patronpersonalresponse/security/address");
		String securityEmailAddress = XPathUtil.getStringFromXPath(element, "/searchresponse/security/emailaddress | /patronpersonalresponse/security/emailaddress");
		String securityPhone = XPathUtil.getStringFromXPath(element, "/searchresponse/security/phone | /patronpersonalresponse/security/phone");
		String securityMaxBibs = XPathUtil.getStringFromXPath(element, "/searchresponse/security/maxbibs | /patronpersonalresponse/security/maxbibs");

		System.out.println("response: securityAuth=" + securityAuth);
		if (securityAuth.equals("true")) {
			System.out.println("response: securityName=" + securityName);
			System.out.println("response: securityPatronId=" + securityPatronId);
			System.out.println("response: securityAddress=" + securityAddress);
			System.out.println("response: securityEmailAddress=" + securityEmailAddress);
			System.out.println("response: securityPhone=" + securityPhone);
			System.out.println("response: securityMaxBibs=" + securityMaxBibs);
		}
		return "true".equals(securityAuth) ? true : false;
	}
	
	/**
	 * NOTE: not fit for consumption yet
	 * @param element
	 */
	public void showError(Element element) {
		NodeList errorList = XPathUtil.getNodeListFromXPath(element, "//error");
		for (int i = 0; i < errorList.getLength(); i++) {
			Node error = errorList.item(i);
			String errorType = XPathUtil.getStringFromXPath(error,"type");
			String errorSubject = XPathUtil.getStringFromXPath(error,"subject");
			String errorReason = XPathUtil.getStringFromXPath(error,"reason");
			String errorMessage = XPathUtil.getStringFromXPath(error,"message");			

			if (!isEmpty(errorType + errorSubject + errorReason + errorMessage)) {
				System.out.println("response: errorType=" + errorType);
				System.out.println("response: errorSubject=" + errorSubject);
				System.out.println("response: errorReason=" + errorReason);
				System.out.println("response: errorMessage=" + errorMessage);
			}
		}
	}

	/**
	 * NOTE: not fit for consumption yet
	 * @param element
	 */
	public void showItemsOut(Element element) {
		String itemsOutTotalCount = XPathUtil.getStringFromXPath(element,	"/patronpersonalresponse/itemsoutdata/totalcount");
		String itemsOutTotalOverdue = XPathUtil.getStringFromXPath(element, "/patronpersonalresponse/itemsoutdata/totaloverdue");
		String itemsOutTotalLost = XPathUtil.getStringFromXPath(element, "/patronpersonalresponse/itemsoutdata/totallost");
		String itemsOutRenewalAllowed = XPathUtil.getStringFromXPath(element, "/patronpersonalresponse/itemsoutdata/renewalsallowed");

		System.out.println("response: itemsOutTotalCount="+ itemsOutTotalCount);
		System.out.println("response: itemsOutTotalOverdue="+ itemsOutTotalOverdue);
		System.out.println("response: itemsOutTotalLost="+ itemsOutTotalLost);
		System.out.println("response: itemsOutRenewalAllowed="+ itemsOutRenewalAllowed);
		
		NodeList itemsOutList = XPathUtil.getNodeListFromXPath(element, "/patronpersonalresponse/itemsoutdata/itemout");
		showItems(itemsOutList);
	}
	
	/**
	 * NOTE: not fit for consumption yet
	 * read and waiting sections
	 * @param element
	 */
	public void showHolds(Element element) {
		NodeList waitingList = XPathUtil.getNodeListFromXPath(element,"/patronpersonalresponse/holdsdata/waiting/waitingitem");
		showItems(waitingList);
		NodeList readyList = XPathUtil.getNodeListFromXPath(element,"/patronpersonalresponse/holdsdata/ready/readyitem"); 
		showItems(readyList);
	}
	
	/**
	 * NOTE: not fit for consumption yet
	 * @param nodeList
	 */
	public void showItems(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			String itemOutHoldingKey = XPathUtil.getStringFromXPath(node, "holdingkey");
			String itemOutKey = XPathUtil.getStringFromXPath(node, "key");
			String itemOutIKey = XPathUtil.getStringFromXPath(node, "ikey");
			String itemOutIsbn = XPathUtil.getStringFromXPath(node, "isbn");
			String itemOutCallNum = XPathUtil.getStringFromXPath(node, "CALL/data/text");
			String itemOutTitle = XPathUtil.getStringFromXPath(node, "TITLE/data/text");
			String itemOutDisplayTitle = XPathUtil.getStringFromXPath(node, "disptitle");
			/* NOTE: AUTHOR can have multiple entries */
			NodeList authorList = XPathUtil.getNodeListFromXPath(node, "AUTHOR/data");
			List<String> authors = new ArrayList<String>();
			for (int j = 0; j < authorList.getLength(); j++) {
				Node author = authorList.item(j);
				authors.add(XPathUtil.getStringFromXPath(author, "text"));
			}
			String publisher = XPathUtil.getStringFromXPath(node, "PUBLISHER/data/text");
			String subject = XPathUtil.getStringFromXPath(node, "SUBJECT/data/text");
			String itemOutLocation = XPathUtil.getStringFromXPath(node, "location");
			String itemOutNumRenewals = XPathUtil.getStringFromXPath(node, "numrenewals");
			String itemOutCheckOutDate = XPathUtil.getStringFromXPath(node, "ckodate");
			String itemOutDueDate = XPathUtil.getStringFromXPath(node, "duedate");
			// holds specific items
			String holdsQueuePosition = XPathUtil.getStringFromXPath(node, "queuepos");
			String holdsDateExpires = XPathUtil.getStringFromXPath(node, "dateexpires");
			String holdsStatus = XPathUtil.getStringFromXPath(node, "status");
			String holdsDatePlaced = XPathUtil.getStringFromXPath(node, "dateplaced");
			String holdsPickupLocation = XPathUtil.getStringFromXPath(node, "pickuploc");
			String holdsItemKey = XPathUtil.getStringFromXPath(node, "itemkey");
			
			System.out.println("response: Item #" + i);
			System.out.println("response: holdingKey="+ itemOutHoldingKey);
			System.out.println("response: key=" + itemOutKey);
			System.out.println("response: iKey=" + itemOutIKey);
			System.out.println("response: isbn=" + itemOutIsbn);
			System.out.println("response: callNum=" + itemOutCallNum);
			System.out.println("response: title=" + itemOutTitle);
			System.out.println("response: displayTitle="	+ itemOutDisplayTitle);
			for (String author : authors) {
				System.out.println("response: author=" + author);
			}
			System.out.println("response: publisher=" + publisher);
			System.out.println("response: subject=" + subject);
			System.out.println("response: location=" + itemOutLocation);
			System.out.println("response: numRenewals="+ itemOutNumRenewals);
			System.out.println("response: checkOutDate="+ itemOutCheckOutDate);
			System.out.println("response: dueDate=" + itemOutDueDate);
			// hold specific items
			System.out.println("response: holdsQueuePosition=" + holdsQueuePosition);
			System.out.println("response: holdsDateExpires=" + holdsDateExpires);
			System.out.println("response: holdsStatus=" + holdsStatus);
			System.out.println("response: holdsDatePlaced=" + holdsDatePlaced);
			System.out.println("response: holdsPickupLocation=" + holdsPickupLocation);
			System.out.println("response: holdsItemKey=" + holdsItemKey);
		}
	}

	/**
	 * NOTE: not fit for consumption yet
	 * @param element
	 */
	public void showBlocks(Element element) {
		String blockDataTotalCount = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/blockdata/totalcount");
		System.out.println("response: blockDataTotalCount="	+ blockDataTotalCount);

		NodeList blockList = XPathUtil.getNodeListFromXPath(element,"/patronpersonalresponse/blockdata/block");
		for (int i = 0; i < blockList.getLength(); i++) {
			Node block = blockList.item(i);
			String blockDataTitle = XPathUtil.getStringFromXPath(block, "title");
			String blockDataDisplayTitle = XPathUtil.getStringFromXPath(block, "disptitle");
			String blockDataReason = XPathUtil.getStringFromXPath(block, "reason");
			String blockDataDueDate = XPathUtil.getStringFromXPath(block, "duedate");
			String blockDataAmount = XPathUtil.getStringFromXPath(block, "amount");
			System.out.println("response: Item #" + i);
			System.out.println("response: blockDataTitle=" + blockDataTitle);
			System.out.println("response: blockDataDisplayTitle="+ blockDataDisplayTitle);
			System.out.println("response: blockDataReason=" + blockDataReason);
			System.out.println("response: blockDataDueDate=" + blockDataDueDate);
			System.out.println("response: blockDataAmount=" + blockDataAmount);
		}
	}

	/**
	 * NOTE: not fit for consumption yet
	 * @param element
	 */
	public void showOverview(Element element) {
		String overviewItemsOut = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/overview/itemsout");
		String overviewItemsOverdue = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/overview/itemsoverdue");
		String overviewItemsLost = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/overview/itemslost");
		String overviewHoldsReady = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/overview/holdsready");
		String overviewHoldsWaiting = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/overview/holdswaiting");
		String overviewBlocks = XPathUtil.getStringFromXPath(element, "/patronpersonalresponse/overview/blocks");
		String overviewBalance = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/overview/balance");

		System.out.println("response: overviewItemsOut=" + overviewItemsOut);
		System.out.println("response: overviewItemsOverdue="+ overviewItemsOverdue);
		System.out.println("response: overviewItemsLost=" + overviewItemsLost);
		System.out.println("response: overviewHoldsReady=" + overviewHoldsReady);
		System.out.println("response: overviewHoldsWaiting="+ overviewHoldsWaiting);
		System.out.println("response: overviewBlocks=" + overviewBlocks);
		System.out.println("response: overviewBalance=" + overviewBalance);
	}


	/**
	 * NOTE: not fit for consumption yet
	 * @param element
	 */
	public void showPatronInfo(Element element) {
		String patronInfoNameFull = XPathUtil.getStringFromXPath(element,	"/patronpersonalresponse/patroninfo/name/full");
		String patronInfoAddressType = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/addresses/address/type");
		String patronInfoAddressStreet = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/addresses/address/street");
		String patronInfoAddressCityState = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/addresses/address/citystate");
		String patronInfoAddressPostal = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/addresses/address/postal");
		String patronInfoPhoneType = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/phones/phone/type");
		String patronInfoPhoneFull = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/phones/phone/full");
		String patronInfoEmailType = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/emailaddresses/emailaddress/type");
		String patronInfoEmail = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/emailaddresses/emailaddress/email");
		String patronInfoLocation = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/location");
		String patronInfoCardExpiresDate = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/cardexpiresdate");
		String patronInfoAddressCheckSet = XPathUtil.getStringFromXPath(element,"/patronpersonalresponse/patroninfo/addresscheckset");

		System.out.println("response: patronInfoNameFull=" + patronInfoNameFull);
		System.out.println("response: patronInfoAddressType="+ patronInfoAddressType);
		System.out.println("response: patronInfoAddressStreet="+ patronInfoAddressStreet);
		System.out.println("response: patronInfoAddressCityState="+ patronInfoAddressCityState);
		System.out.println("response: patronInfoAddressPostal="+ patronInfoAddressPostal);
		System.out.println("response: patronInfoPhoneType="+ patronInfoPhoneType);
		System.out.println("response: patronInfoPhoneFull="+ patronInfoPhoneFull);
		System.out.println("response: patronInfoEmailType="+ patronInfoEmailType);
		System.out.println("response: patronInfoEmail=" + patronInfoEmail);
		System.out.println("response: patronInfoLocation=" + patronInfoLocation);
		System.out.println("response: patronInfoCardExpiresDate="+ patronInfoCardExpiresDate);
		System.out.println("response: patronInfoAddressCheckSet="+ patronInfoAddressCheckSet);
	}
	
	/**
	 * NOTE: not fit for consumption yet
	 * @param element
	 */
	public void showSearchResults(Element element) {
		NodeList nodeList = XPathUtil.getNodeListFromXPath(element,"/searchresponse/items/searchresults/results/row");
		String requestsColumn = XPathUtil.getStringFromXPath(element, "count(/searchresponse/fullnonmarc/searchresults/header/col[label='Requests']/preceding-sibling::*)+1");
		try {
			int columnNum = Integer.parseInt(requestsColumn);
			String requestsStr = XPathUtil.getStringFromXPath(element, "/searchresponse/fullnonmarc/searchresults/results/row/cell["+columnNum+"]/data/text");
			System.out.println("requests="+requestsStr);
			int requests = Integer.parseInt(requestsStr);
			if (nodeList != null) {
				int copies = nodeList.getLength();
				System.out.println("response: copies="+copies);
				String waitStatus = "UNKNOWN";
				if (requests <= 0 && copies > 0) {
					waitStatus = "AVAILABLE";
				} else if (requests > 0 && requests < copies) {
					waitStatus = "SHORT_WAIT";
				} else if (requests > 0 && copies <= requests && requests <= 2*copies) {
					waitStatus = "WAIT";
				} else if (requests >0 && requests > 2*copies) {
					waitStatus = "LONG_WAIT";
				}
				System.out.println("wait status: "+waitStatus);
				
			}
		} catch (NumberFormatException e) {
			// ignore
		}
		// circulation status list
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			String key = XPathUtil.getStringFromXPath(node, "key");
			String callNumber = XPathUtil.getStringFromXPath(node, "CALLNUMBER/data/text");
			String copyNumber = XPathUtil.getStringFromXPath(node, "COPYNUMBER/data/text");
			String midspine = XPathUtil.getStringFromXPath(node, "MIDSPINE/data/text");
			String restrictions = XPathUtil.getStringFromXPath(node, "RESTRICTIONS/data/text");
			String availableThru = XPathUtil.getStringFromXPath(node, "AVAILABLETHRU/data/text");
			String availabilityDate = XPathUtil.getStringFromXPath(node, "AVAILABILITYDATE/data/text");
			String shelvingData = XPathUtil.getStringFromXPath(node, "SHELVINGDATA/data/text");
			String shelvingLocation = XPathUtil.getStringFromXPath(node, "SHELVINGLOCATION/data/text");
			String localLocation = XPathUtil.getStringFromXPath(node, "LOCALLOCATION/data/text");
			String temporaryLocation = XPathUtil.getStringFromXPath(node, "TEMPORARYLOCATION/data/text");
			String publicNote = XPathUtil.getStringFromXPath(node, "PUBLICNOTE/data/text");
			String itemId = XPathUtil.getStringFromXPath(node, "ITEMID/data/text");
			System.out.println("response: Item #" + i);
			System.out.println("response: key=" + key);
			System.out.println("response: restrictions="+ restrictions);
			System.out.println("response: availableThru=" + availableThru);
			System.out.println("response: availabilityDate=" + availabilityDate);
			System.out.println("response: midspine=" + midspine);
			System.out.println("response: callNumber=" + callNumber);
			System.out.println("response: copyNumber=" + copyNumber);
			System.out.println("response: shelvingData=" + shelvingData);
			System.out.println("response: shelvingLocation=" + shelvingLocation);
			System.out.println("response: localLocation=" + localLocation);
			System.out.println("response: publicNote=" + publicNote);
			System.out.println("response: itemId=" + itemId);
			System.out.println("response: temporaryLocation=" + temporaryLocation);
		}
	}
	/*
	 *  * Test client for the Integrated Library System - SirsiDynix - Horizon
	 * Information Portal
	 * 
	 * Found versions: 3.0 3.05 3.06.A 3.08 3.08_RC1_54.02 3.08_RC1_57.01
	 * 3.08_RC1_58.01 3.09_72.02 3.09_74.01 3.10_74.02 3.20a_75.00 3.20c_75.00
	 * 3.20d_75.00 (loudoun) 3.21_3023 3.21_4846
	 * 
	 * TODO: fix in case "Connection Refused" or "404", etc 
	 * TODO: change to use "get" for getting information and only "post" when sending a form
	 * 
	 * Search POST form values <li>session=<server_generated_session_id>, server
	 * session id <li>aspect=subtab89, menu item (values are of the form subtabX)
	 * <li>index=PISBNEX, search value (PALLTI, PAUTHOR, PSUBJ, ...) <li>term=?,
	 * search value (example ISBN number) <li>npp=20, search results per page <li>
	 * ri=1, search results page index <li>ipp=20, ? <li>spp=20, ? <li>profile=adm,
	 * ? <li>source=~!horizon_public, ? <li>x=10, ? <li>y=12, ?
	 * 
	 * location value #link to holding in library limit value #limit search results
	 * (fiction, non-fiction, children, ...) sort value #? match option value #?
	 * 
	 * ServerRequest(menu, submenu, aspect)
	 * 
	 * Read Search Options get parameters: menu=search aspect=basic_search
	 * 
	 * Patron Details get parameters: menu=account submenu=itemsout //security/name
	 * //security/address //security/emailaddress //security/patronid
	 * //security/phone
	 * 
	 * Items Lost Count //itemsoutdata/totallost
	 * 
	 * Items Overdue Count //itemsoutdata/totaloverdue
	 * 
	 * Renewals Allowed //itemsoutdata/renewalsallowed
	 * 
	 * Holds get parameters: menu=account submenu=holds //holdsdata/ready/readyitem
	 * dateplaced dateexpires pickuploc *figure out how the above fields fit in the
	 * holds section
	 * 
	 * Blocks get parameters: menu=account submenu=blocks
	 * //patronpersonalresponse/blockdata/block title reason duedate amount *figure
	 * out how the above fields fit in the holds section
	 * 
	 * Renew Holding post menu=account submenu=itemsout lang=eng renewitems=Renew
	 * session=<sessionId> renewitemkeys=<barcode>
	 * 
	 * Login post GetXML=true menu=home subtab=subtab11 lang=eng button=Login
	 * login_prompt=true sec1=<barcode> sec2=<password> session=<sessionId>
	 * 
	 * // Determine valid searches
	 * //GetSearchTypes(doc.SelectSingleNode("searchresponse/aspectlist"));
	 * 
	 * // Determine sort options
	 * //GetSortOptions(doc.SelectSingleNode("searchresponse/predefsortlist"));
	 * 
	 * // Determine limits (fiction, children's, Downtown Branch, etc.)
	 * //GetLimits(doc
	 * .SelectSingleNode("searchresponse/predefinedlimits/limitbox"));
	 * 
	 * 	// private Tidy getTidyInstance() {
		// Tidy tidy = new Tidy();
		// tidy.setTidyMark(false);
		// tidy.setQuiet(true);
		// tidy.setShowWarnings(false);
		// tidy.setXmlOut(true);
		// tidy.setIndentAttributes(true);
		// tidy.setSmartIndent(true);
		// tidy.setHideComments(true);
		// return tidy;
		// }

		// private String getHeaders(HttpResponse httpResponse) {
		// Header[] headerList = httpResponse.getAllHeaders();
		// StringBuilder builder = new StringBuilder();
		// for(Header header : headerList){
		// builder.append(header.getName()+"="+header.getValue()+"\n");
		// }
		// return builder.toString();
		// }

	 * */

}
