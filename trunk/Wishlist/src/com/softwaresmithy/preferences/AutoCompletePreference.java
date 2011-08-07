package com.softwaresmithy.preferences;

import java.util.List;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompletePreference extends EditTextPreference {

	private AutoCompleteTextView mEditText = null;
	private final String[] COUNTRIES = new String[] {
        "Belgium", "France", "Italy", "Germany", "Spain"
    };
	private ArrayAdapter<? extends Object> adapter;
	
	public AutoCompletePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		mEditText = new AutoCompleteTextView(context, attrs);
		mEditText.setThreshold(0);
		adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		mEditText.setAdapter(adapter);
	}
	
	public void setAdapterContent(List<String> newList) {
		adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, newList);
		mEditText.setAdapter(adapter);
	}

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

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		mEditText.setEnabled(enabled);
	}
	
	
}
