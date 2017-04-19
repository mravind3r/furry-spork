package com.rav.basic;

/**
 * Created by ravi on 17/04/2017.
 */
public class BasicConsumerWithCustDeserializerMain {
    public static void main(String[] args) {
        BasicConsumerWithCustomDeserializer deserializerMain = new BasicConsumerWithCustomDeserializer("3-part-topic");
        deserializerMain.receive();
        System.out.println("exiting main..");
    }
}
