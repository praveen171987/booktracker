package com.softwaresmithy.xpath;


import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathUtil {

	/**
	 * Perform the XPathExpression as represented by the 'expression' on the Node and return a String result
	 * @param node
	 * @param expression
	 * @return
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
	 * @param node
	 * @param expression
	 * @return
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
	 * @param xmlString
	 * @return
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
