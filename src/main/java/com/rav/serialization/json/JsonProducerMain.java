package com.rav.serialization.json;

import com.rav.domain.Customer;

/**
 * Created by ravi on 18/04/2017.
 */
public class JsonProducerMain {
    public static void main(String[] args) {
        JsonProducer jsonProducer = new JsonProducer("json-topic");
        jsonProducer.synchSend(new Customer(101,"surender mandal"));
    }
}
