package com.training.server.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"name", "phone"})
public class SomeBean1 {
    private String name;
    private String phone;
    //JsonIgnore indicates that the annotated method or field is to be ignored
    @JsonIgnore
    private String salary;

    //generating constructor
    public SomeBean1(String name, String phone, String salary) {
        super();
        this.name = name;
        this.phone = phone;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}