package com.softwaresmithy.library.impl;

import com.softwaresmithy.library.Library;

import java.util.Map;

public class Default extends Library {

  @Override
  public boolean isCompatible(String url) {
    return true;
  }

  @Override
  public void init(Map<String, String> args) {
    // TODO Auto-generated method stub

  }

}
