package com.rav.serialization.json;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ravi on 18/04/2017.
 */
public class JsonSerializer implements Serializer<JsonNode> {
    private ObjectMapper objectMapper = new ObjectMapper() ;
    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String s, JsonNode jsonNode) {
        byte[] bytes = null;

        if (jsonNode != null) {
            try {
                bytes = objectMapper.writeValueAsBytes(jsonNode);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
       return bytes;
    }

    public void close() {

    }
}
