package com.softwaresmithy.library;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public abstract class Library {
  private static XPathExpression nameXml;
  private static XPathExpression stateXml;
  private static XPathExpression linkXml;
  private static XPathExpression argsXml;

  static {
    try {
      XPath factory = XPathFactory.newInstance().newXPath();
      nameXml = factory.compile("name");
      stateXml = factory.compile("state");
      linkXml = factory.compile("link");
      argsXml = factory.compile("arg");

    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
  }

  private String name;
  private String state;
  private String link;
  private Map<String, String> args = new HashMap<String, String>();

  public abstract void init(Map<String, String> args);

  public void parseNode(Node xmlNode) {
    try {
      name = nameXml.evaluate(xmlNode);
      state = stateXml.evaluate(xmlNode);
      link = linkXml.evaluate(xmlNode);
      NodeList argList = (NodeList) argsXml.evaluate(xmlNode, XPathConstants.NODESET);
      for (int i = 0; i < argList.getLength(); i++) {
        Node argNode = argList.item(i);
        String key = argNode.getAttributes().getNamedItem("key").getTextContent();
        String val = argNode.getTextContent();
        args.put(key, val);
      }
      init(args);
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
  }

  public abstract boolean isCompatible(String url) throws URISyntaxException;

  public String getName() {
    return name;
  }

  public String getState() {
    return state;
  }

  public String getLink() {
    return link;
  }

  public Map<String, String> getArgs() {
    return args;
  }

  @Override
  public String toString() {
    return name;
  }

}
