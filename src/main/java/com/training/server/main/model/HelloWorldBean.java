package com.training.server.main.model;

public class HelloWorldBean {
    public String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" + "message='" + message + '\'' + '}';
    }
}
