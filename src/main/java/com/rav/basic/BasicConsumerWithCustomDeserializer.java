package com.rav.basic;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.rav.domain.Customer;

public class BasicConsumerWithCustomDeserializer {

    private String topic;
    private KafkaConsumer<String, Customer> kafkaConsumer;

    public BasicConsumerWithCustomDeserializer(String topic) {
        this.topic = topic;
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "customserial");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.rav.basic.CustomDeSerializer");
        kafkaConsumer = new KafkaConsumer<String, Customer>(props);
        List<String> topics = Arrays.asList(topic);
        kafkaConsumer.subscribe(topics);
    }


    public void receive(){
        while(true){
            ConsumerRecords<String,Customer> customerConsumerRecords = kafkaConsumer.poll(10);
            for(ConsumerRecord cr:customerConsumerRecords){
                System.out.println(cr.value().toString());
            }
        }
    }


}
