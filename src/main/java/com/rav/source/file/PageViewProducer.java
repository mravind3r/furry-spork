package com.rav.source.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * Created by ravi on 19/04/2017.
 */
public class PageViewProducer {

    private String topic;
    private KafkaProducer<String,String> kafkaProducer;
    private String filePath;
    private Stream<String> fileStream;


    public PageViewProducer(String topic,String filePath) throws IOException {
        this.topic = topic;
        this.filePath = filePath;

        // file init
        fileStream = Files.lines(Paths.get(this.filePath));
        Properties config = new Properties();
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,PageViewPartitioner.class.getName());
        config.put("numpartitions","3"); // useful for custom partition
        kafkaProducer = new KafkaProducer<String, String>(config);
    }


    public void tailFile(){
      try {
          fileStream.parallel()
                          .forEach(s -> {
                              ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(this.topic, s);
                              kafkaProducer.send(producerRecord, ((recordMetadata, e) -> {
                                  System.out.println("Topic:" + recordMetadata.topic() + " partition:" + recordMetadata.partition());
                              }));
                          });
      }finally{
          kafkaProducer.close();
      }

    }


}
