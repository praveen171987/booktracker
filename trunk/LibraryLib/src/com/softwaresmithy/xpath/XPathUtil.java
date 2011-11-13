package com.softwaresmithy.xpath;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringReader;

public class XPathUtil {

  /**
   * Perform the XPathExpression as represented by the 'expression' on the Node and return a String result
   *
   * @param node       xml node
   * @param expression a node relative xpath expression to extract, returns a String
   * @return the String specified by the xpath expression
   */
  public static String getStringFromXPath(Node node, String expression) {
    String nodeResult = "";
    try {
      XPathFactory factory = XPathFactory.newInstance();
      XPath xpath = factory.newXPath();
      XPathExpression expr = xpath.compile(expression);
      nodeResult = (String) expr.evaluate(node, XPathConstants.STRING);
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
    return nodeResult;
  }

  /**
   * Perform the XPathExpression as represented by the 'expression' on the Node and return a NodeList result
   *
   * @param node       xml node
   * @param expression a node relative xpath expression to extract, returns a List
   * @return the specified List
   */
  public static NodeList getNodeListFromXPath(Node node, String expression) {
    NodeList nodeResult = null;
    try {
      XPathFactory factory = XPathFactory.newInstance();
      XPath xpath = factory.newXPath();
      XPathExpression expr = xpath.compile(expression);
      nodeResult = (NodeList) expr.evaluate(node, XPathConstants.NODESET);
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
    return nodeResult;
  }

  /**
   * Create the Document from the String and return the Document Element
   *
   * @param xmlString full document as XML
   * @return the parsed document as an element
   */
  public static Element getElement(String xmlString) {
    Element element = null;
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      InputSource inputSource = new InputSource();
      inputSource.setCharacterStream(new StringReader(xmlString));
      Document document = documentBuilder.parse(inputSource);
      element = document.getDocumentElement();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return element;
  }

}
