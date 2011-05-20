package com.tlcdelivers.librarysolution.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.softwaresmithy.httpclient.HttpClientTool;
import com.softwaresmithy.library.LibStatus.STATUS;
import com.softwaresmithy.xpath.util.XPathUtil;

/**
 * Tools for interacting with The Library Corporation's Library.Solution.PAC product
 * @author Jesse Hess
 *
 */
public class LibrarySolutionTools {

  public static HttpClientTool httpClientTool;

  public LibrarySolutionTools() {
    httpClientTool = new HttpClientTool();
  }
  
	/**
	 * for using via a proxy
	 * @param d
	 * @param port
	 */
	public LibrarySolutionTools(String proxyHostName, int proxyPort) {
		httpClientTool = new HttpClientTool(proxyHostName, proxyPort);
	}

  /**
   * This implementation gives status of AVAILABLE, NO_MATCH, or WAIT because the number of 'holds' is unknown
   * @param url
   * @param isbn
   * @return
   */
  public LibrarySolutionStatus searchIsbnForStatus(String url, String isbn) {
    List<NameValuePair> parameters = new ArrayList<NameValuePair>();
    parameters.add(new BasicNameValuePair("GetAvailability", ""));
    parameters.add(new BasicNameValuePair("type", "isbn"));
    parameters.add(new BasicNameValuePair("IdList", isbn));
    String xml = httpClientTool.httpGet(url, parameters);
    System.out.println(xml);
    Element element = XPathUtil.getElement(xml);
    //<r><t><lo ac="value"></t></r>
    int available = getCountFromElementUsingXPath(element, "/r/t/lo/@ac");
    //<r><t><lo tc="value"></t></r>
    int copies = getCountFromElementUsingXPath(element, "/r/t/lo/@tc");
    System.out.println("available="+available+" copies="+copies);
    LibrarySolutionStatus status = LibrarySolutionStatus.NO_MATCH;
    if (copies > 0 && available > 0) {
    	status = LibrarySolutionStatus.AVAILABLE;
    } else if (copies > 0 && available == 0) {
    	status = LibrarySolutionStatus.WAIT;
    }
    return status;
  }
  
  /**
   * Uses the xPath to get a NodeList which is used looped to get the value and add together for the total count
   * @param element
   * @param xPath
   * @return
   */
  private int getCountFromElementUsingXPath(Element element, String xPath) {
    int count = 0;
    NodeList nodeList = XPathUtil.getNodeListFromXPath(element, xPath);
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      String value = node.getNodeValue();
      try {
        count += Integer.parseInt(value);
      } catch(NumberFormatException e) {
        System.out.println("failed using: "+xPath+" on value="+value);
      }
    }
    return count;
  }
}
