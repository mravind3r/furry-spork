package com.rav.serialization.json;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rav.domain.Customer;

public class JsonProducer {

    private String topic;
    private KafkaProducer<String,JsonNode> kafkaProducer;
    ObjectMapper objectMapper;

    public JsonProducer(String topic) {
        this.topic = topic;
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class.getName());
        kafkaProducer = new KafkaProducer<String, JsonNode>(config);
        objectMapper = new ObjectMapper();
    }


    public void synchSend(Customer customer){
        JsonNode jsonNode = objectMapper.valueToTree(customer);
        ProducerRecord<String,JsonNode> producerRecord = new ProducerRecord<String, JsonNode>(this.topic,jsonNode);
        try {
            RecordMetadata recordMetadata = kafkaProducer.send(producerRecord).get();
            System.out.println(recordMetadata.topic());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }




}
