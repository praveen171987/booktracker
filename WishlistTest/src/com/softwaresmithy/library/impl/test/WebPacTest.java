package com.softwaresmithy.library.impl.test;

import android.test.AndroidTestCase;
import com.softwaresmithy.library.impl.WebPac;

import java.net.URL;

public class WebPacTest extends AndroidTestCase {
    /*
    * http://innopac.fauquiercounty.gov/search/?searchtype=i&searcharg=9781934861288&searchscope=1
    */
    String[] libUrls = new String[]{
            "http://libraries.etsu.edu/search/?searchtype=i&amp;searcharg=%s&amp;searchscope=1", // watauga regional
            "http://library.chesterfield.gov/search/?searchtype=i&amp;searcharg=%s&amp;searchscope=11", // chesterfield co, http://www.iii.com
            "http://millennium.fallschurchva.gov/search/?searchtype=i&amp;searcharg=%s", // falls church, encore,
            "http://innopac.fauquiercounty.gov/search/?searchtype=i&amp;searcharg=%s&amp;searchscope=6", // fauquier co, WebPAC PRO Innovative Interfaces
            "http://aries.jmrl.org/search/?searchtype=i&amp;searcharg=%s&amp;searchscope=9", // jefferson-madison regional WebPAC PRO  Innovative Interfaces, Inc.
            "http://libsys.arlingtonva.us/search/?searchtype=i&amp;searcharg=%s&amp;searchscope=1", // encore, innovative interfaces, inc.
            "http://innopac.hal.org/search/?searchtype=i&amp;searcharg=%s&amp;searchscope=6" // washington co
    };

    public void testIsbnSearch() throws Exception {
        for (String libUrl : libUrls) {
            URL url = new URL(libUrl);
            WebPac webpac = new WebPac();
//			webpac.init(libUrl);
//			STATUS status = webpac.checkAvailability("9781934861288");
//			System.out.println(url.getHost()+": "+status);
        }
    }
}
