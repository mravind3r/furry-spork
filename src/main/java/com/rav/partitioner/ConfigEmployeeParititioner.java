package com.rav.partitioner;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class ConfigEmployeeParititioner implements Partitioner {

    private static Map<String,Integer> departmentLookUp;

    public int partition(String s, Object key, byte[] bytes, Object value, byte[] bytes1, Cluster cluster) {
        String deptName = (String)key;
        int partition = departmentLookUp.get(deptName);
        return partition;
    }

    public void close() {

    }

    // this is used to read the config properties..
    /*
      Properties p = new Properties();
      p.put(config.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,ConfigEmployeeParititioner.class.getName());
      p.put("partition.0","marketing");
      p.put("partition.1","finance");
      p.put("partition.2","sales");
     */

    public void configure(Map<String, ?> map) {
        System.out.println("inside configure of paritioner...");
        departmentLookUp = new HashMap<String, Integer>();
        for(Map.Entry<String,?> entry: map.entrySet()){
            if(entry.getKey().startsWith("partition.")){
                String keyName = entry.getKey();
                String value = (String)entry.getValue();
                System.out.println( keyName.substring(10));
                int paritionId = Integer.parseInt(keyName.substring(10));
                departmentLookUp.put(value,paritionId);
            }
        }
    }

}
