package com.rav.source.file;

import java.io.IOException;

public class PageViewMain {

    public static void main(String[] args) {

        try {
            PageViewProducer pageViewProducer = new PageViewProducer("page-view-topic","src/main/resources/pageviews");
            pageViewProducer.tailFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
