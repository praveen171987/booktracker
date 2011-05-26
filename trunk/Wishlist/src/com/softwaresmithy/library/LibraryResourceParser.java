package com.softwaresmithy.library;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

import com.softwaresmithy.LibraryJB;

public class LibraryResourceParser {
	private static List<LibraryJB> libList;
	private Context context;
	//TODO: make this configurable?
	/**
	 * One day in milliseconds.
	 */
	private final int refreshRate = 1000*60*60*24;
	
	
	public LibraryResourceParser(Context context){
		this.context = context;
	}
	
	public  List<LibraryJB> getLibraries(){
		if(libList == null){
			libList = parseLibXml();
		}
		return libList;
	}
	private List<LibraryJB> parseLibXml() {
		
		File appDir = context.getExternalFilesDir(null);
		File libCache = new File(appDir, "libResources.xml");
		
		if(libCache.exists() && (System.currentTimeMillis()-libCache.lastModified() <= refreshRate)){
			//TODO: Read from file
		}else{
			//grab a new one, save it to file (if we can)
		}
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://booktracker.googlecode.com/svn/trunk/libraries.xml");
		
		List<LibraryJB> retLibList = new ArrayList<LibraryJB>();

		try {
			HttpResponse resp = client.execute(get);
			
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

			parser.setInput(resp.getEntity().getContent(), "UTF-8");
			
			int parserEvent = -1;
			String tagName = "";
			String argKey = "";
			LibraryJB jb = null;

			parserEvent = parser.getEventType();
			
			while(parserEvent != XmlPullParser.END_DOCUMENT){
				switch(parserEvent){
				case XmlPullParser.START_TAG:
					tagName = parser.getName();
					
					if(tagName.equalsIgnoreCase("LIBRARY")){
						jb = new LibraryJB();
					}else if(tagName.equalsIgnoreCase("ARG")){
						argKey = parser.getAttributeValue(0);
					}
					break;
				case XmlPullParser.END_TAG:
					tagName = parser.getName();
					if(tagName.equalsIgnoreCase("LIBRARY")){
						retLibList.add(jb);
					}
					break;
				case XmlPullParser.TEXT:
					if(parser.getText().trim().equals("")){
						break;
					}
					if(tagName.equalsIgnoreCase("NAME")){
						jb.setName(parser.getText());
					}else if(tagName.equalsIgnoreCase("STATE")){
						jb.setState(parser.getText());
					}else if(tagName.equalsIgnoreCase("CLASS")){
						jb.setClazz(parser.getText());
					}else if(tagName.equalsIgnoreCase("ARG")){
						jb.getArgs().put(argKey, parser.getText());
					}
					break;
				default:
					//Don't care	
				}
				parserEvent = parser.next();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return retLibList;
	}
}
