package com.rav.twitter;

/**
 * Created by ravi on 18/04/2017.
 */
public class TwitterMain {

    public static void main(String[] args) {
        String topic = "twitter-two-topic";
        String consumerKey = "82OpPE2A4UEIbfJoiH0RMfPur";
        String consumerSecret = "";
        String token = "59229651-qUcPTQw6nbzaopVuqgPCWJ4iIecdcfl28pS0u8Qqq";
        String secret = "";
        TwitterKafkaProducer producer = new TwitterKafkaProducer(topic,consumerKey,consumerSecret,token,secret);
        producer.sendQueuedMessages();
    }

}
