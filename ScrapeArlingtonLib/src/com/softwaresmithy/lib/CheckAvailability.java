package com.softwaresmithy.lib;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class CheckAvailability {
	private static final String isbnSearchUrl = "http://libsys.arlingtonva.us/search/?searchtype=i&searcharg=%s&searchscope=1";
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		HttpClient httpclient = new DefaultHttpClient();
		HttpHost proxy = new HttpHost("proxy.houston.hp.com",8080);
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		
		InputStream is = CheckAvailability.class.getResourceAsStream("/isbns.txt");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String isbn = null;
		HttpGet get = null;
		while((isbn=br.readLine()) != null){
			System.out.println("Target url: \n\t"+String.format(isbnSearchUrl, isbn));
			get = new HttpGet(String.format(isbnSearchUrl, isbn));
			HttpResponse resp = httpclient.execute(get);
			System.out.println(isbn+": "+!new SearchInputStream(resp.getEntity().getContent()).find("class=\"yourEntryWouldBeHere\""));
			get.abort();
		}
	}

}
