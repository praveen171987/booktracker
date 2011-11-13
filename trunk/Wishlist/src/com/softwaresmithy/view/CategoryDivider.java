package com.softwaresmithy.view;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * I'd like to do this by making a style that extends listSeparatorTextViewStyle, but unfortunately,
 * it's private :(
 *
 * @author matt
 */
public class CategoryDivider extends TextView {

  public CategoryDivider(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    int vertPadding = (int) Math.round(2.0 * getResources().getDisplayMetrics().density);
    int leftPadding = (int) Math.round(5.0 * getResources().getDisplayMetrics().density);
    setPadding(leftPadding, vertPadding, vertPadding, 0);
  }

  public CategoryDivider(Context context, AttributeSet attrs) {
    this(context, attrs, R.attr.listSeparatorTextViewStyle);
  }

  public CategoryDivider(Context context) {
    this(context, null, R.attr.listSeparatorTextViewStyle);
  }
}
