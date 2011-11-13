package com.polarislibrary.polaris.library;

import com.softwaresmithy.httpclient.HttpClientTool;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesse Hess
 *         <p/>
 *         GET request created for ISBN search from http://librarycatalog.pwcgov.org:
 *         /polaris/search/searchresults.aspx?
 *         ctx=1.1033.0.0.3&
 *         type=Advanced&
 *         term=9780743292337&
 *         relation=ALL&
 *         by=ISBN&
 *         bool4=AND&
 *         limit=TOM=*&
 *         sort=PD_TI&
 *         page=0
 *         <p/>
 *         http://librarycatalog.pwcgov.org/polaris/search/searchresults.aspx?ctx=1.1033.0.0.3&type=Advanced&term=9780743292337&relation=ALL&by=ISBN&bool4=AND&limit=TOM=*&sort=PD_TI&page=0
 *         <p/>
 *         bool4 AND
 *         by  ISBN
 *         ctx 1.1033.0.0.3
 *         limit TOM=*
 *         page  0
 *         relation  ALL
 *         sort  PD_TI
 *         term  9780743292337
 *         type  Advanced
 *         <p/>
 *         <p/>
 *         <p/>
 *         http://librarycatalog.pwcgov.org/polaris/search/components/availability.aspx?level=local&pos=1&morelink=0&bibid=167950&requestlevel=
 *         <p/>
 *         bibid  167950
 *         level local
 *         morelink  0
 *         pos 1
 *         requestlevel
 *         <p/>
 *         <p/>
 *         <p/>
 *         Some JavaScript code that seems to be showing the content...
 *         <p/>
 *         function ShowContent(pos, show, highlight, isbn, bibid, requestLevel)
 *         {
 *         if (isbn)
 *         strISBN = isbn;
 *         else
 *         strISBN = "";
 *         <p/>
 *         var nBibID = "";
 *         if (bibid)
 *         nBibID = bibid;
 *         <p/>
 *         var strRequestLevel = "";
 *         if (requestLevel)
 *         strRequestLevel = requestLevel;
 *         <p/>
 *         if (IsThisTitleExpanded(pos) == false)
 *         ExpandThisTitle(pos);
 *         <p/>
 *         if (show != "" && highlight != 0)
 *         HighlightThisTitle(pos);
 *         <p/>
 *         http = getHTTPObject();
 *         <p/>
 *         divid = "content_" + pos;
 *         <p/>
 *         if (show == "localavail0" || show == "autoavail0")
 *         http.open("GET", strServerRoot + "search/components/availability.aspx?level=local&pos=" + pos + "&morelink=0" + "&bibid=" + nBibID + "&requestlevel=" + strRequestLevel, true);
 *         else if (show == "localavail1" || show == "autoavail1")
 *         http.open("GET", strServerRoot + "search/components/availability.aspx?level=local&pos=" + pos + "&morelink=1" + "&bibid=" + nBibID + "&requestlevel=" + strRequestLevel, true);
 *         else if (show == "systemavail")
 *         http.open("GET", strServerRoot + "search/components/availability.aspx?level=system&pos=" + pos + "&bibid=" + nBibID + "&requestlevel=" + strRequestLevel, true);
 *         else if (show == "details" || show == "autodetails")
 *         http.open("GET", strServerRoot + "search/components/details.aspx?pos=" + pos, true);
 *         else if (show == "marc")
 *         http.open("GET", strServerRoot + "search/components/marc.aspx?pos=" + pos, true);
 *         else if (show == "similar-ti")
 *         http.open("GET", strServerRoot + "search/components/similartitles.aspx?isbn=" + isbn + "&pos=" + pos, true);
 *         else if (show == "similar-au")
 *         http.open("GET", strServerRoot + "search/components/similarauthors.aspx?isbn=" + isbn, true);
 *         else if (show == "nextreads")
 *         http.open("GET", strServerRoot + "search/components/nextreads.aspx?isbn=" + isbn, true);
 *         else if (show == "mapit")
 *         {
 *         document.getElementById(divid).innerHTML = "<iframe width=650 height=700 frameborder=0 src=" + strServerRoot + "search/components/mapit.aspx?pos=" + pos + "></iframe>";
 *         idExt = pos;
 *         ShowExtendedOptions(idExt);
 *         return;
 *         }
 *         else
 *         {
 *         document.getElementById(divid).innerHTML = "";
 *         return;
 *         }
 *         <p/>
 *         if (show == "autoavail0" || show == "autoavail1" || "autodetails")
 *         {
 *         idExt = pos;
 *         http.onreadystatechange = handleHttpResponseContentInitial;
 *         }
 *         else
 *         http.onreadystatechange = handleHttpResponseContent;
 *         <p/>
 *         http.send(null);
 *         document.body.style.cursor = "wait";
 *         }
 */
public class PolarisTools {

  public static void main(String[] args) {
    HttpClientTool httpClient = new HttpClientTool();
    String result;
//    String url = "http://librarycatalog.pwcgov.org/polaris/search/searchresults.aspx";
//    List<NameValuePair> parameters = new ArrayList<NameValuePair>();
//    parameters.add(new BasicNameValuePair("ctx", "1.1033.0.0.3"));
//    parameters.add(new BasicNameValuePair("type", "Advanced"));
//    parameters.add(new BasicNameValuePair("term", "9780743292337"));
//    parameters.add(new BasicNameValuePair("relation", "ALL"));
//    parameters.add(new BasicNameValuePair("by", "ISBN"));
//    parameters.add(new BasicNameValuePair("bool4", "AND"));
//    parameters.add(new BasicNameValuePair("limit", "TOM=*"));
//    parameters.add(new BasicNameValuePair("sort", "PD_TI"));
//    parameters.add(new BasicNameValuePair("page", "0"));    
//    result = httpClient.httpGet(url, parameters);

    String url = "http://librarycatalog.pwcgov.org/polaris/search/components/availability.aspx";
    List<NameValuePair> parameters = new ArrayList<NameValuePair>();
    parameters.add(new BasicNameValuePair("bibid", "167950"));
    parameters.add(new BasicNameValuePair("level", "local"));
    parameters.add(new BasicNameValuePair("morelink", "0"));
    parameters.add(new BasicNameValuePair("pos", "1"));
    parameters.add(new BasicNameValuePair("requestlevel", ""));
    result = httpClient.httpGet(url, parameters);

    try {
      FileWriter fw = new FileWriter(new File("myresults.txt"));
      fw.write(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("request completed\n" + result);
  }
}
