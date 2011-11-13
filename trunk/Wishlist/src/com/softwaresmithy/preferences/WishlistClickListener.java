package com.softwaresmithy.preferences;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class WishlistClickListener implements OnClickListener {
  private boolean retVal;

  @Override
  public void onClick(DialogInterface dialog, int which) {
    retVal = which == DialogInterface.BUTTON_POSITIVE;
  }

  public boolean getRetVal() {
    return retVal;
  }
}
