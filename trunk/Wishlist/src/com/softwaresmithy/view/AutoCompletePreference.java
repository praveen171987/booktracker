package com.softwaresmithy.view;

import java.util.List;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AutoCompleteTextView;

import com.softwaresmithy.LibraryJB;
import com.softwaresmithy.LibraryResourceAdapter;
import com.softwaresmithy.library.LibraryResourceParser;

public class AutoCompletePreference extends EditTextPreference {

	private static AutoCompleteTextView mEditText = null;

	public AutoCompletePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		mEditText = new AutoCompleteTextView(context, attrs);
		mEditText.setThreshold(0);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		List<LibraryJB> libList = new LibraryResourceParser(context).getLibraries();
		LibraryResourceAdapter adapter = new LibraryResourceAdapter(context, android.R.layout.simple_dropdown_item_1line, libList);
		mEditText.setAdapter(adapter);
	}
    private static final String[] COUNTRIES = new String[] {
        "Belgium", "France", "Italy", "Germany", "Spain"
    };

	@Override
	protected void onBindDialogView(View view) {
		//super.onBindDialogView(view);
        AutoCompleteTextView editText = mEditText;
        editText.setText(getText());

        ViewParent oldParent = editText.getParent();
        if (oldParent != view) {
            if (oldParent != null) {
                ((ViewGroup) oldParent).removeView(editText);
            }
            onAddEditTextToDialogView(view, editText);
        }
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            String value = mEditText.getText().toString();
            if (callChangeListener(value)) {
                setText(value);
            }
        }
	}
}
