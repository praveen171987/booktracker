package com.softwaresmithy.preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.softwaresmithy.util.MapList;

public class DownloadLibrariesTask extends AsyncTask<Object, Void, Boolean> {
	
	private XPathExpression rootXpath;
	private XPathExpression libraries;
	private XPathExpression className;
	private XPathExpression state;
	private XPathExpression name;
	
	private Set<String> distinctStates = new HashSet<String>();
	private MapList<String, String> libList = new MapList<String, String>();
	private XPath xpath;
	private Node rootNode;
	
	public DownloadLibrariesTask() {
		super();
		xpath = XPathFactory.newInstance().newXPath();
		try {
			rootXpath = xpath.compile("/xml");
			libraries = xpath.compile("library");
			className = xpath.compile("class");
			state = xpath.compile("state");
			name = xpath.compile("name");

		} catch (XPathExpressionException e) {
			Log.e(this.getClass().getName(), "xpath error", e);
		}		
	}
	
	@Override
	protected Boolean doInBackground(Object... params) {
		
		File storageDir = Environment.getExternalStorageDirectory();
		File libXml = new File(storageDir, "libraries.xml");
		InputSource inputSource = null;
		try {
			if(libXml.exists()){
				inputSource = new InputSource(new FileInputStream(libXml));
			}else {
				HttpClient client = (HttpClient) params[0];
				HttpGet getLibs = new HttpGet((String)params[1]);		
				HttpResponse resp = client.execute(getLibs);
				InputStream is = resp.getEntity().getContent();
				
				if(libXml.createNewFile()){
					FileOutputStream fos = new FileOutputStream(libXml);
					try {
						
						byte[] buf = new byte[1024];
						int len;
						while ((len = is.read(buf)) > 0) {
							fos.write(buf, 0, len);
						}
					} finally {
						fos.close();
						is.close();
					}
					inputSource = new InputSource(new FileInputStream(libXml));
				}else {
					inputSource = new InputSource(is);
				}
			}

			rootNode = (Node) rootXpath.evaluate(inputSource, XPathConstants.NODE);
			
			NodeList libraryNodes = (NodeList) libraries.evaluate(rootNode, XPathConstants.NODESET);
			for(int i=0; i<libraryNodes.getLength(); i++) {
				Node libNode = libraryNodes.item(i);
				String stateText = state.evaluate(libNode);				
				distinctStates.add(stateText);		
			}
		} catch (Exception e){
			Log.e(this.getClass().getName(), "xpath error creating preferences", e);
		} finally {
			try {
				inputSource.getByteStream().close();
			} catch (Exception e) {}
		}
		return null;
	}
	
	public Set<String> getDistinctStates() {
		return distinctStates;
	}

	public Node getRootNode() {
		return rootNode;
	} 
}
