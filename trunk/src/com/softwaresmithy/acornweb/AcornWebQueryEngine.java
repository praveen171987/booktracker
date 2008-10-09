/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softwaresmithy.acornweb;

import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import com.softwaresmithy.amazon.AmazonResult;
/**
 *
 * @author Matt
 */
public class AcornWebQueryEngine {
    String[][] holds;
    String[][] fines;
    String[][] checkedOut;
    HttpClient client;
    
    public static void main(String[] args) {
    	AcornWebQueryEngine library = new AcornWebQueryEngine();
    	List<String> list = new java.util.ArrayList<String>();
    	list.add("1595540865");
    	System.out.println(library.atLibrary(list));
    }
    public AcornWebQueryEngine(){
        System.out.println("Constructed");
        //Initialize web client
        //Constants for paths
        //Default to my username/password
        //Create a new session (w/Post) at every query, or
        //AcornWebQueryEngine("202002686286","levan");
		Credentials defaultcreds = new UsernamePasswordCredentials("szy4zq", "scumBKT19");		
		
		client = new HttpClient();
		client.getHostConfiguration().setProxy("Internetpln.eds.com", 80);
		client.getState().setProxyCredentials(AuthScope.ANY, defaultcreds);
    }
    public AcornWebQueryEngine(String userid, String lastname) {
        client = new HttpClient();
    }
    public String atLibrary(List<String> isbns){
        //http://visual.acornweb.org/rss.asp?q=isbn:1595540865%20OR%20isbn:1595540857
        System.out.println("Executing atLibrary");
        int responseCode = -1;
        int numItems = 0;
        GetMethod get = new GetMethod("http://visual.acornweb.org");
        
        get.setRequestHeader("Proxy-Authorization", "Basic c3p5NHpxOnNjdW1CS1QxOQ==");
        get.setRequestHeader("Proxy-Connection", "Keep-Alive");
        
        get.setPath("rss.asp");
        get.setQueryString("q="+listToOrString(isbns));
        try{
            responseCode = client.executeMethod(get);            
            //System.out.println(get.getResponseBodyAsString());
            numItems = get.getResponseBodyAsString().split("<item").length-1;
            if(numItems>0){
	            String firstRecord = get.getResponseBodyAsString().split("<item")[1];
	            int idStart = firstRecord.indexOf("<link>")+6;
	            int idEnd = firstRecord.indexOf("</link>")-1;
	            String carlId = firstRecord.substring(idStart,idEnd);
	            return carlId.substring(carlId.length()-9);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            get.releaseConnection();
        }
        return null;
    }
    public String atLibrary(AmazonResult item){
    	List<String> isbns = new java.util.ArrayList<String>();
    	if(item.getISBN()!= null)
    		isbns.add(item.getISBN());
    	if(item.getAlternateVersions()!= null)
    		isbns.addAll(item.getAlternateVersions());
    	return atLibrary(isbns);
    }
    public String[][] getHolds(){
        return holds;
    }
    public String[][] getFines(){
        return fines;
    }
    public String[][] getCheckedOut(){
        return checkedOut;
    }
    public String[][] updateHolds(){
        return holds;
    }
    public String[][] updateFines(){
        return fines;
    }
    public String[][] updateCheckedOut(){
        return checkedOut;
    }
    public void updateAll(){
        updateHolds();
        updateFines();
        updateCheckedOut();
    }
    private String listToOrString(List<String> isbns){
        String orString="";
        for(int i=0;i<isbns.size();i++){
            if(i<isbns.size()-1){
                orString += "isbn:"+isbns.get(i)+" OR ";
            }
            else orString += "isbn:"+isbns.get(i);
        }
        
        try{
        	return URLEncoder.encode(orString, "UTF8");
        }catch(Exception e){return null;}
    }
    
}
