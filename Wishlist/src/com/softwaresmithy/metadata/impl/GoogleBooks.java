package com.softwaresmithy.metadata.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

import com.softwaresmithy.BookJB;
import com.softwaresmithy.metadata.MetadataProvider;

public class GoogleBooks implements MetadataProvider {
	private static XPathExpression titleXpath;
	private static XPathExpression authorXpath;
	private static XPathExpression volumeIdXpath;
	private static XPathExpression thumbXpath;
	private static XPathExpression entityXpath;
	
	//For injection during testing
	private HttpClient client;
	
	public GoogleBooks(){
   		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new GoogleBooksNamespaceContext());
		try{
			entityXpath = xpath.compile("/atom:feed/atom:entry[1]");
			
			titleXpath = xpath.compile("dc:title");
			authorXpath = xpath.compile("dc:creator");
			volumeIdXpath = xpath.compile("dc:identifier[1]/text()");
			thumbXpath = xpath.compile("atom:link[@rel=\"http://schemas.google.com/books/2008/thumbnail\"]/@href");
		}catch(XPathException e){
			Log.e(this.getClass().getName(), "Something bad happened building xpath constants", e);
		}
		client = new DefaultHttpClient();
	}
	@Override
	public BookJB getInfo(String isbn) {
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("q", isbn));
    	try{
    		URI uri = URIUtils.createURI("http", "books.google.com", -1, "/books/feeds/volumes", URLEncodedUtils.format(params, "UTF-8"), null);
    		HttpResponse resp = client.execute(new HttpGet(uri));
   		
    		Node bookNode = (Node) entityXpath.evaluate(new InputSource(resp.getEntity().getContent()), XPathConstants.NODE);
    		
    		NodeList titleNodes = (NodeList) titleXpath.evaluate(bookNode, XPathConstants.NODESET);
    		String title = concatNodes(titleNodes, ": ");
    		
    		NodeList authorNodes = (NodeList) authorXpath.evaluate(bookNode, XPathConstants.NODESET);
    		String author = concatNodes(authorNodes, ", ");
    		
    		String volumeId = (String) volumeIdXpath.evaluate(bookNode, XPathConstants.STRING);
    		Log.d(this.getClass().getName(), "volumeId: "+volumeId);
    		
    		String thumbUrl = (String) thumbXpath.evaluate(bookNode, XPathConstants.STRING);
    		
    		BookJB retVal = new BookJB(isbn, volumeId, title, author);
    		retVal.setThumbUrl(thumbUrl);
    		return retVal;
    	}catch(Exception e){
			Log.e(this.getClass().getName(), e.getMessage(), e);
			return null;
    	}
    }
    
    private String concatNodes(NodeList nodes, String sep){
		StringBuilder str = new StringBuilder("");
		for(int i=0; i<nodes.getLength(); i++){
			Node titleNode = nodes.item(i);
			if(str.length() > 0) {
				str.append(sep);
			}
			str.append(titleNode.getTextContent());
		}
		return str.toString();
    }
	@Override
	public boolean saveThumbnail(Context context, String volumeId, String thumbUrl) {
		try {
    		URI dest;
    		if(thumbUrl != null){
    			dest = new URI(thumbUrl);
    			String[] args = dest.getQuery().split("&");
    			//TODO: remove the 'edge' arg (if exists)
    		}else {
    			//http://bks3.books.google.com/books?id=1abqveXLr1QC&amp;printsec=frontcover&amp;img=1&amp;zoom=5&amp;edge=curl&amp;source=gbs_gdata
    			List<NameValuePair> params = new ArrayList<NameValuePair>();
    			params.add(new BasicNameValuePair("id",volumeId));
    			params.add(new BasicNameValuePair("printsec","frontcover"));
    			params.add(new BasicNameValuePair("img","1"));
    			params.add(new BasicNameValuePair("zoom","5"));
    			params.add(new BasicNameValuePair("source","gbs_gdata"));
    			
    			dest = URIUtils.createURI("http", "bks3.books.google.com", -1, "/books", URLEncodedUtils.format(params, "UTF-8"), null);
    		}
		
			HttpGet cover = new HttpGet(dest);
			HttpResponse resp = client.execute(cover);
			if(resp.getEntity().getContentType().getValue().equals("image/jpeg")){
				Bitmap image = BitmapFactory.decodeStream(resp.getEntity().getContent());
				File file = new File(context.getExternalCacheDir(),volumeId+".jpg");
				image.compress(CompressFormat.JPEG, 95, new FileOutputStream(file));
			}
			return true;
		}catch(Exception e){
			Log.e(this.getClass().getName(), e.getMessage(), e);
			return false;
		}
	}
	public HttpClient getClient() {
		return client;
	}
	public void setClient(HttpClient client) {
		this.client = client;
	}

}
