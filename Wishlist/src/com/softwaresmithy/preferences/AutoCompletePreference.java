package com.softwaresmithy.preferences;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.List;

public class AutoCompletePreference extends EditTextPreference {

  private AutoCompleteTextView mEditText = null;

  private ArrayAdapter<?> adapter;

  public AutoCompletePreference(Context context, AttributeSet attrs) {
    super(context, attrs);
    mEditText = new AutoCompleteTextView(context, attrs);
    mEditText.setThreshold(0);
    mEditText.setAdapter(adapter);
  }

  public void setAdapterContent(List<String> newList) {
    adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, newList);
    mEditText.setAdapter(adapter);
  }

  @Override
  protected void onBindDialogView(View view) {
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
