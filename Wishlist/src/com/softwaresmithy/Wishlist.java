package com.softwaresmithy;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Wishlist extends ListActivity {
	
	private int mNoteNumber = 1;
	private WishlistDbAdapter mDbHelper;
	private EditText isbnInput;
	private SimpleCursorAdapter listData;
	
	//Same index used to map from database table column to layout ID in item view
//	private String[] mapFrom = WishlistDbAdapter.allColumns;
	private String[] mapFrom = new String[]{WishlistDbAdapter.allColumns[3], WishlistDbAdapter.allColumns[4]};	
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
    	Toast.makeText(this, "I've been clicked!", Toast.LENGTH_SHORT).show();
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
			// 1) get basic info from xISBN
			addItemToDB(isbn);
			getImages(isbn);
			listData.notifyDataSetChanged();
    	}else{
    		Toast.makeText(this, isbn+" is not a valid ISBN", Toast.LENGTH_SHORT).show();
    	}
    }
    
    private void getImages(String isbn) {
		HttpClient client = new DefaultHttpClient();
		//http://imagesa.btol.com/ContentCafe/Jacket.aspx?UserID=ContentCafeClient&Password=Client&Type=S&Value=9780857660763
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("UserID", "ContentCafeClient"));
		params.add(new BasicNameValuePair("Password", "Client"));
		params.add(new BasicNameValuePair("Type", "S"));
		params.add(new BasicNameValuePair("Value", isbn));
		try {
			URI btol = URIUtils.createURI("http", "imagesa.btol.com", -1, "/contentCafe/Jacket.aspx", URLEncodedUtils.format(params, "UTF-8"), null);
			HttpGet cover = new HttpGet(btol);
			HttpResponse resp = client.execute(cover);
			if(resp.getEntity().getContentType().getValue().equals("image/jpeg")){
				Bitmap image = BitmapFactory.decodeStream(resp.getEntity().getContent());
				File file = new File(getExternalCacheDir(),isbn+".jpg");
				image.compress(CompressFormat.JPEG, 95, new FileOutputStream(file));
			}
		}catch(Exception e){
			Log.e(this.getClass().getName(), e.getMessage(), e);
		}
		
	}

	private void addItemToDB(String isbn) {
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("method", "getMetadata"));
		params.add(new BasicNameValuePair("format", "json"));
		params.add(new BasicNameValuePair("ai", "scumbkt19"));
		params.add(new BasicNameValuePair("fl", "title,author"));
		try {
			URI uri  = URIUtils.createURI("http", "xisbn.worldcat.org", -1, "/webservices/xid/isbn/"+isbn, URLEncodedUtils.format(params, "UTF-8"), null);
			HttpGet xIsbn = new HttpGet(uri);
			HttpResponse resp = client.execute(xIsbn);
			String jsonResp = EntityUtils.toString(resp.getEntity());
			Log.d(this.getClass().getName(), "json: "+jsonResp);
			JSONObject obj = (JSONObject) new JSONTokener(jsonResp).nextValue();
			//Parse object
			if(!obj.getString("stat").equals("ok")){
				Toast.makeText(this, "an error occured: "+obj.getString("stat"), Toast.LENGTH_SHORT).show();
			}else {
				JSONArray list = obj.getJSONArray("list");
				JSONObject item = (JSONObject) list.get(0);
				String title = null, author = null;
				if(item.has("title")){
					title = item.getString("title");
				}
				if(item.has("author")){
					author = item.getString("author");
				}
				mDbHelper.createItem(isbn, null, title, author, null);
			}
			
		} catch (Exception e) {
			Log.e(this.getClass().getName(), e.getMessage(), e);
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
    		//Call external scanner via Intent
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
}