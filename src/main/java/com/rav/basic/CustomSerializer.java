package com.rav.basic;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.rav.domain.Customer;

public class CustomSerializer implements  Serializer<Customer> {


    public void configure(Map<String, ?> map, boolean b) {
    }

    public byte[] serialize(String s, Customer data) {
        byte[] serializedName;
        try {

            int stringSize;
            if (data == null)
                return null;
            else {
                if (data.getCustomerName() != null) {
                    serializedName = data.getCustomerName().getBytes("UTF-8");
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
            }

            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
            buffer.putInt(data.getCustomerId());
            buffer.putInt(stringSize);
            buffer.put(serializedName);

            return buffer.array();
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Customer to byte[] " + e);
        }

    }

    public void close() {
    }
}
