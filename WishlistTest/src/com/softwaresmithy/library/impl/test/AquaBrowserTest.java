package com.softwaresmithy.library.impl.test;

import com.softwaresmithy.library.impl.AquaBrowser;

public class AquaBrowserTest {
    public static void main(String... args) {
        AquaBrowser ab = new AquaBrowser();
//		ab.init("http://catalog.columbuslibrary.org/rss.ashx?q=%s&uilang=en");
        //Not found
        System.out.println(ab.checkAvailability("9781934861288"));

        //Found
        System.out.println(ab.checkAvailability("9780756406547"));
    }
}
