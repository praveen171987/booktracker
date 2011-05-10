package com.softwaresmithy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Wishlist extends ListActivity {
	
	private int mNoteNumber = 1;
	private WishlistDbAdapter mDbHelper;
	private EditText isbnInput;
	private SimpleCursorAdapter listData;
	
	//Intent request states
	private final int ACTIVITY_CREATE = 0;
	private final int ACTIVITY_EDIT = 1;
	
	//Same index used to map from database table column to layout ID in item view
//	private String[] mapFrom = WishlistDbAdapter.allColumns;
	private String[] mapFrom = new String[]{WishlistDbAdapter.COL_TITLE, WishlistDbAdapter.COL_AUTHOR};	
	private int[] mapTo = new int[]{R.id.title, R.id.author};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerForContextMenu(getListView());
        
        mDbHelper = new WishlistDbAdapter(this);
        mDbHelper.open();
        //initialize text input listener
        isbnInput = (EditText)findViewById(R.id.input_isbn); 
        isbnInput.setOnKeyListener(new OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
					createItem(isbnInput.getText().toString());
					return true;
				}
				return false;
			}
        });
        fillData();
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
    	super.onCreateContextMenu(menu, v, menuInfo);
    	getMenuInflater().inflate(R.menu.item_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	switch(item.getItemId()){
    	case R.id.edit_item:
	    	Intent i = new Intent(this,EditItem.class);
	    	i.putExtra(EditItem.ROWID, info.id);
	    	startActivityForResult(i, ACTIVITY_EDIT);
    		break;
    	case R.id.delete_item:
    		new AlertDialog.Builder(this)
    			.setTitle(R.string.del_title)
    			.setMessage(R.string.del_message)
    			.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mDbHelper.deleteItem(info.id);
						fillData();
					}
				})
				.setNegativeButton(R.string.no, null)
				.show();
    		break;
    	}
    	return super.onContextItemSelected(item);
    }
    
    private void fillData(){
    	//Get all rows from DB
    	Cursor c = mDbHelper.getAll();
    	startManagingCursor(c);
    	
    	listData = new ImageCursorAdapter(this, R.layout.list_item, c, mapFrom, mapTo,getExternalCacheDir().getAbsolutePath());
    	setListAdapter(listData);
    }
    
    private void createItem(String isbn){
    	if(validIsbn(isbn)){
			isbnInput.setVisibility(View.GONE);
			isbnInput.setText("");
			new DownloadDataTask().execute(isbn);
    	}else{
    		Toast.makeText(this, isbn+" is not a valid ISBN", Toast.LENGTH_SHORT).show();
    	}
    }


	public boolean validIsbn(String isbn){
    	char[] nums = isbn.toCharArray();
    	if(nums.length == 10){
    		if(nums[9]=='X' || nums[9]=='x'){
    			nums[9] = 10+'0'; //So that the subtraction below works out correctly
    		}
    		int a=0, b=0;
    		for(int i=0; i<10; i++){
    			a += nums[i]-'0';
    			b += a;
    		}
    		return b % 11 == 0;
    	}else if(nums.length == 13){
    		int s=0;
    		for(int i=0; i<12; i++){
    			s += (nums[i]-'0')*(1+(2*(i%2)));
    		}
    		return 10-(s%10) == nums[12]-'0';
    	}
    	return false;
    }
    /*Menu related functions*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    	case R.id.add_text:
    		isbnInput.setVisibility(View.VISIBLE);
    		isbnInput.requestFocus();
    		return true;
    	case R.id.add_barcode:
    		//TODO: customize text?
    		AlertDialog alert = IntentIntegrator.initiateScan(this);
    		if(alert != null){
    			alert.show();
    		}
    		return true;
    	case R.id.add_scratch:
        	Intent i = new Intent(this,EditItem.class);
        	startActivityForResult(i, ACTIVITY_CREATE);
        	return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	switch(requestCode){
	    	case IntentIntegrator.REQUEST_CODE: {
	    		IntentResult res = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
	    		
	    		String scanContents;
	    		if(res != null){
	    			scanContents = res.getContents();
	    		}else {
	    			return;
	    			//Nothing to see here, move along
	    		}
	    		
	    		if(validIsbn(scanContents)){
	    			createItem(scanContents);
	    		}else {
	    			new AlertDialog.Builder(this).setMessage(R.string.bad_scan).show();
	    		}
	    	}
	    	break;
	    	default: //unknown request code
    	}
    }
    
    
    private class DownloadDataTask extends AsyncTask<String, Void, NameValuePair> {
    	@Override
		protected void onPostExecute(NameValuePair result) {
			if(result.getName().equals(GOOD)){
				//Refresh UI
				fillData();
			}else {
				Log.e(this.getClass().getName(), "Fuck, bad return value");
			}
			super.onPostExecute(result);
		}

		public static final String BAD = "error";
    	public static final String GOOD = "good";
    	
    	@Override
    	protected NameValuePair doInBackground(String... params) {
    		String isbn = params[0];
			// 1) get basic info from xISBN
			//TODO: Surround with try/catch, catch net exception, throw to manual edit page with error
    		NameValuePair status;
			status = addItemToDB(isbn);
			if(status.getName().equals(GOOD)){
				String[] out = status.getValue().split(",");
				status = getImages(out[0], out[1]);
			}else{
				return status;
			}
			if(!status.getName().equals(GOOD)){
				return status;
			}
    		return new BasicNameValuePair(GOOD,null);
    	}
        private NameValuePair getImages(String volumeId, String thumbUrl) {
    		HttpClient client = new DefaultHttpClient();
    		try {
	    		URI dest;
	    		if(thumbUrl != null){
	    			dest = new URI(thumbUrl);
	    			String[] args = dest.getQuery().split("&");
	    			//TODO: remove the 'edge' arg
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
    				File file = new File(getExternalCacheDir(),volumeId+".jpg");
    				image.compress(CompressFormat.JPEG, 95, new FileOutputStream(file));
    			}
    			return new BasicNameValuePair(GOOD, null);
    		}catch(Exception e){
    			Log.e(this.getClass().getName(), e.getMessage(), e);
    			return new BasicNameValuePair(BAD, e.getMessage());
    		}
    		
    	}

        private NameValuePair addItemToDB(String isbn) {
        	HttpClient client = new DefaultHttpClient();
        	List<NameValuePair> params = new ArrayList<NameValuePair>();
        	params.add(new BasicNameValuePair("q", isbn));
        	try{
        		URI uri = URIUtils.createURI("http", "books.google.com", -1, "/books/feeds/volumes", URLEncodedUtils.format(params, "UTF-8"), null);
        		HttpResponse resp = client.execute(new HttpGet(uri));
        		
        		BufferedHttpEntity ent = new BufferedHttpEntity(resp.getEntity());
        		XPath xpath = XPathFactory.newInstance().newXPath();
        		xpath.setNamespaceContext(new GoogleBooksNamespaceContext());
        		
        		String entityXpath = "/atom:feed/atom:entry[1]";
        		Node bookNode = (Node)xpath.evaluate(entityXpath, new InputSource(ent.getContent()), XPathConstants.NODE);
        		
        		printNode(bookNode);
        		
        		String titleXpath = "/atom:feed/atom:entry[1]/dc:title";
        		NodeList titleNodes = (NodeList) xpath.evaluate(titleXpath, bookNode, XPathConstants.NODESET);
        		String title = concatNodes(titleNodes, ": ");
        		
        		String authorXpath = "/atom:feed/atom:entry[1]/dc:creator";
        		NodeList authorNodes = (NodeList) xpath.evaluate(authorXpath, bookNode, XPathConstants.NODESET);
        		String author = concatNodes(authorNodes, ", ");
        		
        		String volumeIdXpath = "/atom:feed/atom:entry[1]/dc:identifier[1]/text()";
        		String volumeId = (String) xpath.evaluate(volumeIdXpath, bookNode, XPathConstants.STRING);
        		Log.d(this.getClass().getName(), "volumeId: "+volumeId);
        		
        		String thumbXpath = "/atom:feed/atom:entry[1]/atom:link[@rel=\"http://schemas.google.com/books/2008/thumbnail\"]/@href";
        		String thumbUrl = (String) xpath.evaluate(thumbXpath, bookNode, XPathConstants.STRING);
        		
        		mDbHelper.createItem(new BookJB(isbn, volumeId, title, author));
        		return new BasicNameValuePair(GOOD, volumeId+","+thumbUrl);
        	}catch(Exception e){
    			Log.e(this.getClass().getName(), e.getMessage(), e);
    			return new BasicNameValuePair(BAD, e.getMessage());       		
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
        //Original xisbn version
//    	private NameValuePair addItemToDB(String isbn) {
//    		HttpClient client = new DefaultHttpClient();
//    		List<NameValuePair> params = new ArrayList<NameValuePair>();
//    		params.add(new BasicNameValuePair("method", "getMetadata"));
//    		params.add(new BasicNameValuePair("format", "json"));
//    		params.add(new BasicNameValuePair("ai", "scumbkt19"));
//    		params.add(new BasicNameValuePair("fl", "title,author"));
//    		try {
//    			URI uri  = URIUtils.createURI("http", "xisbn.worldcat.org", -1, "/webservices/xid/isbn/"+isbn, URLEncodedUtils.format(params, "UTF-8"), null);
//    			HttpGet xIsbn = new HttpGet(uri);
//    			HttpResponse resp = client.execute(xIsbn);
//    			String jsonResp = EntityUtils.toString(resp.getEntity());
//    			Log.d(this.getClass().getName(), "json: "+jsonResp);
//    			JSONObject obj = (JSONObject) new JSONTokener(jsonResp).nextValue();
//    			//Parse object
//    			if(!obj.getString("stat").equals("ok")){
//    				return new BasicNameValuePair(BAD, "an error occured: "+obj.getString("stat"));
//    			}else {
//    				JSONArray list = obj.getJSONArray("list");
//    				JSONObject item = (JSONObject) list.get(0);
//    				String title = null, author = null;
//    				if(item.has("title")){
//    					title = item.getString("title");
//    					title = title.endsWith(".")?title.substring(0,title.length()-1):title;
//    				}
//    				if(item.has("author")){
//    					author = item.getString("author");
//    					author = author.endsWith(".")?author.substring(0,author.length()-1):author;
//    				}
//    				mDbHelper.createItem(new BookJB(null, isbn, null, title, author, null, null, null, null));
//    			}
//    			return null;
//    		} catch (Exception e) {
//    			Log.e(this.getClass().getName(), e.getMessage(), e);
//    			return new BasicNameValuePair(BAD, e.getMessage());
//    		} 
//    	}
        private void printNode(Node node){
        	try
            {
              // Set up the output transformer
              TransformerFactory transfac = TransformerFactory.newInstance();
              Transformer trans = transfac.newTransformer();
              trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
              trans.setOutputProperty(OutputKeys.INDENT, "yes");

              // Print the DOM node

              StringWriter sw = new StringWriter();
              StreamResult result = new StreamResult(sw);
              DOMSource source = new DOMSource(node);
              trans.transform(source, result);
              String xmlString = sw.toString();

              System.out.println(xmlString);
            }
            catch (TransformerException e)
            {
              e.printStackTrace();
            }
        }
    }
}