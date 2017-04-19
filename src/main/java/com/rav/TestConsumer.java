package com.rav;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * Created by ravi on 16/04/2017.
 */
public class TestConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id","test");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
        try {

            String topic = "my_topic";

            List<String> topics = Arrays.asList(topic);
            kafkaConsumer.subscribe(topics);
            while (true) {

                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10);
                for (ConsumerRecord r : consumerRecords) {
                    System.out.println(r.key() + ":" + r.value());
                }

            }
        }finally {
            kafkaConsumer.close();
        }


    }
}
