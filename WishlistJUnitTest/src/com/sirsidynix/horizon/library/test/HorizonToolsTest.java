package com.sirsidynix.horizon.library.test;

import java.util.ArrayList;
import java.util.List;

import com.sirsidynix.horizon.library.HorizonStatus;
import com.sirsidynix.horizon.library.HorizonTools;

public class HorizonToolsTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    List<String> urls = new ArrayList<String>();
    urls.add("http://192.69.117.21/ipac20/ipac.jsp"); // amherst co
    urls.add("http://216.54.20.117/ipac20/ipac.jsp"); // portsmouth public
    urls.add("http://catalog.lcpl.lib.va.us/ipac20/ipac.jsp"); // loudoun co
    urls.add("http://catalog.wrl.org/ipac20/ipac.jsp"); // williamsburg regional
    urls.add("http://hip.hamptonpubliclibrary.org/ipac20/ipac.jsp"); // hampton public
    urls.add("http://horizon.samuelslibrary.net/ipac20/ipac.jsp"); // samuels public
    urls.add("http://ipac.librarypoint.org/ipac20/ipac.jsp"); // central rappahannock regional
    urls.add("http://ipac.yorkcounty.gov:8080/ipac20/ipac.jsp"); // york co
    HorizonTools tools = new HorizonTools();
    String isbn = "9780743292337";
    String isbnIndex = "";
    for (String url : urls) {
      HorizonStatus status = tools.searchIsbnForStatus(url, isbnIndex, isbn);
      System.out.println(status+" for "+url);
    }
  }

}
