package com.sirsidynix.horizon.library.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.softwaresmithy.library.LibStatus;
import com.softwaresmithy.library.Library;

public class HorizonBrowser extends Library implements LibStatus {
	
	private String url;
	
	@Override
	public void init(String... strings) {
		if(strings.length > 0){
			url = strings[0];
		}
	}
	
	@Override
	public STATUS checkAvailability(String isbn) {
		String xmlGet = httpGet(url, "auth=true", "GetXML=true");
		Element element = getElement(xmlGet);
		String sessionId = getStringFromXPath(element, "/searchresponse/session | /patronpersonalresponse/session");

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("session", sessionId));
		parameters.add(new BasicNameValuePair("index", "PISBNEX"));
		parameters.add(new BasicNameValuePair("term", isbn));
		parameters.add(new BasicNameValuePair("ipp", "150"));
		parameters.add(new BasicNameValuePair("GetXML", "true"));
				
		String xmlPost = httpPost(url, parameters);
		element = getElement(xmlPost);
		
		NodeList nodeList = getNodeListFromXPath(element,"/searchresponse/items/searchresults/results/row");
		String requestsColumn = getStringFromXPath(element, "count(/searchresponse/fullnonmarc/searchresults/header/col[label='Requests']/preceding-sibling::*)+1");
		STATUS waitStatus = STATUS.NO_MATCH;
		try {
			int columnNum = Integer.parseInt(requestsColumn);
			String requestsStr = getStringFromXPath(element, "/searchresponse/fullnonmarc/searchresults/results/row/cell["+columnNum+"]/data/text");
			int requests = Integer.parseInt(requestsStr);
			if (nodeList != null) {
				int copies = nodeList.getLength();
				if (requests <= 0 && copies > 0) {
					waitStatus = STATUS.AVAILABLE;
				} else if (requests > 0 && requests < copies) {
					waitStatus = STATUS.SHORT_WAIT;
				} else if (requests > 0 && copies <= requests && requests <= 2*copies) {
					waitStatus = STATUS.WAIT;
				} else if (requests >0 && requests > 2*copies) {
					waitStatus = STATUS.LONG_WAIT;
				}
			}
		} catch (NumberFormatException e) {
			// ignore
		}
		NodeList errorList = getNodeListFromXPath(element, "//error");
		for (int i = 0; i < errorList.getLength(); i++) {
			Node error = errorList.item(i);
			String errorType = getStringFromXPath(error,"type");
			String errorSubject = getStringFromXPath(error,"subject");
			String errorReason = getStringFromXPath(error,"reason");
			String errorMessage = getStringFromXPath(error,"message");			

			if (!isEmpty(errorType + errorSubject + errorReason + errorMessage)) {
				waitStatus = STATUS.NO_MATCH;
			}
		}
		return waitStatus;
	}
	
	private String httpGet(String url, String... parameters) {
		String response = "";
		StringBuilder b = new StringBuilder();
		boolean isFirst = true;
		for (String parameter : parameters) {
			b.append((isFirst ? "?" : "&") + parameter);
			isFirst = false;
		}
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url + b.toString());
			HttpResponse httpGetResponse = httpClient.execute(httpget);
			response = EntityUtils.toString(httpGetResponse.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private String httpPost(String url, List<NameValuePair> parameters) {
		String response = "";
		try {
			StringBuilder b = new StringBuilder();
			boolean isFirst = true;
			for (NameValuePair parameter : parameters) {
				b.append((isFirst ? "?" : "&") + parameter.getName() + "=" + parameter.getValue());
				isFirst = false;
			}
			UrlEncodedFormEntity form = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(form);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse postResponse = httpClient.execute(httpPost);
			response = EntityUtils.toString(postResponse.getEntity());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private Element getElement(String xmlString) {
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

	private String getStringFromXPath(Node node, String expression) {
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
	
	private NodeList getNodeListFromXPath(Node node, String expression) {
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

	private boolean isEmpty(String s) {
		boolean value = false;
		if (s == null || "".equals(s)) {
			value = true;
		}
		return value;
	}
}