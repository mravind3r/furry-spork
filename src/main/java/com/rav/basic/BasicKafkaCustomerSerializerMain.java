package com.rav.basic;

import com.rav.domain.Customer;

/**
 * Created by ravi on 17/04/2017.
 */
public class BasicKafkaCustomerSerializerMain {

    public static void main(String[] args) {
        Customer c = new Customer(1,"abcdef");
        BasicProducerWithCustomSerializer customSerializer = new BasicProducerWithCustomSerializer("3-part-topic");
        customSerializer.synchSend(c);
    }
}
