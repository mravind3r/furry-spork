package com.rav.source.file;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

/**
 * Created by ravi on 19/04/2017.
 */
public class PageViewPartitioner implements Partitioner {
    private int numPartitions;
    @Override public int partition(String s, Object key, byte[] bytes, Object value, byte[] bytes1, Cluster cluster) {
        String payload = (String) value;
        return payload.length()%numPartitions;
    }

    @Override public void close() {

    }

    @Override public void configure(Map<String, ?> map) {
        numPartitions = Integer.parseInt((String)map.get("numpartitions"));
    }
}
