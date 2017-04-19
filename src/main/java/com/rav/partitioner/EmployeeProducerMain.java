package com.rav.partitioner;

import com.rav.domain.Employee;

/**
 * Created by ravi on 18/04/2017.
 */
public class EmployeeProducerMain {
    public static void main(String[] args) {
        EmployeeProducer employeeProducer = new EmployeeProducer("3-part-employee-topic");
        Employee john = new Employee(1, "john", "marketing");
        Employee joe = new Employee(2, "joe", "finance");
        Employee jill = new Employee(3, "jill", "sales");
        employeeProducer.synchSend(john);
        employeeProducer.synchSend(joe);
        employeeProducer.synchSend(jill);
    }
}
