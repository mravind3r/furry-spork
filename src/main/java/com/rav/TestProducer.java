package com.rav;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TestProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");




        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);
        DateFormat dtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
        String topic = "my_topic";

        int numberOfRecords = 100; // number of records to send
        long sleepTimer = 5; // how long you want to wait before the next record to be sent

        try {
            for (int i = 0; i < numberOfRecords; i++ ) {
                ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic, "Message:" + i + "sent at:" + dtFormat.format(new Date()));
                myProducer.send(record);
                Thread.sleep(sleepTimer);
            }
            // Thread.sleep(new Random(5000).nextLong()); // use if you want to randomize the time between record sends
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myProducer.close();
        }


    }


}
