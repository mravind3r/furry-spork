package com.rav.partitioner;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ravi on 18/04/2017.
 */
public class EmployeeDeSerializer implements Deserializer<JsonNode> {

    private ObjectMapper objectMapper = new ObjectMapper();
    public void configure(Map<String, ?> map, boolean b) {

    }

    public JsonNode deserialize(String s, byte[] bytes) {

        try {
            return objectMapper.readTree(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void close() {

    }
}
