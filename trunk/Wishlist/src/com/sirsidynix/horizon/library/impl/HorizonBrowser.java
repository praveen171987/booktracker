package com.sirsidynix.horizon.library.impl;

import com.sirsidynix.horizon.library.HorizonStatus;
import com.sirsidynix.horizon.library.HorizonTools;
import com.softwaresmithy.library.LibStatus;
import com.softwaresmithy.library.Library;

/**
 * The Horizon WishList Library and LibStatus implementation
 * @author Jesse Hess
 *
 */
public class HorizonBrowser extends Library implements LibStatus {
	
	private String url;
	private String isbnIndex;

	/**
	 * init uses the first parameter as the URL and the second parameter as the index for the key needed in an ISBN search
	 */
	@Override
	public void init(String... strings) {
		if(strings.length > 0){
			this.url = strings[0];
		}
		if (strings.length > 1) {
		  this.isbnIndex = strings[1];
		}
	}
	
	/**
	 * returns the availability of an ISBN from a Horizon ISBN search
	 */
	@Override
	public STATUS checkAvailability(String isbn) {
	  HorizonTools tools = new HorizonTools();
	  HorizonStatus horizonStatus = tools.searchIsbnForStatus(this.url, this.isbnIndex, isbn);
	  
	  STATUS wishListStatus = STATUS.NO_MATCH;
	  if (HorizonStatus.AVAILABLE == horizonStatus) {
	    wishListStatus = STATUS.AVAILABLE;
	  } else if (HorizonStatus.SHORT_WAIT == horizonStatus) {
	    wishListStatus = STATUS.SHORT_WAIT;
    } else if (HorizonStatus.WAIT == horizonStatus) {
      wishListStatus = STATUS.WAIT;
    } else if (HorizonStatus.LONG_WAIT == horizonStatus) {
      wishListStatus = STATUS.LONG_WAIT;
    } else if (HorizonStatus.NO_COPIES == horizonStatus) {
      wishListStatus = STATUS.NO_MATCH;
    } else if (HorizonStatus.NO_MATCH == horizonStatus) {
      wishListStatus = STATUS.NO_MATCH;
    } else if (HorizonStatus.ERROR == horizonStatus) {
      wishListStatus = STATUS.NO_MATCH;
    }
		return wishListStatus;
	}
}