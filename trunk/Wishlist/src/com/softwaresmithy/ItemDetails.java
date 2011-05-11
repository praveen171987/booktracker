package com.softwaresmithy;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ItemDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		WebView xslt = new WebView(this);
		setContentView(xslt);
		xslt.loadData("Matt was Here", "text/html", "utf-8");
		
	}

}
