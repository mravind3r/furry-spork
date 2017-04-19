package com.rav.basic;

/**
 * Created by ravi on 17/04/2017.
 */
public class BasicKafkaMain {

    // these have been tested using the consumer utility
  //  ~/tools/kafka_2.11-0.10.2.0/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic 3-part-topic  --from-beginning

    public static void main(String[] args) {


        BasicProducer basicProducer = new BasicProducer("3-part-topic");
        long t0 = System.currentTimeMillis();
        basicProducer.postSynchMessage("Synched  8 Message");
        long t1 = System.currentTimeMillis();
        System.out.println("time taken basicProducer.postSynchMessage=" + (t1-t0)); // 192 // 193

        long t2 = System.currentTimeMillis();
        basicProducer.postAsynchMessage("async  8 Message");
        long t3 = System.currentTimeMillis();
        System.out.println("time taken basicProducer.postSynchMessage=" + (t3-t2)); //14


    }
}
