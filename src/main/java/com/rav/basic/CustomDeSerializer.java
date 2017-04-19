package com.rav.basic;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.rav.domain.Customer;

public class CustomDeSerializer implements Deserializer<Customer>{

    public void configure(Map<String, ?> map, boolean b) {

    }

    public Customer deserialize(String s, byte[] bytes) {

        byte[] idBytes = new byte[4];
        for( int i=0;i<4;i++) {
          idBytes[i] = bytes[i];
        }
        byte[] nameBytes = new byte[bytes.length-7];
        for( int i=0;i<nameBytes.length;i++) {
            nameBytes[i] = bytes[i+7];
        }
        int id = ByteBuffer.wrap(bytes).getInt();
        String name = new String(nameBytes);
        Customer customer = new Customer(id,name);

        return customer;
    }

    public void close() {

    }
}
