package com.softwaresmithy.library;

import java.util.Arrays;

import com.softwaresmithy.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class LibraryFactory {
	private String className;
	private String[] args;
	public LibraryFactory(Context context){
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefVal = prefs.getString(context.getString(R.string.perf_libName), null);
        if(prefVal != null){
			String[] allArgs = prefVal.split(" ");
			className = allArgs[0];
			if(allArgs.length>1){
				args = (String[]) Arrays.asList(allArgs).subList(1, allArgs.length).toArray(allArgs);
			}
        }else {
        	throw new RuntimeException("Library implementation not found in Preferences");
        }
	}
	
	public Library getLibrary() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		Library clazz = (Library) Class.forName(className).newInstance();
		clazz.init(args);
		return clazz;
	}
}
