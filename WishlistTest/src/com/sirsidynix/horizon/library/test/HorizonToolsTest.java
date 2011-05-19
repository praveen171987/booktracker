package com.sirsidynix.horizon.library.test;

import java.util.LinkedHashMap;
import java.util.Map;

import android.test.AndroidTestCase;

import com.sirsidynix.horizon.library.HorizonStatus;
import com.sirsidynix.horizon.library.HorizonTools;

/**
 * Test the HorizonTools class
 * @author Jesse Hess
 * TODO: flesh this out, figure out how to simulate responses using local XML
 */
public class HorizonToolsTest extends AndroidTestCase {

  private HorizonTools tools;
  
  public HorizonToolsTest() {
    this.tools = new HorizonTools();
  }
  
  public void testNothingAtAll() {
    assertTrue(true);
  }
  
  /**
   * @param args
   */
  public void testIsbnSearch() {
    // for testing ISBN
//    String isbn = "9780765315151"; // 1 result
//    String isbn = "9780441020379"; // no results
//    String isbn = "9781436178518"; // 2 results
//    String isbn = "9780312651206"; // held item
//    String isbn = "9780394900018"; // cat in the hat
//    String isbn = "9780394800011"; // cat in the hat
//    String isbn = "0340364777"; // IT
//    String isbn = "0450411435"; // IT
//    String isbn = "9780743292337"; // Cell
    String isbn = "0375504397"; // black house
//    String isbn = "0670260770"; // the dead zone
//    String isbn = "0451155750"; // the dead zone
    Map<String, String> urlMap = new LinkedHashMap<String, String>();
    urlMap.put("http://192.69.117.21/ipac20/ipac.jsp", ""/*"ISBNEX"*/); // amherst co, VA
    urlMap.put("http://216.54.20.117/ipac20/ipac.jsp", ""/*"ISBNEX"*/); // portsmouth public, VA
    urlMap.put("http://catalog.lcpl.lib.va.us/ipac20/ipac.jsp", "PISBNEX"); // loudoun co, VA
    urlMap.put("http://catalog.wrl.org/ipac20/ipac.jsp", "ISBNEX"); // williamsburg regional, VA
    urlMap.put("http://hip.hamptonpubliclibrary.org/ipac20/ipac.jsp", "ISBNEX"); // hampton public, VA
    urlMap.put("http://hip.hcplonline.info/ipac20/ipac.jsp", "ISBNEX"); // harford co, MD
    urlMap.put("http://horizon.samuelslibrary.net/ipac20/ipac.jsp", "ISBNEX"); // samuels public, VA
    urlMap.put("http://ipac.librarypoint.org/ipac20/ipac.jsp", "ISBNE"); // central rappahannock regional, VA
    urlMap.put("http://ipac.yorkcounty.gov:8080/ipac20/ipac.jsp", "ISBNEX"); // york co, VA
    // other horizon libraries
//    urlMap.put("http://146.74.92.18/ipac20/ipac.jsp", "ISBNEX"); // request positive
//    urlMap.put("http://199.120.252.134/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://216.57.213.200/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://catalog.lcpl.lib.va.us/ipac20/ipac.jsp", "PISBNEX"); // available
//    urlMap.put("http://catalog.mylibrary.us/ipac20/ipac.jsp", "ISBNEX"); // request positive
//    urlMap.put("http://bronte.egvpl.org/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://64.60.64.101:81/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://4.22.114.43/ipac20/ipac.jsp", "ISBN"); // 0 matches, available
//    urlMap.put("http://66.195.188.172/ipac20/ipac.jsp", "ISBNEX"); // no copies, available
//    urlMap.put("http://catalog.hcpl.net/ipac20/ipac.jsp", "ISBNEXH"); // no copies, available
//    urlMap.put("http://catalog.spl.org/ipac20/ipac.jsp", "ISBNZEX"); // no match, available
//    urlMap.put("http://192.69.117.21/ipac20/ipac.jsp", "ISBNEX"); // legit no copies of dr suess
//    urlMap.put("http://capemay.njstatelib.org/ipac20/ipac.jsp", "ISBNEX"); // 0 matches, available
//    urlMap.put("http://catalog.vencolibrary.org/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://catalog.westportlibrary.org/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://comarms.ipac.dynixasp.com/ipac20/ipac.jsp", "ISBN"); // available
//    urlMap.put("http://hip.hcplonline.info/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://hip.plcmc.org/ipac20/ipac.jsp", "ISBNEX"); // requests positive
//    urlMap.put("http://hip1.sjvls.org/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://ipac.anaheim.net/ipac20/ipac.jsp", "ISBNE"); // available
//    urlMap.put("http://ipac.hcplc.org/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://ipac.librarypoint.org/ipac20/ipac.jsp", "ISBNE"); // available
//    urlMap.put("http://ipac2.vpl.ca/ipac20/ipac.jsp", "ISBNEX"); // short wait, available
//    urlMap.put("http://lincpac.lincolntrail.info:8080/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://search.idaho-lynx.org/ipac20/ipac.jsp", "ISBNEX"); // available
//    urlMap.put("http://webpac.barringtonarealibrary.org/ipac20/ipac.jsp", ".BN"); // available
//    urlMap.put("http://www3.mississauga.ca/ipac20/ipac.jsp", "ISBN"); // available
//    urlMap.put("https://libcat.uchicago.edu/ipac20/ipac.jsp", "ISBN"); // available
    // still testing
//    urlMap.put("http://134.241.121.88/ipac20/ipac.jsp", "ISBNEX"); // more than 1 copy problem for dr suess
//    urlMap.put("http://ipac.kentonlibrary.org:8080/ipac20/ipac.jsp", "ISBNB"); // seems to only support ISBN "browse" search
    // not quite working libraries
//    urlMap.put("http://helix.usc.edu/ipac20/ipac.jsp", "ISBN"); // 0 matches
//    urlMap.put("http://hip.rpls.ws/ipac20/ipac.jsp", "ISBN"); // no copies
//    urlMap.put("http://ipac.canterbury.ac.nz/ipac20/ipac.jsp", "ISBN"); // 0 matches
//    urlMap.put("http://ipac.ci.escondido.ca.us/ipac20/ipac.jsp", "ISBN"); // no copies
//    urlMap.put("http://ipac.lewisham.gov.uk/ipac20/ipac.jsp", "ISBNEX"); // 0 matches
//    urlMap.put("http://ipac.wrhs.org:8080/ipac20/ipac.jsp", "ISBNEX"); // 0 matches
//    urlMap.put("http://leonardo.lindahall.org/ipac20/ipac.jsp", "ISBN"); // 0 matches
//    urlMap.put("http://libraries.rochdale.gov.uk/ipac20/ipac.jsp", "ISBN"); // 0 matches
//    urlMap.put("http://merlin.liu.edu/ipac20/ipac.jsp", "ISBN"); // no copies
//    urlMap.put("http://ohip.hpl.ca/ipac20/ipac.jsp", "ISBN"); // no copies
//    urlMap.put("http://ouipac.ouboces.org/ipac20/ipac.jsp", "ISBN"); // no copies
//    urlMap.put("http://webcat.hud.ac.uk/ipac20/ipac.jsp", "ISBN"); // 0 matches
//    urlMap.put("http://webpac.twu.ca/ipac20/ipac.jsp", "ISBN");  // 0 matches
//    urlMap.put("http://www.biblartepac.gulbenkian.pt/ipac20/ipac.jsp", "ISBN"); // 0 matches
//    urlMap.put("http://www3.cca.qc.ca/ipac20/ipac.jsp", "ISBN"); // 0 matches
    // failing
//    urlMap.put("http://catalog.ccpa.net/ipac20/ipac.jsp", "PISBN"); // something wrong, definitely has 0451155750, but says no copies
//    urlMap.put("http://catalog.mvlc.org/ipac20/ipac.jsp", "ISBNEX"); // no copies, possibly requires login to see holdings
//    urlMap.put("http://catalog.pasadenapubliclibrary.net/ipac20/ipac.jsp", "ISBN"); // no copies, definitely has 0375504397, something wrong
//    urlMap.put("http://catalog.tscpl.org/ipac20/ipac.jsp", "ISBN"); // no copies, definitely has 0375504397, but no results
//    urlMap.put("http://libserv.aip.org:81/ipac20/ipac.jsp", ""); // does not seem to support ISBN search
//    urlMap.put("http://216.68.116.14:8081/ipac20/ipac.jsp", ""); // does not seem to support ISBN search
//    urlMap.put("http://ipac.librarieshawaii.org/ipac20/ipac.jsp", ""); // does not seem to support ISBN search
//    urlMap.put("http://libserv.aip.org:81/ipac20/ipac.jsp", ""); // does not seem to support ISBN search
//    urlMap.put("http://ipac.selco.info/ipac20/ipac.jsp"); // requires login
//    urlMap.put("http://ipac.infolynx.org/ipac20/ipac.jsp"); // requires login
//    urlMap.put("http://ipac.selco.info/ipac20/ipac.jsp"); // requires login   
//    urlMap.put("http://wpl-pac.winnipeg.ca/ipac20/ipac.jsp", ""); // URL encoding a properly encoded submenu, the submenu that fails is the one with the ISBN
//    urlMap.put("http://siris-archives.si.edu/ipac20/ipac.jsp", ""); // Expected left paren or shortcut, misplaced symbol: 0375504397
//    urlMap.put("http://206.187.12.10/ipac20/ipac.jsp", ""); // Expected left paren or shortcut, misplaced symbol: 0375504397
//    urlMap.put("http://216.170.15.165/ipac20/ipac.jsp", "");  // Expected left paren or shortcut, misplaced symbol: 0375504397
        
    int count = 0;
    for (String url : urlMap.keySet()) {
      System.out.println((count+1)+" of "+urlMap.size());
      HorizonStatus status = this.tools.searchIsbnForStatus(url, urlMap.get(url), isbn);
      assertTrue(status+" for "+ url, true);
      System.out.println(status+" for "+ url);
      count++;
    }
    assertTrue(true);
  }
}
