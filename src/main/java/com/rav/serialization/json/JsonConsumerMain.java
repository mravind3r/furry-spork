package com.rav.serialization.json;

/**
 * Created by ravi on 18/04/2017.
 */
public class JsonConsumerMain {
    public static void main(String[] args) {
        JsonConsumer jsonConsumer = new JsonConsumer("json-topic");
        jsonConsumer.consumeJsonMessage();
        System.out.println("exiting... main...");
    }
}
