package com.rav.twitter;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

/**
 * Created by ravi on 19/04/2017.
 */
public class TwitterPartition implements Partitioner {

    private int numPartitions;

    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        int key = Integer.parseInt((String)o);
        return key%numPartitions;
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {
       numPartitions = Integer.parseInt((String)map.get("numpartitions"));
    }
}
