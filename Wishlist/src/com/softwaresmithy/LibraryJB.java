package com.softwaresmithy;

import java.util.HashMap;

public class LibraryJB {
  private String name;
  private String state;
  private String clazz;

  private HashMap<String, String> args;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getClazz() {
    return clazz;
  }

  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  public HashMap<String, String> getArgs() {
    if (args == null) {
      args = new HashMap<String, String>();
    }
    return args;
  }

  public void setArgs(HashMap<String, String> args) {
    this.args = args;
  }

  @Override
  public String toString() {
    return name + ", " + state;
  }
}
