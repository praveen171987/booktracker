package com.softwaresmithy.library.impl;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;

import android.util.Log;

import com.softwaresmithy.library.LibStatus;
import com.softwaresmithy.library.Library;

public class AquaBrowser extends Library implements LibStatus {
	private String isbnSearchUrl;
	private XPathExpression itemXpath;
	
	public AquaBrowser(){
		XPath xpath = XPathFactory.newInstance().newXPath();
		try{
			itemXpath = xpath.compile("/rss/channel/item[1]/link");
		}catch(XPathException e){
			Log.e(this.getClass().getName(), "Something bad happened building xpath constants", e);
		}
	}
	@Override
	public void init(String... strings) {
		if(strings.length > 0){
			isbnSearchUrl = strings[0];
		}
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
			}else{
				return STATUS.NO_MATCH;
			}
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
