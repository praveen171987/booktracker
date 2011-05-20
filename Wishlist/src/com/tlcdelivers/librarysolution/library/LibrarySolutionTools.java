package com.tlcdelivers.librarysolution.library;

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
 * Tools for interacting with The Library Corporation's Library.Solution.PAC product
 * @author Jesse Hess
 *
 */
public class LibrarySolutionTools {

  public static HttpClientTool httpClientTool;

  public LibrarySolutionTools() {
    httpClientTool = new HttpClientTool();
  }
  
  public void searchIsbnForStatus(String url, String isbn) {
    List<NameValuePair> parameters = new ArrayList<NameValuePair>();
    parameters.add(new BasicNameValuePair("GetAvailability", ""));
    parameters.add(new BasicNameValuePair("type", "isbn"));
    parameters.add(new BasicNameValuePair("IdList", isbn));
    String xml = httpClientTool.httpGet(url, parameters);
    System.out.println(xml);
    Element element = XPathUtil.getElement(xml);
    int available = getAvailableCount(element);
    int copies = getCopyCount(element);
    System.out.println("available="+available+" copies="+copies);
    // TODO: return the "status" based on the values given
  }
  
  /**
   * perform XPATH to get the number of available
   * @param element
   * @return
   */
  private int getAvailableCount(Element element) {
    //<r><t><lo ac="value"></t></r>
    int count = getCountFromElementUsingXPath(element, "/r/t/lo/@ac");
    return count;
  }
  
  /**
   * perform XPATH to get the number of total copies
   * @param element
   * @return
   */
  private int getCopyCount(Element element) {
    //<r><t><lo tc="value"></t></r>
    int count = getCountFromElementUsingXPath(element, "/r/t/lo/@tc");
    return count;
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
