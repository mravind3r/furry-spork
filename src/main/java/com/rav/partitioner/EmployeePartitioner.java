package com.rav.partitioner;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class EmployeePartitioner implements Partitioner{

    public int partition(String s, Object key, byte[] bytes, Object value, byte[] bytes1, Cluster cluster) {

        String deptNameKey = (String)key;

        if(deptNameKey!=null){
            return deptNameKey.length()%3; // cos i know there are 3 parititons, find a way to do it dynamically inject it
        }
        return 0;
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}