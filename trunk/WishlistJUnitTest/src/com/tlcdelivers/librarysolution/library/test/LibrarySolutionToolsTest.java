package com.tlcdelivers.librarysolution.library.test;

import java.util.ArrayList;
import java.util.List;

import com.tlcdelivers.librarysolution.library.LibrarySolutionTools;


public class LibrarySolutionToolsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LibrarySolutionTools tools;
		if (args.length > 1) {
			String proxyUrl = args[0];
			int port = Integer.parseInt(args[1]);
			tools = new LibrarySolutionTools(proxyUrl, port);
		} else {
			tools = new LibrarySolutionTools();
		}
		
		List<String> urls = new ArrayList<String>();
//		urls.add("http://12.131.0.195/TLCScripts/interpac.dll");
//		urls.add("http://166.61.230.4/TLCScripts/interpac.dll");
//		urls.add("http://204.111.4.131/TLCScripts/interpac.dll");
//		urls.add("http://206.113.131.6/TLCScripts/interpac.dll");
//		urls.add("http://206.248.241.200/TLCScripts/interpac.dll");
//		urls.add("http://207.68.46.102/TLCScripts/interpac.dll");
//		urls.add("http://64.4.111.3/TLCScripts/interpac.dll");
//		urls.add("http://65.168.251.21/TLCScripts/interpac.dll");
//		urls.add("http://65.254.191.49/TLCScripts/interpac.dll");
		urls.add("http://69.27.84.133/TLCScripts/interpac.dll"); // web server down
//		urls.add("http://71.0.81.91/TLCScripts/interpac.dll");
//		urls.add("http://ccl.campbell.k12.va.us/TLCScripts/interpac.dll");
//		urls.add("http://circdesk.wythegrayson.lib.va.us/TLCScripts/interpac.dll");
//		urls.add("http://libcat.pclibs.org/TLCScripts/interpac.dll");
//		urls.add("http://mcplva.org/TLCScripts/interpac.dll");
//		urls.add("http://pac.cclva.org/TLCScripts/interpac.dll");
//		urls.add("http://www.brrl.lib.va.us/TLCScripts/interpac.dll");
//		urls.add("http://www.cclibrary.net/TLCScripts/interpac.dll");
//		urls.add("http://www.fcplva.org/TLCScripts/interpac.dll");
//		urls.add("http://www.lprlibrary.org/TLCScripts/interpac.dll");
//		urls.add("http://www.valleylibraries.org/TLCScripts/interpac.dll");
		String isbn = "0375504397";
		for (String url : urls) {
			System.out.println((urls.indexOf(url)+1)+" of "+urls.size()+" for "+url);
			tools.searchIsbnForStatus(url, isbn);
		}
	}

}
