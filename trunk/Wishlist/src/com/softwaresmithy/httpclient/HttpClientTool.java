package com.softwaresmithy.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientTool {
	
	private HttpClient httpClient;

	public HttpClientTool() {
		createHttpClient(null, -1);
	}
	
	public HttpClientTool(String hostName, int port) {
		createHttpClient(hostName, port);
	}
	
	/**
	 * Perform an HTTP GET
	 * @param url
	 * @param parameters
	 * @return
	 */
	public String httpGet(String url, List<NameValuePair> parameters) {
		String response = "";
		String params = buildHttpParameters(parameters);
		//System.out.println("request: get=" + url + params); // for debugging
		HttpGet httpGet = new HttpGet(url + params);
		response = getHttpResponse(httpGet);
		return response;
	}

	/**
	 * Perform an HTTP POST
	 * @param url
	 * @param parameters
	 * @return
	 */
	public String httpPost(String url, List<NameValuePair> parameters) {
		String response = "";
		// for debugging
		//String params = buildHttpParameters(parameters);
		//System.out.println("request: post " + url + params); // for debugging
		UrlEncodedFormEntity form = null;
		try {
			form = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); // should never happen because UTF_8 is the standard encoding
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(form);
		response = getHttpResponse(httpPost);
		return response;
	}
	
	/**
	 * Build the parameter lists in the form: ?param1=value1&param2=value2
	 * @param parameters
	 * @return
	 */
	private String buildHttpParameters(List<NameValuePair> parameters) {
		StringBuilder b = new StringBuilder();
		boolean isFirst = true;
		for (NameValuePair parameter : parameters) {
		  String n = parameter.getName();
		  String v = parameter.getValue();
			b.append((isFirst ? "?" : "&") + n + (v != null && !v.trim().equals("") ? "=" + v : ""));
			isFirst = false;
		}
		return b.toString();
	}
	
	/**
	 * NOTE: possible location for handling HTTP failures
	 * @param request
	 * @return
	 */
	private String getHttpResponse(HttpUriRequest request) {
		String response = "";
		HttpResponse postResponse;
		try {
			postResponse = this.httpClient.execute(request);
			response = EntityUtils.toString(postResponse.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Create the HttpClient for use by the HorizonTools methods
	 * @param hostName
	 * @param port
	 */
	private void createHttpClient(String hostName, int port) {
		this.httpClient = new DefaultHttpClient();
		/* handling sending via a proxy */
		if (hostName != null) {
			HttpHost httpHost = new HttpHost(hostName, port);
			this.httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, httpHost);
		}
	}

}
