package com.softwaresmithy.library.impl;

import java.net.URISyntaxException;

import com.softwaresmithy.library.LibStatus;
import com.softwaresmithy.library.Library;
import com.tlcdelivers.librarysolution.library.LibrarySolutionStatus;
import com.tlcdelivers.librarysolution.library.LibrarySolutionTools;

/**
 * The Library Corporation's Library.Solution.PAC WishList Library and LibStatus implementation
 * @author Jesse Hess
 *
 */
public class LibrarySolution extends Library implements LibStatus {

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
		LibrarySolutionStatus librarySolutionStatus = tools.searchIsbnForStatus(this.url, isbn);
		STATUS status = STATUS.NO_MATCH;
		if (librarySolutionStatus == LibrarySolutionStatus.AVAILABLE) {
			status = STATUS.AVAILABLE;
		} else if (librarySolutionStatus == LibrarySolutionStatus.NO_MATCH) {
			status = STATUS.NO_MATCH;
		} else if (librarySolutionStatus == LibrarySolutionStatus.WAIT) {
			status = STATUS.WAIT;
		}
		return status;
	}

	/**
	 * Assumes the URL will end in 'TLCScripts/interpac.dll'
	 */
	@Override
	public boolean isCompatible(String sUrl) throws URISyntaxException {
		boolean compatible = false;
		if (sUrl != null && sUrl.endsWith("TLCScripts/interpac.dll")) {
			compatible = true;
		}
		return compatible;
	}
}
