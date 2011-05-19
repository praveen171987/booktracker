package com.softwaresmithy.library.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.regex.MatchResult;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.softwaresmithy.library.LibStatus;
import com.softwaresmithy.library.Library;

public class WebPac extends Library implements LibStatus{
	private String isbnSearchUrl;
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
			Scanner s = new Scanner(resp.getEntity().getContent());
			String pattern = s.findWithinHorizon("((\\d*) hold[s]? on first copy returned of (\\d*) )?[cC]opies", 0);
			
			if(pattern != null){
				MatchResult match = s.match();
				if(match.groupCount() == 3){
					if(match.group(2) == null){
						return STATUS.AVAILABLE;
					}else{
						int numHolds = Integer.parseInt(match.group(2));
						int numCopies = Integer.parseInt(match.group(3));
						if(numHolds < numCopies){
							return STATUS.SHORT_WAIT;
						}else if(numHolds >= numCopies && numHolds <= (2*numCopies)){
							return STATUS.WAIT;
						}else{
							return STATUS.LONG_WAIT;
						}
					}
				}
			}
			return STATUS.NO_MATCH;
    	}catch(Exception e){
			Log.e(this.getClass().getName(), e.getMessage(), e);
			return null;       		
    	}finally{
    		if(get != null)
    			get.abort();
    	}
	}
	@Override
	public boolean isCompatible(String url) throws URISyntaxException{
		URI checkurl = new URI(url);
		
		HttpGet get = null;
		try{
			HttpClient client = new DefaultHttpClient();
			get = new HttpGet(checkurl);
			HttpResponse resp = client.execute(get);
			
			Scanner s = new Scanner(resp.getEntity().getContent());
			String pattern = s.findWithinHorizon("<link.*\"/scripts/ProStyles.css\"", 0);
			return (pattern != null && !pattern.equals(""));
		} catch (ClientProtocolException e) {
			Log.e(this.getClass().getName(), "failed checking compatibility", e);
		} catch (IOException e) {
			Log.e(this.getClass().getName(), "failed checking compatibility", e);
		}finally{
			if(get != null)
				get.abort();
		}
		return false;
	}

}
