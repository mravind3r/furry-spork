package com.rav.basic;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.rav.domain.Customer;

public class BasicProducerWithCustomSerializer {

    private String topic;
    private KafkaProducer<String,Customer> kafkaProducer;

    public BasicProducerWithCustomSerializer(String topic) {
        this.topic = topic;
        Properties config = new Properties();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "com.rav.basic.CustomSerializer");
        kafkaProducer = new KafkaProducer<String, Customer>(config);
    }


    public void synchSend(Customer customer){
        try{
            ProducerRecord<String,Customer> producerRecord = new ProducerRecord<String, Customer>(this.topic,customer);
            kafkaProducer.send(producerRecord);
        }finally{
            kafkaProducer.close();
        }
    }


}
