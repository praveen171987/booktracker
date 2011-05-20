package com.tlcdelivers.librarysolution.library.impl;

import java.net.URISyntaxException;

import com.softwaresmithy.library.LibStatus;
import com.softwaresmithy.library.Library;
import com.tlcdelivers.librarysolution.library.LibrarySolutionTools;

/**
 * The Library Corporation's Library.Solution.PAC WishList Library and LibStatus implementation
 * @author Jesse Hess
 *
 */
public class LibrarySolutionBrowser extends Library implements LibStatus {

	private String url;
	
	/**
	 * Initialize the URL
	 */
	@Override
	public void init(String... strings) {
		if(strings.length > 0){
			this.url = strings[0];
		}
	}	
	
	/**
	 * Check the availability which gives status of AVAILABLE, NO_MATCH, or WAIT because the number of 'holds' is unknown
	 */
	@Override
	public STATUS checkAvailability(String isbn) {
		LibrarySolutionTools tools = new LibrarySolutionTools();
		STATUS status = tools.searchIsbnForStatus(this.url, isbn);
		return status;
	}

	/**
	 * Assumes the URL will end in 'TLCScripts/interpac.dll'
	 */
	@Override
	public boolean isCompatible(String url) throws URISyntaxException {
		boolean compatible = false;
		if (url != null && url.endsWith("TLCScripts/interpac.dll")) {
			compatible = true;
		}
		return compatible;
	}
}
