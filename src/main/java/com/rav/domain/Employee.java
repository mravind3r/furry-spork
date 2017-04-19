package com.rav.domain;

public class Employee {

  private int id;
  private String name;
  private String deptName;

    public Employee(int id, String name, String deptName) {
        this.id = id;
        this.name = name;
        this.deptName = deptName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeptName() {
        return deptName;
    }
}
