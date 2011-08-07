package com.softwaresmithy.preferences;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class WishlistClickListener implements OnClickListener {
	private boolean retVal;
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			retVal = true;
		}else {
			retVal = false;
		}
	}
	
	public boolean getRetVal(){
		return retVal;
	}
}
