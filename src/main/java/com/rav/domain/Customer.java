package com.rav.domain;

/**
 * Created by ravi on 17/04/2017.
 */
public class Customer {

    private int customerId;
    private String customerName;

    public Customer(){

    }

    public Customer(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    @Override public String toString() {
        return "Customer{" +
                        "customerId=" + customerId +
                        ", customerName='" + customerName + '\'' +
                        '}';
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerId() {

        return customerId;
    }
}
