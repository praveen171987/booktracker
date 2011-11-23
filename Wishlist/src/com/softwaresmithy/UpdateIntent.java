package com.softwaresmithy;

import android.content.Intent;
import com.softwaresmithy.library.AndroidLibStatus.STATUS;

public class UpdateIntent extends Intent {

  private static final String ISBN = "isbn";

  public UpdateIntent(String action, String isbn, STATUS oldStatus, STATUS newStatus) {
    super(action);
    putExtra(ISBN, isbn);
    if (oldStatus == null) {
      oldStatus = STATUS.NO_MATCH;
    }
    putExtra("oldStatus", oldStatus.name());
    putExtra("newStatus", newStatus.name());
  }

  public UpdateIntent(Intent updateIntent) {
    super(updateIntent);
  }

  public STATUS getOldStatus() {
    return STATUS.valueOf(getStringExtra("oldStatus"));
  }

  public STATUS getNewStatus() {
    return STATUS.valueOf(getStringExtra("newStatus"));
  }

  public String getIsbn() {
    return getStringExtra(ISBN);
  }
}
