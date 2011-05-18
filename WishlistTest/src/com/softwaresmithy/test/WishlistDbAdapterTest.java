package com.softwaresmithy.test;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import com.softwaresmithy.WishlistDbAdapter;

public class WishlistDbAdapterTest extends AndroidTestCase {

	private WishlistDbAdapter db;
	private Context mockContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mockContext = new MockContext();
		db = new WishlistDbAdapter(mockContext);
	}
	
	public void testCreateItem(){
		
	}
	
	public void testReadItem(){
		
	}
	
	public void testReadItemByIsbn(){
		
	}

	public void testUpdateItem(){
		
	}
	public void testDeleteItem(){
		
	}
	public void testGetAll(){
		
	}
}
