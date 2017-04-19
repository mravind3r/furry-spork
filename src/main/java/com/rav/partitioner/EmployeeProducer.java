package com.rav.partitioner;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rav.domain.Employee;

/**
 * Created by ravi on 18/04/2017.
 */
public class EmployeeProducer {

    private String topic;
    private KafkaProducer<String,JsonNode> kafkaProducer;
    private ObjectMapper objectMapper;

    public EmployeeProducer(String topic) {
        this.topic = topic;
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,EmployeeSerializer.class.getName());
        config.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,EmployeePartitioner.class.getName());
        kafkaProducer = new KafkaProducer<String, JsonNode>(config);
        objectMapper = new ObjectMapper();
    }

    public void asynchSend(Employee e){
        JsonNode value = objectMapper.valueToTree(e);
        ProducerRecord<String,JsonNode> record = new ProducerRecord<String, JsonNode>
                                                     (this.topic,e.getDeptName(),value);
        kafkaProducer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("message sent to partition:" + recordMetadata.partition());
            }
        });
    }

    public void synchSend(Employee e){
        JsonNode value = objectMapper.valueToTree(e);
        ProducerRecord<String,JsonNode> record = new ProducerRecord<String, JsonNode>
                        (this.topic,e.getDeptName(),value);
        try {
            RecordMetadata r = kafkaProducer.send(record).get();
            System.out.println(r.partition());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
    }


}
