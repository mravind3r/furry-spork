package com.rav.partitioner;

import com.rav.domain.Employee;

/**
 * Created by ravi on 18/04/2017.
 */
public class ConfigEmployeeProducerMain {

    public static void main(String[] args) {
        ConfigEmployeeProducer employeeProducer = new ConfigEmployeeProducer("3-part-employee-topic");
        Employee homer = new Employee(1, "homer", "marketing");
        Employee bart = new Employee(2, "bart", "finance");
        Employee marge = new Employee(3, "marge", "sales");
        employeeProducer.synchSend(homer);
        employeeProducer.synchSend(bart);
        employeeProducer.synchSend(marge);
    }
}
