package com.rav;

/**
 * Created by ravi on 13/04/2017.
 */
public interface KafkaEventStore {
     String serializeEvent(String event); // can be customer object as input
     void sendEvent(String event);
}
