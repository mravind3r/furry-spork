package com.rav.partitioner;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeSerializer implements Serializer<JsonNode>{
   private ObjectMapper objectMapper = new ObjectMapper();

    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String s, JsonNode jsonNode) {
        try {
            return objectMapper.writeValueAsBytes(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public void close() {

    }
}