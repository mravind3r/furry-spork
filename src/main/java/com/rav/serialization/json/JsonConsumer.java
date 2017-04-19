package com.rav.serialization.json;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rav.domain.Customer;

/**
 * Created by ravi on 18/04/2017.
 */
public class JsonConsumer {

    private String topic;
    private KafkaConsumer<String,JsonNode> kafkaConsumer;
    ObjectMapper objectMapper;

    public JsonConsumer(String topic) {
        this.topic = topic;

        Properties config = new Properties();
        config.put("bootstrap.servers","localhost:9092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer","com.rav.serialization.json.JsonDeSerializer");
        config.put("group.id","json");
        kafkaConsumer = new KafkaConsumer<String, JsonNode>(config);
        List<String> topics = Arrays.asList(topic);
        kafkaConsumer.subscribe(topics);
        objectMapper = new ObjectMapper();
    }

    public void consumeJsonMessage(){
        while(true){
            ConsumerRecords<String,JsonNode> crs = kafkaConsumer.poll(10);
            for(ConsumerRecord c:crs){
                JsonNode node = (JsonNode)c.value();
                try {
                    Customer customer = objectMapper.treeToValue(node,Customer.class);
                    System.out.println(customer);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
