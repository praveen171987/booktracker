package com.softwaresmithy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class DataImportExport extends Activity {

  //Intent results
  private static final int PICK_FILE = 0;
  private static final int GO_TO_MARKET = 1;

  //Dialog codes
  private static final int SHOW_NO_INTENT = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.import_export);

    findViewById(R.id.import_file_text).setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View view, boolean b) {
        if (b) {
          invokePickFile(view);
        }
      }
    });
  }

  /**
   * R.id.import_file_text onClick method as specified in import_export.xml
   *
   * @param view the import file button
   */
  public void invokePickFile(View view) {
    Intent intent = new Intent("org.openintents.action.PICK_FILE");
    intent.setData(Uri.parse("file:///sdcard/MyBooks.csv"));
    intent.putExtra("org.openintents.extra.TITLE", "Please select a file");

    try {
      startActivityForResult(intent, PICK_FILE);
    } catch (ActivityNotFoundException e) {
      if (getPreferences(MODE_PRIVATE).getBoolean("show_no_intent", true)) {
        showDialog(SHOW_NO_INTENT);
      }
    }
  }

  @SuppressWarnings({"UnusedDeclaration"})
  public void invokeStartExport(View view) {
    new ImportExportTask().execute(getApplicationContext());
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (PICK_FILE == requestCode && resultCode == RESULT_OK) {
      Uri uri = data.getData();
      ((EditText) findViewById(R.id.import_file_text)).setText(uri.toString());
    }
  }

  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {
      case SHOW_NO_INTENT:
        View checkboxLayout = LayoutInflater.from(this).inflate(R.layout.no_pick_file_intent, null);
        final CheckBox checkbox = (CheckBox) checkboxLayout.findViewById(R.id.show_no_intent_checkbox);
        return new AlertDialog.Builder(this)
            .setMessage(R.string.no_intent_text)
            .setPositiveButton(R.string.no_intent_market, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                getPreferences(MODE_PRIVATE).edit().putBoolean("show_no_intent", !checkbox.isChecked()).commit();
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=org.openintents.filemanager"));
                startActivityForResult(goToMarket, GO_TO_MARKET);
              }
            })
            .setNegativeButton(R.string.no_intent_continue, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                getPreferences(MODE_PRIVATE).edit().putBoolean("show_no_intent", !checkbox.isChecked()).commit();
              }
            })
            .setView(checkboxLayout)
            .create();
      default:
        return super.onCreateDialog(id);
    }
  }
}
