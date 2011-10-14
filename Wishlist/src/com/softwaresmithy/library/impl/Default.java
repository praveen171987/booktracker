package com.softwaresmithy.library.impl;

import java.util.Map;

import com.softwaresmithy.library.Library;

public class Default extends Library {

	@Override
	public boolean isCompatible(String url) {
		return true;
	}

	@Override
	public void init(Map<String, String> args) {
		// TODO Auto-generated method stub
		
	}

}
