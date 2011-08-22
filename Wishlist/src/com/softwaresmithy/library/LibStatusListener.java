package com.softwaresmithy.library;

import com.softwaresmithy.library.AndroidLibStatus.STATUS;


public interface LibStatusListener {
	void onItemStatusChange(String isbn, STATUS result);
}
