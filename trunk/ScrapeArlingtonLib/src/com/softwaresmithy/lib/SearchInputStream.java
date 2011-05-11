package com.softwaresmithy.lib;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SearchInputStream{

	private InputStream is;
	public SearchInputStream(InputStream is){
		this.is = is;
	}
	
	public boolean find(String str) throws IOException{
		int match = 0;
		byte[] bytes = str.getBytes();
		int curByte = is.read();
		while(match < bytes.length && curByte > -1){
			if(curByte == bytes[match]) match++;
			else match = 0;
			
			curByte = is.read();
		}
		if(match == bytes.length) return true;
		return false;
	}
	
	public static void main(String... args) throws Exception{
		String testStr = "this is a test of the emergency broadcast system";
		InputStream is = new ByteArrayInputStream(testStr.getBytes());
		
		System.out.println(new SearchInputStream(is).find(("of the public")));
	}
}
