package com.softwaresmithy.library;

import java.util.Arrays;


public class LibraryFactory {
	private String className;
	private String[] args;
	public LibraryFactory(String value){
		String[] allArgs = value.split(" ");
		className = allArgs[0];
		if(allArgs.length>1){
			args = (String[]) Arrays.asList(allArgs).subList(1, allArgs.length).toArray(allArgs);
		}
	}
	
	public Library getLibrary() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		Library clazz = (Library) Class.forName(className).newInstance();
		clazz.init(args);
		return clazz;
	}
}
