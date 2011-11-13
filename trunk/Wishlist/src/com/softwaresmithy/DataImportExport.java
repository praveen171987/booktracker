package com.softwaresmithy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DataImportExport extends Activity {

    private static final int PICK_FILE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_export);
    }

    /**
     * R.id.import_file_button onClick method as specified in import_export.xml
     *
     * @param button layout specified button
     */
    public void invokePickFile(View button) {
        Intent intent = new Intent("org.openintents.action.PICK_FILE");
        intent.setData(Uri.parse("file:///sdcard/notepad.csv"));
        intent.putExtra("org.openintents.extra.TITLE", "Please select a file");
        startActivityForResult(intent, PICK_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (PICK_FILE == requestCode && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            ((EditText) findViewById(R.id.import_file_text)).setText(uri.toString());
        }
    }
}
