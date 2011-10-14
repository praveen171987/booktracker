package com.softwaresmithy.library.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import android.util.Log;

import com.softwaresmithy.library.AndroidLibStatus;

public class AquaBrowser extends AndroidLibStatus {
	private String isbnSearchUrl;
	private XPathExpression itemXpath;
	
	public AquaBrowser(){
		XPath xpath = XPathFactory.newInstance().newXPath();
		try{
			this.itemXpath = xpath.compile("/rss/channel/item[1]/link");
		}catch(XPathException e){
			Log.e(this.getClass().getName(), "Something bad happened building xpath constants", e);
		}
	}
	public void init(Map<String, String> args) {
		if(args.containsKey("url")){
			this.isbnSearchUrl = args.get("url");
		}
	}

	@Override
	public boolean isCompatible(String url) throws URISyntaxException {
		URI uri = new URI(url);
		HttpClient client = new DefaultHttpClient();
		URI mangledUri = new URI(uri.getScheme()+"://"+uri.getAuthority()+"/rss.ashx");
		HttpGet get = new HttpGet(mangledUri);
		try{
			HttpResponse resp = client.execute(get);
			StatusLine respStatus = resp.getStatusLine();
			if(respStatus.getStatusCode() == HttpURLConnection.HTTP_OK){
				return true;
			}else if(respStatus.getStatusCode() == HttpURLConnection.HTTP_UNAVAILABLE && respStatus.getReasonPhrase().equals("Rss module not active.")){
				//retStatus="AquaBrowser, but unsupported";
				return false;
			}else{
				//retStatus="Not AquaBrowser";
				return false;
			}
		} catch (ClientProtocolException e) {
			Log.e(this.getClass().getName(), "failed checking compatibility", e);
		} catch (IOException e) {
			Log.e(this.getClass().getName(), "failed checking compatibility", e);
		}finally{
			if(get != null){
				get.abort();
			}
		}
		return false;
	}
	@Override
	public STATUS checkAvailability(String isbn) {
		HttpGet get = null;
		try{
			HttpClient client = new DefaultHttpClient();
			get = new HttpGet(String.format(isbnSearchUrl, isbn));
			HttpResponse resp = client.execute(get);
			
			String link = (String) itemXpath.evaluate(new InputSource(resp.getEntity().getContent()), XPathConstants.STRING);
			if(link != null && !link.equals("")){
				//TODO: follow said link to determine the wait time
				return STATUS.AVAILABLE;
			}
			return STATUS.NO_MATCH;
		}catch(Exception e){
			Log.e(this.getClass().getName(), e.getMessage(), e);
			return null;       		
		}finally{
			if(get != null){
				get.abort();
			}
		}
	}

}
